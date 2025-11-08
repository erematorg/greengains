package com.adjust.sdk;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Process;
import android.text.TextUtils;
import com.adjust.sdk.network.ActivityPackageSender;
import com.adjust.sdk.network.UtilNetworking;
import com.adjust.sdk.scheduler.SingleThreadCachedScheduler;
import com.adjust.sdk.scheduler.ThreadExecutor;
import com.adjust.sdk.scheduler.TimerCycle;
import com.adjust.sdk.scheduler.TimerOnce;
import com.reown.android.push.notifications.PushMessagingService;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.json.JSONObject;

public class ActivityHandler implements IActivityHandler {
    private static final String ACTIVITY_STATE_NAME = "Activity state";
    private static final String ATTRIBUTION_NAME = "Attribution";
    private static long BACKGROUND_TIMER_INTERVAL = 0;
    private static final String BACKGROUND_TIMER_NAME = "Background timer";
    private static final String DELAY_START_TIMER_NAME = "Delay Start timer";
    private static long FOREGROUND_TIMER_INTERVAL = 0;
    private static final String FOREGROUND_TIMER_NAME = "Foreground timer";
    private static long FOREGROUND_TIMER_START = 0;
    private static final String SESSION_CALLBACK_PARAMETERS_NAME = "Session Callback parameters";
    private static long SESSION_INTERVAL = 0;
    private static final String SESSION_PARAMETERS_NAME = "Session parameters";
    private static final String SESSION_PARTNER_PARAMETERS_NAME = "Session Partner parameters";
    private static long SUBSESSION_INTERVAL = 0;
    private static final String TIME_TRAVEL = "Time travel!";
    private ActivityState activityState;
    /* access modifiers changed from: private */
    public AdjustConfig adjustConfig;
    /* access modifiers changed from: private */
    public AdjustAttribution attribution;
    private IAttributionHandler attributionHandler;
    private TimerOnce backgroundTimer;
    private String basePath;
    private OnDeeplinkResolvedListener cachedDeeplinkResolutionCallback;
    private TimerOnce delayStartTimer;
    private DeviceInfo deviceInfo;
    private ThreadExecutor executor = new SingleThreadCachedScheduler("ActivityHandler");
    private TimerCycle foregroundTimer;
    private String gdprPath;
    private InstallReferrer installReferrer;
    private InstallReferrerHuawei installReferrerHuawei;
    private InstallReferrerMeta installReferrerMeta;
    /* access modifiers changed from: private */
    public InternalState internalState;
    /* access modifiers changed from: private */
    public ILogger logger;
    private IPackageHandler packageHandler;
    private IPurchaseVerificationHandler purchaseVerificationHandler;
    private ISdkClickHandler sdkClickHandler;
    private SessionParameters sessionParameters;
    private String subscriptionPath;

    public class InternalState {
        boolean background;
        boolean delayStart;
        boolean enabled;
        boolean firstLaunch;
        boolean firstSdkStart;
        boolean offline;
        boolean preinstallHasBeenRead;
        boolean sessionResponseProcessed;
        boolean updatePackages;

        public InternalState() {
        }

        public boolean hasFirstSdkStartNotOcurred() {
            return !this.firstSdkStart;
        }

        public boolean hasFirstSdkStartOcurred() {
            return this.firstSdkStart;
        }

        public boolean hasPreinstallBeenRead() {
            return this.preinstallHasBeenRead;
        }

        public boolean hasSessionResponseNotBeenProcessed() {
            return !this.sessionResponseProcessed;
        }

        public boolean isDisabled() {
            return !this.enabled;
        }

        public boolean isEnabled() {
            return this.enabled;
        }

        public boolean isFirstLaunch() {
            return this.firstLaunch;
        }

        public boolean isInBackground() {
            return this.background;
        }

        public boolean isInDelayedStart() {
            return this.delayStart;
        }

        public boolean isInForeground() {
            return !this.background;
        }

        public boolean isNotFirstLaunch() {
            return !this.firstLaunch;
        }

        public boolean isNotInDelayedStart() {
            return !this.delayStart;
        }

        public boolean isOffline() {
            return this.offline;
        }

        public boolean isOnline() {
            return !this.offline;
        }

        public boolean itHasToUpdatePackages() {
            return this.updatePackages;
        }
    }

    public class a implements Runnable {
        public a() {
        }

        public final void run() {
            ActivityHandler.this.sendReftagReferrerI();
        }
    }

    public class a0 implements Runnable {
        public a0() {
        }

        public final void run() {
            ActivityHandler.this.foregroundTimerFiredI();
        }
    }

    public class a1 implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Uri f3165a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ long f3166b;

        public a1(Uri uri, long j2) {
            this.f3165a = uri;
            this.f3166b = j2;
        }

        public final void run() {
            ActivityHandler.this.readOpenUrlI(this.f3165a, this.f3166b);
        }
    }

    public class b implements Runnable {
        public b() {
        }

        public final void run() {
            ActivityHandler.this.sendPreinstallReferrerI();
        }
    }

    public class b0 implements Runnable {
        public b0() {
        }

        public final void run() {
            ActivityHandler.this.backgroundTimerFiredI();
        }
    }

    public class b1 implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ boolean f3170a;

        public b1(boolean z2) {
            this.f3170a = z2;
        }

        public final void run() {
            ActivityHandler.this.setAskingAttributionI(this.f3170a);
        }
    }

    public class c implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ ReferrerDetails f3172a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ String f3173b;

        public c(ReferrerDetails referrerDetails, String str) {
            this.f3172a = referrerDetails;
            this.f3173b = str;
        }

        public final void run() {
            ActivityHandler.this.sendInstallReferrerI(this.f3172a, this.f3173b);
        }
    }

    public class c0 implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ AdjustPurchase f3175a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ OnPurchaseVerificationFinishedListener f3176b;

        public c0(AdjustPurchase adjustPurchase, OnPurchaseVerificationFinishedListener onPurchaseVerificationFinishedListener) {
            this.f3175a = adjustPurchase;
            this.f3176b = onPurchaseVerificationFinishedListener;
        }

        public final void run() {
            ActivityHandler.this.verifyPurchaseI(this.f3175a, this.f3176b);
        }
    }

    public class d implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ EventResponseData f3178a;

        public d(EventResponseData eventResponseData) {
            this.f3178a = eventResponseData;
        }

        public final void run() {
            ActivityHandler.this.launchEventResponseTasksI(this.f3178a);
        }
    }

    public class d0 implements IRunActivityHandler {
        public d0() {
        }

        public final void run(ActivityHandler activityHandler) {
            activityHandler.setEnabledI(ActivityHandler.this.adjustConfig.startEnabled.booleanValue());
        }
    }

    public class e implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ SdkClickResponseData f3181a;

        public e(SdkClickResponseData sdkClickResponseData) {
            this.f3181a = sdkClickResponseData;
        }

        public final void run() {
            ActivityHandler.this.launchSdkClickResponseTasksI(this.f3181a);
        }
    }

    public class e0 implements Runnable {
        public e0() {
        }

        public final void run() {
            ActivityHandler.this.foregroundTimerFired();
        }
    }

    public class f implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ SessionResponseData f3184a;

        public f(SessionResponseData sessionResponseData) {
            this.f3184a = sessionResponseData;
        }

        public final void run() {
            ActivityHandler.this.launchSessionResponseTasksI(this.f3184a);
        }
    }

    public class f0 implements Runnable {
        public f0() {
        }

        public final void run() {
            ActivityHandler.this.backgroundTimerFired();
        }
    }

    public class g implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ AttributionResponseData f3187a;

        public g(AttributionResponseData attributionResponseData) {
            this.f3187a = attributionResponseData;
        }

        public final void run() {
            ActivityHandler.this.launchAttributionResponseTasksI(this.f3187a);
        }
    }

    public class g0 implements Runnable {
        public g0() {
        }

        public final void run() {
            ActivityHandler.this.stopForegroundTimerI();
            ActivityHandler.this.startBackgroundTimerI();
            ActivityHandler.this.logger.verbose("Subsession end", new Object[0]);
            ActivityHandler.this.endI();
        }
    }

    public class h implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ PurchaseVerificationResponseData f3190a;

        public h(PurchaseVerificationResponseData purchaseVerificationResponseData) {
            this.f3190a = purchaseVerificationResponseData;
        }

        public final void run() {
            ActivityHandler.this.launchPurchaseVerificationResponseTasksI(this.f3190a);
        }
    }

    public class h0 implements Runnable {
        public h0() {
        }

        public final void run() {
            ActivityHandler.this.sendFirstPackages();
        }
    }

    public class i implements Runnable {
        public i() {
        }

        public final void run() {
            ActivityHandler.this.sendFirstPackagesI();
        }
    }

    public class i0 implements InstallReferrerReadListener {
        public i0() {
        }

        public final void onInstallReferrerRead(ReferrerDetails referrerDetails, String str) {
            ActivityHandler.this.sendInstallReferrer(referrerDetails, str);
        }
    }

    public class j implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ String f3195a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ String f3196b;

        public j(String str, String str2) {
            this.f3195a = str;
            this.f3196b = str2;
        }

        public final void run() {
            ActivityHandler.this.addSessionCallbackParameterI(this.f3195a, this.f3196b);
        }
    }

    public class j0 implements InstallReferrerReadListener {
        public j0() {
        }

        public final void onInstallReferrerRead(ReferrerDetails referrerDetails, String str) {
            ActivityHandler.this.sendInstallReferrer(referrerDetails, str);
        }
    }

    public class k implements Runnable {
        public k() {
        }

        public final void run() {
            ActivityHandler.this.initI();
        }
    }

    public class k0 implements InstallReferrerReadListener {
        public k0() {
        }

        public final void onInstallReferrerRead(ReferrerDetails referrerDetails, String str) {
            ActivityHandler.this.sendInstallReferrer(referrerDetails, str);
        }
    }

    public class l implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ String f3201a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ String f3202b;

        public l(String str, String str2) {
            this.f3201a = str;
            this.f3202b = str2;
        }

        public final void run() {
            ActivityHandler.this.addSessionPartnerParameterI(this.f3201a, this.f3202b);
        }
    }

    public class l0 implements Runnable {
        public l0() {
        }

        public final void run() {
            ReferrerDetails samsungReferrer = Reflection.getSamsungReferrer(ActivityHandler.this.getContext(), ActivityHandler.this.logger);
            if (samsungReferrer != null) {
                ActivityHandler.this.sendInstallReferrer(samsungReferrer, Constants.REFERRER_API_SAMSUNG);
            }
        }
    }

    public class m implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ String f3205a;

        public m(String str) {
            this.f3205a = str;
        }

        public final void run() {
            ActivityHandler.this.removeSessionCallbackParameterI(this.f3205a);
        }
    }

    public class m0 implements Runnable {
        public m0() {
        }

        public final void run() {
            ReferrerDetails xiaomiReferrer = Reflection.getXiaomiReferrer(ActivityHandler.this.getContext(), ActivityHandler.this.logger);
            if (xiaomiReferrer != null) {
                ActivityHandler.this.sendInstallReferrer(xiaomiReferrer, Constants.REFERRER_API_XIAOMI);
            }
        }
    }

    public class n implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ String f3208a;

        public n(String str) {
            this.f3208a = str;
        }

        public final void run() {
            ActivityHandler.this.removeSessionPartnerParameterI(this.f3208a);
        }
    }

    public class n0 implements Runnable {
        public n0() {
        }

        public final void run() {
            ReferrerDetails vivoReferrer = Reflection.getVivoReferrer(ActivityHandler.this.getContext(), ActivityHandler.this.logger);
            if (vivoReferrer != null) {
                ActivityHandler.this.sendInstallReferrer(vivoReferrer, Constants.REFERRER_API_VIVO);
            }
        }
    }

    public class o implements Runnable {
        public o() {
        }

        public final void run() {
            ActivityHandler.this.resetSessionCallbackParametersI();
        }
    }

    public class o0 implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ EventResponseData f3212a;

        public o0(EventResponseData eventResponseData) {
            this.f3212a = eventResponseData;
        }

        public final void run() {
            if (ActivityHandler.this.adjustConfig != null && ActivityHandler.this.adjustConfig.onEventTrackingSucceededListener != null) {
                ActivityHandler.this.adjustConfig.onEventTrackingSucceededListener.onFinishedEventTrackingSucceeded(this.f3212a.getSuccessResponseData());
            }
        }
    }

    public class p implements Runnable {
        public p() {
        }

        public final void run() {
            ActivityHandler.this.resetSessionPartnerParametersI();
        }
    }

    public class p0 implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ EventResponseData f3215a;

        public p0(EventResponseData eventResponseData) {
            this.f3215a = eventResponseData;
        }

        public final void run() {
            if (ActivityHandler.this.adjustConfig != null && ActivityHandler.this.adjustConfig.onEventTrackingFailedListener != null) {
                ActivityHandler.this.adjustConfig.onEventTrackingFailedListener.onFinishedEventTrackingFailed(this.f3215a.getFailureResponseData());
            }
        }
    }

    public class q implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ boolean f3217a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ String f3218b;

        public q(boolean z2, String str) {
            this.f3217a = z2;
            this.f3218b = str;
        }

        public final void run() {
            if (!this.f3217a) {
                SharedPreferencesManager.getDefaultInstance(ActivityHandler.this.getContext()).savePushToken(this.f3218b);
            }
            if (!ActivityHandler.this.internalState.hasFirstSdkStartNotOcurred()) {
                ActivityHandler.this.setPushTokenI(this.f3218b);
            }
        }
    }

    public class q0 implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OnDeeplinkResolvedListener f3220a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ SdkClickResponseData f3221b;

        public q0(OnDeeplinkResolvedListener onDeeplinkResolvedListener, SdkClickResponseData sdkClickResponseData) {
            this.f3220a = onDeeplinkResolvedListener;
            this.f3221b = sdkClickResponseData;
        }

        public final void run() {
            this.f3220a.onDeeplinkResolved(this.f3221b.resolvedDeeplink);
        }
    }

    public class r implements Runnable {
        public r() {
        }

        public final void run() {
            ActivityHandler.this.gdprForgetMeI();
        }
    }

    public class r0 implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ AdjustEvent f3223a;

        public r0(AdjustEvent adjustEvent) {
            this.f3223a = adjustEvent;
        }

        public final void run() {
            if (ActivityHandler.this.internalState.hasFirstSdkStartNotOcurred()) {
                ActivityHandler.this.logger.warn("Event tracked before first activity resumed.\nIf it was triggered in the Application class, it might timestamp or even send an install long before the user opens the app.\nPlease check https://github.com/adjust/android_sdk#can-i-trigger-an-event-at-application-launch for more information.", new Object[0]);
                ActivityHandler.this.startI();
            }
            ActivityHandler.this.trackEventI(this.f3223a);
        }
    }

    public class s implements Runnable {
        public s() {
        }

        public final void run() {
            ActivityHandler.this.disableThirdPartySharingI();
        }
    }

    public class s0 implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ SessionResponseData f3226a;

        public s0(SessionResponseData sessionResponseData) {
            this.f3226a = sessionResponseData;
        }

        public final void run() {
            if (ActivityHandler.this.adjustConfig != null && ActivityHandler.this.adjustConfig.onSessionTrackingSucceededListener != null) {
                ActivityHandler.this.adjustConfig.onSessionTrackingSucceededListener.onFinishedSessionTrackingSucceeded(this.f3226a.getSuccessResponseData());
            }
        }
    }

    public class t implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ AdjustThirdPartySharing f3228a;

        public t(AdjustThirdPartySharing adjustThirdPartySharing) {
            this.f3228a = adjustThirdPartySharing;
        }

        public final void run() {
            ActivityHandler.this.trackThirdPartySharingI(this.f3228a);
        }
    }

    public class t0 implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ SessionResponseData f3230a;

        public t0(SessionResponseData sessionResponseData) {
            this.f3230a = sessionResponseData;
        }

        public final void run() {
            if (ActivityHandler.this.adjustConfig != null && ActivityHandler.this.adjustConfig.onSessionTrackingFailedListener != null) {
                ActivityHandler.this.adjustConfig.onSessionTrackingFailedListener.onFinishedSessionTrackingFailed(this.f3230a.getFailureResponseData());
            }
        }
    }

    public class u implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ boolean f3232a;

        public u(boolean z2) {
            this.f3232a = z2;
        }

        public final void run() {
            ActivityHandler.this.trackMeasurementConsentI(this.f3232a);
        }
    }

    public class u0 implements Runnable {
        public u0() {
        }

        public final void run() {
            if (ActivityHandler.this.adjustConfig != null && ActivityHandler.this.adjustConfig.onAttributionChangedListener != null) {
                ActivityHandler.this.adjustConfig.onAttributionChangedListener.onAttributionChanged(ActivityHandler.this.attribution);
            }
        }
    }

    public class v implements Runnable {
        public v() {
        }

        public final void run() {
            ActivityHandler.this.delayStartI();
            ActivityHandler.this.stopBackgroundTimerI();
            ActivityHandler.this.startForegroundTimerI();
            ActivityHandler.this.logger.verbose("Subsession start", new Object[0]);
            ActivityHandler.this.startI();
        }
    }

    public class v0 implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ PurchaseVerificationResponseData f3236a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ AdjustPurchaseVerificationResult f3237b;

        public v0(PurchaseVerificationResponseData purchaseVerificationResponseData, AdjustPurchaseVerificationResult adjustPurchaseVerificationResult) {
            this.f3236a = purchaseVerificationResponseData;
            this.f3237b = adjustPurchaseVerificationResult;
        }

        public final void run() {
            this.f3236a.activityPackage.getPurchaseVerificationCallback().onVerificationFinished(this.f3237b);
        }
    }

    public class w implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ String f3238a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ JSONObject f3239b;

        public w(String str, JSONObject jSONObject) {
            this.f3238a = str;
            this.f3239b = jSONObject;
        }

        public final void run() {
            ActivityHandler.this.trackAdRevenueI(this.f3238a, this.f3239b);
        }
    }

    public class w0 implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Uri f3241a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ Intent f3242b;

        public w0(Intent intent, Uri uri) {
            this.f3241a = uri;
            this.f3242b = intent;
        }

        public final void run() {
            if (ActivityHandler.this.adjustConfig != null) {
                if (ActivityHandler.this.adjustConfig.onDeeplinkResponseListener != null ? ActivityHandler.this.adjustConfig.onDeeplinkResponseListener.launchReceivedDeeplink(this.f3241a) : true) {
                    ActivityHandler.this.launchDeeplinkMain(this.f3242b, this.f3241a);
                }
            }
        }
    }

    public class x implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ AdjustAdRevenue f3244a;

        public x(AdjustAdRevenue adjustAdRevenue) {
            this.f3244a = adjustAdRevenue;
        }

        public final void run() {
            ActivityHandler.this.trackAdRevenueI(this.f3244a);
        }
    }

    public class x0 implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ boolean f3246a;

        public x0(boolean z2) {
            this.f3246a = z2;
        }

        public final void run() {
            ActivityHandler.this.setEnabledI(this.f3246a);
        }
    }

    public class y implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ AdjustPlayStoreSubscription f3248a;

        public y(AdjustPlayStoreSubscription adjustPlayStoreSubscription) {
            this.f3248a = adjustPlayStoreSubscription;
        }

        public final void run() {
            ActivityHandler.this.trackSubscriptionI(this.f3248a);
        }
    }

    public class y0 implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ boolean f3250a;

        public y0(boolean z2) {
            this.f3250a = z2;
        }

        public final void run() {
            ActivityHandler.this.setOfflineModeI(this.f3250a);
        }
    }

    public class z implements Runnable {
        public z() {
        }

        public final void run() {
            ActivityHandler.this.gotOptOutResponseI();
        }
    }

    public class z0 implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Uri f3253a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ long f3254b;

        public z0(Uri uri, long j2) {
            this.f3253a = uri;
            this.f3254b = j2;
        }

        public final void run() {
            ActivityHandler.this.readOpenUrlI(this.f3253a, this.f3254b);
        }
    }

    private ActivityHandler(AdjustConfig adjustConfig2) {
        init(adjustConfig2);
        ILogger logger2 = AdjustFactory.getLogger();
        this.logger = logger2;
        logger2.lockLogLevel();
        InternalState internalState2 = new InternalState();
        this.internalState = internalState2;
        Boolean bool = adjustConfig2.startEnabled;
        internalState2.enabled = bool != null ? bool.booleanValue() : true;
        InternalState internalState3 = this.internalState;
        internalState3.offline = adjustConfig2.startOffline;
        internalState3.background = true;
        internalState3.delayStart = false;
        internalState3.updatePackages = false;
        internalState3.sessionResponseProcessed = false;
        internalState3.firstSdkStart = false;
        internalState3.preinstallHasBeenRead = false;
        this.executor.submit(new k());
    }

    /* access modifiers changed from: private */
    public void backgroundTimerFiredI() {
        if (toSendI()) {
            this.packageHandler.sendFirstPackage();
        }
    }

    private boolean checkActivityStateI(ActivityState activityState2) {
        if (!this.internalState.hasFirstSdkStartNotOcurred()) {
            return true;
        }
        this.logger.error("Sdk did not yet start", new Object[0]);
        return false;
    }

    private boolean checkAdjustAdRevenue(AdjustAdRevenue adjustAdRevenue) {
        if (adjustAdRevenue == null) {
            this.logger.error("Ad revenue object missing", new Object[0]);
            return false;
        } else if (adjustAdRevenue.isValid()) {
            return true;
        } else {
            this.logger.error("Ad revenue object not initialized correctly", new Object[0]);
            return false;
        }
    }

    private void checkAfterNewStartI() {
        checkAfterNewStartI(SharedPreferencesManager.getDefaultInstance(getContext()));
    }

    private void checkAttributionStateI() {
        if (checkActivityStateI(this.activityState)) {
            if (this.internalState.isFirstLaunch() && this.internalState.hasSessionResponseNotBeenProcessed()) {
                return;
            }
            if (this.attribution == null || this.activityState.askingAttribution) {
                this.attributionHandler.getAttribution();
            }
        }
    }

    private boolean checkEventI(AdjustEvent adjustEvent) {
        if (adjustEvent == null) {
            this.logger.error("Event missing", new Object[0]);
            return false;
        } else if (adjustEvent.isValid()) {
            return true;
        } else {
            this.logger.error("Event not initialized correctly", new Object[0]);
            return false;
        }
    }

    private void checkForInstallReferrerInfo(SdkClickResponseData sdkClickResponseData) {
        if (sdkClickResponseData.isInstallReferrer) {
            String str = sdkClickResponseData.referrerApi;
            if (str == null || !str.equalsIgnoreCase(Constants.REFERRER_API_HUAWEI_ADS)) {
                String str2 = sdkClickResponseData.referrerApi;
                if (str2 == null || !str2.equalsIgnoreCase(Constants.REFERRER_API_HUAWEI_APP_GALLERY)) {
                    String str3 = sdkClickResponseData.referrerApi;
                    if (str3 == null || !str3.equalsIgnoreCase(Constants.REFERRER_API_META)) {
                        String str4 = sdkClickResponseData.referrerApi;
                        if (str4 == null || !str4.equalsIgnoreCase(Constants.REFERRER_API_SAMSUNG)) {
                            String str5 = sdkClickResponseData.referrerApi;
                            if (str5 == null || !str5.equalsIgnoreCase(Constants.REFERRER_API_XIAOMI)) {
                                String str6 = sdkClickResponseData.referrerApi;
                                if (str6 == null || !str6.equalsIgnoreCase(Constants.REFERRER_API_VIVO)) {
                                    ActivityState activityState2 = this.activityState;
                                    activityState2.clickTime = sdkClickResponseData.clickTime;
                                    activityState2.installBegin = sdkClickResponseData.installBegin;
                                    activityState2.installReferrer = sdkClickResponseData.installReferrer;
                                    activityState2.clickTimeServer = sdkClickResponseData.clickTimeServer;
                                    activityState2.installBeginServer = sdkClickResponseData.installBeginServer;
                                    activityState2.installVersion = sdkClickResponseData.installVersion;
                                    activityState2.googlePlayInstant = sdkClickResponseData.googlePlayInstant;
                                } else {
                                    ActivityState activityState3 = this.activityState;
                                    activityState3.clickTimeVivo = sdkClickResponseData.clickTime;
                                    activityState3.installBeginVivo = sdkClickResponseData.installBegin;
                                    activityState3.installReferrerVivo = sdkClickResponseData.installReferrer;
                                    activityState3.installVersionVivo = sdkClickResponseData.installVersion;
                                }
                            } else {
                                ActivityState activityState4 = this.activityState;
                                activityState4.clickTimeXiaomi = sdkClickResponseData.clickTime;
                                activityState4.installBeginXiaomi = sdkClickResponseData.installBegin;
                                activityState4.installReferrerXiaomi = sdkClickResponseData.installReferrer;
                                activityState4.clickTimeServerXiaomi = sdkClickResponseData.clickTimeServer;
                                activityState4.installBeginServerXiaomi = sdkClickResponseData.installBeginServer;
                                activityState4.installVersionXiaomi = sdkClickResponseData.installVersion;
                            }
                        } else {
                            ActivityState activityState5 = this.activityState;
                            activityState5.clickTimeSamsung = sdkClickResponseData.clickTime;
                            activityState5.installBeginSamsung = sdkClickResponseData.installBegin;
                            activityState5.installReferrerSamsung = sdkClickResponseData.installReferrer;
                        }
                    } else {
                        ActivityState activityState6 = this.activityState;
                        activityState6.clickTimeMeta = sdkClickResponseData.clickTime;
                        activityState6.installReferrerMeta = sdkClickResponseData.installReferrer;
                        activityState6.isClickMeta = sdkClickResponseData.isClick;
                    }
                } else {
                    ActivityState activityState7 = this.activityState;
                    activityState7.clickTimeHuawei = sdkClickResponseData.clickTime;
                    activityState7.installBeginHuawei = sdkClickResponseData.installBegin;
                    activityState7.installReferrerHuaweiAppGallery = sdkClickResponseData.installReferrer;
                }
            } else {
                ActivityState activityState8 = this.activityState;
                activityState8.clickTimeHuawei = sdkClickResponseData.clickTime;
                activityState8.installBeginHuawei = sdkClickResponseData.installBegin;
                activityState8.installReferrerHuawei = sdkClickResponseData.installReferrer;
            }
            writeActivityStateI();
        }
    }

    private void checkForPreinstallI() {
        ActivityState activityState2 = this.activityState;
        if (activityState2 != null && activityState2.enabled && !activityState2.isGdprForgotten) {
            sendPreinstallReferrerI();
            if (this.adjustConfig.preinstallTrackingEnabled && !this.internalState.hasPreinstallBeenRead()) {
                String str = this.deviceInfo.packageName;
                if (str == null || str.isEmpty()) {
                    this.logger.debug("Can't read preinstall payload, invalid package name", new Object[0]);
                    return;
                }
                SharedPreferencesManager defaultInstance = SharedPreferencesManager.getDefaultInstance(getContext());
                long preinstallPayloadReadStatus = defaultInstance.getPreinstallPayloadReadStatus();
                if (PreinstallUtil.hasAllLocationsBeenRead(preinstallPayloadReadStatus)) {
                    this.internalState.preinstallHasBeenRead = true;
                    return;
                }
                if (PreinstallUtil.hasNotBeenRead(Constants.SYSTEM_PROPERTIES, preinstallPayloadReadStatus)) {
                    String payloadFromSystemProperty = PreinstallUtil.getPayloadFromSystemProperty(this.deviceInfo.packageName, this.logger);
                    if (payloadFromSystemProperty == null || payloadFromSystemProperty.isEmpty()) {
                        preinstallPayloadReadStatus = PreinstallUtil.markAsRead(Constants.SYSTEM_PROPERTIES, preinstallPayloadReadStatus);
                    } else {
                        this.sdkClickHandler.sendPreinstallPayload(payloadFromSystemProperty, Constants.SYSTEM_PROPERTIES);
                    }
                }
                if (PreinstallUtil.hasNotBeenRead(Constants.SYSTEM_PROPERTIES_REFLECTION, preinstallPayloadReadStatus)) {
                    String payloadFromSystemPropertyReflection = PreinstallUtil.getPayloadFromSystemPropertyReflection(this.deviceInfo.packageName, this.logger);
                    if (payloadFromSystemPropertyReflection == null || payloadFromSystemPropertyReflection.isEmpty()) {
                        preinstallPayloadReadStatus = PreinstallUtil.markAsRead(Constants.SYSTEM_PROPERTIES_REFLECTION, preinstallPayloadReadStatus);
                    } else {
                        this.sdkClickHandler.sendPreinstallPayload(payloadFromSystemPropertyReflection, Constants.SYSTEM_PROPERTIES_REFLECTION);
                    }
                }
                if (PreinstallUtil.hasNotBeenRead(Constants.SYSTEM_PROPERTIES_PATH, preinstallPayloadReadStatus)) {
                    String payloadFromSystemPropertyFilePath = PreinstallUtil.getPayloadFromSystemPropertyFilePath(this.deviceInfo.packageName, this.logger);
                    if (payloadFromSystemPropertyFilePath == null || payloadFromSystemPropertyFilePath.isEmpty()) {
                        preinstallPayloadReadStatus = PreinstallUtil.markAsRead(Constants.SYSTEM_PROPERTIES_PATH, preinstallPayloadReadStatus);
                    } else {
                        this.sdkClickHandler.sendPreinstallPayload(payloadFromSystemPropertyFilePath, Constants.SYSTEM_PROPERTIES_PATH);
                    }
                }
                if (PreinstallUtil.hasNotBeenRead(Constants.SYSTEM_PROPERTIES_PATH_REFLECTION, preinstallPayloadReadStatus)) {
                    String payloadFromSystemPropertyFilePathReflection = PreinstallUtil.getPayloadFromSystemPropertyFilePathReflection(this.deviceInfo.packageName, this.logger);
                    if (payloadFromSystemPropertyFilePathReflection == null || payloadFromSystemPropertyFilePathReflection.isEmpty()) {
                        preinstallPayloadReadStatus = PreinstallUtil.markAsRead(Constants.SYSTEM_PROPERTIES_PATH_REFLECTION, preinstallPayloadReadStatus);
                    } else {
                        this.sdkClickHandler.sendPreinstallPayload(payloadFromSystemPropertyFilePathReflection, Constants.SYSTEM_PROPERTIES_PATH_REFLECTION);
                    }
                }
                if (PreinstallUtil.hasNotBeenRead(Constants.CONTENT_PROVIDER, preinstallPayloadReadStatus)) {
                    String payloadFromContentProviderDefault = PreinstallUtil.getPayloadFromContentProviderDefault(this.adjustConfig.context, this.deviceInfo.packageName, this.logger);
                    if (payloadFromContentProviderDefault == null || payloadFromContentProviderDefault.isEmpty()) {
                        preinstallPayloadReadStatus = PreinstallUtil.markAsRead(Constants.CONTENT_PROVIDER, preinstallPayloadReadStatus);
                    } else {
                        this.sdkClickHandler.sendPreinstallPayload(payloadFromContentProviderDefault, Constants.CONTENT_PROVIDER);
                    }
                }
                if (PreinstallUtil.hasNotBeenRead(Constants.CONTENT_PROVIDER_INTENT_ACTION, preinstallPayloadReadStatus)) {
                    List<String> payloadsFromContentProviderIntentAction = PreinstallUtil.getPayloadsFromContentProviderIntentAction(this.adjustConfig.context, this.deviceInfo.packageName, this.logger);
                    if (payloadsFromContentProviderIntentAction == null || payloadsFromContentProviderIntentAction.isEmpty()) {
                        preinstallPayloadReadStatus = PreinstallUtil.markAsRead(Constants.CONTENT_PROVIDER_INTENT_ACTION, preinstallPayloadReadStatus);
                    } else {
                        for (String sendPreinstallPayload : payloadsFromContentProviderIntentAction) {
                            this.sdkClickHandler.sendPreinstallPayload(sendPreinstallPayload, Constants.CONTENT_PROVIDER_INTENT_ACTION);
                        }
                    }
                }
                if (PreinstallUtil.hasNotBeenRead(Constants.CONTENT_PROVIDER_NO_PERMISSION, preinstallPayloadReadStatus)) {
                    List<String> payloadsFromContentProviderNoPermission = PreinstallUtil.getPayloadsFromContentProviderNoPermission(this.adjustConfig.context, this.deviceInfo.packageName, this.logger);
                    if (payloadsFromContentProviderNoPermission == null || payloadsFromContentProviderNoPermission.isEmpty()) {
                        preinstallPayloadReadStatus = PreinstallUtil.markAsRead(Constants.CONTENT_PROVIDER_NO_PERMISSION, preinstallPayloadReadStatus);
                    } else {
                        for (String sendPreinstallPayload2 : payloadsFromContentProviderNoPermission) {
                            this.sdkClickHandler.sendPreinstallPayload(sendPreinstallPayload2, Constants.CONTENT_PROVIDER_NO_PERMISSION);
                        }
                    }
                }
                if (PreinstallUtil.hasNotBeenRead(Constants.FILE_SYSTEM, preinstallPayloadReadStatus)) {
                    String payloadFromFileSystem = PreinstallUtil.getPayloadFromFileSystem(this.deviceInfo.packageName, this.adjustConfig.preinstallFilePath, this.logger);
                    if (payloadFromFileSystem == null || payloadFromFileSystem.isEmpty()) {
                        preinstallPayloadReadStatus = PreinstallUtil.markAsRead(Constants.FILE_SYSTEM, preinstallPayloadReadStatus);
                    } else {
                        this.sdkClickHandler.sendPreinstallPayload(payloadFromFileSystem, Constants.FILE_SYSTEM);
                    }
                }
                defaultInstance.setPreinstallPayloadReadStatus(preinstallPayloadReadStatus);
                this.internalState.preinstallHasBeenRead = true;
            }
        }
    }

    private boolean checkOrderIdI(String str) {
        if (str != null && !str.isEmpty()) {
            if (this.activityState.findOrderId(str)) {
                this.logger.info("Skipping duplicated order ID '%s'", str);
                return false;
            }
            this.activityState.addOrderId(str);
            this.logger.verbose("Added order ID '%s'", str);
        }
        return true;
    }

    private Intent createDeeplinkIntentI(Uri uri) {
        Intent intent;
        if (this.adjustConfig.deepLinkComponent == null) {
            intent = new Intent("android.intent.action.VIEW", uri);
        } else {
            AdjustConfig adjustConfig2 = this.adjustConfig;
            intent = new Intent("android.intent.action.VIEW", uri, adjustConfig2.context, adjustConfig2.deepLinkComponent);
        }
        intent.setFlags(268435456);
        intent.setPackage(this.adjustConfig.context.getPackageName());
        return intent;
    }

    /* access modifiers changed from: private */
    public void delayStartI() {
        if (!this.internalState.isNotInDelayedStart() && !isToUpdatePackagesI()) {
            Double d2 = this.adjustConfig.delayStart;
            double doubleValue = d2 != null ? d2.doubleValue() : 0.0d;
            long maxDelayStart = AdjustFactory.getMaxDelayStart();
            long j2 = (long) (1000.0d * doubleValue);
            if (j2 > maxDelayStart) {
                double d3 = (double) (maxDelayStart / 1000);
                DecimalFormat decimalFormat = Util.SecondsDisplayFormat;
                this.logger.warn("Delay start of %s seconds bigger than max allowed value of %s seconds", decimalFormat.format(doubleValue), decimalFormat.format(d3));
                doubleValue = d3;
            } else {
                maxDelayStart = j2;
            }
            this.logger.info("Waiting %s seconds before starting first session", Util.SecondsDisplayFormat.format(doubleValue));
            this.delayStartTimer.startIn(maxDelayStart);
            this.internalState.updatePackages = true;
            ActivityState activityState2 = this.activityState;
            if (activityState2 != null) {
                activityState2.updatePackages = true;
                writeActivityStateI();
            }
        }
    }

    public static boolean deleteActivityState(Context context) {
        return context.deleteFile(Constants.ACTIVITY_STATE_FILENAME);
    }

    public static boolean deleteAttribution(Context context) {
        return context.deleteFile(Constants.ATTRIBUTION_FILENAME);
    }

    public static boolean deleteSessionCallbackParameters(Context context) {
        return context.deleteFile(Constants.SESSION_CALLBACK_PARAMETERS_FILENAME);
    }

    public static boolean deleteSessionPartnerParameters(Context context) {
        return context.deleteFile(Constants.SESSION_PARTNER_PARAMETERS_FILENAME);
    }

    public static void deleteState(Context context) {
        deleteActivityState(context);
        deleteAttribution(context);
        deleteSessionCallbackParameters(context);
        deleteSessionPartnerParameters(context);
        SharedPreferencesManager.getDefaultInstance(context).clear();
    }

    private void disableThirdPartySharingForCoppaEnabledI() {
        if (shouldDisableThirdPartySharingWhenCoppaEnabled()) {
            this.activityState.isThirdPartySharingDisabledForCoppa = true;
            writeActivityStateI();
            AdjustThirdPartySharing adjustThirdPartySharing = new AdjustThirdPartySharing(Boolean.FALSE);
            PackageBuilder packageBuilder = new PackageBuilder(this.adjustConfig, this.deviceInfo, this.activityState, this.sessionParameters, System.currentTimeMillis());
            packageBuilder.internalState = this.internalState;
            ActivityPackage buildThirdPartySharingPackage = packageBuilder.buildThirdPartySharingPackage(adjustThirdPartySharing);
            this.packageHandler.addPackage(buildThirdPartySharingPackage);
            if (this.adjustConfig.eventBufferingEnabled) {
                this.logger.info("Buffered event %s", buildThirdPartySharingPackage.getSuffix());
            } else {
                this.packageHandler.sendFirstPackage();
            }
        }
    }

    /* access modifiers changed from: private */
    public void disableThirdPartySharingI() {
        SharedPreferencesManager defaultInstance = SharedPreferencesManager.getDefaultInstance(getContext());
        defaultInstance.setDisableThirdPartySharing();
        if (checkActivityStateI(this.activityState) && isEnabledI()) {
            ActivityState activityState2 = this.activityState;
            if (activityState2.isGdprForgotten || activityState2.isThirdPartySharingDisabled) {
                return;
            }
            if (this.adjustConfig.coppaCompliantEnabled) {
                this.logger.warn("Call to disable third party sharing API ignored, already done when COPPA enabled", new Object[0]);
                return;
            }
            activityState2.isThirdPartySharingDisabled = true;
            writeActivityStateI();
            PackageBuilder packageBuilder = new PackageBuilder(this.adjustConfig, this.deviceInfo, this.activityState, this.sessionParameters, System.currentTimeMillis());
            packageBuilder.internalState = this.internalState;
            ActivityPackage buildDisableThirdPartySharingPackage = packageBuilder.buildDisableThirdPartySharingPackage();
            this.packageHandler.addPackage(buildDisableThirdPartySharingPackage);
            defaultInstance.removeDisableThirdPartySharing();
            if (this.adjustConfig.eventBufferingEnabled) {
                this.logger.info("Buffered event %s", buildDisableThirdPartySharingPackage.getSuffix());
            } else {
                this.packageHandler.sendFirstPackage();
            }
        }
    }

    /* access modifiers changed from: private */
    public void endI() {
        if (!toSendI()) {
            pauseSendingI();
        }
        if (updateActivityStateI(System.currentTimeMillis())) {
            writeActivityStateI();
        }
    }

    /* access modifiers changed from: private */
    public void foregroundTimerFiredI() {
        if (!isEnabledI()) {
            stopForegroundTimerI();
            return;
        }
        if (toSendI()) {
            this.packageHandler.sendFirstPackage();
        }
        if (updateActivityStateI(System.currentTimeMillis())) {
            writeActivityStateI();
        }
    }

    /* access modifiers changed from: private */
    public void gdprForgetMeI() {
        if (checkActivityStateI(this.activityState) && isEnabledI()) {
            ActivityState activityState2 = this.activityState;
            if (!activityState2.isGdprForgotten) {
                activityState2.isGdprForgotten = true;
                writeActivityStateI();
                PackageBuilder packageBuilder = new PackageBuilder(this.adjustConfig, this.deviceInfo, this.activityState, this.sessionParameters, System.currentTimeMillis());
                packageBuilder.internalState = this.internalState;
                ActivityPackage buildGdprPackage = packageBuilder.buildGdprPackage();
                this.packageHandler.addPackage(buildGdprPackage);
                SharedPreferencesManager.getDefaultInstance(getContext()).removeGdprForgetMe();
                if (this.adjustConfig.eventBufferingEnabled) {
                    this.logger.info("Buffered event %s", buildGdprPackage.getSuffix());
                } else {
                    this.packageHandler.sendFirstPackage();
                }
            }
        }
    }

    public static ActivityHandler getInstance(AdjustConfig adjustConfig2) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        if (adjustConfig2 == null) {
            AdjustFactory.getLogger().error("AdjustConfig missing", new Object[0]);
            return null;
        } else if (!adjustConfig2.isValid()) {
            AdjustFactory.getLogger().error("AdjustConfig not initialized correctly", new Object[0]);
            return null;
        } else {
            if (adjustConfig2.processName != null) {
                int myPid = Process.myPid();
                ActivityManager activityManager = (ActivityManager) adjustConfig2.context.getSystemService("activity");
                if (activityManager != null && (runningAppProcesses = activityManager.getRunningAppProcesses()) != null) {
                    Iterator<ActivityManager.RunningAppProcessInfo> it = runningAppProcesses.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        ActivityManager.RunningAppProcessInfo next = it.next();
                        if (next.pid == myPid) {
                            if (!next.processName.equalsIgnoreCase(adjustConfig2.processName)) {
                                AdjustFactory.getLogger().info("Skipping initialization in background process (%s)", next.processName);
                                return null;
                            }
                        }
                    }
                } else {
                    return null;
                }
            }
            return new ActivityHandler(adjustConfig2);
        }
    }

    /* access modifiers changed from: private */
    public void gotOptOutResponseI() {
        this.activityState.isGdprForgotten = true;
        writeActivityStateI();
        this.packageHandler.flush();
        setEnabledI(false);
    }

    private boolean hasChangedStateI(boolean z2, boolean z3, String str, String str2) {
        if (z2 != z3) {
            return true;
        }
        ILogger iLogger = this.logger;
        if (z2) {
            iLogger.debug(str, new Object[0]);
        } else {
            iLogger.debug(str2, new Object[0]);
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void initI() {
        Double d2;
        String pushToken;
        SESSION_INTERVAL = AdjustFactory.getSessionInterval();
        SUBSESSION_INTERVAL = AdjustFactory.getSubsessionInterval();
        FOREGROUND_TIMER_INTERVAL = AdjustFactory.getTimerInterval();
        FOREGROUND_TIMER_START = AdjustFactory.getTimerStart();
        BACKGROUND_TIMER_INTERVAL = AdjustFactory.getTimerInterval();
        readAttributionI(this.adjustConfig.context);
        readActivityStateI(this.adjustConfig.context);
        this.sessionParameters = new SessionParameters();
        readSessionCallbackParametersI(this.adjustConfig.context);
        readSessionPartnerParametersI(this.adjustConfig.context);
        AdjustConfig adjustConfig2 = this.adjustConfig;
        if (adjustConfig2.startEnabled != null) {
            adjustConfig2.preLaunchActions.preLaunchActionsArray.add(new d0());
        }
        if (this.internalState.hasFirstSdkStartOcurred()) {
            InternalState internalState2 = this.internalState;
            ActivityState activityState2 = this.activityState;
            internalState2.enabled = activityState2.enabled;
            internalState2.updatePackages = activityState2.updatePackages;
            internalState2.firstLaunch = false;
        } else {
            this.internalState.firstLaunch = true;
        }
        readConfigFile(this.adjustConfig.context);
        this.deviceInfo = new DeviceInfo(this.adjustConfig);
        if (this.adjustConfig.eventBufferingEnabled) {
            this.logger.info("Event buffering is enabled", new Object[0]);
        }
        this.deviceInfo.reloadPlayIds(this.adjustConfig);
        if (this.deviceInfo.playAdId == null) {
            if (!Util.canReadPlayIds(this.adjustConfig)) {
                if (this.adjustConfig.coppaCompliantEnabled) {
                    this.logger.info("Cannot read Google Play Services Advertising ID with COPPA enabled", new Object[0]);
                }
                if (this.adjustConfig.playStoreKidsAppEnabled) {
                    this.logger.info("Cannot read Google Play Services Advertising ID with play store kids app enabled", new Object[0]);
                }
            } else {
                this.logger.warn("Unable to get Google Play Services Advertising ID at start time", new Object[0]);
            }
            if (this.deviceInfo.androidId == null) {
                if (!Util.canReadNonPlayIds(this.adjustConfig)) {
                    if (this.adjustConfig.coppaCompliantEnabled) {
                        this.logger.info("Cannot read non Play IDs with COPPA enabled", new Object[0]);
                    }
                    if (this.adjustConfig.playStoreKidsAppEnabled) {
                        this.logger.info("Cannot read non Play IDs with play store kids app enabled", new Object[0]);
                    }
                } else {
                    this.logger.error("Unable to get any Device IDs. Please check if Proguard is correctly set with Adjust SDK", new Object[0]);
                }
            }
        } else {
            this.logger.info("Google Play Services Advertising ID read correctly at start time", new Object[0]);
        }
        String str = this.adjustConfig.defaultTracker;
        if (str != null) {
            this.logger.info("Default tracker: '%s'", str);
        }
        String str2 = this.adjustConfig.pushToken;
        if (str2 != null) {
            this.logger.info("Push token: '%s'", str2);
            if (this.internalState.hasFirstSdkStartOcurred()) {
                setPushToken(this.adjustConfig.pushToken, false);
            } else {
                SharedPreferencesManager.getDefaultInstance(getContext()).savePushToken(this.adjustConfig.pushToken);
            }
        } else if (this.internalState.hasFirstSdkStartOcurred() && (pushToken = SharedPreferencesManager.getDefaultInstance(getContext()).getPushToken()) != null) {
            setPushToken(pushToken, true);
        }
        if (this.cachedDeeplinkResolutionCallback == null) {
            this.cachedDeeplinkResolutionCallback = this.adjustConfig.cachedDeeplinkResolutionCallback;
        }
        if (this.internalState.hasFirstSdkStartOcurred() && SharedPreferencesManager.getDefaultInstance(getContext()).getGdprForgetMe()) {
            gdprForgetMe();
        }
        this.foregroundTimer = new TimerCycle(new e0(), FOREGROUND_TIMER_START, FOREGROUND_TIMER_INTERVAL, FOREGROUND_TIMER_NAME);
        if (this.adjustConfig.sendInBackground) {
            this.logger.info("Send in background configured", new Object[0]);
            this.backgroundTimer = new TimerOnce(new f0(), BACKGROUND_TIMER_NAME);
        }
        if (this.internalState.hasFirstSdkStartNotOcurred() && (d2 = this.adjustConfig.delayStart) != null && d2.doubleValue() > 0.0d) {
            this.logger.info("Delay start configured", new Object[0]);
            this.internalState.delayStart = true;
            this.delayStartTimer = new TimerOnce(new h0(), DELAY_START_TIMER_NAME);
        }
        UtilNetworking.setUserAgent(this.adjustConfig.userAgent);
        AdjustConfig adjustConfig3 = this.adjustConfig;
        this.packageHandler = AdjustFactory.getPackageHandler(this, this.adjustConfig.context, toSendI(false), new ActivityPackageSender(adjustConfig3.urlStrategy, adjustConfig3.basePath, adjustConfig3.gdprPath, adjustConfig3.subscriptionPath, adjustConfig3.purchaseVerificationPath, this.deviceInfo.clientSdk, adjustConfig3.context));
        AdjustConfig adjustConfig4 = this.adjustConfig;
        this.attributionHandler = AdjustFactory.getAttributionHandler(this, toSendI(false), new ActivityPackageSender(adjustConfig4.urlStrategy, adjustConfig4.basePath, adjustConfig4.gdprPath, adjustConfig4.subscriptionPath, adjustConfig4.purchaseVerificationPath, this.deviceInfo.clientSdk, adjustConfig4.context));
        AdjustConfig adjustConfig5 = this.adjustConfig;
        this.sdkClickHandler = AdjustFactory.getSdkClickHandler(this, toSendI(true), new ActivityPackageSender(adjustConfig5.urlStrategy, adjustConfig5.basePath, adjustConfig5.gdprPath, adjustConfig5.subscriptionPath, adjustConfig5.purchaseVerificationPath, this.deviceInfo.clientSdk, adjustConfig5.context));
        AdjustConfig adjustConfig6 = this.adjustConfig;
        this.purchaseVerificationHandler = AdjustFactory.getPurchaseVerificationHandler(this, toSendI(true), new ActivityPackageSender(adjustConfig6.urlStrategy, adjustConfig6.basePath, adjustConfig6.gdprPath, adjustConfig6.subscriptionPath, adjustConfig6.purchaseVerificationPath, this.deviceInfo.clientSdk, adjustConfig6.context));
        if (isToUpdatePackagesI()) {
            updatePackagesI();
        }
        this.installReferrer = new InstallReferrer(this.adjustConfig.context, new i0());
        this.installReferrerHuawei = new InstallReferrerHuawei(this.adjustConfig.context, new j0());
        AdjustConfig adjustConfig7 = this.adjustConfig;
        this.installReferrerMeta = new InstallReferrerMeta(adjustConfig7.context, adjustConfig7.fbAppId, new k0());
        preLaunchActionsI(this.adjustConfig.preLaunchActions.preLaunchActionsArray);
        sendReftagReferrerI();
    }

    private boolean isEnabledI() {
        ActivityState activityState2 = this.activityState;
        return activityState2 != null ? activityState2.enabled : this.internalState.isEnabled();
    }

    private boolean isToUpdatePackagesI() {
        ActivityState activityState2 = this.activityState;
        return activityState2 != null ? activityState2.updatePackages : this.internalState.itHasToUpdatePackages();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0004, code lost:
        r1 = r1.installReferrer;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isValidReferrerDetails(com.adjust.sdk.ReferrerDetails r1) {
        /*
            r0 = this;
            r0 = 0
            if (r1 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.lang.String r1 = r1.installReferrer
            if (r1 != 0) goto L_0x0009
            return r0
        L_0x0009:
            int r1 = r1.length()
            if (r1 == 0) goto L_0x0010
            r0 = 1
        L_0x0010:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adjust.sdk.ActivityHandler.isValidReferrerDetails(com.adjust.sdk.ReferrerDetails):boolean");
    }

    private void launchAttributionListenerI(Handler handler) {
        if (this.adjustConfig.onAttributionChangedListener != null) {
            handler.post(new u0());
        }
    }

    /* access modifiers changed from: private */
    public void launchAttributionResponseTasksI(AttributionResponseData attributionResponseData) {
        updateAdidI(attributionResponseData.adid);
        Handler handler = new Handler(this.adjustConfig.context.getMainLooper());
        if (updateAttributionI(attributionResponseData.attribution)) {
            launchAttributionListenerI(handler);
        }
        prepareDeeplinkI(attributionResponseData.deeplink, handler);
    }

    /* access modifiers changed from: private */
    public void launchDeeplinkMain(Intent intent, Uri uri) {
        if (this.adjustConfig.context.getPackageManager().queryIntentActivities(intent, 0).size() > 0) {
            this.logger.info("Open deferred deep link (%s)", uri);
            this.adjustConfig.context.startActivity(intent);
            return;
        }
        this.logger.error("Unable to open deferred deep link (%s)", uri);
    }

    /* access modifiers changed from: private */
    public void launchEventResponseTasksI(EventResponseData eventResponseData) {
        Runnable p0Var;
        updateAdidI(eventResponseData.adid);
        Handler handler = new Handler(this.adjustConfig.context.getMainLooper());
        boolean z2 = eventResponseData.success;
        if (z2 && this.adjustConfig.onEventTrackingSucceededListener != null) {
            this.logger.debug("Launching success event tracking listener", new Object[0]);
            p0Var = new o0(eventResponseData);
        } else if (!z2 && this.adjustConfig.onEventTrackingFailedListener != null) {
            this.logger.debug("Launching failed event tracking listener", new Object[0]);
            p0Var = new p0(eventResponseData);
        } else {
            return;
        }
        handler.post(p0Var);
    }

    /* access modifiers changed from: private */
    public void launchPurchaseVerificationResponseTasksI(PurchaseVerificationResponseData purchaseVerificationResponseData) {
        Handler handler = new Handler(this.adjustConfig.context.getMainLooper());
        JSONObject jSONObject = purchaseVerificationResponseData.jsonResponse;
        handler.post(new v0(purchaseVerificationResponseData, jSONObject == null ? new AdjustPurchaseVerificationResult("not_verified", 101, purchaseVerificationResponseData.message) : new AdjustPurchaseVerificationResult(UtilNetworking.extractJsonString(jSONObject, "verification_status"), UtilNetworking.extractJsonInt(jSONObject, "code"), UtilNetworking.extractJsonString(jSONObject, PushMessagingService.KEY_MESSAGE))));
    }

    /* access modifiers changed from: private */
    public void launchSdkClickResponseTasksI(SdkClickResponseData sdkClickResponseData) {
        updateAdidI(sdkClickResponseData.adid);
        Handler handler = new Handler(this.adjustConfig.context.getMainLooper());
        if (updateAttributionI(sdkClickResponseData.attribution)) {
            launchAttributionListenerI(handler);
        }
        if (!TextUtils.isEmpty(sdkClickResponseData.resolvedDeeplink)) {
            OnDeeplinkResolvedListener onDeeplinkResolvedListener = this.cachedDeeplinkResolutionCallback;
            this.cachedDeeplinkResolutionCallback = null;
            if (onDeeplinkResolvedListener != null) {
                handler.post(new q0(onDeeplinkResolvedListener, sdkClickResponseData));
            }
        }
    }

    private void launchSessionResponseListenerI(SessionResponseData sessionResponseData, Handler handler) {
        Runnable t0Var;
        boolean z2 = sessionResponseData.success;
        if (z2 && this.adjustConfig.onSessionTrackingSucceededListener != null) {
            this.logger.debug("Launching success session tracking listener", new Object[0]);
            t0Var = new s0(sessionResponseData);
        } else if (!z2 && this.adjustConfig.onSessionTrackingFailedListener != null) {
            this.logger.debug("Launching failed session tracking listener", new Object[0]);
            t0Var = new t0(sessionResponseData);
        } else {
            return;
        }
        handler.post(t0Var);
    }

    /* access modifiers changed from: private */
    public void launchSessionResponseTasksI(SessionResponseData sessionResponseData) {
        this.logger.debug("Launching SessionResponse tasks", new Object[0]);
        updateAdidI(sessionResponseData.adid);
        Handler handler = new Handler(this.adjustConfig.context.getMainLooper());
        if (updateAttributionI(sessionResponseData.attribution)) {
            launchAttributionListenerI(handler);
        }
        if (this.attribution == null && !this.activityState.askingAttribution) {
            this.attributionHandler.getAttribution();
        }
        if (sessionResponseData.success) {
            SharedPreferencesManager.getDefaultInstance(getContext()).setInstallTracked();
        }
        launchSessionResponseListenerI(sessionResponseData, handler);
        this.internalState.sessionResponseProcessed = true;
    }

    private void pauseSendingI() {
        this.attributionHandler.pauseSending();
        this.packageHandler.pauseSending();
        if (!toSendI(true)) {
            this.sdkClickHandler.pauseSending();
            this.purchaseVerificationHandler.pauseSending();
            return;
        }
        this.sdkClickHandler.resumeSending();
        this.purchaseVerificationHandler.resumeSending();
    }

    private boolean pausedI() {
        return pausedI(false);
    }

    private void preLaunchActionsI(List<IRunActivityHandler> list) {
        if (list != null) {
            for (IRunActivityHandler run : list) {
                run.run(this);
            }
        }
    }

    private void prepareDeeplinkI(Uri uri, Handler handler) {
        if (uri != null) {
            this.logger.info("Deferred deeplink received (%s)", uri);
            handler.post(new w0(createDeeplinkIntentI(uri), uri));
        }
    }

    private void processCachedDeeplinkI() {
        if (checkActivityStateI(this.activityState)) {
            SharedPreferencesManager defaultInstance = SharedPreferencesManager.getDefaultInstance(getContext());
            String deeplinkUrl = defaultInstance.getDeeplinkUrl();
            long deeplinkClickTime = defaultInstance.getDeeplinkClickTime();
            if (deeplinkUrl != null && deeplinkClickTime != -1) {
                readOpenUrl(Uri.parse(deeplinkUrl), deeplinkClickTime);
                defaultInstance.removeDeeplink();
            }
        }
    }

    private void processCoppaComplianceI() {
        if (!this.adjustConfig.coppaCompliantEnabled) {
            resetThirdPartySharingCoppaActivityStateI();
        } else {
            disableThirdPartySharingForCoppaEnabledI();
        }
    }

    private void processSessionI() {
        if (!this.activityState.isGdprForgotten) {
            long currentTimeMillis = System.currentTimeMillis();
            ActivityState activityState2 = this.activityState;
            long j2 = currentTimeMillis - activityState2.lastActivity;
            if (j2 < 0) {
                this.logger.error(TIME_TRAVEL, new Object[0]);
                this.activityState.lastActivity = currentTimeMillis;
                writeActivityStateI();
            } else if (j2 > SESSION_INTERVAL) {
                trackNewSessionI(currentTimeMillis);
                checkAfterNewStartI();
            } else if (j2 > SUBSESSION_INTERVAL) {
                int i3 = activityState2.subsessionCount + 1;
                activityState2.subsessionCount = i3;
                activityState2.sessionLength += j2;
                activityState2.lastActivity = currentTimeMillis;
                this.logger.verbose("Started subsession %d of session %d", Integer.valueOf(i3), Integer.valueOf(this.activityState.sessionCount));
                writeActivityStateI();
                checkForPreinstallI();
                this.installReferrer.startConnection();
                this.installReferrerHuawei.readReferrer();
                this.installReferrerMeta.readReferrer();
                readInstallReferrerSamsung();
                readInstallReferrerXiaomi();
                readInstallReferrerVivo();
            } else {
                this.logger.verbose("Time span since last activity too short for a new subsession", new Object[0]);
            }
        }
    }

    private void readActivityStateI(Context context) {
        try {
            this.activityState = (ActivityState) Util.readObject(context, Constants.ACTIVITY_STATE_FILENAME, ACTIVITY_STATE_NAME, ActivityState.class);
        } catch (Exception e3) {
            this.logger.error("Failed to read %s file (%s)", ACTIVITY_STATE_NAME, e3.getMessage());
            this.activityState = null;
        }
        if (this.activityState != null) {
            this.internalState.firstSdkStart = true;
        }
    }

    private void readAttributionI(Context context) {
        try {
            this.attribution = (AdjustAttribution) Util.readObject(context, Constants.ATTRIBUTION_FILENAME, ATTRIBUTION_NAME, AdjustAttribution.class);
        } catch (Exception e3) {
            this.logger.error("Failed to read %s file (%s)", ATTRIBUTION_NAME, e3.getMessage());
            this.attribution = null;
        }
    }

    private void readConfigFile(Context context) {
        try {
            InputStream open = context.getAssets().open("adjust_config.properties");
            Properties properties = new Properties();
            properties.load(open);
            this.logger.verbose("adjust_config.properties file read and loaded", new Object[0]);
            String property = properties.getProperty("defaultTracker");
            if (property != null) {
                this.adjustConfig.defaultTracker = property;
            }
        } catch (Exception e3) {
            this.logger.debug("%s file not found in this app", e3.getMessage());
        }
    }

    private void readInstallReferrerSamsung() {
        this.executor.submit(new l0());
    }

    private void readInstallReferrerVivo() {
        this.executor.submit(new n0());
    }

    private void readInstallReferrerXiaomi() {
        this.executor.submit(new m0());
    }

    /* access modifiers changed from: private */
    public void readOpenUrlI(Uri uri, long j2) {
        if (isEnabledI()) {
            if (Util.isUrlFilteredOut(uri)) {
                ILogger iLogger = this.logger;
                iLogger.debug("Deeplink (" + uri.toString() + ") processing skipped", new Object[0]);
                return;
            }
            ActivityPackage buildDeeplinkSdkClickPackage = PackageFactory.buildDeeplinkSdkClickPackage(uri, j2, this.activityState, this.adjustConfig, this.deviceInfo, this.sessionParameters, this.internalState);
            if (buildDeeplinkSdkClickPackage != null) {
                this.sdkClickHandler.sendSdkClick(buildDeeplinkSdkClickPackage);
            }
        }
    }

    private void readSessionCallbackParametersI(Context context) {
        try {
            this.sessionParameters.callbackParameters = (Map) Util.readObject(context, Constants.SESSION_CALLBACK_PARAMETERS_FILENAME, SESSION_CALLBACK_PARAMETERS_NAME, Map.class);
        } catch (Exception e3) {
            this.logger.error("Failed to read %s file (%s)", SESSION_CALLBACK_PARAMETERS_NAME, e3.getMessage());
            this.sessionParameters.callbackParameters = null;
        }
    }

    private void readSessionPartnerParametersI(Context context) {
        try {
            this.sessionParameters.partnerParameters = (Map) Util.readObject(context, Constants.SESSION_PARTNER_PARAMETERS_FILENAME, SESSION_PARTNER_PARAMETERS_NAME, Map.class);
        } catch (Exception e3) {
            this.logger.error("Failed to read %s file (%s)", SESSION_PARTNER_PARAMETERS_NAME, e3.getMessage());
            this.sessionParameters.partnerParameters = null;
        }
    }

    private void resetThirdPartySharingCoppaActivityStateI() {
        ActivityState activityState2 = this.activityState;
        if (activityState2 != null && activityState2.isThirdPartySharingDisabledForCoppa) {
            activityState2.isThirdPartySharingDisabledForCoppa = false;
            writeActivityStateI();
        }
    }

    private void resumeSendingI() {
        this.attributionHandler.resumeSending();
        this.packageHandler.resumeSending();
        this.sdkClickHandler.resumeSending();
        this.purchaseVerificationHandler.resumeSending();
    }

    /* access modifiers changed from: private */
    public void sendFirstPackagesI() {
        if (this.internalState.isNotInDelayedStart()) {
            this.logger.info("Start delay expired or never configured", new Object[0]);
            return;
        }
        updatePackagesI();
        this.internalState.delayStart = false;
        this.delayStartTimer.cancel();
        this.delayStartTimer = null;
        updateHandlersStatusAndSendI();
    }

    /* access modifiers changed from: private */
    public void sendInstallReferrerI(ReferrerDetails referrerDetails, String str) {
        if (isEnabledI() && isValidReferrerDetails(referrerDetails) && !Util.isEqualReferrerDetails(referrerDetails, str, this.activityState)) {
            this.sdkClickHandler.sendSdkClick(PackageFactory.buildInstallReferrerSdkClickPackage(referrerDetails, str, this.activityState, this.adjustConfig, this.deviceInfo, this.sessionParameters, this.internalState));
        }
    }

    /* access modifiers changed from: private */
    public void sendPreinstallReferrerI() {
        String preinstallReferrer;
        if (isEnabledI() && !this.internalState.hasFirstSdkStartNotOcurred() && (preinstallReferrer = SharedPreferencesManager.getDefaultInstance(getContext()).getPreinstallReferrer()) != null && !preinstallReferrer.isEmpty()) {
            this.sdkClickHandler.sendPreinstallPayload(preinstallReferrer, Constants.SYSTEM_INSTALLER_REFERRER);
        }
    }

    /* access modifiers changed from: private */
    public void sendReftagReferrerI() {
        if (isEnabledI() && !this.internalState.hasFirstSdkStartNotOcurred()) {
            this.sdkClickHandler.sendReftagReferrers();
        }
    }

    /* access modifiers changed from: private */
    public void setAskingAttributionI(boolean z2) {
        this.activityState.askingAttribution = z2;
        writeActivityStateI();
    }

    /* access modifiers changed from: private */
    public void setEnabledI(boolean z2) {
        ActivityState activityState2;
        if (hasChangedStateI(isEnabledI(), z2, "Adjust already enabled", "Adjust already disabled")) {
            if (!z2 || (activityState2 = this.activityState) == null || !activityState2.isGdprForgotten) {
                InternalState internalState2 = this.internalState;
                internalState2.enabled = z2;
                if (internalState2.hasFirstSdkStartNotOcurred()) {
                    updateStatusI(!z2, "Handlers will start as paused due to the SDK being disabled", "Handlers will still start as paused", "Handlers will start as active due to the SDK being enabled");
                    return;
                }
                this.activityState.enabled = z2;
                writeActivityStateI();
                if (z2) {
                    SharedPreferencesManager defaultInstance = SharedPreferencesManager.getDefaultInstance(getContext());
                    if (defaultInstance.getGdprForgetMe()) {
                        gdprForgetMeI();
                    } else {
                        processCoppaComplianceI();
                        if (defaultInstance.getDisableThirdPartySharing()) {
                            disableThirdPartySharingI();
                        }
                        for (AdjustThirdPartySharing trackThirdPartySharingI : this.adjustConfig.preLaunchActions.preLaunchAdjustThirdPartySharingArray) {
                            trackThirdPartySharingI(trackThirdPartySharingI);
                        }
                        Boolean bool = this.adjustConfig.preLaunchActions.lastMeasurementConsentTracked;
                        if (bool != null) {
                            trackMeasurementConsentI(bool.booleanValue());
                        }
                        this.adjustConfig.preLaunchActions.preLaunchAdjustThirdPartySharingArray = new ArrayList();
                        this.adjustConfig.preLaunchActions.lastMeasurementConsentTracked = null;
                    }
                    if (!defaultInstance.getInstallTracked()) {
                        this.logger.debug("Detected that install was not tracked at enable time", new Object[0]);
                        trackNewSessionI(System.currentTimeMillis());
                    }
                    checkAfterNewStartI(defaultInstance);
                }
                updateStatusI(!z2, "Pausing handlers due to SDK being disabled", "Handlers remain paused", "Resuming handlers due to SDK being enabled");
                return;
            }
            this.logger.error("Re-enabling SDK not possible for forgotten user", new Object[0]);
        }
    }

    /* access modifiers changed from: private */
    public void setOfflineModeI(boolean z2) {
        String str;
        String str2;
        String str3;
        if (hasChangedStateI(this.internalState.isOffline(), z2, "Adjust already in offline mode", "Adjust already in online mode")) {
            InternalState internalState2 = this.internalState;
            internalState2.offline = z2;
            if (internalState2.hasFirstSdkStartNotOcurred()) {
                str = "Handlers will still start as paused";
                str2 = "Handlers will start as active due to SDK being online";
                str3 = "Handlers will start paused due to SDK being offline";
            } else {
                str = "Handlers remain paused";
                str2 = "Resuming handlers to put SDK in online mode";
                str3 = "Pausing handlers to put SDK offline mode";
            }
            updateStatusI(z2, str3, str, str2);
        }
    }

    /* access modifiers changed from: private */
    public void setPushTokenI(String str) {
        if (checkActivityStateI(this.activityState) && isEnabledI()) {
            ActivityState activityState2 = this.activityState;
            if (!activityState2.isGdprForgotten && str != null && !str.equals(activityState2.pushToken)) {
                this.activityState.pushToken = str;
                writeActivityStateI();
                PackageBuilder packageBuilder = new PackageBuilder(this.adjustConfig, this.deviceInfo, this.activityState, this.sessionParameters, System.currentTimeMillis());
                packageBuilder.internalState = this.internalState;
                ActivityPackage buildInfoPackage = packageBuilder.buildInfoPackage(Constants.PUSH);
                this.packageHandler.addPackage(buildInfoPackage);
                SharedPreferencesManager.getDefaultInstance(getContext()).removePushToken();
                if (this.adjustConfig.eventBufferingEnabled) {
                    this.logger.info("Buffered event %s", buildInfoPackage.getSuffix());
                } else {
                    this.packageHandler.sendFirstPackage();
                }
            }
        }
    }

    private boolean shouldDisableThirdPartySharingWhenCoppaEnabled() {
        if (this.activityState == null || !isEnabledI()) {
            return false;
        }
        ActivityState activityState2 = this.activityState;
        if (activityState2.isGdprForgotten) {
            return false;
        }
        return !activityState2.isThirdPartySharingDisabledForCoppa;
    }

    /* access modifiers changed from: private */
    public void startBackgroundTimerI() {
        if (this.backgroundTimer != null && toSendI() && this.backgroundTimer.getFireIn() <= 0) {
            this.backgroundTimer.startIn(BACKGROUND_TIMER_INTERVAL);
        }
    }

    private void startFirstSessionI() {
        this.activityState = new ActivityState();
        this.internalState.firstSdkStart = true;
        updateHandlersStatusAndSendI();
        long currentTimeMillis = System.currentTimeMillis();
        SharedPreferencesManager defaultInstance = SharedPreferencesManager.getDefaultInstance(getContext());
        this.activityState.pushToken = defaultInstance.getPushToken();
        if (this.internalState.isEnabled()) {
            if (defaultInstance.getGdprForgetMe()) {
                gdprForgetMeI();
            } else {
                processCoppaComplianceI();
                if (defaultInstance.getDisableThirdPartySharing()) {
                    disableThirdPartySharingI();
                }
                for (AdjustThirdPartySharing trackThirdPartySharingI : this.adjustConfig.preLaunchActions.preLaunchAdjustThirdPartySharingArray) {
                    trackThirdPartySharingI(trackThirdPartySharingI);
                }
                Boolean bool = this.adjustConfig.preLaunchActions.lastMeasurementConsentTracked;
                if (bool != null) {
                    trackMeasurementConsentI(bool.booleanValue());
                }
                this.adjustConfig.preLaunchActions.preLaunchAdjustThirdPartySharingArray = new ArrayList();
                this.adjustConfig.preLaunchActions.lastMeasurementConsentTracked = null;
                this.activityState.sessionCount = 1;
                transferSessionPackageI(currentTimeMillis);
                checkAfterNewStartI(defaultInstance);
            }
        }
        this.activityState.resetSessionAttributes(currentTimeMillis);
        this.activityState.enabled = this.internalState.isEnabled();
        this.activityState.updatePackages = this.internalState.itHasToUpdatePackages();
        writeActivityStateI();
        defaultInstance.removePushToken();
        defaultInstance.removeGdprForgetMe();
        defaultInstance.removeDisableThirdPartySharing();
        processCachedDeeplinkI();
    }

    /* access modifiers changed from: private */
    public void startForegroundTimerI() {
        if (isEnabledI()) {
            this.foregroundTimer.start();
        }
    }

    /* access modifiers changed from: private */
    public void startI() {
        if (this.internalState.hasFirstSdkStartNotOcurred()) {
            AdjustSigner.onResume(this.adjustConfig.logger);
            startFirstSessionI();
            return;
        }
        if (SharedPreferencesManager.getDefaultInstance(getContext()).getDisableThirdPartySharing()) {
            disableThirdPartySharingI();
        }
        for (AdjustThirdPartySharing trackThirdPartySharingI : this.adjustConfig.preLaunchActions.preLaunchAdjustThirdPartySharingArray) {
            trackThirdPartySharingI(trackThirdPartySharingI);
        }
        Boolean bool = this.adjustConfig.preLaunchActions.lastMeasurementConsentTracked;
        if (bool != null) {
            trackMeasurementConsentI(bool.booleanValue());
        }
        this.adjustConfig.preLaunchActions.preLaunchAdjustThirdPartySharingArray = new ArrayList();
        AdjustConfig adjustConfig2 = this.adjustConfig;
        adjustConfig2.preLaunchActions.lastMeasurementConsentTracked = null;
        if (this.activityState.enabled) {
            AdjustSigner.onResume(adjustConfig2.logger);
            updateHandlersStatusAndSendI();
            processCoppaComplianceI();
            processSessionI();
            checkAttributionStateI();
            processCachedDeeplinkI();
        }
    }

    /* access modifiers changed from: private */
    public void stopBackgroundTimerI() {
        TimerOnce timerOnce = this.backgroundTimer;
        if (timerOnce != null) {
            timerOnce.cancel();
        }
    }

    /* access modifiers changed from: private */
    public void stopForegroundTimerI() {
        this.foregroundTimer.suspend();
    }

    private void teardownActivityStateS() {
        synchronized (ActivityState.class) {
            try {
                if (this.activityState != null) {
                    this.activityState = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void teardownAllSessionParametersS() {
        synchronized (SessionParameters.class) {
            try {
                if (this.sessionParameters != null) {
                    this.sessionParameters = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void teardownAttributionS() {
        synchronized (AdjustAttribution.class) {
            try {
                if (this.attribution != null) {
                    this.attribution = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private boolean toSendI() {
        return toSendI(false);
    }

    /* access modifiers changed from: private */
    public void trackAdRevenueI(AdjustAdRevenue adjustAdRevenue) {
        if (checkActivityStateI(this.activityState) && isEnabledI() && checkAdjustAdRevenue(adjustAdRevenue) && !this.activityState.isGdprForgotten) {
            PackageBuilder packageBuilder = new PackageBuilder(this.adjustConfig, this.deviceInfo, this.activityState, this.sessionParameters, System.currentTimeMillis());
            InternalState internalState2 = this.internalState;
            packageBuilder.internalState = internalState2;
            this.packageHandler.addPackage(packageBuilder.buildAdRevenuePackage(adjustAdRevenue, internalState2.isInDelayedStart()));
            this.packageHandler.sendFirstPackage();
        }
    }

    /* access modifiers changed from: private */
    public void trackEventI(AdjustEvent adjustEvent) {
        if (checkActivityStateI(this.activityState) && isEnabledI() && checkEventI(adjustEvent) && checkOrderIdI(adjustEvent.orderId) && !this.activityState.isGdprForgotten) {
            long currentTimeMillis = System.currentTimeMillis();
            this.activityState.eventCount++;
            updateActivityStateI(currentTimeMillis);
            PackageBuilder packageBuilder = new PackageBuilder(this.adjustConfig, this.deviceInfo, this.activityState, this.sessionParameters, currentTimeMillis);
            InternalState internalState2 = this.internalState;
            packageBuilder.internalState = internalState2;
            ActivityPackage buildEventPackage = packageBuilder.buildEventPackage(adjustEvent, internalState2.isInDelayedStart());
            this.packageHandler.addPackage(buildEventPackage);
            if (this.adjustConfig.eventBufferingEnabled) {
                this.logger.info("Buffered event %s", buildEventPackage.getSuffix());
            } else {
                this.packageHandler.sendFirstPackage();
            }
            if (this.adjustConfig.sendInBackground && this.internalState.isInBackground()) {
                startBackgroundTimerI();
            }
            writeActivityStateI();
        }
    }

    /* access modifiers changed from: private */
    public void trackMeasurementConsentI(boolean z2) {
        if (!checkActivityStateI(this.activityState)) {
            this.adjustConfig.preLaunchActions.lastMeasurementConsentTracked = Boolean.valueOf(z2);
        } else if (isEnabledI() && !this.activityState.isGdprForgotten) {
            PackageBuilder packageBuilder = new PackageBuilder(this.adjustConfig, this.deviceInfo, this.activityState, this.sessionParameters, System.currentTimeMillis());
            packageBuilder.internalState = this.internalState;
            ActivityPackage buildMeasurementConsentPackage = packageBuilder.buildMeasurementConsentPackage(z2);
            this.packageHandler.addPackage(buildMeasurementConsentPackage);
            if (this.adjustConfig.eventBufferingEnabled) {
                this.logger.info("Buffered event %s", buildMeasurementConsentPackage.getSuffix());
            } else {
                this.packageHandler.sendFirstPackage();
            }
        }
    }

    private void trackNewSessionI(long j2) {
        ActivityState activityState2 = this.activityState;
        activityState2.sessionCount++;
        activityState2.lastInterval = j2 - activityState2.lastActivity;
        transferSessionPackageI(j2);
        this.activityState.resetSessionAttributes(j2);
        writeActivityStateI();
    }

    /* access modifiers changed from: private */
    public void trackSubscriptionI(AdjustPlayStoreSubscription adjustPlayStoreSubscription) {
        if (checkActivityStateI(this.activityState) && isEnabledI() && !this.activityState.isGdprForgotten) {
            PackageBuilder packageBuilder = new PackageBuilder(this.adjustConfig, this.deviceInfo, this.activityState, this.sessionParameters, System.currentTimeMillis());
            InternalState internalState2 = this.internalState;
            packageBuilder.internalState = internalState2;
            this.packageHandler.addPackage(packageBuilder.buildSubscriptionPackage(adjustPlayStoreSubscription, internalState2.isInDelayedStart()));
            this.packageHandler.sendFirstPackage();
        }
    }

    /* access modifiers changed from: private */
    public void trackThirdPartySharingI(AdjustThirdPartySharing adjustThirdPartySharing) {
        if (!checkActivityStateI(this.activityState)) {
            this.adjustConfig.preLaunchActions.preLaunchAdjustThirdPartySharingArray.add(adjustThirdPartySharing);
        } else if (!isEnabledI() || this.activityState.isGdprForgotten) {
        } else {
            if (this.adjustConfig.coppaCompliantEnabled) {
                this.logger.warn("Calling third party sharing API not allowed when COPPA enabled", new Object[0]);
                return;
            }
            PackageBuilder packageBuilder = new PackageBuilder(this.adjustConfig, this.deviceInfo, this.activityState, this.sessionParameters, System.currentTimeMillis());
            packageBuilder.internalState = this.internalState;
            ActivityPackage buildThirdPartySharingPackage = packageBuilder.buildThirdPartySharingPackage(adjustThirdPartySharing);
            this.packageHandler.addPackage(buildThirdPartySharingPackage);
            if (this.adjustConfig.eventBufferingEnabled) {
                this.logger.info("Buffered event %s", buildThirdPartySharingPackage.getSuffix());
            } else {
                this.packageHandler.sendFirstPackage();
            }
        }
    }

    private void transferSessionPackageI(long j2) {
        PackageBuilder packageBuilder = new PackageBuilder(this.adjustConfig, this.deviceInfo, this.activityState, this.sessionParameters, j2);
        InternalState internalState2 = this.internalState;
        packageBuilder.internalState = internalState2;
        this.packageHandler.addPackage(packageBuilder.buildSessionPackage(internalState2.isInDelayedStart()));
        this.packageHandler.sendFirstPackage();
    }

    private boolean updateActivityStateI(long j2) {
        if (!checkActivityStateI(this.activityState)) {
            return false;
        }
        ActivityState activityState2 = this.activityState;
        long j3 = j2 - activityState2.lastActivity;
        if (j3 > SESSION_INTERVAL) {
            return false;
        }
        activityState2.lastActivity = j2;
        if (j3 < 0) {
            this.logger.error(TIME_TRAVEL, new Object[0]);
            return true;
        }
        activityState2.sessionLength += j3;
        activityState2.timeSpent += j3;
        return true;
    }

    private void updateAdidI(String str) {
        if (str != null && !str.equals(this.activityState.adid)) {
            this.activityState.adid = str;
            writeActivityStateI();
        }
    }

    private void updateHandlersStatusAndSendI() {
        if (!toSendI()) {
            pauseSendingI();
            return;
        }
        resumeSendingI();
        if (!this.adjustConfig.eventBufferingEnabled || (this.internalState.isFirstLaunch() && this.internalState.hasSessionResponseNotBeenProcessed())) {
            this.packageHandler.sendFirstPackage();
        }
    }

    private void updatePackagesI() {
        this.packageHandler.updatePackages(this.sessionParameters);
        this.internalState.updatePackages = false;
        ActivityState activityState2 = this.activityState;
        if (activityState2 != null) {
            activityState2.updatePackages = false;
            writeActivityStateI();
        }
    }

    private void updateStatusI(boolean z2, String str, String str2, String str3) {
        if (z2) {
            this.logger.info(str, new Object[0]);
        } else if (!pausedI(false)) {
            this.logger.info(str3, new Object[0]);
        } else if (pausedI(true)) {
            this.logger.info(str2, new Object[0]);
        } else {
            this.logger.info(android.support.v4.media.session.a.m(str2, ", except the Sdk Click Handler"), new Object[0]);
        }
        updateHandlersStatusAndSendI();
    }

    /* access modifiers changed from: private */
    public void verifyPurchaseI(AdjustPurchase adjustPurchase, OnPurchaseVerificationFinishedListener onPurchaseVerificationFinishedListener) {
        if (onPurchaseVerificationFinishedListener == null) {
            this.logger.warn("Purchase verification aborted because verification callback is null", new Object[0]);
            return;
        }
        String str = this.adjustConfig.urlStrategy;
        if (str != null && (str.equals(AdjustConfig.DATA_RESIDENCY_EU) || this.adjustConfig.urlStrategy.equals(AdjustConfig.DATA_RESIDENCY_US) || this.adjustConfig.urlStrategy.equals(AdjustConfig.DATA_RESIDENCY_TR))) {
            this.logger.warn("Purchase verification not available for data residency users right now", new Object[0]);
        } else if (!checkActivityStateI(this.activityState)) {
            onPurchaseVerificationFinishedListener.onVerificationFinished(new AdjustPurchaseVerificationResult("not_verified", 102, "Purchase verification aborted because SDK is still not initialized"));
            this.logger.warn("Purchase verification aborted because SDK is still not initialized", new Object[0]);
        } else if (!isEnabledI()) {
            onPurchaseVerificationFinishedListener.onVerificationFinished(new AdjustPurchaseVerificationResult("not_verified", 103, "Purchase verification aborted because SDK is disabled"));
            this.logger.warn("Purchase verification aborted because SDK is disabled", new Object[0]);
        } else if (this.activityState.isGdprForgotten) {
            onPurchaseVerificationFinishedListener.onVerificationFinished(new AdjustPurchaseVerificationResult("not_verified", 104, "Purchase verification aborted because user is GDPR forgotten"));
            this.logger.warn("Purchase verification aborted because user is GDPR forgotten", new Object[0]);
        } else if (adjustPurchase == null) {
            this.logger.warn("Purchase verification aborted because purchase instance is null", new Object[0]);
            onPurchaseVerificationFinishedListener.onVerificationFinished(new AdjustPurchaseVerificationResult("not_verified", 105, "Purchase verification aborted because purchase instance is null"));
        } else {
            PackageBuilder packageBuilder = new PackageBuilder(this.adjustConfig, this.deviceInfo, this.activityState, this.sessionParameters, System.currentTimeMillis());
            packageBuilder.internalState = this.internalState;
            ActivityPackage buildVerificationPackage = packageBuilder.buildVerificationPackage(adjustPurchase, onPurchaseVerificationFinishedListener);
            if (buildVerificationPackage == null) {
                this.logger.warn("Purchase verification aborted because verification package is null", new Object[0]);
                onPurchaseVerificationFinishedListener.onVerificationFinished(new AdjustPurchaseVerificationResult("not_verified", 106, "Purchase verification aborted because verification package is null"));
                return;
            }
            this.purchaseVerificationHandler.sendPurchaseVerificationPackage(buildVerificationPackage);
        }
    }

    private void writeActivityStateI() {
        synchronized (ActivityState.class) {
            try {
                ActivityState activityState2 = this.activityState;
                if (activityState2 != null) {
                    Util.writeObject(activityState2, this.adjustConfig.context, Constants.ACTIVITY_STATE_FILENAME, ACTIVITY_STATE_NAME);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void writeAttributionI() {
        synchronized (AdjustAttribution.class) {
            try {
                AdjustAttribution adjustAttribution = this.attribution;
                if (adjustAttribution != null) {
                    Util.writeObject(adjustAttribution, this.adjustConfig.context, Constants.ATTRIBUTION_FILENAME, ATTRIBUTION_NAME);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void writeSessionCallbackParametersI() {
        synchronized (SessionParameters.class) {
            try {
                SessionParameters sessionParameters2 = this.sessionParameters;
                if (sessionParameters2 != null) {
                    Util.writeObject(sessionParameters2.callbackParameters, this.adjustConfig.context, Constants.SESSION_CALLBACK_PARAMETERS_FILENAME, SESSION_CALLBACK_PARAMETERS_NAME);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void writeSessionPartnerParametersI() {
        synchronized (SessionParameters.class) {
            try {
                SessionParameters sessionParameters2 = this.sessionParameters;
                if (sessionParameters2 != null) {
                    Util.writeObject(sessionParameters2.partnerParameters, this.adjustConfig.context, Constants.SESSION_PARTNER_PARAMETERS_FILENAME, SESSION_PARTNER_PARAMETERS_NAME);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void addSessionCallbackParameter(String str, String str2) {
        this.executor.submit(new j(str, str2));
    }

    public void addSessionCallbackParameterI(String str, String str2) {
        if (Util.isValidParameter(str, JwtUtilsKt.DID_METHOD_KEY, "Session Callback") && Util.isValidParameter(str2, "value", "Session Callback")) {
            SessionParameters sessionParameters2 = this.sessionParameters;
            if (sessionParameters2.callbackParameters == null) {
                sessionParameters2.callbackParameters = new LinkedHashMap();
            }
            String str3 = this.sessionParameters.callbackParameters.get(str);
            if (str2.equals(str3)) {
                this.logger.verbose("Key %s already present with the same value", str);
                return;
            }
            if (str3 != null) {
                this.logger.warn("Key %s will be overwritten", str);
            }
            this.sessionParameters.callbackParameters.put(str, str2);
            writeSessionCallbackParametersI();
        }
    }

    public void addSessionPartnerParameter(String str, String str2) {
        this.executor.submit(new l(str, str2));
    }

    public void addSessionPartnerParameterI(String str, String str2) {
        if (Util.isValidParameter(str, JwtUtilsKt.DID_METHOD_KEY, "Session Partner") && Util.isValidParameter(str2, "value", "Session Partner")) {
            SessionParameters sessionParameters2 = this.sessionParameters;
            if (sessionParameters2.partnerParameters == null) {
                sessionParameters2.partnerParameters = new LinkedHashMap();
            }
            String str3 = this.sessionParameters.partnerParameters.get(str);
            if (str2.equals(str3)) {
                this.logger.verbose("Key %s already present with the same value", str);
                return;
            }
            if (str3 != null) {
                this.logger.warn("Key %s will be overwritten", str);
            }
            this.sessionParameters.partnerParameters.put(str, str2);
            writeSessionPartnerParametersI();
        }
    }

    public void backgroundTimerFired() {
        this.executor.submit(new b0());
    }

    public void disableThirdPartySharing() {
        this.executor.submit(new s());
    }

    public void finishedTrackingActivity(ResponseData responseData) {
        if (responseData instanceof SessionResponseData) {
            this.logger.debug("Finished tracking session", new Object[0]);
            this.attributionHandler.checkSessionResponse((SessionResponseData) responseData);
        } else if (responseData instanceof SdkClickResponseData) {
            SdkClickResponseData sdkClickResponseData = (SdkClickResponseData) responseData;
            checkForInstallReferrerInfo(sdkClickResponseData);
            this.attributionHandler.checkSdkClickResponse(sdkClickResponseData);
        } else if (responseData instanceof EventResponseData) {
            launchEventResponseTasks((EventResponseData) responseData);
        } else if (responseData instanceof PurchaseVerificationResponseData) {
            launchPurchaseVerificationResponseTasks((PurchaseVerificationResponseData) responseData);
        }
    }

    public void foregroundTimerFired() {
        this.executor.submit(new a0());
    }

    public void gdprForgetMe() {
        this.executor.submit(new r());
    }

    public ActivityState getActivityState() {
        return this.activityState;
    }

    public String getAdid() {
        ActivityState activityState2 = this.activityState;
        if (activityState2 == null) {
            return null;
        }
        return activityState2.adid;
    }

    public AdjustConfig getAdjustConfig() {
        return this.adjustConfig;
    }

    public AdjustAttribution getAttribution() {
        return this.attribution;
    }

    public Context getContext() {
        return this.adjustConfig.context;
    }

    public DeviceInfo getDeviceInfo() {
        return this.deviceInfo;
    }

    public InternalState getInternalState() {
        return this.internalState;
    }

    public SessionParameters getSessionParameters() {
        return this.sessionParameters;
    }

    public void gotOptOutResponse() {
        this.executor.submit(new z());
    }

    public void init(AdjustConfig adjustConfig2) {
        this.adjustConfig = adjustConfig2;
    }

    public boolean isEnabled() {
        return isEnabledI();
    }

    public void launchAttributionResponseTasks(AttributionResponseData attributionResponseData) {
        this.executor.submit(new g(attributionResponseData));
    }

    public void launchEventResponseTasks(EventResponseData eventResponseData) {
        this.executor.submit(new d(eventResponseData));
    }

    public void launchPurchaseVerificationResponseTasks(PurchaseVerificationResponseData purchaseVerificationResponseData) {
        this.executor.submit(new h(purchaseVerificationResponseData));
    }

    public void launchSdkClickResponseTasks(SdkClickResponseData sdkClickResponseData) {
        this.executor.submit(new e(sdkClickResponseData));
    }

    public void launchSessionResponseTasks(SessionResponseData sessionResponseData) {
        this.executor.submit(new f(sessionResponseData));
    }

    public void onPause() {
        this.internalState.background = true;
        this.executor.submit(new g0());
    }

    public void onResume() {
        this.internalState.background = false;
        this.executor.submit(new v());
    }

    public void readOpenUrl(Uri uri, long j2) {
        this.executor.submit(new z0(uri, j2));
    }

    public void removeSessionCallbackParameter(String str) {
        this.executor.submit(new m(str));
    }

    public void removeSessionCallbackParameterI(String str) {
        if (Util.isValidParameter(str, JwtUtilsKt.DID_METHOD_KEY, "Session Callback")) {
            Map<String, String> map = this.sessionParameters.callbackParameters;
            if (map == null) {
                this.logger.warn("Session Callback parameters are not set", new Object[0]);
            } else if (map.remove(str) == null) {
                this.logger.warn("Key %s does not exist", str);
            } else {
                this.logger.debug("Key %s will be removed", str);
                writeSessionCallbackParametersI();
            }
        }
    }

    public void removeSessionPartnerParameter(String str) {
        this.executor.submit(new n(str));
    }

    public void removeSessionPartnerParameterI(String str) {
        if (Util.isValidParameter(str, JwtUtilsKt.DID_METHOD_KEY, "Session Partner")) {
            Map<String, String> map = this.sessionParameters.partnerParameters;
            if (map == null) {
                this.logger.warn("Session Partner parameters are not set", new Object[0]);
            } else if (map.remove(str) == null) {
                this.logger.warn("Key %s does not exist", str);
            } else {
                this.logger.debug("Key %s will be removed", str);
                writeSessionPartnerParametersI();
            }
        }
    }

    public void resetSessionCallbackParameters() {
        this.executor.submit(new o());
    }

    public void resetSessionCallbackParametersI() {
        if (this.sessionParameters.callbackParameters == null) {
            this.logger.warn("Session Callback parameters are not set", new Object[0]);
        }
        this.sessionParameters.callbackParameters = null;
        writeSessionCallbackParametersI();
    }

    public void resetSessionPartnerParameters() {
        this.executor.submit(new p());
    }

    public void resetSessionPartnerParametersI() {
        if (this.sessionParameters.partnerParameters == null) {
            this.logger.warn("Session Partner parameters are not set", new Object[0]);
        }
        this.sessionParameters.partnerParameters = null;
        writeSessionPartnerParametersI();
    }

    public void sendFirstPackages() {
        this.executor.submit(new i());
    }

    public void sendInstallReferrer(ReferrerDetails referrerDetails, String str) {
        this.executor.submit(new c(referrerDetails, str));
    }

    public void sendPreinstallReferrer() {
        this.executor.submit(new b());
    }

    public void sendReftagReferrer() {
        this.executor.submit(new a());
    }

    public void setAskingAttribution(boolean z2) {
        this.executor.submit(new b1(z2));
    }

    public void setEnabled(boolean z2) {
        this.executor.submit(new x0(z2));
    }

    public void setOfflineMode(boolean z2) {
        this.executor.submit(new y0(z2));
    }

    public void setPushToken(String str, boolean z2) {
        this.executor.submit(new q(z2, str));
    }

    public void teardown() {
        TimerOnce timerOnce = this.backgroundTimer;
        if (timerOnce != null) {
            timerOnce.teardown();
        }
        TimerCycle timerCycle = this.foregroundTimer;
        if (timerCycle != null) {
            timerCycle.teardown();
        }
        TimerOnce timerOnce2 = this.delayStartTimer;
        if (timerOnce2 != null) {
            timerOnce2.teardown();
        }
        ThreadExecutor threadExecutor = this.executor;
        if (threadExecutor != null) {
            threadExecutor.teardown();
        }
        IPackageHandler iPackageHandler = this.packageHandler;
        if (iPackageHandler != null) {
            iPackageHandler.teardown();
        }
        IAttributionHandler iAttributionHandler = this.attributionHandler;
        if (iAttributionHandler != null) {
            iAttributionHandler.teardown();
        }
        ISdkClickHandler iSdkClickHandler = this.sdkClickHandler;
        if (iSdkClickHandler != null) {
            iSdkClickHandler.teardown();
        }
        IPurchaseVerificationHandler iPurchaseVerificationHandler = this.purchaseVerificationHandler;
        if (iPurchaseVerificationHandler != null) {
            iPurchaseVerificationHandler.teardown();
        }
        SessionParameters sessionParameters2 = this.sessionParameters;
        if (sessionParameters2 != null) {
            Map<String, String> map = sessionParameters2.callbackParameters;
            if (map != null) {
                map.clear();
            }
            Map<String, String> map2 = this.sessionParameters.partnerParameters;
            if (map2 != null) {
                map2.clear();
            }
        }
        teardownActivityStateS();
        teardownAttributionS();
        teardownAllSessionParametersS();
        this.packageHandler = null;
        this.logger = null;
        this.foregroundTimer = null;
        this.executor = null;
        this.backgroundTimer = null;
        this.delayStartTimer = null;
        this.internalState = null;
        this.deviceInfo = null;
        this.adjustConfig = null;
        this.attributionHandler = null;
        this.sdkClickHandler = null;
        this.purchaseVerificationHandler = null;
        this.sessionParameters = null;
    }

    public void trackAdRevenue(AdjustAdRevenue adjustAdRevenue) {
        this.executor.submit(new x(adjustAdRevenue));
    }

    public void trackEvent(AdjustEvent adjustEvent) {
        this.executor.submit(new r0(adjustEvent));
    }

    public void trackMeasurementConsent(boolean z2) {
        this.executor.submit(new u(z2));
    }

    public void trackPlayStoreSubscription(AdjustPlayStoreSubscription adjustPlayStoreSubscription) {
        this.executor.submit(new y(adjustPlayStoreSubscription));
    }

    public void trackThirdPartySharing(AdjustThirdPartySharing adjustThirdPartySharing) {
        this.executor.submit(new t(adjustThirdPartySharing));
    }

    public boolean updateAttributionI(AdjustAttribution adjustAttribution) {
        if (adjustAttribution == null) {
            return false;
        }
        if (this.adjustConfig.finalAttributionEnabled && this.activityState.askingAttribution) {
            this.logger.info("Not updating intermediate attribution", new Object[0]);
            return false;
        } else if (adjustAttribution.equals(this.attribution)) {
            return false;
        } else {
            this.attribution = adjustAttribution;
            writeAttributionI();
            return true;
        }
    }

    public void verifyPurchase(AdjustPurchase adjustPurchase, OnPurchaseVerificationFinishedListener onPurchaseVerificationFinishedListener) {
        this.executor.submit(new c0(adjustPurchase, onPurchaseVerificationFinishedListener));
    }

    private void checkAfterNewStartI(SharedPreferencesManager sharedPreferencesManager) {
        String pushToken = sharedPreferencesManager.getPushToken();
        if (pushToken != null && !pushToken.equals(this.activityState.pushToken)) {
            setPushToken(pushToken, true);
        }
        if (sharedPreferencesManager.getRawReferrerArray() != null) {
            sendReftagReferrer();
        }
        checkForPreinstallI();
        this.installReferrer.startConnection();
        this.installReferrerHuawei.readReferrer();
        this.installReferrerMeta.readReferrer();
        readInstallReferrerSamsung();
        readInstallReferrerXiaomi();
        readInstallReferrerVivo();
    }

    private boolean pausedI(boolean z2) {
        return z2 ? this.internalState.isOffline() || !isEnabledI() : this.internalState.isOffline() || !isEnabledI() || this.internalState.isInDelayedStart();
    }

    private boolean toSendI(boolean z2) {
        if (pausedI(z2)) {
            return false;
        }
        if (this.adjustConfig.sendInBackground) {
            return true;
        }
        return this.internalState.isInForeground();
    }

    /* access modifiers changed from: private */
    public void trackAdRevenueI(String str, JSONObject jSONObject) {
        if (checkActivityStateI(this.activityState) && isEnabledI() && !this.activityState.isGdprForgotten) {
            PackageBuilder packageBuilder = new PackageBuilder(this.adjustConfig, this.deviceInfo, this.activityState, this.sessionParameters, System.currentTimeMillis());
            packageBuilder.internalState = this.internalState;
            this.packageHandler.addPackage(packageBuilder.buildAdRevenuePackage(str, jSONObject));
            this.packageHandler.sendFirstPackage();
        }
    }

    public void readOpenUrl(Uri uri, long j2, OnDeeplinkResolvedListener onDeeplinkResolvedListener) {
        this.cachedDeeplinkResolutionCallback = onDeeplinkResolvedListener;
        this.executor.submit(new a1(uri, j2));
    }

    public void trackAdRevenue(String str, JSONObject jSONObject) {
        this.executor.submit(new w(str, jSONObject));
    }
}
