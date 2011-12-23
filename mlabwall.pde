import mpe.config.*;
import mpe.client.*;

TCPClient cl;
String ip;
String configname;
Logo logo;

void setup() {
//  try {
//    InetAddress local = InetAddress.getLocalHost();
//    ip = local.getHostAddress();
//    println(ip);
//  } catch( UnknownHostException e ) {
//    println( "Unknown Host Exception:" );
//    println( e );
//    exit();
//  }
//  String p[] = splitTokens(ip, ".");
//  String[] cn = new String[3];
//  cn[0] = "config/temp/full-config-";
//  cn[1] = ip;
//  cn[2] = ".ini";
//  configname = join(cn, "");
  configname = "z:/Dropbox/mlab/mlabwall/config/temp/full-config-192.168.8.2.ini";
  println(configname);
  
 // composeClientConfig();
  
  // make a new Client using an INI file
  // sketchPath() is used so that the INI file is local to the sketch
  cl = new TCPClient(sketchPath(configname), this);
  
  // the size is determined by the client's local width and height
//  size(cl.getLWidth(), cl.getLHeight(), OPENGL);
//  hint(ENABLE_OPENGL_4X_SMOOTH); 
  size(cl.getLWidth(), cl.getLHeight());
  hint(ENABLE_OPENGL_4X_SMOOTH); 
  background(255);
  smooth();
  noStroke();
  
  logo = new Logo(cl.getMWidth()/2+180, cl.getMHeight()/2);
  
  // Important, must start the client!
  cl.start();
}

void composeClientConfig() {
 String shared[] = loadStrings("config/mpeConfigShared.ini");
 String local[] = loadStrings("config/local/"+ip+".ini");
 String[] config = concat( shared, local );
 saveStrings("config/temp/full-config-"+ip+".ini", config);
}

//--------------------------------------
// Triggered by the client whenever a new frame should be rendered.
// All synchronized drawing should be done here when in auto mode.
 void frameEvent(TCPClient c) {
     background(0);

    renderText();
  
    logo.calc();
    logo.display();
}

void draw(){
}

void renderText() {
  // text
  pushMatrix();
  translate(0, cl.getMHeight()/2);
  fill(#ffffff);
  textSize(144); 
    
  text("M-Lab", 15, 50);
  popMatrix();
}


