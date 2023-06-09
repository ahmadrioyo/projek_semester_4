package com.example.amplangan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class tentangkami extends AppCompatActivity {
    ImageView backtk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentangkami);

        backtk = (ImageView) findViewById(R.id.backtk);
        backtk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resgisterIntent = new Intent(tentangkami.this, home.class);
                startActivity(resgisterIntent);
                overridePendingTransition(0,0);
                finish();
            }
        });
    }
}