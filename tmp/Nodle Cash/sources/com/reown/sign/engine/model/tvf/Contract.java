package com.reown.sign.engine.model.tvf;

import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J!\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0005HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/reown/sign/engine/model/tvf/Contract;", "", "parameter", "Lcom/reown/sign/engine/model/tvf/Parameter;", "type", "", "<init>", "(Lcom/reown/sign/engine/model/tvf/Parameter;Ljava/lang/String;)V", "getParameter", "()Lcom/reown/sign/engine/model/tvf/Parameter;", "getType", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Contract {
    @Nullable
    private final Parameter parameter;
    @Nullable
    private final String type;

    public Contract(@Nullable Parameter parameter2, @Nullable String str) {
        this.parameter = parameter2;
        this.type = str;
    }

    public static /* synthetic */ Contract copy$default(Contract contract, Parameter parameter2, String str, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            parameter2 = contract.parameter;
        }
        if ((i3 & 2) != 0) {
            str = contract.type;
        }
        return contract.copy(parameter2, str);
    }

    @Nullable
    public final Parameter component1() {
        return this.parameter;
    }

    @Nullable
    public final String component2() {
        return this.type;
    }

    @NotNull
    public final Contract copy(@Nullable Parameter parameter2, @Nullable String str) {
        return new Contract(parameter2, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Contract)) {
            return false;
        }
        Contract contract = (Contract) obj;
        return Intrinsics.areEqual((Object) this.parameter, (Object) contract.parameter) && Intrinsics.areEqual((Object) this.type, (Object) contract.type);
    }

    @Nullable
    public final Parameter getParameter() {
        return this.parameter;
    }

    @Nullable
    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        Parameter parameter2 = this.parameter;
        int i3 = 0;
        int hashCode = (parameter2 == null ? 0 : parameter2.hashCode()) * 31;
        String str = this.type;
        if (str != null) {
            i3 = str.hashCode();
        }
        return hashCode + i3;
    }

    @NotNull
    public String toString() {
        Parameter parameter2 = this.parameter;
        String str = this.type;
        return "Contract(parameter=" + parameter2 + ", type=" + str + ")";
    }
}
