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
import java.util.*;
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

    public Node getNode() {
        return node;
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
                String message = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                Credential senderCredential = new Credential(datagramPacket.getAddress().getHostAddress(), datagramPacket.getPort(), null);
                Message response = Parser.parse(message, senderCredential);
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
        RegisterRequest registerRequest = new RegisterRequest(node.getCredential());
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
        JoinRequest joinRequest = new JoinRequest(node.getCredential());
        String msg = joinRequest.getMessageAsString(Constant.Command.JOIN);
        try {
            socket.send(new DatagramPacket(msg.getBytes(), msg.getBytes().length, InetAddress.getByName(neighbourCredential.getIp()), neighbourCredential.getPort()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void joinOk(Credential senderCredential) {
        JoinResponse joinResponse = new JoinResponse(0, node.getCredential());
        String msg = joinResponse.getMessageAsString(Constant.Command.JOINOK);
        try {
            socket.send(new DatagramPacket(msg.getBytes(), msg.getBytes().length, InetAddress.getByName(senderCredential.getIp()), senderCredential.getPort()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void leave(Credential neighbourCredential) {
        LeaveRequest leaveRequest = new LeaveRequest(node.getCredential());
        String msg = leaveRequest.getMessageAsString(Constant.Command.LEAVE);
        try {
            socket.send(new DatagramPacket(msg.getBytes(), msg.getBytes().length, InetAddress.getByName(neighbourCredential.getIp()), neighbourCredential.getPort()));
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
    public void search(SearchRequest searchRequest, Credential sendCredentials) {
        String msg = searchRequest.getMessageAsString(Constant.Command.SEARCH);
        try {
            socket.send(new DatagramPacket(msg.getBytes(), msg.getBytes().length, InetAddress.getByName(sendCredentials.getIp()), sendCredentials.getPort()));
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
        fileList.add("Adventures_of_Tintin");
        fileList.add("Jack_and_Jill");
        fileList.add("Glee");
        fileList.add("The_Vampire Diarie");
        fileList.add("King_Arthur");
        fileList.add("Windows_XP");
        fileList.add("Harry_Potter");
        fileList.add("Kung_Fu_Panda");
        fileList.add("Lady_Gaga");
        fileList.add("Twilight");
        fileList.add("Windows_8");
        fileList.add("Mission_Impossible");
        fileList.add("Turn_Up_The_Music");
        fileList.add("Super_Mario");
        fileList.add("American_Pickers");
        fileList.add("Microsoft_Office_2010");
        fileList.add("Happy_Feet");
        fileList.add("Modern_Family");
        fileList.add("American_Idol");
        fileList.add("Hacking_for_Dummies");
        Collections.shuffle(fileList);
        List<String> subFileList = fileList.subList(0, 5);
        System.out.println("File List : " + Arrays.toString(subFileList.toArray()));
        return subFileList;
    }

    @Override
    public void processResponse(Message response) {
        if (response instanceof RegisterResponse) {
            RegisterResponse registerResponse = (RegisterResponse) response;
            if (registerResponse.getNoOfNodes() == Constant.Codes.Register.ERROR_ALREADY_REGISTERED) {
                System.out.println("Already registered at Bootstrap with same username");
                Credential credential = node.getCredential();
                credential.setUsername(UUID.randomUUID().toString());
                node.setCredential(credential);
                register();
            } else if (registerResponse.getNoOfNodes() == Constant.Codes.Register.ERROR_DUPLICATE_IP) {
                System.out.println("Already registered at Bootstrap with same port");
                Credential credential = node.getCredential();
                credential.setPort(credential.getPort() + 1);
                node.setCredential(credential);
                register();
            } else if (registerResponse.getNoOfNodes() == Constant.Codes.Register.ERROR_CANNOT_REGISTER) {
                System.out.printf("Can’t register. Bootstrap server full. Try again later");
            } else if (registerResponse.getNoOfNodes() == Constant.Codes.Register.ERROR_COMMAND) {
                System.out.println("Error in command");
            } else {
                List<Credential> credentialList = registerResponse.getCredentials();
                ArrayList<Credential> routingTable = new ArrayList();
                for (Credential credential : credentialList) {
                    routingTable.add(credential);
                }
                printRoutingTable(routingTable);
                //TODO: check whether the received nodes are alive before adding to routing table
                this.node.setRoutingTable(routingTable);
                this.regOk = true;
            }

        } else if (response instanceof UnregisterResponse) {
            //TODO: set leave request for all of the nodes at routing table
            node.setRoutingTable(new ArrayList<>());
            node.setFileList(new ArrayList<>());
            node.setStatTable(new ArrayList<>());
            this.regOk = false;

        } else if (response instanceof SearchRequest) {
            SearchRequest searchRequest = (SearchRequest) response;
            triggerSearchRequest(searchRequest);

        } else if (response instanceof SearchResponse) {
            SearchResponse searchResponse = (SearchResponse) response;
            if (searchResponse.getNoOfFiles() == Constant.Codes.Search.ERROR_NODE_UNREACHABLE) {
                System.out.println("Failure due to node unreachable");
            } else if (searchResponse.getNoOfFiles() == Constant.Codes.Search.ERROR_OTHER) {
                System.out.println("Some other error");
            } else {
                System.out.printf(searchResponse.toString());
            }

        } else if (response instanceof JoinRequest) {
            joinOk(node.getCredential());

        } else if (response instanceof JoinResponse) {
            JoinResponse joinResponse = (JoinResponse) response;
            List<Credential> routingTable = node.getRoutingTable();
            routingTable.add(joinResponse.getSenderCredential());
            node.setRoutingTable(routingTable);

        } else if (response instanceof LeaveRequest) {
            LeaveRequest leaveRequest = (LeaveRequest) response;
            List<Credential> routingTable = node.getRoutingTable();
            routingTable.remove(leaveRequest.getCredential());
            node.setRoutingTable(routingTable);

        } else if (response instanceof LeaveResponse) {
            //Nothing to do here

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

    @Override
    public void printRoutingTable(List<Credential> routingTable) {
        System.out.println("Routing table updated as :");
        System.out.println("--------------------------------------------------------");
        System.out.println("IP \t \t \t PORT");
        for (Credential credential : routingTable) {
            System.out.println(credential.getIp() + "\t" + credential.getPort());
        }
    }

    @Override
    public void triggerSearchRequest(SearchRequest searchRequest) {
        System.out.println("Triggered search request");
        List<String> searchResult = checkForFiles(searchRequest.getFileName(), node.getFileList());
        if (!searchResult.isEmpty()) {
            System.out.println("File is available");
            SearchResponse searchResponse = new SearchResponse(searchRequest.getSequenceNo(), searchResult.size(), searchRequest.getCredential(), searchRequest.incHops(), searchResult);
            if (searchRequest.getCredential().getIp() == node.getCredential().getIp() && searchRequest.getCredential().getPort() == node.getCredential().getPort()) {
                System.out.println(searchResponse.toString());
            } else {
                System.out.println("Send SEARCHOK response message");
                searchOk(searchResponse);
            }

        } else {
            System.out.println("File is not available");
            for (Credential credential : node.getRoutingTable()) {
                searchRequest.setHops(searchRequest.incHops());
                search(searchRequest, credential);
                System.out.println("Send SER request message to " + credential.getIp() + " : " + credential.getPort());
            }
        }
    }
}
