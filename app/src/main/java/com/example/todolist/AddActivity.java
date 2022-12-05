package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaActionSound;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddActivity extends AppCompatActivity {

    EditText ed_title;
    EditText ed_description;
    Button bt_save;
    ImageView img_Work;
    final int REQUEST_CODE_CAMERA=1;
    final int REQUEST_CODE_GALLERY = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ed_title=findViewById(R.id.ed_title);
        ed_description=findViewById(R.id.ed_description);
        bt_save=findViewById(R.id.bt_save);
        img_Work=findViewById(R.id.img_work);
        addListener();
    }



    private void addListener(){
        img_Work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(AddActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    // Request permission
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_CAMERA);
                } else {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CODE_CAMERA);
                }
            }
        });
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title= ed_title.getText().toString();
                String description=ed_description.getText().toString();
                
                if(title.isEmpty()){
                    Toast.makeText(AddActivity.this,"Please enter a title",Toast.LENGTH_SHORT).show();
                    return;
                }else if(description.isEmpty()){
                    Toast.makeText(AddActivity.this,"Please enter a description",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
                    Toast.makeText(AddActivity.this,"add work success",Toast.LENGTH_SHORT).show();
                    Work work=new Work(img,dateFormat.format(new Date()),title,description);
                    WorkUtils.getInstance().addWork(work);
                }
                finish();
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        int result = grantResults[0];
        if (result != PackageManager.PERMISSION_GRANTED) return;
        Intent intent;
        switch (requestCode) {
            case REQUEST_CODE_CAMERA:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CODE_CAMERA);
                break;
            default:
                intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != RESULT_OK && data == null) return;
        switch (requestCode) {
            case REQUEST_CODE_CAMERA:
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                img_Work.setImageBitmap(bitmap);
                break;
            default:
                try {
                    Uri imageUri = data.getData();
                    InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    img_Work.setImageBitmap(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}