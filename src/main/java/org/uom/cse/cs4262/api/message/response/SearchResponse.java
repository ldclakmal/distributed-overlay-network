package org.uom.cse.cs4262.api.message.response;

import org.uom.cse.cs4262.api.Credential;
import org.uom.cse.cs4262.api.message.Message;

import java.util.List;

/**
 * @author Chandu Herath
 * @date 23/10/2017
 * @since 1.0
 */

public class SearchResponse extends Message {
    private int noOfFiles;
    private Credential credential;
    private int hops;
    private List<String> fileList;

    public SearchResponse(int noOfFiles, Credential credential, int hops, List<String> fileList) {
        this.noOfFiles = noOfFiles;
        this.credential = credential;
        this.hops = hops;
        this.fileList = fileList;
    }

    public int getNoOfFiles() {
        return noOfFiles;
    }

    public void setNoOfFiles(int noOfFiles) {
        this.noOfFiles = noOfFiles;
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    public int getHops() {
        return hops;
    }

    public void setHops(int hops) {
        this.hops = hops;
    }

    public List<String> getFileList() {
        return fileList;
    }

    public void setFileList(List<String> fileList) {
        this.fileList = fileList;
    }

    @Override
    public String getMessageAsString(String message) {
        message += " " + this.getCredential().getIp() + " " + this.getCredential().getPort() + " " + this.getHops();
        for (String file : fileList) {
            message += " " + file;
        }
        return super.getMessageAsString(message);
    }
}
