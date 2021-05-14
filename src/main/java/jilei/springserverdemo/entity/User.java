package jilei.springserverdemo.entity;

import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Arrays;

public class User {
    String username;
    String password;
    byte[] image;

    public User() {
    }

    public User(String username, String password, byte[] image) {
        this.username = username;
        this.password = password;
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

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

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}
