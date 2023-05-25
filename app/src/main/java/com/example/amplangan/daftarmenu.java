package com.example.amplangan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class daftarmenu extends AppCompatActivity {
    ImageView backmn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentangkami);

        backmn = (ImageView) findViewById(R.id.backmn);
        backmn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resgisterIntent = new Intent(daftarmenu.this, home.class);
                startActivity(resgisterIntent);
                overridePendingTransition(0,0);
            }
        });
    }
}