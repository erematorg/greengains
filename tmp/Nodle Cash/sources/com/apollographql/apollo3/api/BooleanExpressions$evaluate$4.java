package com.apollographql.apollo3.api;

import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/apollographql/apollo3/api/BTerm;", "invoke", "(Lcom/apollographql/apollo3/api/BTerm;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 5, 1}, xi = 48)
public final class BooleanExpressions$evaluate$4 extends Lambda implements Function1<BTerm, Boolean> {
    final /* synthetic */ C0216AdapterContext $adapterContext;
    final /* synthetic */ List<Object> $croppedPath;
    final /* synthetic */ String $typename;
    final /* synthetic */ Set<String> $variables;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BooleanExpressions$evaluate$4(Set<String> set, C0216AdapterContext adapterContext, List<? extends Object> list, String str) {
        super(1);
        this.$variables = set;
        this.$adapterContext = adapterContext;
        this.$croppedPath = list;
        this.$typename = str;
    }

    @NotNull
    public final Boolean invoke(@NotNull BTerm bTerm) {
        boolean z2;
        Intrinsics.checkNotNullParameter(bTerm, "it");
        if (bTerm instanceof BVariable) {
            z2 = !this.$variables.contains(((BVariable) bTerm).getName());
        } else if (bTerm instanceof BLabel) {
            C0216AdapterContext adapterContext = this.$adapterContext;
            List<Object> list = this.$croppedPath;
            Intrinsics.checkNotNull(list);
            z2 = adapterContext.hasDeferredFragment(list, ((BLabel) bTerm).getLabel());
        } else if (bTerm instanceof BPossibleTypes) {
            z2 = CollectionsKt.contains(((BPossibleTypes) bTerm).getPossibleTypes(), this.$typename);
        } else {
            throw new NoWhenBranchMatchedException();
        }
        return Boolean.valueOf(z2);
    }
}
