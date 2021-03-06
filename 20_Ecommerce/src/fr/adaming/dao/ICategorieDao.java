package fr.adaming.dao;
import java.util.List;

import javax.ejb.Local;
import fr.adaming.model.Categorie;

@Local
public interface ICategorieDao {

	public Categorie addCategorie(Categorie cat);
	
	public int deleteCategorie(Categorie cat);
	
	public int updateCategorie(Categorie cat);
	
	public List<Categorie> getAllCategorie();
	
	public Categorie getCategorieById(Categorie cat);
	
}
