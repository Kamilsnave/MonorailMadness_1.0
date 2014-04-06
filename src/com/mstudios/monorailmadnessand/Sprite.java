package com.mstudios.monorailmadnessand;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

public class Sprite {
	Bitmap bitmap;
	Rect src;
	RectF dst;
	
	public Sprite(String id){
		bitmap = ResourceManager.getBitmap(id);
		src = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		dst = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
	}
	
	public void crop(int x, int y, int width, int height){
		src = new Rect(x, y, width, height);
	}
	
	public void drawSprite(float x, float y, float width, float height, Canvas canvas, Paint paint){
		dst = new RectF(x, y, width, height);
		paint.setColor(Color.WHITE);
		canvas.drawBitmap(bitmap, src, dst, paint);
	}
	
	public void drawSprite(Vector2f pos, Canvas canvas, Paint paint){
		dst.set(pos.getX(), pos.getY(), pos.getX()+dst.width(), pos.getY()+dst.height());
		paint.setColor(Color.WHITE);
		canvas.drawBitmap(bitmap, src, dst, paint);
	}
	
	public void drawCenteredSprite(Vector2f pos, Canvas canvas, Paint paint){
		Vector2f offset = pos.subtract(new Vector2f(dst.width()/2, dst.height()/2));
		dst.set(offset.getX(), offset.getY(), offset.getX()+dst.width(), offset.getY()+dst.height());
		paint.setColor(Color.WHITE);
		canvas.drawBitmap(bitmap, src, dst, paint);
	}
	
	public void setDimensions(float width, float height){
		dst.set(dst.left, dst.top, dst.left+width, dst.top+height);
	}
	
	public int getBitmapWidth(){
		return bitmap.getWidth();
	}
	
	public int getBitmapHeight(){
		return bitmap.getHeight();
	}
	
	public void scale(float s){
		setDimensions(dst.width()*s, dst.height()*s);
	}
	
	public float getDstWidth(){
		return dst.width();
	}
	
	public float getDstHeight(){
		return dst.height();
	}

}
