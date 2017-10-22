package org.uom.cse.cs4262.api.message.request;

import org.uom.cse.cs4262.api.Node;
import org.uom.cse.cs4262.api.message.Message;

/**
 * @author Sachithra Dangalla
 * @date 10/22/2017
 * @since 1.0
 */
public class SearchRequest extends Message {
    private Node node;
    private String fileName;
    private int hops;

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getHops() {
        return hops;
    }

    public void setHops(int hops) {
        this.hops = hops;
    }

    @Override
    public String getMessageAsString(String message) {
        message += " " + this.getNode().getCredential().getIp() + " " + this.getNode().getCredential().getPort() + " " + this.getFileName() + " " + this.getHops();
        return super.getMessageAsString(message);
    }
}