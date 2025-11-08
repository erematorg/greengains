package com.apollographql.apollo3.api;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 5, 1}, xi = 48)
public final class DefaultFakeResolver$resolveLeaf$1 extends Lambda implements Function1<Object, CharSequence> {
    final /* synthetic */ DefaultFakeResolver this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DefaultFakeResolver$resolveLeaf$1(DefaultFakeResolver defaultFakeResolver) {
        super(1);
        this.this$0 = defaultFakeResolver;
    }

    @NotNull
    public final CharSequence invoke(@NotNull Object obj) {
        Intrinsics.checkNotNullParameter(obj, "it");
        return this.this$0.toPathComponent(obj);
    }
}
