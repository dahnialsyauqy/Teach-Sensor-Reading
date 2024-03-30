package com.example.teachsensorreading;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PositionActivity extends AppCompatActivity implements SensorEventListener {
private SensorManager mysensormanager;
private Sensor myproximity;
private TextView mytextview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_position);

        mysensormanager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        myproximity=mysensormanager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        mytextview=(TextView)findViewById(R.id.textView3);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
float distance = event.values[0];
mytextview.setText(Float.toString(distance));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume(){
        super.onResume();
        mysensormanager.registerListener(PositionActivity.this,myproximity,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause(){
        super.onPause();
        mysensormanager.unregisterListener(PositionActivity.this);
    }
}