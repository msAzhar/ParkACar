package com.acer.parkacar2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static Button btn_sbm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Reservation();
    }

    public void Reservation(){
        btn_sbm = (Button)findViewById(R.id.button);
        btn_sbm.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent = new Intent("android.intent.action.activity_choice");
                        startActivity(intent);
                    }
                }
        );
    }
}
