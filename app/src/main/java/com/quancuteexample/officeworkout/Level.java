package com.quancuteexample.officeworkout;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class Level extends AppCompatActivity {
    private Context myContext;
    private Intent boyIntent;
    private Intent girlIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        final Button basicButton = findViewById(R.id.button1);
        basicButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent= getIntent();
                Bundle extras=intent.getExtras();
                if (extras.getString("sex").equals("boy")) {
                    //Toast.makeText(getApplicationContext(), "boy basic", Toast.LENGTH_LONG).show();
                    boyIntent=new Intent(Level.this,BoyActivity.class);
                    boyIntent.putExtra("level","basic");
                    startActivity(boyIntent);
                    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                }
                if (extras.getString("sex").equals("girl")) {
                    //Toast.makeText(getApplicationContext(), "girl basic", Toast.LENGTH_LONG).show();
                    girlIntent=new Intent(Level.this,GirlActivity.class);
                    girlIntent.putExtra("level","basic");
                    startActivity(girlIntent);
                    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                }
            }
        });
        final Button interButton = findViewById(R.id.button2);
        interButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent= getIntent();
                Bundle extras=intent.getExtras();
                if (extras.getString("sex").equals("boy")) {
                    //Toast.makeText(getApplicationContext(), "boy inter", Toast.LENGTH_LONG).show();
                    boyIntent=new Intent(Level.this,BoyActivity.class);
                    boyIntent.putExtra("level","inter");
                    startActivity(boyIntent);
                    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                }
                if (extras.getString("sex").equals("girl")) {
                    //Toast.makeText(getApplicationContext(), "girl inter", Toast.LENGTH_LONG).show();
                    girlIntent=new Intent(Level.this,GirlActivity.class);
                    girlIntent.putExtra("level","inter");
                    startActivity(girlIntent);
                    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                }
            }
        });
        final Button masterButton = findViewById(R.id.button3);
        masterButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent= getIntent();
                Bundle extras=intent.getExtras();
                if (extras.getString("sex").equals("boy")) {
                    //Toast.makeText(getApplicationContext(), "boy master", Toast.LENGTH_LONG).show();
                    boyIntent=new Intent(Level.this,BoyActivity.class);
                    boyIntent.putExtra("level","master");
                    startActivity(boyIntent);
                    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                }
                if (extras.getString("sex").equals("girl")) {
                    //Toast.makeText(getApplicationContext(), "girl master", Toast.LENGTH_LONG).show();
                    girlIntent=new Intent(Level.this,GirlActivity.class);
                    girlIntent.putExtra("level","master");
                    startActivity(girlIntent);
                    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                }
            }
        });
    }
}

