package gr.infoera.phonebook;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

/**
 * Created by mtsougranis on 9/12/2016.
 */

public class TestProvider extends AndroidTestCase {
    //prepei sto manifest.xml na diloseis ton content provider
    public static final String LOG_TAG=TestProvider.class.getSimpleName();

    public void testdeleteAllRecordsFromProvider(){
        mContext.getContentResolver().delete(
                PhoneBookContract.Contacts.CONTENT_URI,null,null
        );

        Cursor cursor=mContext.getContentResolver().query(PhoneBookContract.Contacts.CONTENT_URI,
                null,null,null,null);

        assertEquals("Error: Records not deleted from Weather table during delete ",0,cursor.getCount());
        cursor.close();
    }

    public ContentValues getTestContentValues(){
        ContentValues contentValues=new ContentValues();
        contentValues.put(PhoneBookContract.Contacts.COLUMN_NAME,"michalis");
        contentValues.put(PhoneBookContract.Contacts.COLUMN_SURNAME,"tsougranis");
        contentValues.put(PhoneBookContract.Contacts.COLUMN_PHONE,"22430");
        return contentValues;
    }
    public void testBasicLocationQueries(){
        PhonebookDbHelper dbHelper=new PhonebookDbHelper(mContext);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues(getTestContentValues());

        Cursor cursor=mContext.getContentResolver().query(
                PhoneBookContract.Contacts.CONTENT_URI,
                null,null,null,null
        );
        //TODO: na synexiso ti methodo
    }
}


