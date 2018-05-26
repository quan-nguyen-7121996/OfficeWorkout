package com.quancuteexample.officeworkout;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class BoyActivity extends AppCompatActivity {
    private Intent intent;
    private ListView listView;
    private static CustomListAdapter adapter;
    public static List<WorkoutExcercise> listData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boy);
        intent= getIntent();
        Bundle extras=intent.getExtras();
        listView =findViewById(R.id.boylistView);

        if(extras.getString("level").equals("basic")) {
            listData=new ArrayList<>();
            adapter=new CustomListAdapter(this,listData);
            listView.setAdapter(adapter);
            //ReadJSON("http://192.168.1.102/workoutDatabase/boybasic.php");
            ReadJSON("http://118.69.182.206:8880/quan/php/boybasic.php");


        }
        if(extras.getString("level").equals("inter")) {
            listData=new ArrayList<>();
            adapter=new CustomListAdapter(this,listData);
            listView.setAdapter(adapter);
            //ReadJSON("http://192.168.1.102/workoutDatabase/boyinter.php");
            ReadJSON("http://118.69.182.206:8880/quan/php/boyinter.php");

        }
        if(extras.getString("level").equals("master")) {
            listData=new ArrayList<>();
            adapter=new CustomListAdapter(this,listData);
            listView.setAdapter(adapter);
            //ReadJSON("http://192.168.1.102/workoutDatabase/boymaster.php");
            ReadJSON("http://118.69.182.206:8880/quan/php/boymaster.php");
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                WorkoutExcercise workout = (WorkoutExcercise) o;
                Intent intent=new Intent(BoyActivity.this,ExerciseActivity.class);
                intent.putParcelableArrayListExtra("list", (ArrayList<? extends Parcelable>) listData);
                intent.putExtra("name",workout.getName());
                intent.putExtra("reps",workout.getRep());
                intent.putExtra("gif",workout.getGifName());
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
    }
    private void ReadJSON(String url){
        RequestQueue request= Volley.newRequestQueue(this);
        final JsonArrayRequest array=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject object=response.getJSONObject(i);
                        listData.add(new WorkoutExcercise(object.getString("Name"),object.getString("Reps"),object.getString("GifName")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                })
                ;
        request.add(array);
    }
    private void writetoFile(String filename){
        File file=new File(filename);
        String fileContents="hihi";
        if(file.length()==0){
            try {
                FileOutputStream out = this.openFileOutput(filename, MODE_PRIVATE);
                out.write(fileContents.getBytes());
                out.close();
                Toast.makeText(this,"File saved!",Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this,"Error:"+ e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
    }
}
