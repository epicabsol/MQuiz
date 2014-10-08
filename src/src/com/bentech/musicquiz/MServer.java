package com.bentech.musicquiz;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import com.github.kevinsawicki.http.HttpRequest;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

public class MServer {
	//public static final String ServerURL = "http://192.168.1.102:5041";
	public static final String ServerURL = "http://epicabsol.us.to:5041";
	static class RetrieveImageTask extends AsyncTask<String, Void, Bitmap> 
	{
	    protected Bitmap doInBackground(String... ImageName) 
	    {
	    	Log.e("ServerResponse", "1");
	        try 
	        {
	        	Log.d("ServerResponse", "2");
	        	Bitmap result;
	        	Log.d("ServerResponse", "3");
	        	String path = ServerURL + "/img/" + ImageName[0];
	        	InputStream instream = HttpRequest.get(path).stream();
	        	Log.d("ServerResponse", "4|" + instream.available() + "|" + path);
	        	result = BitmapFactory.decodeStream(instream);
	        	Log.d("ServerResponse", "5");
	        	return result;
	        } 
	        catch (Exception e) 
	        {
	        	Log.e("ServerResponse", "Error fetching image: " + e.getMessage());
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
				String request = ServerURL + "/auth-" + Code[0];
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
				Log.e("ServerResponse", "Error authenticating: " + e.getMessage());
				return false;
			}
		}
	}
	
	static class ListImagesTask extends AsyncTask<Void, Void, List<String>>
	{

		@Override
		protected List<String> doInBackground(Void... v) {
			try
			{
				String request = ServerURL + "/imglist";
				String response = HttpRequest.get(request).body();
				List<String> lines = new ArrayList<String>(Arrays.asList(response.split("\\r?\\n")));
				Log.d("ServerResponse", "Server sent " + lines.size() + " image names.");
				return lines;
			}
			catch (Exception e)
			{
				return null;
			}
		}
		
	}
	
	public static boolean ValidateCode(String Code)
	{
		Log.e("HEY", "HEY!!!!!!!!");
		try {
			return new AuthCodeTask().execute(Code).get();
		} catch (InterruptedException e) {
			Log.d("ServerResponse", "Hey2");
			return false;
		} catch (ExecutionException e) {
			Log.d("ServerResponse", "Hey1");
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
		try {
			return new ListImagesTask().execute().get();
		} catch (InterruptedException e) {
			return null;
		} catch (ExecutionException e) {
			return null;
		}
	}
}
