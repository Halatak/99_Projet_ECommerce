package fr.adaming.model;

public class SendMailSSL {

	public SendMailSSL(){
		super();
	}
	
	public int sendMail(String destinataire,String message) throws Exception{
		int a=0;
		Mail.send("ozzy.osborn1948@gmail.com", "ozzy1948", "cangi@laposte.net", "Fiche produit", "coucou");
		
		a++;
		return a;
	}
}
