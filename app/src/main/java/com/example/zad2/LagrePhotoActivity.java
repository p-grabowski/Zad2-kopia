package com.example.zad2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class LagrePhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lagre_photo);

        int id = getIntent().getIntExtra("idPhoto", 0);

        ImageView image = findViewById(R.id.photo);
        TextView text = findViewById(R.id.text);
        image.setImageURI(BazaObrazkow.uri.get(id));
        text.setText(BazaObrazkow.tittle.get(id)+"  "+BazaObrazkow.subtittle.get(id));
    }
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
