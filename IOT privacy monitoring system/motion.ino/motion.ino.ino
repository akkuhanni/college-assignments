#include <SPI.h>
#include <Ethernet.h>
//-------------------------------------------------------------------------------
byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED }; //Setting MAC Address
char server[] = "api.pushingbox.com"; //pushingbox API server
IPAddress ip(192,168,1,3); //Arduino IP address. Only used when DHCP is turned off.
EthernetClient client; //define 'client' as object
String data; //GET query with data
boolean connection = false;
int ledPin = 13;                // choose the pin for the LED
int inputPin = 2;               // choose the input pin (for PIR sensor)
int pirState = LOW;             // we start, assuming no motion detected
int val;                    // variable for reading the pin status
 
void setup() {
  Serial.begin(9600);
  if (Ethernet.begin(mac) == 0) {
  Serial.println("Failed to configure Ethernet using DHCP");
  Ethernet.begin(mac, ip);
  }
  pinMode(ledPin, OUTPUT);      // declare LED as output
  pinMode(inputPin, INPUT);     // declare sensor as input
   delay(1000);
}
 
void loop(){
  val = digitalRead(inputPin);  // read input value
  gData(); //packing GET query with data
   Serial.println("connecting...");
   if (client.connect(server, 80)) {
     sendData();  
     connection = true; //connected = true
   }
   else{
     Serial.println("connection failed");
   }
  // loop
  while(connection){
    if (client.available()) {
    char c = client.read(); //save http header to c
    Serial.print(c); //print http header to serial monitor
    }
    if (!client.connected()) {
    Serial.println();
    Serial.println("disconnecting.");
                  Serial.println(val); //print sent value to serial monitor
          client.stop(); 
          connection = false; 
          data = ""; //data reset
    }
  }
  delay(5000); // interval
  if (val == HIGH) {            // check if the input is HIGH
    digitalWrite(ledPin, HIGH);  // turn LED ON
    if (pirState == LOW) {
      // we have just turned on
      Serial.println("Motion detected!");
      val=1;
      // We only want to print on the output change, not state
      pirState = HIGH;
    }
    
  } else {
    digitalWrite(ledPin, LOW); // turn LED OFF
    if (pirState == HIGH){
      // we have just turned of
      Serial.println("Motion ended!");
      // We only want to print on the output change, not state
      pirState = LOW;
      val=0;
    }
    
  }
}

void gData(){
  data+="";
  data+="GET /pushingbox?devid=v8C012E4A9161BF7&sensor="; //GET request query to pushingbox API
  data+=val;
  data+=" HTTP/1.1";
}
void sendData(){
  Serial.println("connected");
  client.println(data);
  client.println("Host: api.pushingbox.com");
  client.println("Connection: close");
  client.println();
}
