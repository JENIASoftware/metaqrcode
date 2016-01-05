package it.jenia.metaqrcode.client.scanner;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;

import org.junit.Test;

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

public class TestMultibarcodeReader {
	
//	private static double[] scalingRetry = {1d, 0.975d, 1.025d, 0.95d, 1.05d, 0.925d, 1.075d, 0.9d, 1.1d};
	private static double[] scalingRetry = {1d, 0.99d, 1.01d, 0.98d, 1.02d};
//	private static double[] scalingRetry = {1d, 0.975d, 1.025d, 0.95d, 1.05d};
//	private static double[] scalingRetry = {100d};

	@Test
	public void test() throws NotFoundException, IOException {
		
		Map<Double,Integer> counts = new HashMap<Double,Integer>();
		for (double perc : scalingRetry) {
			counts.put(perc, 0);
		}
		
		long start = System.currentTimeMillis();
		
		int notfound = 0;
		boolean onlyOdd=false;
		boolean odd=true;
		
		Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
		Vector<BarcodeFormat> v = new Vector<BarcodeFormat>();
		v.add(BarcodeFormat.QR_CODE);
		hints.put(DecodeHintType.POSSIBLE_FORMATS, v);
		// hints.put(DecodeHintType.PURE_BARCODE, Boolean.FALSE);
		hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);

		File scanFolder = new File(
				"./src/test/resources");
		
		System.out.println(scanFolder.getAbsolutePath());

		File[] lista = null;

		lista = scanFolder.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".jpg");
			}
		});

		Arrays.sort(lista, new Comparator<File>() {
			@Override
			public int compare(File o1, File o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});

		if (lista != null && lista.length > 0) {
			for (File scanFolderFile : lista) {
				if (scanFolderFile.isFile()) {
					if ((onlyOdd && odd) || !onlyOdd) {
						boolean foundBarcode = false;
						BufferedImage image = ImageIO.read(scanFolderFile);
						for (double perc : scalingRetry) {
							LuminanceSource source = new BufferedImageLuminanceSource(getScaledImage(image, perc));
							HybridBinarizer binarizer = new HybridBinarizer(source);
		
							BinaryBitmap bitmap = new BinaryBitmap(binarizer);
							GenericMultipleBarcodeReader reader = new GenericMultipleBarcodeReader(new QRCodeMultiReader());
							Result[] results = null;
							try {
								results = reader.decodeMultiple(bitmap, hints);
							} catch (NotFoundException e) {
							}
							if (results != null) {
								for (Result result : results) {
									System.out.println(result.getText() + "[" + result.getBarcodeFormat() + "]");
								}
								counts.put(perc,counts.get(perc)+1);
								foundBarcode = true;
								break;
							}
						}
						if (!foundBarcode) {
							System.out.println("qrcode not found in " + scanFolderFile.getName());
							notfound++;
						}
					}
					odd=!odd;
				}
			}
		}
		
		long end = System.currentTimeMillis();
		System.out.println((onlyOdd?lista.length/2:lista.length) + " : " + (end-start));
		for (double perc : scalingRetry) {
			System.out.println("Found with perc " + perc + "=" + counts.get(perc));
		}
		System.out.println("Not found " + notfound);

	}

	private BufferedImage getScaledImage(BufferedImage image, double perc) throws IOException {
		return getScaledImage(image, perc, perc);
	}

	private BufferedImage getScaledImage(BufferedImage image, double scaleX, double scaleY) throws IOException {
		
		if (scaleX==1d && scaleY==1d) return image;
		
		int newWidth = (int)(image.getWidth()*scaleX);
		int newHeight = (int)(image.getHeight()*scaleY);
		
	    AffineTransform scaleTransform = AffineTransform.getScaleInstance(scaleX, scaleY);
	    AffineTransformOp bilinearScaleOp = new AffineTransformOp(scaleTransform, AffineTransformOp.TYPE_BILINEAR);

	    BufferedImage ret = bilinearScaleOp.filter(image, new BufferedImage(newWidth, newHeight, image.getType()));
//	    debug(ret);
	    return ret;
	}
	
	public void debug(BufferedImage image) {
		try {
			ImageWriter wr = ImageIO.getImageWritersBySuffix("jpg").next();
			ImageWriteParam iwp = wr.getDefaultWriteParam();
		    iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		    iwp.setCompressionQuality(1f);
			FileImageOutputStream baosM = new FileImageOutputStream(new File("/home/andreatessaro/debug/"+System.currentTimeMillis()+".jpg"));
			wr.setOutput(baosM);
			wr.write(null, new IIOImage(image,null,null), iwp);
			wr.dispose();
			baosM.flush();
			baosM.close();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

}
