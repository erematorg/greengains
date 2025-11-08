package com.reown.foundation.common.model;

import A.a;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\b@\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0003¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u000fHÖ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b\u0013\u0010\u0005R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0001\u0002¨\u0006\u0014"}, d2 = {"Lcom/reown/foundation/common/model/PublicKey;", "Lcom/reown/foundation/common/model/Key;", "keyAsHex", "", "constructor-impl", "(Ljava/lang/String;)Ljava/lang/String;", "getKeyAsHex", "()Ljava/lang/String;", "equals", "", "other", "", "equals-impl", "(Ljava/lang/String;Ljava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(Ljava/lang/String;)I", "toString", "toString-impl", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
@JvmInline
public final class PublicKey implements Key {
    @NotNull
    private final String keyAsHex;

    private /* synthetic */ PublicKey(String str) {
        this.keyAsHex = str;
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ PublicKey m8855boximpl(String str) {
        return new PublicKey(str);
    }

    @NotNull
    /* renamed from: constructor-impl  reason: not valid java name */
    public static String m8856constructorimpl(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "keyAsHex");
        return str;
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m8857equalsimpl(String str, Object obj) {
        return (obj instanceof PublicKey) && Intrinsics.areEqual((Object) str, (Object) ((PublicKey) obj).m8862unboximpl());
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m8858equalsimpl0(String str, String str2) {
        return Intrinsics.areEqual((Object) str, (Object) str2);
    }

    @NotNull
    /* renamed from: getKeyAsBytes-impl  reason: not valid java name */
    public static byte[] m8859getKeyAsBytesimpl(String str) {
        return m8855boximpl(str).getKeyAsBytes();
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m8860hashCodeimpl(String str) {
        return str.hashCode();
    }

    /* renamed from: toString-impl  reason: not valid java name */
    public static String m8861toStringimpl(String str) {
        return a.l("PublicKey(keyAsHex=", str, ")");
    }

    public boolean equals(Object obj) {
        return m8857equalsimpl(this.keyAsHex, obj);
    }

    @NotNull
    public byte[] getKeyAsBytes() {
        return super.getKeyAsBytes();
    }

    @NotNull
    public String getKeyAsHex() {
        return this.keyAsHex;
    }

    public int hashCode() {
        return m8860hashCodeimpl(this.keyAsHex);
    }

    public String toString() {
        return m8861toStringimpl(this.keyAsHex);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ String m8862unboximpl() {
        return this.keyAsHex;
    }
}
