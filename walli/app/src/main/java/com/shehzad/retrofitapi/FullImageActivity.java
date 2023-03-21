package com.shehzad.retrofitapi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import java.io.IOException;

public class FullImageActivity extends AppCompatActivity {
    WallpaperManager wallpaperManager;
    private ImageView fullimage;
    private Button apply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);

        fullimage = findViewById(R.id.fullimage);
        apply = findViewById(R.id.apply);

        wallpaperManager = WallpaperManager.getInstance(getApplicationContext());

        Glide.with(this).load(getIntent().getStringExtra("image")).into(fullimage);

        apply.setOnClickListener(view -> {
            setBackground();
            Toast.makeText(getApplicationContext(), "Wallpaper Applied", Toast.LENGTH_SHORT).show();
        });
    }

    //setting wallpaper to an phone
    private void setBackground() {
        Bitmap bitmap = ((BitmapDrawable) fullimage.getDrawable()).getBitmap();
        WallpaperManager manager = WallpaperManager.getInstance(getApplicationContext());
        try {
            manager.setBitmap(bitmap);
        } catch (IOException e) {
            Toast.makeText(this, "Error : " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
