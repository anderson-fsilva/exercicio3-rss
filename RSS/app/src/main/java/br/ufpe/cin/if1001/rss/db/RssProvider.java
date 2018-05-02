package br.ufpe.cin.if1001.rss.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

public class RssProvider extends ContentProvider {

    SQLiteRSSHelper db;

    public RssProvider() {
    }

    private boolean isRSSUri(Uri uri) {
        return uri.getLastPathSegment().equals(RssProviderContract.ITEMS_TABLE);
    }



    // Implementação do método de deleção.


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        if (isRSSUri(uri)) {
            return db.getWritableDatabase().delete(SQLiteRSSHelper.DATABASE_TABLE, selection, selectionArgs);

        } else {
            return 0;
        }
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }



    // Implementação do método de inserção.


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (isRSSUri(uri)) {
            long id = db.getWritableDatabase().insert(SQLiteRSSHelper.DATABASE_TABLE, null, values);
            return Uri.withAppendedPath(RssProviderContract.ITEMS_LIST_URI, Long.toString(id));
        } else {
            return null;
        }
    }



    @Override
    public boolean onCreate() {
        db = SQLiteRSSHelper.getInstance(getContext());

        return true;
    }



    // Implementação da query.


    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        Cursor cursor = null;

        if (isRSSUri(uri)) {
            cursor = db.getReadableDatabase().query(SQLiteRSSHelper.DATABASE_TABLE, projection, selection, selectionArgs, null, null, sortOrder);

        } else {
            throw new UnsupportedOperationException("Not yet implemented.");
        }

        return cursor;
    }


    // Implementação do método de atualização.


    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {

        if (isRSSUri(uri)) {
            return db.getWritableDatabase().update(SQLiteRSSHelper.DATABASE_TABLE, values, selection, selectionArgs);

        } else {
            return 0;
        }
    }
}
