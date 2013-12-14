package com.benjih.ld28.menu;

import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.benjih.ld28.GameDisplay;

public class MainMenu {
	
	GameDisplay display;
	
	public MainMenu (GameDisplay display) {
		this.display = display;
	}

	public MenuChoice run () throws IOException {
		
		Texture logo = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/logo.png"));
		
		ArrayList<MenuChoice> choices = new ArrayList<MenuChoice>();
		
		choices.add(MenuChoice.PLAY);
		choices.add(MenuChoice.OPTIONS);
		choices.add(MenuChoice.EXIT);
		
		int selection = 0;
		
		while(true) {
			
			display.blit();
			
			drawBackground();
			drawLogo(logo);
			
			if (Display.isCloseRequested()) {
				display.end();
			}
			
			while (Keyboard.next()) {
			    if (Keyboard.getEventKeyState()) {
			        if (Keyboard.getEventKey() == Keyboard.KEY_W || Keyboard.getEventKey() == Keyboard.KEY_UP) {
						selection--;
						if (selection == -1 ) {
							selection = 1;
						}
			        }
			        if (Keyboard.getEventKey() == Keyboard.KEY_D || Keyboard.getEventKey() == Keyboard.KEY_DOWN) {
						selection++;
						if (selection == 3 ) {
							selection = 0;
						}
			        }
			        if (Keyboard.getEventKey() == Keyboard.KEY_SPACE || Keyboard.getEventKey() == Keyboard.KEY_RETURN) {
			        	return choices.get(selection);
			        }
			    }
			}
			
			display.update();
			
		}
		
	}
	
	private void drawBackground () {
		new Color(109, 217, 0).bind();
		
		GL11.glBegin(GL11.GL_QUADS);
		
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex2f(0, 0);
		
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex2f(800, 0);
		
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex2f(800, 600);
		
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex2f(0, 600);
		
		GL11.glEnd();
	}
	
	private void drawLogo (Texture logo) {
		new Color(255, 255, 255).bind();
		
		GL11.glEnable(GL11.GL_TEXTURE_2D); 
		GL11.glBindTexture(0, logo.getTextureID());
		GL11.glBegin(GL11.GL_QUADS);
		
		GL11.glTexCoord2f(0,0);
		GL11.glVertex2f(200, 50);
		
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex2f(200 + logo.getTextureWidth(), 50);
		
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex2f(200 + logo.getTextureWidth(), 50 +logo.getTextureHeight());
		
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex2f(200, 50 + logo.getTextureHeight());
		
		GL11.glEnd();
		GL11.glDisable(GL11.GL_TEXTURE_2D); 
	}

}
