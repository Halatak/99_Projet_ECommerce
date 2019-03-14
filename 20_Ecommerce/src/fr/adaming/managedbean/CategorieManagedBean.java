package fr.adaming.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "catMB")
@RequestScoped

public class CategorieManagedBean implements Serializable {

	// Déclaration des attributs
	private Categorie cat;
	private Categorie cat1;
	private Categorie cat2;
	private Categorie cat3;
	private Categorie cat4;
	private UploadedFile image;

	// transformation de l'association UML en JAVA
	@EJB
	private ICategorieService catService;
	@EJB
	private IProduitService prodService;

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

	@PostConstruct // cette annotation sert � dire que la m�thode doit �tre
	// ex�cut�e apr�s l'instanciation de l'objet
	public void init() {
		cat1.setIdCategorie(1);
		cat2.setIdCategorie(2);
		cat3.setIdCategorie(3);
		cat4.setIdCategorie(4);
		List<Produit> listeProdMetal = prodService.getProdByCat(cat1);
		List<Produit> listeProdRock = prodService.getProdByCat(cat2);
		List<Produit> listeProdHomme = prodService.getProdByCat(cat3);
		List<Produit> listeProdFemme = prodService.getProdByCat(cat4);
	}

	// Déclaration des méthodes métiers

	public String ajouterCategorie() {

		System.out.println(this.image);
		if (this.image != null) {

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
