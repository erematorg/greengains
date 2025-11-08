package com.amplitude.api;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class Amplitude {
    static final Map<String, AmplitudeClient> instances = new HashMap();

    @Deprecated
    public static void disableLocationListening() {
        getInstance().disableLocationListening();
    }

    @Deprecated
    public static void enableLocationListening() {
        getInstance().enableLocationListening();
    }

    @Deprecated
    public static void enableNewDeviceIdPerInstall(boolean z2) {
        getInstance().enableNewDeviceIdPerInstall(z2);
    }

    @Deprecated
    public static void endSession() {
    }

    @Deprecated
    public static String getDeviceId() {
        return getInstance().getDeviceId();
    }

    public static AmplitudeClient getInstance() {
        return getInstance((String) null);
    }

    @Deprecated
    public static void initialize(Context context, String str) {
        getInstance().initialize(context, str);
    }

    @Deprecated
    public static void logEvent(String str) {
        getInstance().logEvent(str);
    }

    @Deprecated
    public static void logRevenue(double d2) {
        getInstance().logRevenue(d2);
    }

    @Deprecated
    public static void setOptOut(boolean z2) {
        getInstance().setOptOut(z2);
    }

    @Deprecated
    public static void setSessionTimeoutMillis(long j2) {
        getInstance().setSessionTimeoutMillis(j2);
    }

    @Deprecated
    public static void setUserId(String str) {
        getInstance().setUserId(str);
    }

    @Deprecated
    public static void setUserProperties(JSONObject jSONObject) {
        getInstance().setUserProperties(jSONObject);
    }

    @Deprecated
    public static void startSession() {
    }

    @Deprecated
    public static void uploadEvents() {
        getInstance().uploadEvents();
    }

    @Deprecated
    public static void useAdvertisingIdForDeviceId() {
        getInstance().useAdvertisingIdForDeviceId();
    }

    public static synchronized AmplitudeClient getInstance(String str) {
        AmplitudeClient amplitudeClient;
        synchronized (Amplitude.class) {
            String normalizeInstanceName = Utils.normalizeInstanceName(str);
            Map<String, AmplitudeClient> map = instances;
            amplitudeClient = map.get(normalizeInstanceName);
            if (amplitudeClient == null) {
                amplitudeClient = new AmplitudeClient(normalizeInstanceName);
                map.put(normalizeInstanceName, amplitudeClient);
            }
        }
        return amplitudeClient;
    }

    @Deprecated
    public static void initialize(Context context, String str, String str2) {
        getInstance().initialize(context, str, str2);
    }

    @Deprecated
    public static void logEvent(String str, JSONObject jSONObject) {
        getInstance().logEvent(str, jSONObject);
    }

    @Deprecated
    public static void logRevenue(String str, int i3, double d2) {
        getInstance().logRevenue(str, i3, d2);
    }

    @Deprecated
    public static void setUserProperties(JSONObject jSONObject, boolean z2) {
        getInstance().setUserProperties(jSONObject, z2);
    }

    @Deprecated
    public static void logRevenue(String str, int i3, double d2, String str2, String str3) {
        getInstance().logRevenue(str, i3, d2, str2, str3);
    }
}
