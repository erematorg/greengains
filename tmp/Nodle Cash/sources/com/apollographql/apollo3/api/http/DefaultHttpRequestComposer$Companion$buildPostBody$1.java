package com.apollographql.apollo3.api.http;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.BufferedSink;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u0007XD¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000e"}, d2 = {"com/apollographql/apollo3/api/http/DefaultHttpRequestComposer$Companion$buildPostBody$1", "Lcom/apollographql/apollo3/api/http/HttpBody;", "contentLength", "", "getContentLength", "()J", "contentType", "", "getContentType", "()Ljava/lang/String;", "writeTo", "", "bufferedSink", "Lokio/BufferedSink;", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class DefaultHttpRequestComposer$Companion$buildPostBody$1 implements HttpBody {
    final /* synthetic */ ByteString $operationByteString;
    private final long contentLength;
    @NotNull
    private final String contentType = "application/json";

    public DefaultHttpRequestComposer$Companion$buildPostBody$1(ByteString byteString) {
        this.$operationByteString = byteString;
        this.contentLength = (long) byteString.size();
    }

    public long getContentLength() {
        return this.contentLength;
    }

    @NotNull
    public String getContentType() {
        return this.contentType;
    }

    public void writeTo(@NotNull BufferedSink bufferedSink) {
        Intrinsics.checkNotNullParameter(bufferedSink, "bufferedSink");
        bufferedSink.write(this.$operationByteString);
    }
}
