package com.example.trabalhosistemasdistribuidos;

import java.io.*;
import java.net.*;

public class SocketClient {
    private String ip;
    private int porta;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public SocketClient(String ip, String porta){
        this.ip = ip;
        this.porta = Integer.parseInt(porta);
    }

    public void conectarSocket(){
        try {
            socket = new Socket(this.ip,this.porta);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Conexão a " + ip + ":" + porta + " realizada com sucesso!");
        } catch (UnknownHostException e) {
            System.err.println("Host: " + this.ip + " não encontrado");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Impossível encontrar I/O para o host: " + this.ip);
            System.exit(1);
        }
    }

    public String enviarString(String string) throws IOException{
        out.println(string);
        return in.readLine();
    }

    public void fecharSocket(){
        try{
            in.close();
            out.close();
            socket.close();
            System.out.println("Conexão com " + this.ip + ":" + this.porta + " fechada com sucesso!");
        }catch(IOException IOE){
            System.err.println(IOE);
        }
    }
}
