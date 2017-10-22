package org.uom.cse.cs4262.api.message.request;

import org.uom.cse.cs4262.api.Node;
import org.uom.cse.cs4262.api.message.Message;

/**
 * @author Sachithra Dangalla
 * @date 10/22/2017
 * @since 1.0
 */
public class JoinRequest extends Message {
    private Node node;

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    @Override
    public String getMessageAsString(String message) {
        message += " " + this.getNode().getCredential().getIp() + " " + this.getNode().getCredential().getPort();
        return super.getMessageAsString(message);
    }
}