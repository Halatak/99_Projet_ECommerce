package fr.adaming.managedbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import fr.adaming.model.Categorie;
import fr.adaming.service.ICategorieService;

@ManagedBean(name="catMB")
@RequestScoped

public class CategorieManagedBean implements Serializable {

	//Déclaration des attributs
	private Categorie cat;
	
	//transformation de l'association UML en JAVA
	private ICategorieService catService;
	
}
