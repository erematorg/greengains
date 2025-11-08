package com.reown.android.internal.common.model;

import A.a;
import com.reown.android.internal.common.model.Namespace;
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
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u000f\u001a\u00020\u000bH\u0016J\u0010\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001a\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/reown/android/internal/common/model/Namespace_SessionJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/android/internal/common/model/Namespace$Session;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "nullableListOfStringAdapter", "", "", "listOfStringAdapter", "constructorRef", "Ljava/lang/reflect/Constructor;", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nNamespace_SessionJsonAdapter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Namespace_SessionJsonAdapter.kt\ncom/reown/android/internal/common/model/Namespace_SessionJsonAdapter\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,115:1\n1#2:116\n*E\n"})
public final class Namespace_SessionJsonAdapter extends JsonAdapter<Namespace.Session> {
    @Nullable
    private volatile Constructor<Namespace.Session> constructorRef;
    @NotNull
    private final JsonAdapter<List<String>> listOfStringAdapter;
    @NotNull
    private final JsonAdapter<List<String>> nullableListOfStringAdapter;
    @NotNull
    private final JsonReader.Options options;

    public Namespace_SessionJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of("chains", "accounts", "methods", "events");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        Class<String> cls = String.class;
        Class<List> cls2 = List.class;
        this.nullableListOfStringAdapter = a.i(moshi, Types.newParameterizedType(cls2, cls), "chains", "adapter(...)");
        this.listOfStringAdapter = a.i(moshi, Types.newParameterizedType(cls2, cls), "accounts", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(39, "GeneratedJsonAdapter(Namespace.Session)");
    }

    @NotNull
    public Namespace.Session fromJson(@NotNull JsonReader jsonReader) {
        JsonReader jsonReader2 = jsonReader;
        Intrinsics.checkNotNullParameter(jsonReader2, "reader");
        jsonReader.beginObject();
        List list = null;
        List list2 = null;
        List list3 = null;
        List list4 = null;
        int i3 = -1;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader2.selectName(this.options);
            if (selectName == -1) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else if (selectName == 0) {
                list = this.nullableListOfStringAdapter.fromJson(jsonReader2);
                i3 = -2;
            } else if (selectName == 1) {
                list2 = this.listOfStringAdapter.fromJson(jsonReader2);
                if (list2 == null) {
                    throw Util.unexpectedNull("accounts", "accounts", jsonReader2);
                }
            } else if (selectName == 2) {
                list3 = this.listOfStringAdapter.fromJson(jsonReader2);
                if (list3 == null) {
                    throw Util.unexpectedNull("methods", "methods", jsonReader2);
                }
            } else if (selectName == 3 && (list4 = this.listOfStringAdapter.fromJson(jsonReader2)) == null) {
                throw Util.unexpectedNull("events", "events", jsonReader2);
            }
        }
        jsonReader.endObject();
        if (i3 != -2) {
            Constructor<Namespace.Session> constructor = this.constructorRef;
            if (constructor == null) {
                constructor = Namespace.Session.class.getDeclaredConstructor(new Class[]{List.class, List.class, List.class, List.class, Integer.TYPE, Util.DEFAULT_CONSTRUCTOR_MARKER});
                this.constructorRef = constructor;
                Intrinsics.checkNotNullExpressionValue(constructor, "also(...)");
            }
            if (list2 == null) {
                throw Util.missingProperty("accounts", "accounts", jsonReader2);
            } else if (list3 == null) {
                throw Util.missingProperty("methods", "methods", jsonReader2);
            } else if (list4 != null) {
                Namespace.Session newInstance = constructor.newInstance(new Object[]{list, list2, list3, list4, Integer.valueOf(i3), null});
                Intrinsics.checkNotNullExpressionValue(newInstance, "newInstance(...)");
                return newInstance;
            } else {
                throw Util.missingProperty("events", "events", jsonReader2);
            }
        } else if (list2 == null) {
            throw Util.missingProperty("accounts", "accounts", jsonReader2);
        } else if (list3 == null) {
            throw Util.missingProperty("methods", "methods", jsonReader2);
        } else if (list4 != null) {
            return new Namespace.Session(list, list2, list3, list4);
        } else {
            throw Util.missingProperty("events", "events", jsonReader2);
        }
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable Namespace.Session session) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (session != null) {
            jsonWriter.beginObject();
            jsonWriter.name("chains");
            this.nullableListOfStringAdapter.toJson(jsonWriter, session.getChains());
            jsonWriter.name("accounts");
            this.listOfStringAdapter.toJson(jsonWriter, session.getAccounts());
            jsonWriter.name("methods");
            this.listOfStringAdapter.toJson(jsonWriter, session.getMethods());
            jsonWriter.name("events");
            this.listOfStringAdapter.toJson(jsonWriter, session.getEvents());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
