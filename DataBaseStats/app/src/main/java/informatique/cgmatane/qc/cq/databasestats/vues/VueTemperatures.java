package informatique.cgmatane.qc.cq.databasestats.vues;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.List;

import informatique.cgmatane.qc.cq.databasestats.R;
import informatique.cgmatane.qc.cq.databasestats.donnees.TemperaturesDAO;

public class VueTemperatures extends AppCompatActivity {

    protected ListView vueListeTemperatures;
    protected List<HashMap<String,String>> listeTemperatures;
    protected TemperaturesDAO temperaturesDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_temperatures);

        vueListeTemperatures = (ListView)findViewById(R.id.liste_temperatures_vue_temperatures);

        temperaturesDAO = new TemperaturesDAO();

        afficherLesTemperatures();
    }

    protected void afficherLesTemperatures(){

        listeTemperatures = temperaturesDAO.listerLesTemperaturesEnHashMap();

        SimpleAdapter adapteurVueListeTemperatures = new SimpleAdapter(
                this,
                listeTemperatures,
                android.R.layout.two_line_list_item,
                new  String[]{"temperature","date"},
                new int[]{android.R.id.text1, android.R.id.text2}
        );

        vueListeTemperatures.setAdapter(adapteurVueListeTemperatures);
    }
}
