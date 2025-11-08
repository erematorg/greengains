package com.reown.sign.engine.model.tvf;

import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J!\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/reown/sign/engine/model/tvf/Parameter;", "", "type_url", "", "value", "Lcom/reown/sign/engine/model/tvf/ContractValue;", "<init>", "(Ljava/lang/String;Lcom/reown/sign/engine/model/tvf/ContractValue;)V", "getType_url", "()Ljava/lang/String;", "getValue", "()Lcom/reown/sign/engine/model/tvf/ContractValue;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Parameter {
    @Nullable
    private final String type_url;
    @Nullable
    private final ContractValue value;

    public Parameter(@Nullable String str, @Nullable @Json(name = "value") ContractValue contractValue) {
        this.type_url = str;
        this.value = contractValue;
    }

    public static /* synthetic */ Parameter copy$default(Parameter parameter, String str, ContractValue contractValue, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = parameter.type_url;
        }
        if ((i3 & 2) != 0) {
            contractValue = parameter.value;
        }
        return parameter.copy(str, contractValue);
    }

    @Nullable
    public final String component1() {
        return this.type_url;
    }

    @Nullable
    public final ContractValue component2() {
        return this.value;
    }

    @NotNull
    public final Parameter copy(@Nullable String str, @Nullable @Json(name = "value") ContractValue contractValue) {
        return new Parameter(str, contractValue);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Parameter)) {
            return false;
        }
        Parameter parameter = (Parameter) obj;
        return Intrinsics.areEqual((Object) this.type_url, (Object) parameter.type_url) && Intrinsics.areEqual((Object) this.value, (Object) parameter.value);
    }

    @Nullable
    public final String getType_url() {
        return this.type_url;
    }

    @Nullable
    public final ContractValue getValue() {
        return this.value;
    }

    public int hashCode() {
        String str = this.type_url;
        int i3 = 0;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        ContractValue contractValue = this.value;
        if (contractValue != null) {
            i3 = contractValue.hashCode();
        }
        return hashCode + i3;
    }

    @NotNull
    public String toString() {
        String str = this.type_url;
        ContractValue contractValue = this.value;
        return "Parameter(type_url=" + str + ", value=" + contractValue + ")";
    }
}
