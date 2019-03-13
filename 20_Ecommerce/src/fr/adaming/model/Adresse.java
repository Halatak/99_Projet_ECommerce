package fr.adaming.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable 
@Access(AccessType.FIELD)
public class Adresse {

	//déclaration des attributs
	private int numVoie;
	private String nomVoie;
	@Column(name="cpVoie_adresse")
	private int cpVoie;
	private String ville;

	//Pas de constructeurs dans une classe embedded !
	
	//getters & setters
	public int getNumVoie() {
		return numVoie;
	}
	public void setNumVoie(int numVoie) {
		this.numVoie = numVoie;
	}
	public String getNomVoie() {
		return nomVoie;
	}
	public void setNomVoie(String nomVoie) {
		this.nomVoie = nomVoie;
	}
	public int getCpVoie() {
		return cpVoie;
	}
	public void setCpVoie(int cpVoie) {
		this.cpVoie = cpVoie;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	
	//ToString
	@Override
	public String toString() {
		return "Adresse [numVoie=" + numVoie + ", nomVoie=" + nomVoie + ", cpVoie=" + cpVoie + ", ville=" + ville + "]";
	}
	
	
}
