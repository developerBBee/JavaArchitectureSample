package jp.developer.bbee.javamvvmdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import jp.developer.bbee.javamvvmdemo.presentation.artist.ArtistActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.startButton).setOnClickListener(v -> {
            startActivity(new Intent(this, ArtistActivity.class));
            finish();
        });
    }
}