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
		
		protected Boolean doInBackground(String... Code) {
			try
			{
				String request = "http://epicabsol.us.to:5041/auth-" + Code[0];
				String response = HttpRequest.get(request).body();
				Log.d("ServerResponse", "Sent Request: " + request + "|AuthCode Response: " + response);
				if (response.contains("good"))
				{
					Log.d("ServerResponse", "Response was good.");
					return true;
				}
				else
				{
					Log.d("ServerResponse", "Response was, \"" + response + "\".");
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
