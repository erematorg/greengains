package com.reown.sign.engine.model.tvf;

import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/sign/engine/model/tvf/WalletCapabilities;", "", "caip345", "Lcom/reown/sign/engine/model/tvf/CAIP345;", "<init>", "(Lcom/reown/sign/engine/model/tvf/CAIP345;)V", "getCaip345", "()Lcom/reown/sign/engine/model/tvf/CAIP345;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class WalletCapabilities {
    @Nullable
    private final CAIP345 caip345;

    public WalletCapabilities(@Nullable CAIP345 caip3452) {
        this.caip345 = caip3452;
    }

    public static /* synthetic */ WalletCapabilities copy$default(WalletCapabilities walletCapabilities, CAIP345 caip3452, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            caip3452 = walletCapabilities.caip345;
        }
        return walletCapabilities.copy(caip3452);
    }

    @Nullable
    public final CAIP345 component1() {
        return this.caip345;
    }

    @NotNull
    public final WalletCapabilities copy(@Nullable CAIP345 caip3452) {
        return new WalletCapabilities(caip3452);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof WalletCapabilities) && Intrinsics.areEqual((Object) this.caip345, (Object) ((WalletCapabilities) obj).caip345);
    }

    @Nullable
    public final CAIP345 getCaip345() {
        return this.caip345;
    }

    public int hashCode() {
        CAIP345 caip3452 = this.caip345;
        if (caip3452 == null) {
            return 0;
        }
        return caip3452.hashCode();
    }

    @NotNull
    public String toString() {
        CAIP345 caip3452 = this.caip345;
        return "WalletCapabilities(caip345=" + caip3452 + ")";
    }
}
