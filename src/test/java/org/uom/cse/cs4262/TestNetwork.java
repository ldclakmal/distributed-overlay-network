package org.uom.cse.cs4262;

import org.junit.Test;
import org.uom.cse.cs4262.api.Constant;
import org.uom.cse.cs4262.api.Credential;
import org.uom.cse.cs4262.api.message.request.SearchRequest;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TestNetwork {

    @Test
    public void testNetwork() {
        Credential bootstrapServerCredential = new Credential(Constant.IP_BOOTSTRAP_SERVER, Constant.PORT_BOOTSTRAP_SERVER, Constant.USERNAME_BOOTSTRAP_SERVER);

        // Generate self credentials
        int PORT_NODE = new Random().nextInt(Constant.MAX_PORT_NODE - Constant.MIN_PORT_NODE) + Constant.MIN_PORT_NODE;
        Credential nodeCredential = new Credential(Constant.IP_NODE, PORT_NODE, UUID.randomUUID().toString());

        // Initiate the thread for UDP connection
        NodeOpsUDP nodeOpsUDP = new NodeOpsUDP(bootstrapServerCredential, nodeCredential);

        // Register in network
        nodeOpsUDP.register();

        while (true) {
            if (nodeOpsUDP.isRegOk()) {
                SearchRequest searchRequest = new SearchRequest(1, nodeOpsUDP.getNode().getCredential(), "Twilight", 0);
                nodeOpsUDP.triggerSearchRequest(searchRequest);
            }
        }
    }

    @Test
    public void testSearch() {
        List<String> fileList = new ArrayList<>();
        fileList.add("Twilight");
        fileList.add("Jack");
        fileList.add("Jack and Jill");
        fileList.add("Twilight saga");
        fileList.add("My Twilight");
        Collections.shuffle(fileList);
        fileList = fileList.subList(0, 5);

        Pattern pattern = Pattern.compile("Twilight");
        List<String> matching = fileList.stream().filter(pattern.asPredicate()).collect(Collectors.toList());

        for (String s : matching) {
            System.out.println(s);
        }
    }
}