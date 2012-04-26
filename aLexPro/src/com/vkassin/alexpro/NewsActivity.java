package com.vkassin.alexpro;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
//import android.widget.ProgressBar;
//import android.widget.Toast;

import com.vkassin.alexpro.NewsArrayAdapter;
import com.vkassin.alexpro.Common.item_type;

public class NewsActivity extends Activity {

	private static final String TAG = "aLexPro.NewsActivity"; 
	private ListView list;
	private NewsArrayAdapter adapter;
//	private RSSItem topitem;
	private ProgressBar pb;
//	private TextView refreshText;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.news);
        
        list = (ListView)this.findViewById(R.id.NewsList);
        pb = (ProgressBar)findViewById(R.id.ProgressBar01);
//        refreshText = (TextView)findViewById(R.id.TopTextRefr);
        
    	adapter = new NewsArrayAdapter(this, R.layout.newsitem, new ArrayList<RSSItem>());
    	list.setAdapter(adapter);
    	
    	list.setOnItemClickListener(new OnItemClickListener() {
			
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				
//				if(Common.topnews == null)
//					return;
				Common.news = adapter.getItems();
				Common.curnews = adapter.getItems().get(arg2);
				Intent i = new Intent(NewsActivity.this, NewsDetail.class);
//				RSSItem it = adapter.getItems().get(arg2);
//				i.putExtra("rssitem", it);
//				i.putExtra("topitem", topitem);
//				i.putExtra("itemlist", adapter.getItems());
				NewsDetail.prepare();
				startActivity(i);
				//Log.i(TAG, "row: "+arg2+" arg3: "+arg3);
			}
		});
    	    
//    	Locale locale = new Locale("us");
//    	Locale.setDefault(locale);
//    	Configuration config = new Configuration();
//    	config.locale = locale;
//    	getBaseContext().getResources().updateConfiguration(config,
//    	      getBaseContext().getResources().getDisplayMetrics());
    	
    	refresh();
    	
    }
    
    private void refresh() {
    	
		pb.setVisibility(View.VISIBLE);

    	new getRSS().execute();
    	
//    	NewsDetail.resetViewsList();

    }
    
    private class getRSS extends AsyncTask<Context, Integer, ArrayList<RSSItem>> {

    	@Override
		protected ArrayList<RSSItem> doInBackground(Context... params) {
    					
    		ArrayList<RSSItem> rssItems = Common.getNews();
            return rssItems;
		}
    	
        protected void onProgressUpdate(Integer... progress) {
        //    ProgressBar mProgress = (ProgressBar)NewsActivity.this.findViewById(R.id.progressBar1);
        //    mProgress.setProgress(progress[0]);
        }

        protected void onPostExecute(final ArrayList<RSSItem> result) {

    		pb.setVisibility(View.GONE);
    		
//    		String sformat = "Обновлено MM.dd в HH:mm";
//    		
//    		Calendar cal = Calendar.getInstance();
//    		SimpleDateFormat sdf = new SimpleDateFormat(sformat);
//    		refreshText.setText(sdf.format(cal.getTime()));
        	adapter.setItems(result);
			adapter.notifyDataSetChanged();
        }
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.newsmenu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.refreshitem: {
	        	
	        	this.refresh();
                break;
	        }
	    }
	    return true;
	}    		
}
