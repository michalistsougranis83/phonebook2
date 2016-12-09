package gr.infoera.phonebook.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import gr.infoera.phonebook.PhoneBookContract;
import gr.infoera.phonebook.PhonebookDbHelper;

/**
 * Created by mtsougranis on 9/12/2016.
 */

public class PhonebookContentProvider extends ContentProvider {
    private static final UriMatcher sUriMatcher=buildUriMatcher();
    private static final int CONTACTS=100;

    private PhonebookDbHelper phonebookDbHelper;

    static UriMatcher buildUriMatcher(){
        final UriMatcher matcher=new UriMatcher(UriMatcher.NO_MATCH);
        final String authority= PhoneBookContract.CONTENT_AUTHORITY;

        matcher.addURI(authority,PhoneBookContract.PATH_CONTACTS,CONTACTS);
        return matcher;
    }

    @Override
    public boolean onCreate(){
        phonebookDbHelper=new PhonebookDbHelper(getContext());
        return true;
    }
    @Override
    public String getType(Uri uri){
        final int match=sUriMatcher.match(uri);
        switch (match){
            case CONTACTS:
                return PhoneBookContract.Contacts.CONTENT_TYPE;

            default:
                throw new UnsupportedOperationException("Uknown URI: "+uri);
        }
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs){
        final SQLiteDatabase db=phonebookDbHelper.getWritableDatabase();
        final int match=sUriMatcher.match(uri);
        int rowsUpdated;

        switch (match){
            case CONTACTS:
                rowsUpdated=db.update(PhoneBookContract.Contacts.TABLE_NAME,contentValues, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Uknown URI: "+uri);
        }
        if (rowsUpdated!=0){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return rowsUpdated;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues){
        final SQLiteDatabase db=phonebookDbHelper.getWritableDatabase();
        final int match=sUriMatcher.match(uri);
        Uri returnUri;

        switch (match){
            case CONTACTS:
                long _id=db.insert(PhoneBookContract.Contacts.TABLE_NAME,null,contentValues);
                if (_id>0)
                    returnUri=PhoneBookContract.Contacts.buildContactsUri(_id);
                else
                    throw new android.database.SQLException(("Failed to insert row into "+uri));
                break;
            default:
                throw new UnsupportedOperationException("Uknown URI "+uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs){
        final SQLiteDatabase db=phonebookDbHelper.getWritableDatabase();
        final int match=sUriMatcher.match(uri);
        int rowsDeleted;

        if (null==selection) selection="1";

        switch (match){
            case CONTACTS:
                rowsDeleted=db.delete(
                        PhoneBookContract.Contacts.TABLE_NAME,selection,selectionArgs
                );
                break;
            default:
                throw new UnsupportedOperationException("Uknown URI "+uri);
        }
        if (rowsDeleted!=0){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return rowsDeleted;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder){
        Cursor retCursor;
        final int match=sUriMatcher.match(uri);
        switch (match){
            case CONTACTS:
                retCursor=phonebookDbHelper.getReadableDatabase().query(
                    PhoneBookContract.Contacts.TABLE_NAME,
                        projection,selection,selectionArgs,null,null,sortOrder
                );
                break;
            default:
                throw new UnsupportedOperationException("Uri not found "+uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(),uri);
        return retCursor;


    }

}
