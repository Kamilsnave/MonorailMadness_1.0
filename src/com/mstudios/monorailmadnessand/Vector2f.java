package com.mstudios.monorailmadnessand;

public class Vector2f {
	private float x, y;
	
	public Vector2f(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public Vector2f(float mag, Vector2f dir)	{ copy(dir.unit().scale(mag, mag)); }
	public float getX()							{ return x; }
	public float getY()							{ return y; }
	public void set(float mag, Vector2f dir)	{ copy(dir.unit().scale(mag, mag)); }
	public Vector2f unit()						{ return new Vector2f(x/magnitude(), y/magnitude()); }
	public float magnitude()					{ return (float) Math.sqrt(this.dot(this)); }
	public float dot(Vector2f v1)				{ return x*v1.getX() + y*v1.getY(); }
	public Vector2f negate()					{ return scale(-1, -1); }
	public Vector2f add(Vector2f v1)			{ return new Vector2f(x+v1.getX(), y+v1.getY()); }
	public Vector2f subtract(Vector2f v1)		{ return add(v1.negate()); }
	public Vector2f scale(float sx, float sy)	{ return new Vector2f(x*sx, y*sy); }
	public Vector2f abs()						{ return new Vector2f(Math.abs(x), Math.abs(y)); }
	public float distance(Vector2f v1)			{ return (float)Math.sqrt(dot(v1)); }
	
	public void copy(Vector2f v1){
		x = v1.x;
		y = v1.y;
	}
	
	public void set(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public void setX(float x){
		this.x = x;
	}
	
	public void setY(float y){
		this.y = y;
	}

}
