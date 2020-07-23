package com.vsp.teamworksinternshipdemoapp;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.app.Activity.RESULT_OK;

public class Tab2 extends Fragment {
    ImageView imageView;
    Button gallerybtn,selectanotherimg;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.tab2, container, false);

        gallerybtn = view.findViewById(R.id.galleryid);
//        selectanotherimg = view.findViewById(R.id.selectanotherid);
        imageView = view.findViewById(R.id.imageid);


        gallerybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");


                startActivityForResult(Intent.createChooser(intent, "Select image"), 1);


            }
        });
        /*selectanotherimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");


                startActivityForResult(Intent.createChooser(intent, "Select image"), 1);

            }
        });*/

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), imageUri);
                imageView.setImageBitmap(bitmap);
                gallerybtn.setVisibility(View.GONE);

            } catch (IOException e) {
                e.printStackTrace();
                toast("exception cause");
            }
           /* ClipData clipData;
            clipData = data.getClipData();
            File file = new File(clipData.toString());

            imageView.setImageResource(file.);*/
        }

    }
    public void toast(String x) {
        Toast.makeText(getActivity(), x, Toast.LENGTH_SHORT).show();
    }

}
