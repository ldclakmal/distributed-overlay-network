package org.uom.cse.cs4262.api;

/**
 * @author Chanaka Lakmal
 * @date 22/10/2017
 * @since 1.0
 */

public class Constant {

    public final static String IP_BOOTSTRAP_SERVER = "127.0.0.1";
    public final static String IP_NODE = "127.0.0.1";
    public final static int PORT_BOOTSTRAP_SERVER = 55555;
    public final static int PORT_NODE = 44444;
    public final static String USERNAME_BOOTSTRAP_SERVER = "Bootstrap";

    public static class Command {

        public final static String REG = "REG";
        public final static String REGOK = "REGOK";
        public final static String UNREG = "UNREG";
        public final static String UNREGOK = "UNREGOK";
        public final static String JOIN = "JOIN";
        public final static String JOINOK = "JOINOK";
        public final static String LEAVE = "LEAVE";
        public final static String LEAVEOK = "LEAVEOK";
        public final static String SEARCH = "SER";
        public final static String SEARCHOK = "SEROK";
        public final static String ERROR = "ERROR";
    }

}