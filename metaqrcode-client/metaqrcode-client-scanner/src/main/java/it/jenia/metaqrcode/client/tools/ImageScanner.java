package it.jenia.metaqrcode.client.tools;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.GenericMultipleBarcodeReader;
import com.google.zxing.multi.qrcode.QRCodeMultiReader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ImageScanner {
	
	private List<Double> scalingRetry;
	
	private boolean stopAtFirstQRCode;
	
	private Hashtable<DecodeHintType, Object> barcodeReaderHints;

	/**
	 * create an image scanner that make only one retry with original image (no scaling retry done)
	 */
	public ImageScanner() {
		super();
		scalingRetry = new ArrayList<Double>();
		scalingRetry.add(1d);
		stopAtFirstQRCode = true;
		initHints();
	}

	/**
	 * create an image scanner that make only scaling retry
	 * a basic scaling sequence should be (by ie) : 1d, 0.99d, 1.01d, 0.98d, 1.02d
	 * This means that first scan will be done at 100% of image size
	 * second at 99%
	 * third at 101%
	 * and so on...
	 */
	public ImageScanner(List<Double> scalingRetry) {
		super();
		this.scalingRetry = scalingRetry;
		this.stopAtFirstQRCode = true;
		initHints();
	}
	
	/**
	 * create an image scanner that make only one retry with original image (no scaling retry done)
	 */
	public ImageScanner(boolean stopAtFirstQRCode) {
		super();
		this.scalingRetry = new ArrayList<Double>();
		this.scalingRetry.add(1d);
		this.stopAtFirstQRCode = stopAtFirstQRCode;
		initHints();
	}

	/**
	 * create an image scanner that make only scaling retry
	 * a basic scaling sequence should be (by ie) : 1d, 0.99d, 1.01d, 0.98d, 1.02d
	 * This means that first scan will be done at 100% of image size
	 * second at 99%
	 * third at 101%
	 * and so on...
	 */
	public ImageScanner(boolean stopAtFirstQRCode, List<Double> scalingRetry) {
		super();
		this.scalingRetry = scalingRetry;
		this.stopAtFirstQRCode = stopAtFirstQRCode;
		initHints();
	}
	
	/**
	 * initialize list of hints needed by barcodescanner (zxing)
	 */
	private void initHints() {
		barcodeReaderHints = new Hashtable<DecodeHintType, Object>();
		Vector<BarcodeFormat> v = new Vector<BarcodeFormat>();
		v.add(BarcodeFormat.QR_CODE);
		barcodeReaderHints.put(DecodeHintType.POSSIBLE_FORMATS, v);
		// hints.put(DecodeHintType.PURE_BARCODE, Boolean.FALSE);
		barcodeReaderHints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
	}

	/**
	 * scan the given image multiple times (different scaling)
	 * 
	 * @param image the image to be canned
	 * @return a list of barcode (if found)
	 */
	public List<String> scanForQRCodes(InputStream is) throws IOException {
		BufferedImage image = ImageIO.read(is);
		return scanForQRCodes(image);
	}
	
	/**
	 * scan the given image multiple times (different scaling)
	 * 
	 * @param image the image to be canned
	 * @return a list of barcode (if found)
	 */
	public List<String> scanForQRCodes(BufferedImage image) throws IOException {
		log.debug("Scanning for qr codes using scaling : " + scalingRetry);
		List<String> ret = new ArrayList<String>();
		for (double perc : scalingRetry) {
			LuminanceSource source = new BufferedImageLuminanceSource(getScaledImage(image, perc));
			HybridBinarizer binarizer = new HybridBinarizer(source);

			BinaryBitmap bitmap = new BinaryBitmap(binarizer);
			GenericMultipleBarcodeReader reader = new GenericMultipleBarcodeReader(new QRCodeMultiReader());
			Result[] results = null;
			try {
				results = reader.decodeMultiple(bitmap, barcodeReaderHints);
			} catch (NotFoundException e) {
				log.debug("no qr code found on this image with scaling " + perc);
			}
			if (results != null) {
				for (Result result : results) {
					log.debug("Barcode found : " + result.getText() + "[" + result.getBarcodeFormat() + "]");
					ret.add(result.getText());
					if (stopAtFirstQRCode) {
						log.debug("stopAtFirstQRCode was setted, i will return to caller");
						break;
					}
				}
			}
		}
		log.debug("Scanning done, returning : " + ret);
		return ret;
	}

	/**
	 * try to modify image dimension to verify if the qrcode is better readable
	 * 
	 * @param image the image to be scaled
	 * @param perc x and y percent scaling
	 * @return the image scaled
	 * @throws IOException if something goes wrong
	 */
	private BufferedImage getScaledImage(BufferedImage image, double perc) throws IOException {
		return getScaledImage(image, perc, perc);
	}

	/**
	 * try to modify image dimension to verify if the qrcode is better readable
	 * 
	 * @param image the image to be scaled
	 * @param scaleX x parcent scaling
	 * @param scaleY y percent scaling
	 * @return the image scaled
	 * @throws IOException if something goes wrong
	 */
	private BufferedImage getScaledImage(BufferedImage image, double scaleX, double scaleY) throws IOException {
		
		if (scaleX==1d && scaleY==1d) return image;
		
		int newWidth = (int)(image.getWidth()*scaleX);
		int newHeight = (int)(image.getHeight()*scaleY);
		
	    AffineTransform scaleTransform = AffineTransform.getScaleInstance(scaleX, scaleY);
	    AffineTransformOp bilinearScaleOp = new AffineTransformOp(scaleTransform, AffineTransformOp.TYPE_BILINEAR);

	    BufferedImage ret = bilinearScaleOp.filter(image, new BufferedImage(newWidth, newHeight, image.getType()));
	    return ret;
	}

}
