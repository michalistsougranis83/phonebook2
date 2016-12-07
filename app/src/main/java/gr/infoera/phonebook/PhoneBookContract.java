package gr.infoera.phonebook;

import android.provider.BaseColumns;

/**
 * Created by mtsougranis on 5/12/2016.
 */

public final class PhoneBookContract {
    private PhoneBookContract(){};
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    public static class Contacts implements BaseColumns {
        public static final String TABLE_NAME = "contacts";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_SURNAME = "surname";
        public static final String COLUMN_PHONE="phone";
    }

    public static final String SQL_CREATE_PHONEBOOK_TABLE=
            "CREATE TABLE "+Contacts.TABLE_NAME+" ("+Contacts.COLUMN_NAME+TEXT_TYPE+COMMA_SEP
                    +Contacts.COLUMN_SURNAME+TEXT_TYPE+COMMA_SEP+
                    Contacts.COLUMN_PHONE+TEXT_TYPE+")";
    public static final String SQL_DELETE_ENTRIES_PHONEBOOK_TABLE=
            "DROP TABLE IF EXISTS "+Contacts.TABLE_NAME;
}
