package ca.qc.cgmatane.informatique.capteurvaleur.Donnees;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDeDonnees extends SQLiteOpenHelper
{

    private static BaseDeDonnees instance = null;
    public static String databasePath;

    public static BaseDeDonnees getInstance(Context contexte)
    {
        if (null == instance) instance = new BaseDeDonnees(contexte);
        return instance;
    }

    public static BaseDeDonnees getInstance()
    {
        return instance;
    }

    public BaseDeDonnees(Context contexte)
    {
        super(contexte, "capteurvaleur.db", null, 1);
        databasePath = contexte.getDatabasePath("capteurvaleur.db").getPath();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS accelerometre" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "x DOUBLE, " +
                "y DOUBLE, " +
                "z DOUBLE, " +
                "date TEXT, " +
                "heure TEXT)";

        db.execSQL(CREATE_TABLE);
    }

    @Override

    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2)
    {

    }

    @Override
    public void onOpen(SQLiteDatabase db)
    {
       // String INSERT_1 = "INSERT INTO accelerometre(id, x, y, z, date, heure) " +
                //"VALUES('1','test x', 'test y', 'test z', 'test date', 'test heure')";

        //db.execSQL(INSERT_1);
    }
}
