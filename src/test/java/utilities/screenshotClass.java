package utilities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class screenshotClass {
  public String screenCapture(WebDriver driver) throws IOException {
  File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    BufferedImage src = ImageIO.read(scrFile);
    BufferedImage convertedImg = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
    convertedImg.getGraphics().drawImage(src, 0, 0, null);

    File output = new File("compressed.jpeg");
    OutputStream out = new FileOutputStream(output);

    ImageWriter writer = ImageIO.getImageWritersByFormatName("jpeg").next();
    ImageOutputStream ios = ImageIO.createImageOutputStream(out);
    writer.setOutput(ios);

    ImageWriteParam param = writer.getDefaultWriteParam();
    if (param.canWriteCompressed()) {
      param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
      param.setCompressionQuality(0.5f);
    }
    writer.write(null, new IIOImage(convertedImg, null, null), param);
    out.close();
    ios.close();
    writer.dispose();
    String encodedBase64 = null;
    FileInputStream fileInputStreamReader = null;
    try {
      fileInputStreamReader = new FileInputStream(output);
      byte[] bytes = new byte[(int) output.length()];
      while (fileInputStreamReader.read(bytes) > 0) {
        encodedBase64 = new String(Base64.getEncoder().encode(bytes));
      }
    } catch (
        IOException e) {
      Assert.fail("this is failed because of exception " + e.getMessage());
    } finally {
      if (fileInputStreamReader != null) {
        fileInputStreamReader.close();
      }
    }
    return "data:image/dib;base64," + encodedBase64;
  }
}
