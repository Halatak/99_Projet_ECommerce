package fr.adaming.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Administrateur;

@Repository
public class AdministrateurDaoImpl implements IAdministrateurDao {

	@Autowired
	private SessionFactory sf;

	// le setter pour l'injection d�pendance
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public Administrateur isExist (Administrateur adminIn) {
		
		//r�cup�rer la session
		Session s = sf.getCurrentSession();
		
		//requete HQL
		String req = "FROM Administrateur AS admin WHERE admin.mail=:pMail AND admin.mdp=:pMdp";
		
		//R�cup�rer un objet de type query
		Query query = s.createQuery(req);
		
		//passage des param�tres
		query.setParameter("pMail", adminIn.getMail());
		query.setParameter("pMdp", adminIn.getMdp());
		
		try {
		return (Administrateur) query.uniqueResult();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
