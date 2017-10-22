package org.uom.cse.cs4262.api;

import java.util.ArrayList;

/**
 * @author Chamin Wickramarathna
 * @date 22/10/2017
 * @since 1.0
 */
public class Node {

    private Credential credential;
    private ArrayList<String> fileList;
    private ArrayList<Credential> routingTable;
    private ArrayList<StatRecord> statTable;

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    public ArrayList<String> getFileList() {
        return fileList;
    }

    public void setFileList(ArrayList<String> fileList) {
        this.fileList = fileList;
    }

    public ArrayList<Credential> getRoutingTable() {
        return routingTable;
    }

    public void setRoutingTable(ArrayList<Credential> routingTable) {
        this.routingTable = routingTable;
    }

    public ArrayList<StatRecord> getStatTable() {
        return statTable;
    }

    public void setStatTable(ArrayList<StatRecord> statTable) {
        this.statTable = statTable;
    }
}
