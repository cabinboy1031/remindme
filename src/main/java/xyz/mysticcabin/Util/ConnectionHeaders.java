package xyz.mysticcabin.Util;

import com.google.gson.Gson;

public class ConnectionHeaders {
    private final boolean isObjectData = true;
    private boolean isReadOnly;

    ConnectionHeaders(boolean readOnly) {
        this.isReadOnly = readOnly;
    }

    public boolean isObjectData() {
        return isObjectData;
    }

    public boolean isReadOnly() {
        return isReadOnly;
    }

    public String serializeJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static ConnectionHeaders deserializeFromJson(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, ConnectionHeaders.class);
    }
}
