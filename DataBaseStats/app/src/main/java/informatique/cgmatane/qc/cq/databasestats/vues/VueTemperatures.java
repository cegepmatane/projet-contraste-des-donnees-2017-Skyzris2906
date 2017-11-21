package informatique.cgmatane.qc.cq.databasestats.vues;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import informatique.cgmatane.qc.cq.databasestats.R;
import informatique.cgmatane.qc.cq.databasestats.donnees.TemperaturesDAO;
import informatique.cgmatane.qc.cq.databasestats.modele.StatistiquesTemperatures;
import informatique.cgmatane.qc.cq.databasestats.modele.Temperature;

public class VueTemperatures extends AppCompatActivity {

    protected ListView vueListeTemperatures;
    protected List<HashMap<String,String>> listeTemperaturesEnHashMap;
    protected TemperaturesDAO temperaturesDAO;
    protected List<Temperature> listeTemperatures;
    protected SimpleAdapter adapteurVueListeTemperatures;

    protected TextView libelleMoyenne;
    protected TextView libelleValeurMax;
    protected TextView libelleValeurMin;
    protected TextView libelleNombreValeurs;
    protected Button boutonAnnee;
    protected Button boutonMois;
    protected Button boutonSemaine;
    protected Button boutonJour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_temperatures);

        vueListeTemperatures = (ListView)findViewById(R.id.liste_temperatures_vue_temperatures);
        libelleNombreValeurs = (TextView)findViewById(R.id.libelle_nombrevaleurs_vue_temperatures);
        libelleMoyenne = (TextView)findViewById(R.id.libelle_moyenne_vue_temperatures);
        libelleValeurMax = (TextView)findViewById(R.id.libelle_valeurmax_vue_temperatures);
        libelleValeurMin = (TextView)findViewById(R.id.libelle_valeurmin_vue_temperatures);
        boutonAnnee = (Button)findViewById(R.id.bouton_annee_vue_temperatures);
        boutonMois = (Button)findViewById(R.id.bouton_mois_vue_temperatures);
        boutonSemaine = (Button)findViewById(R.id.bouton_semaine_vue_temperatures);
        boutonJour = (Button)findViewById(R.id.bouton_jour_vue_temperatures);

        temperaturesDAO = new TemperaturesDAO(getApplicationContext());

        listeTemperatures = temperaturesDAO.listerToutesLesTemperatures();
        afficherLesTemperatures();
        afficherStatistiques();


        boutonAnnee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listeTemperatures = temperaturesDAO.listerTemperaturesAnnee();
                adapteurVueListeTemperatures.notifyDataSetChanged();
                afficherLesTemperatures();
                afficherStatistiques();

            }
        });

        boutonMois.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                listeTemperatures = temperaturesDAO.listerTemperaturesMois();
                adapteurVueListeTemperatures.notifyDataSetChanged();
                afficherLesTemperatures();
                afficherStatistiques();

            }
        });

        boutonSemaine.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                listeTemperatures = temperaturesDAO.listerTemperaturesSemaine();
                adapteurVueListeTemperatures.notifyDataSetChanged();
                afficherLesTemperatures();
                afficherStatistiques();

            }
        });

        boutonJour.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                listeTemperatures = temperaturesDAO.listerTemperaturesJour();
                adapteurVueListeTemperatures.notifyDataSetChanged();
                afficherLesTemperatures();
                afficherStatistiques();

            }
        });

    }

    protected void afficherLesTemperatures(){

        listeTemperaturesEnHashMap = temperaturesDAO.listerLesTemperaturesEnHashMap();

        adapteurVueListeTemperatures = new SimpleAdapter(
                this,
                listeTemperaturesEnHashMap,
                android.R.layout.two_line_list_item,
                new  String[]{"temperature","date"},
                new int[]{android.R.id.text1, android.R.id.text2}
        );

        vueListeTemperatures.setAdapter(adapteurVueListeTemperatures);
    }

    protected void afficherStatistiques(){

        libelleNombreValeurs.setText("Total : "+ listeTemperatures.size() +" valeurs");
        libelleMoyenne.setText("Valeur moyenne : " + StatistiquesTemperatures.calculerMoyenne(listeTemperatures)+" °C");
        libelleValeurMax.setText("Valeur maximum : " + StatistiquesTemperatures.calculerMaximum(listeTemperatures)+" °C");
        libelleValeurMin.setText("Valeur minimum : " + StatistiquesTemperatures.calculerMinimum(listeTemperatures)+" °C");
    }
}
