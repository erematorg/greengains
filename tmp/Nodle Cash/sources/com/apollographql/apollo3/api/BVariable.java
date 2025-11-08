package com.apollographql.apollo3.api;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\tJ\u0010\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003J$\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0013\u0010\u0011\u001a\u00020\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/apollographql/apollo3/api/BVariable;", "Lcom/apollographql/apollo3/api/BTerm;", "name", "", "(Ljava/lang/String;)V", "defaultValue", "", "(Ljava/lang/String;Ljava/lang/Boolean;)V", "getDefaultValue", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getName", "()Ljava/lang/String;", "component1", "component2", "copy", "(Ljava/lang/String;Ljava/lang/Boolean;)Lcom/apollographql/apollo3/api/BVariable;", "equals", "other", "", "hashCode", "", "toString", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class BVariable extends BTerm {
    @Nullable
    private final Boolean defaultValue;
    @NotNull
    private final String name;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BVariable(@NotNull String str, @Nullable Boolean bool) {
        super((DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "name");
        this.name = str;
        this.defaultValue = bool;
    }

    public static /* synthetic */ BVariable copy$default(BVariable bVariable, String str, Boolean bool, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = bVariable.name;
        }
        if ((i3 & 2) != 0) {
            bool = bVariable.defaultValue;
        }
        return bVariable.copy(str, bool);
    }

    @NotNull
    public final String component1() {
        return this.name;
    }

    @Nullable
    public final Boolean component2() {
        return this.defaultValue;
    }

    @NotNull
    public final BVariable copy(@NotNull String str, @Nullable Boolean bool) {
        Intrinsics.checkNotNullParameter(str, "name");
        return new BVariable(str, bool);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BVariable)) {
            return false;
        }
        BVariable bVariable = (BVariable) obj;
        return Intrinsics.areEqual((Object) this.name, (Object) bVariable.name) && Intrinsics.areEqual((Object) this.defaultValue, (Object) bVariable.defaultValue);
    }

    @Nullable
    public final Boolean getDefaultValue() {
        return this.defaultValue;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    public int hashCode() {
        int hashCode = this.name.hashCode() * 31;
        Boolean bool = this.defaultValue;
        return hashCode + (bool == null ? 0 : bool.hashCode());
    }

    @NotNull
    public String toString() {
        return "BVariable(name=" + this.name + ", defaultValue=" + this.defaultValue + ')';
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public BVariable(@NotNull String str) {
        this(str, Boolean.TRUE);
        Intrinsics.checkNotNullParameter(str, "name");
    }

    public static /* synthetic */ BVariable copy$default(BVariable bVariable, String str, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = bVariable.name;
        }
        return bVariable.copy(str);
    }

    @NotNull
    public final BVariable copy(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        return new BVariable(str, this.defaultValue);
    }
}
