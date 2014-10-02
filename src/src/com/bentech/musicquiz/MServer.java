package com.bentech.musicquiz;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.github.kevinsawicki.http.HttpRequest;
import com.github.kevinsawicki.http.HttpRequest.HttpRequestException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

public class MServer {
	static class RetrieveImageTask extends AsyncTask<String, Void, Bitmap> 
	{
	    protected Bitmap doInBackground(String... ImageName) 
	    {
	        try 
	        {
	        	Bitmap result;
	        	InputStream instream = HttpRequest.get("http://epicabsol.us.to:5041/img/" + ImageName).stream();
	    		result = BitmapFactory.decodeStream(instream);
	    		return result;
	        } 
	        catch (Exception e) 
	        {
	            //this.exception = e;
	            return null;
	        }
	    }
	}
	static class AuthCodeTask extends AsyncTask<String, Void, Boolean>
	{
		
		protected Boolean doInBackground(String... params) {
			try
			{
				String response = HttpRequest.get("").body();
				if (response == "good")
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			catch (Exception e)
			{
				return false;
			}
		}
	}
	public static boolean ValidateCode(String Code)
	{
		try {
			return new AuthCodeTask().execute(Code).get();
		} catch (InterruptedException e) {
			return false;
		} catch (ExecutionException e) {
			return false;
		}
	}
	
	
	public static Bitmap GetImage(String ImageName)
	{
		try {
			return new RetrieveImageTask().execute(ImageName).get();
		} catch (InterruptedException e) {
			return null;
		} catch (ExecutionException e) {
			return null;
		}
	}
	
	public static List<String> GetImageList()
	{
		List<String> result = new ArrayList<String>();
		
		
		return result;
	}
}
