package fr.adaming.service;

import fr.adaming.model.Categorie;

public interface ICategorieService {

	public Categorie addCategorie(Categorie cat);

	public int deleteCategorie(Categorie cat);

	public int updateCategorie(Categorie cat);

}
