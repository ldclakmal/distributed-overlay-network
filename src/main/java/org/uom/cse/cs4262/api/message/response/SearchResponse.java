package org.uom.cse.cs4262.api.message.response;

import org.uom.cse.cs4262.api.Node;

import java.util.ArrayList;

/**
 * @author Chandu Herath
 * @date 23/10/2017
 * @since 1.0
 */

public class SearchResponse {
    private int noOfFiles;
    private Node resultNode;
    private int hops;
    private ArrayList<String> fileList;

    public int getNoOfFiles() {
        return noOfFiles;
    }

    public void setNoOfFiles(int noOfFiles) {
        this.noOfFiles = noOfFiles;
    }

    public Node getResultNode() {
        return resultNode;
    }

    public void setResultNode(Node resultNode) {
        this.resultNode = resultNode;
    }

    public int getHops() {
        return hops;
    }

    public void setHops(int hops) {
        this.hops = hops;
    }

    public ArrayList<String> getFileList() {
        return fileList;
    }

    public void setFileList(ArrayList<String> fileList) {
        this.fileList = fileList;
    }
}
