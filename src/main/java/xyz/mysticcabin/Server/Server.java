package xyz.mysticcabin.Server;

import com.google.gson.Gson;
import xyz.mysticcabin.Util.ConnectionHeaders;
import xyz.mysticcabin.Util.IInputStreamWrapper;
import xyz.mysticcabin.Util.JsonStreamWrapper;
import xyz.mysticcabin.Util.ObjectStreamWrapper;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Server {
    private ArrayList<IConnection> connections;
    private ServerSocket    server   = null;
    private boolean daemonShouldStop = false;

    Server(int port){
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("SIGINT caught. Shutting down threads and exiting.");
            daemonShouldStop = true;
        }));

        try {
            Gson gson = new Gson();

            server = new ServerSocket(port);
            System.out.println("Server Started.");

            System.out.println("Waiting for a client...");

            while(!daemonShouldStop){
                Socket newConnection = server.accept();
                DataInputStream tempStream = new DataInputStream(
                        new BufferedInputStream(newConnection.getInputStream()));

                // Wait for connection headers.
                String stringHeaders = tempStream.readUTF();
                ConnectionHeaders headers = gson.fromJson(stringHeaders,ConnectionHeaders.class);
                tempStream.close();

                IInputStreamWrapper inputStream;
                ObjectOutputStream outputStream = new ObjectOutputStream(
                        new BufferedOutputStream(newConnection.getOutputStream()));

                if(headers.isObjectData()){
                    // Serialize from object data.
                     inputStream = new ObjectStreamWrapper(
                            new BufferedInputStream(newConnection.getInputStream()));
                } else {
                    // Serialize from json data.
                    inputStream = new JsonStreamWrapper(
                            new BufferedInputStream(newConnection.getInputStream()));
                }

                if(headers.isReadOnly()){
                    // Spawn a read only connection
                    ReadConnection newThread = new ReadConnection(newConnection, inputStream);
                    newThread.start();
                    connections.add(newThread);
                } else {
                    // Spawn a read/write connection
                    ReadWriteConnection newThread = new ReadWriteConnection(newConnection, inputStream);
                    newThread.start();
                    connections.add(newThread);
                }
            }

            connections.forEach(IConnection::close);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
