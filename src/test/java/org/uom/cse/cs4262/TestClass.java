package org.uom.cse.cs4262;

import org.junit.Assert;
import org.junit.Test;
import org.uom.cse.cs4262.api.message.Message;
import org.uom.cse.cs4262.api.message.request.*;
import org.uom.cse.cs4262.api.message.response.*;
import org.uom.cse.cs4262.feature.Parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TestClass {

    public String REG = "0036 REG 129.82.123.45 5001 1234abcd";
    public String UNREG = "0028 UNREG 64.12.123.190 432 1234abcd";
    public String JOIN = "0027 JOIN 64.12.123.190 432";
    public String LEAVE = "0028 LEAVE 64.12.123.190 432";
    public String SER = "0047 SER 12 129.82.62.142 5070 Lord_of_the_rings 10";

    public String REGOK = "0051 REGOK 2 129.82.123.45 5001 64.12.123.190 34001";
    public String UNROK = "0012 UNROK 0";
    public String JOINOK = "0014 JOINOK 0";
    public String LEAVEOK = "0014 LEAVEOK 0";
    public String SEROK = "0114 SEROK 4 3 129.82.128.123 8823 01 baby_go_home.mp3 baby_come_back.mp3 baby.mpeg";
    public String ERROROK = "0010 ERROR";

    @Test
    public void testParser() {
        Parser parser = new Parser();
        Message msgObject1 = parser.parse(REGOK, null);
        Assert.assertTrue(msgObject1 instanceof RegisterResponse);
        Message msgObject2 = parser.parse(UNROK, null);
        Assert.assertTrue(msgObject2 instanceof UnregisterResponse);
        Message msgObject3 = parser.parse(JOINOK, null);
        Assert.assertTrue(msgObject3 instanceof JoinResponse);
        Message msgObject4 = parser.parse(LEAVEOK, null);
        Assert.assertTrue(msgObject4 instanceof LeaveResponse);
        Message msgObject5 = parser.parse(SEROK, null);
        Assert.assertTrue(msgObject5 instanceof SearchResponse);
        Message msgObject6 = parser.parse(ERROROK, null);
        Assert.assertTrue(msgObject6 instanceof ErrorResponse);
    }

    @Test
    public void testParserRequests() {
        Parser parser = new Parser();
        Message msgObject1 = parser.parse(REG, null);
        Assert.assertTrue(msgObject1 instanceof RegisterRequest);
        Message msgObject2 = parser.parse(UNREG, null);
        Assert.assertTrue(msgObject2 instanceof UnregisterRequest);
        Message msgObject3 = parser.parse(JOIN, null);
        Assert.assertTrue(msgObject3 instanceof JoinRequest);
        Message msgObject4 = parser.parse(LEAVE, null);
        Assert.assertTrue(msgObject4 instanceof LeaveRequest);
        Message msgObject5 = parser.parse(SER, null);
        Assert.assertTrue(msgObject5 instanceof SearchRequest);
    }

    @Test
    public void testSearch() {
        List<String> fileList = new ArrayList<>();
        fileList.add("Twilight");
        fileList.add("Jack");
        fileList.add("Jack and Jill");
        fileList.add("Twilight saga");
        fileList.add("My Twilight");
        Collections.shuffle(fileList);
        fileList = fileList.subList(0, 5);

        Pattern pattern = Pattern.compile("Twilight");
        List<String> matching = fileList.stream().filter(pattern.asPredicate()).collect(Collectors.toList());

        for (String s : matching) {
            Assert.assertTrue(s.equals("Twilight") || s.equals("Twilight saga") || s.equals("My Twilight"));
        }
    }
}