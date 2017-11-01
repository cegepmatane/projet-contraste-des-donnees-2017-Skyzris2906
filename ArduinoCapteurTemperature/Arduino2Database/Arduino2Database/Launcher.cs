using System;

public class Launcher
{
    static void Main(string[] args)
    {
        Console.WriteLine("Script lancé");
        Console.WriteLine("Ecriture dans la table: \"temperature\" toutes les 30 minutes");
        new ReadFromArduino();
    }
}
