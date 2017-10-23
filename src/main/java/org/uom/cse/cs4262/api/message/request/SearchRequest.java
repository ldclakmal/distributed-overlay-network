package org.uom.cse.cs4262.api.message.request;

import org.uom.cse.cs4262.api.Credential;
import org.uom.cse.cs4262.api.message.Message;

/**
 * @author Sachithra Dangalla
 * @date 10/22/2017
 * @since 1.0
 */
public class SearchRequest extends Message {

    private Credential credential;
    private String fileName;
    private int hops;

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
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
        message += " " + this.getCredential().getIp() + " " + this.getCredential().getPort() + " " + this.getFileName() + " " + this.getHops();
        return super.getMessageAsString(message);
    }

    public int incHops() {
        return hops++;
    }
}