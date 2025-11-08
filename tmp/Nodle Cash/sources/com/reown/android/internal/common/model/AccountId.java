package com.reown.android.internal.common.model;

import A.a;
import com.reown.android.internal.utils.CoreValidator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0005\b@\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\r\u0010\f\u001a\u00020\u0003¢\u0006\u0004\b\r\u0010\u0005J\u001a\u0010\u000e\u001a\u00020\t2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0013HÖ\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b\u0017\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0001\u0002¨\u0006\u0018"}, d2 = {"Lcom/reown/android/internal/common/model/AccountId;", "", "value", "", "constructor-impl", "(Ljava/lang/String;)Ljava/lang/String;", "getValue", "()Ljava/lang/String;", "isValid", "", "isValid-impl", "(Ljava/lang/String;)Z", "address", "address-impl", "equals", "other", "equals-impl", "(Ljava/lang/String;Ljava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(Ljava/lang/String;)I", "toString", "toString-impl", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@JvmInline
public final class AccountId {
    @NotNull
    private final String value;

    private /* synthetic */ AccountId(String str) {
        this.value = str;
    }

    @NotNull
    /* renamed from: address-impl  reason: not valid java name */
    public static final String m8748addressimpl(String str) {
        return (String) CollectionsKt.last(StringsKt__StringsKt.split$default((CharSequence) str, new String[]{":"}, false, 0, 6, (Object) null));
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ AccountId m8749boximpl(String str) {
        return new AccountId(str);
    }

    @NotNull
    /* renamed from: constructor-impl  reason: not valid java name */
    public static String m8750constructorimpl(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "value");
        return str;
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m8751equalsimpl(String str, Object obj) {
        return (obj instanceof AccountId) && Intrinsics.areEqual((Object) str, (Object) ((AccountId) obj).m8756unboximpl());
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m8752equalsimpl0(String str, String str2) {
        return Intrinsics.areEqual((Object) str, (Object) str2);
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m8753hashCodeimpl(String str) {
        return str.hashCode();
    }

    /* renamed from: isValid-impl  reason: not valid java name */
    public static final boolean m8754isValidimpl(String str) {
        return CoreValidator.INSTANCE.isAccountIdCAIP10Compliant(str);
    }

    /* renamed from: toString-impl  reason: not valid java name */
    public static String m8755toStringimpl(String str) {
        return a.l("AccountId(value=", str, ")");
    }

    public boolean equals(Object obj) {
        return m8751equalsimpl(this.value, obj);
    }

    @NotNull
    public final String getValue() {
        return this.value;
    }

    public int hashCode() {
        return m8753hashCodeimpl(this.value);
    }

    public String toString() {
        return m8755toStringimpl(this.value);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ String m8756unboximpl() {
        return this.value;
    }
}
