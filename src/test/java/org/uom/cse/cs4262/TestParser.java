package org.uom.cse.cs4262;

import org.junit.Test;
import org.uom.cse.cs4262.api.message.Message;
import org.uom.cse.cs4262.api.message.response.*;

/**
 * @author Chandu Herath
 * @date 24/10/2017
 * @since 1.0
 */

public class TestParser {

    public String REGOK = "0051 REGOK 2 129.82.123.45 5001 64.12.123.190 34001";
    public String UNROK = "0012 UNROK 0";
    public String JOINOK = "0014 JOINOK 0";
    public String LEAVEOK = "0014 LEAVEOK 0";
    public String SEROK = "0114 SEROK 3 129.82.128.123 8823 01 baby_go_home.mp3 baby_come_back.mp3 baby.mpeg";
    public String ERROROK = "0010 ERROR";
    @Test
    public void testParser(){
        Parser parser = new Parser();
        Message msgObject1 = parser.parse(REGOK,null);
        System.out.println(msgObject1 instanceof RegisterResponse);
        Message msgObject2 = parser.parse(UNROK,null);
        System.out.println(msgObject2 instanceof UnregisterResponse);
        Message msgObject3 = parser.parse(JOINOK,null);
        System.out.println(msgObject3 instanceof JoinResponse);
        Message msgObject4 = parser.parse(LEAVEOK,null);
        System.out.println(msgObject4 instanceof LeaveResponse);
        Message msgObject5 = parser.parse(SEROK,null);
        System.out.println(msgObject5 instanceof SearchResponse);
        Message msgObject6 = parser.parse(ERROROK,null);
        System.out.println(msgObject6 instanceof ErrorResponse);
    }
}
