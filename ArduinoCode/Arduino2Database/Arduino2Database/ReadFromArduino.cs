using Npgsql;
using System;
using System.IO.Ports;
using Newtonsoft.Json;
using System.Data;
using System.Threading.Tasks;
using System.Net.Http;
using System.Text;

public class ReadFromArduino
{
    SerialPort arduino = new SerialPort("COM7", 9600, Parity.None, 8, StopBits.One);
    public bool droitEcriture = true;
    public bool droitEcriture2 = true;
    DataTable tableTemporaire = GetTable();

    public ReadFromArduino()
    {
        arduino.DataReceived += new SerialDataReceivedEventHandler(ArduinoDataReceived); //Get les datas du port

        arduino.Open(); //Ouvre le port

        Console.Read();
    }

    private void ArduinoDataReceived(object sender, SerialDataReceivedEventArgs e)
    {
        String valeurArduino = arduino.ReadLine();
        if (valeurArduino.Length == 6 && droitEcriture == true && (DateTime.Now.Minute == 38|| DateTime.Now.Minute == 43))
        {
            droitEcriture = false;
            Insert2Database(valeurArduino);
        }

        else
            valeurArduino = "";

        if (droitEcriture == false && (DateTime.Now.Minute == 15 || DateTime.Now.Minute == 25))
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
        var connectionInformations = "Host=127.0.0.1;Username=postgres;Password=Sql1995;Database=temperaturedb;";

        using (var connection = new NpgsqlConnection(connectionInformations))
        {
            connection.Open();

            using (var cmd = new NpgsqlCommand())
            {
                cmd.Connection = connection;

                cmd.CommandText = "INSERT INTO temperature (temperature_celsius, temperature_date, temperature_heure, sync) VALUES (@c, @d, @h, @s)";
                cmd.Parameters.AddWithValue("@c", valeur);
                cmd.Parameters.AddWithValue("@d", DateTime.Now.ToString("MM/dd/yyyy"));
                cmd.Parameters.AddWithValue("@h", DateTime.Now.ToString("hh'h'mm"));
                cmd.Parameters.AddWithValue("@s", "0");

                tableTemporaire.Rows.Add(valeur, DateTime.Now.ToString("MM/dd/yyyy"), DateTime.Now.ToString("hh'h'mm"), "0");

                cmd.ExecuteNonQuery();

                Console.WriteLine("Valeur inscrite: " + valeur);
                Console.WriteLine("Date inscrite: " + DateTime.Now.ToString("MM/dd/yyyy"));
                Console.WriteLine("Heure inscrite: " + DateTime.Now.ToString("hh'h'mm"));


                String jsonTable = DataTableToJson(tableTemporaire);
                Console.WriteLine(jsonTable);
                Console.WriteLine();
                
                //RequeteJson(jsonTable);
                //Console.WriteLine("Envoi");

                if (DateTime.Now.Hour == 09 && DateTime.Now.Minute == 43 && droitEcriture2 == true)
                {
                    droitEcriture2 = false;             
                    RequeteJson(jsonTable);
                    Console.WriteLine("Envoi de la requete : " + jsonTable);
                    cmd.CommandText = "UPDATE temperature SET sync = '1' WHERE sync = '0' ";
                    cmd.ExecuteNonQuery();
                    tableTemporaire.Clear();
                    tableTemporaire = GetTable();
                    jsonTable = DataTableToJson(tableTemporaire);
                    Console.WriteLine("Envoi de la requete 2 : " + jsonTable);
                }

                if (droitEcriture2 == false && DateTime.Now.Hour == 00 && DateTime.Now.Minute == 01)
                    droitEcriture2 = true;

            }

            connection.Close();
        }


    }

    static DataTable GetTable()
    {
        DataTable table = new DataTable();

        table.Columns.Add("temperature_celsius", typeof(string));
        table.Columns.Add("temperature_date", typeof(string));
        table.Columns.Add("temperature_heure", typeof(string));
        table.Columns.Add("sync", typeof(string));

        return table;
    }

    static string DataTableToJson(DataTable table)
    {
        string JSONString = string.Empty;
        JSONString = JsonConvert.SerializeObject(table);
        return JSONString;
    }

    //Envoi en HTTP
    static async Task RequeteJson(String data)
    {
        // notre cible
        string page = "http://192.168.1.146:8080/ajouter-temperature/";

        using (HttpClient client = new HttpClient())
        {
            HttpContent contentPost = new StringContent(data,
            Encoding.UTF8, "application/json");

            var response = await client.PostAsync(new Uri(page), contentPost)
                  .ContinueWith(
                     (t) => t.Result.EnsureSuccessStatusCode()
                   );
        }
    }
}


