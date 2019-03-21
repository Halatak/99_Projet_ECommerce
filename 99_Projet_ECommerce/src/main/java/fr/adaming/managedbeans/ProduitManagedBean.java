package fr.adaming.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.UploadedFile;

import fr.adaming.model.Administrateur;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;
import fr.adaming.model.SendMailSSL;
import fr.adaming.service.IPDFService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "prodMB")
@RequestScoped

public class ProduitManagedBean implements Serializable {

	// D�claration des attributs
	private Produit prod;
	private Categorie cat;
	private Administrateur admin;
	private List<Produit> listeProdByCat;
	private List<Produit> listeMetal;
	private String messageMail;
	private String keyWord;

	private UploadedFile image;

	// Transformation de l'association UML en Java
	@ManagedProperty("#{prodService}")
	IProduitService prodService;
	@ManagedProperty("#{pdfService}")
	IPDFService pdfService;

	private HttpSession maSession;

	// Constructeur vide

	public ProduitManagedBean() {
		this.prod = new Produit();
		this.cat = new Categorie();

	}

	//setters pour l'injection d�pendance
	public void setProdService(IProduitService prodService) {
		this.prodService = prodService;
	}
	
	public void setPdfService(IPDFService pdfService) {
		this.pdfService = pdfService;
	}

	@PostConstruct // cette annotation sert a dire que la methode doit etre
					// execut� apres l'instanciation de l'objet
	public void init() {
		maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.admin = (Administrateur) maSession.getAttribute("adminSession");
	}

	// Getters & setters

	public Produit getProd() {
		return prod;
	}

	public void setProd(Produit prod) {
		this.prod = prod;
	}

	public Categorie getCat() {
		return cat;
	}

	public void setCat(Categorie cat) {
		this.cat = cat;
	}

	public UploadedFile getImage() {
		return image;
	}

	public void setImage(UploadedFile image) {
		this.image = image;
	}

	public List<Produit> getListeProdByCat() {
		return listeProdByCat;
	}

	public void setListeProdByCat(List<Produit> listeProdByCat) {
		this.listeProdByCat = listeProdByCat;
	}

	public String getMessageMail() {
		return messageMail;
	}

	public void setMessageMail(String messageMail) {
		this.messageMail = messageMail;
	}

	public List<Produit> getListeMetal() {
		return listeMetal;
	}

	public void setListeMetal(List<Produit> listeMetal) {
		this.listeMetal = listeMetal;
	}

	// M�thodes m�tier
	public String ajouterProduit() {

		if (this.image != null) {
			this.prod.setPhoto(this.image.getContents());
		}

		// Cr�ation du produit
		Produit prodAjout = prodService.addProduit(prod, cat);

		//cr�ation du pdf
		pdfService.creerPDF(prodAjout);
		
		if (prodAjout != null) {
			// Actualisation de la liste des produits
			List<Produit> listeProd = prodService.getAllProduits();
			maSession.setAttribute("listeProdSession", listeProd);

			// Cr�ation du message du mail.
			String messageMail = "Bonjour, un produit a �t� ajout� : \n" + prodAjout.getDesignation() + ", quantit� : "
					+ prodAjout.getQuantite() + ", vendu au prix de : " + prodAjout.getPrix() + "�. \n"
					+ "Vous trouverez ci joint le descriptif du produit en pdf.\n"
					+ "Cordialement, l'Administrateur.";

			// Cr�ation d'un v�rificateur d'envoi de mail.
			int verifMail = 0;

			// Envoi du mail
			SendMailSSL sm = new SendMailSSL();
			try {
				// V�rif va servir � savoir si le mail est envoy� vu que la
				// fonction sendmail retourne un int
				verifMail = sm.sendMail("cangi@laposte.net", messageMail);
				System.out.println("Mail envoy�");
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (verifMail != 0) {
				System.out.println("Mail envoy�2");
			}
			return "accueilAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("l'ajout a �chou�"));
			return "ajoutProduit";
		}

	}

	public String supprimerProduit() {
		int verif = prodService.deleteProduit(prod, cat);
		if (verif != 0) {
			// recuperer la nouvelle liste
			List<Produit> liste = prodService.getAllProduits();
			maSession.setAttribute("listeProdSession", liste);

			return "accueilAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la suppression a �chou�"));
			return "supprProduit";
		}
	}

	public String modifierProduit() {
		int verif = prodService.updateProduit(prod, cat);
		if (verif != 0) {
			// recuperer la nouvelle liste
			List<Produit> liste = prodService.getAllProduits();
			maSession.setAttribute("listeProdSession", liste);

			return "accueilAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la suppression a �chou�"));
			return "modifProduit";
		}
	}

	public String rechercherProdByCat() {
		List<Produit> listeProdByCat = prodService.getProdByCat(cat);
		if (listeProdByCat != null) {

			return "rechercheCat";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la recherche a echoue"));
			return "site";
		}
	}

	public String rechercherProdByIdCat() {
		List<Produit> listeProdByCat = prodService.getProdByIdCat(cat);
		if (listeProdByCat != null) {
			switch (cat.getIdCategorie()) {
			case 1:
				listeMetal= prodService.getProdByIdCat(cat);
				return "catMetal";

			case 2:
				
				return "catRock";

			case 3:
			
				return "catHomme";

			case 4:
				
				return "catFemme";

			default:
				return "site";
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la recherche a echoue"));
			return "site";
		}
	}

	// public String envoieMail() {
	//
	// IAdministrateurService adminService = new AdministrateurServiceImpl();
	//
	// messageMail = "Bonjour," + this.prod.getQuantite() + "exemplaire(s) de "
	// + this.prod.getDesignation()
	// + " ont �t� ajout� au stock. Veuillez trouvez ci-joint la fiche
	// produit.";
	//
	// int verifMail = 0;
	// SendMailSSL sm = new SendMailSSL();
	// try {
	// verifMail = sm.sendMail("cangi@laposte.net", "test");
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// if (verifMail != 0) {
	// return "accueilAdmin";
	// } else {
	// FacesContext.getCurrentInstance().addMessage(null, new
	// FacesMessage("envoie �chou�"));
	// return "accueilAdmin";
	// }
	// }

	public String rechercherProdByKeyWord() {
		List<Produit> listeProdByKeyWord = prodService.getProdByKeyWord(keyWord);

		if (listeProdByKeyWord != null) {
			return "site";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la recherche a echoue"));
			return "site";
		}

	}
}
