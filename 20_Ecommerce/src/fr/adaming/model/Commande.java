package fr.adaming.model;

import java.util.Date;

public class Commande {

	//attributs
	private int idCommande;
	private Date dateCommande;
	
	//constructeurs
	public Commande() {
		super();
	}


	public Commande(Date dateCommande) {
		super();
		this.dateCommande = dateCommande;
	}


	public Commande(int idCommande, Date dateCommande) {
		super();
		this.idCommande = idCommande;
		this.dateCommande = dateCommande;
	}

	//getters et setters
	public int getIdCommande() {
		return idCommande;
	}


	public void setIdCommande(int idCommande) {
		this.idCommande = idCommande;
	}


	public Date getDateCommande() {
		return dateCommande;
	}


	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}


	@Override
	public String toString() {
		return "Commande [idCommande=" + idCommande + ", dateCommande=" + dateCommande + "]";
	}
	
	
}
