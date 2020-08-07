package at.ac.univie.hci.MyA3App;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    
    /*This is the main method to create and run this page*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*This is a method to run the second page by clicking the button "Rhyme"*/
    public void onClick1(View v1) {
        EditText rhymeEditText = findViewById(R.id.Text1);
        Intent intent = new Intent(MainActivity.this, Rhyme.class);
        intent.putExtra("word", rhymeEditText.getText().toString());
        startActivity(intent);
    }

    /*This is a method to run the third page by clicking the button "Sound like"*/
    public void onClick2(View v1) {
        EditText rhymeEditText = findViewById(R.id.Text1);
        Intent intent = new Intent(MainActivity.this, SoundLike.class);
        intent.putExtra("word", rhymeEditText.getText().toString());
        startActivity(intent);
    }

    /*This is a method to run the fourth page by clicking the button "Spelled Similarly"*/
    public void onClick3(View v1) {
        EditText rhymeEditText = findViewById(R.id.Text1);
        Intent intent = new Intent(MainActivity.this, SpelledSimilarly.class);
        intent.putExtra("word", rhymeEditText.getText().toString());
        startActivity(intent);
    }

    /*This is a method to run the fifth page by clicking the button "Last save"*/
    public void onClick4(View v1) {
        Intent intent = new Intent(MainActivity.this, LastSave.class);
        startActivity(intent);
    }
}
