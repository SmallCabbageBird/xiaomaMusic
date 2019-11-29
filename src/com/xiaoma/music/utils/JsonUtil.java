package com.xiaoma.music.utils;

import com.google.gson.Gson;

public final class JsonUtil {
	private static Gson gson = new Gson();
	
	private JsonUtil() {}

	public static String toJson(Object obj){
		return gson.toJson(obj);
	}
}
