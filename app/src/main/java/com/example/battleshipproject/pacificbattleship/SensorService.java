package com.example.battleshipproject.pacificbattleship;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.EventListener;



public class SensorService extends Service {


    public interface StormInterface extends EventListener {

        void startStorm();
        void StormIsComing();
        void StopStorm();
    }


    private StormInterface stormInterfaceListener;
    private final IBinder mBinder = new MyBinder();
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private float currentX, currentY, currentZ, originX, originY, originZ;
    private boolean isFirstTime = true;
    private long startTime = 0, diffTime = 0;
    private long currentTime;
    private final double RANGE = 2;
    private final int TIME = 1000;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(SensorL, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mSensorManager.registerListener(SensorL, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
        return Service.START_NOT_STICKY;
    }


    public void setStormInterfaceListener(StormInterface stormInterfaceListener){
        this.stormInterfaceListener = stormInterfaceListener;



    }
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    float[] mGravity = null;
    float[] mGeomagnetic = null;
    int counter = 0;
    boolean pastStartPosition = true;

    private SensorEventListener SensorL = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {

            Thread t = new Thread(() -> {
                if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
                    mGravity = event.values;
                if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
                    mGeomagnetic = event.values;
                if (mGravity != null && mGeomagnetic != null) {
                    float R[] = new float[9];
                    float I[] = new float[9];
                    boolean success = SensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic);
                    if (success) {
                        float orientation[] = new float[3];
                        SensorManager.getOrientation(R, orientation);
                        Log.i("Local Service", "orientation: (" + orientation[0] + ", " + orientation[1] + ", " + orientation[2] + ")");
                        diffTime = 0;

                        if (isFirstTime) {
                            originX = orientation[0];
                            originY = orientation[1];
                            originZ = orientation[2];
                            isFirstTime = false;
                            pastStartPosition = true;
                        }

                        currentX = orientation[0];
                        currentY = orientation[1];
                        currentZ = orientation[2];


                        boolean inStartPosition = Math.abs(originX - currentX)+Math.abs(originY - currentY)+ Math.abs(originZ - currentZ)  < RANGE;



                        Log.d("SERVICE SENSOR", "CHANGED");

                        if (pastStartPosition && !inStartPosition && counter <= 5) {
                            counter++;
                            if (counter == 5) {
                                if (stormInterfaceListener != null) {
                                    stormInterfaceListener.StormIsComing();
                                    counter++;

                                }
                                startTime = System.currentTimeMillis();
                                pastStartPosition = false;
                            }

                        } else if (!pastStartPosition && !inStartPosition && counter == 6) {
                            diffTime = System.currentTimeMillis() - startTime;
                            if (diffTime > TIME) {
                                counter++;
                                Log.d("SERVICE SENSOR", "5 SEC");
                                if (stormInterfaceListener != null) {
                                    stormInterfaceListener.startStorm();
                                }
                            }
                        } else if (!pastStartPosition && inStartPosition && counter >= -5) {
                            counter--;
                            if (counter == -5) {
                                if (stormInterfaceListener != null) {
                                    stormInterfaceListener.StopStorm();
                                }
                                pastStartPosition=true;
                            }
                        }
                    }
                }
            });

            t.start();
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    };


    public class MyBinder extends Binder {
        SensorService getService() {
            return SensorService.this;
        }
    }

    public void setCallbacks(StormInterface callbacks) {
        stormInterfaceListener = callbacks;
    }
}
