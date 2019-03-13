package fr.adaming.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
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

	// transformation de l'association UML en JAVA
	@EJB
	private IAdministrateurService adminService;
	@EJB
	private IProduitService prodService;
	@EJB
	private ICategorieService catService;

	
	
	// D�claration des attributs
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

	// D�claration des m�thodes m�tiers
	public String seConnecter() {
		// chercher le Administrateur par son mail et son mdp
		Administrateur adminOut = adminService.isExist(admin);

		if (adminOut != null) {
			
			//r�cup�rer la liste des �tudiants de ce formateur
			List<Categorie> listeCat = catService.getAllCategorie();			
			//mettre la liste dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeCatSession", listeCat);
			
			//r�cup�rer la liste des �tudiants de ce formateur
			List<Produit> listeProd = prodService.getAllProduits();			
			//mettre la liste dans la session
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
