int capteurTemp = 0; //Le capteur de temperature est positionné à l'analog 0

void setup() 
{
  Serial.begin(9600); //Connexion avec l'ordinateur
}

void loop() 
{
  int lectureTemp = analogRead(capteurTemp); //Lecture des volts du capteur de temperature

    //Capteur branché en 5V
  float volts = lectureTemp * 5.0;
  volts /= 1024.0;

    //Si le capteur est branché en 3.3V
  /* float volts = lectureTemp * 3.3;
  volts /= 1024.0; */

  Serial.print(volts); //Affiche le
  Serial.println(" V"); //voltage

    //Conversion en temperature Celsius
  float temperatureCelsius = (volts - 0.5) * 100 ; //10mV par °C

  Serial.print(temperatureCelsius);
  Serial.println(" °C");

    //Conversion en temperature Fahrenheit
  float temperatureFahrenheit = (temperatureCelsius * 9.0 / 5.0) + 32.0;

  Serial.print(temperatureFahrenheit);
  Serial.println(" °F");

  delay(1000); //Pour 1 seconde
  //delay(60000) //Pour 1 minute
  //delay(600000); //Pour 10 minutes

}
