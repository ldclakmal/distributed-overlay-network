package org.uom.cse.cs4262;

import org.junit.Test;
import org.uom.cse.cs4262.api.message.Message;
import org.uom.cse.cs4262.api.message.request.*;
import org.uom.cse.cs4262.api.message.response.*;

/**
 * @author Chandu Herath
 * @date 24/10/2017
 * @since 1.0
 */

public class TestParser {

    public String REG = "0036 REG 129.82.123.45 5001 1234abcd";
    public String UNREG = "0028 UNREG 64.12.123.190 432 1234abcd";
    public String JOIN = "0027 JOIN 64.12.123.190 432";
    public String LEAVE = "0028 LEAVE 64.12.123.190 432";
    public String SER = "0047 SER 12 129.82.62.142 5070 \"Lordoftherings\" 10";

    public String REGOK = "0051 REGOK 2 129.82.123.45 5001 64.12.123.190 34001";
    public String UNROK = "0012 UNROK 0";
    public String JOINOK = "0014 JOINOK 0";
    public String LEAVEOK = "0014 LEAVEOK 0";
    public String SEROK = "0114 SEROK 4 3 129.82.128.123 8823 01 baby_go_home.mp3 baby_come_back.mp3 baby.mpeg";
    public String ERROROK = "0010 ERROR";

    @Test
    public void testParserResponses() {
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

    @Test
    public void testParserRequests() {
        Parser parser = new Parser();
        Message msgObject1 = parser.parse(REG, null);
        System.out.println(msgObject1 instanceof RegisterRequest);
        Message msgObject2 = parser.parse(UNREG, null);
        System.out.println(msgObject2 instanceof UnregisterRequest);
        Message msgObject3 = parser.parse(JOIN, null);
        System.out.println(msgObject3 instanceof JoinRequest);
        Message msgObject4 = parser.parse(LEAVE, null);
        System.out.println(msgObject4 instanceof LeaveRequest);
        Message msgObject5 = parser.parse(SER, null);
        System.out.println(msgObject5 instanceof SearchRequest);
    }

}
