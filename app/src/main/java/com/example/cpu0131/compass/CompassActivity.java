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
    private TextView geomagnetic_X, geomagnetic_Y, geomagnetic_Z;
    private Sensor My_Sensor, My_Sensor1;
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


        // tạo cảm biến ACCELEROMETER and GEOMAGNETIC
        My_Sensor1 = SM.getDefaultSensor( Sensor.TYPE_ACCELEROMETER );
        My_Sensor = SM.getDefaultSensor( Sensor.TYPE_MAGNETIC_FIELD );


        // lắng nghe cảm biến
        SM.registerListener( this, My_Sensor, SensorManager.SENSOR_DELAY_NORMAL );
        SM.registerListener( this, My_Sensor1, SensorManager.SENSOR_DELAY_NORMAL );


        //cài đặt text view in ra gia tốc
        accelerometer_X = ( TextView ) findViewById ( R.id.X );
        accelerometer_Y = ( TextView ) findViewById ( R.id.Y );
        accelerometer_Z = ( TextView ) findViewById ( R.id.Z );


        //cài đặt text view in ra giá trị từ trường
        geomagnetic_X = ( TextView ) findViewById ( R.id._X );
        geomagnetic_Y = ( TextView ) findViewById ( R.id._Y );
        geomagnetic_Z = ( TextView ) findViewById ( R.id._Z );
        
        
        readFile();

    }

    private void readFile() {

    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if ( sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER ) {

            accelerometer_X.setText( getString(R.string.x) + ": " + sensorEvent.values[0] );

            accelerometer_Y.setText( getString(R.string.y) + ": " + sensorEvent.values[1] );

            accelerometer_Z.setText( getString(R.string.z) + ": " + sensorEvent.values[2] );

        }

        if ( sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD ) {

            geomagnetic_X.setText( getString(R.string.x) + ": " + sensorEvent.values[0] );

            geomagnetic_Y.setText( getString(R.string.y) + ": " + sensorEvent.values[1] );

            geomagnetic_Z.setText( getString(R.string.z) + ": " + sensorEvent.values[2] );

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {


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

}
