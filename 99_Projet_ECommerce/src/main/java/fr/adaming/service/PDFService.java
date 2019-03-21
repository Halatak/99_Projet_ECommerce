package fr.adaming.service;

import java.io.FileOutputStream;
import java.io.IOException;

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
	
	//Méthode pour créer le PDF et le placer à l'emplacement ci-dessus
	public int creerPDF (Produit prod) {
		
		//Vérificateur de fonctionnement
		int verifPDF = 0;
		
		Document doc = new Document();
		
		try {
			
			PdfWriter.getInstance(doc, new FileOutputStream(chemin));
			doc.open();
			
			//Ajout du contenu
			doc.add(new Paragraph("Fiche du nouveau produit."));
			
			verifPDF++;
	
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException eio) {
			eio.printStackTrace();
		}
		
		//Fermeture du document
		doc.close();
		
		return verifPDF;
	}
	
	//Méthode pour créer un tableau dans le pdf
	public static PdfPTable AddTableau() {
		
		//créer un tableau (deux colonnes)
		PdfPTable tableau = new PdfPTable(2);
		
		//créer un objet cellule
		PdfPCell cellule;
		
		//Fusion des cellules de la premiere ligne
		cellule = new PdfPCell(new Phrase("Descriptif du nouveau produit"));
		cellule.setColspan(2);
		tableau.addCell(cellule);
		
		
		return tableau;
	}
	
}
