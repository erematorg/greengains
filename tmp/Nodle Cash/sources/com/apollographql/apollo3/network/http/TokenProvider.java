package com.apollographql.apollo3.network.http;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H¦@¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0003H¦@¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/apollographql/apollo3/network/http/TokenProvider;", "", "currentToken", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "refreshToken", "previousToken", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
@Deprecated(message = "BearerTokenInterceptor was provided as an example but is too simple for most use cases.Define your own interceptor or take a look at https://www.apollographql.com/docs/kotlin/advanced/interceptors-http for more details.")
public interface TokenProvider {
    @Nullable
    Object currentToken(@NotNull Continuation<? super String> continuation);

    @Nullable
    Object refreshToken(@NotNull String str, @NotNull Continuation<? super String> continuation);
}
