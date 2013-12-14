package com.benjih.ld28;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class DeveloperSplash {
	
	GameDisplay display;
	
	public DeveloperSplash (GameDisplay display) {
		this.display = display;
	}
	
	public void run () throws Exception {
		boolean isRunning = true;
		
		int i = 0;
		
		Sprite splash = new Sprite(0, 0, "res/splash.png");
		
		while (isRunning) {
			display.blit();
			
			splash.render();
			
			Display.update();
			Display.sync(60);
			
			i++;
			if(i >= 90) {
				isRunning = false;
			}
			
		}
	}

}
