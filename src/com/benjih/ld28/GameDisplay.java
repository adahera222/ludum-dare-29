package com.benjih.ld28;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class GameDisplay {
	
	public GameDisplay () {
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.setFullscreen(false);
			Display.setTitle("One Shot");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void init () {

		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);       
		 
		GL11.glViewport(0,0,800,600);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		 
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 600, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
	public void blit () {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}
	
	public void update () {
		GL11.glDisable(GL11.GL_BLEND);
		Display.update();
		Display.sync(60);
	}
	
	public void end () {
		Display.destroy();
		System.exit(0);
	}

}
