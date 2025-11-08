package com.adjust.sdk;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import androidx.core.app.NotificationCompat;
import com.adjust.sdk.GooglePlayServicesClient;
import com.appsamurai.storyly.exoplayer2.common.C;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

class DeviceInfo {
    private static final String OFFICIAL_FACEBOOK_SIGNATURE = "30820268308201d102044a9c4610300d06092a864886f70d0101040500307a310b3009060355040613025553310b3009060355040813024341311230100603550407130950616c6f20416c746f31183016060355040a130f46616365626f6f6b204d6f62696c653111300f060355040b130846616365626f6f6b311d301b0603550403131446616365626f6f6b20436f72706f726174696f6e3020170d3039303833313231353231365a180f32303530303932353231353231365a307a310b3009060355040613025553310b3009060355040813024341311230100603550407130950616c6f20416c746f31183016060355040a130f46616365626f6f6b204d6f62696c653111300f060355040b130846616365626f6f6b311d301b0603550403131446616365626f6f6b20436f72706f726174696f6e30819f300d06092a864886f70d010101050003818d0030818902818100c207d51df8eb8c97d93ba0c8c1002c928fab00dc1b42fca5e66e99cc3023ed2d214d822bc59e8e35ddcf5f44c7ae8ade50d7e0c434f500e6c131f4a2834f987fc46406115de2018ebbb0d5a3c261bd97581ccfef76afc7135a6d59e8855ecd7eacc8f8737e794c60a761c536b72b11fac8e603f5da1a2d54aa103b8a13c0dbc10203010001300d06092a864886f70d0101040500038181005ee9be8bcbb250648d3b741290a82a1c9dc2e76a0af2f2228f1d9f9c4007529c446a70175c5a900d5141812866db46be6559e2141616483998211f4a673149fb2232a10d247663b26a9031e15f84bc1c74d141ff98a02d76f85b2c8ab2571b6469b232d8e768a7f7ca04f7abe4a775615916c07940656b58717457b42bd928a2";
    String abi;
    String androidId;
    String apiLevel;
    String appInstallTime;
    String appSetId;
    String appUpdateTime;
    String appVersion;
    String buildName;
    String clientSdk;
    int connectivityType;
    String country;
    String deviceManufacturer;
    String deviceName;
    String deviceType;
    String displayHeight;
    String displayWidth;
    String fbAttributionId;
    String fireAdId;
    Boolean fireTrackingEnabled;
    String hardwareName;
    Map<String, String> imeiParameters;
    boolean isGooglePlayGamesForPC;
    Boolean isSamsungCloudEnvironment;
    Boolean isTrackingEnabled;
    String language;
    String mcc;
    String mnc;
    private boolean nonGoogleIdsReadOnce = false;
    Map<String, String> oaidParameters;
    String osName;
    String osVersion;
    private boolean otherDeviceInfoParamsReadOnce = false;
    String packageName;
    String playAdId;
    int playAdIdAttempt = -1;
    String playAdIdSource;
    private boolean playIdsReadOnce = false;
    String screenDensity;
    String screenFormat;
    String screenSize;
    int uiMode;

    public static class UtilDeviceIds {
        private UtilDeviceIds() {
        }

        /* access modifiers changed from: private */
        public static int getConnectivityType(Context context, ILogger iLogger) {
            Network activeNetwork;
            NetworkCapabilities networkCapabilities;
            try {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                if (connectivityManager == null || (activeNetwork = connectivityManager.getActiveNetwork()) == null || (networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)) == null) {
                    return -1;
                }
                if (networkCapabilities.hasTransport(1)) {
                    return 1;
                }
                if (networkCapabilities.hasTransport(0)) {
                    return 0;
                }
                if (networkCapabilities.hasTransport(3)) {
                    return 3;
                }
                if (networkCapabilities.hasTransport(4)) {
                    return 4;
                }
                if (networkCapabilities.hasTransport(2)) {
                    return 2;
                }
                if (networkCapabilities.hasTransport(5)) {
                    return 5;
                }
                return networkCapabilities.hasTransport(6) ? 6 : -1;
            } catch (Exception e3) {
                iLogger.warn("Couldn't read connectivity type (%s)", e3.getMessage());
            }
        }

        /* access modifiers changed from: private */
        public static String getFireAdvertisingId(ContentResolver contentResolver) {
            if (contentResolver == null) {
                return null;
            }
            try {
                return Settings.Secure.getString(contentResolver, "advertising_id");
            } catch (Exception unused) {
                return null;
            }
        }

        private static Boolean getFireTrackingEnabled(ContentResolver contentResolver) {
            try {
                return Boolean.valueOf(Settings.Secure.getInt(contentResolver, "limit_ad_tracking") == 0);
            } catch (Exception unused) {
                return null;
            }
        }

        /* access modifiers changed from: private */
        public static Map<String, String> getImeiParameters(AdjustConfig adjustConfig, ILogger iLogger) {
            if (adjustConfig.coppaCompliantEnabled) {
                return null;
            }
            return Reflection.getImeiParameters(adjustConfig.context, iLogger);
        }

        public static String getMcc(Context context, ILogger iLogger) {
            try {
                String networkOperator = ((TelephonyManager) context.getSystemService("phone")).getNetworkOperator();
                if (!TextUtils.isEmpty(networkOperator)) {
                    return networkOperator.substring(0, 3);
                }
                AdjustFactory.getLogger().warn("Couldn't receive networkOperator string to read MCC", new Object[0]);
                return null;
            } catch (Exception unused) {
                AdjustFactory.getLogger().warn("Couldn't return mcc", new Object[0]);
                return null;
            }
        }

        /* access modifiers changed from: private */
        public static String getMnc(Context context, ILogger iLogger) {
            try {
                String networkOperator = ((TelephonyManager) context.getSystemService("phone")).getNetworkOperator();
                if (!TextUtils.isEmpty(networkOperator)) {
                    return networkOperator.substring(3);
                }
                AdjustFactory.getLogger().warn("Couldn't receive networkOperator string to read MNC", new Object[0]);
                return null;
            } catch (Exception unused) {
                iLogger.warn("Couldn't return mnc", new Object[0]);
                return null;
            }
        }

        /* access modifiers changed from: private */
        public static Map<String, String> getOaidParameters(AdjustConfig adjustConfig, ILogger iLogger) {
            if (adjustConfig.coppaCompliantEnabled) {
                return null;
            }
            return Reflection.getOaidParameters(adjustConfig.context, iLogger);
        }

        /* access modifiers changed from: private */
        public static String getFireAdvertisingId(AdjustConfig adjustConfig) {
            if (adjustConfig.coppaCompliantEnabled) {
                return null;
            }
            return getFireAdvertisingId(adjustConfig.context.getContentResolver());
        }

        /* access modifiers changed from: private */
        public static Boolean getFireTrackingEnabled(AdjustConfig adjustConfig) {
            if (adjustConfig.coppaCompliantEnabled) {
                return null;
            }
            return getFireTrackingEnabled(adjustConfig.context.getContentResolver());
        }
    }

    public DeviceInfo(AdjustConfig adjustConfig) {
        Context context = adjustConfig.context;
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        Locale locale = Util.getLocale(configuration);
        PackageInfo packageInfo = getPackageInfo(context);
        int i3 = configuration.screenLayout;
        this.isGooglePlayGamesForPC = Util.isGooglePlayGamesForPC(context);
        this.packageName = getPackageName(context);
        this.appVersion = getAppVersion(packageInfo);
        this.deviceType = getDeviceType(configuration);
        this.deviceName = getDeviceName();
        this.deviceManufacturer = getDeviceManufacturer();
        this.osName = getOsName();
        this.osVersion = getOsVersion();
        this.apiLevel = getApiLevel();
        this.language = getLanguage(locale);
        this.country = getCountry(locale);
        this.screenSize = getScreenSize(i3);
        this.screenFormat = getScreenFormat(i3);
        this.screenDensity = getScreenDensity(displayMetrics);
        this.displayWidth = getDisplayWidth(displayMetrics);
        this.displayHeight = getDisplayHeight(displayMetrics);
        this.clientSdk = getClientSdk(adjustConfig.sdkPrefix);
        this.fbAttributionId = getFacebookAttributionId(context);
        this.hardwareName = getHardwareName();
        this.abi = getABI();
        this.buildName = getBuildName();
        this.appInstallTime = getAppInstallTime(packageInfo);
        this.appUpdateTime = getAppUpdateTime(packageInfo);
        this.uiMode = getDeviceUiMode(configuration);
        if (Reflection.isAppRunningInSamsungCloudEnvironment(context, adjustConfig.logger)) {
            this.isSamsungCloudEnvironment = Boolean.TRUE;
            this.playAdId = Reflection.getSamsungCloudDevGoogleAdId(context, adjustConfig.logger);
            this.playAdIdSource = "samsung_cloud_sdk";
        }
        if (Util.canReadPlayIds(adjustConfig)) {
            this.appSetId = Reflection.getAppSetId(context);
        }
    }

    private String getABI() {
        String[] supportedAbis = Util.getSupportedAbis();
        return (supportedAbis == null || supportedAbis.length == 0) ? Util.getCpuAbi() : supportedAbis[0];
    }

    private String getApiLevel() {
        return "" + Build.VERSION.SDK_INT;
    }

    private String getAppInstallTime(PackageInfo packageInfo) {
        try {
            return Util.dateFormatter.format(new Date(packageInfo.firstInstallTime));
        } catch (Exception unused) {
            return null;
        }
    }

    private String getAppUpdateTime(PackageInfo packageInfo) {
        try {
            return Util.dateFormatter.format(new Date(packageInfo.lastUpdateTime));
        } catch (Exception unused) {
            return null;
        }
    }

    private String getAppVersion(PackageInfo packageInfo) {
        try {
            return packageInfo.versionName;
        } catch (Exception unused) {
            return null;
        }
    }

    private String getBuildName() {
        return Build.ID;
    }

    private String getClientSdk(String str) {
        return str == null ? Constants.CLIENT_SDK : Util.formatString("%s@%s", str, Constants.CLIENT_SDK);
    }

    private String getCountry(Locale locale) {
        return locale.getCountry();
    }

    private String getDeviceManufacturer() {
        return Build.MANUFACTURER;
    }

    private String getDeviceName() {
        if (this.isGooglePlayGamesForPC) {
            return null;
        }
        return Build.MODEL;
    }

    private String getDeviceType(Configuration configuration) {
        if (this.isGooglePlayGamesForPC) {
            return "pc";
        }
        if ((configuration.uiMode & 15) == 4) {
            return "tv";
        }
        int i3 = configuration.screenLayout & 15;
        if (i3 == 1 || i3 == 2) {
            return "phone";
        }
        if (i3 == 3 || i3 == 4) {
            return "tablet";
        }
        return null;
    }

    private int getDeviceUiMode(Configuration configuration) {
        return configuration.uiMode & 15;
    }

    private String getDisplayHeight(DisplayMetrics displayMetrics) {
        return String.valueOf(displayMetrics.heightPixels);
    }

    private String getDisplayWidth(DisplayMetrics displayMetrics) {
        return String.valueOf(displayMetrics.widthPixels);
    }

    private String getFacebookAttributionId(Context context) {
        Cursor query;
        try {
            SigningInfo signingInfo = context.getPackageManager().getPackageInfo("com.facebook.katana", C.BUFFER_FLAG_FIRST_SAMPLE).signingInfo;
            Signature[] apkContentsSigners = signingInfo != null ? signingInfo.getApkContentsSigners() : null;
            if (apkContentsSigners != null) {
                if (apkContentsSigners.length == 1) {
                    if (!OFFICIAL_FACEBOOK_SIGNATURE.equals(apkContentsSigners[0].toCharsString()) || (query = context.getContentResolver().query(Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider"), new String[]{"aid"}, (String) null, (String[]) null, (String) null)) == null) {
                        return null;
                    }
                    if (!query.moveToFirst()) {
                        query.close();
                        return null;
                    }
                    String string = query.getString(query.getColumnIndex("aid"));
                    query.close();
                    return string;
                }
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public static String getFireAdvertisingIdBypassConditions(ContentResolver contentResolver) {
        return UtilDeviceIds.getFireAdvertisingId(contentResolver);
    }

    private String getHardwareName() {
        return Build.DISPLAY;
    }

    private String getLanguage(Locale locale) {
        return locale.getLanguage();
    }

    private String getOsName() {
        return this.isGooglePlayGamesForPC ? "windows" : com.amplitude.api.DeviceInfo.OS_NAME;
    }

    private String getOsVersion() {
        if (this.isGooglePlayGamesForPC) {
            return null;
        }
        return Build.VERSION.RELEASE;
    }

    private PackageInfo getPackageInfo(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 4096);
        } catch (Exception unused) {
            return null;
        }
    }

    private String getPackageName(Context context) {
        return context.getPackageName();
    }

    private String getScreenDensity(DisplayMetrics displayMetrics) {
        int i3 = displayMetrics.densityDpi;
        if (i3 == 0) {
            return null;
        }
        return i3 < 140 ? Constants.LOW : i3 > 200 ? Constants.HIGH : "medium";
    }

    private String getScreenFormat(int i3) {
        int i4 = i3 & 48;
        if (i4 == 16) {
            return Constants.NORMAL;
        }
        if (i4 != 32) {
            return null;
        }
        return "long";
    }

    private String getScreenSize(int i3) {
        int i4 = i3 & 15;
        if (i4 == 1) {
            return Constants.SMALL;
        }
        if (i4 == 2) {
            return Constants.NORMAL;
        }
        if (i4 == 3) {
            return Constants.LARGE;
        }
        if (i4 != 4) {
            return null;
        }
        return Constants.XLARGE;
    }

    public void reloadNonPlayIds(AdjustConfig adjustConfig) {
        if (Util.canReadNonPlayIds(adjustConfig) && !this.nonGoogleIdsReadOnce) {
            this.androidId = Util.getAndroidId(adjustConfig.context);
            this.nonGoogleIdsReadOnce = true;
        }
    }

    public void reloadOtherDeviceInfoParams(AdjustConfig adjustConfig, ILogger iLogger) {
        if (!adjustConfig.readDeviceInfoOnceEnabled || !this.otherDeviceInfoParamsReadOnce) {
            this.imeiParameters = UtilDeviceIds.getImeiParameters(adjustConfig, iLogger);
            this.oaidParameters = UtilDeviceIds.getOaidParameters(adjustConfig, iLogger);
            this.fireAdId = UtilDeviceIds.getFireAdvertisingId(adjustConfig);
            this.fireTrackingEnabled = UtilDeviceIds.getFireTrackingEnabled(adjustConfig);
            this.connectivityType = UtilDeviceIds.getConnectivityType(adjustConfig.context, iLogger);
            this.mcc = UtilDeviceIds.getMcc(adjustConfig.context, iLogger);
            this.mnc = UtilDeviceIds.getMnc(adjustConfig.context, iLogger);
            this.otherDeviceInfoParamsReadOnce = true;
        }
    }

    public void reloadPlayIds(AdjustConfig adjustConfig) {
        Boolean bool = this.isSamsungCloudEnvironment;
        if ((bool != null && bool.booleanValue()) || !Util.canReadPlayIds(adjustConfig)) {
            return;
        }
        if (!this.playIdsReadOnce || !adjustConfig.readDeviceInfoOnceEnabled) {
            Context context = adjustConfig.context;
            String str = this.playAdId;
            Boolean bool2 = this.isTrackingEnabled;
            this.playAdId = null;
            this.isTrackingEnabled = null;
            this.playAdIdSource = null;
            this.playAdIdAttempt = -1;
            for (int i3 = 1; i3 <= 3; i3++) {
                try {
                    GooglePlayServicesClient.GooglePlayServicesInfo googlePlayServicesInfo = GooglePlayServicesClient.getGooglePlayServicesInfo(context, (long) (i3 * 3000));
                    if (this.playAdId == null) {
                        this.playAdId = googlePlayServicesInfo.getGpsAdid();
                        this.playIdsReadOnce = true;
                    }
                    if (this.isTrackingEnabled == null) {
                        this.isTrackingEnabled = googlePlayServicesInfo.isTrackingEnabled();
                    }
                    if (!(this.playAdId == null || this.isTrackingEnabled == null)) {
                        this.playAdIdSource = NotificationCompat.CATEGORY_SERVICE;
                        this.playAdIdAttempt = i3;
                        return;
                    }
                } catch (Exception unused) {
                }
            }
            for (int i4 = 1; i4 <= 3; i4++) {
                Object advertisingInfoObject = Util.getAdvertisingInfoObject(context, 11000);
                if (advertisingInfoObject != null) {
                    if (this.playAdId == null) {
                        this.playAdId = Util.getPlayAdId(context, advertisingInfoObject, 1000);
                        this.playIdsReadOnce = true;
                    }
                    if (this.isTrackingEnabled == null) {
                        this.isTrackingEnabled = Util.isPlayTrackingEnabled(context, advertisingInfoObject, 1000);
                    }
                    if (!(this.playAdId == null || this.isTrackingEnabled == null)) {
                        this.playAdIdSource = "library";
                        this.playAdIdAttempt = i4;
                        return;
                    }
                }
            }
            if (this.playAdId == null) {
                this.playAdId = str;
                this.playIdsReadOnce = true;
            }
            if (this.isTrackingEnabled == null) {
                this.isTrackingEnabled = bool2;
            }
        }
    }
}
