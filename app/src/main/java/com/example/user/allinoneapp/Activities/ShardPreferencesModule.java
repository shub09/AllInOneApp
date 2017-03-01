package com.example.user.allinoneapp.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.allinoneapp.R;
import com.example.user.allinoneapp.Utility.Constants;
import com.example.user.allinoneapp.Utility.MySharedPreferences;

/**
 * Created by user on 2/9/2017.
 */

public class ShardPreferencesModule extends AppCompatActivity
{
    TextView txt;
    EditText edtUserName, edtPhoneNo;
    Button btnSumit;
    MySharedPreferences mySharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharedpreferences_module);

        mySharedPreferences = new MySharedPreferences(this);

        txt=(TextView) findViewById(R.id.textView);
        edtUserName = (EditText) findViewById(R.id.edt_username);
        edtPhoneNo = (EditText) findViewById(R.id.edt_phone);
        btnSumit = (Button) findViewById(R.id.btn_submit);

        Bundle b = getIntent().getExtras();
        txt.setText(b.getString("name"));

        btnSumit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySharedPreferences.putString(Constants.KEY_USERNAME, edtUserName.getText().toString());
                mySharedPreferences.putString(Constants.KEY_PHONE_NO, edtPhoneNo.getText().toString());

                edtUserName.setText("");
                edtPhoneNo.setText("");
                getPrefValues();
            }
        });
    }

    public void getPrefValues(){
        Toast.makeText(this, mySharedPreferences.getString(Constants.KEY_USERNAME)
                + "  "+ mySharedPreferences.getString(Constants.KEY_PHONE_NO), Toast.LENGTH_SHORT).show();
    }
}
