package com.apollographql.apollo3.internal;

import android.support.v4.media.session.PlaybackStateCompat;
import androidx.compose.animation.core.a;
import com.apollographql.apollo3.annotations.ApolloInternal;
import com.apollographql.apollo3.api.http.HttpHeader;
import com.apollographql.apollo3.exception.ApolloException;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Options;
import okio.Source;
import okio.Timeout;
import org.apache.commons.lang3.StringUtils;
import org.apache.xml.serialize.LineSeparator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SourceDebugExtension({"SMAP\nMultipartReader.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MultipartReader.kt\ncom/apollographql/apollo3/internal/MultipartReader\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,231:1\n1#2:232\n*E\n"})
@ApolloInternal
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u001d2\u00060\u0001j\u0002`\u0002:\u0003\u001d\u001e\u001fB\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0019H\u0002J\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cR\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0018\u00010\u0011R\u00020\u0000X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/apollographql/apollo3/internal/MultipartReader;", "Ljava/io/Closeable;", "Lokio/Closeable;", "source", "Lokio/BufferedSource;", "boundary", "", "(Lokio/BufferedSource;Ljava/lang/String;)V", "afterBoundaryOptions", "Lokio/Options;", "getBoundary", "()Ljava/lang/String;", "closed", "", "crlfDashDashBoundary", "Lokio/ByteString;", "currentPart", "Lcom/apollographql/apollo3/internal/MultipartReader$PartSource;", "dashDashBoundary", "noMoreParts", "partCount", "", "close", "", "currentPartBytesRemaining", "", "maxResult", "nextPart", "Lcom/apollographql/apollo3/internal/MultipartReader$Part;", "Companion", "Part", "PartSource", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class MultipartReader implements Closeable {
    @NotNull
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @NotNull
    private final Options afterBoundaryOptions;
    @NotNull
    private final String boundary;
    private boolean closed;
    @NotNull
    private final ByteString crlfDashDashBoundary;
    /* access modifiers changed from: private */
    @Nullable
    public PartSource currentPart;
    @NotNull
    private final ByteString dashDashBoundary;
    private boolean noMoreParts;
    private int partCount;
    /* access modifiers changed from: private */
    @NotNull
    public final BufferedSource source;

    @SourceDebugExtension({"SMAP\nMultipartReader.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MultipartReader.kt\ncom/apollographql/apollo3/internal/MultipartReader$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,231:1\n1#2:232\n*E\n"})
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0002¨\u0006\b"}, d2 = {"Lcom/apollographql/apollo3/internal/MultipartReader$Companion;", "", "()V", "readHeaders", "", "Lcom/apollographql/apollo3/api/http/HttpHeader;", "source", "Lokio/BufferedSource;", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* access modifiers changed from: private */
        public final List<HttpHeader> readHeaders(BufferedSource bufferedSource) {
            ArrayList arrayList = new ArrayList();
            while (true) {
                String readUtf8LineStrict = bufferedSource.readUtf8LineStrict();
                if (readUtf8LineStrict.length() == 0) {
                    return arrayList;
                }
                int A2 = StringsKt__StringsKt.indexOf$default((CharSequence) readUtf8LineStrict, (char) AbstractJsonLexerKt.COLON, 0, false, 6, (Object) null);
                if (A2 != -1) {
                    String substring = readUtf8LineStrict.substring(0, A2);
                    Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
                    String obj = StringsKt__StringsKt.trim((CharSequence) substring).toString();
                    String substring2 = readUtf8LineStrict.substring(A2 + 1);
                    Intrinsics.checkNotNullExpressionValue(substring2, "substring(...)");
                    arrayList.add(new HttpHeader(obj, StringsKt__StringsKt.trim((CharSequence) substring2).toString()));
                } else {
                    throw new IllegalStateException("Unexpected header: ".concat(readUtf8LineStrict).toString());
                }
            }
        }

        private Companion() {
        }
    }

    @ApolloInternal
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00060\u0001j\u0002`\u0002B\u001b\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\r\u001a\u00020\u000eH\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/apollographql/apollo3/internal/MultipartReader$Part;", "Ljava/io/Closeable;", "Lokio/Closeable;", "headers", "", "Lcom/apollographql/apollo3/api/http/HttpHeader;", "body", "Lokio/BufferedSource;", "(Ljava/util/List;Lokio/BufferedSource;)V", "getBody", "()Lokio/BufferedSource;", "getHeaders", "()Ljava/util/List;", "close", "", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Part implements Closeable {
        @NotNull
        private final BufferedSource body;
        @NotNull
        private final List<HttpHeader> headers;

        public Part(@NotNull List<HttpHeader> list, @NotNull BufferedSource bufferedSource) {
            Intrinsics.checkNotNullParameter(list, "headers");
            Intrinsics.checkNotNullParameter(bufferedSource, "body");
            this.headers = list;
            this.body = bufferedSource;
        }

        public void close() {
            this.body.close();
        }

        @NotNull
        public final BufferedSource getBody() {
            return this.body;
        }

        @NotNull
        public final List<HttpHeader> getHeaders() {
            return this.headers;
        }
    }

    @SourceDebugExtension({"SMAP\nMultipartReader.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MultipartReader.kt\ncom/apollographql/apollo3/internal/MultipartReader$PartSource\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,231:1\n1#2:232\n*E\n"})
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006H\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/apollographql/apollo3/internal/MultipartReader$PartSource;", "Lokio/Source;", "(Lcom/apollographql/apollo3/internal/MultipartReader;)V", "close", "", "read", "", "sink", "Lokio/Buffer;", "byteCount", "timeout", "Lokio/Timeout;", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public final class PartSource implements Source {
        public PartSource() {
        }

        public void close() {
            if (Intrinsics.areEqual((Object) MultipartReader.this.currentPart, (Object) this)) {
                MultipartReader.this.currentPart = null;
            }
        }

        public long read(@NotNull Buffer buffer, long j2) {
            Intrinsics.checkNotNullParameter(buffer, "sink");
            if (j2 < 0) {
                throw new IllegalArgumentException(a.s("byteCount < 0: ", j2).toString());
            } else if (Intrinsics.areEqual((Object) MultipartReader.this.currentPart, (Object) this)) {
                long access$currentPartBytesRemaining = MultipartReader.this.currentPartBytesRemaining(j2);
                if (access$currentPartBytesRemaining == 0) {
                    return -1;
                }
                return MultipartReader.this.source.read(buffer, access$currentPartBytesRemaining);
            } else {
                throw new IllegalStateException("closed");
            }
        }

        @NotNull
        public Timeout timeout() {
            return MultipartReader.this.source.timeout();
        }
    }

    public MultipartReader(@NotNull BufferedSource bufferedSource, @NotNull String str) {
        Intrinsics.checkNotNullParameter(bufferedSource, "source");
        Intrinsics.checkNotNullParameter(str, "boundary");
        this.source = bufferedSource;
        this.boundary = str;
        this.dashDashBoundary = new Buffer().writeUtf8("--").writeUtf8(str).readByteString();
        this.crlfDashDashBoundary = new Buffer().writeUtf8("\r\n--").writeUtf8(str).readByteString();
        Options.Companion companion = Options.Companion;
        ByteString.Companion companion2 = ByteString.Companion;
        this.afterBoundaryOptions = companion.of(companion2.encodeUtf8("\r\n--" + str + "--"), companion2.encodeUtf8(LineSeparator.Windows), companion2.encodeUtf8("--"), companion2.encodeUtf8(StringUtils.SPACE), companion2.encodeUtf8("\t"));
    }

    /* access modifiers changed from: private */
    public final long currentPartBytesRemaining(long j2) {
        this.source.require((long) this.crlfDashDashBoundary.size());
        long indexOf = this.source.getBuffer().indexOf(this.crlfDashDashBoundary);
        return indexOf == -1 ? Math.min(j2, (this.source.getBuffer().size() - ((long) this.crlfDashDashBoundary.size())) + 1) : Math.min(j2, indexOf);
    }

    public void close() {
        if (!this.closed) {
            this.closed = true;
            this.currentPart = null;
            this.source.close();
        }
    }

    @NotNull
    public final String getBoundary() {
        return this.boundary;
    }

    @Nullable
    public final Part nextPart() {
        if (this.closed) {
            throw new IllegalStateException("closed");
        } else if (this.noMoreParts) {
            return null;
        } else {
            if (this.partCount != 0 || !this.source.rangeEquals(0, this.dashDashBoundary)) {
                while (true) {
                    long currentPartBytesRemaining = currentPartBytesRemaining(PlaybackStateCompat.ACTION_PLAY_FROM_URI);
                    if (currentPartBytesRemaining == 0) {
                        break;
                    }
                    this.source.skip(currentPartBytesRemaining);
                }
                this.source.skip((long) this.crlfDashDashBoundary.size());
            } else {
                this.source.skip((long) this.dashDashBoundary.size());
            }
            boolean z2 = false;
            while (true) {
                int select = this.source.select(this.afterBoundaryOptions);
                if (select == -1) {
                    throw new ApolloException("unexpected characters after boundary", (Throwable) null, 2, (DefaultConstructorMarker) null);
                } else if (select != 0) {
                    if (select == 1) {
                        this.partCount++;
                        List access$readHeaders = Companion.readHeaders(this.source);
                        PartSource partSource = new PartSource();
                        this.currentPart = partSource;
                        return new Part(access$readHeaders, Okio.buffer((Source) partSource));
                    } else if (select != 2) {
                        if (select == 3 || select == 4) {
                            z2 = true;
                        }
                    } else if (z2) {
                        throw new ApolloException("unexpected characters after boundary", (Throwable) null, 2, (DefaultConstructorMarker) null);
                    } else if (this.partCount != 0) {
                        this.noMoreParts = true;
                        return null;
                    } else {
                        throw new ApolloException("expected at least 1 part", (Throwable) null, 2, (DefaultConstructorMarker) null);
                    }
                } else if (this.partCount != 0) {
                    this.noMoreParts = true;
                    return null;
                } else {
                    throw new ApolloException("expected at least 1 part", (Throwable) null, 2, (DefaultConstructorMarker) null);
                }
            }
        }
    }
}
