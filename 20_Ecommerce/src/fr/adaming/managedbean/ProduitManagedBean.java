package fr.adaming.managedbean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "prodMB")
@RequestScoped

public class ProduitManagedBean implements Serializable {

	// Déclaration des attributs
	private Produit prod;
	private Categorie cat;

	// Transformation de l'association UML en Java

	IProduitService sProd;

	// Constructeur vide

	public ProduitManagedBean() {
		this.prod = new Produit();
	}

	// Getters & setters

	public Produit getProd() {
		return prod;
	}

	public void setProd(Produit prod) {
		this.prod = prod;
	}

	// Méthodes métier
	public String ajouterProduit() {
		return null;
	}

	public String supprimerProduit() {
		return null;
	}

	public String modifierProduit() {
		return null;
	}

	public String rechercherProduit() {
		return null;
	}

}
