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
		Texture playSelected = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/play-selected.png"));
		Texture playUnselected = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/play-unselected.png"));
		Texture optionsSelected = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/options-selected.png"));
		Texture optionsUnselected = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/options-unselected.png"));
		Texture exitSelected = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/exit-selected.png"));
		Texture exitUnselected = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/exit-unselected.png"));
		
		ArrayList<MenuChoice> choices = new ArrayList<MenuChoice>();
		
		choices.add(MenuChoice.PLAY);
		choices.add(MenuChoice.OPTIONS);
		choices.add(MenuChoice.EXIT);
		
		int selection = 0;
		
		while(true) {
			
			display.blit();
			
			drawBackground();
			drawLogo(logo);
			
			if (selection == 0) {
				drawMenu(playSelected, optionsUnselected, exitUnselected);
			} else if (selection == 1) {
				drawMenu(playUnselected, optionsSelected, exitUnselected);
			} else {
				drawMenu(playUnselected, optionsUnselected, exitSelected);
			}
			
			if (Display.isCloseRequested()) {
				display.end();
			}
			
			while (Keyboard.next()) {
			    if (Keyboard.getEventKeyState()) {
			        if (Keyboard.getEventKey() == Keyboard.KEY_W || Keyboard.getEventKey() == Keyboard.KEY_UP) {
						selection--;
						if (selection == -1 ) {
							selection = 2;
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
		logo.bind();
		GL11.glBegin(GL11.GL_QUADS);
		
		GL11.glTexCoord2f(0,0);
		GL11.glVertex2f(200, 50);
		
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex2f(200 + logo.getTextureWidth(), 50);
		
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex2f(200 + logo.getTextureWidth(), 50 + logo.getTextureHeight());
		
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex2f(200, 50 + logo.getTextureHeight());
		
		GL11.glEnd();
		GL11.glDisable(GL11.GL_TEXTURE_2D); 
	}
	
	private void drawMenu(Texture play, Texture options, Texture exit) {
		new Color(255, 255, 255).bind();
		
		GL11.glEnable(GL11.GL_TEXTURE_2D); 
		play.bind();
		GL11.glBegin(GL11.GL_QUADS);
		
		GL11.glTexCoord2f(0,0);
		GL11.glVertex2f(200, 200);
		
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex2f(200 + play.getTextureWidth(), 200);
		
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex2f(200 + play.getTextureWidth(), 200 + play.getTextureHeight());
		
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex2f(200, 200 + play.getTextureHeight());
		
		GL11.glEnd();
		GL11.glDisable(GL11.GL_TEXTURE_2D); 
		
		
		GL11.glEnable(GL11.GL_TEXTURE_2D); 
		options.bind();
		GL11.glBegin(GL11.GL_QUADS);
		
		GL11.glTexCoord2f(0,0);
		GL11.glVertex2f(200, 300);
		
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex2f(200 + options.getTextureWidth(), 300);
		
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex2f(200 + options.getTextureWidth(), 300 + options.getTextureHeight());
		
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex2f(200, 300 + play.getTextureHeight());
		
		GL11.glEnd();
		GL11.glDisable(GL11.GL_TEXTURE_2D); 
		
		
		GL11.glEnable(GL11.GL_TEXTURE_2D); 
		exit.bind();
		GL11.glBegin(GL11.GL_QUADS);
		
		GL11.glTexCoord2f(0,0);
		GL11.glVertex2f(200, 400);
		
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex2f(200 + exit.getTextureWidth(), 400);
		
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex2f(200 + exit.getTextureWidth(), 400 + options.getTextureHeight());
		
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex2f(200, 400 + exit.getTextureHeight());
		
		GL11.glEnd();
		GL11.glDisable(GL11.GL_TEXTURE_2D); 
	}

}
