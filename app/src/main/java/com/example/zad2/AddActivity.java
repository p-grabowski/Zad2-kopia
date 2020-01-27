package com.example.zad2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import static android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION;
import static android.content.Intent.FLAG_GRANT_WRITE_URI_PERMISSION;


public class AddActivity extends AppCompatActivity {

    EditText tittle, subtittle;
    Button photo, add;
    private static int RESULT_LOAD_IMAGE = 1;
    TextView adress;
    String photoPath = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        tittle = findViewById(R.id.add_tittle);
        subtittle = findViewById(R.id.add_subtittle);
        photo = findViewById(R.id.add_select);
        add = findViewById(R.id.add_selected_photo);
        adress = findViewById(R.id.adres);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BazaObrazkow.tittle.add(tittle.getText().toString());
                BazaObrazkow.subtittle.add(subtittle.getText().toString());
                BazaObrazkow.path.add(photoPath);
                Toast.makeText(AddActivity.this, "Dodany", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });


        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
                adress.setText(photoPath);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            BazaObrazkow.uri.add(selectedImage);


            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            adress.setText(cursor.getString(columnIndex));
            photoPath = cursor.getString(columnIndex);


            cursor.close();
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
