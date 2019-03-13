package fr.adaming.service;

import javax.ejb.Local;

import fr.adaming.model.Categorie;

@Local
public interface ICategorieService {

	public Categorie addCategorie(Categorie cat);

	public int deleteCategorie(Categorie cat);

	public int updateCategorie(Categorie cat);

}
