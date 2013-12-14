package com.benjih.ld28;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Game {

	public static void main (String args[]) throws LWJGLException {
		
		Display.setDisplayMode(new DisplayMode(800, 600));
		Display.setFullscreen(false);
		Display.create();
		
		boolean isRunning = true;
		while(isRunning == true) {
			Display.update();
			isRunning = !Display.isCloseRequested();
		}
		
		Display.destroy();
	}
	
}
