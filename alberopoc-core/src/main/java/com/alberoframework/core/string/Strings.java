package com.alberoframework.core.string;

import java.text.Normalizer;
import java.text.Normalizer.Form;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Objects;

public class Strings {

	public static String upperCaseFirstLetter(String original) {
	    if (original == null || original.length() == 0) {
	        return original;
	    }
	    return original.substring(0, 1).toUpperCase() + original.substring(1);
	}
	
	public static String lowerCaseFirstLetter(String original) {
	    if (original == null || original.length() == 0) {
	        return original;
	    }
	    return original.substring(0, 1).toLowerCase() + original.substring(1);
	}
	
	public static String normalize(String s) {
        if (s == null) return null;
        return Normalizer.normalize(s.toLowerCase(), Form.NFD).replaceAll("[^a-z]", "");
    }
	
	public static boolean equalsTrimNormalized(String s1, String s2) {
		return Objects.equal(normalize(StringUtils.trim(s1)), normalize(StringUtils.trim(s2)));
	}
	
	public static boolean equalsTrimIgnoreCase(String s1, String s2) {
		if (s1 == null && s2 == null) return true;
		if (s1 == null && s2 != null) return false;
		if (s1 != null && s2 == null) return false;
		return s1.trim().equalsIgnoreCase(s2.trim());
	}
	
	public static boolean equalsIgnoreCase(String s1, String s2) {
		if (s1 == null && s2 == null) return true;
		if (s1 == null && s2 != null) return false;
		if (s1 != null && s2 == null) return false;
		return s1.equalsIgnoreCase(s2);
	}
	
	public static boolean containsOnlyDigits(String s1) {
		return s1.chars().allMatch(ch -> Character.isDigit(ch));
	}
	
}
