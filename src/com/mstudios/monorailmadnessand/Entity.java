package com.mstudios.monorailmadnessand;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.MediaPlayer;

public class Entity {
	
	public static float R = 10f;	//Rounded Rect Radius
	
	Scene scene;
	int layer;
	String type;

	Vector2f dis, vel, acc;
	Vector2f size;
	
	Vector2f hitBoxOffset;
	Vector2f hitBoxSize;
	
	public MediaPlayer mediaPlayer;
	
	public Entity(Scene scene, int layer){
		this.scene = scene;
		this.layer = layer;
		
		dis		= new Vector2f(0.0f, 0.0f);
		vel		= new Vector2f(0.0f, 0.0f);
		acc 	= new Vector2f(0.0f, 0.0f);
		size 	= new Vector2f(100.0f, 100.0f);
		hitBoxOffset = new Vector2f(0.0f, 0.0f);
		hitBoxSize = size;
		type = "DEFAULT";
	}
	
	public void update(float dt, Input input){
		handleInput(input);
		handleMotion(dt);
		handleWorldBoundaries();
		checkCollision();
	}
	
	public void render(Canvas canvas, Paint paint){
		paint.setColor(Color.GREEN);
		canvas.drawRect(getHitBox(), paint);
	}
	
	public void addedToScene(){}
	public void removedFromScene(){}
	public void handleInput(Input input){}
	public void handleWorldBoundaries(){}

	public void handleMotion(float dt){
		vel = vel.add(acc.scale(dt, dt));
		dis = dis.add(vel.scale(dt, dt));
	}
	
	private void checkCollision(){
		for(Entity e1 : scene.getLayer(layer)){
			if(RectF.intersects(getHitBox(), e1.getHitBox())){
				handleCollision(e1);
			}
		}
	}
	
	public void handleCollision(Entity e1){}
	public void kill(){};
	
	
	//Getters
	public int getLayer()				{ return layer; }
	public String getType()				{ return type; }
	public Vector2f getDisplacement()	{ return dis; }
	public Vector2f getVelocity()		{ return vel; }
	public Vector2f getAcceleration()	{ return acc; }
	public Vector2f getSize()			{ return size; }
	
	public RectF	getHitBox()			{ 
		RectF hitBox = new RectF(dis.getX() + hitBoxOffset.getX(), 
				dis.getY() + hitBoxOffset.getY(), 
				dis.getX() + hitBoxOffset.getX() + hitBoxSize.getX(),
				dis.getY() + hitBoxOffset.getY() + hitBoxSize.getY());
		
		return hitBox;
	}
	
	//Setters
	public void	setDisplacement(Vector2f dis)	{ this.dis = dis; }
	public void setVelocity(Vector2f vel)		{ this.vel = vel; }
	public void setAcceleration(Vector2f acc)	{ this.acc = acc; }
	public void setSize(Vector2f size)			{ this.size = size; }
	public void setHitBoxOffset(Vector2f offx)	{ hitBoxOffset = offx; }
	public void setHitBoxSize(Vector2f size)	{ hitBoxSize = size; }
	public void setType(String type)			{ this.type = type; }
}
