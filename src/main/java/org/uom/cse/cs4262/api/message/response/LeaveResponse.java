package org.uom.cse.cs4262.api.message.response;

import org.uom.cse.cs4262.api.message.Message;

/**
 * Created by Chandu Herath on 23/10/2017.
 */
public class LeaveResponse extends Message {
    private int value;

    public LeaveResponse(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
