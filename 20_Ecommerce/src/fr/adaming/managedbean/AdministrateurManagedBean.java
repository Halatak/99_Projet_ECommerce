package fr.adaming.managedbean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.model.Administrateur;
import fr.adaming.service.IAdministrateurService;

@ManagedBean(name = "adminMB")
@RequestScoped

public class AdministrateurManagedBean implements Serializable {

	// transformation de l'association UML en JAVA
	@EJB
	private IAdministrateurService adminService;

	// Déclaration des attributs
	private Administrateur admin;

	// constructeur

	public AdministrateurManagedBean() {
		this.admin = new Administrateur();
	}

	// Getters & setters
	public Administrateur getAdministrateur() {
		return admin;
	}

	public void setAdministrateur(Administrateur admin) {
		this.admin = admin;
	}

	// Déclaration des méthodes métiers
	public String seConnecter() {
		// chercher le Administrateur par son mail et son mdp
		Administrateur adminOut = adminService.isExist(admin);

		if (adminOut != null) {

			// mettre le Administrateur dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adminSession", adminOut);

			return "accueilAdmin";

		} else {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Erreur de connexion de l'administrateur."));

			return "loginAdmin";

		}

	}

}
