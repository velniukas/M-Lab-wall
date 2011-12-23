package com.messina.mlab.wall;

import processing.core.PApplet;

//PGraphicsOpenGL pgl;
//GL gl;

public class Logo {
	PApplet parent;

	Logo(PApplet p) {
		parent = p;
	}

	float x = 0;
	float y = 0;
	float xdir = 1;
	float ydir = 1;
	float r = 200;

	Logo(PApplet p, float _x, float _y) {
		parent = p;
		xdir = parent.random(-5, 5);
		ydir = parent.random(-5, 5);
		x = _x;
		y = _y;
	}

	public void calc() {
		if (x < 0 || x > ((mlabwall) parent).cl.getMWidth())
			xdir *= -1;
		if (y < 0 || y > ((mlabwall) parent).cl.getMHeight())
			ydir *= -1;
		x += xdir;
		y += ydir;
	}

	public void display() {
		// logoBlend();

		// rotating logo
		parent.pushMatrix();
		// centre image
		parent.translate(x, y);
		// rotate
		parent.rotate(PApplet.radians(parent.frameCount % 360));
		// scale
		parent.scale(0.8f);
		drawLogo();
		parent.popMatrix();
	}

	void drawLogo() {
		parent.pushMatrix();
		int offset = 40;

		drawBlue(offset, 135);
		drawOrange(offset, -135);
		drawGreen(offset, -45);
		drawMagenta(offset, 45);
		parent.popMatrix();
	}

	// void logoBlend() {
	// // blending setup
	// pgl = (PGraphicsOpenGL) g;
	// gl = pgl.beginGL();
	// gl.glDisable(GL.GL_DEPTH_TEST);
	// gl.glEnable(GL.GL_BLEND);
	// //gl.glBlendFunc(GL.GL_SRC_COLOR, GL.GL_ZERO);
	// gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ZERO);
	// //gl.glBlendFunc(GL.GL_DST_COLOR, GL.GL_ZERO);
	// gl.glBlendEquationSeparate(GL.GL_FUNC_ADD, GL.GL_FUNC_SUBTRACT);
	// gl.glBlendFuncSeparate(GL.GL_ONE, GL.GL_ONE_MINUS_SRC_ALPHA, GL.GL_ONE,
	// GL.GL_ZERO);/
	// gl.setSwapInterval(1);
	// pgl.endGL();
	// // end blending setup
	// }

	// blue block and circle
	void drawBlue(int offset, int angle) {
		parent.pushMatrix();
		// rotate and offset
		parent.rotate(PApplet.radians(angle));
		parent.translate(offset, 0);
		// block
		int c = parent.color(62, 109, 151);
		parent.fill(c);
		parent.rect(0, 0, 200, 110);
		// circle
		c = parent.color(98, 130, 145);
		parent.fill(c, 200);
		parent.ellipse(145, 125, 110, 110);
		parent.popMatrix();
	}

	// orange blocks
	void drawOrange(int offset, int angle) {
		parent.pushMatrix();
		// rotate and offset
		parent.rotate(PApplet.radians(-135));
		parent.translate(offset, 0);
		int c = parent.color(216, 138, 38);
		parent.fill(c, 100);
		parent.rect(0, 0, 250, 150);

		c = parent.color(199, 132, 89);
		parent.fill(c, 200);
		parent.rect(-30, 70, 170, 100);
		parent.popMatrix();
	}

	void drawGreen(int offset, int angle) {
		// green block and white circle
		parent.pushMatrix();
		// rotate and offset
		parent.rotate(PApplet.radians(-45));
		parent.translate(offset, 0);
		int c = parent.color(136, 140, 55);
		parent.fill(c);
		parent.rect(0, 0, 200, 120);

		c = parent.color(255, 255, 255);
		parent.fill(c, 255);
		parent.ellipse(140, 90, 90, 90);
		parent.popMatrix();
	}

	void drawMagenta(int offset, int angle) {
		// magenta blocks
		parent.pushMatrix();
		// rotate and offset
		parent.rotate(PApplet.radians(45));
		parent.translate(offset, 0);
		int c = parent.color(152, 45, 69);
		parent.fill(c);
		parent.rect(0, 0, 180, 180);

		c = parent.color(128, 31, 40);
		parent.fill(c, 230);
		parent.rect(0, 0, 120, 100);
		parent.popMatrix();
	}
}
