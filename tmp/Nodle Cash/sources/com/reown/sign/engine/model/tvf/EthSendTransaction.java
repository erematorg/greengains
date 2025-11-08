package com.reown.sign.engine.model.tvf;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.state.b;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003JY\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\t\u0010!\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\r¨\u0006\""}, d2 = {"Lcom/reown/sign/engine/model/tvf/EthSendTransaction;", "", "from", "", "to", "data", "gasLimit", "gasPrice", "value", "nonce", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getFrom", "()Ljava/lang/String;", "getTo", "getData", "getGasLimit", "getGasPrice", "getValue", "getNonce", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class EthSendTransaction {
    @Nullable
    private final String data;
    @NotNull
    private final String from;
    @Nullable
    private final String gasLimit;
    @Nullable
    private final String gasPrice;
    @Nullable
    private final String nonce;
    @NotNull
    private final String to;
    @Nullable
    private final String value;

    public EthSendTransaction(@NotNull String str, @NotNull String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7) {
        Intrinsics.checkNotNullParameter(str, "from");
        Intrinsics.checkNotNullParameter(str2, TypedValues.TransitionType.S_TO);
        this.from = str;
        this.to = str2;
        this.data = str3;
        this.gasLimit = str4;
        this.gasPrice = str5;
        this.value = str6;
        this.nonce = str7;
    }

    public static /* synthetic */ EthSendTransaction copy$default(EthSendTransaction ethSendTransaction, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = ethSendTransaction.from;
        }
        if ((i3 & 2) != 0) {
            str2 = ethSendTransaction.to;
        }
        String str8 = str2;
        if ((i3 & 4) != 0) {
            str3 = ethSendTransaction.data;
        }
        String str9 = str3;
        if ((i3 & 8) != 0) {
            str4 = ethSendTransaction.gasLimit;
        }
        String str10 = str4;
        if ((i3 & 16) != 0) {
            str5 = ethSendTransaction.gasPrice;
        }
        String str11 = str5;
        if ((i3 & 32) != 0) {
            str6 = ethSendTransaction.value;
        }
        String str12 = str6;
        if ((i3 & 64) != 0) {
            str7 = ethSendTransaction.nonce;
        }
        return ethSendTransaction.copy(str, str8, str9, str10, str11, str12, str7);
    }

    @NotNull
    public final String component1() {
        return this.from;
    }

    @NotNull
    public final String component2() {
        return this.to;
    }

    @Nullable
    public final String component3() {
        return this.data;
    }

    @Nullable
    public final String component4() {
        return this.gasLimit;
    }

    @Nullable
    public final String component5() {
        return this.gasPrice;
    }

    @Nullable
    public final String component6() {
        return this.value;
    }

    @Nullable
    public final String component7() {
        return this.nonce;
    }

    @NotNull
    public final EthSendTransaction copy(@NotNull String str, @NotNull String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7) {
        Intrinsics.checkNotNullParameter(str, "from");
        Intrinsics.checkNotNullParameter(str2, TypedValues.TransitionType.S_TO);
        return new EthSendTransaction(str, str2, str3, str4, str5, str6, str7);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EthSendTransaction)) {
            return false;
        }
        EthSendTransaction ethSendTransaction = (EthSendTransaction) obj;
        return Intrinsics.areEqual((Object) this.from, (Object) ethSendTransaction.from) && Intrinsics.areEqual((Object) this.to, (Object) ethSendTransaction.to) && Intrinsics.areEqual((Object) this.data, (Object) ethSendTransaction.data) && Intrinsics.areEqual((Object) this.gasLimit, (Object) ethSendTransaction.gasLimit) && Intrinsics.areEqual((Object) this.gasPrice, (Object) ethSendTransaction.gasPrice) && Intrinsics.areEqual((Object) this.value, (Object) ethSendTransaction.value) && Intrinsics.areEqual((Object) this.nonce, (Object) ethSendTransaction.nonce);
    }

    @Nullable
    public final String getData() {
        return this.data;
    }

    @NotNull
    public final String getFrom() {
        return this.from;
    }

    @Nullable
    public final String getGasLimit() {
        return this.gasLimit;
    }

    @Nullable
    public final String getGasPrice() {
        return this.gasPrice;
    }

    @Nullable
    public final String getNonce() {
        return this.nonce;
    }

    @NotNull
    public final String getTo() {
        return this.to;
    }

    @Nullable
    public final String getValue() {
        return this.value;
    }

    public int hashCode() {
        int i3 = a.i(this.to, this.from.hashCode() * 31, 31);
        String str = this.data;
        int i4 = 0;
        int hashCode = (i3 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.gasLimit;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.gasPrice;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.value;
        int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.nonce;
        if (str5 != null) {
            i4 = str5.hashCode();
        }
        return hashCode4 + i4;
    }

    @NotNull
    public String toString() {
        String str = this.from;
        String str2 = this.to;
        String str3 = this.data;
        String str4 = this.gasLimit;
        String str5 = this.gasPrice;
        String str6 = this.value;
        String str7 = this.nonce;
        StringBuilder l2 = C0118y.l("EthSendTransaction(from=", str, ", to=", str2, ", data=");
        b.w(l2, str3, ", gasLimit=", str4, ", gasPrice=");
        b.w(l2, str5, ", value=", str6, ", nonce=");
        return A.a.n(l2, str7, ")");
    }
}
