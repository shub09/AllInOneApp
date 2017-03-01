package com.example.user.allinoneapp.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.user.allinoneapp.Fragments.Fragment1;
import com.example.user.allinoneapp.Fragments.Fragment2;
import com.example.user.allinoneapp.R;

/**
 * Created by user on 2/16/2017.
 */

public class FragmentModule extends AppCompatActivity implements View.OnClickListener{

    Button bFragment1, bFragment2;
    FrameLayout container;
    FragmentManager fm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_module);

        bFragment1 = (Button) findViewById(R.id.btn_fragment1);
        bFragment2 = (Button) findViewById(R.id.btn_fragment2);
        container = (FrameLayout)findViewById(R.id.container);

        fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.container, new Fragment1(), "frag1").commit();

        /**
         * Instead of adding onCLicklistener indivisualy,
         * we can implement the interface which can be used to handle all the click events.
         */

        /*bFragment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

        bFragment1.setOnClickListener(this);
        bFragment2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_fragment1:
                fm.beginTransaction().add(R.id.container, new Fragment1(), "frag1").addToBackStack(null).commit();
                break;

            case R.id.btn_fragment2:
                fm.beginTransaction().add(R.id.container, new Fragment2(), "frag2").addToBackStack("frag1").commit();
                break;
        }
    }
}
