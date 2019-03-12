package fr.adaming.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Administrateur;

@Stateless
public class AdministrateurDaoImpl implements IAdministrateurDao {

	@PersistenceContext(unitName="PU_EC") //cette annotation permet d'injecter un em instancié par le conteneur EJB
	private EntityManager em;
	
	
	@Override
	public Administrateur isExist (Administrateur adminIn) {
		//requete JPQL
		String req = "SELECT a FROM Administrateur AS a WHERE a.mail = :pMail AND a.mdp = :pMdp";
		
		//Récupérer un objet de type query
		Query query = em.createQuery(req);
		
		//passage des paramètres
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
