package pos.label;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import org.apache.pdfbox.exceptions.COSVisitorException;
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
	
	public void generateSingleItemLabelPage(String path, String name, BufferedImage image, String upc) throws IOException, COSVisitorException{
		int px_topMargin = (int)(topMargin * 72.0f);
		int px_sideMargin = (int)(sideMargin * 72.0f);
		int px_labelHeight = (int)(labelHeight * 72.0f);
		int px_labelWidth = (int)(labelWidth * 72.0f);
		int px_verticalPitch = (int)(verticalPitch * 72.0f);
		int px_horizontalPitch = (int)(horizontalPitch * 72.0f);
		
		System.out.println(px_verticalPitch);
		
		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		document.addPage(page);
		
		PDJpeg upcImage = new PDJpeg(document, image);
		
		PDPageContentStream contentStream = new PDPageContentStream(document, page);
		contentStream.beginText();
		contentStream.setFont(PDType1Font.HELVETICA, 10);
		contentStream.moveTextPositionByAmount(65, 765);
		contentStream.drawString("Name: " + name);
		contentStream.endText();
		
		for(int down = 0; down < numberDown; down++){
			for(int across = 0; across < numberAcross; across++){
				int imgX = px_sideMargin + across * px_horizontalPitch + (px_labelWidth - upcImage.getWidth()) / 2;
				int imgY = 792 - (px_topMargin + down * px_verticalPitch + (px_labelHeight - upcImage.getHeight()) / 2 + upcImage.getHeight());
				int deltaY = (792 - (px_topMargin + (down + 1) * px_verticalPitch + (px_labelHeight - upcImage.getHeight()) / 2 + upcImage.getHeight())) - imgY;
				contentStream.drawImage(upcImage, imgX, imgY);
				System.out.println(deltaY);
			}
		}
		
		contentStream.close();
		
		document.save(path);
		document.close();

	}
		
}
