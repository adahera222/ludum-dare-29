package com.benjih.ld28;

import org.lwjgl.opengl.Display;

public class Game {
	
	public static void main (String args[]) throws Exception {
		
		GameDisplay display = new GameDisplay();
		display.init();
		
		new DeveloperSplash(display).run();
		
		while(true) {
			String playerChoice = new MainMenu(display).run();
			
			if (playerChoice == "play" ) {
				//new Level().run();
			} else if (playerChoice == "options" ) {
				//new Options().run();	
			}
			
		}
			
	}
	
}
