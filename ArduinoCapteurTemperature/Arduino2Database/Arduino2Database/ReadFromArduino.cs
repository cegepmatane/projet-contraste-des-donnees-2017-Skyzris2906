using Npgsql;
using System;
using System.Data.SQLite;
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

        if (DateTime.Now.Second == 00)
        {
            switch (DateTime.Now.Minute)
            {
                case 10:
                    Console.WriteLine("Ecriture dans 20 Minutes");
                    break;

                case 20:
                    Console.WriteLine("Ecriture dans 10 Minutes");
                    break;

                case 25:
                    Console.WriteLine("Ecriture dans 5 Minutes");
                    break;

                case 40:
                    Console.WriteLine("Ecriture dans 20 Minutes");
                    break;

                case 50:
                    Console.WriteLine("Ecriture dans 10 Minutes");
                    break;

                case 55:
                    Console.WriteLine("Ecriture dans 5 Minutes");
                    break;
            }
        }
    }

    private void Insert2Database(String valeur)
    {
        using (var connection = new SQLiteConnection(Launcher.DatabaseSource))
        {
            using (var cmd = new SQLiteCommand(connection))
            {
                connection.Open();
                cmd.CommandText = Launcher.CreateTable;
                cmd.ExecuteNonQuery();

                cmd.CommandText = "INSERT INTO temperature (celsius, date, heure) VALUES (@c, @d, @h)";
                cmd.Parameters.AddWithValue("@c", valeur);
                cmd.Parameters.AddWithValue("d", DateTime.Now.ToString("MM/dd/yyyy"));
                cmd.Parameters.AddWithValue("h", DateTime.Now.ToString("hh'h'mm"));
                cmd.ExecuteNonQuery();

                Console.WriteLine("Valeur inscrite: " + valeur);
                Console.WriteLine("Date inscrite: " + DateTime.Now.ToString("MM/dd/yyyy"));
                Console.WriteLine("Heure inscrite: " + DateTime.Now.ToString("hh'h'mm"));

                connection.Close();
            }
        }
    }
}
