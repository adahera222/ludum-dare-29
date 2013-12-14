package com.benjih.ld28;

import com.benjih.ld28.menu.MainMenu;
import com.benjih.ld28.menu.MenuChoice;

public class Game {
	
	public static void main (String args[]) throws Exception {
		
		GameDisplay display = new GameDisplay();
		display.init();
		
//		new DeveloperSplash(display).run();
		
		while(true) {
			MenuChoice playerChoice = new MainMenu(display).run();
			
			if (playerChoice == MenuChoice.PLAY) {
				new PlayGame(display).run();
			} else if (playerChoice == MenuChoice.OPTIONS ) {
				//new Options().run();
				display.end();
			} else {
				display.end();
			}
			
		}
			
	}
	
}
