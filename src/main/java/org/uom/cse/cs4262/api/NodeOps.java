package org.uom.cse.cs4262.api;

import org.uom.cse.cs4262.api.message.Message;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Chanaka Lakmal
 * @date 22/10/2017
 * @since 1.0
 */

public abstract class NodeOps {

    public abstract void start();

    public abstract void register();

    public abstract void unRegister();

    public abstract void join(Credential credential);

    public abstract void leave();

    public abstract void search(Credential credential);

    public ArrayList<String> createFileList() {
        ArrayList<String> fileList = new ArrayList<>();
        fileList.add("Twilight");
        fileList.add("Jack");
        Collections.shuffle(fileList);
        return (ArrayList<String>) fileList.subList(0, 5);
    }

    public abstract void processResponse(Message response);

    public abstract boolean isRegOk();
}
