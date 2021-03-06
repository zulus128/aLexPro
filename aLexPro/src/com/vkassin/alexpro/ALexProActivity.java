package com.vkassin.alexpro;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

public class ALexProActivity extends TabActivity {
	
	private static final float textsize = 11.5f;
	
	private void copyFile(InputStream in, OutputStream out) throws IOException {
		
	    byte[] buffer = new byte[1024];
	    int read;
	    while((read = in.read(buffer)) != -1){
	      out.write(buffer, 0, read);
	    }
	}

	/** Called when the activity is first created. */
    @Override
	public void onCreate(Bundle savedInstanceState) {
		
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);

	    
        InputStream in = null;
        OutputStream out = null;
//        AssetManager assetManager = getResources().getAssets();
        try {
//          in = assetManager.open("raw/style.css");
          in = getAssets().open("style.css");
          out = new FileOutputStream(getFilesDir().getAbsolutePath() + File.separator + "style.css");
          copyFile(in, out);
          in.close();
          in = null;
          out.flush();
          out.close();
          out = null;
        } catch(Exception e) {
            Log.e("copy style.css ERROR", e.toString());
            e.printStackTrace();
        }       
	    
	    
	    Common.app_ctx = getApplicationContext();
	    
	    Common.loadFavr();
	    
 //       Common.prepareUserAgent(this);

	    Resources res = getResources(); // Resource object to get Drawables
	    Common.tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Reusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, MainActivity.class);

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = Common.tabHost.newTabSpec("tmain").setIndicator("LexPro",
	                      res.getDrawable(R.drawable.lexpro))
	                  .setContent(intent);
	    Common.tabHost.addTab(spec);

	    // Do the same for the other tabs
	    intent = new Intent().setClass(this, DownloadActivity.class);
	    spec = Common.tabHost.newTabSpec("tfavr").setIndicator("Загруженные",
	                      res.getDrawable(R.drawable.favourites))
	                  .setContent(intent);
	    Common.tabHost.addTab(spec);

	    intent = new Intent().setClass(this, NewsActivity.class);
	    spec = Common.tabHost.newTabSpec("tnews").setIndicator("Новости",
	                      res.getDrawable(R.drawable.news))
	                  .setContent(intent);
	    Common.tabHost.addTab(spec);

	    intent = new Intent().setClass(this, CabinetActivity.class);
	    spec = Common.tabHost.newTabSpec("tcab").setIndicator("Кабинет",
	                      res.getDrawable(R.drawable.contacts))
	                  .setContent(intent);
	    Common.tabHost.addTab(spec);

//	    intent = new Intent().setClass(this, SettActivity.class);
//	    spec = tabHost.newTabSpec("tsett").setIndicator("�?а�?тройки",
//	                      res.getDrawable(R.drawable.gear))
//	                  .setContent(intent);
//	    tabHost.addTab(spec);
	  
	    LinearLayout ll = (LinearLayout) Common.tabHost.getChildAt(0);
	    TabWidget tw = (TabWidget) ll.getChildAt(0);
	    RelativeLayout rllf = (RelativeLayout) tw.getChildAt(0);
//	    rllf.setBackgroundColor(0xFF0000FF);
	    TextView lf = (TextView) rllf.getChildAt(1);
	    lf.setTextSize(textsize);
	    lf.setPadding(0, 0, 0, 2);
	    rllf = (RelativeLayout) tw.getChildAt(1);
//	    rllf.setBackgroundColor(0xFF0000FF);
	    lf = (TextView) rllf.getChildAt(1);
	    lf.setTextSize(textsize);
//	    lf.setLines(2);
//	    lf.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
	    lf.setPadding(0, 0, 0, 2);
	    rllf = (RelativeLayout) tw.getChildAt(2);
//	    rllf.setBackgroundColor(0xFF0000FF);
	    lf = (TextView) rllf.getChildAt(1);
	    lf.setTextSize(textsize);
	    lf.setPadding(0, 0, 0, 2);
	    rllf = (RelativeLayout) tw.getChildAt(3);
//	    rllf.setBackgroundColor(0xFF0000FF);
	    lf = (TextView) rllf.getChildAt(1);
	    lf.setTextSize(textsize);
	    lf.setPadding(0, 0, 0, 2);

//	    rllf = (RelativeLayout) tw.getChildAt(4);
////	    rllf.setBackgroundColor(0xFF0000FF);
//	    lf = (TextView) rllf.getChildAt(1);
//	    lf.setTextSize(textsize);
//	    lf.setPadding(0, 0, 0, 2);
	    
	    Common.tabHost.setCurrentTab(0);
	}
}