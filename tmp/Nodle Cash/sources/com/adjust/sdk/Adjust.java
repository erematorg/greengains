package com.adjust.sdk;

import android.content.Context;
import android.net.Uri;
import java.util.Map;
import org.json.JSONObject;

public class Adjust {
    private static AdjustInstance defaultInstance;

    private Adjust() {
    }

    public static void addSessionCallbackParameter(String str, String str2) {
        getDefaultInstance().addSessionCallbackParameter(str, str2);
    }

    public static void addSessionPartnerParameter(String str, String str2) {
        getDefaultInstance().addSessionPartnerParameter(str, str2);
    }

    @Deprecated
    public static void appWillOpenUrl(Uri uri) {
        getDefaultInstance().appWillOpenUrl(uri);
    }

    public static void disableThirdPartySharing(Context context) {
        getDefaultInstance().disableThirdPartySharing(extractApplicationContext(context));
    }

    private static Context extractApplicationContext(Context context) {
        if (context == null) {
            return null;
        }
        return context.getApplicationContext();
    }

    public static void gdprForgetMe(Context context) {
        getDefaultInstance().gdprForgetMe(extractApplicationContext(context));
    }

    public static String getAdid() {
        return getDefaultInstance().getAdid();
    }

    public static String getAmazonAdId(Context context) {
        Context extractApplicationContext = extractApplicationContext(context);
        if (extractApplicationContext != null) {
            return DeviceInfo.getFireAdvertisingIdBypassConditions(extractApplicationContext.getContentResolver());
        }
        return null;
    }

    public static AdjustAttribution getAttribution() {
        return getDefaultInstance().getAttribution();
    }

    public static synchronized AdjustInstance getDefaultInstance() {
        AdjustInstance adjustInstance;
        synchronized (Adjust.class) {
            try {
                if (defaultInstance == null) {
                    defaultInstance = new AdjustInstance();
                }
                adjustInstance = defaultInstance;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return adjustInstance;
    }

    public static void getGoogleAdId(Context context, OnDeviceIdsRead onDeviceIdsRead) {
        Util.getGoogleAdId(context != null ? context.getApplicationContext() : null, onDeviceIdsRead);
    }

    public static String getSdkVersion() {
        return getDefaultInstance().getSdkVersion();
    }

    public static boolean isAdjustUninstallDetectionPayload(Map<String, String> map) {
        return Util.isAdjustUninstallDetectionPayload(map);
    }

    public static boolean isEnabled() {
        return getDefaultInstance().isEnabled();
    }

    public static void onCreate(AdjustConfig adjustConfig) {
        getDefaultInstance().onCreate(adjustConfig);
    }

    public static void onPause() {
        getDefaultInstance().onPause();
    }

    public static void onResume() {
        getDefaultInstance().onResume();
    }

    public static void processDeeplink(Uri uri, Context context, OnDeeplinkResolvedListener onDeeplinkResolvedListener) {
        getDefaultInstance().processDeeplink(uri, extractApplicationContext(context), onDeeplinkResolvedListener);
    }

    public static void removeSessionCallbackParameter(String str) {
        getDefaultInstance().removeSessionCallbackParameter(str);
    }

    public static void removeSessionPartnerParameter(String str) {
        getDefaultInstance().removeSessionPartnerParameter(str);
    }

    public static void resetSessionCallbackParameters() {
        getDefaultInstance().resetSessionCallbackParameters();
    }

    public static void resetSessionPartnerParameters() {
        getDefaultInstance().resetSessionPartnerParameters();
    }

    public static void sendFirstPackages() {
        getDefaultInstance().sendFirstPackages();
    }

    public static void setEnabled(boolean z2) {
        getDefaultInstance().setEnabled(z2);
    }

    public static void setOfflineMode(boolean z2) {
        getDefaultInstance().setOfflineMode(z2);
    }

    public static void setPushToken(String str) {
        getDefaultInstance().setPushToken(str);
    }

    public static void setReferrer(String str, Context context) {
        getDefaultInstance().sendReferrer(str, extractApplicationContext(context));
    }

    public static void setTestOptions(AdjustTestOptions adjustTestOptions) {
        Boolean bool = adjustTestOptions.teardown;
        if (bool != null && bool.booleanValue()) {
            AdjustInstance adjustInstance = defaultInstance;
            if (adjustInstance != null) {
                adjustInstance.teardown();
            }
            defaultInstance = null;
            AdjustFactory.teardown(adjustTestOptions.context);
        }
        getDefaultInstance().setTestOptions(adjustTestOptions);
    }

    public static void trackAdRevenue(AdjustAdRevenue adjustAdRevenue) {
        getDefaultInstance().trackAdRevenue(adjustAdRevenue);
    }

    public static void trackEvent(AdjustEvent adjustEvent) {
        getDefaultInstance().trackEvent(adjustEvent);
    }

    public static void trackMeasurementConsent(boolean z2) {
        getDefaultInstance().trackMeasurementConsent(z2);
    }

    public static void trackPlayStoreSubscription(AdjustPlayStoreSubscription adjustPlayStoreSubscription) {
        getDefaultInstance().trackPlayStoreSubscription(adjustPlayStoreSubscription);
    }

    public static void trackThirdPartySharing(AdjustThirdPartySharing adjustThirdPartySharing) {
        getDefaultInstance().trackThirdPartySharing(adjustThirdPartySharing);
    }

    public static void verifyPurchase(AdjustPurchase adjustPurchase, OnPurchaseVerificationFinishedListener onPurchaseVerificationFinishedListener) {
        getDefaultInstance().verifyPurchase(adjustPurchase, onPurchaseVerificationFinishedListener);
    }

    public static void appWillOpenUrl(Uri uri, Context context) {
        getDefaultInstance().appWillOpenUrl(uri, extractApplicationContext(context));
    }

    public static void setPushToken(String str, Context context) {
        getDefaultInstance().setPushToken(str, extractApplicationContext(context));
    }

    public static void trackAdRevenue(String str, JSONObject jSONObject) {
        getDefaultInstance().trackAdRevenue(str, jSONObject);
    }
}
