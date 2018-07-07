package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;


import java.util.ArrayList;
import java.util.List;

public class BodyFragment extends Fragment {
    private static final String LOG_TAG="BodyFragment";

    private static final String IMAGE_ID_LIST="imageId";
    private static final String LIST_INDEX="listIndex";
    private List<Integer> mImageIds;
    private int listIndex;

    public BodyFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_body_part,container,false);

        final ImageView body_image=(ImageView) rootView.findViewById(R.id.body_imageView);
        if(savedInstanceState!=null){
            mImageIds=savedInstanceState.getIntegerArrayList(IMAGE_ID_LIST);
            listIndex=savedInstanceState.getInt(LIST_INDEX);
        }

        if(mImageIds!=null) {
            body_image.setImageResource(mImageIds.get(listIndex));

            body_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listIndex < mImageIds.size()-1)
                        listIndex++;
                    else
                        listIndex = 0;
                    body_image.setImageResource(mImageIds.get(listIndex));
                }
            });

        }
        else
            Log.i(LOG_TAG,"Image Id not set");
        return rootView;
    }

    public void setmImageIds(List<Integer> mImageIds) {
        this.mImageIds = mImageIds;
    }

    public void setListIndex(int listIndex) {
        this.listIndex = listIndex;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putIntegerArrayList(IMAGE_ID_LIST, (ArrayList<Integer>)mImageIds);
        outState.putInt(LIST_INDEX,listIndex);
    }
}
