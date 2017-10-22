package org.uom.cse.cs4262;

import org.junit.Test;
import org.uom.cse.cs4262.api.Constant;
import org.uom.cse.cs4262.api.Credential;

import java.util.UUID;

public class TestNetwork {

    @Test
    public void test() {
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
}
