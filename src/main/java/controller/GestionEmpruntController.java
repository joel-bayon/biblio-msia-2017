package controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import entity.BusinessException;
import entity.Emprunt;
import repository.jpa.AdherentDao;
import repository.jpa.EmpruntDao;
import repository.jpa.LivreDao;
import service.Bibliotheque;

@Controller
@RequestMapping("/gestionEmprunt")
public class GestionEmpruntController {
	
	@Autowired
	LivreDao livreDao;
	@Autowired
	AdherentDao adherentDao;
	@Autowired
	EmpruntDao empruntDao;
	@Autowired
	Bibliotheque bibliotheque;
	
	@RequestMapping("/home")
	public String home(String a, Model model) {
		if (a == null)
		{
			model.addAttribute("a", a);
			model.addAttribute("allEmprunt", empruntDao.getAllEmpruntsEnCours());
			return "gestionEmprunt/home";
		}
		else
		{
			if (a.equals("cours")) {
				model.addAttribute("a", a);
				model.addAttribute("allEmprunt", empruntDao.getAllEmpruntsEnCours());
				return "gestionEmprunt/home";
			} 
			else if (a.equals("termines")) {
				model.addAttribute("a", a);
				model.addAttribute("allEmprunt", empruntDao.getAllEmpruntsTermines());
				return "gestionEmprunt/home";
			}
			else {
				model.addAttribute("a", a);
				model.addAttribute("allEmprunt", empruntDao.getAllEmpruntsEnCours());
				return "gestionEmprunt/home";
			}
		}
	}
	
	@RequestMapping("/edition")
	public String edition(Integer id, Date debut, Date fin, Integer livre, Integer adherent, String a, Model model) {
		// Modification
		if (id != null) {
			if (adherent != null) {
				Emprunt unEmprunt = empruntDao.findOne(id);
				bibliotheque.transfererEmprunt(unEmprunt.getAdherent().getId(), unEmprunt.getLivre().getId(), adherent);
				return this.home(null, model);
			} else {
				model.addAttribute("unEmprunt", empruntDao.findOne(id));
				model.addAttribute("allLivre", livreDao.findAll());
				model.addAttribute("allAdherent", adherentDao.getAdherentNoQuotaMax());
				return "gestionEmprunt/edition";
			}
		// Création
		} else {
			if (livre != null && adherent != null) {
				bibliotheque.emprunterLivre(livre, adherent);
				return this.home(null, model);
			} else {
				model.addAttribute("allLivre", livreDao.getLivreNotEmprunt());
				model.addAttribute("allAdherent", adherentDao.getAdherentNoQuotaMax());
				return "gestionEmprunt/edition";
			}
		}
	}
	
	@RequestMapping("/restituer")
	public String restituer(Integer idLivre, Integer idAdherent, Model model) {
		try {
			bibliotheque.restituerLivre(idLivre, idAdherent);
		} catch (BusinessException e) {
			model.addAttribute("deleteError", "Impossible de restituer le livre !");
		}
		return this.home(null, model);
	}

}
