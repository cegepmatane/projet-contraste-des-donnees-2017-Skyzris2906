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
        if (valeurArduino.Length == 6 && droitEcriture == true && (DateTime.Now.Minute == 11 || DateTime.Now.Minute == 00))
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
        var connectionInformations = "Host=127.0.0.1;Username=postgres;Password=Sql1995;Database=principale;";

        using (var connection = new NpgsqlConnection(connectionInformations))
        {
            connection.Open();

            using (var cmd = new NpgsqlCommand())
            {
                cmd.Connection = connection;

                cmd.CommandText = "INSERT INTO temperature (temperature_celsius, temperature_date, temperature_heure) VALUES (@c, @d, @h)";
                cmd.Parameters.AddWithValue("@c", valeur);
                cmd.Parameters.AddWithValue("d", DateTime.Now.ToString("MM/dd/yyyy"));
                cmd.Parameters.AddWithValue("h", DateTime.Now.ToString("hh'h'mm"));

                tableTemporaire.Rows.Add(valeur, DateTime.Now.ToString("MM/dd/yyyy"), DateTime.Now.ToString("hh'h'mm"));

                cmd.ExecuteNonQuery();

                Console.WriteLine("Valeur inscrite: " + valeur);
                Console.WriteLine("Date inscrite: " + DateTime.Now.ToString("MM/dd/yyyy"));
                Console.WriteLine("Heure inscrite: " + DateTime.Now.ToString("hh'h'mm"));


                String jsonTable = DataTableToJson(tableTemporaire);

                Console.WriteLine(jsonTable);

                if(DateTime.Now.Hour == 00 && DateTime.Now.Minute == 00 && droitEcriture2 == true)
                {
                    droitEcriture2 = false;
                    PostBookAsync(jsonTable);
                }

                if (droitEcriture2 == false && DateTime.Now.Hour == 00 && DateTime.Now.Minute == 01)
                    droitEcriture2 = true;

            }

            //using (NpgsqlCommand cmd = new NpgsqlCommand ("SELECT * FROM temperature", connection))
            //{
            //    using (var reader = cmd.ExecuteReader())
            //    {
            //        while (reader.HasRows)
            //        {
            //            String json = JsonConvert.SerializeObject(reader);
            //            Console.WriteLine(json);
            //            reader.NextResult();
            //        }
            //    }
            //}
            connection.Close();
        }


    }

    static DataTable GetTable()
    {
        DataTable table = new DataTable();

        //table.Columns.Add("id_temperature", typeof(int));
        table.Columns.Add("temperature_celsius", typeof(string));
        table.Columns.Add("temperature_date", typeof(string));
        table.Columns.Add("temperature_heure", typeof(string));

        return table;
    }

    static string DataTableToJson(DataTable table)
    {
        string JSONString = string.Empty;
        JSONString = JsonConvert.SerializeObject(table);
        return JSONString;
    }


    //Envoi en HTTP
    static async Task PostBookAsync(String data)
    {
        // notre cible
        string page = "http://127.0.0.1:8080/ajouter-temperature/";

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


