package xyz.mysticcabin.Util;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class ObjectStreamWrapper extends ObjectInputStream implements IInputStreamWrapper {
    public ObjectStreamWrapper(InputStream in) throws IOException {
        super(in);
    }

    public Object readData() throws IOException, ClassNotFoundException {
        return this.readObject();
    }
}
