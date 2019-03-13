package fr.adaming.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

import fr.adaming.model.Categorie;
import fr.adaming.service.ICategorieService;

@ManagedBean(name = "catMB")
@RequestScoped

public class CategorieManagedBean implements Serializable {

	// Déclaration des attributs
	private Categorie cat;
	private UploadedFile image;
	
	// transformation de l'association UML en JAVA
	@EJB
	private ICategorieService catService;

	// constructeur
	public CategorieManagedBean() {
		this.cat = new Categorie();
	}

	// getters & setters
	public Categorie getCat() {
		return cat;
	}

	public void setCat(Categorie cat) {
		this.cat = cat;
	}
	
	public UploadedFile getImage() {
		return image;
	}

	public void setImage(UploadedFile image) {
		this.image = image;
	}

	// Déclaration des méthodes métiers

	public String ajouterCategorie() {

		if(this.image != null) {
			this.cat.setPhoto(this.image.getContents());
		}
			
		Categorie catIn = catService.addCategorie(cat);

		if (catIn != null) {
			// r�cup�rer la nouvelle liste
			List<Categorie> listeCat = catService.getAllCategorie();

			// mettre la liste dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeCatSession", listeCat);

			return "accueilAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Echec de l'ajout de la catégorie"));

			return "ajoutCategorie";
		}
	}

	public String supprimerCategorie() {

		int verifSupprimer = catService.deleteCategorie(cat);

		if (verifSupprimer != 0) {
			// r�cup�rer la nouvelle liste
			List<Categorie> listeCat = catService.getAllCategorie();

			// mettre la liste dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeCatSession", listeCat);
			
			return "accueilAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Echec de la suppression de la catégorie"));

			return "supprCategorie";
		}
	}

	public String modifierCategorie() {

		int verifModifier = catService.updateCategorie(cat);

		if (verifModifier != 0) {
			// r�cup�rer la nouvelle liste
			List<Categorie> listeCat = catService.getAllCategorie();

			// mettre la liste dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeCatSession", listeCat);
			
			return "accueilAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Echec de la modification de la catégorie"));

			return "modifCategorie";

		}
	}

}
