package fr.adaming.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="commandes")
public class Commande implements Serializable {

	//attributs
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_com")
	private int idCommande;
	private Date dateCommande;
	
	//association uml en java
	@ManyToOne
	@JoinColumn(name="cl_id", referencedColumnName="id_cl")
	private Client cl;
	
	@ManyToMany(mappedBy="listeCom",cascade=CascadeType.PERSIST)
	private List<Produit> listeProd;
	
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


	public Client getCl() {
		return cl;
	}


	public void setCl(Client cl) {
		this.cl = cl;
	}


	public List<Produit> getListeProd() {
		return listeProd;
	}


	public void setListeProd(List<Produit> listeProd) {
		this.listeProd = listeProd;
	}


	@Override
	public String toString() {
		return "Commande [idCommande=" + idCommande + ", dateCommande=" + dateCommande + "]";
	}
	
	
}
