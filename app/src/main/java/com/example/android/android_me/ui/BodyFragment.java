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
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.List;

public class BodyFragment extends Fragment {
    private static final String LOG_TAG="BodyFragment";

    private List<Integer> mImageIds;
    private int listIndex;

    public BodyFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_body_part,container,false);

        ImageView body_image=(ImageView) rootView.findViewById(R.id.body_imageView);

        if(mImageIds!=null) {
            body_image.setImageResource(mImageIds.get(listIndex));
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
}
