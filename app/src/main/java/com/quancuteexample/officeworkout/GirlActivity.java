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

public class GirlActivity extends AppCompatActivity {
    private Intent intent;
    private ListView listView;
    public static List<WorkoutExcercise> listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girl);
        intent=getIntent();
        Bundle extras=intent.getExtras();
        listView =findViewById(R.id.girllistView);
        if(extras.getString("level").equals("basic")) {
            listData=new ArrayList<WorkoutExcercise>();
            WorkoutExcercise plank = new WorkoutExcercise("plank","20","https://img.aws.livestrongcdn.com/ls-article-image-640/cme/photography.prod.demandstudios.com/da320ae2-3ff6-4c3d-9036-dbb354179704.gif");
            WorkoutExcercise pushup = new WorkoutExcercise("high plank", "30","https://img.aws.livestrongcdn.com/ls-article-image-640/cme/photography.prod.demandstudios.com/82f20dde-d703-4e73-85ef-0c67938f604c.gif");
            WorkoutExcercise planche = new WorkoutExcercise("side plank","100","https://img.aws.livestrongcdn.com/ls-article-image-640/cme/photography.prod.demandstudios.com/659e2796-c353-4428-b2fa-421ed4062b20.gif");
            listData.add(plank);
            listData.add(pushup);
            listData.add(planche);

            listView.setAdapter(new CustomListAdapter(this,listData));
        }
        if(extras.getString("level").equals("inter")) {

            listData=new ArrayList<WorkoutExcercise>();
            WorkoutExcercise plank = new WorkoutExcercise("diamond","20","http://assets.menshealth.co.uk/main/assets/how-to-do-the-diamond-press-up.gif?mtime=1453475645");
            WorkoutExcercise pushup = new WorkoutExcercise("decline push up", "30","https://d39ziaow49lrgk.cloudfront.net/wp-content/uploads/2015/07/Push_Up_8.gif");
            WorkoutExcercise planche = new WorkoutExcercise("modified push up","100","https://d39ziaow49lrgk.cloudfront.net/wp-content/uploads/2015/07/Push_Up_11.gif");
            listData.add(plank);
            listData.add(pushup);
            listData.add(planche);

            listView.setAdapter(new CustomListAdapter(this,listData));
        }
        if(extras.getString("level").equals("master")) {

            listData=new ArrayList<WorkoutExcercise>();
            WorkoutExcercise plank = new WorkoutExcercise("squat","20","https://www.shape.com/sites/shape.com/files/styles/slide/public/poppin-plyo-jumps-1-.gif");
            WorkoutExcercise pushup = new WorkoutExcercise("squat jump", "30","https://media.giphy.com/media/gkbrkEbG4jra0/giphy.gif");
            WorkoutExcercise planche = new WorkoutExcercise("pistol squat","100","https://i.pinimg.com/originals/b1/4f/ae/b14fae2edbbc3bef318c280b9a134bb9.gif");
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
}
