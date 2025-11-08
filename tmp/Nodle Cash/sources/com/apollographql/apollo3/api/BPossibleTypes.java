package com.apollographql.apollo3.api;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001b\b\u0016\u0012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0013\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007¢\u0006\u0002\u0010\bJ\u000f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007HÆ\u0003J\u0019\u0010\f\u001a\u00020\u00002\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0004HÖ\u0001R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/apollographql/apollo3/api/BPossibleTypes;", "Lcom/apollographql/apollo3/api/BTerm;", "types", "", "", "([Ljava/lang/String;)V", "possibleTypes", "", "(Ljava/util/Set;)V", "getPossibleTypes", "()Ljava/util/Set;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class BPossibleTypes extends BTerm {
    @NotNull
    private final Set<String> possibleTypes;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BPossibleTypes(@NotNull Set<String> set) {
        super((DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(set, "possibleTypes");
        this.possibleTypes = set;
    }

    public static /* synthetic */ BPossibleTypes copy$default(BPossibleTypes bPossibleTypes, Set<String> set, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            set = bPossibleTypes.possibleTypes;
        }
        return bPossibleTypes.copy(set);
    }

    @NotNull
    public final Set<String> component1() {
        return this.possibleTypes;
    }

    @NotNull
    public final BPossibleTypes copy(@NotNull Set<String> set) {
        Intrinsics.checkNotNullParameter(set, "possibleTypes");
        return new BPossibleTypes(set);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof BPossibleTypes) && Intrinsics.areEqual((Object) this.possibleTypes, (Object) ((BPossibleTypes) obj).possibleTypes);
    }

    @NotNull
    public final Set<String> getPossibleTypes() {
        return this.possibleTypes;
    }

    public int hashCode() {
        return this.possibleTypes.hashCode();
    }

    @NotNull
    public String toString() {
        return "BPossibleTypes(possibleTypes=" + this.possibleTypes + ')';
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public BPossibleTypes(@NotNull String... strArr) {
        this((Set<String>) ArraysKt.toSet((T[]) strArr));
        Intrinsics.checkNotNullParameter(strArr, "types");
    }
}
