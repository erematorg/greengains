package com.apollographql.apollo3.api;

import android.support.v4.media.session.a;
import androidx.camera.core.impl.i;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001a\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0017"}, d2 = {"Lcom/apollographql/apollo3/api/CompiledCondition;", "", "name", "", "inverted", "", "(Ljava/lang/String;Z)V", "defaultValue", "(Ljava/lang/String;ZZ)V", "getDefaultValue", "()Z", "getInverted", "getName", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class CompiledCondition {
    private final boolean defaultValue;
    private final boolean inverted;
    @NotNull
    private final String name;

    public CompiledCondition(@NotNull String str, boolean z2, boolean z3) {
        Intrinsics.checkNotNullParameter(str, "name");
        this.name = str;
        this.inverted = z2;
        this.defaultValue = z3;
    }

    public static /* synthetic */ CompiledCondition copy$default(CompiledCondition compiledCondition, String str, boolean z2, boolean z3, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = compiledCondition.name;
        }
        if ((i3 & 2) != 0) {
            z2 = compiledCondition.inverted;
        }
        if ((i3 & 4) != 0) {
            z3 = compiledCondition.defaultValue;
        }
        return compiledCondition.copy(str, z2, z3);
    }

    @NotNull
    public final String component1() {
        return this.name;
    }

    public final boolean component2() {
        return this.inverted;
    }

    public final boolean component3() {
        return this.defaultValue;
    }

    @NotNull
    public final CompiledCondition copy(@NotNull String str, boolean z2, boolean z3) {
        Intrinsics.checkNotNullParameter(str, "name");
        return new CompiledCondition(str, z2, z3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CompiledCondition)) {
            return false;
        }
        CompiledCondition compiledCondition = (CompiledCondition) obj;
        return Intrinsics.areEqual((Object) this.name, (Object) compiledCondition.name) && this.inverted == compiledCondition.inverted && this.defaultValue == compiledCondition.defaultValue;
    }

    public final boolean getDefaultValue() {
        return this.defaultValue;
    }

    public final boolean getInverted() {
        return this.inverted;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    public int hashCode() {
        return Boolean.hashCode(this.defaultValue) + a.f(this.inverted, this.name.hashCode() * 31, 31);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("CompiledCondition(name=");
        sb.append(this.name);
        sb.append(", inverted=");
        sb.append(this.inverted);
        sb.append(", defaultValue=");
        return i.c(sb, this.defaultValue, ')');
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CompiledCondition(@NotNull String str, boolean z2) {
        this(str, z2, true);
        Intrinsics.checkNotNullParameter(str, "name");
    }

    public static /* synthetic */ CompiledCondition copy$default(CompiledCondition compiledCondition, String str, boolean z2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = compiledCondition.name;
        }
        if ((i3 & 2) != 0) {
            z2 = compiledCondition.inverted;
        }
        return compiledCondition.copy(str, z2);
    }

    @NotNull
    public final CompiledCondition copy(@NotNull String str, boolean z2) {
        Intrinsics.checkNotNullParameter(str, "name");
        return new CompiledCondition(str, z2, this.defaultValue);
    }
}
