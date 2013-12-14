package com.benjih.ld28.menu;

import com.benjih.ld28.GameDisplay;

public class MainMenu {
	
	GameDisplay display;
	
	public MainMenu (GameDisplay display) {
		this.display = display;
	}

	public MenuChoice run () {
		display.end();
		return null;
	}

}
