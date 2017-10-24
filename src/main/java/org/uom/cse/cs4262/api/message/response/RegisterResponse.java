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
    private List<Credential> credentials;

    public RegisterResponse(int noOfNodes, List<Credential> credentials) {
        this.noOfNodes = noOfNodes;
        this.credentials = credentials;
    }

    public List<Credential> getCredentials() {
        return credentials;
    }

    public void setCredentials(List<Credential> credentials) {
        this.credentials = credentials;
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
        for (Credential node : credentials) {
            message += " " + node.getIp() + " " + node.getPort();
        }
        return super.getMessageAsString(message);
    }
}