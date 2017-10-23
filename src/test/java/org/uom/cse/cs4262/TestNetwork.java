package org.uom.cse.cs4262;

import org.junit.Test;
import org.uom.cse.cs4262.api.Constant;
import org.uom.cse.cs4262.api.Credential;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TestNetwork {

    @Test
    public void testNetwork() {
        Credential bootstrapServerCredential = new Credential(Constant.IP_BOOTSTRAP_SERVER, Constant.PORT_BOOTSTRAP_SERVER, Constant.USERNAME_BOOTSTRAP_SERVER);

        // Generate self credentials
        // Generate random username
        UUID randomUsername = UUID.randomUUID();
        Credential nodeCredential = new Credential(Constant.IP_NODE, Constant.PORT_NODE, String.valueOf(randomUsername));

        // Initiate the thread for UDP connection
        NodeOpsUDP nodeOpsUDP = new NodeOpsUDP(bootstrapServerCredential, nodeCredential);

        // Register in network
        nodeOpsUDP.register();


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