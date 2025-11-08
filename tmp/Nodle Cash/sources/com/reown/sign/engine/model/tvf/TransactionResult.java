package com.reown.sign.engine.model.tvf;

import A.a;
import com.squareup.moshi.JsonClass;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\u0011\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u0014J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003JN\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u001dJ\u0013\u0010\u001e\u001a\u00020\t2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0019\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0015\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000e¨\u0006#"}, d2 = {"Lcom/reown/sign/engine/model/tvf/TransactionResult;", "", "txID", "", "signature", "", "raw_data", "Lcom/reown/sign/engine/model/tvf/RawData;", "visible", "", "raw_data_hex", "<init>", "(Ljava/lang/String;Ljava/util/List;Lcom/reown/sign/engine/model/tvf/RawData;Ljava/lang/Boolean;Ljava/lang/String;)V", "getTxID", "()Ljava/lang/String;", "getSignature", "()Ljava/util/List;", "getRaw_data", "()Lcom/reown/sign/engine/model/tvf/RawData;", "getVisible", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getRaw_data_hex", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;Ljava/util/List;Lcom/reown/sign/engine/model/tvf/RawData;Ljava/lang/Boolean;Ljava/lang/String;)Lcom/reown/sign/engine/model/tvf/TransactionResult;", "equals", "other", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TransactionResult {
    @Nullable
    private final RawData raw_data;
    @Nullable
    private final String raw_data_hex;
    @Nullable
    private final List<String> signature;
    @NotNull
    private final String txID;
    @Nullable
    private final Boolean visible;

    public TransactionResult(@NotNull String str, @Nullable List<String> list, @Nullable RawData rawData, @Nullable Boolean bool, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(str, "txID");
        this.txID = str;
        this.signature = list;
        this.raw_data = rawData;
        this.visible = bool;
        this.raw_data_hex = str2;
    }

    public static /* synthetic */ TransactionResult copy$default(TransactionResult transactionResult, String str, List<String> list, RawData rawData, Boolean bool, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = transactionResult.txID;
        }
        if ((i3 & 2) != 0) {
            list = transactionResult.signature;
        }
        List<String> list2 = list;
        if ((i3 & 4) != 0) {
            rawData = transactionResult.raw_data;
        }
        RawData rawData2 = rawData;
        if ((i3 & 8) != 0) {
            bool = transactionResult.visible;
        }
        Boolean bool2 = bool;
        if ((i3 & 16) != 0) {
            str2 = transactionResult.raw_data_hex;
        }
        return transactionResult.copy(str, list2, rawData2, bool2, str2);
    }

    @NotNull
    public final String component1() {
        return this.txID;
    }

    @Nullable
    public final List<String> component2() {
        return this.signature;
    }

    @Nullable
    public final RawData component3() {
        return this.raw_data;
    }

    @Nullable
    public final Boolean component4() {
        return this.visible;
    }

    @Nullable
    public final String component5() {
        return this.raw_data_hex;
    }

    @NotNull
    public final TransactionResult copy(@NotNull String str, @Nullable List<String> list, @Nullable RawData rawData, @Nullable Boolean bool, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(str, "txID");
        return new TransactionResult(str, list, rawData, bool, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TransactionResult)) {
            return false;
        }
        TransactionResult transactionResult = (TransactionResult) obj;
        return Intrinsics.areEqual((Object) this.txID, (Object) transactionResult.txID) && Intrinsics.areEqual((Object) this.signature, (Object) transactionResult.signature) && Intrinsics.areEqual((Object) this.raw_data, (Object) transactionResult.raw_data) && Intrinsics.areEqual((Object) this.visible, (Object) transactionResult.visible) && Intrinsics.areEqual((Object) this.raw_data_hex, (Object) transactionResult.raw_data_hex);
    }

    @Nullable
    public final RawData getRaw_data() {
        return this.raw_data;
    }

    @Nullable
    public final String getRaw_data_hex() {
        return this.raw_data_hex;
    }

    @Nullable
    public final List<String> getSignature() {
        return this.signature;
    }

    @NotNull
    public final String getTxID() {
        return this.txID;
    }

    @Nullable
    public final Boolean getVisible() {
        return this.visible;
    }

    public int hashCode() {
        int hashCode = this.txID.hashCode() * 31;
        List<String> list = this.signature;
        int i3 = 0;
        int hashCode2 = (hashCode + (list == null ? 0 : list.hashCode())) * 31;
        RawData rawData = this.raw_data;
        int hashCode3 = (hashCode2 + (rawData == null ? 0 : rawData.hashCode())) * 31;
        Boolean bool = this.visible;
        int hashCode4 = (hashCode3 + (bool == null ? 0 : bool.hashCode())) * 31;
        String str = this.raw_data_hex;
        if (str != null) {
            i3 = str.hashCode();
        }
        return hashCode4 + i3;
    }

    @NotNull
    public String toString() {
        String str = this.txID;
        List<String> list = this.signature;
        RawData rawData = this.raw_data;
        Boolean bool = this.visible;
        String str2 = this.raw_data_hex;
        StringBuilder sb = new StringBuilder("TransactionResult(txID=");
        sb.append(str);
        sb.append(", signature=");
        sb.append(list);
        sb.append(", raw_data=");
        sb.append(rawData);
        sb.append(", visible=");
        sb.append(bool);
        sb.append(", raw_data_hex=");
        return a.n(sb, str2, ")");
    }
}
