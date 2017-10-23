package org.uom.cse.cs4262;

import org.uom.cse.cs4262.api.Constant;
import org.uom.cse.cs4262.api.Credential;
import org.uom.cse.cs4262.api.Node;
import org.uom.cse.cs4262.api.NodeOps;
import org.uom.cse.cs4262.api.message.Message;
import org.uom.cse.cs4262.api.message.request.JoinRequest;
import org.uom.cse.cs4262.api.message.request.RegisterRequest;
import org.uom.cse.cs4262.api.message.request.SearchRequest;
import org.uom.cse.cs4262.api.message.response.RegisterResponse;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Chanaka Lakmal
 * @date 22/10/2017
 * @since 1.0
 */

public class NodeOpsUDP implements NodeOps, Runnable {

    private Node node;
    private Credential bootstrapServerCredential;
    private DatagramSocket socket;
    private boolean regOk = false;

    public NodeOpsUDP(Credential bootstrapServerCredential, Credential nodeCredential) {
        this.bootstrapServerCredential = bootstrapServerCredential;

        this.node = new Node();
        node.setCredential(nodeCredential);
        node.setFileList(createFileList());
        node.setRoutingTable(new ArrayList());
        node.setStatTable(new ArrayList());

        this.start();
    }

    @Override
    public void run() {
        System.out.println("Server " + this.node.getCredential().getUsername() + " created at " + this.node.getCredential().getPort() + ". Waiting for incoming data...");
        byte buffer[];
        DatagramPacket datagramPacket;
        while (true) {
            buffer = new byte[65536];
            datagramPacket = new DatagramPacket(buffer, buffer.length);
            try {
                socket.receive(datagramPacket);
                Message response = Parser.parse(new String(datagramPacket.getData(), 0, datagramPacket.getLength()));
                processResponse(response);
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
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setNode(node);
        String msg = registerRequest.getMessageAsString(Constant.Command.REG);
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
    public void join(Credential neighbourNode) {
        JoinRequest joinRequest = new JoinRequest();
        joinRequest.setNode(node);
        String msg = joinRequest.getMessageAsString(Constant.Command.JOIN);
        try {
            socket.send(new DatagramPacket(msg.getBytes(), msg.getBytes().length, InetAddress.getByName(neighbourNode.getIp()), neighbourNode.getPort()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void joinOk() {

    }

    @Override
    public void leave() {

    }

    @Override
    public void search(Credential neighbourNode) {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setNode(node);
        String msg = searchRequest.getMessageAsString(Constant.Command.SEARCH);
        try {
            socket.send(new DatagramPacket(msg.getBytes(), msg.getBytes().length, InetAddress.getByName(neighbourNode.getIp()), neighbourNode.getPort()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void searchOk() {

    }

    @Override
    public ArrayList<String> createFileList() {
        ArrayList<String> fileList = new ArrayList<>();
        fileList.add("Twilight");
        fileList.add("Jack");
        //TODO: add all the files
        Collections.shuffle(fileList);
        return (ArrayList<String>) fileList.subList(0, 5);
    }

    @Override
    public void processResponse(Message response) {
        if (response instanceof RegisterResponse) {
            RegisterResponse registerResponse = (RegisterResponse) response;
            List<Node> nodeList = registerResponse.getNodes();
            ArrayList<Credential> routingTable = new ArrayList();
            for (Node node : nodeList) {
                routingTable.add(node.getCredential());
            }
            //TODO: check whether the received nodes are alive before adding to routing table
            this.node.setRoutingTable(routingTable);
            this.regOk = true;
        } else if (response instanceof SearchRequest) {
            SearchRequest searchRequest = (SearchRequest) response;
            List<String> searchResult = checkForFiles(searchRequest.getFileName(), node.getFileList());
            if (!searchResult.isEmpty()) {
                //TODO: create search response object and send it to searchRequest.getNode().getCredentials() after @Chandu
            } else {
                for (Credential credential : node.getRoutingTable()) {
                    search(credential);
                }
            }
        }
        //TODO: proceed with other response messages after @Chandu
    }

    @Override
    public boolean isRegOk() {
        return regOk;
    }

    @Override
    public List<String> checkForFiles(String fileName, ArrayList<String> fileList) {
        Pattern pattern = Pattern.compile(fileName + "*");
        return fileList.stream().filter(pattern.asPredicate()).collect(Collectors.toList());
    }
}
