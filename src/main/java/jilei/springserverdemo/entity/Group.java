package jilei.springserverdemo.entity;

import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Arrays;

public class Group {
    String id;//主键
    String groupname;
    byte[] image;

    public Group() {
    }

    public Group(String id, String groupname, byte[] image) {
        this.id = id;
        this.groupname = groupname;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", groupname='" + groupname + '\'' +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}
