package com.quancuteexample.officeworkout;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
    String tableName;
    public static List<WorkoutExcercise> listData;
    private static CustomListAdapter adapter;
    LocalDatabase localDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girl);
        intent=getIntent();
        Bundle extras=intent.getExtras();
        listView =findViewById(R.id.girllistView);
        localDatabase=new LocalDatabase(this,"GirlExercise.sqlite",null,2);
        localDatabase.QueryData("CREATE TABLE IF NOT EXISTS GirlBasic(Name TEXT PRIMARY KEY, Reps TEXT, GifName TEXT)");
        localDatabase.QueryData("CREATE TABLE IF NOT EXISTS GirlInter(Name TEXT PRIMARY KEY, Reps TEXT, GifName TEXT)");
        localDatabase.QueryData("CREATE TABLE IF NOT EXISTS GirlMaster(Name TEXT PRIMARY KEY, Reps TEXT, GifName TEXT)");

        if(extras.getString("level").equals("basic")) {
            listData=new ArrayList<>();
            tableName="GirlBasic";
            adapter=new CustomListAdapter(this,listData);
            listView.setAdapter(adapter);
            //ReadJSON("http://192.168.1.102/workoutDatabase/girlbasic.php");
            //ReadJSON("http://118.69.182.206:8880/quan/php/girlbasic.php");
            if(connectionAvailable()==true) {
                ReadJSON("http://118.69.182.206:8880/quan/php/girlbasic.php");
            }
            else{
                //Toast.makeText(GirlActivity.this, "Selected :" + "khong co mạng nhé !!! " , Toast.LENGTH_LONG).show();
                GetData("GirlBasic");
            }
        }
        if(extras.getString("level").equals("inter")) {
            listData=new ArrayList<>();
            tableName="GirlInter";
            adapter=new CustomListAdapter(this,listData);
            listView.setAdapter(adapter);
            //ReadJSON("http://192.168.1.102/workoutDatabase/girlinter.php");
            if(connectionAvailable()==true) {
                ReadJSON("http://118.69.182.206:8880/quan/php/girlinter.php");
            }
            else{
                //Toast.makeText(GirlActivity.this, "Selected :" + "khong co mạng nhé !!! " , Toast.LENGTH_LONG).show();
                GetData("GirlInter");
            }
        }
        if(extras.getString("level").equals("master")) {
            listData=new ArrayList<>();
            tableName="GirlMaster";
            adapter=new CustomListAdapter(this,listData);
            listView.setAdapter(adapter);
           // ReadJSON("http://192.168.1.102/workoutDatabase/girlmaster.php");
            //ReadJSON("http://118.69.182.206:8880/quan/php/girlmaster.php");
            if(connectionAvailable()==true) {
                ReadJSON("http://118.69.182.206:8880/quan/php/girlmaster.php");
            }
            else{
                GetData("GirlMaster");
            }
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
                if(localDatabase.GetCount(tableName)!=response.length()) {
                    localDatabase.QueryData("DELETE FROM "+tableName);
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject object = response.getJSONObject(i);
                            //Toast.makeText(GirlActivity.this, object.getString("Name"), Toast.LENGTH_LONG).show();
                            String SQLiteDataBaseQueryHolder = "INSERT INTO " + tableName + "(Name,Reps,GifName) VALUES ('" + object.getString("Name") + "', '" + object.getString("Reps") + "', '" + object.getString("GifName") + "')";
                            localDatabase.QueryData(SQLiteDataBaseQueryHolder);
                        } catch (JSONException E) {
                            E.fillInStackTrace();
                        }
                    }
                }
                //Toast.makeText(GirlActivity.this,tableName, Toast.LENGTH_LONG).show();
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

    private void GetData(String tableName){
        Cursor dataExercise=localDatabase.GetData("SELECT * FROM "+tableName+";");
        if (dataExercise.moveToFirst()) {
            do {
                WorkoutExcercise exercise = new WorkoutExcercise(dataExercise.getString(0), dataExercise.getString(1), dataExercise.getString(2));
                listData.add(exercise);
            } while (dataExercise.moveToNext());
            adapter.notifyDataSetChanged();
        }
        adapter.notifyDataSetChanged();
        //Toast.makeText(GirlActivity.this, String.valueOf(localDatabase.GetCount(tableName)), Toast.LENGTH_LONG).show();

    }
    private boolean connectionAvailable() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            connected = true;
        }
        return connected;
    }
}
