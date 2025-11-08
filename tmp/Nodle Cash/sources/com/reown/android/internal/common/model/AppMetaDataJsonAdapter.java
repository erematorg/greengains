package com.reown.android.internal.common.model;

import A.a;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import com.squareup.moshi.internal.Util;
import java.lang.reflect.Constructor;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0012\u001a\u00020\nH\u0016J\u0010\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u001a\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\f0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0011X\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/reown/android/internal/common/model/AppMetaDataJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/android/internal/common/model/AppMetaData;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "stringAdapter", "", "listOfStringAdapter", "", "nullableRedirectAdapter", "Lcom/reown/android/internal/common/model/Redirect;", "nullableStringAdapter", "constructorRef", "Ljava/lang/reflect/Constructor;", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AppMetaDataJsonAdapter extends JsonAdapter<AppMetaData> {
    @Nullable
    private volatile Constructor<AppMetaData> constructorRef;
    @NotNull
    private final JsonAdapter<List<String>> listOfStringAdapter;
    @NotNull
    private final JsonAdapter<Redirect> nullableRedirectAdapter;
    @NotNull
    private final JsonAdapter<String> nullableStringAdapter;
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<String> stringAdapter;

    public AppMetaDataJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of("description", "url", "icons", "name", "redirect", "verifyUrl");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        Class<String> cls = String.class;
        this.stringAdapter = a.h(moshi, cls, "description", "adapter(...)");
        this.listOfStringAdapter = a.i(moshi, Types.newParameterizedType(List.class, cls), "icons", "adapter(...)");
        this.nullableRedirectAdapter = a.h(moshi, Redirect.class, "redirect", "adapter(...)");
        this.nullableStringAdapter = a.h(moshi, cls, "verifyUrl", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(33, "GeneratedJsonAdapter(AppMetaData)");
    }

    @NotNull
    public AppMetaData fromJson(@NotNull JsonReader jsonReader) {
        JsonReader jsonReader2 = jsonReader;
        Intrinsics.checkNotNullParameter(jsonReader2, "reader");
        jsonReader.beginObject();
        String str = null;
        int i3 = -1;
        String str2 = null;
        List list = null;
        String str3 = null;
        Redirect redirect = null;
        String str4 = null;
        while (jsonReader.hasNext()) {
            switch (jsonReader2.selectName(this.options)) {
                case -1:
                    jsonReader.skipName();
                    jsonReader.skipValue();
                    break;
                case 0:
                    str = this.stringAdapter.fromJson(jsonReader2);
                    if (str != null) {
                        break;
                    } else {
                        throw Util.unexpectedNull("description", "description", jsonReader2);
                    }
                case 1:
                    str2 = this.stringAdapter.fromJson(jsonReader2);
                    if (str2 != null) {
                        i3 &= -3;
                        break;
                    } else {
                        throw Util.unexpectedNull("url", "url", jsonReader2);
                    }
                case 2:
                    list = this.listOfStringAdapter.fromJson(jsonReader2);
                    if (list != null) {
                        break;
                    } else {
                        throw Util.unexpectedNull("icons", "icons", jsonReader2);
                    }
                case 3:
                    str3 = this.stringAdapter.fromJson(jsonReader2);
                    if (str3 != null) {
                        break;
                    } else {
                        throw Util.unexpectedNull("name", "name", jsonReader2);
                    }
                case 4:
                    redirect = this.nullableRedirectAdapter.fromJson(jsonReader2);
                    i3 &= -17;
                    break;
                case 5:
                    str4 = this.nullableStringAdapter.fromJson(jsonReader2);
                    i3 &= -33;
                    break;
            }
        }
        jsonReader.endObject();
        if (i3 != -51) {
            Constructor<AppMetaData> constructor = this.constructorRef;
            if (constructor == null) {
                constructor = AppMetaData.class.getDeclaredConstructor(new Class[]{String.class, String.class, List.class, String.class, Redirect.class, String.class, Integer.TYPE, Util.DEFAULT_CONSTRUCTOR_MARKER});
                this.constructorRef = constructor;
                Intrinsics.checkNotNullExpressionValue(constructor, "also(...)");
            }
            Constructor<AppMetaData> constructor2 = constructor;
            if (str == null) {
                throw Util.missingProperty("description", "description", jsonReader2);
            } else if (list == null) {
                throw Util.missingProperty("icons", "icons", jsonReader2);
            } else if (str3 != null) {
                AppMetaData newInstance = constructor2.newInstance(new Object[]{str, str2, list, str3, redirect, str4, Integer.valueOf(i3), null});
                Intrinsics.checkNotNullExpressionValue(newInstance, "newInstance(...)");
                return newInstance;
            } else {
                throw Util.missingProperty("name", "name", jsonReader2);
            }
        } else if (str != null) {
            Intrinsics.checkNotNull(str2, "null cannot be cast to non-null type kotlin.String");
            if (list == null) {
                throw Util.missingProperty("icons", "icons", jsonReader2);
            } else if (str3 != null) {
                return new AppMetaData(str, str2, list, str3, redirect, str4);
            } else {
                throw Util.missingProperty("name", "name", jsonReader2);
            }
        } else {
            throw Util.missingProperty("description", "description", jsonReader2);
        }
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable AppMetaData appMetaData) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (appMetaData != null) {
            jsonWriter.beginObject();
            jsonWriter.name("description");
            this.stringAdapter.toJson(jsonWriter, appMetaData.getDescription());
            jsonWriter.name("url");
            this.stringAdapter.toJson(jsonWriter, appMetaData.getUrl());
            jsonWriter.name("icons");
            this.listOfStringAdapter.toJson(jsonWriter, appMetaData.getIcons());
            jsonWriter.name("name");
            this.stringAdapter.toJson(jsonWriter, appMetaData.getName());
            jsonWriter.name("redirect");
            this.nullableRedirectAdapter.toJson(jsonWriter, appMetaData.getRedirect());
            jsonWriter.name("verifyUrl");
            this.nullableStringAdapter.toJson(jsonWriter, appMetaData.getVerifyUrl());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
