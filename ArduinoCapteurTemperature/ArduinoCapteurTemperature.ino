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

  Serial.print(volts); //Affiche le
  Serial.println(" V"); //voltage

    //Conversion en temperature Celsius
  float temperatureCelsius = (volts - 0.5) * 100 ; //10mV par °C

  Serial.print(temperatureCelsius);
  Serial.println(" °C");

  delay(1000); //Pour 1 seconde
}
