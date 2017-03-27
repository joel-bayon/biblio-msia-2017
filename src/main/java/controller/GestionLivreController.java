package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import entity.BusinessException;
import entity.Livre;
import repository.jpa.LivreDao;
import service.Bibliotheque;

@Controller
@RequestMapping("/gestionLivre")
public class GestionLivreController {
	
	@Autowired
	LivreDao livreDao;
	@Autowired
	Bibliotheque bibliotheque;
	
	@RequestMapping("/home")
	public String home(Model model) {
		model.addAttribute("allLivre", livreDao.findAll());
		return "gestionLivre/home";
	}
	
	@RequestMapping("/edition")
	public String edition(Integer id, String titre, String parution, String auteur, String a, Model model) {
		// Modification
		if (id != null) {
			if (titre != null && parution != null && auteur != null) {
				int parutionInt = Integer.parseInt(parution);
				livreDao.update(id, titre, auteur, parutionInt);
				return this.home(model);
			} else {
				model.addAttribute("unLivre", livreDao.findOne(id));
				return "gestionLivre/edition";
			}
		// Création
		} else {
			if (titre != null && parution != null && auteur != null) {
				int parutionInt = Integer.parseInt(parution);
				Livre newLivre = new Livre(titre, parutionInt, auteur);
				try {
					bibliotheque.ajouterLivre(newLivre);
				} catch (BusinessException e) {
					model.addAttribute("deleteError", "Un livre ne peut être créée que 3 fois !");
				} 
				return this.home(model);
			} else {
				return "gestionLivre/edition";
			}
		}
	}
	
	@RequestMapping("/delete")
	public String delete(Integer id, Model model) {
		try {
			bibliotheque.retirerLivre(id);
		} catch (BusinessException e) {
			model.addAttribute("deleteError", "Il faut d'abord restituer le livre avant de pouvoir le supprimer !");
		}
		return this.home(model);
	}

}
