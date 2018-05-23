package com.quancuteexample.officeworkout;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        Button nextButton=findViewById(R.id.button);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value="boy";
                Intent intent=new Intent(DialogActivity.this,Level.class);
                intent.putExtra("sex",value);
                startActivity(intent);

            }
        });

    }
}
