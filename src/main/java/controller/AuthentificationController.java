package controller;

import java.util.Date;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/authentification")
@SessionAttributes({"nom","dateDerniereConnexion"})
public class AuthentificationController {
	@Autowired
	Properties usersProperties;
	
	@RequestMapping("/execute")
	public String execute(String login, String password, Model model) {
		model.addAttribute("login", login);
		model.addAttribute("password", password);
		if (usersProperties.containsKey(login) && usersProperties.getProperty(login).equals(password)) {
			model.addAttribute("nom", login);
			model.addAttribute("dateDerniereConnexion", new Date());
			return "accueil";
		} else {
			model.addAttribute("AuthentificationError", "Le login n'est pas égal au mot de passe !");
			return "login";
		}
	}
	
	@RequestMapping("/connect")
	public String connect() {
		return "login";
	}
	
	@RequestMapping("/deconnect")
	public String deconnect(SessionStatus modelSession) {
		modelSession.setComplete();
		return this.connect();
	}
	
}
