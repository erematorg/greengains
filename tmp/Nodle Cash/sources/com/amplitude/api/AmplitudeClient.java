package com.amplitude.api;

import A.a;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.util.Pair;
import androidx.camera.camera2.internal.C0118y;
import androidx.core.os.EnvironmentCompat;
import com.google.common.base.Ascii;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.OkHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AmplitudeClient {
    public static final String DEVICE_ID_KEY = "device_id";
    public static final String END_SESSION_EVENT = "session_end";
    public static final String LAST_EVENT_ID_KEY = "last_event_id";
    public static final String LAST_EVENT_TIME_KEY = "last_event_time";
    public static final String LAST_IDENTIFY_ID_KEY = "last_identify_id";
    public static final String OPT_OUT_KEY = "opt_out";
    public static final String PREVIOUS_SESSION_ID_KEY = "previous_session_id";
    public static final String SEQUENCE_NUMBER_KEY = "sequence_number";
    public static final String START_SESSION_EVENT = "session_start";
    public static final String TAG = "com.amplitude.api.AmplitudeClient";
    public static final String USER_ID_KEY = "user_id";
    /* access modifiers changed from: private */
    public static final AmplitudeLog logger = AmplitudeLog.getLogger();
    protected String apiKey;
    JSONObject apiPropertiesTrackingOptions;
    /* access modifiers changed from: private */
    public boolean backoffUpload;
    /* access modifiers changed from: private */
    public int backoffUploadBatchSize;
    protected Context context;
    protected DatabaseHelper dbHelper;
    protected String deviceId;
    /* access modifiers changed from: private */
    public DeviceInfo deviceInfo;
    private int eventMaxCount;
    /* access modifiers changed from: private */
    public int eventUploadMaxBatchSize;
    private long eventUploadPeriodMillis;
    /* access modifiers changed from: private */
    public int eventUploadThreshold;
    /* access modifiers changed from: private */
    public boolean flushEventsOnClose;
    protected OkHttpClient httpClient;
    WorkerThread httpThread;
    /* access modifiers changed from: private */
    public boolean inForeground;
    protected boolean initialized;
    protected String instanceName;
    Throwable lastError;
    long lastEventId;
    long lastEventTime;
    long lastIdentifyId;
    WorkerThread logThread;
    private long minTimeBetweenSessionsMillis;
    private boolean newDeviceIdPerInstall;
    private boolean offline;
    /* access modifiers changed from: private */
    public boolean optOut;
    protected String platform;
    long previousSessionId;
    long sequenceNumber;
    long sessionId;
    private long sessionTimeoutMillis;
    TrackingOptions trackingOptions;
    /* access modifiers changed from: private */
    public boolean trackingSessionEvents;
    /* access modifiers changed from: private */
    public AtomicBoolean updateScheduled;
    AtomicBoolean uploadingCurrently;
    String url;
    private boolean useAdvertisingIdForDeviceId;
    protected String userId;
    private boolean usingForegroundTracking;

    public AmplitudeClient() {
        this((String) null);
    }

    private Set<String> getInvalidDeviceIds() {
        HashSet hashSet = new HashSet();
        hashSet.add("");
        hashSet.add("9774d56d682e549c");
        hashSet.add(EnvironmentCompat.MEDIA_UNKNOWN);
        hashSet.add("000000000000000");
        hashSet.add(Constants.PLATFORM);
        hashSet.add("DEFACE");
        hashSet.add("00000000-0000-0000-0000-000000000000");
        return hashSet;
    }

    /* access modifiers changed from: private */
    public long getLongvalue(String str, long j2) {
        Long longValue = this.dbHelper.getLongValue(str);
        return longValue == null ? j2 : longValue.longValue();
    }

    private boolean inSession() {
        return this.sessionId >= 0;
    }

    /* access modifiers changed from: private */
    public String initializeDeviceId() {
        Set<String> invalidDeviceIds = getInvalidDeviceIds();
        String value = this.dbHelper.getValue(DEVICE_ID_KEY);
        String stringFromSharedPreferences = Utils.getStringFromSharedPreferences(this.context, this.instanceName, DEVICE_ID_KEY);
        if (!Utils.isEmptyString(value) && !invalidDeviceIds.contains(value)) {
            if (!value.equals(stringFromSharedPreferences)) {
                saveDeviceId(value);
            }
            return value;
        } else if (Utils.isEmptyString(stringFromSharedPreferences) || invalidDeviceIds.contains(stringFromSharedPreferences)) {
            if (!this.newDeviceIdPerInstall && this.useAdvertisingIdForDeviceId && !this.deviceInfo.isLimitAdTrackingEnabled()) {
                String advertisingId = this.deviceInfo.getAdvertisingId();
                if (!Utils.isEmptyString(advertisingId) && !invalidDeviceIds.contains(advertisingId)) {
                    saveDeviceId(advertisingId);
                    return advertisingId;
                }
            }
            String str = DeviceInfo.generateUUID() + "R";
            saveDeviceId(str);
            return str;
        } else {
            saveDeviceId(stringFromSharedPreferences);
            return stringFromSharedPreferences;
        }
    }

    private boolean isWithinMinTimeBetweenSessions(long j2) {
        return j2 - this.lastEventTime < (this.usingForegroundTracking ? this.minTimeBetweenSessionsMillis : this.sessionTimeoutMillis);
    }

    private static void migrateBooleanValue(SharedPreferences sharedPreferences, String str, boolean z2, DatabaseHelper databaseHelper, String str2) {
        if (databaseHelper.getLongValue(str2) == null) {
            databaseHelper.insertOrReplaceKeyLongValue(str2, Long.valueOf(sharedPreferences.getBoolean(str, z2) ? 1 : 0));
            sharedPreferences.edit().remove(str).apply();
        }
    }

    private static void migrateLongValue(SharedPreferences sharedPreferences, String str, long j2, DatabaseHelper databaseHelper, String str2) {
        if (databaseHelper.getLongValue(str2) == null) {
            databaseHelper.insertOrReplaceKeyLongValue(str2, Long.valueOf(sharedPreferences.getLong(str, j2)));
            sharedPreferences.edit().remove(str).apply();
        }
    }

    private static void migrateStringValue(SharedPreferences sharedPreferences, String str, String str2, DatabaseHelper databaseHelper, String str3) {
        if (Utils.isEmptyString(databaseHelper.getValue(str3))) {
            String string = sharedPreferences.getString(str, str2);
            if (!Utils.isEmptyString(string)) {
                databaseHelper.insertOrReplaceKeyValue(str3, string);
                sharedPreferences.edit().remove(str).apply();
            }
        }
    }

    /* access modifiers changed from: private */
    public void saveDeviceId(String str) {
        this.dbHelper.insertOrReplaceKeyValue(DEVICE_ID_KEY, str);
        Utils.writeStringToSharedPreferences(this.context, this.instanceName, DEVICE_ID_KEY, str);
    }

    /* access modifiers changed from: private */
    public void sendSessionEvent(String str) {
        if (contextAndApiKeySet("sendSessionEvent('" + str + "')") && inSession()) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("special", str);
                logEvent(str, (JSONObject) null, jSONObject, (JSONObject) null, (JSONObject) null, (JSONObject) null, this.lastEventTime, false);
            } catch (JSONException e3) {
                Diagnostics logger2 = Diagnostics.getLogger();
                logger2.logError("Failed to generate API Properties JSON for session event " + str, e3);
            }
        }
    }

    /* access modifiers changed from: private */
    public void setSessionId(long j2) {
        this.sessionId = j2;
        setPreviousSessionId(j2);
    }

    private void startNewSession(long j2) {
        if (this.trackingSessionEvents) {
            sendSessionEvent(END_SESSION_EVENT);
        }
        setSessionId(j2);
        refreshSessionTime(j2);
        if (this.trackingSessionEvents) {
            sendSessionEvent(START_SESSION_EVENT);
        }
    }

    private void updateServerLater(long j2) {
        if (!this.updateScheduled.getAndSet(true)) {
            this.logThread.postDelayed(new Runnable() {
                public void run() {
                    AmplitudeClient.this.updateScheduled.set(false);
                    AmplitudeClient.this.updateServer();
                }
            }, j2);
        }
    }

    public static boolean upgradePrefs(Context context2) {
        return upgradePrefs(context2, (String) null, (String) null);
    }

    public static boolean upgradeSharedPrefsToDB(Context context2) {
        return upgradeSharedPrefsToDB(context2, (String) null);
    }

    public String bytesToHexString(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        char[] cArr2 = new char[(bArr.length * 2)];
        for (int i3 = 0; i3 < bArr.length; i3++) {
            byte b3 = bArr[i3];
            int i4 = i3 * 2;
            cArr2[i4] = cArr[(b3 & 255) >>> 4];
            cArr2[i4 + 1] = cArr[b3 & Ascii.SI];
        }
        return new String(cArr2);
    }

    public void clearUserProperties() {
        identify(new Identify().clearAll());
    }

    public synchronized boolean contextAndApiKeySet(String str) {
        if (this.context == null) {
            AmplitudeLog amplitudeLog = logger;
            amplitudeLog.e(TAG, "context cannot be null, set context with initialize() before calling " + str);
            return false;
        } else if (!Utils.isEmptyString(this.apiKey)) {
            return true;
        } else {
            AmplitudeLog amplitudeLog2 = logger;
            amplitudeLog2.e(TAG, "apiKey cannot be null or empty, set apiKey with initialize() before calling " + str);
            return false;
        }
    }

    public AmplitudeClient disableDiagnosticLogging() {
        Diagnostics.getLogger().disableLogging();
        return this;
    }

    public AmplitudeClient disableLocationListening() {
        runOnLogThread(new Runnable() {
            public void run() {
                if (AmplitudeClient.this.deviceInfo != null) {
                    AmplitudeClient.this.deviceInfo.setLocationListening(false);
                    return;
                }
                throw new IllegalStateException("Must initialize before acting on location listening.");
            }
        });
        return this;
    }

    public AmplitudeClient enableDiagnosticLogging() {
        if (!contextAndApiKeySet("enableDiagnosticLogging")) {
            return this;
        }
        Diagnostics.getLogger().enableLogging(this.httpClient, this.apiKey, this.deviceId);
        return this;
    }

    public AmplitudeClient enableForegroundTracking(Application application) {
        if (!this.usingForegroundTracking && contextAndApiKeySet("enableForegroundTracking()")) {
            application.registerActivityLifecycleCallbacks(new AmplitudeCallbacks(this));
        }
        return this;
    }

    public AmplitudeClient enableLocationListening() {
        runOnLogThread(new Runnable() {
            public void run() {
                if (AmplitudeClient.this.deviceInfo != null) {
                    AmplitudeClient.this.deviceInfo.setLocationListening(true);
                    return;
                }
                throw new IllegalStateException("Must initialize before acting on location listening.");
            }
        });
        return this;
    }

    public AmplitudeClient enableLogging(boolean z2) {
        logger.setEnableLogging(z2);
        return this;
    }

    public AmplitudeClient enableNewDeviceIdPerInstall(boolean z2) {
        this.newDeviceIdPerInstall = z2;
        return this;
    }

    public long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public long getNextSequenceNumber() {
        long j2 = this.sequenceNumber + 1;
        this.sequenceNumber = j2;
        this.dbHelper.insertOrReplaceKeyLongValue(SEQUENCE_NUMBER_KEY, Long.valueOf(j2));
        return this.sequenceNumber;
    }

    public long getSessionId() {
        return this.sessionId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void groupIdentify(String str, Object obj, Identify identify) {
        groupIdentify(str, obj, identify, false);
    }

    public void identify(Identify identify) {
        identify(identify, false);
    }

    public AmplitudeClient initialize(Context context2, String str) {
        return initialize(context2, str, (String) null);
    }

    public boolean isInForeground() {
        return this.inForeground;
    }

    public boolean isOptedOut() {
        return this.optOut;
    }

    public boolean isUsingForegroundTracking() {
        return this.usingForegroundTracking;
    }

    public void logEvent(String str) {
        logEvent(str, (JSONObject) null);
    }

    public void logEventAsync(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, JSONObject jSONObject4, JSONObject jSONObject5, long j2, boolean z2) {
        final JSONObject cloneJSONObject = jSONObject != null ? Utils.cloneJSONObject(jSONObject) : jSONObject;
        final JSONObject cloneJSONObject2 = jSONObject2 != null ? Utils.cloneJSONObject(jSONObject2) : jSONObject2;
        final JSONObject cloneJSONObject3 = jSONObject3 != null ? Utils.cloneJSONObject(jSONObject3) : jSONObject3;
        final JSONObject cloneJSONObject4 = jSONObject4 != null ? Utils.cloneJSONObject(jSONObject4) : jSONObject4;
        final JSONObject cloneJSONObject5 = jSONObject5 != null ? Utils.cloneJSONObject(jSONObject5) : jSONObject5;
        final String str2 = str;
        final long j3 = j2;
        final boolean z3 = z2;
        runOnLogThread(new Runnable() {
            public void run() {
                if (!Utils.isEmptyString(AmplitudeClient.this.apiKey)) {
                    AmplitudeClient.this.logEvent(str2, cloneJSONObject, cloneJSONObject2, cloneJSONObject3, cloneJSONObject4, cloneJSONObject5, j3, z3);
                }
            }
        });
    }

    public void logEventSync(String str) {
        logEventSync(str, (JSONObject) null);
    }

    public void logRevenue(double d2) {
        logRevenue((String) null, 1, d2);
    }

    public void logRevenueV2(Revenue revenue) {
        if (contextAndApiKeySet("logRevenueV2()") && revenue != null && revenue.isValidRevenue()) {
            logEvent(Constants.AMP_REVENUE_EVENT, revenue.toJSONObject());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:62:0x01d0  */
    /* JADX WARNING: Removed duplicated region for block: B:67:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void makeEventUploadPostRequest(okhttp3.OkHttpClient r17, java.lang.String r18, long r19, long r21) {
        /*
            r16 = this;
            r7 = r16
            r1 = r18
            r3 = r19
            r5 = r21
            java.lang.String r8 = "Exception:"
            java.lang.String r9 = "Failed to post upload request"
            java.lang.String r10 = "com.amplitude.api.AmplitudeClient"
            java.lang.String r2 = "Upload failed, "
            java.lang.String r11 = "2"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r12 = ""
            r0.<init>(r12)
            long r13 = r16.getCurrentTimeMillis()
            r0.append(r13)
            java.lang.String r13 = r0.toString()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ UnsupportedEncodingException -> 0x004c }
            r0.<init>(r11)     // Catch:{ UnsupportedEncodingException -> 0x004c }
            java.lang.String r14 = r7.apiKey     // Catch:{ UnsupportedEncodingException -> 0x004c }
            r0.append(r14)     // Catch:{ UnsupportedEncodingException -> 0x004c }
            r0.append(r1)     // Catch:{ UnsupportedEncodingException -> 0x004c }
            r0.append(r13)     // Catch:{ UnsupportedEncodingException -> 0x004c }
            java.lang.String r0 = r0.toString()     // Catch:{ UnsupportedEncodingException -> 0x004c }
            com.amplitude.security.MD5 r14 = new com.amplitude.security.MD5     // Catch:{ UnsupportedEncodingException -> 0x004c }
            r14.<init>()     // Catch:{ UnsupportedEncodingException -> 0x004c }
            java.lang.String r15 = "UTF-8"
            byte[] r0 = r0.getBytes(r15)     // Catch:{ UnsupportedEncodingException -> 0x004c }
            byte[] r0 = r14.digest(r0)     // Catch:{ UnsupportedEncodingException -> 0x004c }
            java.lang.String r12 = r7.bytesToHexString(r0)     // Catch:{ UnsupportedEncodingException -> 0x004c }
            goto L_0x005f
        L_0x004c:
            r0 = move-exception
            com.amplitude.api.AmplitudeLog r14 = logger
            java.lang.String r15 = r0.toString()
            r14.e(r10, r15)
            com.amplitude.api.Diagnostics r14 = com.amplitude.api.Diagnostics.getLogger()
            java.lang.String r15 = "Failed to compute checksum for upload request"
            r14.logError(r15, r0)
        L_0x005f:
            okhttp3.FormBody$Builder r0 = new okhttp3.FormBody$Builder
            r0.<init>()
            java.lang.String r14 = "v"
            okhttp3.FormBody$Builder r0 = r0.add(r14, r11)
            java.lang.String r11 = "client"
            java.lang.String r14 = r7.apiKey
            okhttp3.FormBody$Builder r0 = r0.add(r11, r14)
            java.lang.String r11 = "e"
            okhttp3.FormBody$Builder r0 = r0.add(r11, r1)
            java.lang.String r1 = "upload_time"
            okhttp3.FormBody$Builder r0 = r0.add(r1, r13)
            java.lang.String r1 = "checksum"
            okhttp3.FormBody$Builder r0 = r0.add(r1, r12)
            okhttp3.FormBody r0 = r0.build()
            r11 = 0
            okhttp3.Request$Builder r1 = new okhttp3.Request$Builder     // Catch:{ IllegalArgumentException -> 0x01d6 }
            r1.<init>()     // Catch:{ IllegalArgumentException -> 0x01d6 }
            java.lang.String r12 = r7.url     // Catch:{ IllegalArgumentException -> 0x01d6 }
            okhttp3.Request$Builder r1 = r1.url((java.lang.String) r12)     // Catch:{ IllegalArgumentException -> 0x01d6 }
            okhttp3.Request$Builder r0 = r1.post(r0)     // Catch:{ IllegalArgumentException -> 0x01d6 }
            okhttp3.Request r0 = r0.build()     // Catch:{ IllegalArgumentException -> 0x01d6 }
            r1 = r17
            okhttp3.Call r0 = r1.newCall(r0)     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
            okhttp3.Response r0 = r0.execute()     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
            okhttp3.ResponseBody r1 = r0.body()     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
            java.lang.String r1 = r1.string()     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
            java.lang.String r12 = "success"
            boolean r12 = r1.equals(r12)     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
            r13 = 1
            if (r12 == 0) goto L_0x00dc
            com.amplitude.api.WorkerThread r0 = r7.logThread     // Catch:{ ConnectException -> 0x00d9, UnknownHostException -> 0x00d6, IOException -> 0x00d3, AssertionError -> 0x00d0, Exception -> 0x00cd }
            com.amplitude.api.AmplitudeClient$14 r12 = new com.amplitude.api.AmplitudeClient$14     // Catch:{ ConnectException -> 0x00d9, UnknownHostException -> 0x00d6, IOException -> 0x00d3, AssertionError -> 0x00d0, Exception -> 0x00cd }
            r1 = r12
            r2 = r16
            r3 = r19
            r5 = r21
            r1.<init>(r3, r5)     // Catch:{ ConnectException -> 0x00d9, UnknownHostException -> 0x00d6, IOException -> 0x00d3, AssertionError -> 0x00d0, Exception -> 0x00cd }
            r0.post(r12)     // Catch:{ ConnectException -> 0x00d9, UnknownHostException -> 0x00d6, IOException -> 0x00d3, AssertionError -> 0x00d0, Exception -> 0x00cd }
            goto L_0x01ce
        L_0x00cd:
            r0 = move-exception
            goto L_0x018a
        L_0x00d0:
            r0 = move-exception
            goto L_0x0199
        L_0x00d3:
            r0 = move-exception
            goto L_0x01a8
        L_0x00d6:
            r0 = move-exception
            goto L_0x01bb
        L_0x00d9:
            r0 = move-exception
            goto L_0x01c5
        L_0x00dc:
            java.lang.String r12 = "invalid_api_key"
            boolean r12 = r1.equals(r12)     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
            if (r12 == 0) goto L_0x0101
            com.amplitude.api.AmplitudeLog r0 = logger     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
            java.lang.String r1 = "Invalid API key, make sure your API key is correct in initialize()"
            r0.e(r10, r1)     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
            goto L_0x0188
        L_0x00ed:
            r0 = move-exception
            r13 = r11
            goto L_0x018a
        L_0x00f1:
            r0 = move-exception
            r13 = r11
            goto L_0x0199
        L_0x00f5:
            r0 = move-exception
            r13 = r11
            goto L_0x01a8
        L_0x00f9:
            r0 = move-exception
            r13 = r11
            goto L_0x01bb
        L_0x00fd:
            r0 = move-exception
            r13 = r11
            goto L_0x01c5
        L_0x0101:
            java.lang.String r12 = "bad_checksum"
            boolean r12 = r1.equals(r12)     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
            if (r12 == 0) goto L_0x0112
            com.amplitude.api.AmplitudeLog r0 = logger     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
            java.lang.String r1 = "Bad checksum, post request was mangled in transit, will attempt to reupload later"
            r0.w((java.lang.String) r10, (java.lang.String) r1)     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
            goto L_0x0188
        L_0x0112:
            java.lang.String r12 = "request_db_write_failed"
            boolean r12 = r1.equals(r12)     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
            if (r12 == 0) goto L_0x0122
            com.amplitude.api.AmplitudeLog r0 = logger     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
            java.lang.String r1 = "Couldn't write to request database on server, will attempt to reupload later"
            r0.w((java.lang.String) r10, (java.lang.String) r1)     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
            goto L_0x0188
        L_0x0122:
            int r0 = r0.code()     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
            r12 = 413(0x19d, float:5.79E-43)
            if (r0 != r12) goto L_0x0172
            boolean r0 = r7.backoffUpload     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
            if (r0 == 0) goto L_0x0146
            int r0 = r7.backoffUploadBatchSize     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
            if (r0 != r13) goto L_0x0146
            r0 = 0
            int r2 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r2 < 0) goto L_0x013d
            com.amplitude.api.DatabaseHelper r2 = r7.dbHelper     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
            r2.removeEvent(r3)     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
        L_0x013d:
            int r0 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r0 < 0) goto L_0x0146
            com.amplitude.api.DatabaseHelper r0 = r7.dbHelper     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
            r0.removeIdentify(r5)     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
        L_0x0146:
            r7.backoffUpload = r13     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
            com.amplitude.api.DatabaseHelper r0 = r7.dbHelper     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
            long r0 = r0.getEventCount()     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
            int r0 = (int) r0     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
            int r1 = r7.backoffUploadBatchSize     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
            int r0 = java.lang.Math.min(r0, r1)     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
            double r0 = (double) r0     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
            r2 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r0 = r0 / r2
            double r0 = java.lang.Math.ceil(r0)     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
            int r0 = (int) r0     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
            r7.backoffUploadBatchSize = r0     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
            com.amplitude.api.AmplitudeLog r0 = logger     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
            java.lang.String r1 = "Request too large, will decrease size and attempt to reupload"
            r0.w((java.lang.String) r10, (java.lang.String) r1)     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
            com.amplitude.api.WorkerThread r0 = r7.logThread     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
            com.amplitude.api.AmplitudeClient$15 r1 = new com.amplitude.api.AmplitudeClient$15     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
            r1.<init>()     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
            r0.post(r1)     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
            goto L_0x0188
        L_0x0172:
            com.amplitude.api.AmplitudeLog r0 = logger     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
            r3.<init>(r2)     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
            r3.append(r1)     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
            java.lang.String r1 = ", will attempt to reupload later"
            r3.append(r1)     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
            java.lang.String r1 = r3.toString()     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
            r0.w((java.lang.String) r10, (java.lang.String) r1)     // Catch:{ ConnectException -> 0x00fd, UnknownHostException -> 0x00f9, IOException -> 0x00f5, AssertionError -> 0x00f1, Exception -> 0x00ed }
        L_0x0188:
            r13 = r11
            goto L_0x01ce
        L_0x018a:
            com.amplitude.api.AmplitudeLog r1 = logger
            r1.e(r10, r8, r0)
            r7.lastError = r0
            com.amplitude.api.Diagnostics r1 = com.amplitude.api.Diagnostics.getLogger()
            r1.logError(r9, r0)
            goto L_0x01ce
        L_0x0199:
            com.amplitude.api.AmplitudeLog r1 = logger
            r1.e(r10, r8, r0)
            r7.lastError = r0
            com.amplitude.api.Diagnostics r1 = com.amplitude.api.Diagnostics.getLogger()
            r1.logError(r9, r0)
            goto L_0x01ce
        L_0x01a8:
            com.amplitude.api.AmplitudeLog r1 = logger
            java.lang.String r2 = r0.toString()
            r1.e(r10, r2)
            r7.lastError = r0
            com.amplitude.api.Diagnostics r1 = com.amplitude.api.Diagnostics.getLogger()
            r1.logError(r9, r0)
            goto L_0x01ce
        L_0x01bb:
            r7.lastError = r0
            com.amplitude.api.Diagnostics r1 = com.amplitude.api.Diagnostics.getLogger()
            r1.logError(r9, r0)
            goto L_0x01ce
        L_0x01c5:
            r7.lastError = r0
            com.amplitude.api.Diagnostics r1 = com.amplitude.api.Diagnostics.getLogger()
            r1.logError(r9, r0)
        L_0x01ce:
            if (r13 != 0) goto L_0x01d5
            java.util.concurrent.atomic.AtomicBoolean r0 = r7.uploadingCurrently
            r0.set(r11)
        L_0x01d5:
            return
        L_0x01d6:
            r0 = move-exception
            com.amplitude.api.AmplitudeLog r1 = logger
            java.lang.String r2 = r0.toString()
            r1.e(r10, r2)
            java.util.concurrent.atomic.AtomicBoolean r1 = r7.uploadingCurrently
            r1.set(r11)
            com.amplitude.api.Diagnostics r1 = com.amplitude.api.Diagnostics.getLogger()
            java.lang.String r2 = "Failed to build upload request"
            r1.logError(r2, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amplitude.api.AmplitudeClient.makeEventUploadPostRequest(okhttp3.OkHttpClient, java.lang.String, long, long):void");
    }

    public Pair<Pair<Long, Long>, JSONArray> mergeEventsAndIdentifys(List<JSONObject> list, List<JSONObject> list2, long j2) throws JSONException {
        long j3;
        long j4;
        JSONArray jSONArray = new JSONArray();
        long j5 = -1;
        long j6 = -1;
        while (true) {
            if (((long) jSONArray.length()) >= j2) {
                break;
            }
            boolean isEmpty = list.isEmpty();
            boolean isEmpty2 = list2.isEmpty();
            if (isEmpty && isEmpty2) {
                logger.w(TAG, String.format("mergeEventsAndIdentifys: number of events and identifys less than expected by %d", new Object[]{Long.valueOf(j2 - ((long) jSONArray.length()))}));
                break;
            }
            if (isEmpty2) {
                JSONObject remove = list.remove(0);
                j3 = remove.getLong("event_id");
                jSONArray.put(remove);
            } else {
                if (isEmpty) {
                    JSONObject remove2 = list2.remove(0);
                    j4 = remove2.getLong("event_id");
                    jSONArray.put(remove2);
                } else if (!list.get(0).has(SEQUENCE_NUMBER_KEY) || list.get(0).getLong(SEQUENCE_NUMBER_KEY) < list2.get(0).getLong(SEQUENCE_NUMBER_KEY)) {
                    JSONObject remove3 = list.remove(0);
                    j3 = remove3.getLong("event_id");
                    jSONArray.put(remove3);
                } else {
                    JSONObject remove4 = list2.remove(0);
                    j4 = remove4.getLong("event_id");
                    jSONArray.put(remove4);
                }
                j6 = j4;
            }
            j5 = j3;
        }
        return new Pair<>(new Pair(Long.valueOf(j5), Long.valueOf(j6)), jSONArray);
    }

    public void onEnterForeground(final long j2) {
        runOnLogThread(new Runnable() {
            public void run() {
                if (!Utils.isEmptyString(AmplitudeClient.this.apiKey)) {
                    AmplitudeClient.this.startNewSessionIfNeeded(j2);
                    boolean unused = AmplitudeClient.this.inForeground = true;
                }
            }
        });
    }

    public void onExitForeground(final long j2) {
        runOnLogThread(new Runnable() {
            public void run() {
                if (!Utils.isEmptyString(AmplitudeClient.this.apiKey)) {
                    AmplitudeClient.this.refreshSessionTime(j2);
                    boolean unused = AmplitudeClient.this.inForeground = false;
                    if (AmplitudeClient.this.flushEventsOnClose) {
                        AmplitudeClient.this.updateServer();
                    }
                    AmplitudeClient amplitudeClient = AmplitudeClient.this;
                    amplitudeClient.dbHelper.insertOrReplaceKeyValue(AmplitudeClient.DEVICE_ID_KEY, amplitudeClient.deviceId);
                    AmplitudeClient amplitudeClient2 = AmplitudeClient.this;
                    amplitudeClient2.dbHelper.insertOrReplaceKeyValue(AmplitudeClient.USER_ID_KEY, amplitudeClient2.userId);
                    AmplitudeClient amplitudeClient3 = AmplitudeClient.this;
                    amplitudeClient3.dbHelper.insertOrReplaceKeyLongValue(AmplitudeClient.OPT_OUT_KEY, Long.valueOf(amplitudeClient3.optOut ? 1 : 0));
                    AmplitudeClient amplitudeClient4 = AmplitudeClient.this;
                    amplitudeClient4.dbHelper.insertOrReplaceKeyLongValue(AmplitudeClient.PREVIOUS_SESSION_ID_KEY, Long.valueOf(amplitudeClient4.sessionId));
                    AmplitudeClient amplitudeClient5 = AmplitudeClient.this;
                    amplitudeClient5.dbHelper.insertOrReplaceKeyLongValue(AmplitudeClient.LAST_EVENT_TIME_KEY, Long.valueOf(amplitudeClient5.lastEventTime));
                }
            }
        });
    }

    public void refreshSessionTime(long j2) {
        if (inSession()) {
            setLastEventTime(j2);
        }
    }

    public AmplitudeClient regenerateDeviceId() {
        if (!contextAndApiKeySet("regenerateDeviceId()")) {
            return this;
        }
        runOnLogThread(new Runnable() {
            public void run() {
                if (!Utils.isEmptyString(this.apiKey)) {
                    AmplitudeClient.this.setDeviceId(DeviceInfo.generateUUID() + "R");
                }
            }
        });
        return this;
    }

    public Object replaceWithJSONNull(Object obj) {
        return obj == null ? JSONObject.NULL : obj;
    }

    public void runOnLogThread(Runnable runnable) {
        Thread currentThread = Thread.currentThread();
        WorkerThread workerThread = this.logThread;
        if (currentThread != workerThread) {
            workerThread.post(runnable);
        } else {
            runnable.run();
        }
    }

    public long saveEvent(String str, JSONObject jSONObject) {
        String jSONObject2 = jSONObject.toString();
        if (Utils.isEmptyString(jSONObject2)) {
            AmplitudeLog amplitudeLog = logger;
            amplitudeLog.e(TAG, "Detected empty event string for event type " + str + ", skipping");
            return -1;
        }
        if (str.equals(Constants.IDENTIFY_EVENT) || str.equals(Constants.GROUP_IDENTIFY_EVENT)) {
            long addIdentify = this.dbHelper.addIdentify(jSONObject2);
            this.lastIdentifyId = addIdentify;
            setLastIdentifyId(addIdentify);
        } else {
            long addEvent = this.dbHelper.addEvent(jSONObject2);
            this.lastEventId = addEvent;
            setLastEventId(addEvent);
        }
        int min = Math.min(Math.max(1, this.eventMaxCount / 10), 20);
        if (this.dbHelper.getEventCount() > ((long) this.eventMaxCount)) {
            DatabaseHelper databaseHelper = this.dbHelper;
            databaseHelper.removeEvents(databaseHelper.getNthEventId((long) min));
        }
        if (this.dbHelper.getIdentifyCount() > ((long) this.eventMaxCount)) {
            DatabaseHelper databaseHelper2 = this.dbHelper;
            databaseHelper2.removeIdentifys(databaseHelper2.getNthIdentifyId((long) min));
        }
        long totalEventCount = this.dbHelper.getTotalEventCount();
        int i3 = this.eventUploadThreshold;
        if (totalEventCount % ((long) i3) != 0 || totalEventCount < ((long) i3)) {
            updateServerLater(this.eventUploadPeriodMillis);
        } else {
            updateServer();
        }
        return (str.equals(Constants.IDENTIFY_EVENT) || str.equals(Constants.GROUP_IDENTIFY_EVENT)) ? this.lastIdentifyId : this.lastEventId;
    }

    public AmplitudeClient setDeviceId(final String str) {
        Set<String> invalidDeviceIds = getInvalidDeviceIds();
        if (contextAndApiKeySet("setDeviceId()") && !Utils.isEmptyString(str) && !invalidDeviceIds.contains(str)) {
            runOnLogThread(new Runnable() {
                public void run() {
                    if (!Utils.isEmptyString(this.apiKey)) {
                        AmplitudeClient amplitudeClient = this;
                        String str = str;
                        amplitudeClient.deviceId = str;
                        AmplitudeClient.this.saveDeviceId(str);
                    }
                }
            });
        }
        return this;
    }

    public AmplitudeClient setDiagnosticEventMaxCount(int i3) {
        Diagnostics.getLogger().setDiagnosticEventMaxCount(i3);
        return this;
    }

    public AmplitudeClient setEventMaxCount(int i3) {
        this.eventMaxCount = i3;
        return this;
    }

    public AmplitudeClient setEventUploadMaxBatchSize(int i3) {
        this.eventUploadMaxBatchSize = i3;
        this.backoffUploadBatchSize = i3;
        return this;
    }

    public AmplitudeClient setEventUploadPeriodMillis(int i3) {
        this.eventUploadPeriodMillis = (long) i3;
        return this;
    }

    public AmplitudeClient setEventUploadThreshold(int i3) {
        this.eventUploadThreshold = i3;
        return this;
    }

    public AmplitudeClient setFlushEventsOnClose(boolean z2) {
        this.flushEventsOnClose = z2;
        return this;
    }

    public void setGroup(String str, Object obj) {
        JSONObject jSONObject;
        if (contextAndApiKeySet("setGroup()") && !Utils.isEmptyString(str)) {
            try {
                jSONObject = new JSONObject().put(str, obj);
            } catch (JSONException e3) {
                logger.e(TAG, e3.toString());
                Diagnostics logger2 = Diagnostics.getLogger();
                logger2.logError("Failed to generate Group JSON for groupType: " + str, e3);
                jSONObject = null;
            }
            logEventAsync(Constants.IDENTIFY_EVENT, (JSONObject) null, (JSONObject) null, new Identify().setUserProperty(str, obj).userPropertiesOperations, jSONObject, (JSONObject) null, getCurrentTimeMillis(), false);
        }
    }

    public void setLastEventId(long j2) {
        this.lastEventId = j2;
        this.dbHelper.insertOrReplaceKeyLongValue(LAST_EVENT_ID_KEY, Long.valueOf(j2));
    }

    public void setLastEventTime(long j2) {
        this.lastEventTime = j2;
        this.dbHelper.insertOrReplaceKeyLongValue(LAST_EVENT_TIME_KEY, Long.valueOf(j2));
    }

    public void setLastIdentifyId(long j2) {
        this.lastIdentifyId = j2;
        this.dbHelper.insertOrReplaceKeyLongValue(LAST_IDENTIFY_ID_KEY, Long.valueOf(j2));
    }

    public AmplitudeClient setLogLevel(int i3) {
        logger.setLogLevel(i3);
        return this;
    }

    public AmplitudeClient setMinTimeBetweenSessionsMillis(long j2) {
        this.minTimeBetweenSessionsMillis = j2;
        return this;
    }

    public AmplitudeClient setOffline(boolean z2) {
        this.offline = z2;
        if (!z2) {
            uploadEvents();
        }
        return this;
    }

    public AmplitudeClient setOptOut(final boolean z2) {
        if (!contextAndApiKeySet("setOptOut()")) {
            return this;
        }
        runOnLogThread(new Runnable() {
            public void run() {
                if (!Utils.isEmptyString(AmplitudeClient.this.apiKey)) {
                    boolean unused = this.optOut = z2;
                    AmplitudeClient.this.dbHelper.insertOrReplaceKeyLongValue(AmplitudeClient.OPT_OUT_KEY, Long.valueOf(z2 ? 1 : 0));
                }
            }
        });
        return this;
    }

    public void setPreviousSessionId(long j2) {
        this.previousSessionId = j2;
        this.dbHelper.insertOrReplaceKeyLongValue(PREVIOUS_SESSION_ID_KEY, Long.valueOf(j2));
    }

    public AmplitudeClient setServerUrl(String str) {
        if (!Utils.isEmptyString(str)) {
            this.url = str;
        }
        return this;
    }

    public AmplitudeClient setSessionTimeoutMillis(long j2) {
        this.sessionTimeoutMillis = j2;
        return this;
    }

    public AmplitudeClient setTrackingOptions(TrackingOptions trackingOptions2) {
        this.trackingOptions = trackingOptions2;
        this.apiPropertiesTrackingOptions = trackingOptions2.getApiPropertiesTrackingOptions();
        return this;
    }

    public AmplitudeClient setUserId(String str) {
        return setUserId(str, false);
    }

    public void setUserProperties(JSONObject jSONObject, boolean z2) {
        setUserProperties(jSONObject);
    }

    public boolean startNewSessionIfNeeded(long j2) {
        if (inSession()) {
            if (isWithinMinTimeBetweenSessions(j2)) {
                refreshSessionTime(j2);
                return false;
            }
            startNewSession(j2);
            return true;
        } else if (isWithinMinTimeBetweenSessions(j2)) {
            long j3 = this.previousSessionId;
            if (j3 == -1) {
                startNewSession(j2);
                return true;
            }
            setSessionId(j3);
            refreshSessionTime(j2);
            return false;
        } else {
            startNewSession(j2);
            return true;
        }
    }

    public AmplitudeClient trackSessionEvents(boolean z2) {
        this.trackingSessionEvents = z2;
        return this;
    }

    public JSONObject truncate(JSONObject jSONObject) {
        if (jSONObject == null) {
            return new JSONObject();
        }
        if (jSONObject.length() > 1000) {
            logger.w(TAG, "Warning: too many properties (more than 1000), ignoring");
            return new JSONObject();
        }
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            try {
                Object obj = jSONObject.get(next);
                if (!next.equals(Constants.AMP_REVENUE_RECEIPT)) {
                    if (!next.equals(Constants.AMP_REVENUE_RECEIPT_SIG)) {
                        if (obj.getClass().equals(String.class)) {
                            jSONObject.put(next, truncate((String) obj));
                        } else if (obj.getClass().equals(JSONObject.class)) {
                            jSONObject.put(next, truncate((JSONObject) obj));
                        } else if (obj.getClass().equals(JSONArray.class)) {
                            jSONObject.put(next, truncate((JSONArray) obj));
                        }
                    }
                }
                jSONObject.put(next, obj);
            } catch (JSONException e3) {
                logger.e(TAG, e3.toString());
            }
        }
        return jSONObject;
    }

    public void updateServer() {
        updateServer(false);
        Diagnostics.getLogger().flushEvents();
    }

    public void uploadEvents() {
        if (contextAndApiKeySet("uploadEvents()")) {
            this.logThread.post(new Runnable() {
                public void run() {
                    if (!Utils.isEmptyString(AmplitudeClient.this.apiKey)) {
                        AmplitudeClient.this.updateServer();
                    }
                }
            });
        }
    }

    public AmplitudeClient useAdvertisingIdForDeviceId() {
        this.useAdvertisingIdForDeviceId = true;
        return this;
    }

    public void useForegroundTracking() {
        this.usingForegroundTracking = true;
    }

    public boolean validateLogEvent(String str) {
        if (!Utils.isEmptyString(str)) {
            return contextAndApiKeySet("logEvent()");
        }
        logger.e(TAG, "Argument eventType cannot be null or blank in logEvent()");
        return false;
    }

    public AmplitudeClient(String str) {
        this.newDeviceIdPerInstall = false;
        this.useAdvertisingIdForDeviceId = false;
        this.initialized = false;
        this.optOut = false;
        this.offline = false;
        this.trackingOptions = new TrackingOptions();
        this.sessionId = -1;
        this.sequenceNumber = 0;
        this.lastEventId = -1;
        this.lastIdentifyId = -1;
        this.lastEventTime = -1;
        this.previousSessionId = -1;
        this.eventUploadThreshold = 30;
        this.eventUploadMaxBatchSize = 50;
        this.eventMaxCount = 1000;
        this.eventUploadPeriodMillis = 30000;
        this.minTimeBetweenSessionsMillis = 300000;
        this.sessionTimeoutMillis = Constants.SESSION_TIMEOUT_MILLIS;
        this.backoffUpload = false;
        this.backoffUploadBatchSize = 50;
        this.usingForegroundTracking = false;
        this.trackingSessionEvents = false;
        this.inForeground = false;
        this.flushEventsOnClose = true;
        this.updateScheduled = new AtomicBoolean(false);
        this.uploadingCurrently = new AtomicBoolean(false);
        this.url = Constants.EVENT_LOG_URL;
        this.logThread = new WorkerThread("logThread");
        this.httpThread = new WorkerThread("httpThread");
        this.instanceName = Utils.normalizeInstanceName(str);
        this.logThread.start();
        this.httpThread.start();
    }

    public static boolean upgradePrefs(Context context2, String str, String str2) {
        if (str == null) {
            try {
                str = Constants.class.getPackage().getName();
            } catch (Exception unused) {
                str = "com.amplitude.api";
            }
        }
        if (str2 == null) {
            str2 = "com.amplitude.api";
        }
        try {
            if (str2.equals(str)) {
                return false;
            }
            String str3 = str + JwtUtilsKt.JWT_DELIMITER + context2.getPackageName();
            SharedPreferences sharedPreferences = context2.getSharedPreferences(str3, 0);
            if (sharedPreferences.getAll().size() == 0) {
                return false;
            }
            String str4 = str2 + JwtUtilsKt.JWT_DELIMITER + context2.getPackageName();
            SharedPreferences.Editor edit = context2.getSharedPreferences(str4, 0).edit();
            if (sharedPreferences.contains(str + ".previousSessionId")) {
                edit.putLong(Constants.PREFKEY_PREVIOUS_SESSION_ID, sharedPreferences.getLong(str + ".previousSessionId", -1));
            }
            if (sharedPreferences.contains(str + ".deviceId")) {
                edit.putString(Constants.PREFKEY_DEVICE_ID, sharedPreferences.getString(str + ".deviceId", (String) null));
            }
            if (sharedPreferences.contains(str + ".userId")) {
                edit.putString(Constants.PREFKEY_USER_ID, sharedPreferences.getString(str + ".userId", (String) null));
            }
            if (sharedPreferences.contains(str + ".optOut")) {
                edit.putBoolean(Constants.PREFKEY_OPT_OUT, sharedPreferences.getBoolean(str + ".optOut", false));
            }
            edit.apply();
            sharedPreferences.edit().clear().apply();
            logger.i(TAG, "Upgraded shared preferences from " + str3 + " to " + str4);
            return true;
        } catch (Exception e3) {
            logger.e(TAG, "Error upgrading shared preferences", e3);
            Diagnostics.getLogger().logError("Failed to upgrade shared prefs", e3);
            return false;
        }
    }

    public static boolean upgradeSharedPrefsToDB(Context context2, String str) {
        if (str == null) {
            str = "com.amplitude.api";
        }
        DatabaseHelper databaseHelper = DatabaseHelper.getDatabaseHelper(context2);
        String value = databaseHelper.getValue(DEVICE_ID_KEY);
        Long longValue = databaseHelper.getLongValue(PREVIOUS_SESSION_ID_KEY);
        Long longValue2 = databaseHelper.getLongValue(LAST_EVENT_TIME_KEY);
        if (!Utils.isEmptyString(value) && longValue != null && longValue2 != null) {
            return true;
        }
        StringBuilder q2 = a.q(str, JwtUtilsKt.JWT_DELIMITER);
        q2.append(context2.getPackageName());
        SharedPreferences sharedPreferences = context2.getSharedPreferences(q2.toString(), 0);
        migrateStringValue(sharedPreferences, Constants.PREFKEY_DEVICE_ID, (String) null, databaseHelper, DEVICE_ID_KEY);
        SharedPreferences sharedPreferences2 = sharedPreferences;
        DatabaseHelper databaseHelper2 = databaseHelper;
        migrateLongValue(sharedPreferences2, Constants.PREFKEY_LAST_EVENT_TIME, -1, databaseHelper2, LAST_EVENT_TIME_KEY);
        migrateLongValue(sharedPreferences2, Constants.PREFKEY_LAST_EVENT_ID, -1, databaseHelper2, LAST_EVENT_ID_KEY);
        migrateLongValue(sharedPreferences2, Constants.PREFKEY_LAST_IDENTIFY_ID, -1, databaseHelper2, LAST_IDENTIFY_ID_KEY);
        migrateLongValue(sharedPreferences2, Constants.PREFKEY_PREVIOUS_SESSION_ID, -1, databaseHelper2, PREVIOUS_SESSION_ID_KEY);
        migrateStringValue(sharedPreferences, Constants.PREFKEY_USER_ID, (String) null, databaseHelper, USER_ID_KEY);
        migrateBooleanValue(sharedPreferences, Constants.PREFKEY_OPT_OUT, false, databaseHelper, OPT_OUT_KEY);
        return true;
    }

    public void groupIdentify(String str, Object obj, Identify identify, boolean z2) {
        JSONObject jSONObject;
        if (identify != null && identify.userPropertiesOperations.length() != 0 && contextAndApiKeySet("groupIdentify()") && !Utils.isEmptyString(str)) {
            try {
                jSONObject = new JSONObject().put(str, obj);
            } catch (JSONException e3) {
                logger.e(TAG, e3.toString());
                Diagnostics logger2 = Diagnostics.getLogger();
                logger2.logError("Failed to generate Group Identify JSON Object for groupType " + str, e3);
                jSONObject = null;
            }
            logEventAsync(Constants.GROUP_IDENTIFY_EVENT, (JSONObject) null, (JSONObject) null, (JSONObject) null, jSONObject, identify.userPropertiesOperations, getCurrentTimeMillis(), z2);
        }
    }

    public void identify(Identify identify, boolean z2) {
        if (identify != null && identify.userPropertiesOperations.length() != 0 && contextAndApiKeySet("identify()")) {
            logEventAsync(Constants.IDENTIFY_EVENT, (JSONObject) null, (JSONObject) null, identify.userPropertiesOperations, (JSONObject) null, (JSONObject) null, getCurrentTimeMillis(), z2);
        }
    }

    public AmplitudeClient initialize(Context context2, String str, String str2) {
        return initialize(context2, str, str2, (String) null, false);
    }

    public void logEvent(String str, JSONObject jSONObject) {
        logEvent(str, jSONObject, false);
    }

    public void logEventSync(String str, JSONObject jSONObject) {
        logEventSync(str, jSONObject, false);
    }

    public void logRevenue(String str, int i3, double d2) {
        logRevenue(str, i3, d2, (String) null, (String) null);
    }

    public AmplitudeClient setUserId(final String str, final boolean z2) {
        if (!contextAndApiKeySet("setUserId()")) {
            return this;
        }
        runOnLogThread(new Runnable() {
            public void run() {
                if (!Utils.isEmptyString(this.apiKey)) {
                    if (z2 && AmplitudeClient.this.trackingSessionEvents) {
                        AmplitudeClient.this.sendSessionEvent(AmplitudeClient.END_SESSION_EVENT);
                    }
                    AmplitudeClient amplitudeClient = this;
                    String str = str;
                    amplitudeClient.userId = str;
                    AmplitudeClient.this.dbHelper.insertOrReplaceKeyValue(AmplitudeClient.USER_ID_KEY, str);
                    if (z2) {
                        long currentTimeMillis = AmplitudeClient.this.getCurrentTimeMillis();
                        AmplitudeClient.this.setSessionId(currentTimeMillis);
                        AmplitudeClient.this.refreshSessionTime(currentTimeMillis);
                        if (AmplitudeClient.this.trackingSessionEvents) {
                            AmplitudeClient.this.sendSessionEvent(AmplitudeClient.START_SESSION_EVENT);
                        }
                    }
                }
            }
        });
        return this;
    }

    public void setUserProperties(JSONObject jSONObject) {
        if (jSONObject != null && jSONObject.length() != 0 && contextAndApiKeySet("setUserProperties")) {
            JSONObject truncate = truncate(jSONObject);
            if (truncate.length() != 0) {
                Identify identify = new Identify();
                Iterator<String> keys = truncate.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    try {
                        identify.setUserProperty(next, truncate.get(next));
                    } catch (JSONException e3) {
                        logger.e(TAG, e3.toString());
                        Diagnostics logger2 = Diagnostics.getLogger();
                        logger2.logError("Failed to set user property " + next, e3);
                    }
                }
                identify(identify);
            }
        }
    }

    public synchronized AmplitudeClient initialize(Context context2, String str, String str2, String str3, boolean z2) {
        if (context2 == null) {
            logger.e(TAG, "Argument context cannot be null in initialize()");
            return this;
        } else if (Utils.isEmptyString(str)) {
            logger.e(TAG, "Argument apiKey cannot be null or blank in initialize()");
            return this;
        } else {
            Context applicationContext = context2.getApplicationContext();
            this.context = applicationContext;
            this.apiKey = str;
            this.dbHelper = DatabaseHelper.getDatabaseHelper(applicationContext, this.instanceName);
            if (Utils.isEmptyString(str3)) {
                str3 = Constants.PLATFORM;
            }
            this.platform = str3;
            final Context context3 = context2;
            final boolean z3 = z2;
            final String str4 = str;
            final String str5 = str2;
            runOnLogThread(new Runnable() {
                public void run() {
                    AmplitudeClient amplitudeClient = AmplitudeClient.this;
                    if (!amplitudeClient.initialized) {
                        try {
                            if (amplitudeClient.instanceName.equals(Constants.DEFAULT_INSTANCE)) {
                                AmplitudeClient.upgradePrefs(context3);
                                AmplitudeClient.upgradeSharedPrefsToDB(context3);
                            }
                            AmplitudeClient.this.httpClient = new OkHttpClient();
                            DeviceInfo unused = AmplitudeClient.this.deviceInfo = new DeviceInfo(context3);
                            AmplitudeClient amplitudeClient2 = AmplitudeClient.this;
                            amplitudeClient2.deviceId = amplitudeClient2.initializeDeviceId();
                            if (z3) {
                                Diagnostics logger = Diagnostics.getLogger();
                                AmplitudeClient amplitudeClient3 = AmplitudeClient.this;
                                logger.enableLogging(amplitudeClient3.httpClient, str4, amplitudeClient3.deviceId);
                            }
                            AmplitudeClient.this.deviceInfo.prefetch();
                            String str = str5;
                            if (str != null) {
                                this.userId = str;
                                AmplitudeClient.this.dbHelper.insertOrReplaceKeyValue(AmplitudeClient.USER_ID_KEY, str);
                            } else {
                                this.userId = AmplitudeClient.this.dbHelper.getValue(AmplitudeClient.USER_ID_KEY);
                            }
                            Long longValue = AmplitudeClient.this.dbHelper.getLongValue(AmplitudeClient.OPT_OUT_KEY);
                            boolean unused2 = AmplitudeClient.this.optOut = longValue != null && longValue.longValue() == 1;
                            AmplitudeClient amplitudeClient4 = AmplitudeClient.this;
                            amplitudeClient4.previousSessionId = amplitudeClient4.getLongvalue(AmplitudeClient.PREVIOUS_SESSION_ID_KEY, -1);
                            AmplitudeClient amplitudeClient5 = AmplitudeClient.this;
                            long j2 = amplitudeClient5.previousSessionId;
                            if (j2 >= 0) {
                                amplitudeClient5.sessionId = j2;
                            }
                            amplitudeClient5.sequenceNumber = amplitudeClient5.getLongvalue(AmplitudeClient.SEQUENCE_NUMBER_KEY, 0);
                            AmplitudeClient amplitudeClient6 = AmplitudeClient.this;
                            amplitudeClient6.lastEventId = amplitudeClient6.getLongvalue(AmplitudeClient.LAST_EVENT_ID_KEY, -1);
                            AmplitudeClient amplitudeClient7 = AmplitudeClient.this;
                            amplitudeClient7.lastIdentifyId = amplitudeClient7.getLongvalue(AmplitudeClient.LAST_IDENTIFY_ID_KEY, -1);
                            AmplitudeClient amplitudeClient8 = AmplitudeClient.this;
                            amplitudeClient8.lastEventTime = amplitudeClient8.getLongvalue(AmplitudeClient.LAST_EVENT_TIME_KEY, -1);
                            AmplitudeClient.this.dbHelper.setDatabaseResetListener(new DatabaseResetListener() {
                                public void onDatabaseReset(SQLiteDatabase sQLiteDatabase) {
                                    AnonymousClass1 r02 = AnonymousClass1.this;
                                    AmplitudeClient.this.dbHelper.insertOrReplaceKeyValueToTable(sQLiteDatabase, "store", AmplitudeClient.DEVICE_ID_KEY, this.deviceId);
                                    AnonymousClass1 r03 = AnonymousClass1.this;
                                    AmplitudeClient.this.dbHelper.insertOrReplaceKeyValueToTable(sQLiteDatabase, "store", AmplitudeClient.USER_ID_KEY, this.userId);
                                    AnonymousClass1 r04 = AnonymousClass1.this;
                                    AmplitudeClient.this.dbHelper.insertOrReplaceKeyValueToTable(sQLiteDatabase, "long_store", AmplitudeClient.OPT_OUT_KEY, Long.valueOf(this.optOut ? 1 : 0));
                                    AnonymousClass1 r05 = AnonymousClass1.this;
                                    AmplitudeClient.this.dbHelper.insertOrReplaceKeyValueToTable(sQLiteDatabase, "long_store", AmplitudeClient.PREVIOUS_SESSION_ID_KEY, Long.valueOf(this.sessionId));
                                    AnonymousClass1 r5 = AnonymousClass1.this;
                                    AmplitudeClient.this.dbHelper.insertOrReplaceKeyValueToTable(sQLiteDatabase, "long_store", AmplitudeClient.LAST_EVENT_TIME_KEY, Long.valueOf(this.lastEventTime));
                                }
                            });
                            AmplitudeClient.this.initialized = true;
                        } catch (CursorWindowAllocationException e3) {
                            AmplitudeLog access$400 = AmplitudeClient.logger;
                            String message = e3.getMessage();
                            access$400.e(AmplitudeClient.TAG, "Failed to initialize Amplitude SDK due to: " + message);
                            Diagnostics.getLogger().logError("Failed to initialize Amplitude SDK", e3);
                            this.apiKey = null;
                        }
                    }
                }
            });
            return this;
        }
    }

    public void logEvent(String str, JSONObject jSONObject, boolean z2) {
        logEvent(str, jSONObject, (JSONObject) null, z2);
    }

    public void logEventSync(String str, JSONObject jSONObject, boolean z2) {
        logEventSync(str, jSONObject, (JSONObject) null, z2);
    }

    public void logRevenue(String str, int i3, double d2, String str2, String str3) {
        if (contextAndApiKeySet("logRevenue()")) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("special", Constants.AMP_REVENUE_EVENT);
                String str4 = str;
                jSONObject.put("productId", str);
                int i4 = i3;
                jSONObject.put(FirebaseAnalytics.Param.QUANTITY, i3);
                double d3 = d2;
                jSONObject.put(FirebaseAnalytics.Param.PRICE, d2);
                jSONObject.put("receipt", str2);
                jSONObject.put("receiptSig", str3);
            } catch (JSONException e3) {
                Diagnostics.getLogger().logError("Failed to generate API Properties JSON for revenue event", e3);
            }
            logEventAsync(Constants.AMP_REVENUE_EVENT, (JSONObject) null, jSONObject, (JSONObject) null, (JSONObject) null, (JSONObject) null, getCurrentTimeMillis(), false);
        }
    }

    public void updateServer(boolean z2) {
        if (!this.optOut && !this.offline && !this.uploadingCurrently.getAndSet(true)) {
            long min = Math.min((long) (z2 ? this.backoffUploadBatchSize : this.eventUploadMaxBatchSize), this.dbHelper.getTotalEventCount());
            if (min <= 0) {
                this.uploadingCurrently.set(false);
                return;
            }
            try {
                Pair<Pair<Long, Long>, JSONArray> mergeEventsAndIdentifys = mergeEventsAndIdentifys(this.dbHelper.getEvents(this.lastEventId, min), this.dbHelper.getIdentifys(this.lastIdentifyId, min), min);
                if (((JSONArray) mergeEventsAndIdentifys.second).length() == 0) {
                    this.uploadingCurrently.set(false);
                    return;
                }
                final long longValue = ((Long) ((Pair) mergeEventsAndIdentifys.first).first).longValue();
                final long longValue2 = ((Long) ((Pair) mergeEventsAndIdentifys.first).second).longValue();
                final String jSONArray = ((JSONArray) mergeEventsAndIdentifys.second).toString();
                this.httpThread.post(new Runnable() {
                    public void run() {
                        AmplitudeClient amplitudeClient = AmplitudeClient.this;
                        amplitudeClient.makeEventUploadPostRequest(amplitudeClient.httpClient, jSONArray, longValue, longValue2);
                    }
                });
            } catch (JSONException e3) {
                this.uploadingCurrently.set(false);
                logger.e(TAG, e3.toString());
                Diagnostics.getLogger().logError("Failed to update server", e3);
            } catch (CursorWindowAllocationException e4) {
                this.uploadingCurrently.set(false);
                AmplitudeLog amplitudeLog = logger;
                String message = e4.getMessage();
                amplitudeLog.e(TAG, "Caught Cursor window exception during event upload, deferring upload: " + message);
                Diagnostics.getLogger().logError("Failed to update server", e4);
            }
        }
    }

    public void logEvent(String str, JSONObject jSONObject, JSONObject jSONObject2) {
        logEvent(str, jSONObject, jSONObject2, false);
    }

    public void logEventSync(String str, JSONObject jSONObject, JSONObject jSONObject2) {
        logEventSync(str, jSONObject, jSONObject2, false);
    }

    public void logEvent(String str, JSONObject jSONObject, JSONObject jSONObject2, boolean z2) {
        logEvent(str, jSONObject, jSONObject2, getCurrentTimeMillis(), z2);
    }

    public void logEventSync(String str, JSONObject jSONObject, JSONObject jSONObject2, boolean z2) {
        logEventSync(str, jSONObject, jSONObject2, getCurrentTimeMillis(), z2);
    }

    public void logEvent(String str, JSONObject jSONObject, JSONObject jSONObject2, long j2, boolean z2) {
        if (validateLogEvent(str)) {
            logEventAsync(str, jSONObject, (JSONObject) null, (JSONObject) null, jSONObject2, (JSONObject) null, j2, z2);
        }
    }

    public void logEventSync(String str, JSONObject jSONObject, JSONObject jSONObject2, long j2, boolean z2) {
        if (validateLogEvent(str)) {
            logEvent(str, jSONObject, (JSONObject) null, (JSONObject) null, jSONObject2, (JSONObject) null, j2, z2);
        }
    }

    public long logEvent(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, JSONObject jSONObject4, JSONObject jSONObject5, long j2, boolean z2) {
        JSONObject jSONObject6;
        JSONObject jSONObject7;
        JSONObject jSONObject8;
        Location mostRecentLocation;
        String str2 = str;
        JSONObject jSONObject9 = jSONObject;
        JSONObject jSONObject10 = jSONObject3;
        JSONObject jSONObject11 = jSONObject4;
        JSONObject jSONObject12 = jSONObject5;
        long j3 = j2;
        AmplitudeLog amplitudeLog = logger;
        amplitudeLog.d(TAG, "Logged event to Amplitude: " + str2);
        if (this.optOut) {
            return -1;
        }
        if ((!this.trackingSessionEvents || (!str2.equals(START_SESSION_EVENT) && !str2.equals(END_SESSION_EVENT))) && !z2) {
            if (!this.inForeground) {
                startNewSessionIfNeeded(j3);
            } else {
                refreshSessionTime(j3);
            }
        }
        JSONObject jSONObject13 = new JSONObject();
        try {
            jSONObject13.put("event_type", replaceWithJSONNull(str));
            jSONObject13.put("timestamp", j3);
            jSONObject13.put(USER_ID_KEY, replaceWithJSONNull(this.userId));
            jSONObject13.put(DEVICE_ID_KEY, replaceWithJSONNull(this.deviceId));
            jSONObject13.put("session_id", z2 ? -1 : this.sessionId);
            jSONObject13.put("uuid", UUID.randomUUID().toString());
            jSONObject13.put(SEQUENCE_NUMBER_KEY, getNextSequenceNumber());
            if (this.trackingOptions.shouldTrackVersionName()) {
                jSONObject13.put(Constants.AMP_TRACKING_OPTION_VERSION_NAME, replaceWithJSONNull(this.deviceInfo.getVersionName()));
            }
            if (this.trackingOptions.shouldTrackOsName()) {
                jSONObject13.put(Constants.AMP_TRACKING_OPTION_OS_NAME, replaceWithJSONNull(this.deviceInfo.getOsName()));
            }
            if (this.trackingOptions.shouldTrackOsVersion()) {
                jSONObject13.put(Constants.AMP_TRACKING_OPTION_OS_VERSION, replaceWithJSONNull(this.deviceInfo.getOsVersion()));
            }
            if (this.trackingOptions.shouldTrackDeviceBrand()) {
                jSONObject13.put(Constants.AMP_TRACKING_OPTION_DEVICE_BRAND, replaceWithJSONNull(this.deviceInfo.getBrand()));
            }
            if (this.trackingOptions.shouldTrackDeviceManufacturer()) {
                jSONObject13.put(Constants.AMP_TRACKING_OPTION_DEVICE_MANUFACTURER, replaceWithJSONNull(this.deviceInfo.getManufacturer()));
            }
            if (this.trackingOptions.shouldTrackDeviceModel()) {
                jSONObject13.put(Constants.AMP_TRACKING_OPTION_DEVICE_MODEL, replaceWithJSONNull(this.deviceInfo.getModel()));
            }
            if (this.trackingOptions.shouldTrackCarrier()) {
                jSONObject13.put(Constants.AMP_TRACKING_OPTION_CARRIER, replaceWithJSONNull(this.deviceInfo.getCarrier()));
            }
            if (this.trackingOptions.shouldTrackCountry()) {
                jSONObject13.put(Constants.AMP_TRACKING_OPTION_COUNTRY, replaceWithJSONNull(this.deviceInfo.getCountry()));
            }
            if (this.trackingOptions.shouldTrackLanguage()) {
                jSONObject13.put("language", replaceWithJSONNull(this.deviceInfo.getLanguage()));
            }
            if (this.trackingOptions.shouldTrackPlatform()) {
                jSONObject13.put(Constants.AMP_TRACKING_OPTION_PLATFORM, this.platform);
            }
            JSONObject jSONObject14 = new JSONObject();
            jSONObject14.put("name", Constants.LIBRARY);
            jSONObject14.put("version", Constants.VERSION);
            jSONObject13.put("library", jSONObject14);
            JSONObject jSONObject15 = jSONObject2 == null ? new JSONObject() : jSONObject2;
            JSONObject jSONObject16 = this.apiPropertiesTrackingOptions;
            if (jSONObject16 != null && jSONObject16.length() > 0) {
                jSONObject15.put("tracking_options", this.apiPropertiesTrackingOptions);
            }
            if (this.trackingOptions.shouldTrackLatLng() && (mostRecentLocation = this.deviceInfo.getMostRecentLocation()) != null) {
                JSONObject jSONObject17 = new JSONObject();
                jSONObject17.put("lat", mostRecentLocation.getLatitude());
                jSONObject17.put("lng", mostRecentLocation.getLongitude());
                jSONObject15.put(FirebaseAnalytics.Param.LOCATION, jSONObject17);
            }
            if (this.trackingOptions.shouldTrackAdid() && this.deviceInfo.getAdvertisingId() != null) {
                jSONObject15.put("androidADID", this.deviceInfo.getAdvertisingId());
            }
            jSONObject15.put("limit_ad_tracking", this.deviceInfo.isLimitAdTrackingEnabled());
            jSONObject15.put("gps_enabled", this.deviceInfo.isGooglePlayServicesEnabled());
            jSONObject13.put("api_properties", jSONObject15);
            if (jSONObject9 == null) {
                jSONObject6 = new JSONObject();
            } else {
                jSONObject6 = truncate(jSONObject9);
            }
            jSONObject13.put("event_properties", jSONObject6);
            if (jSONObject10 == null) {
                jSONObject7 = new JSONObject();
            } else {
                jSONObject7 = truncate(jSONObject10);
            }
            jSONObject13.put("user_properties", jSONObject7);
            jSONObject13.put("groups", jSONObject11 == null ? new JSONObject() : truncate(jSONObject11));
            if (jSONObject12 == null) {
                jSONObject8 = new JSONObject();
            } else {
                jSONObject8 = truncate(jSONObject12);
            }
            jSONObject13.put("group_properties", jSONObject8);
            return saveEvent(str2, jSONObject13);
        } catch (JSONException e3) {
            logger.e(TAG, C0118y.f("JSON Serialization of event type ", str2, " failed, skipping: ", e3.toString()));
            Diagnostics logger2 = Diagnostics.getLogger();
            logger2.logError("Failed to JSON serialize event type " + str2, e3);
            return -1;
        }
    }

    public JSONArray truncate(JSONArray jSONArray) throws JSONException {
        if (jSONArray == null) {
            return new JSONArray();
        }
        for (int i3 = 0; i3 < jSONArray.length(); i3++) {
            Object obj = jSONArray.get(i3);
            if (obj.getClass().equals(String.class)) {
                jSONArray.put(i3, truncate((String) obj));
            } else if (obj.getClass().equals(JSONObject.class)) {
                jSONArray.put(i3, truncate((JSONObject) obj));
            } else if (obj.getClass().equals(JSONArray.class)) {
                jSONArray.put(i3, truncate((JSONArray) obj));
            }
        }
        return jSONArray;
    }

    public static String truncate(String str) {
        return str.length() <= 1024 ? str : str.substring(0, 1024);
    }
}
