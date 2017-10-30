using Npgsql;
using System;
using System.IO.Ports;

public class ReadFromArduino
{
    SerialPort arduino = new SerialPort("COM6", 9600, Parity.None, 8, StopBits.One);
    public bool droitEcriture = true;
    public ReadFromArduino()
    {
        
        arduino.DataReceived += new SerialDataReceivedEventHandler(ArduinoDataReceived); //Get les datas du port
        
        arduino.Open(); //Ouvre le port
        
        Console.Read();
    }

    private void ArduinoDataReceived(object sender, SerialDataReceivedEventArgs e)
    {
        String valeurArduino = arduino.ReadLine();
        if (valeurArduino.Length == 6 && droitEcriture == true && (DateTime.Now.Minute == 30 || DateTime.Now.Minute == 00))
        {
            droitEcriture = false;
            Insert2Database(valeurArduino);
        }

        else
            valeurArduino = "";

        if (droitEcriture == false && (DateTime.Now.Minute == 29 || DateTime.Now.Minute == 59))
            droitEcriture = true;
    }

    private void Insert2Database(String valeur)
    {
        var connectionInformations = "Host=127.0.0.1;Username=postgres;Password=Sql1995;Database=principale;";

        using (var connection = new NpgsqlConnection(connectionInformations))
        {

            connection.Open();
            using (var cmd = new NpgsqlCommand())
            {
                cmd.Connection = connection;
                cmd.CommandText = "INSERT INTO temperature (temperature_celsius, temperature_date, temperature_heure) VALUES (@t, @d, @h)";
                cmd.Parameters.AddWithValue("t", valeur);
                cmd.Parameters.AddWithValue("d", DateTime.Now.ToString("MM/dd/yyyy"));
                cmd.Parameters.AddWithValue("h", DateTime.Now.ToString("hh'h'mm"));
                cmd.ExecuteNonQuery();
                Console.WriteLine("Valeur inscrite: " + valeur);
                Console.WriteLine("Date inscrite: " + DateTime.Now.ToString("MM/dd/yyyy"));
                Console.WriteLine("Heure inscrite: " + DateTime.Now.ToString("hh'h'mm"));
            }
        }
    }
}
