package fr.adaming.dao;

import org.hibernate.SessionFactory;

import fr.adaming.model.Administrateur;

public class AdministrateurDaoImpl implements IAdministrateurDao {

	private SessionFactory sf;
	
	@Override
	public Administrateur isExist (Administrateur adminIn) {
		
		//requete HQL
		String req = 
		
		//R�cup�rer un objet de type query
		Query query = em.createQuery(req);
		
		//passage des param�tres
		query.setParameter("pMail", adminIn.getMail());
		query.setParameter("pMdp", adminIn.getMdp());
		
		try {
		return (Administrateur) query.getSingleResult();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	
}
