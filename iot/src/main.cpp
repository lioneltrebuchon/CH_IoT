#include <string.h>
#include <Arduino.h>

#include <ESP8266WiFi.h>
#include <dht11.h>
#include <RestClient.h>

#define DEBUG      (1)

#define ID         (1)


//~ const char* ssid = "starthack";
//~ const char* password = "bewhereinovationhappens";
const char* ssid = "XT1039";
const char* password = "";

const char* json_format =  "{ \"id\":%d, \"timestamp\":%d,  \"vibration\":%d, \"noise\":%d, \"humidity\":%d, \"temperature\":%d, \"light\":%d }";

dht11 DHT11;
#define DHT11PIN D6

int lightPin = D8;

RestClient client = RestClient("52.14.216.215",8080);

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

int get_vibration(){
	return(12);
}

int get_noise(){
	return(25);
}

int get_light(){
	//~ int result = analogRead(lightPin);
//~ #if DEBUG == 1
	//~ Serial.print("Light ="); 
	//~ Serial.print(result);
	//~ Serial.println(""); 
 //~ #endif
	int result = 12;
	return(result);
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
	connect_to_wifi();
#if DEBUG == 1
	Serial.println("Setup!");
 #endif
}
 
void loop() {
	char json_data[150];
	timestamp=timestamp+1;
	read_hunidity_temp(DHT11PIN);
	sprintf(json_data, json_format, ID, timestamp, get_vibration(), get_noise(), DHT11.humidity, DHT11.temperature , get_light());
#if DEBUG == 1
	Serial.println(json_data);
#endif
    int statusCode = client.post("/rest/", json_data);
#if DEBUG == 1
    Serial.print("Status code from server: ");
    Serial.println(statusCode);
#endif
	delay(1000);
}
 
