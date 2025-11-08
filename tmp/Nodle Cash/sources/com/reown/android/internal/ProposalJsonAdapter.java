package com.reown.android.internal;

import A.a;
import com.reown.android.internal.Proposal;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import com.squareup.moshi.internal.Util;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u000e\u001a\u00020\u000bH\u0016J\u0010\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u001a\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\r\u0018\u00010\n0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/reown/android/internal/ProposalJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/android/internal/Proposal;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "listOfStringAdapter", "", "", "nullableListOfExtensionAdapter", "Lcom/reown/android/internal/Proposal$Extension;", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ProposalJsonAdapter extends JsonAdapter<Proposal> {
    @NotNull
    private final JsonAdapter<List<String>> listOfStringAdapter;
    @NotNull
    private final JsonAdapter<List<Proposal.Extension>> nullableListOfExtensionAdapter;
    @NotNull
    private final JsonReader.Options options;

    public ProposalJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of("chains", "methods", "events", SchemaSymbols.ATTVAL_EXTENSION);
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        Class<List> cls = List.class;
        this.listOfStringAdapter = a.i(moshi, Types.newParameterizedType(cls, String.class), "chains", "adapter(...)");
        this.nullableListOfExtensionAdapter = a.i(moshi, Types.newParameterizedType(cls, Proposal.Extension.class), "extensions", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(30, "GeneratedJsonAdapter(Proposal)");
    }

    @NotNull
    public Proposal fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        jsonReader.beginObject();
        List list = null;
        List list2 = null;
        List list3 = null;
        List list4 = null;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(this.options);
            if (selectName == -1) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else if (selectName == 0) {
                list = this.listOfStringAdapter.fromJson(jsonReader);
                if (list == null) {
                    throw Util.unexpectedNull("chains", "chains", jsonReader);
                }
            } else if (selectName == 1) {
                list2 = this.listOfStringAdapter.fromJson(jsonReader);
                if (list2 == null) {
                    throw Util.unexpectedNull("methods", "methods", jsonReader);
                }
            } else if (selectName == 2) {
                list3 = this.listOfStringAdapter.fromJson(jsonReader);
                if (list3 == null) {
                    throw Util.unexpectedNull("events", "events", jsonReader);
                }
            } else if (selectName == 3) {
                list4 = this.nullableListOfExtensionAdapter.fromJson(jsonReader);
            }
        }
        jsonReader.endObject();
        if (list == null) {
            throw Util.missingProperty("chains", "chains", jsonReader);
        } else if (list2 == null) {
            throw Util.missingProperty("methods", "methods", jsonReader);
        } else if (list3 != null) {
            return new Proposal(list, list2, list3, list4);
        } else {
            throw Util.missingProperty("events", "events", jsonReader);
        }
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable Proposal proposal) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (proposal != null) {
            jsonWriter.beginObject();
            jsonWriter.name("chains");
            this.listOfStringAdapter.toJson(jsonWriter, proposal.getChains());
            jsonWriter.name("methods");
            this.listOfStringAdapter.toJson(jsonWriter, proposal.getMethods());
            jsonWriter.name("events");
            this.listOfStringAdapter.toJson(jsonWriter, proposal.getEvents());
            jsonWriter.name(SchemaSymbols.ATTVAL_EXTENSION);
            this.nullableListOfExtensionAdapter.toJson(jsonWriter, proposal.getExtensions());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
