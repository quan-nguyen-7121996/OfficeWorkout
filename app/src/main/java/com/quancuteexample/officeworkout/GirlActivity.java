package com.quancuteexample.officeworkout;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GirlActivity extends AppCompatActivity {
    private Intent intent;
    private ListView listView;
    public static List<WorkoutExcercise> listData;
    private static CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girl);
        intent=getIntent();
        Bundle extras=intent.getExtras();
        listView =findViewById(R.id.girllistView);
        if(extras.getString("level").equals("basic")) {
            listData=new ArrayList<>();
            adapter=new CustomListAdapter(this,listData);
            listView.setAdapter(adapter);
            //ReadJSON("http://192.168.1.102/workoutDatabase/girlbasic.php");
            ReadJSON("http://118.69.182.206:8880/quan/php/girlbasic.php");

        }
        if(extras.getString("level").equals("inter")) {
            listData=new ArrayList<>();
            adapter=new CustomListAdapter(this,listData);
            listView.setAdapter(adapter);
            //ReadJSON("http://192.168.1.102/workoutDatabase/girlinter.php");
            ReadJSON("http://118.69.182.206:8880/quan/php/girlinter.php");


        }
        if(extras.getString("level").equals("master")) {
            listData=new ArrayList<>();
            adapter=new CustomListAdapter(this,listData);
            listView.setAdapter(adapter);
           // ReadJSON("http://192.168.1.102/workoutDatabase/girlmaster.php");
            ReadJSON("http://118.69.182.206:8880/quan/php/girlmaster.php");


        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                WorkoutExcercise workout = (WorkoutExcercise) o;
                //Toast.makeText(GirlActivity.this, "Selected :" + " " + position, Toast.LENGTH_LONG).show();
                Intent intent=new Intent(GirlActivity.this,ExerciseActivity.class);
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
                        Toast.makeText(GirlActivity.this, error.toString(), Toast.LENGTH_LONG).show();

                    }
                })
                ;
        request.add(array);

    }
}
