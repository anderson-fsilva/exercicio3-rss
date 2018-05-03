package com.example.afs10.rssconsumer;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);



        // Obtendo uma instância do ContentResolver e as colunas do ContentProvider.
        ContentResolver contentResolver = getContentResolver();

        Cursor c = contentResolver.query(
                RssProviderContract.ITEMS_LIST_URI,
                null,
                null,
                null,
                null
        );


        SimpleCursorAdapter adapter =
                new SimpleCursorAdapter(
                        this,
                        R.layout.itemlista,
                        c,
                        new String[] {RssProviderContract.TITLE, RssProviderContract.DATE},
                        new int[] {R.id.itemTitulo, R.id.itemData},
                        0
                );

        setListAdapter(adapter);
    }
}
