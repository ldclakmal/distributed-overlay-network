package org.uom.cse.cs4262.api;

/**
 * @author Chanaka Lakmal
 * @date 22/10/2017
 * @since 1.0
 */

public class Constant {

    public final static String IP_BOOTSTRAP_SERVER = "127.0.0.1";
    public final static int PORT_BOOTSTRAP_SERVER = 55555;
    public final static String USERNAME_BOOTSTRAP_SERVER = "Bootstrap";
    public final static int MIN_PORT_NODE = 40000;
    public final static int MAX_PORT_NODE = 50000;

    public static class Command {
        public final static String REG = "REG";
        public final static String REGOK = "REGOK";
        public final static String UNREG = "UNREG";
        public final static String UNREGOK = "UNROK";
        public final static String JOIN = "JOIN";
        public final static String JOINOK = "JOINOK";
        public final static String LEAVE = "LEAVE";
        public final static String LEAVEOK = "LEAVEOK";
        public final static String SEARCH = "SER";
        public final static String SEARCHOK = "SEROK";
        public final static String ERROR = "ERROR";
    }

    public static class Codes {

        public static class Register {
            public final static int ERROR_CANNOT_REGISTER = 9996;
            public final static int ERROR_DUPLICATE_IP = 9997;
            public final static int ERROR_ALREADY_REGISTERED = 9998;
            public final static int ERROR_COMMAND = 9999;
        }

        public static class Search {
            public final static int ERROR_NODE_UNREACHABLE = 9999;
            public final static int ERROR_OTHER = 9998;

        }

    }
}