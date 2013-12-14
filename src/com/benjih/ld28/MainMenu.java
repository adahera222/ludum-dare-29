package com.benjih.ld28;

public class MainMenu {
	
	GameDisplay display;
	
	public MainMenu (GameDisplay display) {
		this.display = display;
	}

	public String run () {
		display.end();
		return null;
	}

}
