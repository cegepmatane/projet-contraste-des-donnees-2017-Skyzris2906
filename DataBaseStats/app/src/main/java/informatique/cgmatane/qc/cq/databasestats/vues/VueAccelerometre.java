package informatique.cgmatane.qc.cq.databasestats.vues;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import informatique.cgmatane.qc.cq.databasestats.R;
import informatique.cgmatane.qc.cq.databasestats.donnees.AccelerometreDAO;
import informatique.cgmatane.qc.cq.databasestats.donnees.TemperaturesDAO;
import informatique.cgmatane.qc.cq.databasestats.modele.Accelerometre;
import informatique.cgmatane.qc.cq.databasestats.modele.Temperature;

public class VueAccelerometre extends AppCompatActivity {

    protected ListView vueListeAccelerometre;
    protected List<HashMap<String,String>> listeAccelerometreEnHashMap;
    protected AccelerometreDAO accelerometreDAO;
    protected List<Accelerometre> listeAccelerometre;

    protected TextView libelleMoyenne;
    protected TextView libelleValeurMax;
    protected TextView libelleValeurMin;
    protected TextView libelleNombreValeurs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_accelerometre);

        vueListeAccelerometre = (ListView)findViewById(R.id.liste_accelerometre_vue_accelerometre);
        libelleNombreValeurs = (TextView)findViewById(R.id.libelle_nombrevaleurs_vue_accelerometre);
        libelleMoyenne = (TextView)findViewById(R.id.libelle_moyenne_vue_accelerometre);
        libelleValeurMax = (TextView)findViewById(R.id.libelle_valeurmax_vue_accelerometre);
        libelleValeurMin = (TextView)findViewById(R.id.libelle_valeurmin_vue_accelerometre);

        accelerometreDAO = new AccelerometreDAO();

        afficherValeursAccelerometre();

    }

    protected void afficherValeursAccelerometre(){

        listeAccelerometreEnHashMap = accelerometreDAO.listerLesValeursAccelerometreEnHashMap();

        SimpleAdapter adapteurVueListeAccelerometre = new SimpleAdapter(
                this,
                listeAccelerometreEnHashMap,
                android.R.layout.two_line_list_item,
                new  String[]{"accelerometre","date"},
                new int[]{android.R.id.text1, android.R.id.text2}
        );

        vueListeAccelerometre.setAdapter(adapteurVueListeAccelerometre);
    }
}
