package com.reown.android.internal.common.signing.cacao;

import android.support.v4.media.session.a;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0007R\u0011\u0010\f\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\r\u0010\u0007R\u0011\u0010\u000e\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0007R\u0011\u0010\u0010\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0007¨\u0006\u001b"}, d2 = {"Lcom/reown/android/internal/common/signing/cacao/Issuer;", "", "value", "", "<init>", "(Ljava/lang/String;)V", "getValue", "()Ljava/lang/String;", "chainId", "getChainId", "chainIdReference", "getChainIdReference", "address", "getAddress", "namespace", "getNamespace", "accountId", "getAccountId", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Issuer {
    @NotNull
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @NotNull
    @Deprecated
    public static final String ISS_DELIMITER = ":";
    @Deprecated
    public static final int ISS_POSITION_OF_ADDRESS = 4;
    @Deprecated
    public static final int ISS_POSITION_OF_NAMESPACE = 2;
    @Deprecated
    public static final int ISS_POSITION_OF_REFERENCE = 3;
    @NotNull
    private final String value;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/reown/android/internal/common/signing/cacao/Issuer$Companion;", "", "<init>", "()V", "ISS_DELIMITER", "", "ISS_POSITION_OF_NAMESPACE", "", "ISS_POSITION_OF_REFERENCE", "ISS_POSITION_OF_ADDRESS", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public Issuer(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "value");
        this.value = str;
    }

    public static /* synthetic */ Issuer copy$default(Issuer issuer, String str, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = issuer.value;
        }
        return issuer.copy(str);
    }

    @NotNull
    public final String component1() {
        return this.value;
    }

    @NotNull
    public final Issuer copy(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "value");
        return new Issuer(str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof Issuer) && Intrinsics.areEqual((Object) this.value, (Object) ((Issuer) obj).value);
    }

    @NotNull
    public final String getAccountId() {
        return a.n(getChainId(), ":", getAddress());
    }

    @NotNull
    public final String getAddress() {
        return (String) StringsKt__StringsKt.split$default((CharSequence) this.value, new String[]{":"}, false, 0, 6, (Object) null).get(4);
    }

    @NotNull
    public final String getChainId() {
        Object obj = StringsKt__StringsKt.split$default((CharSequence) this.value, new String[]{":"}, false, 0, 6, (Object) null).get(2);
        Object obj2 = StringsKt__StringsKt.split$default((CharSequence) this.value, new String[]{":"}, false, 0, 6, (Object) null).get(3);
        return obj + ":" + obj2;
    }

    @NotNull
    public final String getChainIdReference() {
        return (String) StringsKt__StringsKt.split$default((CharSequence) this.value, new String[]{":"}, false, 0, 6, (Object) null).get(3);
    }

    @NotNull
    public final String getNamespace() {
        return (String) StringsKt__StringsKt.split$default((CharSequence) this.value, new String[]{":"}, false, 0, 6, (Object) null).get(2);
    }

    @NotNull
    public final String getValue() {
        return this.value;
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    @NotNull
    public String toString() {
        return A.a.l("Issuer(value=", this.value, ")");
    }
}
