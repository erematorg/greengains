package com.google.firebase.remoteconfig.internal;

import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.remoteconfig.ConfigUpdate;
import com.google.firebase.remoteconfig.ConfigUpdateListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigClientException;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigServerException;
import com.google.firebase.remoteconfig.internal.ConfigFetchHandler;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ConfigAutoFetch {
    private static final int MAXIMUM_FETCH_ATTEMPTS = 3;
    private static final String REALTIME_DISABLED_KEY = "featureDisabled";
    private static final String TEMPLATE_VERSION_KEY = "latestTemplateVersionNumber";
    private final ConfigCacheClient activatedCache;
    private final ConfigFetchHandler configFetchHandler;
    @GuardedBy("this")
    private final Set<ConfigUpdateListener> eventListeners;
    private final HttpURLConnection httpURLConnection;
    private final Random random = new Random();
    private final ConfigUpdateListener retryCallback;
    private final ScheduledExecutorService scheduledExecutorService;

    public ConfigAutoFetch(HttpURLConnection httpURLConnection2, ConfigFetchHandler configFetchHandler2, ConfigCacheClient configCacheClient, Set<ConfigUpdateListener> set, ConfigUpdateListener configUpdateListener, ScheduledExecutorService scheduledExecutorService2) {
        this.httpURLConnection = httpURLConnection2;
        this.configFetchHandler = configFetchHandler2;
        this.activatedCache = configCacheClient;
        this.eventListeners = set;
        this.retryCallback = configUpdateListener;
        this.scheduledExecutorService = scheduledExecutorService2;
    }

    private void autoFetch(final int i3, final long j2) {
        if (i3 == 0) {
            propagateErrors(new FirebaseRemoteConfigServerException("Unable to fetch the latest version of the template.", FirebaseRemoteConfigException.Code.CONFIG_UPDATE_NOT_FETCHED));
            return;
        }
        this.scheduledExecutorService.schedule(new Runnable() {
            public void run() {
                ConfigAutoFetch.this.fetchLatestConfig(i3, j2);
            }
        }, (long) this.random.nextInt(4), TimeUnit.SECONDS);
    }

    private synchronized void executeAllListenerCallbacks(ConfigUpdate configUpdate) {
        for (ConfigUpdateListener onUpdate : this.eventListeners) {
            onUpdate.onUpdate(configUpdate);
        }
    }

    private static Boolean fetchResponseIsUpToDate(ConfigFetchHandler.FetchResponse fetchResponse, long j2) {
        boolean z2 = false;
        if (fetchResponse.getFetchedConfigs() != null) {
            if (fetchResponse.getFetchedConfigs().getTemplateVersionNumber() >= j2) {
                z2 = true;
            }
            return Boolean.valueOf(z2);
        }
        if (fetchResponse.getStatus() == 1) {
            z2 = true;
        }
        return Boolean.valueOf(z2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0039, code lost:
        if (r5.has(REALTIME_DISABLED_KEY) == false) goto L_0x0052;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003f, code lost:
        if (r5.getBoolean(REALTIME_DISABLED_KEY) == false) goto L_0x0052;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0041, code lost:
        r9.retryCallback.onError(new com.google.firebase.remoteconfig.FirebaseRemoteConfigServerException("The server is temporarily unavailable. Try again in a few minutes.", com.google.firebase.remoteconfig.FirebaseRemoteConfigException.Code.CONFIG_UPDATE_UNAVAILABLE));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0056, code lost:
        if (isEventListenersEmpty() == false) goto L_0x0059;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005d, code lost:
        if (r5.has(TEMPLATE_VERSION_KEY) == false) goto L_0x0012;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005f, code lost:
        r6 = r9.configFetchHandler.getTemplateVersionNumber();
        r4 = r5.getLong(TEMPLATE_VERSION_KEY);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x006b, code lost:
        if (r4 <= r6) goto L_0x0012;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x006d, code lost:
        autoFetch(3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        r5 = new org.json.JSONObject(r4);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void handleNotifications(java.io.InputStream r10) throws java.io.IOException {
        /*
            r9 = this;
            java.lang.String r0 = "latestTemplateVersionNumber"
            java.lang.String r1 = "featureDisabled"
            java.io.BufferedReader r2 = new java.io.BufferedReader
            java.io.InputStreamReader r3 = new java.io.InputStreamReader
            java.lang.String r4 = "utf-8"
            r3.<init>(r10, r4)
            r2.<init>(r3)
            java.lang.String r3 = ""
        L_0x0012:
            r4 = r3
        L_0x0013:
            java.lang.String r5 = r2.readLine()
            if (r5 == 0) goto L_0x008a
            java.lang.String r4 = android.support.v4.media.session.a.m(r4, r5)
            java.lang.String r6 = "}"
            boolean r5 = r5.contains(r6)
            if (r5 == 0) goto L_0x0013
            java.lang.String r4 = r9.parseAndValidateConfigUpdateMessage(r4)
            boolean r5 = r4.isEmpty()
            if (r5 == 0) goto L_0x0030
            goto L_0x0013
        L_0x0030:
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0050 }
            r5.<init>(r4)     // Catch:{ JSONException -> 0x0050 }
            boolean r4 = r5.has(r1)     // Catch:{ JSONException -> 0x0050 }
            if (r4 == 0) goto L_0x0052
            boolean r4 = r5.getBoolean(r1)     // Catch:{ JSONException -> 0x0050 }
            if (r4 == 0) goto L_0x0052
            com.google.firebase.remoteconfig.ConfigUpdateListener r4 = r9.retryCallback     // Catch:{ JSONException -> 0x0050 }
            com.google.firebase.remoteconfig.FirebaseRemoteConfigServerException r5 = new com.google.firebase.remoteconfig.FirebaseRemoteConfigServerException     // Catch:{ JSONException -> 0x0050 }
            java.lang.String r6 = "The server is temporarily unavailable. Try again in a few minutes."
            com.google.firebase.remoteconfig.FirebaseRemoteConfigException$Code r7 = com.google.firebase.remoteconfig.FirebaseRemoteConfigException.Code.CONFIG_UPDATE_UNAVAILABLE     // Catch:{ JSONException -> 0x0050 }
            r5.<init>((java.lang.String) r6, (com.google.firebase.remoteconfig.FirebaseRemoteConfigException.Code) r7)     // Catch:{ JSONException -> 0x0050 }
            r4.onError(r5)     // Catch:{ JSONException -> 0x0050 }
            goto L_0x008a
        L_0x0050:
            r4 = move-exception
            goto L_0x0072
        L_0x0052:
            boolean r4 = r9.isEventListenersEmpty()     // Catch:{ JSONException -> 0x0050 }
            if (r4 == 0) goto L_0x0059
            goto L_0x008a
        L_0x0059:
            boolean r4 = r5.has(r0)     // Catch:{ JSONException -> 0x0050 }
            if (r4 == 0) goto L_0x0012
            com.google.firebase.remoteconfig.internal.ConfigFetchHandler r4 = r9.configFetchHandler     // Catch:{ JSONException -> 0x0050 }
            long r6 = r4.getTemplateVersionNumber()     // Catch:{ JSONException -> 0x0050 }
            long r4 = r5.getLong(r0)     // Catch:{ JSONException -> 0x0050 }
            int r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r6 <= 0) goto L_0x0012
            r6 = 3
            r9.autoFetch(r6, r4)     // Catch:{ JSONException -> 0x0050 }
            goto L_0x0012
        L_0x0072:
            com.google.firebase.remoteconfig.FirebaseRemoteConfigClientException r5 = new com.google.firebase.remoteconfig.FirebaseRemoteConfigClientException
            java.lang.Throwable r6 = r4.getCause()
            com.google.firebase.remoteconfig.FirebaseRemoteConfigException$Code r7 = com.google.firebase.remoteconfig.FirebaseRemoteConfigException.Code.CONFIG_UPDATE_MESSAGE_INVALID
            java.lang.String r8 = "Unable to parse config update message."
            r5.<init>(r8, r6, r7)
            r9.propagateErrors(r5)
            java.lang.String r5 = "FirebaseRemoteConfig"
            java.lang.String r6 = "Unable to parse latest config update message."
            android.util.Log.e(r5, r6, r4)
            goto L_0x0012
        L_0x008a:
            r2.close()
            r10.close()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.remoteconfig.internal.ConfigAutoFetch.handleNotifications(java.io.InputStream):void");
    }

    private synchronized boolean isEventListenersEmpty() {
        return this.eventListeners.isEmpty();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Task lambda$fetchLatestConfig$0(Task task, Task task2, long j2, int i3, Task task3) throws Exception {
        if (!task.isSuccessful()) {
            return Tasks.forException(new FirebaseRemoteConfigClientException("Failed to auto-fetch config update.", (Throwable) task.getException()));
        }
        if (!task2.isSuccessful()) {
            return Tasks.forException(new FirebaseRemoteConfigClientException("Failed to get activated config for auto-fetch", (Throwable) task2.getException()));
        }
        ConfigFetchHandler.FetchResponse fetchResponse = (ConfigFetchHandler.FetchResponse) task.getResult();
        ConfigContainer configContainer = (ConfigContainer) task2.getResult();
        if (!fetchResponseIsUpToDate(fetchResponse, j2).booleanValue()) {
            Log.d(FirebaseRemoteConfig.TAG, "Fetched template version is the same as SDK's current version. Retrying fetch.");
            autoFetch(i3, j2);
            return Tasks.forResult(null);
        } else if (fetchResponse.getFetchedConfigs() == null) {
            Log.d(FirebaseRemoteConfig.TAG, "The fetch succeeded, but the backend had no updates.");
            return Tasks.forResult(null);
        } else {
            if (configContainer == null) {
                configContainer = ConfigContainer.newBuilder().build();
            }
            Set<String> changedParams = configContainer.getChangedParams(fetchResponse.getFetchedConfigs());
            if (changedParams.isEmpty()) {
                Log.d(FirebaseRemoteConfig.TAG, "Config was fetched, but no params changed.");
                return Tasks.forResult(null);
            }
            executeAllListenerCallbacks(ConfigUpdate.create(changedParams));
            return Tasks.forResult(null);
        }
    }

    private String parseAndValidateConfigUpdateMessage(String str) {
        int indexOf = str.indexOf(123);
        int lastIndexOf = str.lastIndexOf(125);
        return (indexOf < 0 || lastIndexOf < 0 || indexOf >= lastIndexOf) ? "" : str.substring(indexOf, lastIndexOf + 1);
    }

    private synchronized void propagateErrors(FirebaseRemoteConfigException firebaseRemoteConfigException) {
        for (ConfigUpdateListener onError : this.eventListeners) {
            onError.onError(firebaseRemoteConfigException);
        }
    }

    @VisibleForTesting
    public synchronized Task<Void> fetchLatestConfig(int i3, long j2) {
        int i4;
        Task<ConfigFetchHandler.FetchResponse> fetchNowWithTypeAndAttemptNumber;
        Task<ConfigContainer> task;
        i4 = i3 - 1;
        fetchNowWithTypeAndAttemptNumber = this.configFetchHandler.fetchNowWithTypeAndAttemptNumber(ConfigFetchHandler.FetchType.REALTIME, 3 - i4);
        task = this.activatedCache.get();
        return Tasks.whenAllComplete((Task<?>[]) new Task[]{fetchNowWithTypeAndAttemptNumber, task}).continueWithTask(this.scheduledExecutorService, new a(this, fetchNowWithTypeAndAttemptNumber, task, j2, i4));
    }

    @VisibleForTesting
    public void listenForNotifications() {
        HttpURLConnection httpURLConnection2 = this.httpURLConnection;
        if (httpURLConnection2 != null) {
            try {
                InputStream inputStream = httpURLConnection2.getInputStream();
                handleNotifications(inputStream);
                inputStream.close();
            } catch (IOException e3) {
                Log.d(FirebaseRemoteConfig.TAG, "Stream was cancelled due to an exception. Retrying the connection...", e3);
            } catch (Throwable th) {
                this.httpURLConnection.disconnect();
                throw th;
            }
            this.httpURLConnection.disconnect();
        }
    }
}
