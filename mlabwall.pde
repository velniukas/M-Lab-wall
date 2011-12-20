import mpe.config.*;
import mpe.client.*;

import processing.opengl.*;
import javax.media.opengl.*;
import java.util.*;

PGraphicsOpenGL pgl;
GL gl;

TCPClient cl;
String ip;
String configname;

void setup() {
  try {
    InetAddress local = InetAddress.getLocalHost();
    ip = local.getHostAddress();
    println(ip);
  } catch( UnknownHostException e ) {
    println( "Unknown Host Exception:" );
    println( e );
    exit();
  }
  String p[] = splitTokens(ip, ".");
  String[] cn = new String[3];
  cn[0] = "config/temp/full-config-";
  cn[1] = ip;
  cn[2] = ".ini";
  configname = join(cn, "");
  println(configname);
  
  composeClientConfig();
  
  // make a new Client using an INI file
  // sketchPath() is used so that the INI file is local to the sketch
  cl = new TCPClient(sketchPath(configname), this);
  
  // the size is determined by the client's local width and height
  size(cl.getLWidth(), cl.getLHeight(), OPENGL);
  hint(ENABLE_OPENGL_4X_SMOOTH); 
  background(255);
  smooth();
  noStroke();
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

    // text
    pushMatrix();
    translate(0, cl.getMHeight()/2);
    fill(#ffffff);
    textSize(144); 
    
    text("M-Lab", 15, 50);
    popMatrix();
  
  
  // blending setup
    pgl = (PGraphicsOpenGL) g;
    gl = pgl.beginGL();
    gl.glDisable(GL.GL_DEPTH_TEST);
    gl.glEnable(GL.GL_BLEND);
    //gl.glBlendFunc(GL.GL_SRC_COLOR, GL.GL_ZERO);
    gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ZERO);
    //gl.glBlendFunc(GL.GL_DST_COLOR, GL.GL_ZERO);
    gl.glBlendEquationSeparate(GL.GL_FUNC_ADD, GL.GL_FUNC_SUBTRACT);
    gl.glBlendFuncSeparate(GL.GL_ONE, GL.GL_ONE_MINUS_SRC_ALPHA, GL.GL_ONE, GL.GL_ZERO);
    gl.setSwapInterval(1);
    pgl.endGL();
  // end blending setup
   
    
    // rotating logo
    pushMatrix();
    // centre image
    translate(cl.getMWidth()/2+180, cl.getMHeight()/2);
    // rotate
    rotate(radians(frameCount % 360));    
    // scale
    scale(0.8);
    drawLogo();
    popMatrix();

}

void draw(){
}

void drawLogo() {
  pushMatrix();
  color c;
  int offset = 40;

  drawBlue(offset,135);
  drawOrange(offset,-135);
  drawGreen(offset,-45);
  drawMagenta(offset,45);
  popMatrix();
}

void drawBlue(int offset, int angle){
    pgl = (PGraphicsOpenGL) g;
    gl = pgl.beginGL();
    gl.glDisable(GL.GL_DEPTH_TEST);
    gl.glEnable(GL.GL_BLEND);
    gl.glBlendEquationSeparate(GL.GL_FUNC_SUBTRACT, GL.GL_FUNC_SUBTRACT);
    gl.glBlendFuncSeparate(GL.GL_ONE, GL.GL_ONE_MINUS_SRC_ALPHA, GL.GL_ONE, GL.GL_ZERO);
    gl.setSwapInterval(1);
    pgl.endGL();
    
  // blue block and circle
  pushMatrix();
  //rotate and offset
  rotate(radians(angle));
  translate(offset,0);
  // block
  color c = #3e6d97;
  fill(c);
  rect(0,0,200,110);
  // circle
  c = #628291;
  fill(c,200); 
  ellipse(145,125,110,110);
  popMatrix();
}

void drawOrange(int offset, int angle){
    pgl = (PGraphicsOpenGL) g;
    gl = pgl.beginGL();
    gl.glDisable(GL.GL_DEPTH_TEST);
    gl.glEnable(GL.GL_BLEND);
    //gl.glBlendFunc(GL.GL_SRC_COLOR, GL.GL_ZERO);
    gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ZERO);
    //gl.glBlendFunc(GL.GL_DST_COLOR, GL.GL_ZERO);
    gl.glBlendEquationSeparate(GL.GL_FUNC_SUBTRACT, GL.GL_FUNC_ADD);
    gl.glBlendFuncSeparate(GL.GL_ONE, GL.GL_ONE_MINUS_SRC_ALPHA, GL.GL_ONE, GL.GL_ZERO);
    gl.setSwapInterval(1);
    pgl.endGL();
    
  // orange blocks
  pushMatrix();
  // rotate and offset
  rotate(radians(-135));
  translate(offset, 0);
  color c = #d88a26;  
  fill(c, 10);
  rect(0,0,250,150);
  
  c = #c78459;
  fill(c,200);
  rect(-30,70,170,100);
  popMatrix();
}

void drawGreen(int offset, int angle) {
  // green block and white circle
  pushMatrix();
  // rotate and offset
  rotate(radians(-45));
  translate(offset,0);
  color c = #888c37;
  fill(c);
  rect(0,0,200,120);
  
  c = #000000;
  fill(c,255);
  ellipse(140,90,90,90);
  popMatrix();  
}

void drawMagenta(int offset, int angle) {
  // magenta blocks
  pushMatrix();
  // rotate and offset
  rotate(radians(45));
  translate(offset,0);
  color c = #982d45;
  fill(c);
  rect(0,0,180,180);
  
  c = #801f28;
  fill(c,230);
  rect(0,0,120,100);
  popMatrix();  
}
