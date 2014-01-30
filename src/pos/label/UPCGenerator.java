package pos.label;

import java.awt.Color;
import java.awt.image.BufferedImage;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

public class UPCGenerator {
	
	/**
	 * Generates a Code 128 Barcode 121px x 50px (Approximately 1" x 0.5")
	 * @param upc The string of the generated barcode
	 * @return a BufferedImage of the barcode
	 * @throws Exception error in generating barcode
	 */
	public static BufferedImage generateBarcode(String code) throws Exception{
		Barcode barcode = BarcodeFactory.createCode128(code);
        barcode.setBarHeight(50);
        barcode.setBarWidth(1);
        barcode.setDrawingText(false);
        return BarcodeImageHandler.getImage(barcode);
	}

}
