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
    private int sequenceNo;
    private int hops;
    private List<String> fileList;

    public SearchResponse(int noOfFiles, Credential credential, int sequenceNo, int hops, List<String> fileList) {
        this.noOfFiles = noOfFiles;
        this.credential = credential;
        this.sequenceNo = sequenceNo;
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

    public int getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(int sequenceNo) {
        this.sequenceNo = sequenceNo;
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

    @Override
    public String toString() {
        String response = "Search Results:" +
                "\nNo of files: " + fileList.size() +
                "\nIP: " + this.getCredential().getIp() +
                "\nPort: " + this.getCredential().getPort() +
                "\nSequence No: " + this.getSequenceNo() +
                "\nHop count: " + this.getHops();
        for (int i = 0; i < fileList.size(); i++) {
            response += "\nFile " + i + ": " + fileList.get(i);
        }

        return response;
    }
}
