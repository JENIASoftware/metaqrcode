package it.jenia.metaqrcode.server.core.api.test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

public class CreateBarcodeImage {

	@Test
	public void test() throws Exception{
		FileOutputStream fos = new FileOutputStream("/jenia2.0/metaqrcode/test/test.png");
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		QRCode.from("http://www.metaqrcode.com/api/r/1").
		to(ImageType.PNG).
		withCharset("UTF-8").
		withErrorCorrection(ErrorCorrectionLevel.H).
		withHint(EncodeHintType.MARGIN, 2).
		withSize(210, 210).
		writeTo(baos);
		
		// load source images
		BufferedImage image = ImageIO.read(new FileInputStream("/jenia2.0/metaqrcode/workspace/metaqrcode/metaqrcode-server/metaqrcode-server-services/src/main/config/qrcode-background2.gif"));
		BufferedImage background = ImageIO.read(new ByteArrayInputStream(baos.toByteArray()));

		// create the new image, canvas size is the max. of both image sizes
		BufferedImage combined = new BufferedImage(225, 225, BufferedImage.TYPE_BYTE_BINARY);
		// paint both images, preserving the alpha channels
		Graphics g = combined.getGraphics();

		// write text vertical on left
		BufferedImage combinedText = new BufferedImage(225, 225, BufferedImage.TYPE_INT_ARGB);
		Graphics2D gt = combinedText.createGraphics();
		Font font = new Font("Arial", Font.PLAIN, 15);
		gt.setFont(font);
		gt.setColor(Color.BLACK);
//		gt.setBackground(Color.WHITE);
//		Color color = new Color(1, 0, 0, 1); //Red 
		gt.setBackground(Color.WHITE);
		gt.clearRect(0, 0, 225, 225);
        gt.rotate(Math.toRadians(-90));
        gt.drawString("8888888888888888888888888", -207, 222);
		g.drawImage(combinedText, 0, 0, null);

//		// write text vertical on left
//		BufferedImage combinedTextLogo = new BufferedImage(225, 225, BufferedImage.TYPE_INT_ARGB);
//		Graphics2D gl = combinedTextLogo.createGraphics();
//		Font fontL = new Font("Arial", Font.PLAIN, 15);
//		gl.setFont(fontL);
//		gl.setColor(Color.BLACK);
////		gt.setBackground(Color.WHITE);
//		Color color = new Color(1, 0, 0, 1); //Red 
//		gl.setBackground(color);
//		gl.clearRect(0, 0, 225, 225);
////        gl.rotate(Math.toRadians(-90));
//        gl.drawString("metaqrcode", 0, 222);
//		g.drawImage(combinedTextLogo, 0, 0, null);
		g.drawImage(background, 0, 0, null);
		g.drawImage(image, 0, 0, null);

		
		
		// Save as new image
		ImageIO.write(combined, "PNG", fos);
	}

}
