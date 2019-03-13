package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.codec.binary.Base64;

import fr.adaming.model.Categorie;

@Stateless
public class CategorieDaoImpl implements ICategorieDao {

	@PersistenceContext(unitName = "PU_EC")
	private EntityManager em;

	@Override
	public Categorie addCategorie(Categorie cat) {
		em.persist(cat);
		return cat;
	}

	@Override
	public int deleteCategorie(Categorie cat) {
		String req = "DELETE Categorie c WHERE c.id=:cId";
		// recuperer un objet de type query
		Query queryListe = em.createQuery(req);

		queryListe.setParameter("cId", cat.getIdCategorie());

		return queryListe.executeUpdate();
	}

	@Override
	public int updateCategorie(Categorie cat) {
		String req = "UPDATE Categorie c SET c.nomCategorie = :cNomCategorie, c.description = :cDescription WHERE c.idCategorie = :cId";
		// recuperer un objet de type query
		Query queryListe = em.createQuery(req);

		// passage des parametres
		queryListe.setParameter("cNomCategorie", cat.getNomCategorie());
		queryListe.setParameter("cDescription", cat.getDescription());
		queryListe.setParameter("c.idCategorie", cat.getIdCategorie());

		return queryListe.executeUpdate();
	}

	@Override
	public List<Categorie> getAllCategorie() {

		// requete JPQL
		String req = "SELECT c FROM Categorie AS c";

		// r�cup�rer l'objet query
		Query query = em.createQuery(req);

		List<Categorie> listeCat = query.getResultList();

		// boucle pour récupérer les pixels de l'image
		for (Categorie c : listeCat) {
			c.setImg("data:image/png;base64,"+Base64.encodeBase64String(c.getPhoto()));
		}

		return listeCat;
	}

	@Override
	public Categorie getCategorieById(Categorie cat) {

		// requete JPQL
		String req = "SELECT c FROM Categorie AS c WHERE c.idCategorie =:cId";

		// r�cup�rer l'objet query
		Query query = em.createQuery(req);

		// passage des paramères
		query.setParameter("cId", cat.getIdCategorie());
		
		Categorie catOut = (Categorie) query.getSingleResult();
		catOut.setImg("data:image/png;base64," + Base64.encodeBase64String(catOut.getPhoto()));

		return catOut;
	}

}
