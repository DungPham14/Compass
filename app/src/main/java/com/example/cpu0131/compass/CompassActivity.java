package com.example.cpu0131.compass;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.util.Log;
import android.widget.TextView;


public class CompassActivity extends Activity implements SensorEventListener  {


    private static final String TAG = "CompassActivity";
    private Compass compass;
    private TextView accelerometer_X, accelerometer_Y, accelerometer_Z;
    private Sensor My_Sensor;
    private SensorManager SM;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {

        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_compass );

        // tạo đối tượng compass
        compass = new Compass( this) ;
        compass.arrowView = ( ImageView ) findViewById( R.id.main_image_hands );

        // tạo trình quản lý cảm biến
        SM = ( SensorManager ) getSystemService( SENSOR_SERVICE );

        // tạo cảm biến ACCELEROMETER
        My_Sensor = SM.getDefaultSensor( Sensor.TYPE_ACCELEROMETER );

        // lắng nghe cảm biến
        SM.registerListener( this, My_Sensor, SensorManager.SENSOR_DELAY_NORMAL );

        //cài đặt text view
        accelerometer_X = ( TextView ) findViewById ( R.id.X );
        accelerometer_Y = ( TextView ) findViewById ( R.id.Y );
        accelerometer_Z = ( TextView ) findViewById ( R.id.Z );

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d( TAG, "start compass" );
        compass.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        compass.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        compass.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d( TAG, "stop compass" );
        compass.stop();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        accelerometer_X.setText( "x: " + sensorEvent.values[0] );

        accelerometer_Y.setText( "y: " + sensorEvent.values[1] );

        accelerometer_Z.setText( "z: " + sensorEvent.values[2] );

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {


    }

}
