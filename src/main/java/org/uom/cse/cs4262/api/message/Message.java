package org.uom.cse.cs4262.api.message;

/**
 * @author Chanaka Lakmal
 * @date 22/10/2017
 * @since 1.0
 */

public abstract class Message {

    protected String getMessageAsString(String message) {
        return String.format("%04d", message.length() + 5) + " " + message;
    }

}
