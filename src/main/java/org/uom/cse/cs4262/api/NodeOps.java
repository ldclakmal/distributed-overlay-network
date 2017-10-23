package org.uom.cse.cs4262.api;

import org.uom.cse.cs4262.api.message.Message;

import java.util.ArrayList;

/**
 * @author Chanaka Lakmal
 * @date 22/10/2017
 * @since 1.0
 */

public interface NodeOps {

    void start();

    void register();

    void unRegister();

    void join(Credential credential);

    void leave();

    void search(Credential credential);

    ArrayList<String> createFileList();

    void processResponse(Message response);

    boolean isRegOk();

    boolean isFileAvailable(String fileName, ArrayList<String> fileList);
}
