using System;
using System.Collections.Generic;
using System.IO.Ports;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

public class ReadFromArduino
{
    SerialPort arduino = new SerialPort("COM6", 9600, Parity.None, 8, StopBits.One);

    public bool droitEcriture = true;
    public ReadFromArduino();
        }
        
    private void ArduinoDataReceived(object sender, SerialDataReceivedEventArgs e)
    {
        String valeurArduino = arduino.ReadLine();
        if (valeurArduino.Length == 6 && (DateTime.Now.Minute == 30 || DateTime.Now.Minute == 00))
            if (valeurArduino.Length == 6 && droitEcriture == true && (DateTime.Now.Minute == 30 || DateTime.Now.Minute == 00))
            {
                droitEcriture = false;
                Insert2Database(valeurArduino);
            }

            else
                valeurArduino = "";
        valeurArduino = "";

        if (droitEcriture == false && (DateTime.Now.Minute != 30 && DateTime.Now.Minute != 00))
            droitEcriture = true;
    }

private void Insert2Database(String valeur)
{
    var connectionInformations = "Host=127.0.0.1;Username=Nicolas;Password=Sql1995;Database=jeuvideo;";

    using (var connection = new NpgsqlConnection(connectionInformations))
    {

        connection.Open();
        using (var cmd = new NpgsqlCommand())
        {
            cmd.Connection = connection;
            cmd.CommandText = "INSERT INTO developpeur (nom) VALUES (@p)";
            cmd.Parameters.AddWithValue("p", valeur);
            cmd.ExecuteNonQuery();
            Console.WriteLine("Valeur inscrite: " + valeur);
        }
    }
}
}
