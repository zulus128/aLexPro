package com.vkassin.alexpro;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;

public class DownloadActivity extends ListActivity {

	private static final String TAG = "aLexPro.DownloadActivity"; 
//	private ListView list;
	private DownloadArrayAdapter adapter;
	
	public void onCreate(Bundle icicle) {

		super.onCreate(icicle);
        setContentView(R.layout.favs);
        
//        list = (ListView)this.findViewById(R.id.FavsList);

//        list.setEmptyView(findViewById(R.id.empty));
        
    	ArrayList<RSSItem> items = Common.getFavrs();
		adapter = new DownloadArrayAdapter(this, R.layout.favsitem, items);
//    	list.setAdapter(adapter);
		setListAdapter(adapter);

//    	list.setOnItemClickListener(new OnItemClickListener() {
//			
//			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//				
//			}
//		});
		 
	}

	protected void onResume() {
		
		super.onResume();
		
		adapter.setItems(Common.getFavrs());
		adapter.notifyDataSetChanged();
	}
	
	
}
