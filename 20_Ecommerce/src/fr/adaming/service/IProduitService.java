package fr.adaming.service;

import java.util.List;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

public interface IProduitService {

	public Produit addProduit(Produit prod, Categorie cat);
	
	public int deleteProduit(Produit prod, Categorie cat);
	
	public int updateProduit(Produit prod, Categorie cat);
	
	public Produit getProduit(Produit prod, Categorie cat);
		
	public List<Produit> getAllProduits();
	
	public List<Produit> getProdByCat(Categorie cat);
}
