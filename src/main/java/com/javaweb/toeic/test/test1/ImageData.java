package com.javaweb.toeic.test.test1;

public class ImageData {
    private byte[] data;
    private String extension;

    public ImageData(byte[] data, String extension) {
        this.data = data;
        this.extension = extension;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}

