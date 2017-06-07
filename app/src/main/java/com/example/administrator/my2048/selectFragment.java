package com.example.administrator.my2048;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.administrator.my2048.MainActivity.bundle;
import static com.example.administrator.my2048.MainActivity.fm;
import static com.example.administrator.my2048.MainActivity.fragment;
import static com.example.administrator.my2048.moveHandle.restartGame;


public class selectFragment extends Fragment {


    public selectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.select_item,container,false);
        Button restart = (Button) v.findViewById(R.id.restart);
        Button quit = (Button) v.findViewById(R.id.quit);
        TextView textView = (TextView) v.findViewById(R.id.text);

        textView.setText(bundle.getString("text"));

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction()
                        .remove(fragment)
                        .commit();
                fragment = null;
                restartGame(getContext());
            }
        });

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        return v;
    }
}
