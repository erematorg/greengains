package com.apollographql.apollo3.api.http;

import com.apollographql.apollo3.api.ApolloRequest;
import com.apollographql.apollo3.api.CustomScalarAdapters;
import com.apollographql.apollo3.api.Operation;
import com.apollographql.apollo3.api.Subscription;
import com.apollographql.apollo3.api.Upload;
import com.apollographql.apollo3.api.http.HttpRequest;
import com.apollographql.apollo3.api.http.internal.UrlEncodeKt;
import com.apollographql.apollo3.api.json.BufferedSinkJsonWriter;
import com.apollographql.apollo3.api.json.JsonWriter;
import com.apollographql.apollo3.api.json.MapJsonWriter;
import com.apollographql.apollo3.api.json.internal.FileUploadAwareJsonWriter;
import com.google.android.gms.actions.SearchIntents;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Typography;
import okio.Buffer;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.signature.SignatureVisitor;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\u0005\u001a\u00020\u0006\"\b\b\u0000\u0010\u0007*\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00070\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/apollographql/apollo3/api/http/DefaultHttpRequestComposer;", "Lcom/apollographql/apollo3/api/http/HttpRequestComposer;", "serverUrl", "", "(Ljava/lang/String;)V", "compose", "Lcom/apollographql/apollo3/api/http/HttpRequest;", "D", "Lcom/apollographql/apollo3/api/Operation$Data;", "apolloRequest", "Lcom/apollographql/apollo3/api/ApolloRequest;", "Companion", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class DefaultHttpRequestComposer implements HttpRequestComposer {
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @NotNull
    private static final String HEADER_ACCEPT_NAME = "Accept";
    @NotNull
    private static final String HEADER_ACCEPT_VALUE_DEFER = "multipart/mixed; deferSpec=20220824, application/json";
    @NotNull
    private static final String HEADER_ACCEPT_VALUE_MULTIPART = "multipart/mixed; boundary=\"graphql\"; subscriptionSpec=1.0, application/json";
    @NotNull
    public static final String HEADER_APOLLO_OPERATION_ID = "X-APOLLO-OPERATION-ID";
    @NotNull
    public static final String HEADER_APOLLO_OPERATION_NAME = "X-APOLLO-OPERATION-NAME";
    @NotNull
    private final String serverUrl;

    @SourceDebugExtension({"SMAP\nDefaultHttpRequestComposer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DefaultHttpRequestComposer.kt\ncom/apollographql/apollo3/api/http/DefaultHttpRequestComposer$Companion\n+ 2 JsonWriters.kt\ncom/apollographql/apollo3/api/json/-JsonWriters\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,384:1\n46#2,6:385\n46#2,8:391\n52#2,2:399\n67#2,7:401\n46#2,8:408\n74#2:416\n67#2,7:417\n46#2,6:424\n46#2,8:430\n52#2,2:438\n74#2:440\n79#2,7:443\n78#2,8:450\n90#2,7:458\n1855#3,2:441\n*S KotlinDebug\n*F\n+ 1 DefaultHttpRequestComposer.kt\ncom/apollographql/apollo3/api/http/DefaultHttpRequestComposer$Companion\n*L\n119#1:385,6\n125#1:391,8\n119#1:399,2\n183#1:401,7\n185#1:408,8\n183#1:416\n200#1:417,7\n201#1:424,6\n203#1:430,8\n201#1:438,2\n200#1:440\n253#1:443,7\n283#1:450,8\n299#1:458,7\n221#1:441,2\n*E\n"})
    @Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J)\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b\r2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J@\u0010\u0011\u001a\u00020\u0004\"\b\b\u0000\u0010\u0012*\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00042\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00120\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u0010H\u0002J6\u0010\u001a\u001a\u00020\u001b\"\b\b\u0000\u0010\u0012*\u00020\u00132\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00120\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u0010J:\u0010\u001d\u001a\u00020\u001e\"\b\b\u0000\u0010\u0012*\u00020\u00132\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00120\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u00102\b\u0010\u001f\u001a\u0004\u0018\u00010\u0004H\u0007JI\u0010\u001d\u001a\u00020\u001e\"\b\b\u0000\u0010\u0012*\u00020\u00132\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00120\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u001f\u001a\u0004\u0018\u00010\u00042\u0017\u0010 \u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b\rJD\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\"\"\b\b\u0000\u0010\u0012*\u00020\u00132\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00120\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u0010H\u0002J,\u0010#\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\"\"\b\b\u0000\u0010\u0012*\u00020\u00132\f\u0010$\u001a\b\u0012\u0004\u0012\u0002H\u00120%JN\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020'0\"\"\b\b\u0000\u0010\u0012*\u00020\u00132\u0006\u0010(\u001a\u00020\u000b2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00120\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u001f\u001a\u0004\u0018\u00010\u0004H\u0002J_\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020'0\"\"\b\b\u0000\u0010\u0012*\u00020\u00132\u0006\u0010(\u001a\u00020\u000b2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00120\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u001f\u001a\u0004\u0018\u00010\u00042\u0017\u0010 \u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b\rH\u0002J\u001e\u0010)\u001a\u00020\u0004*\u00020\u00042\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\"R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/apollographql/apollo3/api/http/DefaultHttpRequestComposer$Companion;", "", "()V", "HEADER_ACCEPT_NAME", "", "HEADER_ACCEPT_VALUE_DEFER", "HEADER_ACCEPT_VALUE_MULTIPART", "HEADER_APOLLO_OPERATION_ID", "HEADER_APOLLO_OPERATION_NAME", "apqExtensionsWriter", "Lkotlin/Function1;", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "", "Lkotlin/ExtensionFunctionType;", "id", "sendApqExtensions", "", "buildGetUrl", "D", "Lcom/apollographql/apollo3/api/Operation$Data;", "serverUrl", "operation", "Lcom/apollographql/apollo3/api/Operation;", "customScalarAdapters", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "sendDocument", "buildParamsMap", "Lokio/ByteString;", "autoPersistQueries", "buildPostBody", "Lcom/apollographql/apollo3/api/http/HttpBody;", "query", "extensionsWriter", "composeGetParams", "", "composePayload", "apolloRequest", "Lcom/apollographql/apollo3/api/ApolloRequest;", "composePostParams", "Lcom/apollographql/apollo3/api/Upload;", "writer", "appendQueryParameters", "parameters", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final Function1<JsonWriter, Unit> apqExtensionsWriter(String str, boolean z2) {
            return new DefaultHttpRequestComposer$Companion$apqExtensionsWriter$1(z2, str);
        }

        /* access modifiers changed from: private */
        public final <D extends Operation.Data> String buildGetUrl(String str, Operation<D> operation, CustomScalarAdapters customScalarAdapters, boolean z2, boolean z3) {
            return appendQueryParameters(str, composeGetParams(operation, customScalarAdapters, z2, z3));
        }

        private final <D extends Operation.Data> Map<String, String> composeGetParams(Operation<D> operation, CustomScalarAdapters customScalarAdapters, boolean z2, boolean z3) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put("operationName", operation.name());
            Buffer buffer = new Buffer();
            FileUploadAwareJsonWriter fileUploadAwareJsonWriter = new FileUploadAwareJsonWriter(new BufferedSinkJsonWriter(buffer, (String) null));
            fileUploadAwareJsonWriter.beginObject();
            operation.serializeVariables(fileUploadAwareJsonWriter, customScalarAdapters);
            fileUploadAwareJsonWriter.endObject();
            if (fileUploadAwareJsonWriter.collectedUploads().isEmpty()) {
                linkedHashMap.put("variables", buffer.readUtf8());
                if (z3) {
                    linkedHashMap.put(SearchIntents.EXTRA_QUERY, operation.document());
                }
                if (z2) {
                    Buffer buffer2 = new Buffer();
                    BufferedSinkJsonWriter bufferedSinkJsonWriter = new BufferedSinkJsonWriter(buffer2, (String) null);
                    bufferedSinkJsonWriter.beginObject();
                    bufferedSinkJsonWriter.name("persistedQuery");
                    bufferedSinkJsonWriter.beginObject();
                    bufferedSinkJsonWriter.name("version").value(1);
                    bufferedSinkJsonWriter.name("sha256Hash").value(operation.id());
                    bufferedSinkJsonWriter.endObject();
                    bufferedSinkJsonWriter.endObject();
                    linkedHashMap.put("extensions", buffer2.readUtf8());
                }
                return linkedHashMap;
            }
            throw new IllegalStateException("FileUpload and Http GET are not supported at the same time");
        }

        /* access modifiers changed from: private */
        public final <D extends Operation.Data> Map<String, Upload> composePostParams(JsonWriter jsonWriter, Operation<D> operation, CustomScalarAdapters customScalarAdapters, boolean z2, String str) {
            return composePostParams(jsonWriter, operation, customScalarAdapters, str, (Function1<? super JsonWriter, Unit>) apqExtensionsWriter(operation.id(), z2));
        }

        @NotNull
        public final String appendQueryParameters(@NotNull String str, @NotNull Map<String, String> map) {
            Intrinsics.checkNotNullParameter(str, "<this>");
            Intrinsics.checkNotNullParameter(map, "parameters");
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            boolean s3 = StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) "?", false, 2, (Object) null);
            for (Map.Entry entry : map.entrySet()) {
                if (s3) {
                    sb.append(Typography.amp);
                } else {
                    sb.append('?');
                    s3 = true;
                }
                sb.append(UrlEncodeKt.urlEncode((String) entry.getKey()));
                sb.append(SignatureVisitor.INSTANCEOF);
                sb.append(UrlEncodeKt.urlEncode((String) entry.getValue()));
            }
            String sb2 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
            return sb2;
        }

        @NotNull
        public final <D extends Operation.Data> ByteString buildParamsMap(@NotNull Operation<D> operation, @NotNull CustomScalarAdapters customScalarAdapters, boolean z2, boolean z3) {
            Intrinsics.checkNotNullParameter(operation, "operation");
            Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
            Buffer buffer = new Buffer();
            Map unused = DefaultHttpRequestComposer.Companion.composePostParams((JsonWriter) new BufferedSinkJsonWriter(buffer, (String) null), operation, customScalarAdapters, z2, z3 ? operation.document() : null);
            return buffer.readByteString();
        }

        @NotNull
        @Deprecated(message = "Use buildPostBody(operation, customScalarADapters, query, extensionsWriter) instead")
        public final <D extends Operation.Data> HttpBody buildPostBody(@NotNull Operation<D> operation, @NotNull CustomScalarAdapters customScalarAdapters, boolean z2, @Nullable String str) {
            Intrinsics.checkNotNullParameter(operation, "operation");
            Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
            return buildPostBody(operation, customScalarAdapters, str, (Function1<? super JsonWriter, Unit>) apqExtensionsWriter(operation.id(), z2));
        }

        @NotNull
        public final <D extends Operation.Data> Map<String, Object> composePayload(@NotNull ApolloRequest<D> apolloRequest) {
            Intrinsics.checkNotNullParameter(apolloRequest, "apolloRequest");
            Operation<D> operation = apolloRequest.getOperation();
            Boolean sendApqExtensions = apolloRequest.getSendApqExtensions();
            boolean booleanValue = sendApqExtensions != null ? sendApqExtensions.booleanValue() : false;
            Boolean sendDocument = apolloRequest.getSendDocument();
            boolean booleanValue2 = sendDocument != null ? sendDocument.booleanValue() : true;
            CustomScalarAdapters customScalarAdapters = (CustomScalarAdapters) apolloRequest.getExecutionContext().get(CustomScalarAdapters.Key);
            if (customScalarAdapters != null) {
                String document = booleanValue2 ? operation.document() : null;
                MapJsonWriter mapJsonWriter = new MapJsonWriter();
                Map unused = DefaultHttpRequestComposer.Companion.composePostParams((JsonWriter) mapJsonWriter, operation, customScalarAdapters, booleanValue, document);
                Object root = mapJsonWriter.root();
                Intrinsics.checkNotNull(root, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Any?>");
                return (Map) root;
            }
            throw new IllegalStateException("Cannot find a ResponseAdapterCache");
        }

        private Companion() {
        }

        @NotNull
        public final <D extends Operation.Data> HttpBody buildPostBody(@NotNull Operation<D> operation, @NotNull CustomScalarAdapters customScalarAdapters, @Nullable String str, @NotNull Function1<? super JsonWriter, Unit> function1) {
            Intrinsics.checkNotNullParameter(operation, "operation");
            Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
            Intrinsics.checkNotNullParameter(function1, "extensionsWriter");
            Buffer buffer = new Buffer();
            Map access$composePostParams = DefaultHttpRequestComposer.Companion.composePostParams((JsonWriter) new BufferedSinkJsonWriter(buffer, (String) null), operation, customScalarAdapters, str, function1);
            ByteString readByteString = buffer.readByteString();
            if (access$composePostParams.isEmpty()) {
                return new DefaultHttpRequestComposer$Companion$buildPostBody$1(readByteString);
            }
            return new UploadsHttpBody(access$composePostParams, readByteString);
        }

        /* access modifiers changed from: private */
        public final <D extends Operation.Data> Map<String, Upload> composePostParams(JsonWriter jsonWriter, Operation<D> operation, CustomScalarAdapters customScalarAdapters, String str, Function1<? super JsonWriter, Unit> function1) {
            jsonWriter.beginObject();
            jsonWriter.name("operationName");
            jsonWriter.value(operation.name());
            jsonWriter.name("variables");
            FileUploadAwareJsonWriter fileUploadAwareJsonWriter = new FileUploadAwareJsonWriter(jsonWriter);
            fileUploadAwareJsonWriter.beginObject();
            operation.serializeVariables(fileUploadAwareJsonWriter, customScalarAdapters);
            fileUploadAwareJsonWriter.endObject();
            Map<String, Upload> collectedUploads = fileUploadAwareJsonWriter.collectedUploads();
            if (str != null) {
                jsonWriter.name(SearchIntents.EXTRA_QUERY);
                jsonWriter.value(str);
            }
            function1.invoke(jsonWriter);
            jsonWriter.endObject();
            return collectedUploads;
        }
    }

    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                com.apollographql.apollo3.api.http.HttpMethod[] r0 = com.apollographql.apollo3.api.http.HttpMethod.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.apollographql.apollo3.api.http.HttpMethod r1 = com.apollographql.apollo3.api.http.HttpMethod.Get     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.apollographql.apollo3.api.http.HttpMethod r1 = com.apollographql.apollo3.api.http.HttpMethod.Post     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.api.http.DefaultHttpRequestComposer.WhenMappings.<clinit>():void");
        }
    }

    public DefaultHttpRequestComposer(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "serverUrl");
        this.serverUrl = str;
    }

    @NotNull
    public <D extends Operation.Data> HttpRequest compose(@NotNull ApolloRequest<D> apolloRequest) {
        Intrinsics.checkNotNullParameter(apolloRequest, "apolloRequest");
        Operation<D> operation = apolloRequest.getOperation();
        CustomScalarAdapters customScalarAdapters = (CustomScalarAdapters) apolloRequest.getExecutionContext().get(CustomScalarAdapters.Key);
        if (customScalarAdapters == null) {
            customScalarAdapters = CustomScalarAdapters.Empty;
        }
        CustomScalarAdapters customScalarAdapters2 = customScalarAdapters;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new HttpHeader(HEADER_APOLLO_OPERATION_ID, operation.id()));
        arrayList.add(new HttpHeader(HEADER_APOLLO_OPERATION_NAME, operation.name()));
        if (apolloRequest.getOperation() instanceof Subscription) {
            arrayList.add(new HttpHeader("Accept", HEADER_ACCEPT_VALUE_MULTIPART));
        } else {
            arrayList.add(new HttpHeader("Accept", HEADER_ACCEPT_VALUE_DEFER));
        }
        if (apolloRequest.getHttpHeaders() != null) {
            arrayList.addAll(apolloRequest.getHttpHeaders());
        }
        Boolean sendApqExtensions = apolloRequest.getSendApqExtensions();
        boolean booleanValue = sendApqExtensions != null ? sendApqExtensions.booleanValue() : false;
        Boolean sendDocument = apolloRequest.getSendDocument();
        boolean booleanValue2 = sendDocument != null ? sendDocument.booleanValue() : true;
        HttpMethod httpMethod = apolloRequest.getHttpMethod();
        if (httpMethod == null) {
            httpMethod = HttpMethod.Post;
        }
        int i3 = WhenMappings.$EnumSwitchMapping$0[httpMethod.ordinal()];
        if (i3 == 1) {
            return new HttpRequest.Builder(HttpMethod.Get, Companion.buildGetUrl(this.serverUrl, operation, customScalarAdapters2, booleanValue, booleanValue2)).addHeaders(arrayList).build();
        }
        if (i3 == 2) {
            return new HttpRequest.Builder(HttpMethod.Post, this.serverUrl).addHeaders(arrayList).body(Companion.buildPostBody(operation, customScalarAdapters2, booleanValue, booleanValue2 ? operation.document() : null)).build();
        }
        throw new NoWhenBranchMatchedException();
    }
}
