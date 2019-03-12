package fr.adaming.service;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IAdministrateurDao;
import fr.adaming.model.Administrateur;

@Stateful
public class AdministrateurServiceImpl implements IAdministrateurService {

	//Transformation de l'association UML en JAVA
	@EJB
	private IAdministrateurDao adminDao;
	
	@Override
	public Administrateur isExist(Administrateur adminIn) {
		return adminDao.isExist(adminIn);
	}

}
