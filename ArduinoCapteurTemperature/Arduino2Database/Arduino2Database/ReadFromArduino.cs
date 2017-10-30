using Npgsql;
using System;
using System.IO.Ports;

public class ReadFromArduino
{
    SerialPort arduino = new SerialPort("COM6", 9600, Parity.None, 8, StopBits.One);

    public ReadFromArduino()
    {
        
        arduino.DataReceived += new SerialDataReceivedEventHandler(ArduinoDataReceived); //Get les datas du port
        
        arduino.Open(); //Ouvre le port
        
        Console.Read();
    }

    private void ArduinoDataReceived(object sender, SerialDataReceivedEventArgs e)
    {
        String valeurArduino = arduino.ReadLine();
        if (valeurArduino.Length == 6)
            Insert2Database(valeurArduino);
        else
            valeurArduino = "";
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
