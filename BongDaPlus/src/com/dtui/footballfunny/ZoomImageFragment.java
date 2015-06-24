package com.dtui.footballfunny;

import android.app.Activity;
import android.content.Intent;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.dtui.bongdaplus.R;
import com.imagezoom.ImageAttacher;
import com.imagezoom.ImageAttacher.OnMatrixChangedListener;
import com.imagezoom.ImageAttacher.OnPhotoTapListener;
import com.squareup.picasso.Picasso;

public class ZoomImageFragment extends Activity {

	   private ImageView img;

	   @Override
	   protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.fragment_zoom_image);
	      
	      getActionBar().hide();
	      
	      img = (ImageView)findViewById(R.id.zoom_image_funny);
	      
	      Intent i = getIntent();
	      String url = i.getStringExtra("url");
	      
	      Picasso.with(this).load(url).into(img);
	      
	      
	      /*Bitmap bimtBitmap = BitmapFactory.decodeResource(getResources(),
	    		  Picasso.with(this).load(url));
	        img.setImageBitmap(bimtBitmap);*/

	        /**
	         * Use Simple ImageView
	         */
	        usingSimpleImage(img);

	    }

	    public void usingSimpleImage(ImageView imageView) {
	        ImageAttacher mAttacher = new ImageAttacher(imageView);
	        ImageAttacher.MAX_ZOOM = 2.0f; // Double the current Size
	        ImageAttacher.MIN_ZOOM = 0.5f; // Half the current Size
	        MatrixChangeListener mMaListener = new MatrixChangeListener();
	        mAttacher.setOnMatrixChangeListener(mMaListener);
	        PhotoTapListener mPhotoTap = new PhotoTapListener();
	        mAttacher.setOnPhotoTapListener(mPhotoTap);
	    }

	    private class PhotoTapListener implements OnPhotoTapListener {

	        @Override
	        public void onPhotoTap(View view, float x, float y) {
	        }
	    }

	    private class MatrixChangeListener implements OnMatrixChangedListener {

	        @Override
	        public void onMatrixChanged(RectF rect) {

	        }
	    }	}