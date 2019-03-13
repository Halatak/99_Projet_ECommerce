package fr.adaming.dao;


import fr.adaming.model.Categorie;

public interface ICategorieDao {

	public Categorie addCategorie(Categorie cat);
	
	public int deleteCategorie(Categorie cat);
	
	public int updateCategorie(Categorie cat);
	
	
}
