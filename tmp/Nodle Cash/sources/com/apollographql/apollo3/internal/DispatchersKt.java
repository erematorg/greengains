package com.apollographql.apollo3.internal;

import kotlin.Metadata;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\u001a\b\u0010\u0004\u001a\u00020\u0005H\u0000\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0006"}, d2 = {"defaultDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "getDefaultDispatcher", "()Lkotlinx/coroutines/CoroutineDispatcher;", "failOnNativeIfLegacyMemoryManager", "", "apollo-runtime"}, k = 2, mv = {1, 5, 1}, xi = 48)
public final class DispatchersKt {
    @NotNull
    private static final CoroutineDispatcher defaultDispatcher = Dispatchers.getIO();

    public static final void failOnNativeIfLegacyMemoryManager() {
    }

    @NotNull
    public static final CoroutineDispatcher getDefaultDispatcher() {
        return defaultDispatcher;
    }
}
