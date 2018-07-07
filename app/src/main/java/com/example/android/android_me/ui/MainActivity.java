package com.example.android.android_me.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.android_me.R;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener{

    private int headIndex;
    private int bodyIndex;
    private int legIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onImageSelected(int position) {
        int bodyPartNumber=position/12;

        switch (bodyPartNumber){
            case 0:
                headIndex=position-12*bodyPartNumber;
                break;
            case 1:
                bodyIndex=position-12*bodyPartNumber;
                break;
            case 2:
                legIndex=position-12*bodyPartNumber;
                break;
        }

        Bundle b=new Bundle();
        b.putInt("headIndex",headIndex);
        b.putInt("bodyIndex",bodyIndex);
        b.putInt("legIndex",legIndex);

        final Intent intent=new Intent(this,AndroidMeActivity.class);
        intent.putExtras(b);

        Button button=(Button) findViewById(R.id.next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

    }
}
