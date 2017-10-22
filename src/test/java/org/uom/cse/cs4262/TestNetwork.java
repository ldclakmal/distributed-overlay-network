package org.uom.cse.cs4262;

import org.junit.Test;
import org.uom.cse.cs4262.api.Credential;

public class TestNetwork {

    @Test
    public void test() {
        int nodeCount = 2;
        int nodePort = 44444;
        Credential bootstrapServerCredential = new Credential("192.168.43.169", 55555, "admin");

        for (int i = 1; i <= nodeCount; i++) {

            Credential nodeCredential = new Credential("127.0.0.1", nodePort, "child");
            NodeOpsUDP nodeOpsUDP = new NodeOpsUDP(bootstrapServerCredential, nodeCredential);

            int j = 0;

            while (true) {
                System.out.println("Sending " + j++);
                nodeOpsUDP.register();
            }
        }
    }
}
