package com.apollographql.apollo3.api;

import com.apollographql.apollo3.api.http.HttpHeader;
import com.apollographql.apollo3.api.http.HttpMethod;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0015\u0010\u0003\u001a\u00028\u00002\u0006\u0010\u0004\u001a\u00020\u0005H&¢\u0006\u0002\u0010\u0006J\u001d\u0010\u0007\u001a\u00028\u00002\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH&¢\u0006\u0002\u0010\u000bJ\u0017\u0010\f\u001a\u00028\u00002\b\u0010\f\u001a\u0004\u0018\u00010\rH&¢\u0006\u0002\u0010\u000eJ\u0017\u0010\u000f\u001a\u00028\u00002\b\u0010\u000f\u001a\u0004\u0018\u00010\rH&¢\u0006\u0002\u0010\u000eJ\u001d\u0010\u0010\u001a\u00028\u00002\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011H&¢\u0006\u0002\u0010\u0013J\u0017\u0010\u0014\u001a\u00028\u00002\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H&¢\u0006\u0002\u0010\u0016J\u0017\u0010\u0017\u001a\u00028\u00002\b\u0010\u0017\u001a\u0004\u0018\u00010\rH&¢\u0006\u0002\u0010\u000eJ\u0017\u0010\u0018\u001a\u00028\u00002\b\u0010\u0018\u001a\u0004\u0018\u00010\rH&¢\u0006\u0002\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/apollographql/apollo3/api/MutableExecutionOptions;", "T", "Lcom/apollographql/apollo3/api/ExecutionOptions;", "addExecutionContext", "executionContext", "Lcom/apollographql/apollo3/api/ExecutionContext;", "(Lcom/apollographql/apollo3/api/ExecutionContext;)Ljava/lang/Object;", "addHttpHeader", "name", "", "value", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;", "canBeBatched", "", "(Ljava/lang/Boolean;)Ljava/lang/Object;", "enableAutoPersistedQueries", "httpHeaders", "", "Lcom/apollographql/apollo3/api/http/HttpHeader;", "(Ljava/util/List;)Ljava/lang/Object;", "httpMethod", "Lcom/apollographql/apollo3/api/http/HttpMethod;", "(Lcom/apollographql/apollo3/api/http/HttpMethod;)Ljava/lang/Object;", "sendApqExtensions", "sendDocument", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface MutableExecutionOptions<T> extends ExecutionOptions {
    T addExecutionContext(@NotNull ExecutionContext executionContext);

    T addHttpHeader(@NotNull String str, @NotNull String str2);

    T canBeBatched(@Nullable Boolean bool);

    T enableAutoPersistedQueries(@Nullable Boolean bool);

    T httpHeaders(@Nullable List<HttpHeader> list);

    T httpMethod(@Nullable HttpMethod httpMethod);

    T sendApqExtensions(@Nullable Boolean bool);

    T sendDocument(@Nullable Boolean bool);
}
