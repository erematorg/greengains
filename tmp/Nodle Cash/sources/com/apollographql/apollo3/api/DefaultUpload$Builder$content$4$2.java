package com.apollographql.apollo3.api;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import okio.BufferedSink;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "sink", "Lokio/BufferedSink;", "invoke"}, k = 3, mv = {1, 5, 1}, xi = 48)
public final class DefaultUpload$Builder$content$4$2 extends Lambda implements Function1<BufferedSink, Unit> {
    final /* synthetic */ ByteString $byteString;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DefaultUpload$Builder$content$4$2(ByteString byteString) {
        super(1);
        this.$byteString = byteString;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((BufferedSink) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull BufferedSink bufferedSink) {
        Intrinsics.checkNotNullParameter(bufferedSink, "sink");
        bufferedSink.write(this.$byteString);
    }
}
