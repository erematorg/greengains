package com.apollographql.apollo3.api.http;

import com.apollographql.apollo3.api.Upload;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.SourceDebugExtension;
import okio.BufferedSink;
import okio.Okio;
import okio.Sink;
import org.jetbrains.annotations.NotNull;

@SourceDebugExtension({"SMAP\nDefaultHttpRequestComposer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DefaultHttpRequestComposer.kt\ncom/apollographql/apollo3/api/http/UploadsHttpBody$contentLength$2\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,384:1\n1#2:385\n*E\n"})
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Long;"}, k = 3, mv = {1, 5, 1}, xi = 48)
public final class UploadsHttpBody$contentLength$2 extends Lambda implements Function0<Long> {
    final /* synthetic */ UploadsHttpBody this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public UploadsHttpBody$contentLength$2(UploadsHttpBody uploadsHttpBody) {
        super(0);
        this.this$0 = uploadsHttpBody;
    }

    @NotNull
    public final Long invoke() {
        CountingSink countingSink = new CountingSink(Okio.blackhole());
        BufferedSink buffer = Okio.buffer((Sink) countingSink);
        this.this$0.writeBoundaries(buffer, false);
        buffer.flush();
        long bytesWritten = countingSink.getBytesWritten();
        long j2 = 0;
        for (Upload contentLength : this.this$0.uploads.values()) {
            j2 += contentLength.getContentLength();
        }
        return Long.valueOf(bytesWritten + j2);
    }
}
