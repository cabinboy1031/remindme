package xyz.mysticcabin.Util;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public interface IInputStreamWrapper {
    Object readData() throws IOException, ClassNotFoundException;
}

