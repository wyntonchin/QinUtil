package com.hisense.hitv.user;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.util.Log;

import com.hismart.base.LogUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class BitmapUtil {
	private final static String TAG = "BitmapUntil";

	public static Bitmap readBitMap(String pathString) {
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;
		opt.inInputShareable = true;
		opt.inSampleSize = computeSampleSize(opt, -1, 128 * 128); // 计算出图片使用的inSampleSize
		opt.inJustDecodeBounds = false;

		Bitmap bitmap = null;  
	    try  {  
	        File file = new File(pathString);  
	        if(file.exists())  {  
	            bitmap = BitmapFactory.decodeFile(pathString, opt);
	        }  
	    } catch (Exception e){
	    	Log.e(TAG, e.toString());
	    }  

		return bitmap;
	}

	public static int computeSampleSize(BitmapFactory.Options options,
			int minSideLength, int maxNumOfPixels) {
		int initialSize = computeInitialSampleSize(options, minSideLength,
				maxNumOfPixels);

		int roundedSize;
		if (initialSize <= 8) {
			roundedSize = 1;
			while (roundedSize < initialSize) {
				roundedSize <<= 1;
			}
		} else {
			roundedSize = (initialSize + 7) / 8 * 8;
		}

		return roundedSize;
	}

	private static int computeInitialSampleSize(BitmapFactory.Options options,
			int minSideLength, int maxNumOfPixels) {
		double w = options.outWidth;
		double h = options.outHeight;

		int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
				.sqrt(w * h / maxNumOfPixels));
		int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(
				Math.floor(w / minSideLength), Math.floor(h / minSideLength));

		if (upperBound < lowerBound) {
			// return the larger one when there is no overlapping zone.
			return lowerBound;
		}

		if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
			return 1;
		} else if (minSideLength == -1) {
			return lowerBound;
		} else {
			return upperBound;
		}
	}

	private static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;
		if (height > reqHeight || width > reqWidth) {
			final int halfHeight = height / 2;
			final int halfWidth = width / 2;
			while ((halfHeight / inSampleSize) > reqHeight
					&& (halfWidth / inSampleSize) > reqWidth) {
				inSampleSize *= 2;
			}
		}
		return inSampleSize;
	}

	// 如果是放大图片，filter决定是否平滑，如果是缩小图片，filter无影�?
	public static Bitmap createScaleBitmap(Bitmap src, int dstWidth,
			int dstHeight) {
		Bitmap dst = Bitmap.createScaledBitmap(src, dstWidth, dstHeight, false);
		/*if (src != dst) { // 如果没有缩放，那么不回收
			src.recycle(); // 释放Bitmap的native像素数组
		}*/
		return dst;
	}

	// 从Resources中加载图�?
	public static Bitmap decodeSampledBitmapFromResource(Resources res,
			int resId, int reqWidth, int reqHeight) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, resId, options); // 读取图片长款
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight); // 计算inSampleSize
		options.inJustDecodeBounds = false;
		Bitmap src = BitmapFactory.decodeResource(res, resId, options); // 载入�?个稍大的缩略�?
		return createScaleBitmap(src, reqWidth, reqHeight); // 进一步得到目标大小的缩略�?
	}

	// 从sd卡上加载图片
	public static Bitmap decodeSampledBitmapFromSd(String pathName, int reqWidth, int reqHeight) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(pathName, options);
		options.inSampleSize = calculateInSampleSize(options, reqWidth,reqHeight);
		options.inPreferredConfig = Bitmap.Config.RGB_565;
		options.inPurgeable = true;
		options.inInputShareable = true;
		options.inJustDecodeBounds = false;
		Bitmap src = BitmapFactory.decodeFile(pathName, options);
		return createScaleBitmap(src, reqWidth, reqHeight);
	}
	
	// 从stream上加载图�?
		public static Bitmap decodeSampledBitmapFromStream(InputStream in, int reqWidth, int reqHeight) {
			final BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			options.inSampleSize = calculateInSampleSize(options, reqWidth,reqHeight);
			options.inPreferredConfig = Bitmap.Config.RGB_565;
			options.inPurgeable = true;
			options.inInputShareable = true;
			options.inJustDecodeBounds = false;
			Bitmap src = BitmapFactory.decodeStream(in); 
			return createScaleBitmap(src, reqWidth, reqHeight);
		}
	
	//生成圆角图片
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		if(width>200){
			width = 200;
			height = 200*height/bitmap.getWidth();
		}
		Bitmap scaleBitmap = createScaleBitmap(bitmap,width,height);
	  Bitmap roundBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888); 
	   Canvas canvas = new Canvas(roundBitmap); 
	   int color = 0xff424242; 
	   Paint paint = new Paint(); 
	   //设置圆形半径
	   int radius; 
	   if(width>height) {
	       radius = height/2;
	   }else {
	       radius = width/2;
	   }
	   //绘制圆形
	   paint.setAntiAlias(true); 
	   canvas.drawARGB(0, 0, 0, 0); 
	   paint.setColor(color); 
	   canvas.drawCircle( width/ 2, height/ 2, radius, paint);
	   paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN)); 
	   canvas.drawBitmap(scaleBitmap, 0, 0, paint);
	   return roundBitmap; 
	}

	public static void saveUserPicToSDCard(Bitmap bitmap, String dir, String imagename) {

		File path = new File(dir);
		if (!path.exists()) {
			path.mkdirs();
		}
		File file = new File(dir + imagename);

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
			fos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	public static void saveBitmapToSDCard(Bitmap bitmap, String type, String dir, String imagename) throws IOException{
        FileOutputStream fos = null;

       	//String sdPath = Environment.getExternalStorageDirectory().getPath();
       	File path = new File(dir);
       	if(!path.exists())
       		path.mkdirs();
       	File file = new File(dir+imagename);

       	if(!file.exists())
              file.createNewFile();
        fos = new FileOutputStream(file);
        if (fos != null) {
          	if(type.equals("png")){
          	   bitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
            }else if(type.equals("jpg")){
          	   bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos);
          	}
            fos.close();
        }
    }
	
	public static void saveBitmapToSDCard(Bitmap bitmap, String dir, String imagename, 
			String type, int length, int width, int size, int mode) throws IOException{     
        FileOutputStream fos = null;
       	//String sdPath = Environment.getExternalStorageDirectory().getPath();
       	File path = new File(dir);  
       	if(!path.exists())  
       		path.mkdirs();
       	File file = new File(dir+imagename);  
        
       	if(!file.exists())  
              file.createNewFile();
        fos = new FileOutputStream(file);   
        if (fos != null) {
        	int w,h;
        	if(mode==1){
        	   	w=width;
        	   	h=length;
        	}else{
        		w=(int)(width*0.9);
        		h=(int)(length*0.9);
        	}
        	
        	if(type != null && type.contains("png")){
        		LogUtil.d(TAG, "PNG file");
        		createScaleBitmap(bitmap, w, h).compress(Bitmap.CompressFormat.PNG, 90, fos);	
        	}else if(type != null && type.contains("jpg")){
        		LogUtil.d(TAG, "JPG file");
        		createScaleBitmap(bitmap, w, h).compress(Bitmap.CompressFormat.JPEG, 90, fos);	
        	}else{
        		createScaleBitmap(bitmap, w, h).compress(Bitmap.CompressFormat.PNG, 90, fos);
        	}
            fos.close();     
        }
    }
	
	/*public static Bitmap getVideoThumbnail(String videoPath, int width, int height){
		Bitmap bitmap = null;
		FFmpegMediaMetadataRetriever fmmr = new FFmpegMediaMetadataRetriever();
        try {
          fmmr.setDataSource(videoPath);
          bitmap = fmmr.getFrameAtTime();
          if (bitmap != null) {
            Bitmap b2 = fmmr.getFrameAtTime(0,FFmpegMediaMetadataRetriever.OPTION_CLOSEST_SYNC);
            if (b2 != null) {
              bitmap = b2;
            }
            if (bitmap.getWidth() > 640) {// 如果图片宽度规格超过640px,则进行压�?
              bitmap = ThumbnailUtils.extractThumbnail(bitmap,width, height,ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
            }
          }
        } catch (IllegalArgumentException ex) {
          ex.printStackTrace();
        } finally {
          fmmr.release();
        }
        return bitmap;
	}*/
}
