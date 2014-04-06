package com.mstudios.monorailmadnessand;

import java.util.HashMap;
import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;

public class SceneManager {
	GameView view;
	HashMap<Integer, Scene> sceneList;
	Scene currentScene;
	
	@SuppressLint("UseSparseArrays")
	public SceneManager(GameView view){
		this.view = view;
		sceneList = new HashMap<Integer, Scene>();
	}
	
	
	public void update(float dt, Input input){
		if(hasScene()){
			currentScene.update(dt, input);
		}
	}
	
	public void render(Canvas canvas, Paint paint){
		if(hasScene()){
			currentScene.render(canvas, paint);
		}
	}
	
	public void addScene(Integer id, Scene scene){
		sceneList.put(id, scene);
	}
	
	public void setScene(Integer id){
		if(sceneList.containsKey(id)){
			currentScene = sceneList.get(id);
			currentScene.setAsCurrentScene();
		}
	}
	
	public void removeScene(Integer id){
		if(sceneList.containsKey(id) && sceneList.get(id) != currentScene){
			sceneList.remove(id);
		}
	}
	
	public boolean hasScene(){
		return (currentScene != null);
	}
	
	public Scene getCurrentScene(){
		return currentScene;
	}
	
	public GameView getGameView(){
		return view;
	}

}
