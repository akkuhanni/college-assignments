
#include <Ethernet.h>
#include <SPI.h>

byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };
EthernetClient client;

IPAddress ip(192,168,,);
char server[] = "www..pixub.com"; // IP Adres (or name) of server to dump data 
int msv_intruder=0;
String data;
String url;




void setup()
{
  Serial.begin(9600);
  Serial.println("SECURITY MONITOR");

  if (Ethernet.begin(mac) == 0) {
    Serial.println("Failed to configure Ethernet using DHCP"); 
    Ethernet.begin(mac, ip);
     delay(1000);
  }
  Serial.print("IP Address        : ");
  Serial.println(Ethernet.localIP());
 
}

void loop() 
{
  // put your main code here, to run repeatedly:
  data=" ";
  url=" ";
  data="?intruder=" + String(msv_intruder) ;
  url="/set_intr.php";
  url=url+data;
  if (client.connect(server, 80)  && (msv_intruder==1) ) 
  {
    
    Serial.println("-> Connected");
    client.print( String("GET ")+ url + " HTTP/1.1\r\n" + "Host: iot.pixub.com\r\n" + "Connection: close\r\n\r\n"   ); 
    client.println("Content-Type: application/x-www-form-urlencoded"); 
    Serial.println("intruder value is set to 1");
  }
  if (client.connected())
  { 
    client.stop();  
    // DISCONNECT FROM THE SERVER
    Serial.println("-> Disonnected");
  }
   delay(3000); // WAIT FIVE MINUTES BEFORE SENDING AGAIN
}


  


  

}
