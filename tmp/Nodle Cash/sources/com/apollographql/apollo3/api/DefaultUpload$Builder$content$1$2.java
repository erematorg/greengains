package com.apollographql.apollo3.api;

import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import okio.BufferedSink;
import okio.BufferedSource;
import org.jetbrains.annotations.NotNull;

@SourceDebugExtension({"SMAP\nDefaultUpload.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DefaultUpload.kt\ncom/apollographql/apollo3/api/DefaultUpload$Builder$content$1$2\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Okio.kt\nokio/Okio__OkioKt\n*L\n1#1,122:1\n1#2:123\n52#3,18:124\n*S KotlinDebug\n*F\n+ 1 DefaultUpload.kt\ncom/apollographql/apollo3/api/DefaultUpload$Builder$content$1$2\n*L\n52#1:124,18\n*E\n"})
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "sink", "Lokio/BufferedSink;", "invoke"}, k = 3, mv = {1, 5, 1}, xi = 48)
public final class DefaultUpload$Builder$content$1$2 extends Lambda implements Function1<BufferedSink, Unit> {
    final /* synthetic */ Ref.BooleanRef $consumed;
    final /* synthetic */ BufferedSource $content;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DefaultUpload$Builder$content$1$2(Ref.BooleanRef booleanRef, BufferedSource bufferedSource) {
        super(1);
        this.$consumed = booleanRef;
        this.$content = bufferedSource;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((BufferedSink) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull BufferedSink bufferedSink) {
        Long l2;
        Intrinsics.checkNotNullParameter(bufferedSink, "sink");
        if (!this.$consumed.element) {
            BufferedSource bufferedSource = this.$content;
            Throwable th = null;
            try {
                l2 = Long.valueOf(bufferedSink.writeAll(bufferedSource));
            } catch (Throwable th2) {
                th = th2;
                l2 = null;
            }
            if (bufferedSource != null) {
                try {
                    bufferedSource.close();
                } catch (Throwable th3) {
                    if (th == null) {
                        th = th3;
                    } else {
                        ExceptionsKt.addSuppressed(th, th3);
                    }
                }
            }
            if (th == null) {
                Intrinsics.checkNotNull(l2);
                this.$consumed.element = true;
                return;
            }
            throw th;
        }
        throw new IllegalStateException("Apollo: DefaultUpload BufferedSource body can only be read once. If you want to read it several times for logging or other purposes, either use a different kind of body or use your own `Upload` implementation.");
    }
}
