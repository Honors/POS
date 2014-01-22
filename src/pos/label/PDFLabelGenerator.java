package pos.label;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;

public class PDFLabelGenerator {

	private int numberDown, numberAcross;
	private float topMargin, sideMargin, labelHeight, labelWidth, verticalPitch, horizontalPitch;
	
	public PDFLabelGenerator(float topMargin, float sideMargin, int numberDown, int numberAcross, float labelHeight, float labelWidth, float verticalPitch, float horizontalPitch){
		this.topMargin = topMargin;
		this.sideMargin = sideMargin;
		this.numberDown = numberDown;
		this.numberAcross = numberAcross;
		this.labelHeight = labelHeight;
		this.labelWidth = labelWidth;
		this.verticalPitch = verticalPitch;
		this.horizontalPitch = horizontalPitch;
	}
	
	public void generateSingleItemLabelPage(String path, String name, BufferedImage image, String upc){
		int px_topMargin = (int)(topMargin * 72);
		int px_sideMargin = (int)(sideMargin * 72);
		int px_labelHeight = (int)(labelHeight * 72);
		int px_labelWidth = (int)(labelWidth * 72);
		int px_verticalPitch = (int)(verticalPitch * 72);
		int px_horizontalPitch = (int)(horizontalPitch * 72);
		
		try {
			PDDocument document = new PDDocument();
			PDPage page = new PDPage();
			document.addPage(page);
			
			PDJpeg upcImage = new PDJpeg(document, image);
			PDPageContentStream contentStream = new PDPageContentStream(document, page);
			
			contentStream.beginText();
			contentStream.setFont(PDType1Font.HELVETICA, 14);
			contentStream.moveTextPositionByAmount(65, 774);
			contentStream.drawString("Name: " + name);
			contentStream.endText();
			
			for(int down = 0; down < numberDown; down++){
				for(int across = 0; across < numberAcross; across++){
					int imgX = px_sideMargin + across * px_horizontalPitch + (px_labelWidth - image.getWidth()) / 2;
					int imgY = 792 - (px_topMargin + down * px_verticalPitch + (px_labelHeight - image.getHeight()) / 2 + image.getHeight());
					contentStream.drawImage(upcImage, imgX, imgY);
				}
			}
			
			contentStream.close();
			
			document.save(path);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
}
