package com.example.user.allinoneapp.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.allinoneapp.Adapter.ModuleListAdapter;
import com.example.user.allinoneapp.Model.DataModel;
import com.example.user.allinoneapp.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lstModule;
    ModuleListAdapter moduleListAdapter;
    List<DataModel> arrayModule;
    private boolean doubleBackToExitPressedOnce = false;
    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        arrayModule = new ArrayList<>();
        arrayModule.add(new DataModel("SharedPrefrences", R.drawable.img1, ""));
        arrayModule.add(new DataModel("FragmentModule", R.drawable.img2, ""));
        arrayModule.add(new DataModel("ViewPager", R.drawable.img3, ""));
        arrayModule.add(new DataModel("Navigation Drawer", R.drawable.img4, ""));
        arrayModule.add(new DataModel("Module 5", R.drawable.img5, ""));

        moduleListAdapter = new ModuleListAdapter(arrayModule, this);
        lstModule.setAdapter(moduleListAdapter);

        //refreshList();
    }

    private void init(){
        lstModule = (ListView) findViewById(R.id.moduleList);
        refreshLayout= (SwipeRefreshLayout) findViewById(R.id.refresh_layout);

        lstModule.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position){
                    case 0:
                        DataModel dataModel = (DataModel) lstModule.getItemAtPosition(position);
                        Intent intent=new Intent(MainActivity.this,ShardPreferencesModule.class);
                        Bundle bundle=new Bundle();
                        bundle.putString("name",dataModel.getName());
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;

                    case 1:
                        startActivity(new Intent(MainActivity.this,FragmentModule.class));
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_left);
                        break;

                    case 2:
                        startActivity(new Intent(MainActivity.this,ViewPagerModule.class));
                        break;

                    case 3:
                        startActivity(new Intent(MainActivity.this,NavigationDrawerModule.class));
                        break;
                }
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshList();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    private void refreshList(){
        arrayModule.add(new DataModel("Test", R.drawable.img1, ""));
       // arrayModule.add(0,new DataModel("Test", R.drawable.img1));
        moduleListAdapter.notifyDataSetChanged();
        refreshLayout.setRefreshing(false);
    }
}
