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
		
		Texture splash = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/splash.png"));
		
		while (isRunning) {
			display.blit();
			
			Color.white.bind();
			
			GL11.glEnable(GL11.GL_TEXTURE_2D); 
			splash.bind();
			GL11.glBegin(GL11.GL_QUADS);
			
			GL11.glTexCoord2f(0, 0);
			GL11.glVertex2f(0, 0);
			
			GL11.glTexCoord2f(1, 0);
			GL11.glVertex2f(splash.getTextureWidth(), 0);
			
			GL11.glTexCoord2f(1, 1);
			GL11.glVertex2f(splash.getTextureWidth(), splash.getTextureHeight());
			
			GL11.glTexCoord2f(0, 1);
			GL11.glVertex2f(0, splash.getTextureHeight());
			
			GL11.glEnd();
			
			GL11.glDisable(GL11.GL_TEXTURE_2D); 
			
			Display.update();
			Display.sync(60);
			
			i++;
			if(i >= 90) {
				isRunning = false;
			}
			
		}
	}

}
