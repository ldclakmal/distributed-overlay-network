package org.uom.cse.cs4262;

import org.uom.cse.cs4262.api.Constant;
import org.uom.cse.cs4262.api.Credential;
import org.uom.cse.cs4262.api.Node;
import org.uom.cse.cs4262.api.NodeOps;
import org.uom.cse.cs4262.api.message.Message;
import org.uom.cse.cs4262.api.message.request.*;
import org.uom.cse.cs4262.api.message.response.*;

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
                Message response = Parser.parse(datagramPacket);
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
        registerRequest.setCredential(node.getCredential());
        String msg = registerRequest.getMessageAsString(Constant.Command.REG);
        try {
            socket.send(new DatagramPacket(msg.getBytes(), msg.getBytes().length, InetAddress.getByName(bootstrapServerCredential.getIp()), bootstrapServerCredential.getPort()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unRegister() {
        UnregisterRequest unregisterRequest = new UnregisterRequest(node.getCredential());
        String msg = unregisterRequest.getMessageAsString(Constant.Command.UNREG);
        try {
            socket.send(new DatagramPacket(msg.getBytes(), msg.getBytes().length, InetAddress.getByName(bootstrapServerCredential.getIp()), bootstrapServerCredential.getPort()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void join(Credential neighbourCredential) {
        JoinRequest joinRequest = new JoinRequest();
        joinRequest.setCredential(node.getCredential());
        String msg = joinRequest.getMessageAsString(Constant.Command.JOIN);
        try {
            socket.send(new DatagramPacket(msg.getBytes(), msg.getBytes().length, InetAddress.getByName(neighbourCredential.getIp()), neighbourCredential.getPort()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void joinOk(Credential senderCredentials) {
        JoinResponse joinResponse = new JoinResponse(0, node.getCredential());
        String msg = joinResponse.getMessageAsString(Constant.Command.JOINOK);
        try {
            socket.send(new DatagramPacket(msg.getBytes(), msg.getBytes().length, InetAddress.getByName(senderCredentials.getIp()), senderCredentials.getPort()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void leave(Credential neighbourNode) {
        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setCredential(node.getCredential());
        String msg = leaveRequest.getMessageAsString(Constant.Command.LEAVE);
        try {
            socket.send(new DatagramPacket(msg.getBytes(), msg.getBytes().length, InetAddress.getByName(neighbourNode.getIp()), neighbourNode.getPort()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void leaveOk(Credential senderCredentials) {
        LeaveResponse leaveResponse = new LeaveResponse(0);
        String msg = leaveResponse.getMessageAsString(Constant.Command.LEAVEOK);
        try {
            socket.send(new DatagramPacket(msg.getBytes(), msg.getBytes().length, InetAddress.getByName(senderCredentials.getIp()), senderCredentials.getPort()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void search(SearchRequest searchRequest) {
        String msg = searchRequest.getMessageAsString(Constant.Command.SEARCH);
        try {
            socket.send(new DatagramPacket(msg.getBytes(), msg.getBytes().length, InetAddress.getByName(searchRequest.getCredential().getIp()), searchRequest.getCredential().getPort()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void searchOk(SearchResponse searchResponse) {
        String msg = searchResponse.getMessageAsString(Constant.Command.SEARCHOK);
        try {
            socket.send(new DatagramPacket(msg.getBytes(), msg.getBytes().length, InetAddress.getByName(searchResponse.getCredential().getIp()), searchResponse.getCredential().getPort()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void error(Credential senderCredential) {
        ErrorResponse errorResponse = new ErrorResponse();
        String msg = errorResponse.getMessageAsString(Constant.Command.ERROR);
        try {
            socket.send(new DatagramPacket(msg.getBytes(), msg.getBytes().length, InetAddress.getByName(senderCredential.getIp()), senderCredential.getPort()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> createFileList() {
        ArrayList<String> fileList = new ArrayList<>();
        fileList.add("Adventures of Tintin");
        fileList.add("Jack and Jill");
        fileList.add("Glee");
        fileList.add("The Vampire Diarie");
        fileList.add("King Arthur");
        fileList.add("Windows XP");
        fileList.add("Harry Potter");
        fileList.add("Kung Fu Panda");
        fileList.add("Lady Gaga");
        fileList.add("Twilight");
        fileList.add("Windows 8");
        fileList.add("Mission Impossible");
        fileList.add("Turn Up The Music");
        fileList.add("Super Mario");
        fileList.add("American Pickers");
        fileList.add("Microsoft Office 2010");
        fileList.add("Happy Feet");
        fileList.add("Modern Family");
        fileList.add("American Idol");
        fileList.add("Hacking for Dummies");
        Collections.shuffle(fileList);
        return fileList.subList(0, 5);
    }

    @Override
    public void processResponse(Message response) {
        if (response instanceof RegisterResponse) {
            RegisterResponse registerResponse = (RegisterResponse) response;
            List<Credential> credentialList = registerResponse.getNodes();
            ArrayList<Credential> routingTable = new ArrayList();
            for (Credential credential : credentialList) {
                routingTable.add(credential);
            }
            //TODO: check whether the received nodes are alive before adding to routing table
            this.node.setRoutingTable(routingTable);
            this.regOk = true;
        } else if (response instanceof SearchRequest) {
            SearchRequest searchRequest = (SearchRequest) response;
            List<String> searchResult = checkForFiles(searchRequest.getFileName(), node.getFileList());
            if (!searchResult.isEmpty()) {
                SearchResponse searchResponse = new SearchResponse(searchResult.size(), searchRequest.getCredential(), searchRequest.incHops(), searchResult);
                searchOk(searchResponse);
            } else {
                for (Credential credential : node.getRoutingTable()) {
                    searchRequest.setCredential(credential);
                    searchRequest.setHops(searchRequest.incHops());
                    search(searchRequest);
                }
            }
        } else if (response instanceof SearchResponse) {
            SearchResponse searchResponse = (SearchResponse) response;
            System.out.printf(searchResponse.toString());
        } else if (response instanceof JoinResponse) {
            JoinResponse joinResponse = (JoinResponse) response;
            List<Credential> routingTable = node.getRoutingTable();
            routingTable.add(joinResponse.getSenderCredential());
            node.setRoutingTable(routingTable);
        } else if (response instanceof ErrorResponse) {
            ErrorResponse errorResponse = (ErrorResponse) response;
            System.out.println(errorResponse.toString());
        }
    }

    @Override
    public boolean isRegOk() {
        return regOk;
    }

    @Override
    public List<String> checkForFiles(String fileName, List<String> fileList) {
        Pattern pattern = Pattern.compile(fileName);
        return fileList.stream().filter(pattern.asPredicate()).collect(Collectors.toList());
    }
}
