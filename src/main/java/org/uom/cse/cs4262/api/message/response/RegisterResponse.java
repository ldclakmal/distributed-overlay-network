package org.uom.cse.cs4262.api.message.response;

import org.uom.cse.cs4262.api.Credential;
import org.uom.cse.cs4262.api.message.Message;

import java.util.List;

/**
 * @author Chanaka Lakmal
 * @date 22/10/2017
 * @since 1.0
 */

public class RegisterResponse extends Message {

    private int noOfNodes;
    private List<Credential> nodes;

    public RegisterResponse(int noOfNodes, List<Credential> nodes) {
        this.noOfNodes = noOfNodes;
        this.nodes = nodes;
    }

    public List<Credential> getNodes() {
        return nodes;
    }

    public void setNodes(List<Credential> nodes) {
        this.nodes = nodes;
    }

    public int getNoOfNodes() {
        return noOfNodes;
    }

    public void setNoOfNodes(int noOfNodes) {
        this.noOfNodes = noOfNodes;
    }

    @Override
    public String getMessageAsString(String message) {
        message += " " + this.getNoOfNodes();
        for (Credential node : nodes) {
            message += " " + node.getIp() + " " + node.getPort();
        }
        return super.getMessageAsString(message);
    }
}