package com.benjih.ld28;

import com.benjih.ld28.components.GameDisplay;
import com.benjih.ld28.game.Game;
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
				new Cinematic(display).run();
				new Game(display).run();
			} else {
				display.end();
			}
			
		}
			
	}
	
}
