package com.example.animequotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView anime,character,quote;
    ProgressBar progress;
    String send="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anime=findViewById(R.id.anime);
        character=findViewById(R.id.character);
        quote=findViewById(R.id.quote);
    }

    void getdata()
    {
        String url ="https://animechan.vercel.app/api/random";
        StringRequest mystringrequest=new StringRequest(Request.Method.GET,url,response -> {
            try {
                JSONObject jsonObject=new JSONObject(response);
                anime.setText(jsonObject.getString("anime"));
                character.setText(jsonObject.getString("character"));
                quote.setText(jsonObject.getString("quote"));
                send="Anime Name: "+jsonObject.getString("anime")+"\n"+"Character Name: "+jsonObject.getString("character")+"\n"+"Quote: "+jsonObject.getString("quote");

            }catch (JSONException exception){
                exception.printStackTrace();
            }
        },
                volleyError-> Toast.makeText(this,volleyError.getMessage(),Toast.LENGTH_SHORT).show()
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(mystringrequest);

    }
    public void onNext(View view){
        getdata();
    }


    public void onShare(View view) {
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT,send);
        intent.setType("text/plain");
        startActivity(intent);

    }
}