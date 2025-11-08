package com.adjust.sdk;

import android.content.Context;
import com.adjust.sdk.AdjustInstance;

public class AdjustConfig {
    public static final String AD_REVENUE_ADMOB = "admob_sdk";
    public static final String AD_REVENUE_ADMOST = "admost_sdk";
    public static final String AD_REVENUE_ADX = "adx_sdk";
    public static final String AD_REVENUE_APPLOVIN_MAX = "applovin_max_sdk";
    public static final String AD_REVENUE_HELIUM_CHARTBOOST = "helium_chartboost_sdk";
    public static final String AD_REVENUE_IRONSOURCE = "ironsource_sdk";
    public static final String AD_REVENUE_MOPUB = "mopub";
    public static final String AD_REVENUE_SOURCE_PUBLISHER = "publisher_sdk";
    public static final String AD_REVENUE_TOPON = "topon_sdk";
    public static final String AD_REVENUE_TRADPLUS = "tradplus_sdk";
    public static final String AD_REVENUE_UNITY = "unity_sdk";
    public static final String DATA_RESIDENCY_EU = "data_residency_eu";
    public static final String DATA_RESIDENCY_TR = "data_residency_tr";
    public static final String DATA_RESIDENCY_US = "data_residency_us";
    public static final String ENVIRONMENT_PRODUCTION = "production";
    public static final String ENVIRONMENT_SANDBOX = "sandbox";
    public static final String URL_STRATEGY_CHINA = "url_strategy_china";
    public static final String URL_STRATEGY_CN = "url_strategy_cn";
    public static final String URL_STRATEGY_CN_ONLY = "url_strategy_cn_only";
    public static final String URL_STRATEGY_INDIA = "url_strategy_india";
    String appSecret;
    String appToken;
    String basePath;
    OnDeeplinkResolvedListener cachedDeeplinkResolutionCallback;
    Context context;
    boolean coppaCompliantEnabled;
    Class deepLinkComponent;
    String defaultTracker;
    Double delayStart;
    Boolean deviceKnown;
    String environment;
    boolean eventBufferingEnabled;
    String externalDeviceId;
    String fbAppId;
    boolean finalAttributionEnabled;
    String gdprPath;
    ILogger logger;
    Boolean needsCost;
    OnAttributionChangedListener onAttributionChangedListener;
    OnDeeplinkResponseListener onDeeplinkResponseListener;
    OnEventTrackingFailedListener onEventTrackingFailedListener;
    OnEventTrackingSucceededListener onEventTrackingSucceededListener;
    OnSessionTrackingFailedListener onSessionTrackingFailedListener;
    OnSessionTrackingSucceededListener onSessionTrackingSucceededListener;
    boolean playStoreKidsAppEnabled;
    AdjustInstance.PreLaunchActions preLaunchActions;
    String preinstallFilePath;
    boolean preinstallTrackingEnabled;
    String processName;
    String purchaseVerificationPath;
    String pushToken;
    boolean readDeviceInfoOnceEnabled;
    String sdkPrefix;
    String secretId;
    boolean sendInBackground;
    Boolean startEnabled;
    boolean startOffline;
    String subscriptionPath;
    String urlStrategy;
    String userAgent;

    public AdjustConfig(Context context2, String str, String str2) {
        init(context2, str, str2, false);
    }

    private boolean checkAppToken(String str) {
        if (str == null) {
            this.logger.error("Missing App Token", new Object[0]);
            return false;
        } else if (str.length() == 12) {
            return true;
        } else {
            this.logger.error("Malformed App Token '%s'", str);
            return false;
        }
    }

    private boolean checkContext(Context context2) {
        if (context2 == null) {
            this.logger.error("Missing context", new Object[0]);
            return false;
        } else if (Util.checkPermission(context2, "android.permission.INTERNET")) {
            return true;
        } else {
            this.logger.error("Missing permission: INTERNET", new Object[0]);
            return false;
        }
    }

    private boolean checkEnvironment(String str) {
        if (str == null) {
            this.logger.error("Missing environment", new Object[0]);
            return false;
        } else if (str.equals(ENVIRONMENT_SANDBOX)) {
            this.logger.warnInProduction("SANDBOX: Adjust is running in Sandbox mode. Use this setting for testing. Don't forget to set the environment to `production` before publishing!", new Object[0]);
            return true;
        } else {
            boolean equals = str.equals(ENVIRONMENT_PRODUCTION);
            ILogger iLogger = this.logger;
            if (equals) {
                iLogger.warnInProduction("PRODUCTION: Adjust is running in Production mode. Use this setting only for the build that you want to publish. Set the environment to `sandbox` if you want to test your app!", new Object[0]);
                return true;
            }
            iLogger.error("Unknown environment '%s'", str);
            return false;
        }
    }

    private void init(Context context2, String str, String str2, boolean z2) {
        this.logger = AdjustFactory.getLogger();
        setLogLevel((!z2 || !ENVIRONMENT_PRODUCTION.equals(str2)) ? LogLevel.INFO : LogLevel.SUPRESS, str2);
        if (context2 != null) {
            context2 = context2.getApplicationContext();
        }
        this.context = context2;
        this.appToken = str;
        this.environment = str2;
        this.eventBufferingEnabled = false;
        this.sendInBackground = false;
        this.preinstallTrackingEnabled = false;
    }

    public String getAppSecret() {
        return this.appSecret;
    }

    public String getAppToken() {
        return this.appToken;
    }

    public String getBasePath() {
        return this.basePath;
    }

    public Context getContext() {
        return this.context;
    }

    public Class getDeepLinkComponent() {
        return this.deepLinkComponent;
    }

    public String getDefaultTracker() {
        return this.defaultTracker;
    }

    public Double getDelayStart() {
        return this.delayStart;
    }

    public Boolean getDeviceKnown() {
        return this.deviceKnown;
    }

    public String getEnvironment() {
        return this.environment;
    }

    public String getExternalDeviceId() {
        return this.externalDeviceId;
    }

    public String getFbAppId() {
        return this.fbAppId;
    }

    public String getGdprPath() {
        return this.gdprPath;
    }

    public ILogger getLogger() {
        return this.logger;
    }

    public Boolean getNeedsCost() {
        return this.needsCost;
    }

    public OnAttributionChangedListener getOnAttributionChangedListener() {
        return this.onAttributionChangedListener;
    }

    public OnDeeplinkResponseListener getOnDeeplinkResponseListener() {
        return this.onDeeplinkResponseListener;
    }

    public OnEventTrackingFailedListener getOnEventTrackingFailedListener() {
        return this.onEventTrackingFailedListener;
    }

    public OnEventTrackingSucceededListener getOnEventTrackingSucceededListener() {
        return this.onEventTrackingSucceededListener;
    }

    public OnSessionTrackingFailedListener getOnSessionTrackingFailedListener() {
        return this.onSessionTrackingFailedListener;
    }

    public OnSessionTrackingSucceededListener getOnSessionTrackingSucceededListener() {
        return this.onSessionTrackingSucceededListener;
    }

    public AdjustInstance.PreLaunchActions getPreLaunchActions() {
        return this.preLaunchActions;
    }

    public String getPreinstallFilePath() {
        return this.preinstallFilePath;
    }

    public String getProcessName() {
        return this.processName;
    }

    public String getPurchaseVerificationPath() {
        return this.purchaseVerificationPath;
    }

    public String getPushToken() {
        return this.pushToken;
    }

    public String getSdkPrefix() {
        return this.sdkPrefix;
    }

    public String getSecretId() {
        return this.secretId;
    }

    public Boolean getStartEnabled() {
        return this.startEnabled;
    }

    public String getSubscriptionPath() {
        return this.subscriptionPath;
    }

    public String getUrlStrategy() {
        return this.urlStrategy;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public boolean isCoppaCompliantEnabled() {
        return this.coppaCompliantEnabled;
    }

    public boolean isEventBufferingEnabled() {
        return this.eventBufferingEnabled;
    }

    public boolean isFinalAttributionEnabled() {
        return this.finalAttributionEnabled;
    }

    public boolean isPlayStoreKidsAppEnabled() {
        return this.playStoreKidsAppEnabled;
    }

    public boolean isPreinstallTrackingEnabled() {
        return this.preinstallTrackingEnabled;
    }

    public boolean isReadDeviceInfoOnceEnabled() {
        return this.readDeviceInfoOnceEnabled;
    }

    public boolean isSendInBackground() {
        return this.sendInBackground;
    }

    public boolean isStartOffline() {
        return this.startOffline;
    }

    public boolean isValid() {
        return checkAppToken(this.appToken) && checkEnvironment(this.environment) && checkContext(this.context);
    }

    public void setAppSecret(long j2, long j3, long j4, long j5, long j6) {
        this.secretId = Util.formatString(TimeModel.NUMBER_FORMAT, Long.valueOf(j2));
        this.appSecret = Util.formatString("%d%d%d%d", Long.valueOf(j3), Long.valueOf(j4), Long.valueOf(j5), Long.valueOf(j6));
    }

    public void setCoppaCompliantEnabled(boolean z2) {
        this.coppaCompliantEnabled = z2;
    }

    public void setDeepLinkComponent(Class cls) {
        this.deepLinkComponent = cls;
    }

    public void setDefaultTracker(String str) {
        this.defaultTracker = str;
    }

    public void setDelayStart(double d2) {
        this.delayStart = Double.valueOf(d2);
    }

    public void setDeviceKnown(boolean z2) {
        this.deviceKnown = Boolean.valueOf(z2);
    }

    public void setEventBufferingEnabled(Boolean bool) {
        this.eventBufferingEnabled = bool == null ? false : bool.booleanValue();
    }

    public void setExternalDeviceId(String str) {
        this.externalDeviceId = str;
    }

    public void setFbAppId(String str) {
        this.fbAppId = str;
    }

    public void setFinalAttributionEnabled(boolean z2) {
        this.finalAttributionEnabled = z2;
    }

    public void setLogLevel(LogLevel logLevel) {
        setLogLevel(logLevel, this.environment);
    }

    public void setNeedsCost(boolean z2) {
        this.needsCost = Boolean.valueOf(z2);
    }

    public void setOnAttributionChangedListener(OnAttributionChangedListener onAttributionChangedListener2) {
        this.onAttributionChangedListener = onAttributionChangedListener2;
    }

    public void setOnDeeplinkResponseListener(OnDeeplinkResponseListener onDeeplinkResponseListener2) {
        this.onDeeplinkResponseListener = onDeeplinkResponseListener2;
    }

    public void setOnEventTrackingFailedListener(OnEventTrackingFailedListener onEventTrackingFailedListener2) {
        this.onEventTrackingFailedListener = onEventTrackingFailedListener2;
    }

    public void setOnEventTrackingSucceededListener(OnEventTrackingSucceededListener onEventTrackingSucceededListener2) {
        this.onEventTrackingSucceededListener = onEventTrackingSucceededListener2;
    }

    public void setOnSessionTrackingFailedListener(OnSessionTrackingFailedListener onSessionTrackingFailedListener2) {
        this.onSessionTrackingFailedListener = onSessionTrackingFailedListener2;
    }

    public void setOnSessionTrackingSucceededListener(OnSessionTrackingSucceededListener onSessionTrackingSucceededListener2) {
        this.onSessionTrackingSucceededListener = onSessionTrackingSucceededListener2;
    }

    public void setPlayStoreKidsAppEnabled(boolean z2) {
        this.playStoreKidsAppEnabled = z2;
    }

    public void setPreinstallFilePath(String str) {
        this.preinstallFilePath = str;
    }

    public void setPreinstallTrackingEnabled(boolean z2) {
        this.preinstallTrackingEnabled = z2;
    }

    public void setProcessName(String str) {
        this.processName = str;
    }

    public void setReadDeviceInfoOnceEnabled(boolean z2) {
        this.readDeviceInfoOnceEnabled = z2;
    }

    @Deprecated
    public void setReadMobileEquipmentIdentity(boolean z2) {
        this.logger.warn("This method has been deprecated and shouldn't be used anymore", new Object[0]);
    }

    public void setSdkPrefix(String str) {
        this.sdkPrefix = str;
    }

    public void setSendInBackground(boolean z2) {
        this.sendInBackground = z2;
    }

    public void setUrlStrategy(String str) {
        if (str == null || str.isEmpty()) {
            this.logger.error("Invalid url strategy", new Object[0]);
            return;
        }
        if (!str.equals(URL_STRATEGY_INDIA) && !str.equals(URL_STRATEGY_CHINA) && !str.equals(URL_STRATEGY_CN) && !str.equals(URL_STRATEGY_CN_ONLY) && !str.equals(DATA_RESIDENCY_EU) && !str.equals(DATA_RESIDENCY_TR) && !str.equals(DATA_RESIDENCY_US)) {
            this.logger.warn("Unrecognised url strategy %s", str);
        }
        this.urlStrategy = str;
    }

    public void setUserAgent(String str) {
        this.userAgent = str;
    }

    public AdjustConfig(Context context2, String str, String str2, boolean z2) {
        init(context2, str, str2, z2);
    }

    private void setLogLevel(LogLevel logLevel, String str) {
        this.logger.setLogLevel(logLevel, ENVIRONMENT_PRODUCTION.equals(str));
    }
}
