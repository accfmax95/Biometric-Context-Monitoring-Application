package com.example.Maxwell;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.ArrayList;
import java.util.List;

public class RespiratoryService extends Service implements SensorEventListener {
    private SensorManager accelManage;
    private Sensor senseAccel;
    float XValues[] = new float[450];
    float YValues[] = new float[450];
    float ZValues[] = new float[450];
    int index = 0;
    int respiratoryVal = 0;

    @Override
    public void onCreate() {

        Toast.makeText(getApplicationContext(),"Measuring Respiratory Rate...", Toast.LENGTH_LONG).show();
        accelManage = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senseAccel = accelManage.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        accelManage.registerListener(this, senseAccel, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {

        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        Sensor mySensor = event.sensor;
        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            index++;
            XValues[index] = event.values[0];
            YValues[index] = event.values[1];
            ZValues[index] = event.values[2];

            if (index >= 449) {

                index = 0;
                accelManage.unregisterListener(this);
                Toast.makeText(RespiratoryService.this, "Stopped Accelerometer Recording", Toast.LENGTH_LONG).show();
                callMeasureRespRate();
            }
        }
    }

    private void callMeasureRespRate() {

        int i = 0;
        for (int j = 11; j <= 450; j++) {

            float sum = 0;
            for (int k = i; k < j; k++) {

                sum += YValues[k];
            }

            YValues[i] = sum / 20;
            i++;
        }

        List<Integer> ext = new ArrayList<Integer>();
        for (int k = 0; k < YValues.length - 20; k++) {

            if ((YValues[k + 1] - YValues[k]) * (YValues[k + 2] - YValues[k + 1]) <= 0) {

                ext.add(k + 1);
            }
        }

        respiratoryVal = 0;
        for (int k = 0; k < ext.size() - 1; k++) {

            if ((ext.get(k) / 10) != ext.get(k++)) {

                respiratoryVal++;
            }
        }

        respiratoryVal = respiratoryVal / 2;
        Toast.makeText(RespiratoryService.this, "Respiratory Rate is : " + String.valueOf(respiratoryVal), Toast.LENGTH_LONG).show();
        Intent intent = new Intent ("message");
        intent.putExtra("success", respiratoryVal);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) { }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }
}