package com.vkassin.alexpro;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.AdapterView.OnItemClickListener;

public class DownloadActivity extends Activity {

	private static final String TAG = "aLexPro.DownloadActivity"; 
	private ListView list;
	private DownloadArrayAdapter adapter;
	
	public void onCreate(Bundle icicle) {

		super.onCreate(icicle);
        setContentView(R.layout.favs);
        
        list = (ListView)this.findViewById(R.id.FavsList);

    	ArrayList<RSSItem> items = Common.getFavrs();
		adapter = new DownloadArrayAdapter(this, R.layout.favsitem, items);
    	list.setAdapter(adapter);

    	list.setOnItemClickListener(new OnItemClickListener() {
			
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				
			}
		});
		 
	}
}
