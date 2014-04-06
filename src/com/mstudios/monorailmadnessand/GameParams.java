package com.mstudios.monorailmadnessand;

public class GameParams {
	private static Vector2f window;
	private static Vector2f windowCenter;
	
	public static void setWindow(float width, float height){
		window = new Vector2f(width, height);
		windowCenter = new Vector2f(width/2, height/2);
	}
	
	public static Vector2f getWindow(){
		return window;
	}
	
	public static Vector2f getCenter(){
		return windowCenter;
	}

}
