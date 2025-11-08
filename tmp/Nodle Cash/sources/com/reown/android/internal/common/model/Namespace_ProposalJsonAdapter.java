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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u000f\u001a\u00020\u000bH\u0016J\u0010\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001a\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/reown/android/internal/common/model/Namespace_ProposalJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/android/internal/common/model/Namespace$Proposal;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "listOfStringAdapter", "", "", "nullableListOfStringAdapter", "constructorRef", "Ljava/lang/reflect/Constructor;", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Namespace_ProposalJsonAdapter extends JsonAdapter<Namespace.Proposal> {
    @Nullable
    private volatile Constructor<Namespace.Proposal> constructorRef;
    @NotNull
    private final JsonAdapter<List<String>> listOfStringAdapter;
    @NotNull
    private final JsonAdapter<List<String>> nullableListOfStringAdapter;
    @NotNull
    private final JsonReader.Options options;

    public Namespace_ProposalJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of("methods", "chains", "events");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        Class<String> cls = String.class;
        Class<List> cls2 = List.class;
        this.listOfStringAdapter = a.i(moshi, Types.newParameterizedType(cls2, cls), "methods", "adapter(...)");
        this.nullableListOfStringAdapter = a.i(moshi, Types.newParameterizedType(cls2, cls), "chains", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(40, "GeneratedJsonAdapter(Namespace.Proposal)");
    }

    @NotNull
    public Namespace.Proposal fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        jsonReader.beginObject();
        List list = null;
        List list2 = null;
        List list3 = null;
        int i3 = -1;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(this.options);
            if (selectName == -1) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else if (selectName == 0) {
                list = this.listOfStringAdapter.fromJson(jsonReader);
                if (list == null) {
                    throw Util.unexpectedNull("methods", "methods", jsonReader);
                }
            } else if (selectName == 1) {
                list2 = this.nullableListOfStringAdapter.fromJson(jsonReader);
                i3 = -3;
            } else if (selectName == 2 && (list3 = this.listOfStringAdapter.fromJson(jsonReader)) == null) {
                throw Util.unexpectedNull("events", "events", jsonReader);
            }
        }
        jsonReader.endObject();
        if (i3 != -3) {
            Constructor<Namespace.Proposal> constructor = this.constructorRef;
            if (constructor == null) {
                Class<List> cls = List.class;
                constructor = Namespace.Proposal.class.getDeclaredConstructor(new Class[]{cls, cls, cls, Integer.TYPE, Util.DEFAULT_CONSTRUCTOR_MARKER});
                this.constructorRef = constructor;
                Intrinsics.checkNotNullExpressionValue(constructor, "also(...)");
            }
            if (list == null) {
                throw Util.missingProperty("methods", "methods", jsonReader);
            } else if (list3 != null) {
                Namespace.Proposal newInstance = constructor.newInstance(new Object[]{list, list2, list3, Integer.valueOf(i3), null});
                Intrinsics.checkNotNullExpressionValue(newInstance, "newInstance(...)");
                return newInstance;
            } else {
                throw Util.missingProperty("events", "events", jsonReader);
            }
        } else if (list == null) {
            throw Util.missingProperty("methods", "methods", jsonReader);
        } else if (list3 != null) {
            return new Namespace.Proposal(list, list2, list3);
        } else {
            throw Util.missingProperty("events", "events", jsonReader);
        }
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable Namespace.Proposal proposal) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (proposal != null) {
            jsonWriter.beginObject();
            jsonWriter.name("methods");
            this.listOfStringAdapter.toJson(jsonWriter, proposal.getMethods());
            jsonWriter.name("chains");
            this.nullableListOfStringAdapter.toJson(jsonWriter, proposal.getChains());
            jsonWriter.name("events");
            this.listOfStringAdapter.toJson(jsonWriter, proposal.getEvents());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
