package com.apollographql.apollo3.exception;

import com.apollographql.apollo3.api.http.HttpHeader;
import com.reown.android.push.notifications.PushMessagingService;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.BufferedSource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\rR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lcom/apollographql/apollo3/exception/ApolloHttpException;", "Lcom/apollographql/apollo3/exception/ApolloException;", "statusCode", "", "headers", "", "Lcom/apollographql/apollo3/api/http/HttpHeader;", "body", "Lokio/BufferedSource;", "message", "", "cause", "", "(ILjava/util/List;Lokio/BufferedSource;Ljava/lang/String;Ljava/lang/Throwable;)V", "getBody", "()Lokio/BufferedSource;", "getHeaders", "()Ljava/util/List;", "getStatusCode", "()I", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class ApolloHttpException extends ApolloException {
    @Nullable
    private final BufferedSource body;
    @NotNull
    private final List<HttpHeader> headers;
    private final int statusCode;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ApolloHttpException(int i3, List list, BufferedSource bufferedSource, String str, Throwable th, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(i3, list, bufferedSource, str, (i4 & 16) != 0 ? null : th);
    }

    @Nullable
    public final BufferedSource getBody() {
        return this.body;
    }

    @NotNull
    public final List<HttpHeader> getHeaders() {
        return this.headers;
    }

    public final int getStatusCode() {
        return this.statusCode;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ApolloHttpException(int i3, @NotNull List<HttpHeader> list, @Nullable BufferedSource bufferedSource, @NotNull String str, @Nullable Throwable th) {
        super(str, th);
        Intrinsics.checkNotNullParameter(list, "headers");
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
        this.statusCode = i3;
        this.headers = list;
        this.body = bufferedSource;
    }
}
