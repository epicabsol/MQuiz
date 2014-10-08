package com.bentech.musicquiz;

import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Gallery;
import android.widget.ImageView;

public class SetupActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup);
		UpdateImages();
	}
	
	public static int ImageIndex = 0;
	
	void UpdateImages()
	{
		ImageView g  = (ImageView) findViewById(R.id.imageView1);
		List<String> ImageNames = MServer.GetImageList();
		//Bitmap b = MServer.GetImage(ImageNames.get(ImageIndex));
		Bitmap b = MServer.GetImage(ImageNames.get(0));
		if (b == null)
		{
			Log.e("ServerResponse", "Test Image was null!!!");
		}
		else
		{
			g.setImageBitmap(b);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setup, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
