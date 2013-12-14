package com.benjih.ld28.menu;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import com.benjih.ld28.components.GameDisplay;
import com.benjih.ld28.components.KeyboardUtils;
import com.benjih.ld28.components.Sprite;

public class MainMenu {
	
	GameDisplay display;
	
	public MainMenu (GameDisplay display) {
		this.display = display;
	}

	public MenuChoice run () {
		KeyboardUtils.resetKeyboard();
		
		Sprite background = new Sprite(0, 0, "res/background.png");
		Sprite logo = new Sprite(200, 30, "res/logo.png");
		Sprite menuPlay = new Sprite(150, 170, "res/menu_play.png");
		Sprite menuExit = new Sprite(150, 170, "res/menu_exit.png");
		
		ArrayList<MenuChoice> choices = new ArrayList<MenuChoice>();
		
		choices.add(MenuChoice.PLAY);
		choices.add(MenuChoice.EXIT);
		
		int selection = 0;
		
		while(true) {
			
			display.blit();
			
			background.render();
			logo.render();
			
			if (selection == 0) {
				menuPlay.render();
			} else {
				menuExit.render();
			}
			
			display.closeIfRequested();
			
			while (Keyboard.next()) {
			    if (Keyboard.getEventKeyState()) {
			        if (KeyboardUtils.isEventUp()) {
						selection--;
						if (selection == -1 ) {
							selection = 1;
						}
			        }
			        if (KeyboardUtils.isEventDown()) {
						selection++;
						if (selection == 2 ) {
							selection = 0;
						}
			        }
			        if (KeyboardUtils.isEventAction()) {
			        	return choices.get(selection);
			        }
			    }
			}
			
			display.update();
			
		}
		
	}

}