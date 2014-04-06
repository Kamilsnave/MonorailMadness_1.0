package com.mstudios.monorailmadnessand;

import java.util.HashMap;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.util.LruCache;

public class ResourceManager {

	static int maxMemory;
	static int cacheSize;
	static LruCache<String, Bitmap> lruCache;
	static HashMap<String, MediaPlayer> mediaCache;
	static MediaPlayer tempPlayer;

	public static void loadResources(GameView view){
		maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
		cacheSize = maxMemory / 8;
		lruCache = new LruCache<String, Bitmap>(cacheSize);
		mediaCache = new HashMap<String, MediaPlayer>();

		//List of needed Images
		addBitmapToMemoryCache("MSTUDIOS_LOGO", decodeSampledBitmapFromResource(view.getResources(), R.drawable.mstudioslogo, 1024, 512));
		addBitmapToMemoryCache("SAND", decodeSampledBitmapFromResource(view.getResources(), R.drawable.sand, 1024, 512));
		
		addBitmapToMemoryCache("MONORAILCAT", decodeSampledBitmapFromResource(view.getResources(), R.drawable.monorailcat, 250, 125));
		addBitmapToMemoryCache("LONGCAT", decodeSampledBitmapFromResource(view.getResources(), R.drawable.longcat, 100, 200));
		addBitmapToMemoryCache("HOVERCAT", decodeSampledBitmapFromResource(view.getResources(), R.drawable.hovercat, 180, 100));
		addBitmapToMemoryCache("NINJACAT", decodeSampledBitmapFromResource(view.getResources(), R.drawable.ninjacat, 100, 100));
		addBitmapToMemoryCache("ENEMYFURBALL", decodeSampledBitmapFromResource(view.getResources(), R.drawable.enemyfurball, 20, 20));
		addBitmapToMemoryCache("FURBALL", decodeSampledBitmapFromResource(view.getResources(), R.drawable.furball, 20, 20));
		addBitmapToMemoryCache("TACGNOL", decodeSampledBitmapFromResource(view.getResources(), R.drawable.tacgnol, 100, 200));
		addBitmapToMemoryCache("NYANCAT", decodeSampledBitmapFromResource(view.getResources(), R.drawable.nyancat, 558, 188));
		addBitmapToMemoryCache("GRUMPYCAT", decodeSampledBitmapFromResource(view.getResources(), R.drawable.grumpycat, 300, 387));
		addBitmapToMemoryCache("CHEESEBURGER", decodeSampledBitmapFromResource(view.getResources(), R.drawable.burger, 100, 50));
		addBitmapToMemoryCache("TREE0", decodeSampledBitmapFromResource(view.getResources(), R.drawable.tree0, 500, 323));
		addBitmapToMemoryCache("TREE1", decodeSampledBitmapFromResource(view.getResources(), R.drawable.tree1, 307, 500));
		addBitmapToMemoryCache("TREE2", decodeSampledBitmapFromResource(view.getResources(), R.drawable.tree2, 500, 352));
		addBitmapToMemoryCache("BURJ", decodeSampledBitmapFromResource(view.getResources(), R.drawable.burj, 105, 500));
		addBitmapToMemoryCache("LEANING", decodeSampledBitmapFromResource(view.getResources(), R.drawable.leaning, 209, 500));
		addBitmapToMemoryCache("MONORAIL", decodeSampledBitmapFromResource(view.getResources(), R.drawable.monorail, 600, 20));
		addBitmapToMemoryCache("CEILINGCAT", decodeSampledBitmapFromResource(view.getResources(), R.drawable.ceilingcat, 226, 145));
		addBitmapToMemoryCache("UBHACKING", decodeSampledBitmapFromResource(view.getResources(), R.drawable.ubhacking, 303, 114));
		addBitmapToMemoryCache("PROPHETCAT", decodeSampledBitmapFromResource(view.getResources(), R.drawable.prophetcat, 410, 293));
		addBitmapToMemoryCache("BOREDCAT", decodeSampledBitmapFromResource(view.getResources(), R.drawable.boredcat, 600,450));
		addBitmapToMemoryCache("CATNAROK", decodeSampledBitmapFromResource(view.getResources(), R.drawable.catnarok, 500, 375));
		addBitmapToMemoryCache("STORYMONORAIL", decodeSampledBitmapFromResource(view.getResources(), R.drawable.storymonorail, 434, 298));
		
		
		mediaCache.put("THEME", MediaPlayer.create(view.getContext(), R.raw.theme));
		mediaCache.put("NYANCAT_AUDIO", MediaPlayer.create(view.getContext(), R.raw.nyancat_audio));
		mediaCache.put("CHEESEBURGER", MediaPlayer.create(view.getContext(), R.raw.burger));
		mediaCache.put("DEATH0", MediaPlayer.create(view.getContext(), R.raw.death1));
		mediaCache.put("DEATH1", MediaPlayer.create(view.getContext(), R.raw.death2));
		mediaCache.put("DEATH2", MediaPlayer.create(view.getContext(), R.raw.death3));
		mediaCache.put("DEATH3", MediaPlayer.create(view.getContext(), R.raw.death4));
		mediaCache.put("DEATH4", MediaPlayer.create(view.getContext(), R.raw.death5));
		mediaCache.put("DEATH5", MediaPlayer.create(view.getContext(), R.raw.death6));
		mediaCache.put("DEATH6", MediaPlayer.create(view.getContext(), R.raw.grumpycatdeath));
	}
	
	public static MediaPlayer getAudio(String name){
		return mediaCache.get(name);
	}

	public static int calculateInSampleSize(
			BitmapFactory.Options options, int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			// Calculate ratios of height and width to requested height and width
			final int heightRatio = Math.round((float) height / (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);

			// Choose the smallest ratio as inSampleSize value, this will guarantee
			// a final image with both dimensions larger than or equal to the
			// requested height and width.
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}

		return inSampleSize;
	}

	public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
			int reqWidth, int reqHeight) {

		// First decode with inJustDecodeBounds=true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, resId, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeResource(res, resId, options);
	}

	private static int sizeOf(Bitmap bitmap){
		return bitmap.getByteCount()/1024;
	}

	private static void addBitmapToMemoryCache(String key, Bitmap bitmap) {
		if (getBitmapFromMemCache(key) == null) {
			lruCache.put(key, bitmap);
		}
	}

	private static Bitmap getBitmapFromMemCache(String key) {
		return lruCache.get(key);
	}
	
	public static Bitmap getBitmap(String id){
		return getBitmapFromMemCache(id);
	}

	/*public static Bitmap getImage(String id){
		return imageMap.get(id);
	}*/

}
