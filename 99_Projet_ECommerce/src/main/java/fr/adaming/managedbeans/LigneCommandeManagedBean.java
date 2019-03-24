package fr.adaming.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "lcMB")
@RequestScoped

public class LigneCommandeManagedBean {

	private Panier pan;
	private HttpSession maSession;
	private LigneCommande lc;
	private Produit prod;

	@ManagedProperty(value = "#{prodService}")
	private IProduitService prodService;

	// setters pour l'injection dépendance
	public void setProdService(IProduitService prodService) {
		this.prodService = prodService;
	}

	// getters & setters
	public Panier getPan() {
		return pan;
	}

	public void setPan(Panier pan) {
		this.pan = pan;
	}

	public HttpSession getMaSession() {
		return maSession;
	}

	public void setMaSession(HttpSession maSession) {
		this.maSession = maSession;
	}

	public LigneCommande getLc() {
		return lc;
	}

	public void setLc(LigneCommande lc) {
		this.lc = lc;
	}

	public Produit getProd() {
		return prod;
	}

	public void setProd(Produit prod) {
		this.prod = prod;
	}

	public IProduitService getProdService() {
		return prodService;
	}

//	// Méthodes
//	public String ajouterLigneCommandeMB() {
//
//	int test = 0;
//	// Ici on check si on a déjà un panier dans la session
//	if(maSession.getAttribute("panierSession")!=null) {
//		// Si on en a un, on le récupère, lui et sa liste de ligneCommande,
//		this.pan = (Panier) maSession.getAttribute("panierSession");
//		this.pan.getListeLigneCommande();
//	} else {
//		// Ici, on a pas de panier, on en crée donc un nouveau, auquel on
//		// ajoute une liste de ligne commande vide, pour éviter d'avoir des
//		// erreurs de type "NullPointerException" (quand on va chercher la
//		// liste, si on crée que le panier, il n'en trouvera pas)
//		this.pan = new Panier();
//		List<LigneCommande> listelc = new ArrayList<LigneCommande>();
//		this.pan.setListeLigneCommande(listelc);
//	} try {
//		// Ici, on check que le produit existe bien en allant le consulter
//		// dans la Dao, et on check sa quantité pour vérifier qu'il y ait
//		// assez. L'attribut test, permettra de faire un test après le
//		// catch
//		prod = prodService.consulterProduitService(prod);
//		test = prod.getQuantite() - lc.getQuantite();
//	}catch(Exception e)	{
//		// Si produit insuffisant, ou inexistant, catch, et message.
//		e.printStackTrace();
//		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ce produit n'existe pas"));
//		return "accueilclient";
//	}
//	// quantité? supérieur, on continue, inférieure, voir plus bas
//	if(test>=0)
//	{
//			int size = 0;
//
//			try {
//			
//				// Et donc on ajoute une nouvelle ligne de commande au panier
//				// dans laquelle on met le prix et la quantitée
//				ligneCommande.setPrix(produit.getPrix() * ligneCommande.getQuantite());
//				this.ligneCommande.setProduit(produit);
//				this.listeLigneCommande.add(ligneCommande);
//				panier.getListeLigneCommande().addAll(listeLigneCommande);
//			
//			// Enfin on met le panier dans la session et on retourne à l'accueil
//			// panier
//			maSession.setAttribute("panierSession", panier);
//			return "panier";
//
//		} else
//		// Quantité limitée !
//		{
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pas assez de produit en stock"));
//			return "testajoutlc";
//		}
//	}
//
//}
//
//	public String lierCommandeLigneCommandeMB() {
//		int size = 0;
//		// Bon déjà, on récupère le panier
//		this.panier = (Panier) maSession.getAttribute("panierSession");
//		try {
//			// Si le panier à bien une liste de commande, on récupère sa taille
//			// sinon, exception
//			size = panier.getListeLigneCommande().size();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		// On vérifie que la liste soit existante
//		if (size > 0) {
//			// Si oui, on set la date du jour à notre commande
//			commande.setDateCommande(date);
//			// Ici, on ajoute la commande à toute les lignes de commande du
//			// panier
//			for (int i = 0; i < panier.getListeLigneCommande().size(); i++) {
//				this.panier.getListeLigneCommande().get(i).setCommande(commande);
//			}
//			// Enfin, on met le panier modifié dans la session
//			maSession.setAttribute("panierSession", panier);
//			return "testclient";
//			// Si pas de liste dans le panier, message d'erreur
//		} else {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pas de produit choisis"));
//			return "accueilproduit";
//		}
//
//	}
//	
//	public String lierClientCommandeMB() {
//		int verif = 0;
//		// Comme d'hab, on récupère le panier
//		this.panier = (Panier) maSession.getAttribute("panierSession");
//		try {
//			// On check qu'une commande à bien été crée pour y ajouter un client
//			// (d'ailleurs le setId sert surement à rien, sinon, la commande
//			// aura forcément une ligne commande à la ligne 0 de la liste)
//			// Si pas de commande, go catch
//			commande.setIdCommande(panier.getListeLigneCommande().get(0).getCommande().getIdCommande());
//			commandeService.consulterCommandeParIDService(commande);
//			// Du coup, si la commande existe, On check si un client est
//			// connecté dans la session
//			
//				// Ici, pour toutes les lignes commande du panier, on ajoute le
//				// client à la commande(qui est la même commande pour toute les
//				// lignes)
//				for (int i = 0; i < panier.getListeLigneCommande().size(); i++) {
//					this.panier.getListeLigneCommande().get(i).getCommande().setClient(client);
//				}
//				// On ajoute le client et le panier dans la BD
//				clientService.ajouterClientService(client, adresse);
//				maSession.setAttribute("panierSession", panier);
//				verif = 1;
//			}
//			// Pas de commande, on renvoi un message d'erreur
//		} catch (Exception e) {
//			e.printStackTrace();
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pas de commandes en cour"));
//			return "site";
//		}
//		// Ici, on vérifie qu'on a bien ajouté un client, mais le programme fait
//		// qu'il est impossible de ne pas en ajouter un pour l'instant (19/03),
//		// ça peut en ajouter un vide, need validator, requested
//		if (verif != 0) {
//			return "validerpanier";
//		} else {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pas de client choisi"));
//			return "testclient";
//		}
//	}
//	
}
