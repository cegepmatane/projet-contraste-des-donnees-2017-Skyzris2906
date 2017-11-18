using System;
using System.Data.SQLite;
using System.IO;

public class Launcher
{
    static void Main(string[] args)
    {
        Console.WriteLine("Script lancé");
        Console.WriteLine("Ecriture dans la table: \"temperature\" toutes les 30 minutes");
        new ReadFromArduino();
    }
}
