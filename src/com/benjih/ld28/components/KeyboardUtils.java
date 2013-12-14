package com.benjih.ld28.components;

import org.lwjgl.input.Keyboard;

public class KeyboardUtils {
	
	public static void resetKeyboard () {
		Keyboard.destroy();
		
		try {
			Keyboard.create();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
	}

	public static boolean isEventUp () {
		return Keyboard.getEventKey() == Keyboard.KEY_W || Keyboard.getEventKey() == Keyboard.KEY_UP;
	}
	
	public static boolean isEventDown () {
		return Keyboard.getEventKey() == Keyboard.KEY_D || Keyboard.getEventKey() == Keyboard.KEY_DOWN;
	}
	
	public static boolean isEventAction () {
		return Keyboard.getEventKey() == Keyboard.KEY_SPACE || Keyboard.getEventKey() == Keyboard.KEY_RETURN;
	}
	
}
