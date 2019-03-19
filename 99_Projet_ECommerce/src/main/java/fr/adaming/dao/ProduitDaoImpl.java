package fr.adaming.dao;

import java.util.List;


import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Repository
public class ProduitDaoImpl implements IProduitDao {

	@Autowired
	private SessionFactory sf;
	
	
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public Produit addProduit(Produit prod) {
		// recuperer le bus(session de hibernate)
		Session s=sf.getCurrentSession();
		
		s.save(prod);
		return prod;
	}

	@Override
	public int deleteProduit(Produit prod) {
		// recuperer le bus(session de hibernate)
		Session s=sf.getCurrentSession();
		
		String req="DELETE Produit p WHERE p.id=:pId";
		//recuperer un objet de type query
		Query query=s.createQuery(req);
		
		query.setParameter("pId", prod.getIdProduit());
		return query.executeUpdate();
	}

	@Override
	public int updateProduit(Produit prod) {
		// recuperer le bus(session de hibernate)
		Session s=sf.getCurrentSession();
		
		String req="UPDATE Produit p SET p.designation=:pDesignation, p.description=:pDescription, p.prix=:pPrix, p.quantite=:pQuantite, p.selectionne=:pSelectionne, p.photo=:pPhoto WHERE p.id=:pId";
		//recuperer un objet de type query
		Query queryListe=s.createQuery(req);
		
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
		// recuperer le bus(session de hibernate)
		Session s=sf.getCurrentSession();
		
		// construire la requete jsql
		String req="SELECT prod FROM Produit as prod";
						
		//recuperer un objet de type query
		Query query=s.createQuery(req);
		
		List<Produit> listeProd = query.list();
		
		for (Produit p:listeProd){
			p.setImg("data:image/png;base64,"+Base64.encodeBase64String(p.getPhoto()));
		}

		return listeProd;
	}

	@Override
	public List<Produit> getProdByCat(Categorie cat) {
		// recuperer le bus(session de hibernate)
		Session s=sf.getCurrentSession();
		
		// construire la requete jsql
		String req="SELECT prod FROM Produit as prod WHERE prod.cat.id=:pIdCat";
		//recuperer un objet de type query
		Query query=s.createQuery(req);
		
		List<Produit> listeProdByCat = query.list();
		
		for (Produit p:listeProdByCat){
			p.setImg("data:image/png;base64,"+Base64.encodeBase64String(p.getPhoto()));
		}
		return listeProdByCat;
	}

	@Override
	public List<Produit> getProdByIdCat(Categorie cat) {
		// recuperer le bus(session de hibernate)
		Session s=sf.getCurrentSession();
				
		// construire la requete jsql
		String req="SELECT prod FROM Produit as prod WHERE prod.cat.id=:pIdCat";
		//recuperer un objet de type query
		Query query=s.createQuery(req);
		
		//passage des parametres
		query.setParameter("pIdCat", cat.getIdCategorie());
				
		List<Produit> listeProdByCat = query.list();
				
		for (Produit p:listeProdByCat){
					p.setImg("data:image/png;base64,"+Base64.encodeBase64String(p.getPhoto()));
		}
		return listeProdByCat;
			
		}

}
