package com.example.teachsensorreading;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {
private TextView mytextview;
private SensorManager mysensormanager;
private List<Sensor> mylist;
private Button myposbuttton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        mytextview = (TextView)findViewById(R.id.textView);
        mytextview.setVisibility(View.GONE);

        myposbuttton=(Button)findViewById(R.id.button);


        mysensormanager=(SensorManager) getSystemService(SENSOR_SERVICE);
        mylist=mysensormanager.getSensorList(Sensor.TYPE_ALL);

        mytextview.setVisibility(View.VISIBLE);
        for(int i=1;i<mylist.size();i++){
            mytextview.append("\n"+"["+Integer.toString(i)+"] "+mylist.get(i).getName());
        }

        myposbuttton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent myposintent = new Intent(MainActivity.this,PositionActivity.class);
                startActivity(myposintent);
            }

        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}