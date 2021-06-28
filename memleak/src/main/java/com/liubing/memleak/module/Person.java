package com.liubing.memleak.module;

public class Person {
    private String id;

    /**
     * 100MB
     */
    private byte[] nameByte;

    public Person(){
        nameByte = new byte[1024 * 1024 * 100];
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getNameByte() {
        return nameByte;
    }

    public void setNameByte(byte[] nameByte) {
        this.nameByte = nameByte;
    }
}
