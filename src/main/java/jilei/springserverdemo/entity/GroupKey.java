package jilei.springserverdemo.entity;

public class GroupKey {
    String id;
    String key;
    String iv;

    public GroupKey() {
    }

    public GroupKey(String id, String key, String iv) {
        this.id = id;
        this.key = key;
        this.iv = iv;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    @Override
    public String toString() {
        return "GroupKey{" +
                "id='" + id + '\'' +
                ", key='" + key + '\'' +
                ", iv='" + iv + '\'' +
                '}';
    }
}
