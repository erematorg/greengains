package com.apollographql.apollo3.exception;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 5, 1}, xi = 48)
public final class ApolloExceptionHandlerKt$DEFAULT_EXCEPTION_HANDLER$1 extends Lambda implements Function1<Throwable, Unit> {
    public static final ApolloExceptionHandlerKt$DEFAULT_EXCEPTION_HANDLER$1 INSTANCE = new ApolloExceptionHandlerKt$DEFAULT_EXCEPTION_HANDLER$1();

    public ApolloExceptionHandlerKt$DEFAULT_EXCEPTION_HANDLER$1() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull Throwable th) {
        Intrinsics.checkNotNullParameter(th, "it");
        System.out.println("Apollo: unhandled exception");
        th.printStackTrace();
    }
}
