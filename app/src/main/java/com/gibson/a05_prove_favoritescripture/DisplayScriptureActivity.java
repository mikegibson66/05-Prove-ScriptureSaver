package com.gibson.a05_prove_favoritescripture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayScriptureActivity extends AppCompatActivity {
    // Constant and variable defined
    private static final String TAG = "DisplayActivity";
    private String book;
    private String chapter;
    private String verse;

    // getters and setters for class variables
    public void setBook(String book) {
        this.book = book;
    }
    public String getBook() {
        return book;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }
    public String getChapter() {
        return chapter;
    }

    public void setVerse(String verse) {
        this.verse = verse;
    }
    public String getVerse() {
        return verse;
    }

    /** Called when user clicks Save Scripture button */
    public void saveScripture(View view) {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("favoriteScripture", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.putString("book_name", getBook());
        editor.putString("chapter_no", getChapter());
        editor.putString("verses", getVerse());

        // commit changes to preferences
        editor.commit();

        // toast to notify user the reference is being saved to preferences.
        Context context = getApplicationContext();
        CharSequence text = "Saving your favorite scripture...";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    /** Loads on creation of activity from MainActivity */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_scriptures);

        // Get intent that started the activity and extract reference
        Intent intent = getIntent();
        setBook(intent.getStringExtra(MainActivity.BOOK));
        setChapter(intent.getStringExtra(MainActivity.CHAPTER));
        setVerse(intent.getStringExtra(MainActivity.VERSE));

        // set the layout's TextView with string as it's text
        TextView textView = findViewById(R.id.textView);
        String reference = getBook() + " " +
                getChapter() +
                ":" +
                getVerse();

        // log receipt of intent
        String msg = "Received intent with " + reference;
        Log.d(TAG, msg);

        // display
        textView.setText(reference);
    }
}