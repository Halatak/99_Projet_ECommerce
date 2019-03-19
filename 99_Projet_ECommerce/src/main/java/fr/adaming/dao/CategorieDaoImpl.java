package fr.adaming.dao;

import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Categorie;

@Repository
public class CategorieDaoImpl implements ICategorieDao {

	@Autowired
	private SessionFactory sf;

	@Override
	public Categorie addCategorie(Categorie cat) {

		// récupérer la session du formateur
		Session s = sf.getCurrentSession();

		// rendre l'objet persistant
		s.save(cat);

		return cat;
	}

	@Override
	public int deleteCategorie(Categorie cat) {

		// récupérer la session
		Session s = sf.getCurrentSession();

		String req = "DELETE FROM Categorie AS c WHERE c.id=:cId";

		// recuperer un objet de type query
		Query query = s.createQuery(req);

		query.setParameter("cId", cat.getIdCategorie());

		return query.executeUpdate();
	}

	@Override
	public int updateCategorie(Categorie cat) {

		// récupérer la session du formateur
		Session s = sf.getCurrentSession();

		// requete hql
		String req = "UPDATE Categorie AS c SET c.nomCategorie = :cNomCategorie, c.description = :cDescription WHERE c.idCategorie = :cId";

		// recuperer un objet de type query
		Query query = s.createQuery(req);

		// passage des parametres
		query.setParameter("cNomCategorie", cat.getNomCategorie());
		query.setParameter("cDescription", cat.getDescription());
		query.setParameter("c.idCategorie", cat.getIdCategorie());

		return query.executeUpdate();
	}

	@Override
	public List<Categorie> getAllCategorie() {

		// récupérer la session
		Session s = sf.getCurrentSession();

		// requete HQL
		String req = "FROM Categorie";

		// rï¿½cupï¿½rer l'objet query
		Query query = s.createQuery(req);

		List<Categorie> listeCat = query.list();

		// boucle pour rÃ©cupÃ©rer les pixels de l'image
		for (Categorie c : listeCat) {
			c.setImg("data:image/png;base64," + Base64.encodeBase64String(c.getPhoto()));
		}

		return listeCat;
	}

	@Override
	public Categorie getCategorieById(Categorie cat) {

		// récupérer la session
		Session s = sf.getCurrentSession();

		// requete JPQL
		String req = "FROM Categorie AS c WHERE c.idCategorie =:cId";

		// rï¿½cupï¿½rer l'objet query
		Query query = s.createQuery(req);

		// passage des paramÃ¨res
		query.setParameter("cId", cat.getIdCategorie());

		Categorie catOut = (Categorie) query.uniqueResult();

		catOut.setImg("data:image/png;base64," + Base64.encodeBase64String(catOut.getPhoto()));

		return catOut;
	}

}
