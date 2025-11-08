package com.reown.android.internal.common.model;

import A.a;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.internal.Util;
import java.lang.reflect.Constructor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u000f\u001a\u00020\nH\u0016J\u0010\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001a\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/reown/android/internal/common/model/RedirectJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/android/internal/common/model/Redirect;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "nullableStringAdapter", "", "booleanAdapter", "", "constructorRef", "Ljava/lang/reflect/Constructor;", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nRedirectJsonAdapter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RedirectJsonAdapter.kt\ncom/reown/android/internal/common/model/RedirectJsonAdapter\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,111:1\n1#2:112\n*E\n"})
public final class RedirectJsonAdapter extends JsonAdapter<Redirect> {
    @NotNull
    private final JsonAdapter<Boolean> booleanAdapter;
    @Nullable
    private volatile Constructor<Redirect> constructorRef;
    @NotNull
    private final JsonAdapter<String> nullableStringAdapter;
    @NotNull
    private final JsonReader.Options options;

    public RedirectJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of("native", "universal", "linkMode");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        this.nullableStringAdapter = a.h(moshi, String.class, "native", "adapter(...)");
        this.booleanAdapter = a.h(moshi, Boolean.TYPE, "linkMode", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(30, "GeneratedJsonAdapter(Redirect)");
    }

    @NotNull
    public Redirect fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        Boolean bool = Boolean.FALSE;
        jsonReader.beginObject();
        String str = null;
        String str2 = null;
        int i3 = -1;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(this.options);
            if (selectName == -1) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else if (selectName == 0) {
                str = this.nullableStringAdapter.fromJson(jsonReader);
                i3 &= -2;
            } else if (selectName == 1) {
                str2 = this.nullableStringAdapter.fromJson(jsonReader);
                i3 &= -3;
            } else if (selectName == 2) {
                bool = this.booleanAdapter.fromJson(jsonReader);
                if (bool != null) {
                    i3 &= -5;
                } else {
                    throw Util.unexpectedNull("linkMode", "linkMode", jsonReader);
                }
            } else {
                continue;
            }
        }
        jsonReader.endObject();
        if (i3 == -8) {
            return new Redirect(str, str2, bool.booleanValue());
        }
        Constructor<Redirect> constructor = this.constructorRef;
        if (constructor == null) {
            Class<String> cls = String.class;
            constructor = Redirect.class.getDeclaredConstructor(new Class[]{cls, cls, Boolean.TYPE, Integer.TYPE, Util.DEFAULT_CONSTRUCTOR_MARKER});
            this.constructorRef = constructor;
            Intrinsics.checkNotNullExpressionValue(constructor, "also(...)");
        }
        Redirect newInstance = constructor.newInstance(new Object[]{str, str2, bool, Integer.valueOf(i3), null});
        Intrinsics.checkNotNullExpressionValue(newInstance, "newInstance(...)");
        return newInstance;
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable Redirect redirect) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (redirect != null) {
            jsonWriter.beginObject();
            jsonWriter.name("native");
            this.nullableStringAdapter.toJson(jsonWriter, redirect.getNative());
            jsonWriter.name("universal");
            this.nullableStringAdapter.toJson(jsonWriter, redirect.getUniversal());
            jsonWriter.name("linkMode");
            this.booleanAdapter.toJson(jsonWriter, Boolean.valueOf(redirect.getLinkMode()));
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
