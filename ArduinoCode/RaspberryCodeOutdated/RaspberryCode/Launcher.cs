using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

public class Launcher
{
    static void Main(string[] args)
    {
        Console.WriteLine("Script lancé");
        Console.WriteLine("Ecriture dans la table: \"temperature\" toutes les 30 minutes");
        new ReadFromArduino();
    }
}
