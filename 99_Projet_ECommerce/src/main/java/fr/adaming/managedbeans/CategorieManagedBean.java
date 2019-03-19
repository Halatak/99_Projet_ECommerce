package fr.adaming.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

import fr.adaming.model.Categorie;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "catMB")
@RequestScoped
public class CategorieManagedBean implements Serializable {

	// Déclaration des attributs
	private Categorie cat;
	// private Categorie cat1;
	// private Categorie cat2;
	// private Categorie cat3;
	// private Categorie cat4;
	private UploadedFile image;

	// transformation de l'association UML en JAVA
	@ManagedProperty(value = "#{catService}")
	private ICategorieService catService;
	@ManagedProperty(value = "#{prodService}")
	private IProduitService prodService;

	// setters pour l'injection dépendance
	public void setCatService(ICategorieService catService) {
		this.catService = catService;
	}

	public void setProdService(IProduitService prodService) {
		this.prodService = prodService;
	}

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

	// DÃ©claration des mÃ©thodes mÃ©tiers

	public String ajouterCategorie() {

		System.out.println(this.image);
		if (this.image != null) {

			this.cat.setPhoto(this.image.getContents());
		}

		Categorie catIn = catService.addCategorie(cat);

		if (catIn != null) {
			// rï¿½cupï¿½rer la nouvelle liste
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
			// rï¿½cupï¿½rer la nouvelle liste
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
			// rï¿½cupï¿½rer la nouvelle liste
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

	public String rechercherCatById() {

		Categorie catVerif = catService.getCategorieById(cat);

		if (catVerif != null) {
			// rï¿½cupï¿½rer la nouvelle liste
			List<Categorie> listeCat = catService.getAllCategorie();

			// mettre la liste dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeCatSession", listeCat);

			return "rechercheCat";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Echec de la recherche de la catégorie"));

			return "rechercheCat";
		}
	}

}
