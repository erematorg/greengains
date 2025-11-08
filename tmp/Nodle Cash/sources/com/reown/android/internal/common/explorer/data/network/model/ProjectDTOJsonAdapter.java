package com.reown.android.internal.common.explorer.data.network.model;

import A.a;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.internal.Util;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0010\u001a\u00020\nH\u0016J\u0010\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u001a\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/reown/android/internal/common/explorer/data/network/model/ProjectDTOJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/android/internal/common/explorer/data/network/model/ProjectDTO;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "stringAdapter", "", "nullableStringAdapter", "nullableImageUrlDTOAdapter", "Lcom/reown/android/internal/common/explorer/data/network/model/ImageUrlDTO;", "nullableLongAdapter", "", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ProjectDTOJsonAdapter extends JsonAdapter<ProjectDTO> {
    @NotNull
    private final JsonAdapter<ImageUrlDTO> nullableImageUrlDTOAdapter;
    @NotNull
    private final JsonAdapter<Long> nullableLongAdapter;
    @NotNull
    private final JsonAdapter<String> nullableStringAdapter;
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<String> stringAdapter;

    public ProjectDTOJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of(TtmlNode.ATTR_ID, "name", "homepage", "image_id", "description", "image_url", "dapp_url", "order");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        Class<String> cls = String.class;
        this.stringAdapter = a.h(moshi, cls, TtmlNode.ATTR_ID, "adapter(...)");
        this.nullableStringAdapter = a.h(moshi, cls, "name", "adapter(...)");
        this.nullableImageUrlDTOAdapter = a.h(moshi, ImageUrlDTO.class, "imageUrl", "adapter(...)");
        this.nullableLongAdapter = a.h(moshi, Long.class, "order", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(32, "GeneratedJsonAdapter(ProjectDTO)");
    }

    @NotNull
    public ProjectDTO fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        jsonReader.beginObject();
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        ImageUrlDTO imageUrlDTO = null;
        String str6 = null;
        Long l2 = null;
        while (jsonReader.hasNext()) {
            switch (jsonReader.selectName(this.options)) {
                case -1:
                    jsonReader.skipName();
                    jsonReader.skipValue();
                    break;
                case 0:
                    str = this.stringAdapter.fromJson(jsonReader);
                    if (str != null) {
                        break;
                    } else {
                        throw Util.unexpectedNull(TtmlNode.ATTR_ID, TtmlNode.ATTR_ID, jsonReader);
                    }
                case 1:
                    str2 = this.nullableStringAdapter.fromJson(jsonReader);
                    break;
                case 2:
                    str3 = this.nullableStringAdapter.fromJson(jsonReader);
                    break;
                case 3:
                    str4 = this.nullableStringAdapter.fromJson(jsonReader);
                    break;
                case 4:
                    str5 = this.nullableStringAdapter.fromJson(jsonReader);
                    break;
                case 5:
                    imageUrlDTO = this.nullableImageUrlDTOAdapter.fromJson(jsonReader);
                    break;
                case 6:
                    str6 = this.nullableStringAdapter.fromJson(jsonReader);
                    break;
                case 7:
                    l2 = this.nullableLongAdapter.fromJson(jsonReader);
                    break;
            }
        }
        jsonReader.endObject();
        if (str != null) {
            return new ProjectDTO(str, str2, str3, str4, str5, imageUrlDTO, str6, l2);
        }
        throw Util.missingProperty(TtmlNode.ATTR_ID, TtmlNode.ATTR_ID, jsonReader);
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable ProjectDTO projectDTO) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (projectDTO != null) {
            jsonWriter.beginObject();
            jsonWriter.name(TtmlNode.ATTR_ID);
            this.stringAdapter.toJson(jsonWriter, projectDTO.getId());
            jsonWriter.name("name");
            this.nullableStringAdapter.toJson(jsonWriter, projectDTO.getName());
            jsonWriter.name("homepage");
            this.nullableStringAdapter.toJson(jsonWriter, projectDTO.getHomepage());
            jsonWriter.name("image_id");
            this.nullableStringAdapter.toJson(jsonWriter, projectDTO.getImageId());
            jsonWriter.name("description");
            this.nullableStringAdapter.toJson(jsonWriter, projectDTO.getDescription());
            jsonWriter.name("image_url");
            this.nullableImageUrlDTOAdapter.toJson(jsonWriter, projectDTO.getImageUrl());
            jsonWriter.name("dapp_url");
            this.nullableStringAdapter.toJson(jsonWriter, projectDTO.getDappUrl());
            jsonWriter.name("order");
            this.nullableLongAdapter.toJson(jsonWriter, projectDTO.getOrder());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
