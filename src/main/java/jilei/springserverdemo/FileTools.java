package jilei.springserverdemo;

import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;

public class FileTools {

    public static byte[] ReadFileToBytes(String path) {
        try {
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len = -1;
            byte[] buf = new byte[1024];
            while ((len = fis.read(buf)) != -1) {//汇总字节流到内存
                baos.write(buf, 0, len);
            }
            baos.close();
            fis.close();
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] ConvertBlobToBytes(Blob image) {
        try {
            InputStream is = image.getBinaryStream();
            int len = -1;
            byte[] buf = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((len = is.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }
            is.close();
            baos.close();
            return baos.toByteArray();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static String GenerateDataUri(String type, byte[] image) {
        StringBuilder sb = new StringBuilder();
        sb.append("data:image/").append(type).append(";base64,");
        String base64image = Base64.encodeBase64String(image);
        sb.append(base64image);
        return sb.toString();
    }

}
