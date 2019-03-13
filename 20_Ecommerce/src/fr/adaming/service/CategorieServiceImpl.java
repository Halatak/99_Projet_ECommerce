package fr.adaming.service;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.model.Categorie;

@Stateful
public class CategorieServiceImpl implements ICategorieService {

	//Transformation de l'association UML en Java
	@EJB
	ICategorieDao catDao;
	
	@Override
	public Categorie addCategorie(Categorie cat) {
		return catDao.addCategorie(cat);
	}

	@Override
	public int deleteCategorie(Categorie cat) {
		return catDao.deleteCategorie(cat);
	}

	@Override
	public int updateCategorie(Categorie cat) {
		return catDao.updateCategorie(cat);
	}

}
