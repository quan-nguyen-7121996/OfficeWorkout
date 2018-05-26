package com.quancuteexample.officeworkout;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageButton boyButton = findViewById(R.id.boyButton);
        boyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String value="boy";

                Intent intent=new Intent(MainActivity.this,Level.class);
                intent.putExtra("sex",value);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);


            }
        });
        final ImageButton girlButton = findViewById(R.id.girlButton);
        girlButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String value="girl";
                Intent intent=new Intent(MainActivity.this,Level.class);
                intent.putExtra("sex",value);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });

    }
}
