package fr.adaming.dao;

import java.util.List;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

public interface IProduitDao {

	public Produit addProduit(Produit prod);
	
	public int deleteProduit(Produit prod);
	
	public int updateProduit(Produit prod);
	
	public Produit getProduit(Produit prod);
	
	public List<Produit> getAllProduits();
	
	public List<Produit> getProdByCat(Categorie cat);
	
	public List<Produit> getProdByIdCat(Categorie cat);
	
	public List<Produit> getProdByKeyWord(String keyWord);
	
	
}
