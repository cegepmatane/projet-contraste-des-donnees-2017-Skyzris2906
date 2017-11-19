package informatique.cgmatane.qc.cq.databasestats.vues;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import informatique.cgmatane.qc.cq.databasestats.R;

public class MainActivity extends AppCompatActivity {

    protected Button boutonTemperatures;
    protected Button boutonAccelerometre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        boutonTemperatures = (Button)findViewById(R.id.bouton_temperatures_main_activity);
        boutonAccelerometre = (Button)findViewById(R.id.bouton_accelerometre_main_activity);

        boutonTemperatures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(MainActivity.this, VueTemperatures.class);
                    startActivity(intent);
                }catch (Exception ex){
                    Log.d("APPERROR",ex.getMessage());
                }
            }
        });

        boutonAccelerometre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,VueAccelerometre.class);
                startActivity(intent);
            }
        });

    }

}
