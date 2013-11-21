package pos.core;

import java.awt.image.BufferedImage;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

public class UPCGenerator {

	/**
	 * Generates a Code 128 Barcode
	 * @param upc The string of the generated barcode
	 * @return a BufferedImage of the barcode
	 * @throws BarcodeException error in generating barcode
	 * @throws OutputException error in image writing the barcode
	 */
	public static BufferedImage generateBarcode(String code) throws BarcodeException, OutputException{
		Barcode barcode = BarcodeFactory.createCode128(code);
		barcode.setBarHeight(100);
		barcode.setDrawingText(false);
		return BarcodeImageHandler.getImage(barcode);
	}

}
