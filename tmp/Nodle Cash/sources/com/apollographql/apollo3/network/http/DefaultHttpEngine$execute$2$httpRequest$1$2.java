package com.apollographql.apollo3.network.http;

import com.apollographql.apollo3.api.http.HttpBody;
import com.apollographql.apollo3.api.http.UploadsHttpBody;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"com/apollographql/apollo3/network/http/DefaultHttpEngine$execute$2$httpRequest$1$2", "Lokhttp3/RequestBody;", "contentLength", "", "contentType", "Lokhttp3/MediaType;", "isOneShot", "", "writeTo", "", "sink", "Lokio/BufferedSink;", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class DefaultHttpEngine$execute$2$httpRequest$1$2 extends RequestBody {
    final /* synthetic */ HttpBody $body;

    public DefaultHttpEngine$execute$2$httpRequest$1$2(HttpBody httpBody) {
        this.$body = httpBody;
    }

    public long contentLength() {
        return this.$body.getContentLength();
    }

    @NotNull
    public MediaType contentType() {
        return MediaType.Companion.get(this.$body.getContentType());
    }

    public boolean isOneShot() {
        return this.$body instanceof UploadsHttpBody;
    }

    public void writeTo(@NotNull BufferedSink bufferedSink) {
        Intrinsics.checkNotNullParameter(bufferedSink, "sink");
        this.$body.writeTo(bufferedSink);
    }
}
