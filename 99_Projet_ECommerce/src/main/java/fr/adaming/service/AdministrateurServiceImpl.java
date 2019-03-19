package fr.adaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IAdministrateurDao;
import fr.adaming.model.Administrateur;

@Service("adminService")
@Transactional
public class AdministrateurServiceImpl implements IAdministrateurService {

	// Transformation de l'association UML en JAVA
	@Autowired
	private IAdministrateurDao adminDao;

	//setter pour l'injection dépendance
	public void setAdminDao(IAdministrateurDao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public Administrateur isExist(Administrateur adminIn) {
		return adminDao.isExist(adminIn);
	}

}
