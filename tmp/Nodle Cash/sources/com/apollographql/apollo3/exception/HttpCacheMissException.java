package com.apollographql.apollo3.exception;

import com.reown.android.push.notifications.PushMessagingService;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u0006¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/apollographql/apollo3/exception/HttpCacheMissException;", "Lcom/apollographql/apollo3/exception/ApolloException;", "message", "", "cause", "Ljava/lang/Exception;", "Lkotlin/Exception;", "(Ljava/lang/String;Ljava/lang/Exception;)V", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class HttpCacheMissException extends ApolloException {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HttpCacheMissException(@NotNull String str, @Nullable Exception exc) {
        super(str, exc);
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ HttpCacheMissException(String str, Exception exc, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i3 & 2) != 0 ? null : exc);
    }
}
