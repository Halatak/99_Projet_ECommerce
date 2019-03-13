package fr.adaming.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Categorie;

public class CategorieDaoImpl implements ICategorieDao {

	@PersistenceContext(unitName="PU_EC")
	private EntityManager em;
	
	@Override
	public Categorie addCategorie(Categorie cat) {
		em.persist(cat);
		return cat;
	}

	@Override
	public int deleteCategorie(Categorie cat) {
		String req="DELETE Categorie c WHERE c.id=:cId";
		//recuperer un objet de type query
		Query queryListe=em.createQuery(req);
		
		queryListe.setParameter("cId", cat.getIdCategorie());
		
		return queryListe.executeUpdate();
	}

	@Override
	public int updateCategorie(Categorie cat) {
		String req="UPDATE Categorie c SET c.nomCategorie = :cNomCategorie, c.description = :cDescription WHERE c.idCategorie = :cId";
		//recuperer un objet de type query
		Query queryListe=em.createQuery(req);
		
		//passage des parametres
		queryListe.setParameter("cNomCategorie", cat.getNomCategorie());
		queryListe.setParameter("cDescription", cat.getDescription());
		queryListe.setParameter("c.idCategorie", cat.getIdCategorie());
		
		return queryListe.executeUpdate();
	}

}
