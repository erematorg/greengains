package com.google.firebase.remoteconfig.internal;

import android.content.Context;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import com.google.firebase.FirebaseApp;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.remoteconfig.ConfigUpdateListener;
import com.google.firebase.remoteconfig.ConfigUpdateListenerRegistration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;

public class ConfigRealtimeHandler {
    private final ConfigCacheClient activatedCacheClient;
    private final ConfigFetchHandler configFetchHandler;
    @GuardedBy("this")
    private final ConfigRealtimeHttpClient configRealtimeHttpClient;
    private final Context context;
    private final FirebaseApp firebaseApp;
    private final FirebaseInstallationsApi firebaseInstallations;
    @GuardedBy("this")
    private final Set<ConfigUpdateListener> listeners;
    private final ConfigMetadataClient metadataClient;
    private final String namespace;
    private final ScheduledExecutorService scheduledExecutorService;

    public class ConfigUpdateListenerRegistrationInternal implements ConfigUpdateListenerRegistration {
        private final ConfigUpdateListener listener;

        public ConfigUpdateListenerRegistrationInternal(ConfigUpdateListener configUpdateListener) {
            this.listener = configUpdateListener;
        }

        public void remove() {
            ConfigRealtimeHandler.this.removeRealtimeConfigUpdateListener(this.listener);
        }
    }

    public ConfigRealtimeHandler(FirebaseApp firebaseApp2, FirebaseInstallationsApi firebaseInstallationsApi, ConfigFetchHandler configFetchHandler2, ConfigCacheClient configCacheClient, Context context2, String str, ConfigMetadataClient configMetadataClient, ScheduledExecutorService scheduledExecutorService2) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        this.listeners = linkedHashSet;
        this.configRealtimeHttpClient = new ConfigRealtimeHttpClient(firebaseApp2, firebaseInstallationsApi, configFetchHandler2, configCacheClient, context2, str, linkedHashSet, configMetadataClient, scheduledExecutorService2);
        this.firebaseApp = firebaseApp2;
        this.configFetchHandler = configFetchHandler2;
        this.firebaseInstallations = firebaseInstallationsApi;
        this.activatedCacheClient = configCacheClient;
        this.context = context2;
        this.namespace = str;
        this.metadataClient = configMetadataClient;
        this.scheduledExecutorService = scheduledExecutorService2;
    }

    private synchronized void beginRealtime() {
        if (!this.listeners.isEmpty()) {
            this.configRealtimeHttpClient.startHttpConnection();
        }
    }

    /* access modifiers changed from: private */
    public synchronized void removeRealtimeConfigUpdateListener(ConfigUpdateListener configUpdateListener) {
        this.listeners.remove(configUpdateListener);
    }

    @NonNull
    public synchronized ConfigUpdateListenerRegistration addRealtimeConfigUpdateListener(@NonNull ConfigUpdateListener configUpdateListener) {
        this.listeners.add(configUpdateListener);
        beginRealtime();
        return new ConfigUpdateListenerRegistrationInternal(configUpdateListener);
    }

    public synchronized void setBackgroundState(boolean z2) {
        this.configRealtimeHttpClient.setRealtimeBackgroundState(z2);
        if (!z2) {
            beginRealtime();
        }
    }
}
