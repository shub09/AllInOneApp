package com.example.user.allinoneapp.Fragments;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.user.allinoneapp.Adapter.ContactsAdapter;
import com.example.user.allinoneapp.Model.ContactsModel;
import com.example.user.allinoneapp.R;
import com.example.user.allinoneapp.interfaces.CustomOnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2/25/2017.
 */

public class ContactsFragment extends Fragment {

    AppCompatActivity mActivity;
    RecyclerView contactsRecyclerView;
    List<ContactsModel> contactsList;
    ContactsAdapter contactsAdapter;
    ProgressDialog p;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_contacts, container, false);

        contactsRecyclerView= (RecyclerView) mView.findViewById(R.id.contacts_list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mActivity);
        contactsRecyclerView.setLayoutManager(mLayoutManager);

        p = new ProgressDialog(mActivity);
        p.setMessage("Fetching Contacts");

        if (ContextCompat.checkSelfPermission(mActivity,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(mActivity,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    0);
        }else{
            new fetchContactTask().execute();
        }
        return  mView;
    }

    public void fetchContacts() {
        String phoneNumber = null;

        Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
        String _ID = ContactsContract.Contacts._ID;
        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;

        Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

        ContentResolver contentResolver = mActivity.getContentResolver();
        Cursor cursor = contentResolver.query(CONTENT_URI, null, null, null, null);

        contactsList = new ArrayList<>();

        // Loop for every contact in the phone
        if (cursor.getCount() > 0) {

            while (cursor.moveToNext()) {

                String contact_id = cursor.getString(cursor.getColumnIndex(_ID));
                String name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));

                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)));

                if (hasPhoneNumber > 0) {

                    ContactsModel contactsModel = new ContactsModel();
                    contactsModel.setFirstName(name);

                    // Query and loop for every phone number of the contact
                    Cursor phoneCursor = contentResolver.query(PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[]{contact_id}, null);

                    List<String> phoneList = new ArrayList<>();
                    while (phoneCursor.moveToNext()) {
                        phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
                        phoneList.add(phoneNumber);
                    }
                    contactsModel.setPhoneNumber(phoneList);
                    contactsList.add(contactsModel);

                    phoneCursor.close();
                }
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (AppCompatActivity) getActivity();
    }

    private class fetchContactTask extends AsyncTask<String, String, String> {
        private String resp;

        @Override
        protected void onPreExecute() {
            p.show();
        }

        @Override
        protected String doInBackground(String... params) {
            fetchContacts();
            return resp;
        }

        @Override
        protected void onPostExecute(String s) {
            p.cancel();
            contactsAdapter = new ContactsAdapter(mActivity, contactsList);
            contactsRecyclerView.setAdapter(contactsAdapter);
        }
    }
}
