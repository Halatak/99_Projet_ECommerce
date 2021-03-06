package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.dao.IProduitDao;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Service("prodService")
@Transactional
public class ProduitServiceImpl implements IProduitService {

	//transformation uml java
	@Autowired
	private IProduitDao prodDao;
	
	@Autowired
	private ICategorieDao catDao;
	
	
		
	public void setProdDao(IProduitDao prodDao) {
		this.prodDao = prodDao;
	}

	public void setCatDao(ICategorieDao catDao) {
		this.catDao = catDao;
	}

	@Override
	public Produit addProduit(Produit prod, Categorie cat) {
		//lier les objets en java
		
		cat=catDao.getCategorieById(cat);
		prod.setCat(cat);
		return prodDao.addProduit(prod);
	}

	@Override
	public int deleteProduit(Produit prod, Categorie cat) {
		//lier les objets en java
		prod.setCat(cat);
		return prodDao.deleteProduit(prod);
	}

	@Override
	public int updateProduit(Produit prod, Categorie cat) {
		cat=catDao.getCategorieById(cat);
		prod.setCat(cat);
		return prodDao.updateProduit(prod);
	}

	@Override
	public Produit getProduit(Produit prod, Categorie cat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produit> getAllProduits() {
		return prodDao.getAllProduits();
	}

	@Override
	public List<Produit> getProdByCat(Categorie catIn) {
		Categorie catFind = catDao.getCategorieById(catIn);
		return prodDao.getProdByCat(catFind);
	}

	@Override
	public List<Produit> getProdByIdCat(Categorie cat) {
		Categorie catFind= catDao.getCategorieById(cat);
		return prodDao.getProdByIdCat(catFind);
	}

	@Override
	public List<Produit> getProdByKeyWord(String keyWord) {
		
		return prodDao.getProdByKeyWord(keyWord);
	}

}
