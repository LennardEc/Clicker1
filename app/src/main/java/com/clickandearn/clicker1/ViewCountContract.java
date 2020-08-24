package com.clickandearn.clicker1;

import android.provider.BaseColumns;

public class ViewCountContract {
    private ViewCountContract() {}

    public static class ViewCount implements BaseColumns {
        public static final String DATABASE_NAME = "clickerDB";
        public static final String TABLE_NAME = "viewCount";
        public static final String COLUMN_NAME_CLICKS = "clicks";
        public static final String COLUMN_NAME_VIEWS = "views";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_AGB_VERSION = "agb_version";
    }
}
