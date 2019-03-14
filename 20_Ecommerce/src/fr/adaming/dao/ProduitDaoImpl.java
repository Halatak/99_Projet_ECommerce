package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.codec.binary.Base64;

import fr.adaming.model.Produit;
@Stateless
public class ProduitDaoImpl implements IProduitDao {

	@PersistenceContext(unitName="PU_EC") //cette annotation permet d'injecter un em instanci� par le conteneur ejb
	private EntityManager em;
	
	@Override
	public Produit addProduit(Produit prod) {
		em.persist(prod);
		return prod;
	}

	@Override
	public int deleteProduit(Produit prod) {
		String req="DELETE Produit p WHERE p.id=:pId";
		//recuperer un objet de type query
		Query queryListe=em.createQuery(req);
		
		queryListe.setParameter("pId", prod.getIdProduit());
		return queryListe.executeUpdate();
	}

	@Override
	public int updateProduit(Produit prod) {
		String req="UPDATE Produit p SET p.designation=:pDesignation, p.description=:pDescription, p.prix=:pPrix, p.quantite=:pQuantite, p.selectionne=:pSelectionne, p.photo=:pPhoto WHERE p.id=:pId";
		//recuperer un objet de type query
		Query queryListe=em.createQuery(req);
		
		//passage des parametres
		queryListe.setParameter("pDesignation", prod.getDesignation());
		queryListe.setParameter("pDescription", prod.getDescription());
		queryListe.setParameter("pPrix", prod.getPrix());
		queryListe.setParameter("pQuantite", prod.getQuantite());
		queryListe.setParameter("pSelectionne", prod.isSelectionne());
		queryListe.setParameter("pPhoto", prod.getPhoto());
		queryListe.setParameter("pId", prod.getIdProduit());
		
		return queryListe.executeUpdate();
	}
	

	@Override
	public Produit getProduit(Produit prod) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produit> getAllProduits() {
		// construire la requete jsql
		String req="SELECT prod FROM Produit as prod";
						
		//recuperer un objet de type query
		Query queryListe=em.createQuery(req);
		
		List<Produit> listeProd = queryListe.getResultList();
		
		for (Produit p:listeProd){
			p.setImg("data:image/png;base64,"+Base64.encodeBase64String(p.getPhoto()));
		}

		return listeProd;
	}

	@Override
	public List<Produit> getProdByCat(Produit prod) {
		
		// construire la requete jsql
		String req="SELECT prod FROM Produit as prod WHERE prod.cat.id=:pIdCat";
		//recuperer un objet de type query
		Query queryListe=em.createQuery(req);
		
		List<Produit> listeProdByCat = queryListe.getResultList();
		
		for (Produit p:listeProdByCat){
			p.setImg("data:image/png;base64,"+Base64.encodeBase64String(p.getPhoto()));
		}
		return listeProdByCat;
	}

}
