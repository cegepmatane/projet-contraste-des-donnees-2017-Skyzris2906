package ca.qc.cgmatane.informatique.capteurvaleur;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ca.qc.cgmatane.informatique.capteurvaleur.Donnees.BaseDeDonnees;

public class VueCapteur extends AppCompatActivity implements SensorEventListener {
    SensorManager mSensorManager = null;
    Sensor mAccelerometer = null;
    TextView valeur;
    public static final int MY_PERMISSIONS_REQUEST_STORAGE = 999;
    BaseDeDonnees accesseurBaseDeDonnees;
    boolean write = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_capteur);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        checkStoragePermission();

        valeur = (TextView)findViewById(R.id.valeur_capteur);

        accesseurBaseDeDonnees = BaseDeDonnees.getInstance(getApplicationContext());
        Log.d("INSTANCE", "Bdd = " + accesseurBaseDeDonnees);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        final String strDate = dateFormat.format(calendar.getTime());

        final String strTemps = calendar.getTime().getHours() + ":" + calendar.getTime().getMinutes();

        final float x = sensorEvent.values[0];
        final float y = sensorEvent.values[1];
        final float z = sensorEvent.values[2];

        valeur.setText(" x: " + x + " y: " + y + " z: " + z + " date: " + strDate + " heure: " + strTemps + " seconds: " + calendar.getTime().getSeconds());

        if(calendar.getTime().getSeconds() == 0 && (calendar.getTime().getMinutes() == 30 || calendar.getTime().getMinutes() == 0))
        {
            if(!write)
            {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String AJOUTER_LA_VALEUR = "INSERT INTO accelerometre(x, y, z, " +
                                "date, heure) VALUES('" + x +
                                "', '" + y + "', " +
                                "'" + z + "', " +
                                "'" + strDate + "', " +
                                "'" + strTemps + "')";

                        Log.d("INSERT", AJOUTER_LA_VALEUR);

                        accesseurBaseDeDonnees.getWritableDatabase().execSQL(AJOUTER_LA_VALEUR);

                        Toast.makeText(VueCapteur.this, "Valeur ajouté à la BDD", Toast.LENGTH_LONG).show();
                        write = true;
                    }
                }, 1);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private void checkStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE))
            {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("Storage Permission Needed")
                        .setMessage("This app needs the Storage permission, please accept to use storage functionality")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(VueCapteur.this,
                                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                        MY_PERMISSIONS_REQUEST_STORAGE );
                            }
                        })
                        .create()
                        .show();
            }
            else
            {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_STORAGE );
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_GRANTED) {
                        //add stuff
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
