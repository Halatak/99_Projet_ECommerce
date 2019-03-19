package fr.adaming.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.model.Administrateur;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;
import fr.adaming.service.IAdministrateurService;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "adminMB")
@RequestScoped

public class AdministrateurManagedBean implements Serializable {

	// Déclaration des attributs
	private Administrateur admin;

	// transformation de l'association UML en JAVA
	@ManagedProperty("#{adminService}")
	private IAdministrateurService adminService;
	@ManagedProperty("#{prodService}")
	private IProduitService prodService;
	@ManagedProperty("#{catService}")
	private ICategorieService catService;

	// constructeur
	public AdministrateurManagedBean() {
		this.admin = new Administrateur();
	}

	// setters pour l'injection dépendance
	public void setAdminService(IAdministrateurService adminService) {
		this.adminService = adminService;
	}

	public void setProdService(IProduitService prodService) {
		this.prodService = prodService;
	}

	public void setCatService(ICategorieService catService) {
		this.catService = catService;
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

			// récupérer la liste des catégories
			List<Categorie> listeCat = catService.getAllCategorie();
			// mettre la liste dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeCatSession", listeCat);
			
			// récupérer la liste des produits
			List<Produit> listeProd = prodService.getAllProduits();
			// mettre la liste dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeProdSession", listeProd);

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
