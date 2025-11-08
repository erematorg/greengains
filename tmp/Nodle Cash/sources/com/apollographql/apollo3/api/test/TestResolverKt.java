package com.apollographql.apollo3.api.test;

import com.apollographql.apollo3.annotations.ApolloExperimental;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a)\u0010\b\u001a\u0002H\t\"\u0004\b\u0000\u0010\t2\u0006\u0010\n\u001a\u00020\u00012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\t0\fH\u0007¢\u0006\u0002\u0010\r\"&\u0010\u0000\u001a\u0004\u0018\u00010\u00018\u0000@\u0000X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"currentTestResolver", "Lcom/apollographql/apollo3/api/test/TestResolver;", "getCurrentTestResolver$annotations", "()V", "getCurrentTestResolver", "()Lcom/apollographql/apollo3/api/test/TestResolver;", "setCurrentTestResolver", "(Lcom/apollographql/apollo3/api/test/TestResolver;)V", "withTestResolver", "T", "testResolver", "block", "Lkotlin/Function0;", "(Lcom/apollographql/apollo3/api/test/TestResolver;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "apollo-api"}, k = 2, mv = {1, 5, 1}, xi = 48)
public final class TestResolverKt {
    @Nullable
    private static TestResolver currentTestResolver;

    @Nullable
    public static final TestResolver getCurrentTestResolver() {
        return currentTestResolver;
    }

    @ApolloExperimental
    public static /* synthetic */ void getCurrentTestResolver$annotations() {
    }

    public static final void setCurrentTestResolver(@Nullable TestResolver testResolver) {
        currentTestResolver = testResolver;
    }

    @ApolloExperimental
    public static final <T> T withTestResolver(@NotNull TestResolver testResolver, @NotNull Function0<? extends T> function0) {
        Intrinsics.checkNotNullParameter(testResolver, "testResolver");
        Intrinsics.checkNotNullParameter(function0, "block");
        currentTestResolver = testResolver;
        T invoke = function0.invoke();
        currentTestResolver = null;
        return invoke;
    }
}
