package fr.adaming.model;

import java.util.List;

public class Panier {

	// association uml en java
	private List<LigneCommande> listeLc;

	// constructeur vide
	public Panier() {
		super();
	}

	// getter et setter
	public List<LigneCommande> getListeLc() {
		return listeLc;
	}

	public void setListeLc(List<LigneCommande> listeLc) {
		this.listeLc = listeLc;
	}

	@Override
	public String toString() {
		return "Panier [listeLc=" + listeLc + "]";
	}
	
	

}
