package org.uom.cse.cs4262;

import org.uom.cse.cs4262.api.Credential;
import org.uom.cse.cs4262.api.Node;
import org.uom.cse.cs4262.api.NodeOps;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

public class NodeOpsUDP implements NodeOps, Runnable {

    int i = 0;
    private Node node;
    private Credential bootstrapServerCredential;
    private DatagramSocket socket;

    public NodeOpsUDP(Credential bootstrapServerCredential, Credential nodeCredential) {
        this.bootstrapServerCredential = bootstrapServerCredential;

        this.node = new Node();
        node.setCredential(nodeCredential);
        node.setFileList(new ArrayList());
        node.setRoutingTable(new ArrayList());
        node.setStatTable(new ArrayList());

        this.start();
    }

    @Override
    public void run() {
        byte buffer[];
        DatagramPacket datagramPacket;
        while (true) {
            buffer = new byte[65536];
            datagramPacket = new DatagramPacket(buffer, buffer.length);
            try {
                socket.receive(datagramPacket);
                Parser.parse(new String(datagramPacket.getData(), 0, datagramPacket.getLength()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void start() {
        try {
            socket = new DatagramSocket(this.node.getCredential().getPort());
        } catch (SocketException e) {
            e.printStackTrace();
        }
        new Thread(this).start();
    }

    @Override
    public void register() {
        String msg = "TEST MESSAGE";
        try {
            socket.send(new DatagramPacket(msg.getBytes(), msg.getBytes().length, InetAddress.getByName(bootstrapServerCredential.getIp()), bootstrapServerCredential.getPort()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unRegister() {

    }

    @Override
    public void join() {

    }

    @Override
    public void leave() {

    }

    @Override
    public void search() {

    }
}
