package org.uom.cse.cs4262;

import org.uom.cse.cs4262.api.Constant;
import org.uom.cse.cs4262.api.Credential;
import org.uom.cse.cs4262.api.message.request.SearchRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * @author Chanaka Lakmal
 * @date 25/10/2017
 * @since 1.0
 */

public class BootstrapNode {

    public static void main(String[] args) {

        HashMap<String, String> paramMap = new HashMap<>();

        for (int i = 0; i < args.length; i = i + 2) {
            paramMap.put(args[i], args[i + 1]);
            System.out.println(args[i] + " : " + args[i + 1]);
        }

        System.out.println();

        String bootstrapIp = paramMap.get("-b") != null ? paramMap.get("-b") : Constant.IP_BOOTSTRAP_SERVER;
        String nodeIp = paramMap.get("-i") != null ? paramMap.get("-i") : Constant.IP_BOOTSTRAP_SERVER;
        int nodePort = paramMap.get("-p") != null ? Integer.parseInt(paramMap.get("-p")) : new Random().nextInt(Constant.MAX_PORT_NODE - Constant.MIN_PORT_NODE) + Constant.MIN_PORT_NODE;
        String nodeUsername = paramMap.get("-u") != null ? paramMap.get("-u") : UUID.randomUUID().toString();

        Credential bootstrapServerCredential = new Credential(bootstrapIp, Constant.PORT_BOOTSTRAP_SERVER, Constant.USERNAME_BOOTSTRAP_SERVER);
        Map<Integer, String> searchQueryList = new HashMap<>();
        int sequentialNum = 0;
        searchQueryList.put(++sequentialNum, "");

        // Generate self credentials
        Credential nodeCredential = new Credential(nodeIp, nodePort, nodeUsername);

        // Initiate the thread for UDP connection
        NodeOpsUDP nodeOpsUDP = new NodeOpsUDP(bootstrapServerCredential, nodeCredential);

        // Register in network
        nodeOpsUDP.register();
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (nodeOpsUDP.isRegOk()) {
                SearchRequest searchRequest = new SearchRequest(1, nodeOpsUDP.getNode().getCredential(), "Kung", 0);
                nodeOpsUDP.triggerSearchRequest(searchRequest);
                break;
            }
        }
        while (true) ;
    }
}
