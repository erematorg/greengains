package com.reown.android.internal.common.model;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b@\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\b\u001a\u00020\u00032\b\u0010\t\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u0011HÖ\u0001¢\u0006\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0001\u0002¨\u0006\u0014"}, d2 = {"Lcom/reown/android/internal/common/model/TelemetryEnabled;", "", "value", "", "constructor-impl", "(Z)Z", "getValue", "()Z", "equals", "other", "equals-impl", "(ZLjava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(Z)I", "toString", "", "toString-impl", "(Z)Ljava/lang/String;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@JvmInline
public final class TelemetryEnabled {
    private final boolean value;

    private /* synthetic */ TelemetryEnabled(boolean z2) {
        this.value = z2;
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ TelemetryEnabled m8785boximpl(boolean z2) {
        return new TelemetryEnabled(z2);
    }

    /* renamed from: constructor-impl  reason: not valid java name */
    public static boolean m8786constructorimpl(boolean z2) {
        return z2;
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m8787equalsimpl(boolean z2, Object obj) {
        return (obj instanceof TelemetryEnabled) && z2 == ((TelemetryEnabled) obj).m8791unboximpl();
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m8788equalsimpl0(boolean z2, boolean z3) {
        return z2 == z3;
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m8789hashCodeimpl(boolean z2) {
        return Boolean.hashCode(z2);
    }

    /* renamed from: toString-impl  reason: not valid java name */
    public static String m8790toStringimpl(boolean z2) {
        return "TelemetryEnabled(value=" + z2 + ")";
    }

    public boolean equals(Object obj) {
        return m8787equalsimpl(this.value, obj);
    }

    public final boolean getValue() {
        return this.value;
    }

    public int hashCode() {
        return m8789hashCodeimpl(this.value);
    }

    public String toString() {
        return m8790toStringimpl(this.value);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ boolean m8791unboximpl() {
        return this.value;
    }
}
