package com.example.administrator.kongzhiqi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button ykong = (Button) findViewById(R.id.yaoKong);
        Button szhi = (Button) findViewById(R.id.sheZhi);
        Button ss = (Button) findViewById(R.id.shiShi);
        ykong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Yaokong.class);
                startActivity(intent);
            }
        });

        szhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Shezhi.class);
                startActivity(intent);
            }
        });
        ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Yaokong2.class);
                startActivity(intent);
            }
        });



    }
}
