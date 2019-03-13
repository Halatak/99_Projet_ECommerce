package fr.adaming.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

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
	@EJB
	IProduitService prodService;
	
	
	private HttpSession maSession;

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
		Produit prodAjout=prodService.addProduit(prod, cat);
		if(prodAjout!=null){
			List<Produit> liste=prodService.getAllProduits();
			
			
			return "accueilAdmin";
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("l'ajout a échoué"));
			return "ajoutProduit";
		}
		
	}

	public String supprimerProduit() {
		int verif=prodService.deleteProduit(prod, cat);
		if(verif!=0){
			//recuperer la nouvelle liste
			List<Produit> liste=prodService.getAllProduits();
			
			
			return "accueilAdmin";
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la suppression a échoué"));
			return "supprProduit";
		}
	}

	public String modifierProduit() {
		int verif=prodService.updateProduit(prod, cat);
		if(verif!=0){
			//recuperer la nouvelle liste
			List<Produit> liste=prodService.getAllProduits();
			
			return "accueilAdmin";
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la suppression a échoué"));
			return "modifProduit";
		}
	}

	public String rechercherProduit() {
		return null;
	}

}
