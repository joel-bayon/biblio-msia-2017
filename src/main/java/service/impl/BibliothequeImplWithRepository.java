package service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import entity.Adherent;
import entity.BusinessException;
import entity.Emprunt;
import entity.Livre;
import repository.jpa.AdherentDao;
import repository.jpa.EmpruntDao;
import repository.jpa.LivreDao;
import service.Bibliotheque;

@Service
@Transactional
public class BibliothequeImplWithRepository implements Bibliotheque {
	final int maxLivreIdentique;
	final int maxEmpruntAdherent;
	
	@Autowired
	LivreDao livreDao;
	@Autowired
	AdherentDao adherentDao;
	@Autowired
	EmpruntDao empruntDao;
	
	@Autowired
	public BibliothequeImplWithRepository(@Value("3") Integer maxLivreIdentique, @Value("5") Integer maxEmpruntAdherent) {
		this.maxLivreIdentique = maxLivreIdentique; 
		this.maxEmpruntAdherent = maxEmpruntAdherent;
	}

	@Override
	public int getMaxEmpruntAdherent() { 
		return maxEmpruntAdherent;
	}
	
	@Override
	public int getMaxLivreIdentique() {
		return maxLivreIdentique;
	}
	
	@Override
	public int ajouterLivre(Livre livre)  {
		//RG : maxLivreIdentique ?
		if(livreDao.getCount(livre.getTitre(), livre.getAuteur()) == maxLivreIdentique ) 
			throw new BusinessException("Impossible de cr�ee 3 livres identiques !", null);
		livreDao.save(livre);
		return livre.getId();
	}


	@Override
	public void retirerLivre(int idLivre) {
		//RG : livre vacant ?
		if(empruntDao.getEmpruntEnCoursByLivre(idLivre) != null) 
			throw new BusinessException("BibliothequeImpl.retirerLivre", null); 
		//détruire d'abord les anciens emprunts puis le live ....
		empruntDao.delete(empruntDao.findByLivre(idLivre));
		livreDao.delete(idLivre);
	}

	@Override
	public int ajouterAdherent(Adherent adherent) {
		//RG est déjà Present ?
//		if(adherentDao.isPresent(adherent))
		if(adherentDao.isPresent(adherent.getNom(), adherent.getPrenom()))
			throw new BusinessException("BibliothequeImpl.ajouterAdherent", null); 
		adherentDao.save(adherent);
		return adherent.getId();
	}


	@Override
	public void retirerAdherent(int idAdherent) {
		//RG : adherent vacant ?
		if(empruntDao.getEmpruntsEnCoursByAdherent(idAdherent).size() > 0)
			throw new BusinessException("BibliothequeImpl.retirerAdherent", null); 
		//détruire d'abord les anciens emprunts puis l'adhérent ....
		empruntDao.delete(empruntDao.findByAdherent(idAdherent));
		adherentDao.delete(idAdherent);	
	}

	@Override
	public void emprunterLivre(int idLivre, int idAdherent) {
		//RG : maxEmpruntAdherent ?
		if( empruntDao.findByAdherent(idAdherent).size() == maxEmpruntAdherent)
			throw new BusinessException("BibliothequeImpl.emprunterLivre", null); 
		//RG : livre déjà emprunté !
		if(empruntDao.getEmpruntEnCoursByLivre(idLivre) != null)
			throw new BusinessException("BibliothequeImpl.emprunterLivre", null); 

		empruntDao.save(new Emprunt(livreDao.findOne(idLivre), adherentDao.findOne(idAdherent)));
	}
	
	@Override
	public void restituerLivre(int idLivre, int idAdherent) {
		// RG : un emprunt doit exist� avec le couple idLivre/idAdherent
		Emprunt emprunt = empruntDao.getEmpruntEnCoursByLivre(idLivre);
		if(emprunt == null || emprunt.getAdherent().getId() != idAdherent)
			throw new BusinessException("BibliothequeImpl.restituerLivre", null);  /// A finir ...
		emprunt.setFin(new Date());
		empruntDao.save(emprunt);
		
	}

	@Override
	public void transfererEmprunt(int idAdherentPrecedent, int idLivre,
			int idAdherentSuivant) {
		restituerLivre(idLivre, idAdherentPrecedent);
		emprunterLivre(idLivre, idAdherentSuivant);	
	}

}
