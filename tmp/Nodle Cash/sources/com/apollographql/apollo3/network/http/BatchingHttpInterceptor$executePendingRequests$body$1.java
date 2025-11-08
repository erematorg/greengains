package com.apollographql.apollo3.network.http;

import com.apollographql.apollo3.api.http.HttpBody;
import com.apollographql.apollo3.api.json.BufferedSinkJsonWriter;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okio.Buffer;
import okio.BufferedSink;
import org.jetbrains.annotations.NotNull;

@SourceDebugExtension({"SMAP\nBatchingHttpInterceptor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BatchingHttpInterceptor.kt\ncom/apollographql/apollo3/network/http/BatchingHttpInterceptor$executePendingRequests$body$1\n+ 2 JsonWriters.kt\ncom/apollographql/apollo3/api/json/-JsonWriters\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,245:1\n57#2,6:246\n63#2,2:254\n1855#3,2:252\n*S KotlinDebug\n*F\n+ 1 BatchingHttpInterceptor.kt\ncom/apollographql/apollo3/network/http/BatchingHttpInterceptor$executePendingRequests$body$1\n*L\n144#1:246,6\n144#1:254,2\n146#1:252,2\n*E\n"})
@Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003XD¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u0007XD¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000e"}, d2 = {"com/apollographql/apollo3/network/http/BatchingHttpInterceptor$executePendingRequests$body$1", "Lcom/apollographql/apollo3/api/http/HttpBody;", "contentLength", "", "getContentLength", "()J", "contentType", "", "getContentType", "()Ljava/lang/String;", "writeTo", "", "bufferedSink", "Lokio/BufferedSink;", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class BatchingHttpInterceptor$executePendingRequests$body$1 implements HttpBody {
    final /* synthetic */ List<HttpBody> $allBodies;
    private final long contentLength = -1;
    @NotNull
    private final String contentType = "application/json";

    public BatchingHttpInterceptor$executePendingRequests$body$1(List<? extends HttpBody> list) {
        this.$allBodies = list;
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
        BufferedSinkJsonWriter bufferedSinkJsonWriter = new BufferedSinkJsonWriter(bufferedSink, (String) null, 2, (DefaultConstructorMarker) null);
        List<HttpBody> list = this.$allBodies;
        bufferedSinkJsonWriter.beginArray();
        for (HttpBody writeTo : list) {
            Buffer buffer = new Buffer();
            writeTo.writeTo(buffer);
            bufferedSinkJsonWriter.jsonValue(buffer.readUtf8());
        }
        bufferedSinkJsonWriter.endArray();
    }
}
