package com.dream.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class DatagramSocketSendRunnable extends SendRunnable {

    private InetSocketAddress address;
    private String host;
    private int port;
    private boolean isAddressChanged = false;
    private DatagramSocket socket;

    public void setDatagramSocket(DatagramSocket socket) {
        this.socket = socket;
    }

    public void changeAddress(String host, int port) {
        this.host = host;
        this.port = port;
        isAddressChanged = true;
    }

    @Override
    protected void doSend(byte[] buffer, int offset, int length) {
        if(isAddressChanged){
            isAddressChanged = false;
            address = new InetSocketAddress(host, port);
        }
        if (socket != null && address != null) {
            try {
                socket.send(new DatagramPacket(buffer, offset, length, address));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}