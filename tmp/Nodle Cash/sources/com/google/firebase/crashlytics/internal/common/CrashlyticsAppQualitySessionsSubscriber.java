package com.google.firebase.crashlytics.internal.common;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.persistence.FileStore;
import com.google.firebase.sessions.api.SessionSubscriber;

public class CrashlyticsAppQualitySessionsSubscriber implements SessionSubscriber {
    private final CrashlyticsAppQualitySessionsStore appQualitySessionsStore;
    private final DataCollectionArbiter dataCollectionArbiter;

    public CrashlyticsAppQualitySessionsSubscriber(DataCollectionArbiter dataCollectionArbiter2, FileStore fileStore) {
        this.dataCollectionArbiter = dataCollectionArbiter2;
        this.appQualitySessionsStore = new CrashlyticsAppQualitySessionsStore(fileStore);
    }

    @Nullable
    public String getAppQualitySessionId(@NonNull String str) {
        return this.appQualitySessionsStore.getAppQualitySessionId(str);
    }

    @NonNull
    public SessionSubscriber.Name getSessionSubscriberName() {
        return SessionSubscriber.Name.CRASHLYTICS;
    }

    public boolean isDataCollectionEnabled() {
        return this.dataCollectionArbiter.isAutomaticDataCollectionEnabled();
    }

    public void onSessionChanged(@NonNull SessionSubscriber.SessionDetails sessionDetails) {
        Logger logger = Logger.getLogger();
        logger.d("App Quality Sessions session changed: " + sessionDetails);
        this.appQualitySessionsStore.rotateAppQualitySessionId(sessionDetails.getSessionId());
    }

    public void setSessionId(@Nullable String str) {
        this.appQualitySessionsStore.rotateSessionId(str);
    }
}
