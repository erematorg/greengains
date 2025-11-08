package com.apollographql.apollo3.api.http;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.BufferedSink;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\n8VX\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0013"}, d2 = {"Lcom/apollographql/apollo3/api/http/ByteStringHttpBody;", "Lcom/apollographql/apollo3/api/http/HttpBody;", "contentType", "", "string", "(Ljava/lang/String;Ljava/lang/String;)V", "byteString", "Lokio/ByteString;", "(Ljava/lang/String;Lokio/ByteString;)V", "contentLength", "", "getContentLength", "()J", "getContentType", "()Ljava/lang/String;", "writeTo", "", "bufferedSink", "Lokio/BufferedSink;", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class ByteStringHttpBody implements HttpBody {
    @NotNull
    private final ByteString byteString;
    @NotNull
    private final String contentType;

    public ByteStringHttpBody(@NotNull String str, @NotNull ByteString byteString2) {
        Intrinsics.checkNotNullParameter(str, "contentType");
        Intrinsics.checkNotNullParameter(byteString2, "byteString");
        this.contentType = str;
        this.byteString = byteString2;
    }

    public long getContentLength() {
        return (long) this.byteString.size();
    }

    @NotNull
    public String getContentType() {
        return this.contentType;
    }

    public void writeTo(@NotNull BufferedSink bufferedSink) {
        Intrinsics.checkNotNullParameter(bufferedSink, "bufferedSink");
        bufferedSink.write(this.byteString);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ByteStringHttpBody(@NotNull String str, @NotNull String str2) {
        this(str, ByteString.Companion.encodeUtf8(str2));
        Intrinsics.checkNotNullParameter(str, "contentType");
        Intrinsics.checkNotNullParameter(str2, "string");
    }
}
