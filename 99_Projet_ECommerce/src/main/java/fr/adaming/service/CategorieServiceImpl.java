package fr.adaming.service;

import java.util.List;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.model.Categorie;


public class CategorieServiceImpl implements ICategorieService {

	//Transformation de l'association UML en Java

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
