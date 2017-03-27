package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import entity.Adherent;
import entity.BusinessException;
import repository.jpa.AdherentDao;
import service.Bibliotheque;

@Controller
@RequestMapping("/gestionAdherent")
public class GestionAdherentController {
	
	@Autowired
	AdherentDao adherentDao;
	@Autowired
	Bibliotheque bibliotheque;
	
	@RequestMapping("/home")
	public String home(Model model) {
		model.addAttribute("allAdherent", adherentDao.findAll());
		return "gestionAdherent/home";
	}
	
	@RequestMapping("/edition")
	public String edition(Integer id, String nom, String prenom, String tel, String email, String a, Model model) {
		// Modification
		if (id != null) {
			if (nom != null && prenom != null && tel != null && email != null) {
				adherentDao.update(id, nom, prenom, tel, email);
				return this.home(model);
			} else {
				model.addAttribute("unAdherent", adherentDao.findOne(id));
				return "gestionAdherent/edition";
			}
		// Création
		} else {
			if (nom != null && prenom != null && tel != null && email != null) {
				Adherent newAdherent = new Adherent(nom, prenom, tel, email);
				bibliotheque.ajouterAdherent(newAdherent);
				return this.home(model);
			} else {
				return "gestionAdherent/edition";
			}
		}
	}
	
	@RequestMapping("/delete")
	public String delete(Integer id, Model model) {
		try {
			bibliotheque.retirerAdherent(id);
		} catch (BusinessException e) {
			model.addAttribute("deleteError", "Il faut d'abord que l'adherent restitue les livres empruntés avant de pouvoir le supprimer !");
		}
		return this.home(model);
	}

}
