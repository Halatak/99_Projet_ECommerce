package fr.adaming.service;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import fr.adaming.model.Produit;

@Service("pdfService")
@Transactional

public class PDFService implements IPDFService {

	public static final String chemin = "C:\\Users\\IN-BR-003\\FicheProduit.pdf";

	// Méthode pour créer le PDF et le placer à l'emplacement ci-dessus
	public int creerPDF(Produit prod) {

		// Vérificateur de fonctionnement
		int verifPDF = 0;
		
		Produit prodTab = prod;
		Document doc = new Document();

		try {
			// créer le pdf à l'endroit voulu sur le pc (chemin)
			PdfWriter.getInstance(doc, new FileOutputStream(chemin));
			
			//ouvrir le doc pour faire des modifs
			doc.open();

			// Ajout du contenu
			doc.addTitle("Fiche du nouveau produit");
			doc.add(new Paragraph("Bonjour, ci-dessous un tableau résumant le dernier produit ajouté."));
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));

			// ajout du tableau (méthode en dessous)
			doc.add(addTableau(prodTab));

			verifPDF++;

		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException eio) {
			eio.printStackTrace();
		}

		// Fermeture du document
		doc.close();

		// 1 si tout va bien
		return verifPDF;
	}

	// Méthode pour créer un tableau dans le pdf
	public static PdfPTable addTableau(Produit prod) {

		// créer un tableau (deux colonnes)
		PdfPTable tableau = new PdfPTable(2);

		// créer un objet cellule
		PdfPCell cellule;

		// Fusion des cellules de la premiere ligne
		cellule = new PdfPCell(new Phrase("Descriptif du nouveau produit"));
		cellule.setColspan(2);
		tableau.addCell(cellule);

		// Remplissage du tableau
		// Tableau de deux colonnes, donc on rempli la colonne de gauche, puis
		// celle de droite.
		tableau.addCell("Désignation");
		tableau.addCell(prod.getDesignation());
		
		tableau.addCell("Description");
		tableau.addCell(prod.getDescription());
		
		Produit produit = prod;
//		produit.setImg("data:image/png;base64,"+Base64.encodeBase64String(prod.getPhoto()));
		tableau.addCell("Image");
		tableau.addCell(produit.getImg());
		
		tableau.addCell("Quantité");
		tableau.addCell(Integer.toString(prod.getQuantite()));

		//Transformer double en String
		double prix = prod.getPrix();
		String prix1 = String.valueOf(prix);
		tableau.addCell("Prix");
		tableau.addCell(prix1);

		tableau.addCell("Numéro Catégorie");
		tableau.addCell(Integer.toString(prod.getCat().getIdCategorie()));
		
		return tableau;
	}

}
