package com.google.firebase.crashlytics.internal;

import A1.C0219a;
import androidx.annotation.NonNull;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.c;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.crashlytics.internal.model.StaticSessionData;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import java.io.File;
import java.util.concurrent.atomic.AtomicReference;

public final class CrashlyticsNativeComponentDeferredProxy implements CrashlyticsNativeComponent {
    private static final NativeSessionFileProvider MISSING_NATIVE_SESSION_FILE_PROVIDER = new MissingNativeSessionFileProvider();
    private final AtomicReference<CrashlyticsNativeComponent> availableNativeComponent = new AtomicReference<>((Object) null);
    private final Deferred<CrashlyticsNativeComponent> deferredNativeComponent;

    public static final class MissingNativeSessionFileProvider implements NativeSessionFileProvider {
        private MissingNativeSessionFileProvider() {
        }

        public File getAppFile() {
            return null;
        }

        public CrashlyticsReport.ApplicationExitInfo getApplicationExitInto() {
            return null;
        }

        public File getBinaryImagesFile() {
            return null;
        }

        public File getDeviceFile() {
            return null;
        }

        public File getMetadataFile() {
            return null;
        }

        public File getMinidumpFile() {
            return null;
        }

        public File getOsFile() {
            return null;
        }

        public File getSessionFile() {
            return null;
        }
    }

    public CrashlyticsNativeComponentDeferredProxy(Deferred<CrashlyticsNativeComponent> deferred) {
        this.deferredNativeComponent = deferred;
        deferred.whenAvailable(new C0219a(this, 22));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(Provider provider) {
        Logger.getLogger().d("Crashlytics native component now available.");
        this.availableNativeComponent.set((CrashlyticsNativeComponent) provider.get());
    }

    @NonNull
    public NativeSessionFileProvider getSessionFileProvider(@NonNull String str) {
        CrashlyticsNativeComponent crashlyticsNativeComponent = this.availableNativeComponent.get();
        return crashlyticsNativeComponent == null ? MISSING_NATIVE_SESSION_FILE_PROVIDER : crashlyticsNativeComponent.getSessionFileProvider(str);
    }

    public boolean hasCrashDataForCurrentSession() {
        CrashlyticsNativeComponent crashlyticsNativeComponent = this.availableNativeComponent.get();
        return crashlyticsNativeComponent != null && crashlyticsNativeComponent.hasCrashDataForCurrentSession();
    }

    public boolean hasCrashDataForSession(@NonNull String str) {
        CrashlyticsNativeComponent crashlyticsNativeComponent = this.availableNativeComponent.get();
        return crashlyticsNativeComponent != null && crashlyticsNativeComponent.hasCrashDataForSession(str);
    }

    public void prepareNativeSession(@NonNull String str, @NonNull String str2, long j2, @NonNull StaticSessionData staticSessionData) {
        Logger logger = Logger.getLogger();
        logger.v("Deferring native open session: " + str);
        this.deferredNativeComponent.whenAvailable(new c(str, str2, j2, staticSessionData));
    }
}
