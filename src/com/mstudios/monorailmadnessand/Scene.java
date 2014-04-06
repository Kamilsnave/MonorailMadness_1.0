package com.mstudios.monorailmadnessand;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Scene {
	public static final int BACKGROUND = 0;
	public static final int COREGROUND = 1;
	public static final int FOREGROUND = 2;
	public static final int ALL = 3;
	
	SceneManager manager;
	String title;
	
	ArrayList<Entity> addable;
	ArrayList<Entity> removable;
	
	ArrayList<Entity> background;
	ArrayList<Entity> coreground;
	ArrayList<Entity> foreground;
	
	public Scene(SceneManager manager, String title){
		this.manager = manager;
		this.title = title;
		
		addable = new ArrayList<Entity>();
		removable = new ArrayList<Entity>();
		
		background = new ArrayList<Entity>();
		coreground = new ArrayList<Entity>();
		foreground = new ArrayList<Entity>();
	}
	
	public void update(float dt, Input input){
		
		//Add Pending Entities
		if(addable.size() > 0){
			for(Entity e1 : addable){
				switch(e1.getLayer()){
				case BACKGROUND:
					background.add(e1);
					e1.addedToScene();
					break;
				case COREGROUND:
					coreground.add(e1);
					e1.addedToScene();
					break;
				case FOREGROUND:
					foreground.add(e1);
					e1.addedToScene();
					break;
				default:
					coreground.add(e1);
					e1.addedToScene();
					break;
				}
			}
		}
		addable.clear();
		
		//Clear Pending Entities
		if(removable.size() > 0){
			for(Entity e1 : removable){
				switch(e1.getLayer()){
				case BACKGROUND:
					if(background.contains(e1)){
						e1.removedFromScene();
						background.remove(e1);
					}
					break;
				case COREGROUND:
					if(coreground.contains(e1)){
						e1.removedFromScene();
						coreground.remove(e1);
					}
					break;
				case FOREGROUND:
					if(foreground.contains(e1)){
						e1.removedFromScene();
						foreground.remove(e1);
					}
					break;
				default:
					if(coreground.contains(e1)){
						e1.removedFromScene();
						coreground.remove(e1);
					}
					break;
				}
			}
			removable.clear();
		}
		
		//Update Entities
		for(Entity e1 : background){
			e1.update(dt, input);
		}
		
		for(Entity e1 : coreground){
			e1.update(dt, input);
		}
		
		for(Entity e1 : foreground){
			e1.update(dt, input);
		}
		
	}
	
	public void render(Canvas canvas, Paint paint){
		for(Entity e1 : background){
			e1.render(canvas, paint);
		}
		
		for(Entity e1 : coreground){
			e1.render(canvas, paint);
		}
		
		for(Entity e1 : foreground){
			e1.render(canvas, paint);
		}
	}
	
	public void addEntity(Entity e1){
		addable.add(e1);
	}
	
	public void removeEntity(Entity e1){
		removable.add(e1);
	}
	
	public String getTitle(){
		return title;
	}
	
	public int getNumEntities(int layer){
		switch(layer){
		case BACKGROUND:
			return background.size();
		case COREGROUND:
			return coreground.size();
		case FOREGROUND:
			return foreground.size();
		case ALL:
			return getNumEntities(BACKGROUND) + getNumEntities(COREGROUND) + getNumEntities(FOREGROUND);
		}
		
		return 0;
	}
	
	public SceneManager getManager(){
		return manager;
	}
	
	public ArrayList<Entity> getLayer(int layer){
		switch(layer){
		case BACKGROUND:
			return background;
		case COREGROUND:
			return coreground;
		case FOREGROUND:
			return foreground;
		}
		
		return null;
	}
	
	public void setAsCurrentScene(){
		
	}
}



