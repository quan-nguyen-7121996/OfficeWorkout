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

import java.util.ArrayList;
import java.util.List;

public class BoyActivity extends AppCompatActivity {
    private Intent intent;
    private ListView listView;
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
            WorkoutExcercise plank = new WorkoutExcercise("plank","20","http://assets.menshealth.co.uk/main/assets/plank.gif?mtime=1429703016");
            WorkoutExcercise pushup = new WorkoutExcercise("push up", "30","http://assets.menshealth.co.uk/main/assets/press-up_animated.gif?mtime=1447238571");
            WorkoutExcercise planche = new WorkoutExcercise("planche","100","http://assets.menshealth.co.uk/main/assets/99-Mountain-climbers.gif?mtime=1499687675");
            listData.add(plank);
            listData.add(pushup);
            listData.add(planche);

            listView.setAdapter(new CustomListAdapter(this,listData));
        }
        if(extras.getString("level").equals("inter")) {
            listData=new ArrayList<WorkoutExcercise>();
            WorkoutExcercise plank = new WorkoutExcercise("Diamond","20","http://assets.menshealth.co.uk/main/assets/diamond.gif?mtime=1444148582");
            WorkoutExcercise pushup = new WorkoutExcercise("slow clap", "30","http://assets.menshealth.co.uk/main/assets/slowclap.gif?mtime=1444151173");
            WorkoutExcercise planche = new WorkoutExcercise("Decline push up","100","http://finetixfitness.com/images/Exercise%20Library/Decline%20Push%20Up.gif");
            listData.add(plank);
            listData.add(pushup);
            listData.add(planche);

            listView.setAdapter(new CustomListAdapter(this,listData));
        }
        if(extras.getString("level").equals("master")) {
            listData=new ArrayList<WorkoutExcercise>();
            WorkoutExcercise plank = new WorkoutExcercise("arrow push up","20","https://static1.squarespace.com/static/51085846e4b0c6e336d6b1b2/t/5a5230920852298a1222efd0/1515335982576/Arrow+Push+Up.gif?format=500w");
            WorkoutExcercise pushup = new WorkoutExcercise("burpee", "30","http://s3.amazonaws.com/photography.prod.demandstudios.com/977bf1e0-fb9f-439a-b8ad-b4eb65cfdfc5.gif");
            WorkoutExcercise planche = new WorkoutExcercise("pistol squat","100","https://s.yimg.com/ny/api/res/1.2/WOfD51ifHeNo3iNoVlrKEg--/YXBwaWQ9aGlnaGxhbmRlcjtzbT0xO3c9NTIwO2g9MzQ1/http://33.media.tumblr.com/0946f0fabb272f9ce2f8ce5d1093b163/tumblr_inline_nxeqqtFN3I1tdqw1o_540.gif");
            listData.add(plank);
            listData.add(pushup);
            listData.add(planche);
            listView.setAdapter(new CustomListAdapter(this,listData));

        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                WorkoutExcercise workout = (WorkoutExcercise) o;
                //Toast.makeText(BoyActivity.this, "Selected :" + " " + position, Toast.LENGTH_LONG).show();
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
}
