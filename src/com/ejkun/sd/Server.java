package com.ejkun.sd;

import java.net.*;

public class Server {
    public static void main(String[] args) throws Exception {
        int port = 9898;
        int numConn = 1;

        DatagramSocket serverSocket = new DatagramSocket(port);

        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];

        while (true) {

            DatagramPacket receivePacket = new DatagramPacket(receiveData,
                    receiveData.length);
            System.out.println("Esperando por datagrama UDP na porta " + port);
            serverSocket.receive(receivePacket);
            String sentence = new String(receivePacket.getData());

            System.out.print("Datagrama UDP [" + numConn + "] recebido: "+sentence.trim());

            InetAddress IPAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();

            String capitalizedSentence = sentence.toUpperCase();

            sendData = capitalizedSentence.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData,
                    sendData.length, IPAddress, clientPort);

            System.out.print("Enviando " + capitalizedSentence);

            serverSocket.send(sendPacket);
            System.out.println("Enviado\n");
        }
    }
}
