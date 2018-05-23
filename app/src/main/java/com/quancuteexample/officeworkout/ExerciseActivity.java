package com.quancuteexample.officeworkout;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

public class ExerciseActivity extends AppCompatActivity {
    private TextView nameText;
    private TextView repsText;
    private GifImageView gifImage;

    private String name;
    private String gifName;
    private String reps;
    private Button nextButton;
    private Context context;
    private int position;
    private ArrayList<WorkoutExcercise> result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        context=getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        Intent intent=getIntent();
        result = intent.getParcelableArrayListExtra("list");
        gifName=intent.getStringExtra("gif");
        name=intent.getStringExtra("name");
        reps=intent.getStringExtra("reps");
        position=intent.getIntExtra("position",-1);
        nameText=findViewById(R.id.textView7);
        repsText=findViewById(R.id.textView8);
        gifImage=findViewById(R.id.gifImageView);

        Glide.with(context)
                .load(gifName)
                .thumbnail()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(gifImage);


        nameText.setText(name);
        repsText.setText("reps: "+reps);
        String pkgName = context.getPackageName();
//        int resID = context.getResources().getIdentifier(gifName , "mipmap", pkgName);
//        Log.i("CustomListView", "Res Name: "+ gifName+"==> Res ID = "+ resID);
//        gifImage.setImageResource(resID);

        nextButton=findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                position=position+1;
                if(position+1 > result.size()){
                    position=0;
                }
                nameText.setText(result.get(position).getName());
                repsText.setText("reps: "+result.get(position).getRep());
                String pkgName = context.getPackageName();
//                int resID = context.getResources().getIdentifier(result.get(position).getGifName(), "mipmap", pkgName);
//                Log.i("CustomListView", "Res Name: "+ result.get(position).getGifName()+"==> Res ID = "+ resID);
//                gifImage.setImageResource(resID);
                Glide.with(context)
                        .load(result.get(position).getGifName())
                        .into(gifImage);


            }
        });
    }
}
