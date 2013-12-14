package com.benjih.ld28;

import org.lwjgl.opengl.Display;

public class Game {
	
	public static void main (String args[]) throws Exception {
		
		GameDisplay display = new GameDisplay();
		display.init();
		
		new DeveloperSplash().run();
			
		Display.destroy();
	}
	
}
