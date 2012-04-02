package com.vkassin.alexpro;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

public class ALexProActivity extends TabActivity {
	
	private static final float textsize = 11.5f;
	
    /** Called when the activity is first created. */
    @Override
	public void onCreate(Bundle savedInstanceState) {
		
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);

	    Common.app_ctx = getApplicationContext();
	    
 //       Common.prepareUserAgent(this);

	    Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Reusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, MainActivity.class);

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("tmain").setIndicator("LexPro",
	                      res.getDrawable(R.drawable.lexpro))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    // Do the same for the other tabs
	    intent = new Intent().setClass(this, DownloadActivity.class);
	    spec = tabHost.newTabSpec("tfavr").setIndicator("Загруженные",
	                      res.getDrawable(R.drawable.favourites))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    intent = new Intent().setClass(this, NewsActivity.class);
	    spec = tabHost.newTabSpec("tnews").setIndicator("Новости",
	                      res.getDrawable(R.drawable.news))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    intent = new Intent().setClass(this, CabinetActivity.class);
	    spec = tabHost.newTabSpec("tcab").setIndicator("Кабинет",
	                      res.getDrawable(R.drawable.contacts))
	                  .setContent(intent);
	    tabHost.addTab(spec);

//	    intent = new Intent().setClass(this, SettActivity.class);
//	    spec = tabHost.newTabSpec("tsett").setIndicator("�?а�?тройки",
//	                      res.getDrawable(R.drawable.gear))
//	                  .setContent(intent);
//	    tabHost.addTab(spec);
	  
	    LinearLayout ll = (LinearLayout) tabHost.getChildAt(0);
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
	    
	    tabHost.setCurrentTab(0);
	}
}