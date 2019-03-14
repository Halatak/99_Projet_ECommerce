package fr.adaming.service;

import java.util.List;

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

	@Override
	public List<Categorie> getAllCategorie() {
		return catDao.getAllCategorie();
	}

	@Override
	public Categorie getCategorieById(Categorie cat) {
		return catDao.getCategorieById(cat);
	}

}
