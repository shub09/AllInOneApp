package com.example.user.allinoneapp.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.user.allinoneapp.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import static android.app.Activity.RESULT_OK;

/**
 * Created by user on 2/24/2017.
 */

public class CameraFragment extends Fragment {

    Button btnCamera, btnGallery, btnShare;
    ImageView imvImage;
    AppCompatActivity mActivity;

    int REQUEST_CAMERA = 1;
    int REQUEST_EXTERNAL_STORAGE = 2;
    int LOAD_IMAGE = 3;

    Bitmap bitmap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.fragment_camera, container, false);

        btnCamera = (Button) mView.findViewById(R.id.btn_camera);
        btnGallery = (Button) mView.findViewById(R.id.btn_gallery);
        btnShare = (Button) mView.findViewById(R.id.btn_share);
        imvImage = (ImageView) mView.findViewById(R.id.imv);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(mActivity,
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(mActivity,
                            new String[]{Manifest.permission.CAMERA},
                            REQUEST_CAMERA);

                }else{
                    Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                }
            }
        });

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((ContextCompat.checkSelfPermission(mActivity,
                        Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)){

                    ActivityCompat.requestPermissions(mActivity,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_EXTERNAL_STORAGE);
                }else{
                    Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, LOAD_IMAGE);
                }
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((ContextCompat.checkSelfPermission(mActivity,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
                && (ContextCompat.checkSelfPermission(mActivity,
                        Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)){

                    ActivityCompat.requestPermissions(mActivity,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUEST_EXTERNAL_STORAGE);

                }else{
                    if(bitmap != null){
                        shareImage(bitmap);
                    }
                }
            }
        });
        return mView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK && null != data) {
            if (requestCode == REQUEST_CAMERA) {
                bitmap = (Bitmap) data.getExtras().get("data");
                imvImage.setImageBitmap(bitmap);

            } else if (requestCode == LOAD_IMAGE) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                Cursor cursor = mActivity.getContentResolver().query(selectedImage,filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();

                bitmap = BitmapFactory.decodeFile(picturePath);
                imvImage.setImageBitmap(bitmap);
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity= (AppCompatActivity) getActivity();
    }

    public void shareImage (Bitmap bitmap) {

        try {
            final File dir = new File(Environment.getExternalStorageDirectory(), "Test");

            if (!dir.exists()) {
                dir.mkdirs();
            }

            final File img = new File(dir, "test" + ".jpeg");
            if (img.exists()) {
                img.delete();
            }
            final OutputStream outStream = new FileOutputStream(img);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            //outStream.flush();
            outStream.close();

            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("image/jpeg");
            share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(img));
            startActivity(Intent.createChooser(share,"Share via"));

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Something Went Wrong, Please Try Again!2", Toast.LENGTH_SHORT).show();
        }
    }
}
