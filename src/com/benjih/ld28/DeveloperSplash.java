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
		boolean running = true;
		
		Sprite splash = new Sprite(0, 0, "res/splash.png");
		long start = display.getTime();
		
		while (running) {
			display.blit();
			
			splash.render();
			
			System.out.println(display.getTime());
			Display.update();
			Display.sync(60);
			
			if (display.getTime() >= start + 3000) {
				running = false;
			}
			
		}
		
	}
	
}