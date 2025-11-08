package com.reown.android.pulse.model;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/reown/android/pulse/model/ConnectionMethod;", "", "<init>", "()V", "QR_CODE", "", "MOBILE", "WEB", "UNDEFINED", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ConnectionMethod {
    @NotNull
    public static final ConnectionMethod INSTANCE = new ConnectionMethod();
    @NotNull
    public static final String MOBILE = "mobile";
    @NotNull
    public static final String QR_CODE = "qrcode";
    @NotNull
    public static final String UNDEFINED = "undefined";
    @NotNull
    public static final String WEB = "web";

    private ConnectionMethod() {
    }
}
