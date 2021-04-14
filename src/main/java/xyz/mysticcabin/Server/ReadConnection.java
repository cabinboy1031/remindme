package xyz.mysticcabin.Server;

import xyz.mysticcabin.Util.IInputStreamWrapper;

import java.io.*;
import java.net.Socket;

public class ReadConnection extends Thread implements IConnection {
    private Socket connection;
    private IInputStreamWrapper input;
    private DataOutputStream outputStream;

    ReadConnection(Socket connection, IInputStreamWrapper input){
        try {
            outputStream = new DataOutputStream(new BufferedOutputStream(connection.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Sending Message: Hello! Its nice to meet you! Now i shall immediately die.");
        try {
            outputStream.writeUTF("Hello! Its nice to meet you! Now i shall immediately die.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void close(){
        //TODO
    }
}
