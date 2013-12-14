package com.benjih.ld28;

import org.lwjgl.opengl.Display;

public class PlayGame {
	
	GameDisplay display;
	
	public PlayGame (GameDisplay display) {
		this.display = display;
	}
	
	public void run () {
		Sprite background = new Sprite(0, 0, "res/background.png");
		Sprite bow = new Sprite(0, 100, "res/bow.png");
		Arrow arrow = new Arrow();
		
		while(true) {
			
			display.blit();
			
			background.render();
			bow.render();
			arrow.render();
			
			if (Display.isCloseRequested()) {
				display.end();
			}
			
			display.update();
		}
		
	}
	
}
