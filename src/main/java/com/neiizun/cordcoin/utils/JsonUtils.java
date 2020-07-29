package com.neiizun.cordcoin.utils;

import com.google.gson.Gson;

public class JsonUtils {
    private final Gson gson = new Gson();

    public String serialize(Object object) {
        return this.gson.toJson(object);
    }

    public Object deserialize(String json, Class<?> objectClass) {
        return this.gson.fromJson(json, objectClass);
    }
}
