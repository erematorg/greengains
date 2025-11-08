package com.reown.android.internal.common.model;

import A.a;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\b@\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u000eHÖ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b\u0012\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0001\u0002¨\u0006\u0013"}, d2 = {"Lcom/reown/android/internal/common/model/PackageName;", "", "value", "", "constructor-impl", "(Ljava/lang/String;)Ljava/lang/String;", "getValue", "()Ljava/lang/String;", "equals", "", "other", "equals-impl", "(Ljava/lang/String;Ljava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(Ljava/lang/String;)I", "toString", "toString-impl", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@JvmInline
public final class PackageName {
    @NotNull
    private final String value;

    private /* synthetic */ PackageName(String str) {
        this.value = str;
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ PackageName m8764boximpl(String str) {
        return new PackageName(str);
    }

    @NotNull
    /* renamed from: constructor-impl  reason: not valid java name */
    public static String m8765constructorimpl(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "value");
        return str;
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m8766equalsimpl(String str, Object obj) {
        return (obj instanceof PackageName) && Intrinsics.areEqual((Object) str, (Object) ((PackageName) obj).m8770unboximpl());
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m8767equalsimpl0(String str, String str2) {
        return Intrinsics.areEqual((Object) str, (Object) str2);
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m8768hashCodeimpl(String str) {
        return str.hashCode();
    }

    /* renamed from: toString-impl  reason: not valid java name */
    public static String m8769toStringimpl(String str) {
        return a.l("PackageName(value=", str, ")");
    }

    public boolean equals(Object obj) {
        return m8766equalsimpl(this.value, obj);
    }

    @NotNull
    public final String getValue() {
        return this.value;
    }

    public int hashCode() {
        return m8768hashCodeimpl(this.value);
    }

    public String toString() {
        return m8769toStringimpl(this.value);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ String m8770unboximpl() {
        return this.value;
    }
}
