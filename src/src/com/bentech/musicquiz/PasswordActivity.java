package com.bentech.musicquiz;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import com.github.kevinsawicki.http.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

public class PasswordActivity extends Activity {
	public static String Code = "";
	public static int CodeLength = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_password);
	}
	
	public void BackPassword(View v)
	{
		CodeLength -= 1;
		if (CodeLength > 0)
		{
			Code = Code.substring(0, CodeLength);
		}
		ProgressBar bar = (ProgressBar) v.findViewById(R.id.progressBar1);
		bar.setProgress(CodeLength);
		v.invalidate();
	}
	
	public void Key0(View v)
	{
		addKey("0", v);
	}
	
	public void Key1(View v)
	{
		addKey("1", v);
	}
	
	public void Key2(View v)
	{
		addKey("2", v);
	}
	
	public void Key3(View v)
	{
		addKey("3", v);
	}
	
	public void Key4(View v)
	{
		addKey("4", v);
	}
	
	public void Key5(View v)
	{
		addKey("5", v);
	}
	
	public void Key6(View v)
	{
		addKey("6", v);
	}
	
	public void Key7(View v)
	{
		addKey("7", v);
	}
	
	public void Key8(View v)
	{
		addKey("8", v);
	}
	
	public void Key9(View v)
	{
		addKey("9", v);
	}
	
	private void addKey(String key, View v)
	{
		if (CodeLength < 4)
		{
			Code = Code + key;
			CodeLength += 1;
		}
		
		ProgressBar bar = (ProgressBar) v.findViewById(R.id.progressBar1);
		bar.setProgress(CodeLength);
		v.invalidate();
	}
	
	private boolean TestCode()
	{
		try
		{
			URL url = new URL("http://epicabsol.us.to:5041/auth-" + Code);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			String result = "";
			
			result = br.readLine();
			if (result == "good")
			{
				Log.d("ServerResult", "Good!!!!!");
			}
			else if (result == "bad")
			{
				Log.d("ServerResult", "BAD!!!!");
			}
			else
			{
				Log.d("ServerResult", result);
			}
			return true;
		}
		catch (Exception ex)
		{
			return false;
		}
	}
	public void SubmitCode(View v)
	{
		if (TestCode())
		{
			Intent HomeIntent = new Intent(this, HomeActivity.class);
	    	startActivity(HomeIntent);
		}
		else
		{
			BackPassword(v);
		}
	}
}
