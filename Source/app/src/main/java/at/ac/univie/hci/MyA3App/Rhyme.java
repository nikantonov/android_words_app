package at.ac.univie.hci.MyA3App;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.JsonReader;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;


public class Rhyme extends AppCompatActivity {

    /*This is a set to save my results*/
    final java.util.Set<String> results = new HashSet<String>();
    String word3 = null;

    /* This is a method to run this page*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rhyme);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String word = getIntent().getExtras().getString("word");

        TextView infoTextView = (TextView)findViewById(R.id.text2);
        infoTextView.setText("Your word: "+word);

        final String word2 = word;
        word3 = word2;
        //final java.util.Set<String> results = new HashSet<String>();

        /*This is an asynchronous task to run the internet connection*/
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL Datamuse = new URL("https://api.datamuse.com/words?rel_rhy="+word2);

                    HttpsURLConnection new_Connection =
                            (HttpsURLConnection) Datamuse.openConnection();

                    if (new_Connection.getResponseCode() == 200) {
                        System.out.println("Ok");
                    } else {
                        System.out.println("URL not valid");
                    }

                    InputStream responseBodyReader = new_Connection.getInputStream();

                    /*
                      Here i used idea of two Objects (BufferedReader and StringBuilder) from
                        https://stackoverflow.com/questions/22461663/convert-inputstream-to-jsonobject
                       to parse my JSON correctly.
                       First i tried to use a JSONParser Object, but i had problems with parsing JSONArray
                     */
                    BufferedReader streamBodyReader = new BufferedReader(new InputStreamReader(responseBodyReader, "UTF-8"));
                    StringBuilder responseStringBuilder = new StringBuilder();

                    String answer;
                    while ((answer = streamBodyReader.readLine()) != null)
                        responseStringBuilder.append(answer);
                    /*End*/

                    JSONArray jsonArray = new JSONArray(responseStringBuilder.toString());

                    /*
                     Here i save the results. I save only max. 20 results, cause if there are for example 50-100,
                      it is hard to understand something.
                     */
                    for(int i = 0; i < jsonArray.length();i++){
                        JSONObject Jsn = jsonArray.getJSONObject(i);
                        String Word = Jsn.getString("word");
                        if (results.size() <= 20) {
                            results.add(Word);
                        }

                    }

                    new_Connection.disconnect();

                } catch (IOException e ) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                /*Here i display the results on the screen*/
                String rhyme = "Rhyme to your word:"+"\n";
                for (String s : results){
                    rhyme = rhyme + s +", ";
                }
                rhyme = rhyme + word2 +"(Your word).";
                TextView infoTextView2 = findViewById(R.id.text3);
                infoTextView2.setText(rhyme);
            }
        });




        /*Was default by creating Android activity*/
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    /*This is a method to activate the "Save" button and save the results*/
    public void onClick10(View v1) {
        LastSave.setWord(word3);
        LastSave.setWords(results);
        TextView infoTextView = findViewById(R.id.text20);
        if(results.size() >= 0) {
            infoTextView.setText("SAVED!");
        }
        else
            infoTextView.setText("NOTHING TO SAVE!");
    }

}
