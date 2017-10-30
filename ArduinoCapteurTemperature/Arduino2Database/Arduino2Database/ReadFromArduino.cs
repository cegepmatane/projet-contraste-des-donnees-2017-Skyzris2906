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
    }
}
