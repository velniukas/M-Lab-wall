//import processing.opengl.*;
//import javax.media.opengl.*;
import java.util.*;

//PGraphicsOpenGL pgl;
//GL gl;

class Logo {
  
    float x = 0;
    float y = 0;
    float xdir = 1;
    float ydir = 1;
    float r = 200;
    
    Logo(float _x, float _y) {
      xdir = random(-5, 5);
      ydir = random(-5, 5);
      x = _x;
      y = _y;
    }
    
    void calc() {
      if (x < 0 || x > cl.getMWidth()) xdir *= -1;
      if (y < 0 || y > cl.getMHeight()) ydir *= -1;
      x += xdir;
      y += ydir;
    }
    
    void display() {
  //    logoBlend();
      
      // rotating logo
      pushMatrix();
      // centre image
      translate(x, y);
      // rotate
      rotate(radians(frameCount % 360));    
      // scale
      scale(0.8);
      drawLogo();
      popMatrix();
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
    
//    void logoBlend() {
//      // blending setup
//      pgl = (PGraphicsOpenGL) g;
//      gl = pgl.beginGL();
//      gl.glDisable(GL.GL_DEPTH_TEST);
//      gl.glEnable(GL.GL_BLEND);
//      //gl.glBlendFunc(GL.GL_SRC_COLOR, GL.GL_ZERO);
//      gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ZERO);
//      //gl.glBlendFunc(GL.GL_DST_COLOR, GL.GL_ZERO);
//      gl.glBlendEquationSeparate(GL.GL_FUNC_ADD, GL.GL_FUNC_SUBTRACT);
//      gl.glBlendFuncSeparate(GL.GL_ONE, GL.GL_ONE_MINUS_SRC_ALPHA, GL.GL_ONE, GL.GL_ZERO);/
//      gl.setSwapInterval(1);
//      pgl.endGL();
//      // end blending setup
//    }
           
    void drawBlue(int offset, int angle){
//        pgl = (PGraphicsOpenGL) g;
//        gl = pgl.beginGL();
//        gl.glDisable(GL.GL_DEPTH_TEST);
//        gl.glEnable(GL.GL_BLEND);
//        gl.glBlendEquationSeparate(GL.GL_FUNC_SUBTRACT, GL.GL_FUNC_SUBTRACT);
//        gl.glBlendFuncSeparate(GL.GL_ONE, GL.GL_ONE_MINUS_SRC_ALPHA, GL.GL_ONE, GL.GL_ZERO);
//        gl.setSwapInterval(1);
//        pgl.endGL();
        
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
//        pgl = (PGraphicsOpenGL) g;
//        gl = pgl.beginGL();
//        gl.glDisable(GL.GL_DEPTH_TEST);
//        gl.glEnable(GL.GL_BLEND);
//        //gl.glBlendFunc(GL.GL_SRC_COLOR, GL.GL_ZERO);
//        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ZERO);
//        //gl.glBlendFunc(GL.GL_DST_COLOR, GL.GL_ZERO);
//        gl.glBlendEquationSeparate(GL.GL_FUNC_SUBTRACT, GL.GL_FUNC_ADD);
//        gl.glBlendFuncSeparate(GL.GL_ONE, GL.GL_ONE_MINUS_SRC_ALPHA, GL.GL_ONE, GL.GL_ZERO);
//        gl.setSwapInterval(1);
//        pgl.endGL();
        
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
}
