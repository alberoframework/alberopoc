package com.alberoframework.sample.issuetracker.service.core.function;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

public class UserFunctions {

	public static String hashPassword(String password) {
		return Hashing.md5().hashString(password, Charsets.UTF_8).toString();
	}
	
}
