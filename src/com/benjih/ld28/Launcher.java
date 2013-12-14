package com.benjih.ld28;

import com.benjih.ld28.menu.MainMenu;
import com.benjih.ld28.menu.MenuChoice;

public class Launcher {
	
	public static void main (String args[]) throws Exception {
		
		GameDisplay display = new GameDisplay();
		display.init();
		
		new DeveloperSplash(display).run();
		
		while(true) {
			MenuChoice playerChoice = new MainMenu(display).run();
			
			if (playerChoice == MenuChoice.PLAY) {
				new Game(display).run();
			} else {
				display.end();
			}
			
		}
			
	}
	
}
