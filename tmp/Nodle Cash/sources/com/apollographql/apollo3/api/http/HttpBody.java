package com.apollographql.apollo3.api.http;

import kotlin.Metadata;
import okio.BufferedSink;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u000e"}, d2 = {"Lcom/apollographql/apollo3/api/http/HttpBody;", "", "contentLength", "", "getContentLength", "()J", "contentType", "", "getContentType", "()Ljava/lang/String;", "writeTo", "", "bufferedSink", "Lokio/BufferedSink;", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface HttpBody {
    long getContentLength();

    @NotNull
    String getContentType();

    void writeTo(@NotNull BufferedSink bufferedSink);
}
