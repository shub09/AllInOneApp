package com.example.user.allinoneapp.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.allinoneapp.Model.ContactsModel;
import com.example.user.allinoneapp.R;

import java.util.List;

/**
 * Created by user on 2/25/2017.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.MyViewHolder> {

    private List<ContactsModel> contactList;
    private Context context;
    private View itemView;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView firstName, firstAlpha;

        public MyViewHolder(View view) {
            super(view);
            firstName = (TextView) view.findViewById(R.id.first_name);
            firstAlpha = (TextView) view.findViewById(R.id.first_alpha);
        }
    }


    public ContactsAdapter(Context context, List<ContactsModel> contactList) {
        this.contactList = contactList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_contacts, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final ContactsModel contactsModel = contactList.get(position);
        holder.firstName.setText(contactsModel.getFirstName());
        holder.firstAlpha.setText(contactsModel.getFirstAlpha());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context,contactsModel.getFirstName(), Toast.LENGTH_SHORT).show();
                Log.e("abc",contactsModel.getFirstName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }



}