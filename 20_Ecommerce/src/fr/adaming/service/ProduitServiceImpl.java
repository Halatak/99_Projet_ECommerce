package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IProduitDao;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;
@Stateful
public class ProduitServiceImpl implements IProduitService {

	//transformation uml java
	@EJB
	private IProduitDao prodDao;
		
	@Override
	public Produit addProduit(Produit prod, Categorie cat) {
		//lier les objets en java
		prod.setCat(cat);;
		return prodDao.addProduit(prod);
	}

	@Override
	public int deleteProduit(Produit prod, Categorie cat) {
		//lier les objets en java
		prod.setCat(cat);;
		return prodDao.deleteProduit(prod);
	}

	@Override
	public int updateProduit(Produit prod, Categorie cat) {
		prod.setCat(cat);;
		return prodDao.updateProduit(prod);
	}

	@Override
	public Produit getProduit(Produit prod, Categorie cat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produit> getAllProduits() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produit> getProdByCat(Categorie cat) {
		// TODO Auto-generated method stub
		return null;
	}

}
