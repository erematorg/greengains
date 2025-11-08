package com.google.firebase.sessions.settings;

import androidx.annotation.VisibleForTesting;
import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.core.Preferences;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.sessions.ApplicationInfo;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Regex;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0000\u0018\u0000 +2\u00020\u0001:\u0001+B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0002\u0010\rJ\r\u0010\"\u001a\u00020#H\u0001¢\u0006\u0002\b$J\b\u0010%\u001a\u00020\u0015H\u0016J\u0010\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020'H\u0002J\u0011\u0010)\u001a\u00020#H@ø\u0001\u0000¢\u0006\u0002\u0010*R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\u0004\u0018\u00010\u00118VX\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0014\u001a\u0004\u0018\u00010\u00158VX\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u001f\u0010\u0018\u001a\u0004\u0018\u00010\u00198VX\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u001b\u0010\u001c\u001a\u00020\u001d8BX\u0002¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\u001e\u0010\u001f\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006,"}, d2 = {"Lcom/google/firebase/sessions/settings/RemoteSettings;", "Lcom/google/firebase/sessions/settings/SettingsProvider;", "backgroundDispatcher", "Lkotlin/coroutines/CoroutineContext;", "firebaseInstallationsApi", "Lcom/google/firebase/installations/FirebaseInstallationsApi;", "appInfo", "Lcom/google/firebase/sessions/ApplicationInfo;", "configsFetcher", "Lcom/google/firebase/sessions/settings/CrashlyticsSettingsFetcher;", "dataStore", "Landroidx/datastore/core/DataStore;", "Landroidx/datastore/preferences/core/Preferences;", "(Lkotlin/coroutines/CoroutineContext;Lcom/google/firebase/installations/FirebaseInstallationsApi;Lcom/google/firebase/sessions/ApplicationInfo;Lcom/google/firebase/sessions/settings/CrashlyticsSettingsFetcher;Landroidx/datastore/core/DataStore;)V", "fetchInProgress", "Lkotlinx/coroutines/sync/Mutex;", "samplingRate", "", "getSamplingRate", "()Ljava/lang/Double;", "sessionEnabled", "", "getSessionEnabled", "()Ljava/lang/Boolean;", "sessionRestartTimeout", "Lkotlin/time/Duration;", "getSessionRestartTimeout-FghU774", "()Lkotlin/time/Duration;", "settingsCache", "Lcom/google/firebase/sessions/settings/SettingsCache;", "getSettingsCache", "()Lcom/google/firebase/sessions/settings/SettingsCache;", "settingsCache$delegate", "Lkotlin/Lazy;", "clearCachedSettings", "", "clearCachedSettings$com_google_firebase_firebase_sessions", "isSettingsStale", "removeForwardSlashesIn", "", "s", "updateSettings", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "com.google.firebase-firebase-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nRemoteSettings.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RemoteSettings.kt\ncom/google/firebase/sessions/settings/RemoteSettings\n+ 2 Mutex.kt\nkotlinx/coroutines/sync/MutexKt\n*L\n1#1,164:1\n107#2,10:165\n*S KotlinDebug\n*F\n+ 1 RemoteSettings.kt\ncom/google/firebase/sessions/settings/RemoteSettings\n*L\n68#1:165,10\n*E\n"})
public final class RemoteSettings implements SettingsProvider {
    @NotNull
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @NotNull
    @Deprecated
    public static final String FORWARD_SLASH_STRING = "/";
    @NotNull
    @Deprecated
    public static final String TAG = "SessionConfigFetcher";
    @NotNull
    private final ApplicationInfo appInfo;
    @NotNull
    private final CoroutineContext backgroundDispatcher;
    @NotNull
    private final CrashlyticsSettingsFetcher configsFetcher;
    @NotNull
    private final Mutex fetchInProgress = MutexKt.Mutex$default(false, 1, (Object) null);
    @NotNull
    private final FirebaseInstallationsApi firebaseInstallationsApi;
    @NotNull
    private final Lazy settingsCache$delegate;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/google/firebase/sessions/settings/RemoteSettings$Companion;", "", "()V", "FORWARD_SLASH_STRING", "", "TAG", "com.google.firebase-firebase-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public RemoteSettings(@NotNull CoroutineContext coroutineContext, @NotNull FirebaseInstallationsApi firebaseInstallationsApi2, @NotNull ApplicationInfo applicationInfo, @NotNull CrashlyticsSettingsFetcher crashlyticsSettingsFetcher, @NotNull DataStore<Preferences> dataStore) {
        Intrinsics.checkNotNullParameter(coroutineContext, "backgroundDispatcher");
        Intrinsics.checkNotNullParameter(firebaseInstallationsApi2, "firebaseInstallationsApi");
        Intrinsics.checkNotNullParameter(applicationInfo, "appInfo");
        Intrinsics.checkNotNullParameter(crashlyticsSettingsFetcher, "configsFetcher");
        Intrinsics.checkNotNullParameter(dataStore, "dataStore");
        this.backgroundDispatcher = coroutineContext;
        this.firebaseInstallationsApi = firebaseInstallationsApi2;
        this.appInfo = applicationInfo;
        this.configsFetcher = crashlyticsSettingsFetcher;
        this.settingsCache$delegate = LazyKt.lazy(new RemoteSettings$settingsCache$2(dataStore));
    }

    /* access modifiers changed from: private */
    public final SettingsCache getSettingsCache() {
        return (SettingsCache) this.settingsCache$delegate.getValue();
    }

    private final String removeForwardSlashesIn(String str) {
        return new Regex("/").replace((CharSequence) str, "");
    }

    @VisibleForTesting
    public final void clearCachedSettings$com_google_firebase_firebase_sessions() {
        Job unused = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(this.backgroundDispatcher), (CoroutineContext) null, (CoroutineStart) null, new RemoteSettings$clearCachedSettings$1(this, (Continuation<? super RemoteSettings$clearCachedSettings$1>) null), 3, (Object) null);
    }

    @Nullable
    public Double getSamplingRate() {
        return getSettingsCache().sessionSamplingRate();
    }

    @Nullable
    public Boolean getSessionEnabled() {
        return getSettingsCache().sessionsEnabled();
    }

    @Nullable
    /* renamed from: getSessionRestartTimeout-FghU774  reason: not valid java name */
    public Duration m8236getSessionRestartTimeoutFghU774() {
        Integer sessionRestartTimeout = getSettingsCache().sessionRestartTimeout();
        if (sessionRestartTimeout == null) {
            return null;
        }
        Duration.Companion companion = Duration.Companion;
        return Duration.m10312boximpl(DurationKt.toDuration(sessionRestartTimeout.intValue(), DurationUnit.SECONDS));
    }

    public boolean isSettingsStale() {
        return getSettingsCache().hasCacheExpired$com_google_firebase_firebase_sessions();
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x008e A[Catch:{ all -> 0x0099 }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x009f A[SYNTHETIC, Splitter:B:38:0x009f] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00c2 A[Catch:{ all -> 0x0037 }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00cd  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object updateSettings(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r13) {
        /*
            r12 = this;
            boolean r0 = r13 instanceof com.google.firebase.sessions.settings.RemoteSettings$updateSettings$1
            if (r0 == 0) goto L_0x0013
            r0 = r13
            com.google.firebase.sessions.settings.RemoteSettings$updateSettings$1 r0 = (com.google.firebase.sessions.settings.RemoteSettings$updateSettings$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.google.firebase.sessions.settings.RemoteSettings$updateSettings$1 r0 = new com.google.firebase.sessions.settings.RemoteSettings$updateSettings$1
            r0.<init>(r12, r13)
        L_0x0018:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 1
            java.lang.String r5 = "SessionConfigFetcher"
            r6 = 2
            r7 = 0
            if (r2 == 0) goto L_0x005d
            if (r2 == r4) goto L_0x004f
            if (r2 == r6) goto L_0x0042
            if (r2 != r3) goto L_0x003a
            java.lang.Object r12 = r0.L$0
            kotlinx.coroutines.sync.Mutex r12 = (kotlinx.coroutines.sync.Mutex) r12
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ all -> 0x0037 }
            goto L_0x014a
        L_0x0037:
            r13 = move-exception
            goto L_0x0152
        L_0x003a:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L_0x0042:
            java.lang.Object r12 = r0.L$1
            kotlinx.coroutines.sync.Mutex r12 = (kotlinx.coroutines.sync.Mutex) r12
            java.lang.Object r2 = r0.L$0
            com.google.firebase.sessions.settings.RemoteSettings r2 = (com.google.firebase.sessions.settings.RemoteSettings) r2
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ all -> 0x0037 }
            goto L_0x00b4
        L_0x004f:
            java.lang.Object r12 = r0.L$1
            kotlinx.coroutines.sync.Mutex r12 = (kotlinx.coroutines.sync.Mutex) r12
            java.lang.Object r2 = r0.L$0
            com.google.firebase.sessions.settings.RemoteSettings r2 = (com.google.firebase.sessions.settings.RemoteSettings) r2
            kotlin.ResultKt.throwOnFailure(r13)
            r13 = r12
            r12 = r2
            goto L_0x0084
        L_0x005d:
            kotlin.ResultKt.throwOnFailure(r13)
            kotlinx.coroutines.sync.Mutex r13 = r12.fetchInProgress
            boolean r13 = r13.isLocked()
            if (r13 != 0) goto L_0x0075
            com.google.firebase.sessions.settings.SettingsCache r13 = r12.getSettingsCache()
            boolean r13 = r13.hasCacheExpired$com_google_firebase_firebase_sessions()
            if (r13 != 0) goto L_0x0075
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        L_0x0075:
            kotlinx.coroutines.sync.Mutex r13 = r12.fetchInProgress
            r0.L$0 = r12
            r0.L$1 = r13
            r0.label = r4
            java.lang.Object r2 = r13.lock(r7, r0)
            if (r2 != r1) goto L_0x0084
            return r1
        L_0x0084:
            com.google.firebase.sessions.settings.SettingsCache r2 = r12.getSettingsCache()     // Catch:{ all -> 0x0099 }
            boolean r2 = r2.hasCacheExpired$com_google_firebase_firebase_sessions()     // Catch:{ all -> 0x0099 }
            if (r2 != 0) goto L_0x009f
            java.lang.String r12 = "Remote settings cache not expired. Using cached values."
            android.util.Log.d(r5, r12)     // Catch:{ all -> 0x0099 }
            kotlin.Unit r12 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0099 }
            r13.unlock(r7)
            return r12
        L_0x0099:
            r12 = move-exception
            r11 = r13
            r13 = r12
            r12 = r11
            goto L_0x0152
        L_0x009f:
            com.google.firebase.sessions.InstallationId$Companion r2 = com.google.firebase.sessions.InstallationId.Companion     // Catch:{ all -> 0x0099 }
            com.google.firebase.installations.FirebaseInstallationsApi r4 = r12.firebaseInstallationsApi     // Catch:{ all -> 0x0099 }
            r0.L$0 = r12     // Catch:{ all -> 0x0099 }
            r0.L$1 = r13     // Catch:{ all -> 0x0099 }
            r0.label = r6     // Catch:{ all -> 0x0099 }
            java.lang.Object r2 = r2.create(r4, r0)     // Catch:{ all -> 0x0099 }
            if (r2 != r1) goto L_0x00b0
            return r1
        L_0x00b0:
            r11 = r2
            r2 = r12
            r12 = r13
            r13 = r11
        L_0x00b4:
            com.google.firebase.sessions.InstallationId r13 = (com.google.firebase.sessions.InstallationId) r13     // Catch:{ all -> 0x0037 }
            java.lang.String r13 = r13.getFid()     // Catch:{ all -> 0x0037 }
            java.lang.String r4 = ""
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r13, (java.lang.Object) r4)     // Catch:{ all -> 0x0037 }
            if (r4 == 0) goto L_0x00cd
            java.lang.String r13 = "Error getting Firebase Installation ID. Skipping this Session Event."
            android.util.Log.w(r5, r13)     // Catch:{ all -> 0x0037 }
            kotlin.Unit r13 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0037 }
            r12.unlock(r7)
            return r13
        L_0x00cd:
            java.lang.String r4 = "X-Crashlytics-Installation-ID"
            kotlin.Pair r13 = kotlin.TuplesKt.to(r4, r13)     // Catch:{ all -> 0x0037 }
            java.lang.String r4 = "X-Crashlytics-Device-Model"
            kotlin.jvm.internal.StringCompanionObject r8 = kotlin.jvm.internal.StringCompanionObject.INSTANCE     // Catch:{ all -> 0x0037 }
            java.lang.String r8 = "%s/%s"
            java.lang.String r9 = android.os.Build.MANUFACTURER     // Catch:{ all -> 0x0037 }
            java.lang.String r10 = android.os.Build.MODEL     // Catch:{ all -> 0x0037 }
            java.lang.Object[] r9 = new java.lang.Object[]{r9, r10}     // Catch:{ all -> 0x0037 }
            java.lang.Object[] r6 = java.util.Arrays.copyOf(r9, r6)     // Catch:{ all -> 0x0037 }
            java.lang.String r6 = java.lang.String.format(r8, r6)     // Catch:{ all -> 0x0037 }
            java.lang.String r8 = "format(format, *args)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r8)     // Catch:{ all -> 0x0037 }
            java.lang.String r6 = r2.removeForwardSlashesIn(r6)     // Catch:{ all -> 0x0037 }
            kotlin.Pair r4 = kotlin.TuplesKt.to(r4, r6)     // Catch:{ all -> 0x0037 }
            java.lang.String r6 = "X-Crashlytics-OS-Build-Version"
            java.lang.String r8 = android.os.Build.VERSION.INCREMENTAL     // Catch:{ all -> 0x0037 }
            java.lang.String r9 = "INCREMENTAL"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r9)     // Catch:{ all -> 0x0037 }
            java.lang.String r8 = r2.removeForwardSlashesIn(r8)     // Catch:{ all -> 0x0037 }
            kotlin.Pair r6 = kotlin.TuplesKt.to(r6, r8)     // Catch:{ all -> 0x0037 }
            java.lang.String r8 = "X-Crashlytics-OS-Display-Version"
            java.lang.String r9 = android.os.Build.VERSION.RELEASE     // Catch:{ all -> 0x0037 }
            java.lang.String r10 = "RELEASE"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r10)     // Catch:{ all -> 0x0037 }
            java.lang.String r9 = r2.removeForwardSlashesIn(r9)     // Catch:{ all -> 0x0037 }
            kotlin.Pair r8 = kotlin.TuplesKt.to(r8, r9)     // Catch:{ all -> 0x0037 }
            java.lang.String r9 = "X-Crashlytics-API-Client-Version"
            com.google.firebase.sessions.ApplicationInfo r10 = r2.appInfo     // Catch:{ all -> 0x0037 }
            java.lang.String r10 = r10.getSessionSdkVersion()     // Catch:{ all -> 0x0037 }
            kotlin.Pair r9 = kotlin.TuplesKt.to(r9, r10)     // Catch:{ all -> 0x0037 }
            kotlin.Pair[] r13 = new kotlin.Pair[]{r13, r4, r6, r8, r9}     // Catch:{ all -> 0x0037 }
            java.util.Map r13 = kotlin.collections.MapsKt.mapOf(r13)     // Catch:{ all -> 0x0037 }
            java.lang.String r4 = "Fetching settings from server."
            android.util.Log.d(r5, r4)     // Catch:{ all -> 0x0037 }
            com.google.firebase.sessions.settings.CrashlyticsSettingsFetcher r4 = r2.configsFetcher     // Catch:{ all -> 0x0037 }
            com.google.firebase.sessions.settings.RemoteSettings$updateSettings$2$1 r5 = new com.google.firebase.sessions.settings.RemoteSettings$updateSettings$2$1     // Catch:{ all -> 0x0037 }
            r5.<init>(r2, r7)     // Catch:{ all -> 0x0037 }
            com.google.firebase.sessions.settings.RemoteSettings$updateSettings$2$2 r2 = new com.google.firebase.sessions.settings.RemoteSettings$updateSettings$2$2     // Catch:{ all -> 0x0037 }
            r2.<init>(r7)     // Catch:{ all -> 0x0037 }
            r0.L$0 = r12     // Catch:{ all -> 0x0037 }
            r0.L$1 = r7     // Catch:{ all -> 0x0037 }
            r0.label = r3     // Catch:{ all -> 0x0037 }
            java.lang.Object r13 = r4.doConfigFetch(r13, r5, r2, r0)     // Catch:{ all -> 0x0037 }
            if (r13 != r1) goto L_0x014a
            return r1
        L_0x014a:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0037 }
            r12.unlock(r7)
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        L_0x0152:
            r12.unlock(r7)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.sessions.settings.RemoteSettings.updateSettings(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
