package com.reown.sign.engine.model.tvf;

import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/reown/sign/engine/model/tvf/Wallet;", "", "id", "", "capabilities", "Lcom/reown/sign/engine/model/tvf/WalletCapabilities;", "<init>", "(Ljava/lang/String;Lcom/reown/sign/engine/model/tvf/WalletCapabilities;)V", "getId", "()Ljava/lang/String;", "getCapabilities", "()Lcom/reown/sign/engine/model/tvf/WalletCapabilities;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Wallet {
    @Nullable
    private final WalletCapabilities capabilities;
    @NotNull
    private final String id;

    public Wallet(@NotNull String str, @Nullable WalletCapabilities walletCapabilities) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        this.id = str;
        this.capabilities = walletCapabilities;
    }

    public static /* synthetic */ Wallet copy$default(Wallet wallet, String str, WalletCapabilities walletCapabilities, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = wallet.id;
        }
        if ((i3 & 2) != 0) {
            walletCapabilities = wallet.capabilities;
        }
        return wallet.copy(str, walletCapabilities);
    }

    @NotNull
    public final String component1() {
        return this.id;
    }

    @Nullable
    public final WalletCapabilities component2() {
        return this.capabilities;
    }

    @NotNull
    public final Wallet copy(@NotNull String str, @Nullable WalletCapabilities walletCapabilities) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        return new Wallet(str, walletCapabilities);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Wallet)) {
            return false;
        }
        Wallet wallet = (Wallet) obj;
        return Intrinsics.areEqual((Object) this.id, (Object) wallet.id) && Intrinsics.areEqual((Object) this.capabilities, (Object) wallet.capabilities);
    }

    @Nullable
    public final WalletCapabilities getCapabilities() {
        return this.capabilities;
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    public int hashCode() {
        int hashCode = this.id.hashCode() * 31;
        WalletCapabilities walletCapabilities = this.capabilities;
        return hashCode + (walletCapabilities == null ? 0 : walletCapabilities.hashCode());
    }

    @NotNull
    public String toString() {
        String str = this.id;
        WalletCapabilities walletCapabilities = this.capabilities;
        return "Wallet(id=" + str + ", capabilities=" + walletCapabilities + ")";
    }
}
