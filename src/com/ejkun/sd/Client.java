package com.ejkun.sd;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {

    public static void main(String[] args) throws Exception {
        BufferedReader clienteInput = new BufferedReader(new InputStreamReader(
                System.in));
        DatagramSocket clientSocket = new DatagramSocket();
        String server = "localhost";
        int port = 9898;

        InetAddress IPAddress = InetAddress.getByName(server);

        byte[] receiveData = new byte[1024];

        System.out.print("Entre com o texto: ");
        String sentence = clienteInput.readLine();
        byte[] sendData = sentence.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData,
                sendData.length, IPAddress, port);
        clientSocket.send(sendPacket);

        DatagramPacket receivePacket = new DatagramPacket(receiveData,
                receiveData.length);

        clientSocket.receive(receivePacket);
        String capitalizedSentence = new String(receivePacket.getData());
        System.out.println("Servidor Retornou: " + capitalizedSentence.trim());
        clientSocket.close();
    }
}
