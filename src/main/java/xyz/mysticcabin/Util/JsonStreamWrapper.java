package xyz.mysticcabin.Util;

import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class JsonStreamWrapper extends DataInputStream implements IInputStreamWrapper {
    public JsonStreamWrapper(InputStream in) {
        super(in);
    }

    public Object readData() throws IOException{
        Gson gson = new Gson();
        return gson.fromJson(this.readUTF(), Object.class);
    }
}
