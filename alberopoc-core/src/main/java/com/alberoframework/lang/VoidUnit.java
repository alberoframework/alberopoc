package com.alberoframework.lang;

import com.alberoframework.lang.object.BaseObject;

public class VoidUnit extends BaseObject {

	private static VoidUnit INSTANCE = new VoidUnit();
	
	public static VoidUnit instance() {
		return INSTANCE;
	}
	
	private VoidUnit() { }
	
}
