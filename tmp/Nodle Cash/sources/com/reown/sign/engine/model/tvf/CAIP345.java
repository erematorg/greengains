package com.reown.sign.engine.model.tvf;

import com.squareup.moshi.JsonClass;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B!\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0019\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/reown/sign/engine/model/tvf/CAIP345;", "", "caip2", "", "transactionHashes", "", "<init>", "(Ljava/lang/String;Ljava/util/List;)V", "getCaip2", "()Ljava/lang/String;", "getTransactionHashes", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CAIP345 {
    @Nullable
    private final String caip2;
    @Nullable
    private final List<String> transactionHashes;

    public CAIP345(@Nullable String str, @Nullable List<String> list) {
        this.caip2 = str;
        this.transactionHashes = list;
    }

    public static /* synthetic */ CAIP345 copy$default(CAIP345 caip345, String str, List<String> list, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = caip345.caip2;
        }
        if ((i3 & 2) != 0) {
            list = caip345.transactionHashes;
        }
        return caip345.copy(str, list);
    }

    @Nullable
    public final String component1() {
        return this.caip2;
    }

    @Nullable
    public final List<String> component2() {
        return this.transactionHashes;
    }

    @NotNull
    public final CAIP345 copy(@Nullable String str, @Nullable List<String> list) {
        return new CAIP345(str, list);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CAIP345)) {
            return false;
        }
        CAIP345 caip345 = (CAIP345) obj;
        return Intrinsics.areEqual((Object) this.caip2, (Object) caip345.caip2) && Intrinsics.areEqual((Object) this.transactionHashes, (Object) caip345.transactionHashes);
    }

    @Nullable
    public final String getCaip2() {
        return this.caip2;
    }

    @Nullable
    public final List<String> getTransactionHashes() {
        return this.transactionHashes;
    }

    public int hashCode() {
        String str = this.caip2;
        int i3 = 0;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        List<String> list = this.transactionHashes;
        if (list != null) {
            i3 = list.hashCode();
        }
        return hashCode + i3;
    }

    @NotNull
    public String toString() {
        String str = this.caip2;
        List<String> list = this.transactionHashes;
        return "CAIP345(caip2=" + str + ", transactionHashes=" + list + ")";
    }
}
