using System;
using System.Data.SQLite;
using System.IO;

public class Launcher
{
    const string DatabaseFile = "Raspberry.db";
    public const string DatabaseSource = "data source=" + DatabaseFile;

    public const string CreateTable = @"CREATE TABLE IF NOT EXISTS [temperature] (
                                        [id] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
                                        [celsius] VARCHAR(2048)  NULL,
                                        [date] VARCHAR(2048)  NULL,
                                        [heure] VARCHAR(2048)  NULL)";

    static void Main(string[] args)
    {
        if (!File.Exists(DatabaseFile))
        {
            SQLiteConnection.CreateFile(DatabaseFile);
        }

        Console.WriteLine("Script lancé");
        Console.WriteLine("Ecriture dans la table: \"temperature\" toutes les 30 minutes");
        new ReadFromArduino();
    }
}
