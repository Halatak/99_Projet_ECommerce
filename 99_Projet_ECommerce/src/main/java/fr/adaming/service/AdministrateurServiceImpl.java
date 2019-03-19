package fr.adaming.service;

import fr.adaming.dao.IAdministrateurDao;
import fr.adaming.model.Administrateur;


public class AdministrateurServiceImpl implements IAdministrateurService {

	//Transformation de l'association UML en JAVA

	private IAdministrateurDao adminDao;
	
	@Override
	public Administrateur isExist(Administrateur adminIn) {
		return adminDao.isExist(adminIn);
	}

}
