#include <string.h>
#include <Arduino.h>
//~ #include <Time.h>

//~ #include <NTPClient.h>
#include <ESP8266WiFi.h>
#include <dht11.h>
#include <RestClient.h>

#define DEBUG      (1)

const char* ssid = "starthack";
const char* password = "bewhereinovationhappens";

const char* json_format =  "{ \"id\":%d, \"timestamp\":%l, \"temperature\":%d, \"humidity\":%d, \"light\":%d, \"noise\":%d, \"vibration\":%d }";

dht11 DHT11;
#define DHT11PIN D6

long timestamp = 0;

int connect_to_wifi(){
#if DEBUG == 1
	Serial.println();
	Serial.println();
	Serial.print("Connecting to ");
	Serial.println(ssid);
#endif
	WiFi.begin(ssid, password);
 
	while (WiFi.status() != WL_CONNECTED) {
		delay(500);
#if DEBUG == 1
		Serial.print(".");
 #endif
	}
#if DEBUG == 1
	Serial.println("");
	Serial.println("WiFi connected");
 #endif
	return(0);
}

int read_hunidity_temp(int pin){
	DHT11.read(pin);
#if DEBUG == 1
	Serial.print("Humidity ="); 
	Serial.print(DHT11.humidity);
	Serial.println(""); 
	Serial.print("Temperature ="); 
	Serial.print(DHT11.temperature);
	Serial.println("");
 #endif
	return 0;
}
 
void setup() {
	timestamp = 0;
#if DEBUG == 1
	Serial.begin(9600);
 #endif
	//~ connect_to_wifi();
}
 
void loop() {
	char json_data[150];
	timestamp++;
	read_hunidity_temp(DHT11PIN);
	//~ sprintf(json_data, json_format,  
	delay(1000);
}
 
