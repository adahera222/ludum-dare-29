package com.benjih.ld28.game;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.benjih.ld28.components.CollidableObject;

public class Arrow {
	private int x, y;
	private Texture resource;
	
	public Arrow () {
		this.x = 20;
		this.y = 274;
		
		try {
			this.resource = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/arrow.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void render () {
		new Color(255, 255, 255).bind();
		
		GL11.glEnable(GL11.GL_TEXTURE_2D); 
		resource.bind();
		GL11.glBegin(GL11.GL_QUADS);
		
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex2f(x, y);
		
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex2f(x + resource.getTextureWidth(), y);
		
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex2f(x + resource.getTextureWidth(), y + resource.getTextureHeight());
		
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex2f(x, y + resource.getTextureHeight());
		
		GL11.glEnd();
		GL11.glDisable(GL11.GL_TEXTURE_2D); 
	}
	
	public void increaseHeight (float speed, int delta) {
		if (y > 0) {
			y = y - (int) Math.floor((speed / 2) * delta);
		}
	}
	
	public void decreaseHeight (float speed, int delta) {
		if (y < 600) {
			y = y + (int) Math.floor((speed / 2) * delta);
		}
	}
	
	public boolean hasHitGround () {
		return y >= 600;
	}
	
	public int getX () {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY () {
		return y;
	}

	public boolean collidesWith(CollidableObject object) {
		if (y >= object.getY() &&
				y <= object.getY() + object.getHeight() &&
				x + resource.getImageWidth() >= object.getX() &&
				x + resource.getImageWidth() <= object.getX() + object.getWidth()) {
			return true;
		}
		return false;
	}
	
 }