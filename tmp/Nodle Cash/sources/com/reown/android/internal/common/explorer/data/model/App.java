package com.reown.android.internal.common.explorer.data.model;

import A.a;
import androidx.camera.camera2.internal.C0118y;
import androidx.constraintlayout.core.state.b;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b%\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001Bu\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u000e\u0010\u000fJ\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0001\u0010'\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010+\u001a\u00020,HÖ\u0001J\t\u0010-\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0011R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0011R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0011¨\u0006."}, d2 = {"Lcom/reown/android/internal/common/explorer/data/model/App;", "", "browser", "", "ios", "android", "mac", "windows", "linux", "chrome", "firefox", "safari", "edge", "opera", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBrowser", "()Ljava/lang/String;", "getIos", "getAndroid", "getMac", "getWindows", "getLinux", "getChrome", "getFirefox", "getSafari", "getEdge", "getOpera", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "equals", "", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class App {
    @Nullable

    /* renamed from: android  reason: collision with root package name */
    private final String f7322android;
    @Nullable
    private final String browser;
    @Nullable
    private final String chrome;
    @Nullable
    private final String edge;
    @Nullable
    private final String firefox;
    @Nullable
    private final String ios;
    @Nullable
    private final String linux;
    @Nullable
    private final String mac;
    @Nullable
    private final String opera;
    @Nullable
    private final String safari;
    @Nullable
    private final String windows;

    public App(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable String str9, @Nullable String str10, @Nullable String str11) {
        this.browser = str;
        this.ios = str2;
        this.f7322android = str3;
        this.mac = str4;
        this.windows = str5;
        this.linux = str6;
        this.chrome = str7;
        this.firefox = str8;
        this.safari = str9;
        this.edge = str10;
        this.opera = str11;
    }

    public static /* synthetic */ App copy$default(App app2, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, int i3, Object obj) {
        App app3 = app2;
        int i4 = i3;
        return app2.copy((i4 & 1) != 0 ? app3.browser : str, (i4 & 2) != 0 ? app3.ios : str2, (i4 & 4) != 0 ? app3.f7322android : str3, (i4 & 8) != 0 ? app3.mac : str4, (i4 & 16) != 0 ? app3.windows : str5, (i4 & 32) != 0 ? app3.linux : str6, (i4 & 64) != 0 ? app3.chrome : str7, (i4 & 128) != 0 ? app3.firefox : str8, (i4 & 256) != 0 ? app3.safari : str9, (i4 & 512) != 0 ? app3.edge : str10, (i4 & 1024) != 0 ? app3.opera : str11);
    }

    @Nullable
    public final String component1() {
        return this.browser;
    }

    @Nullable
    public final String component10() {
        return this.edge;
    }

    @Nullable
    public final String component11() {
        return this.opera;
    }

    @Nullable
    public final String component2() {
        return this.ios;
    }

    @Nullable
    public final String component3() {
        return this.f7322android;
    }

    @Nullable
    public final String component4() {
        return this.mac;
    }

    @Nullable
    public final String component5() {
        return this.windows;
    }

    @Nullable
    public final String component6() {
        return this.linux;
    }

    @Nullable
    public final String component7() {
        return this.chrome;
    }

    @Nullable
    public final String component8() {
        return this.firefox;
    }

    @Nullable
    public final String component9() {
        return this.safari;
    }

    @NotNull
    public final App copy(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable String str9, @Nullable String str10, @Nullable String str11) {
        return new App(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof App)) {
            return false;
        }
        App app2 = (App) obj;
        return Intrinsics.areEqual((Object) this.browser, (Object) app2.browser) && Intrinsics.areEqual((Object) this.ios, (Object) app2.ios) && Intrinsics.areEqual((Object) this.f7322android, (Object) app2.f7322android) && Intrinsics.areEqual((Object) this.mac, (Object) app2.mac) && Intrinsics.areEqual((Object) this.windows, (Object) app2.windows) && Intrinsics.areEqual((Object) this.linux, (Object) app2.linux) && Intrinsics.areEqual((Object) this.chrome, (Object) app2.chrome) && Intrinsics.areEqual((Object) this.firefox, (Object) app2.firefox) && Intrinsics.areEqual((Object) this.safari, (Object) app2.safari) && Intrinsics.areEqual((Object) this.edge, (Object) app2.edge) && Intrinsics.areEqual((Object) this.opera, (Object) app2.opera);
    }

    @Nullable
    public final String getAndroid() {
        return this.f7322android;
    }

    @Nullable
    public final String getBrowser() {
        return this.browser;
    }

    @Nullable
    public final String getChrome() {
        return this.chrome;
    }

    @Nullable
    public final String getEdge() {
        return this.edge;
    }

    @Nullable
    public final String getFirefox() {
        return this.firefox;
    }

    @Nullable
    public final String getIos() {
        return this.ios;
    }

    @Nullable
    public final String getLinux() {
        return this.linux;
    }

    @Nullable
    public final String getMac() {
        return this.mac;
    }

    @Nullable
    public final String getOpera() {
        return this.opera;
    }

    @Nullable
    public final String getSafari() {
        return this.safari;
    }

    @Nullable
    public final String getWindows() {
        return this.windows;
    }

    public int hashCode() {
        String str = this.browser;
        int i3 = 0;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.ios;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.f7322android;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.mac;
        int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.windows;
        int hashCode5 = (hashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.linux;
        int hashCode6 = (hashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.chrome;
        int hashCode7 = (hashCode6 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.firefox;
        int hashCode8 = (hashCode7 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.safari;
        int hashCode9 = (hashCode8 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.edge;
        int hashCode10 = (hashCode9 + (str10 == null ? 0 : str10.hashCode())) * 31;
        String str11 = this.opera;
        if (str11 != null) {
            i3 = str11.hashCode();
        }
        return hashCode10 + i3;
    }

    @NotNull
    public String toString() {
        String str = this.browser;
        String str2 = this.ios;
        String str3 = this.f7322android;
        String str4 = this.mac;
        String str5 = this.windows;
        String str6 = this.linux;
        String str7 = this.chrome;
        String str8 = this.firefox;
        String str9 = this.safari;
        String str10 = this.edge;
        String str11 = this.opera;
        StringBuilder l2 = C0118y.l("App(browser=", str, ", ios=", str2, ", android=");
        b.w(l2, str3, ", mac=", str4, ", windows=");
        b.w(l2, str5, ", linux=", str6, ", chrome=");
        b.w(l2, str7, ", firefox=", str8, ", safari=");
        b.w(l2, str9, ", edge=", str10, ", opera=");
        return a.n(l2, str11, ")");
    }
}
