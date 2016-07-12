package it.jenia.metaqrcode.client.scanner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

import com.google.zxing.NotFoundException;

import it.jenia.metaqrcode.client.tools.ImageScanner;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestMultibarcodeReader {
	
//	private static double[] scalingRetry = {1d, 0.975d, 1.025d, 0.95d, 1.05d, 0.925d, 1.075d, 0.9d, 1.1d};
	private static Double[] scalingRetry = {1d, 0.99d, 1.01d, 0.98d, 1.02d};
//	private static double[] scalingRetry = {1d, 0.975d, 1.025d, 0.95d, 1.05d};
//	private static double[] scalingRetry = {100d};

	@Test
	public void test() throws NotFoundException, IOException {
		
		boolean onlyOdd=false;
		boolean odd=true;

		// we will read images from this folder (test folder)
		File scanFolder = new File("./src/test/resources");
		
		log.debug("scanning for qrcodes in all images of folder "+scanFolder.getAbsolutePath());

		File[] lista = null;

		// extracting only jpg from input folder
		lista = scanFolder.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".jpg");
			}
		});

		// order images by name
		Arrays.sort(lista, new Comparator<File>() {
			@Override
			public int compare(File o1, File o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});

		
		
		ImageScanner scanner = new ImageScanner(false, (List<Double>)Arrays.asList(scalingRetry));

		if (lista != null && lista.length > 0) {
			for (File scanFolderFile : lista) {
				if (scanFolderFile.isFile()) {
					if ((onlyOdd && odd) || !onlyOdd) {
						log.debug("scanning for qrcodes in "+scanFolderFile.getAbsolutePath());
						FileInputStream fis = new FileInputStream(scanFolderFile);
						List<String> barcodes = scanner.scanForQRCodes(fis);
						log.debug("   found : "+barcodes);
					}
					odd=!odd;
				}
			}
		}
		
	}

}
