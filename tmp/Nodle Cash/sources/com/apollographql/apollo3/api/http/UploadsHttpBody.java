package com.apollographql.apollo3.api.http;

import androidx.browser.trusted.c;
import com.apollographql.apollo3.annotations.ApolloInternal;
import com.apollographql.apollo3.api.Upload;
import com.apollographql.apollo3.api.json.BufferedSinkJsonWriter;
import com.apollographql.apollo3.api.json.JsonWriters;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import org.apache.xml.serialize.LineSeparator;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B!\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u001c\u0010\u0013\u001a\u00020\u00072\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003H\u0002J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0014\u0010\u0018\u001a\u00020\u0015*\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u001aH\u0002R\u000e\u0010\t\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\u00020\u000b8VX\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0010\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/apollographql/apollo3/api/http/UploadsHttpBody;", "Lcom/apollographql/apollo3/api/http/HttpBody;", "uploads", "", "", "Lcom/apollographql/apollo3/api/Upload;", "operationByteString", "Lokio/ByteString;", "(Ljava/util/Map;Lokio/ByteString;)V", "boundary", "contentLength", "", "getContentLength", "()J", "contentLength$delegate", "Lkotlin/Lazy;", "contentType", "getContentType", "()Ljava/lang/String;", "buildUploadMap", "writeTo", "", "bufferedSink", "Lokio/BufferedSink;", "writeBoundaries", "writeUploadContents", "", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
@SourceDebugExtension({"SMAP\nDefaultHttpRequestComposer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DefaultHttpRequestComposer.kt\ncom/apollographql/apollo3/api/http/UploadsHttpBody\n+ 2 uuid.kt\ncom/benasher44/uuid/UuidKt\n+ 3 JsonWriters.kt\ncom/apollographql/apollo3/api/json/-JsonWriters\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,384:1\n96#2:385\n79#3,6:386\n85#3:397\n1559#4:392\n1590#4,4:393\n1864#4,3:398\n*S KotlinDebug\n*F\n+ 1 DefaultHttpRequestComposer.kt\ncom/apollographql/apollo3/api/http/UploadsHttpBody\n*L\n311#1:385\n328#1:386,6\n328#1:397\n330#1:392\n330#1:393,4\n352#1:398,3\n*E\n"})
@ApolloInternal
public final class UploadsHttpBody implements HttpBody {
    @NotNull
    private final String boundary;
    @NotNull
    private final Lazy contentLength$delegate = LazyKt.lazy(new UploadsHttpBody$contentLength$2(this));
    @NotNull
    private final String contentType;
    @NotNull
    private final ByteString operationByteString;
    /* access modifiers changed from: private */
    @NotNull
    public final Map<String, Upload> uploads;

    public UploadsHttpBody(@NotNull Map<String, ? extends Upload> map, @NotNull ByteString byteString) {
        Intrinsics.checkNotNullParameter(map, "uploads");
        Intrinsics.checkNotNullParameter(byteString, "operationByteString");
        this.uploads = map;
        this.operationByteString = byteString;
        UUID randomUUID = UUID.randomUUID();
        Intrinsics.checkNotNullExpressionValue(randomUUID, "randomUUID()");
        String uuid = randomUUID.toString();
        Intrinsics.checkNotNullExpressionValue(uuid, "uuid4().toString()");
        this.boundary = uuid;
        this.contentType = c.a("multipart/form-data; boundary=", uuid);
    }

    private final ByteString buildUploadMap(Map<String, ? extends Upload> map) {
        Buffer buffer = new Buffer();
        BufferedSinkJsonWriter bufferedSinkJsonWriter = new BufferedSinkJsonWriter(buffer, (String) null);
        Iterable entrySet = map.entrySet();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(entrySet, 10));
        int i3 = 0;
        for (Object next : entrySet) {
            int i4 = i3 + 1;
            if (i3 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            arrayList.add(TuplesKt.to(String.valueOf(i3), CollectionsKt.listOf(((Map.Entry) next).getKey())));
            i3 = i4;
        }
        JsonWriters.writeAny(bufferedSinkJsonWriter, MapsKt.toMap(arrayList));
        return buffer.readByteString();
    }

    /* access modifiers changed from: private */
    public final void writeBoundaries(BufferedSink bufferedSink, boolean z2) {
        bufferedSink.writeUtf8("--" + this.boundary + LineSeparator.Windows);
        bufferedSink.writeUtf8("Content-Disposition: form-data; name=\"operations\"\r\n");
        bufferedSink.writeUtf8("Content-Type: application/json\r\n");
        bufferedSink.writeUtf8("Content-Length: " + this.operationByteString.size() + LineSeparator.Windows);
        bufferedSink.writeUtf8(LineSeparator.Windows);
        bufferedSink.write(this.operationByteString);
        ByteString buildUploadMap = buildUploadMap(this.uploads);
        bufferedSink.writeUtf8("\r\n--" + this.boundary + LineSeparator.Windows);
        bufferedSink.writeUtf8("Content-Disposition: form-data; name=\"map\"\r\n");
        bufferedSink.writeUtf8("Content-Type: application/json\r\n");
        bufferedSink.writeUtf8("Content-Length: " + buildUploadMap.size() + LineSeparator.Windows);
        bufferedSink.writeUtf8(LineSeparator.Windows);
        bufferedSink.write(buildUploadMap);
        int i3 = 0;
        for (Object next : this.uploads.values()) {
            int i4 = i3 + 1;
            if (i3 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            Upload upload = (Upload) next;
            bufferedSink.writeUtf8("\r\n--" + this.boundary + LineSeparator.Windows);
            bufferedSink.writeUtf8("Content-Disposition: form-data; name=\"" + i3 + '\"');
            if (upload.getFileName() != null) {
                bufferedSink.writeUtf8("; filename=\"" + upload.getFileName() + '\"');
            }
            bufferedSink.writeUtf8(LineSeparator.Windows);
            bufferedSink.writeUtf8("Content-Type: " + upload.getContentType() + LineSeparator.Windows);
            long contentLength = upload.getContentLength();
            if (contentLength != -1) {
                bufferedSink.writeUtf8("Content-Length: " + contentLength + LineSeparator.Windows);
            }
            bufferedSink.writeUtf8(LineSeparator.Windows);
            if (z2) {
                upload.writeTo(bufferedSink);
            }
            i3 = i4;
        }
        bufferedSink.writeUtf8("\r\n--" + this.boundary + "--\r\n");
    }

    public long getContentLength() {
        return ((Number) this.contentLength$delegate.getValue()).longValue();
    }

    @NotNull
    public String getContentType() {
        return this.contentType;
    }

    public void writeTo(@NotNull BufferedSink bufferedSink) {
        Intrinsics.checkNotNullParameter(bufferedSink, "bufferedSink");
        writeBoundaries(bufferedSink, true);
    }
}
