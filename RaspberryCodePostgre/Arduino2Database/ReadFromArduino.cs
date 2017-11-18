using System;

public class ReadFromArduino
{
    SerialPort arduino = new SerialPort("COM3", 9600, Parity.None, 8, StopBits.One);

    ReadFromArduino()
    {
        //Set the datareceived event handler
        sp.DataReceived += new SerialDataReceivedEventHandler(arduinoDataReceived);
        //Open the serial port
        sp.Open();
        //Read from the console, to stop it from closing.
        Console.Read();
    }

    private void arduinoDataReceived(object sender, SerialDataReceivedEventArgs e)
    {
        //Write the serial port data to the console.
        Console.Write(arduino.ReadExisting());
    }
}
