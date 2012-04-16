package com.vkassin.alexpro;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CabinetActivity extends Activity {

	private static final String TAG = "aLexPro.CabinetActivity"; 
	
	public void onCreate(Bundle icicle) {

		super.onCreate(icicle);
		setContentView(R.layout.cabin);
		 
		
	    Button btn3 = (Button) this.findViewById(R.id.button3);
		
	    if (btn3!= null) {
//    	btn2.setWidth(10);
        btn3.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	            	Log.w(TAG, "button3 clicked");
	            }
	        });
	    }
	}
}
