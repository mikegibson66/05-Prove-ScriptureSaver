package com.gibson.a05_prove_favoritescripture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    /** define constants */
    private static final String TAG = "MainActivity";
    public final static String BOOK = "com.gibson.a05_prove_favoritescripture.BOOK";
    public final static String CHAPTER = "com.gibson.a05_prove_favoritescripture.CHAPTER";
    public final static String VERSE = "com.gibson.a05_prove_favoritescripture.VERSE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when user taps Send button */
    public void sendScripture(View view) {

        Intent intent = new Intent(this, DisplayScriptureActivity.class);

        // get scripture reference form form
        EditText editBook = (EditText) findViewById(R.id.book);
        EditText editChapter = (EditText) findViewById(R.id.chapter);
        EditText editVerse = (EditText) findViewById(R.id.verse);

        // put references into variables
        String book = editBook.getText().toString();
        String chapter = editChapter.getText().toString();
        String verse = editVerse.getText().toString();

        // debug log message
        String msg = "About to create intent with " + book + " " + chapter + ":" + verse;
        Log.d(TAG, msg);

        // add references to intent extras
        intent.putExtra(BOOK, book);
        intent.putExtra(CHAPTER, chapter);
        intent.putExtra(VERSE, verse);

        // start activity
        startActivity(intent);
    }

    /** Called when user clicks Load Scripture */
    public void loadScripture(View view) {
        // toast notifying user the favorite is being loaded
        Context context = getApplicationContext();
        CharSequence text = "Loading your favorite scripture...";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        // load preference file
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("favoriteScripture", 0);
        String bookName = preferences.getString("book_name", null);
        String chapterNo = preferences.getString("chapter_no", null);
        String verseNo = preferences.getString("verses", null);

        // access fields in form
        EditText editBook = (EditText) findViewById(R.id.book);
        EditText editChapter = (EditText) findViewById(R.id.chapter);
        EditText editVerse = (EditText) findViewById(R.id.verse);

        // update fields in form
        editBook.setText(bookName);
        editChapter.setText(chapterNo);
        editVerse.setText(verseNo);
    }
}