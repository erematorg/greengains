package com.apollographql.apollo3.api.http;

import com.apollographql.apollo3.api.json.JsonWriter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@SourceDebugExtension({"SMAP\nDefaultHttpRequestComposer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DefaultHttpRequestComposer.kt\ncom/apollographql/apollo3/api/http/DefaultHttpRequestComposer$Companion$apqExtensionsWriter$1\n+ 2 JsonWriters.kt\ncom/apollographql/apollo3/api/json/-JsonWriters\n*L\n1#1,384:1\n46#2,6:385\n46#2,8:391\n52#2,2:399\n*S KotlinDebug\n*F\n+ 1 DefaultHttpRequestComposer.kt\ncom/apollographql/apollo3/api/http/DefaultHttpRequestComposer$Companion$apqExtensionsWriter$1\n*L\n157#1:385,6\n159#1:391,8\n157#1:399,2\n*E\n"})
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "invoke"}, k = 3, mv = {1, 5, 1}, xi = 48)
public final class DefaultHttpRequestComposer$Companion$apqExtensionsWriter$1 extends Lambda implements Function1<JsonWriter, Unit> {
    final /* synthetic */ String $id;
    final /* synthetic */ boolean $sendApqExtensions;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DefaultHttpRequestComposer$Companion$apqExtensionsWriter$1(boolean z2, String str) {
        super(1);
        this.$sendApqExtensions = z2;
        this.$id = str;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((JsonWriter) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull JsonWriter jsonWriter) {
        Intrinsics.checkNotNullParameter(jsonWriter, "$this$null");
        if (this.$sendApqExtensions) {
            jsonWriter.name("extensions");
            String str = this.$id;
            jsonWriter.beginObject();
            jsonWriter.name("persistedQuery");
            jsonWriter.beginObject();
            jsonWriter.name("version").value(1);
            jsonWriter.name("sha256Hash").value(str);
            jsonWriter.endObject();
            jsonWriter.endObject();
        }
    }
}
