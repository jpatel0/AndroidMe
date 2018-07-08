package com.example.android.android_me.ui;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener{

    private int headIndex;
    private int bodyIndex;
    private int legIndex;

    private boolean mTwoPane=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.android_me_linear_layout)!=null) {
            mTwoPane = true;
            Log.i("mtwopane", String.valueOf(mTwoPane));
            if (savedInstanceState == null) {
                BodyFragment headFragment = new BodyFragment();

                headFragment.setmImageIds(AndroidImageAssets.getHeads());
                headFragment.setListIndex(headIndex);

                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .add(R.id.head_frag, headFragment)
                        .commit();

                BodyFragment bodyFragment = new BodyFragment();

                bodyFragment.setmImageIds(AndroidImageAssets.getBodies());
                bodyFragment.setListIndex(bodyIndex);

                BodyFragment legFragment = new BodyFragment();

                legFragment.setmImageIds(AndroidImageAssets.getLegs());
                legFragment.setListIndex(legIndex);

                fragmentManager.beginTransaction()
                        .add(R.id.body_frag, bodyFragment).commit();

                fragmentManager.beginTransaction().add(R.id.leg_frag, legFragment).commit();
            }
        }

    }

    @Override
    public void onImageSelected(int position) {
        int bodyPartNumber=position/12;
        int listIndex=position-12*bodyPartNumber;

        if(mTwoPane){
            Button button = (Button) findViewById(R.id.next);
            button.setVisibility(View.GONE);

            GridView gridView=(GridView) findViewById(R.id.master_list_frag);
            gridView.setNumColumns(2);

            BodyFragment newFragment=new BodyFragment();

            switch (bodyPartNumber) {
                case 0:
                    newFragment.setmImageIds(AndroidImageAssets.getHeads());
                    newFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.head_frag,newFragment)
                            .commit();

                    break;
                case 1:
                    newFragment.setmImageIds(AndroidImageAssets.getBodies());
                    newFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.body_frag,newFragment)
                            .commit();
                    break;
                case 2:
                    newFragment.setmImageIds(AndroidImageAssets.getLegs());
                    newFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.leg_frag,newFragment)
                            .commit();
                    break;
            }


        }else {
            switch (bodyPartNumber) {
                case 0:
                    headIndex = listIndex;
                    break;
                case 1:
                    bodyIndex = listIndex;
                    break;
                case 2:
                    legIndex = listIndex;
                    break;
            }

            Bundle b = new Bundle();
            b.putInt("headIndex", headIndex);
            b.putInt("bodyIndex", bodyIndex);
            b.putInt("legIndex", legIndex);

            final Intent intent = new Intent(this, AndroidMeActivity.class);
            intent.putExtras(b);

            Button button = (Button) findViewById(R.id.next);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(intent);
                }
            });
        }
    }
}
