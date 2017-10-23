package org.uom.cse.cs4262.api;

/**
 * @author Chanaka Lakmal
 * @date 22/10/2017
 * @since 1.0
 */

public interface NodeOps {

    void start();

    void register();

    void unRegister();

    void join();

    void leave();

    void search();

    void addFiles();

    void processResponse();
}
