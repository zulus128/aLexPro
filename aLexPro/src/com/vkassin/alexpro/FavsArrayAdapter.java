package com.vkassin.alexpro;

import java.util.ArrayList;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FavsArrayAdapter extends ArrayAdapter<RSSItem> {
	private ArrayList<RSSItem> items;
	private Context ctx;
	private int resourceId;
	
	public FavsArrayAdapter(Context context, int resourceId, ArrayList<RSSItem> objects) {
		super(context, resourceId, objects);
		this.items = objects;
		this.ctx = context;
		this.resourceId = resourceId;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
    	LinearLayout layout = new LinearLayout(parent.getContext());
    	
    	LayoutInflater vi = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    	layout.addView(vi.inflate(resourceId, null));
    	
   		TextView title = (TextView) layout.findViewById(R.id.FavsTextView);
  		
   		title.setText("title");
    		
    	return layout;
    }

	public void setItems(ArrayList<RSSItem> objects) {
		this.items.clear();
		this.items.addAll(objects);
	}
	
	public void addItems(ArrayList<RSSItem> objects) {
		this.items.addAll(objects);
	}

	public ArrayList<RSSItem> getItems() {
		return items;
	}
}
