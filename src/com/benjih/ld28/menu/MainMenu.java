package com.benjih.ld28.menu;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import com.benjih.ld28.GameDisplay;
import com.benjih.ld28.KeyboardUtils;
import com.benjih.ld28.Sprite;

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
		Sprite menuOptions = new Sprite(150, 170, "res/menu_options.png");
		Sprite menuExit = new Sprite(150, 170, "res/menu_exit.png");
		
		ArrayList<MenuChoice> choices = new ArrayList<MenuChoice>();
		
		choices.add(MenuChoice.PLAY);
		choices.add(MenuChoice.OPTIONS);
		choices.add(MenuChoice.EXIT);
		
		int selection = 0;
		
		while(true) {
			
			display.blit();
			
			background.render();
			logo.render();
			
			if (selection == 0) {
				menuPlay.render();
			} else if (selection == 1) {
				menuOptions.render();
			} else {
				menuExit.render();
			}
			
			display.closeIfRequested();
			
			while (Keyboard.next()) {
			    if (Keyboard.getEventKeyState()) {
			        if (KeyboardUtils.isEventUp()) {
						selection--;
						if (selection == -1 ) {
							selection = 2;
						}
			        }
			        if (KeyboardUtils.isEventDown()) {
						selection++;
						if (selection == 3 ) {
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