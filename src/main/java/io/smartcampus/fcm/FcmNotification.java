package io.smartcampus.fcm;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Created by jmichalik on 05.06.17
 */
public class FcmNotification {
    private String to;
    @SerializedName("data")
    private Map<String, String> data;
    private String createdAt;

    public FcmNotification(String to, Map<String, String> data) {
        this.to = to;
        this.data = data;
    }

    public String getTo() {
        return to;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        this.data.put("createdAt", createdAt);
    }
}
