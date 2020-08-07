package at.ac.univie.hci.MyA3App;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.HashSet;

public class LastSave extends AppCompatActivity {

    /*In word i am saving the key word
    * In words i am saving the latest saved search
    * */
    private static String word;
    static java.util.Set<String> words = new HashSet<String>();

    public static void setWord(String word2){
        word = word2;
    }

    public static void setWords(java.util.Set<String> words2){
        words = words2;
    }

    /*This is a method to run this class and show the latest saved word and search*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.last_save);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView infoTextView = findViewById(R.id.text10);
        if(word == null){
            infoTextView.setText("You did not save anything");
        }else
        infoTextView.setText("Your last saved word: "+word);

        String rhyme = "Your last saved search:"+"\n";
        if(words.size() == 0){
            rhyme = rhyme + "you did not save anything";
        }else {
            for (String s : words) {
                rhyme = rhyme + s + ", ";
            }
            rhyme = rhyme + word + "(Your word).";
        }
        TextView infoTextView2 = findViewById(R.id.text11);
        infoTextView2.setText(rhyme);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }



}
