package com.google.firebase.sessions;

import androidx.compose.animation.core.a;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\bHÆ\u0003J\t\u0010\u001a\u001a\u00020\nHÆ\u0003JE\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\t\u0010!\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000f¨\u0006\""}, d2 = {"Lcom/google/firebase/sessions/ApplicationInfo;", "", "appId", "", "deviceModel", "sessionSdkVersion", "osVersion", "logEnvironment", "Lcom/google/firebase/sessions/LogEnvironment;", "androidAppInfo", "Lcom/google/firebase/sessions/AndroidApplicationInfo;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/google/firebase/sessions/LogEnvironment;Lcom/google/firebase/sessions/AndroidApplicationInfo;)V", "getAndroidAppInfo", "()Lcom/google/firebase/sessions/AndroidApplicationInfo;", "getAppId", "()Ljava/lang/String;", "getDeviceModel", "getLogEnvironment", "()Lcom/google/firebase/sessions/LogEnvironment;", "getOsVersion", "getSessionSdkVersion", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "com.google.firebase-firebase-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ApplicationInfo {
    @NotNull
    private final AndroidApplicationInfo androidAppInfo;
    @NotNull
    private final String appId;
    @NotNull
    private final String deviceModel;
    @NotNull
    private final LogEnvironment logEnvironment;
    @NotNull
    private final String osVersion;
    @NotNull
    private final String sessionSdkVersion;

    public ApplicationInfo(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull LogEnvironment logEnvironment2, @NotNull AndroidApplicationInfo androidApplicationInfo) {
        Intrinsics.checkNotNullParameter(str, RemoteConfigConstants.RequestFieldKey.APP_ID);
        Intrinsics.checkNotNullParameter(str2, "deviceModel");
        Intrinsics.checkNotNullParameter(str3, "sessionSdkVersion");
        Intrinsics.checkNotNullParameter(str4, "osVersion");
        Intrinsics.checkNotNullParameter(logEnvironment2, "logEnvironment");
        Intrinsics.checkNotNullParameter(androidApplicationInfo, "androidAppInfo");
        this.appId = str;
        this.deviceModel = str2;
        this.sessionSdkVersion = str3;
        this.osVersion = str4;
        this.logEnvironment = logEnvironment2;
        this.androidAppInfo = androidApplicationInfo;
    }

    public static /* synthetic */ ApplicationInfo copy$default(ApplicationInfo applicationInfo, String str, String str2, String str3, String str4, LogEnvironment logEnvironment2, AndroidApplicationInfo androidApplicationInfo, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = applicationInfo.appId;
        }
        if ((i3 & 2) != 0) {
            str2 = applicationInfo.deviceModel;
        }
        String str5 = str2;
        if ((i3 & 4) != 0) {
            str3 = applicationInfo.sessionSdkVersion;
        }
        String str6 = str3;
        if ((i3 & 8) != 0) {
            str4 = applicationInfo.osVersion;
        }
        String str7 = str4;
        if ((i3 & 16) != 0) {
            logEnvironment2 = applicationInfo.logEnvironment;
        }
        LogEnvironment logEnvironment3 = logEnvironment2;
        if ((i3 & 32) != 0) {
            androidApplicationInfo = applicationInfo.androidAppInfo;
        }
        return applicationInfo.copy(str, str5, str6, str7, logEnvironment3, androidApplicationInfo);
    }

    @NotNull
    public final String component1() {
        return this.appId;
    }

    @NotNull
    public final String component2() {
        return this.deviceModel;
    }

    @NotNull
    public final String component3() {
        return this.sessionSdkVersion;
    }

    @NotNull
    public final String component4() {
        return this.osVersion;
    }

    @NotNull
    public final LogEnvironment component5() {
        return this.logEnvironment;
    }

    @NotNull
    public final AndroidApplicationInfo component6() {
        return this.androidAppInfo;
    }

    @NotNull
    public final ApplicationInfo copy(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull LogEnvironment logEnvironment2, @NotNull AndroidApplicationInfo androidApplicationInfo) {
        Intrinsics.checkNotNullParameter(str, RemoteConfigConstants.RequestFieldKey.APP_ID);
        Intrinsics.checkNotNullParameter(str2, "deviceModel");
        Intrinsics.checkNotNullParameter(str3, "sessionSdkVersion");
        Intrinsics.checkNotNullParameter(str4, "osVersion");
        Intrinsics.checkNotNullParameter(logEnvironment2, "logEnvironment");
        Intrinsics.checkNotNullParameter(androidApplicationInfo, "androidAppInfo");
        return new ApplicationInfo(str, str2, str3, str4, logEnvironment2, androidApplicationInfo);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ApplicationInfo)) {
            return false;
        }
        ApplicationInfo applicationInfo = (ApplicationInfo) obj;
        return Intrinsics.areEqual((Object) this.appId, (Object) applicationInfo.appId) && Intrinsics.areEqual((Object) this.deviceModel, (Object) applicationInfo.deviceModel) && Intrinsics.areEqual((Object) this.sessionSdkVersion, (Object) applicationInfo.sessionSdkVersion) && Intrinsics.areEqual((Object) this.osVersion, (Object) applicationInfo.osVersion) && this.logEnvironment == applicationInfo.logEnvironment && Intrinsics.areEqual((Object) this.androidAppInfo, (Object) applicationInfo.androidAppInfo);
    }

    @NotNull
    public final AndroidApplicationInfo getAndroidAppInfo() {
        return this.androidAppInfo;
    }

    @NotNull
    public final String getAppId() {
        return this.appId;
    }

    @NotNull
    public final String getDeviceModel() {
        return this.deviceModel;
    }

    @NotNull
    public final LogEnvironment getLogEnvironment() {
        return this.logEnvironment;
    }

    @NotNull
    public final String getOsVersion() {
        return this.osVersion;
    }

    @NotNull
    public final String getSessionSdkVersion() {
        return this.sessionSdkVersion;
    }

    public int hashCode() {
        int i3 = a.i(this.osVersion, a.i(this.sessionSdkVersion, a.i(this.deviceModel, this.appId.hashCode() * 31, 31), 31), 31);
        return this.androidAppInfo.hashCode() + ((this.logEnvironment.hashCode() + i3) * 31);
    }

    @NotNull
    public String toString() {
        return "ApplicationInfo(appId=" + this.appId + ", deviceModel=" + this.deviceModel + ", sessionSdkVersion=" + this.sessionSdkVersion + ", osVersion=" + this.osVersion + ", logEnvironment=" + this.logEnvironment + ", androidAppInfo=" + this.androidAppInfo + ')';
    }
}
