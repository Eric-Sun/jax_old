package com.j13.bar.server.exceptions;

public class ErrorCode {

    public static class Common {
        public static int INPUT_PARAMETER_ERROR = 4;
    }

    public static class User {
        public static int PASSWORD_NOT_RIGHT = 1001;
        public static int MOBILE_EXISTED = 1002;
        public static int NICKNAME_EXISTED = 1003;
    }

    public static class System {
        public static int SYSTEM_ERROR = 1;
        public static int NOT_FOUND_ACTION = 2;
        public static int ACTION_REFLECT_ERROR = 3;
    }


}
