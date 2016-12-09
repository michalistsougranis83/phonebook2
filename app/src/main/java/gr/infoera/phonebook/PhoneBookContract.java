package gr.infoera.phonebook;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by mtsougranis on 5/12/2016.
 */

public final class PhoneBookContract {
    private PhoneBookContract(){};
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    public static final String CONTENT_AUTHORITY="gr.infoera.phonebook";
    public static final Uri BASE_CONTENT_URI=Uri.parse("content://"+CONTENT_AUTHORITY);
    public static final String PATH_CONTACTS="contacts";


    public static class Contacts implements BaseColumns {
        public static final Uri CONTENT_URI=
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_CONTACTS).build();
        public static final String TABLE_NAME = "contacts";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_SURNAME = "surname";
        public static final String COLUMN_PHONE="phone";
        public static final String CONTENT_TYPE=
                ContentResolver.CURSOR_DIR_BASE_TYPE+"/"+CONTENT_AUTHORITY+"/"+PATH_CONTACTS;

        public static Uri buildContactsUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI,id);
        }
    }

    public static final String SQL_CREATE_PHONEBOOK_TABLE=
            "CREATE TABLE "+Contacts.TABLE_NAME+" ("+Contacts.COLUMN_NAME+TEXT_TYPE+COMMA_SEP
                    +Contacts.COLUMN_SURNAME+TEXT_TYPE+COMMA_SEP+
                    Contacts.COLUMN_PHONE+TEXT_TYPE+")";
    public static final String SQL_DELETE_ENTRIES_PHONEBOOK_TABLE=
            "DROP TABLE IF EXISTS "+Contacts.TABLE_NAME;
}
