package jilei.springserverdemo;

import jilei.springserverdemo.entity.Group;

import java.util.Random;
import java.util.UUID;

public class Test {
    public static void main(String[] args) {
        String uuid=UUID.randomUUID().toString();
        System.out.println(uuid);
        System.out.println(uuid.length());
    }
}
