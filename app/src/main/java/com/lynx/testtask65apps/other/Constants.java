package com.lynx.testtask65apps.other;

public class Constants {

    public static class Database{
        private static final String TABLE_NAME = "workers";

        public static class Columns{
//            public static final String
        }

        public static class Queries{
            public static final String CREATE_DATABASE = "CREATE TABLE " + TABLE_NAME;
        }
    }

    public static class BundleKeys{
        public static final String ID_KEY = "id_key";
    }

    public static class WorkersApi{
        public static final String BASE_URL = "http://gitlab.65apps.com";
    }
}
