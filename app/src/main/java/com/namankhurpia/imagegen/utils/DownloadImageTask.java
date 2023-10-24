package com.namankhurpia.imagegen.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

import java.io.InputStream;

public class DownloadImageTask extends AsyncTask<String, LottieAnimationView, Bitmap> {
    ImageView bmImage;
    LottieAnimationView animationView;

    @Override
    protected void onPreExecute() {
        //super.onPreExecute();
        //animationView.setVisibility(View.VISIBLE);
    }

    public DownloadImageTask(ImageView bmImage, LottieAnimationView animationView)
    {
        this.bmImage = bmImage;
        this.animationView = animationView;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try
        {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        }
        catch (Exception e)
        {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
        animationView.setVisibility(View.INVISIBLE);
    }

}