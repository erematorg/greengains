package com.reown.android.internal.common.explorer.data.network.model;

import A.a;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import com.squareup.moshi.internal.Util;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0013\u001a\u00020\nH\u0016J\u0010\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u001a\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0001X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/reown/android/internal/common/explorer/data/network/model/NotifyConfigDataDTOJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/android/internal/common/explorer/data/network/model/NotifyConfigDataDTO;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "stringAdapter", "", "nullableStringAdapter", "nullableImageUrlDTOAdapter", "Lcom/reown/android/internal/common/explorer/data/network/model/ImageUrlDTO;", "listOfNotificationTypeDTOAdapter", "", "Lcom/reown/android/internal/common/explorer/data/network/model/NotificationTypeDTO;", "booleanAdapter", "", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class NotifyConfigDataDTOJsonAdapter extends JsonAdapter<NotifyConfigDataDTO> {
    @NotNull
    private final JsonAdapter<Boolean> booleanAdapter;
    @NotNull
    private final JsonAdapter<List<NotificationTypeDTO>> listOfNotificationTypeDTOAdapter;
    @NotNull
    private final JsonAdapter<ImageUrlDTO> nullableImageUrlDTOAdapter;
    @NotNull
    private final JsonAdapter<String> nullableStringAdapter;
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<String> stringAdapter;

    public NotifyConfigDataDTOJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of("name", "homepage", "description", "dapp_url", "image_url", "notificationTypes", "isVerified");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        Class<String> cls = String.class;
        this.stringAdapter = a.h(moshi, cls, "name", "adapter(...)");
        this.nullableStringAdapter = a.h(moshi, cls, "homepage", "adapter(...)");
        this.nullableImageUrlDTOAdapter = a.h(moshi, ImageUrlDTO.class, "imageUrl", "adapter(...)");
        this.listOfNotificationTypeDTOAdapter = a.i(moshi, Types.newParameterizedType(List.class, NotificationTypeDTO.class), "notificationTypes", "adapter(...)");
        this.booleanAdapter = a.h(moshi, Boolean.TYPE, "isVerified", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(41, "GeneratedJsonAdapter(NotifyConfigDataDTO)");
    }

    @NotNull
    public NotifyConfigDataDTO fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        jsonReader.beginObject();
        Boolean bool = null;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        ImageUrlDTO imageUrlDTO = null;
        List list = null;
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
                        throw Util.unexpectedNull("name", "name", jsonReader);
                    }
                case 1:
                    str2 = this.nullableStringAdapter.fromJson(jsonReader);
                    break;
                case 2:
                    str3 = this.stringAdapter.fromJson(jsonReader);
                    if (str3 != null) {
                        break;
                    } else {
                        throw Util.unexpectedNull("description", "description", jsonReader);
                    }
                case 3:
                    str4 = this.stringAdapter.fromJson(jsonReader);
                    if (str4 != null) {
                        break;
                    } else {
                        throw Util.unexpectedNull("dappUrl", "dapp_url", jsonReader);
                    }
                case 4:
                    imageUrlDTO = this.nullableImageUrlDTOAdapter.fromJson(jsonReader);
                    break;
                case 5:
                    list = this.listOfNotificationTypeDTOAdapter.fromJson(jsonReader);
                    if (list != null) {
                        break;
                    } else {
                        throw Util.unexpectedNull("notificationTypes", "notificationTypes", jsonReader);
                    }
                case 6:
                    bool = this.booleanAdapter.fromJson(jsonReader);
                    if (bool != null) {
                        break;
                    } else {
                        throw Util.unexpectedNull("isVerified", "isVerified", jsonReader);
                    }
            }
        }
        jsonReader.endObject();
        if (str == null) {
            throw Util.missingProperty("name", "name", jsonReader);
        } else if (str3 == null) {
            throw Util.missingProperty("description", "description", jsonReader);
        } else if (str4 == null) {
            throw Util.missingProperty("dappUrl", "dapp_url", jsonReader);
        } else if (list == null) {
            throw Util.missingProperty("notificationTypes", "notificationTypes", jsonReader);
        } else if (bool != null) {
            return new NotifyConfigDataDTO(str, str2, str3, str4, imageUrlDTO, list, bool.booleanValue());
        } else {
            throw Util.missingProperty("isVerified", "isVerified", jsonReader);
        }
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable NotifyConfigDataDTO notifyConfigDataDTO) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (notifyConfigDataDTO != null) {
            jsonWriter.beginObject();
            jsonWriter.name("name");
            this.stringAdapter.toJson(jsonWriter, notifyConfigDataDTO.getName());
            jsonWriter.name("homepage");
            this.nullableStringAdapter.toJson(jsonWriter, notifyConfigDataDTO.getHomepage());
            jsonWriter.name("description");
            this.stringAdapter.toJson(jsonWriter, notifyConfigDataDTO.getDescription());
            jsonWriter.name("dapp_url");
            this.stringAdapter.toJson(jsonWriter, notifyConfigDataDTO.getDappUrl());
            jsonWriter.name("image_url");
            this.nullableImageUrlDTOAdapter.toJson(jsonWriter, notifyConfigDataDTO.getImageUrl());
            jsonWriter.name("notificationTypes");
            this.listOfNotificationTypeDTOAdapter.toJson(jsonWriter, notifyConfigDataDTO.getNotificationTypes());
            jsonWriter.name("isVerified");
            this.booleanAdapter.toJson(jsonWriter, Boolean.valueOf(notifyConfigDataDTO.isVerified()));
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
