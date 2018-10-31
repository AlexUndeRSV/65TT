package com.lynx.testtask65apps.other;

public class Constants {

    public static class Database{

        public static class SpecialityTable{
            public static final String TABLE_NAME = "spec_table";

            public static class Columns{
                public static final String COLUMN_ID = "spec_id";
                public static final String COLUMN_TITLE = "spec_title";
            }

            public static class Queries{
                public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + "("
                        + Columns.COLUMN_ID + " TEXT PRIMARY KEY, "
                        + Columns.COLUMN_TITLE + " TEXT not null"
                        +");";
                public static final String TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;
            }
        }

        public static class WorkersTable{
            public static final String TABLE_NAME = "workers_table";

            public static class Columns{
                public static final String COLUMN_FIRST_NAME = "worker_f_name";
                public static final String COLUMN_LAST_NAME = "worker_l_name";
                public static final String COLUMN_BIRTHDAY = "worker_birthday";
                public static final String COLUMN_AVATAR_URL = "worker_avatar_url";
                public static final String COLUMN_SPEC_IDS = "spec_id";
            }

            public static class Queries{
                public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + "("
                        + Columns.COLUMN_FIRST_NAME + " TEXT not null, "
                        + Columns.COLUMN_LAST_NAME + " TEXT not null, "
                        + Columns.COLUMN_BIRTHDAY + " TEXT, "
                        + Columns.COLUMN_AVATAR_URL + " TEXT, "
                        + Columns.COLUMN_SPEC_IDS + " TEXT not null"
                        +");";
                public static final String TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;
            }
        }
    }

    public static class BundleKeys{
        public static final String ID_KEY = "id_key";
        public static final String TITLE_KEY = "title_key";
    }

    public static class WorkersApi{
        public static final String BASE_URL = "http://gitlab.65apps.com";
    }


}
