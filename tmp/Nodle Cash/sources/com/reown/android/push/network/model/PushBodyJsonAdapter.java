package com.reown.android.push.network.model;

import A.a;
import com.reown.android.internal.common.di.CoreNetworkModuleKt;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.internal.Util;
import java.lang.reflect.Constructor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u000f\u001a\u00020\nH\u0016J\u0010\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001a\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/reown/android/push/network/model/PushBodyJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/android/push/network/model/PushBody;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "stringAdapter", "", "nullableBooleanAdapter", "", "constructorRef", "Ljava/lang/reflect/Constructor;", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPushBodyJsonAdapter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PushBodyJsonAdapter.kt\ncom/reown/android/push/network/model/PushBodyJsonAdapter\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,111:1\n1#2:112\n*E\n"})
public final class PushBodyJsonAdapter extends JsonAdapter<PushBody> {
    @Nullable
    private volatile Constructor<PushBody> constructorRef;
    @NotNull
    private final JsonAdapter<Boolean> nullableBooleanAdapter;
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<String> stringAdapter;

    public PushBodyJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of("client_id", SchemaSymbols.ATTVAL_TOKEN, "type", "always_raw");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        this.stringAdapter = a.h(moshi, String.class, CoreNetworkModuleKt.KEY_CLIENT_ID, "adapter(...)");
        this.nullableBooleanAdapter = a.h(moshi, Boolean.class, "enableEncrypted", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(30, "GeneratedJsonAdapter(PushBody)");
    }

    @NotNull
    public PushBody fromJson(@NotNull JsonReader jsonReader) {
        JsonReader jsonReader2 = jsonReader;
        Intrinsics.checkNotNullParameter(jsonReader2, "reader");
        jsonReader.beginObject();
        String str = null;
        String str2 = null;
        String str3 = null;
        Boolean bool = null;
        int i3 = -1;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader2.selectName(this.options);
            if (selectName == -1) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else if (selectName == 0) {
                str = this.stringAdapter.fromJson(jsonReader2);
                if (str == null) {
                    throw Util.unexpectedNull(CoreNetworkModuleKt.KEY_CLIENT_ID, "client_id", jsonReader2);
                }
            } else if (selectName == 1) {
                str2 = this.stringAdapter.fromJson(jsonReader2);
                if (str2 == null) {
                    throw Util.unexpectedNull(SchemaSymbols.ATTVAL_TOKEN, SchemaSymbols.ATTVAL_TOKEN, jsonReader2);
                }
            } else if (selectName == 2) {
                str3 = this.stringAdapter.fromJson(jsonReader2);
                if (str3 != null) {
                    i3 = -5;
                } else {
                    throw Util.unexpectedNull("type", "type", jsonReader2);
                }
            } else if (selectName == 3) {
                bool = this.nullableBooleanAdapter.fromJson(jsonReader2);
            }
        }
        jsonReader.endObject();
        if (i3 != -5) {
            Constructor<PushBody> constructor = this.constructorRef;
            if (constructor == null) {
                constructor = PushBody.class.getDeclaredConstructor(new Class[]{String.class, String.class, String.class, Boolean.class, Integer.TYPE, Util.DEFAULT_CONSTRUCTOR_MARKER});
                this.constructorRef = constructor;
                Intrinsics.checkNotNullExpressionValue(constructor, "also(...)");
            }
            if (str == null) {
                throw Util.missingProperty(CoreNetworkModuleKt.KEY_CLIENT_ID, "client_id", jsonReader2);
            } else if (str2 != null) {
                PushBody newInstance = constructor.newInstance(new Object[]{str, str2, str3, bool, Integer.valueOf(i3), null});
                Intrinsics.checkNotNullExpressionValue(newInstance, "newInstance(...)");
                return newInstance;
            } else {
                throw Util.missingProperty(SchemaSymbols.ATTVAL_TOKEN, SchemaSymbols.ATTVAL_TOKEN, jsonReader2);
            }
        } else if (str == null) {
            throw Util.missingProperty(CoreNetworkModuleKt.KEY_CLIENT_ID, "client_id", jsonReader2);
        } else if (str2 != null) {
            Intrinsics.checkNotNull(str3, "null cannot be cast to non-null type kotlin.String");
            return new PushBody(str, str2, str3, bool);
        } else {
            throw Util.missingProperty(SchemaSymbols.ATTVAL_TOKEN, SchemaSymbols.ATTVAL_TOKEN, jsonReader2);
        }
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable PushBody pushBody) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (pushBody != null) {
            jsonWriter.beginObject();
            jsonWriter.name("client_id");
            this.stringAdapter.toJson(jsonWriter, pushBody.getClientId());
            jsonWriter.name(SchemaSymbols.ATTVAL_TOKEN);
            this.stringAdapter.toJson(jsonWriter, pushBody.getToken());
            jsonWriter.name("type");
            this.stringAdapter.toJson(jsonWriter, pushBody.getType());
            jsonWriter.name("always_raw");
            this.nullableBooleanAdapter.toJson(jsonWriter, pushBody.getEnableEncrypted());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
