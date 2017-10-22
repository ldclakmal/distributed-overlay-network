package org.uom.cse.cs4262.api.message.response;

import org.uom.cse.cs4262.api.Node;
import org.uom.cse.cs4262.api.message.Message;

import java.util.List;

/**
 * @author Chanaka Lakmal
 * @date 22/10/2017
 * @since 1.0
 */

public class RegisterResponse extends Message {

    private List<Node> nodes;

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

}