package org.uom.cse.cs4262;

import org.uom.cse.cs4262.api.Constant;
import org.uom.cse.cs4262.api.Credential;
import org.uom.cse.cs4262.api.message.request.SearchRequest;

import java.util.Random;
import java.util.UUID;

/**
 * @author Chanaka Lakmal
 * @date 25/10/2017
 * @since 1.0
 */

public class BootstrapNode {

    public static void main(String[] args) {
        Credential bootstrapServerCredential = new Credential(Constant.IP_BOOTSTRAP_SERVER, Constant.PORT_BOOTSTRAP_SERVER, Constant.USERNAME_BOOTSTRAP_SERVER);

        // Generate self credentials
        int PORT_NODE = new Random().nextInt(Constant.MAX_PORT_NODE - Constant.MIN_PORT_NODE) + Constant.MIN_PORT_NODE;
        Credential nodeCredential = new Credential(Constant.IP_NODE, PORT_NODE, UUID.randomUUID().toString());

        // Initiate the thread for UDP connection
        NodeOpsUDP nodeOpsUDP = new NodeOpsUDP(bootstrapServerCredential, nodeCredential);

        // Register in network
        nodeOpsUDP.register();

        while (true) {
            System.out.println(" In while loop");
            if (nodeOpsUDP.isRegOk()) {
                System.out.println("Is reg ok");
                SearchRequest searchRequest = new SearchRequest(1, nodeOpsUDP.getNode().getCredential(), "Twilight", 0);
                nodeOpsUDP.triggerSearchRequest(searchRequest);
                break;
            }
        }
        while (true) ;
    }
}
