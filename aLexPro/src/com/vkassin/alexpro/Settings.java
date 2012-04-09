package com.vkassin.alexpro;

import java.io.Serializable;

public class Settings implements Serializable {

	public Common.paid_type paid;
	
	Settings() {
		
		paid = Common.paid_type.PT_NONE;
	}
}
