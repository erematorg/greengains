package com.reown.sign.engine.model.tvf;

import A.a;
import androidx.camera.camera2.internal.C0118y;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B%\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J-\u0010\u000f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/reown/sign/engine/model/tvf/ContractValue;", "", "data", "", "contract_address", "owner_address", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getData", "()Ljava/lang/String;", "getContract_address", "getOwner_address", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ContractValue {
    @Nullable
    private final String contract_address;
    @Nullable
    private final String data;
    @Nullable
    private final String owner_address;

    public ContractValue(@Nullable String str, @Nullable String str2, @Nullable String str3) {
        this.data = str;
        this.contract_address = str2;
        this.owner_address = str3;
    }

    public static /* synthetic */ ContractValue copy$default(ContractValue contractValue, String str, String str2, String str3, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = contractValue.data;
        }
        if ((i3 & 2) != 0) {
            str2 = contractValue.contract_address;
        }
        if ((i3 & 4) != 0) {
            str3 = contractValue.owner_address;
        }
        return contractValue.copy(str, str2, str3);
    }

    @Nullable
    public final String component1() {
        return this.data;
    }

    @Nullable
    public final String component2() {
        return this.contract_address;
    }

    @Nullable
    public final String component3() {
        return this.owner_address;
    }

    @NotNull
    public final ContractValue copy(@Nullable String str, @Nullable String str2, @Nullable String str3) {
        return new ContractValue(str, str2, str3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ContractValue)) {
            return false;
        }
        ContractValue contractValue = (ContractValue) obj;
        return Intrinsics.areEqual((Object) this.data, (Object) contractValue.data) && Intrinsics.areEqual((Object) this.contract_address, (Object) contractValue.contract_address) && Intrinsics.areEqual((Object) this.owner_address, (Object) contractValue.owner_address);
    }

    @Nullable
    public final String getContract_address() {
        return this.contract_address;
    }

    @Nullable
    public final String getData() {
        return this.data;
    }

    @Nullable
    public final String getOwner_address() {
        return this.owner_address;
    }

    public int hashCode() {
        String str = this.data;
        int i3 = 0;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.contract_address;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.owner_address;
        if (str3 != null) {
            i3 = str3.hashCode();
        }
        return hashCode2 + i3;
    }

    @NotNull
    public String toString() {
        String str = this.data;
        String str2 = this.contract_address;
        return a.n(C0118y.l("ContractValue(data=", str, ", contract_address=", str2, ", owner_address="), this.owner_address, ")");
    }
}
