package fr.adaming.dao;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

public interface IProduitDao {

	public int addProduit(Produit prod, Categorie cat);
	
	public int deleteProduit(Produit prod, Categorie cat);
	
	public int updateProduit(Produit prod, Categorie cat);
	
	public Produit getProduit(Produit prod, Categorie cat);
	
}
