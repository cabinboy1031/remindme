package xyz.mysticcabin.Server;

import xyz.mysticcabin.Util.IInputStreamWrapper;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class ReadWriteConnection extends Thread implements IConnection {
    Socket socket;
    IInputStreamWrapper inputStream;
    ObjectOutputStream outputStream;

    ReadWriteConnection(Socket connection, IInputStreamWrapper input){

    }

    public void close() {
        // TODO
    }
}
