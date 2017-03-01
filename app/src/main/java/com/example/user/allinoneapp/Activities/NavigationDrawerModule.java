package com.example.user.allinoneapp.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.user.allinoneapp.Adapter.ModuleListAdapter;
import com.example.user.allinoneapp.Fragments.CameraFragment;
import com.example.user.allinoneapp.Fragments.ContactsFragment;
import com.example.user.allinoneapp.Model.DataModel;
import com.example.user.allinoneapp.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by user on 2/22/2017.
 */

public class NavigationDrawerModule extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout mDrawer;
    CircleImageView circleImageView;
    Spinner spinner;
    ActionBarDrawerToggle drawerToggle;

    ListView drawerList;
    ModuleListAdapter drawerAdaper;
    List<DataModel> drawerItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer_module);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        mDrawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        spinner = (Spinner)findViewById(R.id.spinner);
        drawerList = (ListView) findViewById(R.id.drawer_list);

        setSupportActionBar(toolbar);

        drawerToggle = setupDrawerToggle();
        mDrawer.addDrawerListener(drawerToggle);

        drawerToggle.syncState();

        List<String> spinnerItems = new ArrayList<>();
        spinnerItems.add("Nayan");
        spinnerItems.add("Shubham");
        spinnerItems.add("Rajendra");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, spinnerItems);

        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(NavigationDrawerModule.this, (String)parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        drawerItems = new ArrayList<>();
        drawerItems.add(new DataModel("Camera & Gallery", R.drawable.img1, "1"));
        drawerItems.add(new DataModel("Contacts", R.drawable.img2, "2"));
        drawerItems.add(new DataModel("Date/Time Picker", R.drawable.img4, "3"));

        drawerAdaper = new ModuleListAdapter(drawerItems, this);
        drawerList.setAdapter(drawerAdaper);

        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position){
                    case 0 :
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,new CameraFragment(),"").commit();
                        break;
                    case 1 :
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,new ContactsFragment(),"").commit();
                        break;
                }
                mDrawer.closeDrawer(Gravity.LEFT);

            }
        });
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_open:
                mDrawer.openDrawer(Gravity.LEFT);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
