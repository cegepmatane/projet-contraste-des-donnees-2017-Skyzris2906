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
import informatique.cgmatane.qc.cq.databasestats.donnees.AccelerometreDAO;
import informatique.cgmatane.qc.cq.databasestats.donnees.TemperaturesDAO;
import informatique.cgmatane.qc.cq.databasestats.modele.Accelerometre;
import informatique.cgmatane.qc.cq.databasestats.modele.StatistiquesAccelerometre;
import informatique.cgmatane.qc.cq.databasestats.modele.StatistiquesTemperatures;
import informatique.cgmatane.qc.cq.databasestats.modele.Temperature;

public class VueAccelerometre extends AppCompatActivity {

    protected ListView vueListeAccelerometre;
    protected List<HashMap<String,String>> listeAccelerometreEnHashMap;
    protected SimpleAdapter adapteurVueListeAccelerometre;
    protected AccelerometreDAO accelerometreDAO;
    protected List<Accelerometre> listeAccelerometre;

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
        setContentView(R.layout.vue_accelerometre);

        vueListeAccelerometre = (ListView)findViewById(R.id.liste_accelerometre_vue_accelerometre);
        libelleNombreValeurs = (TextView)findViewById(R.id.libelle_nombrevaleurs_vue_accelerometre);
        libelleMoyenne = (TextView)findViewById(R.id.libelle_moyenne_vue_accelerometre);
        libelleValeurMax = (TextView)findViewById(R.id.libelle_valeurmax_vue_accelerometre);
        libelleValeurMin = (TextView)findViewById(R.id.libelle_valeurmin_vue_accelerometre);
        boutonAnnee = (Button)findViewById(R.id.bouton_annee_vue_accelerometre);
        boutonMois = (Button)findViewById(R.id.bouton_mois_vue_accelerometre);
        boutonSemaine = (Button)findViewById(R.id.bouton_semaine_vue_accelerometre);
        boutonJour = (Button)findViewById(R.id.bouton_jour_vue_accelerometre);

        accelerometreDAO = new AccelerometreDAO(getApplicationContext());

        listeAccelerometre = accelerometreDAO.listerToutesLesValeursAccelerometre();
        afficherValeursAccelerometre();

        afficherStatistiques();

        boutonAnnee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listeAccelerometre = accelerometreDAO.listerAccelerometreAnnee();
                adapteurVueListeAccelerometre.notifyDataSetChanged();
                afficherValeursAccelerometre();
                afficherStatistiques();

            }
        });

        boutonMois.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listeAccelerometre = accelerometreDAO.listerAccelerometreMois();
                adapteurVueListeAccelerometre.notifyDataSetChanged();
                afficherValeursAccelerometre();
                afficherStatistiques();
            }
        });

        boutonSemaine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listeAccelerometre = accelerometreDAO.listerAccelerometreSemaine();
                adapteurVueListeAccelerometre.notifyDataSetChanged();
                afficherValeursAccelerometre();
                afficherStatistiques();
            }
        });

        boutonJour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listeAccelerometre = accelerometreDAO.listerAccelerometreJour();
                adapteurVueListeAccelerometre.notifyDataSetChanged();
                afficherValeursAccelerometre();
                afficherStatistiques();
            }
        });

    }

    protected void afficherValeursAccelerometre(){

        listeAccelerometreEnHashMap = accelerometreDAO.listerLesValeursAccelerometreEnHashMap();

         adapteurVueListeAccelerometre = new SimpleAdapter(
                this,
                listeAccelerometreEnHashMap,
                android.R.layout.two_line_list_item,
                new  String[]{"accelerometre","date"},
                new int[]{android.R.id.text1, android.R.id.text2}
        );

        vueListeAccelerometre.setAdapter(adapteurVueListeAccelerometre);
    }

    protected void afficherStatistiques(){

        libelleNombreValeurs.setText("Total : "+ listeAccelerometre.size()+ " valeurs");

        libelleMoyenne.setText("Moyenne : "+ StatistiquesAccelerometre.calculerMoyenne(listeAccelerometre));
        libelleValeurMax.setText("Valeur Maximum : "+ StatistiquesAccelerometre.calculerMaximum(listeAccelerometre));
        libelleValeurMin.setText("Valeur Minimum : "+ StatistiquesAccelerometre.calculerMinimum(listeAccelerometre));
    }
}
