package com.reown.android.internal.common.explorer.data.network.model;

import A.a;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import com.squareup.moshi.internal.Util;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u000f\u001a\u00020\u000bH\u0016J\u0010\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001a\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R \u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/reown/android/internal/common/explorer/data/network/model/DappListingsDTOJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/android/internal/common/explorer/data/network/model/DappListingsDTO;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "mapOfStringListingDTOAdapter", "", "", "Lcom/reown/android/internal/common/explorer/data/network/model/ListingDTO;", "intAdapter", "", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DappListingsDTOJsonAdapter extends JsonAdapter<DappListingsDTO> {
    @NotNull
    private final JsonAdapter<Integer> intAdapter;
    @NotNull
    private final JsonAdapter<Map<String, ListingDTO>> mapOfStringListingDTOAdapter;
    @NotNull
    private final JsonReader.Options options;

    public DappListingsDTOJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of("listings", "count", "total");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        this.mapOfStringListingDTOAdapter = a.i(moshi, Types.newParameterizedType(Map.class, String.class, ListingDTO.class), "listings", "adapter(...)");
        this.intAdapter = a.h(moshi, Integer.TYPE, "count", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(37, "GeneratedJsonAdapter(DappListingsDTO)");
    }

    @NotNull
    public DappListingsDTO fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        jsonReader.beginObject();
        Map map = null;
        Integer num = null;
        Integer num2 = null;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(this.options);
            if (selectName == -1) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else if (selectName == 0) {
                map = this.mapOfStringListingDTOAdapter.fromJson(jsonReader);
                if (map == null) {
                    throw Util.unexpectedNull("listings", "listings", jsonReader);
                }
            } else if (selectName == 1) {
                num = this.intAdapter.fromJson(jsonReader);
                if (num == null) {
                    throw Util.unexpectedNull("count", "count", jsonReader);
                }
            } else if (selectName == 2 && (num2 = this.intAdapter.fromJson(jsonReader)) == null) {
                throw Util.unexpectedNull("total", "total", jsonReader);
            }
        }
        jsonReader.endObject();
        if (map == null) {
            throw Util.missingProperty("listings", "listings", jsonReader);
        } else if (num != null) {
            int intValue = num.intValue();
            if (num2 != null) {
                return new DappListingsDTO(map, intValue, num2.intValue());
            }
            throw Util.missingProperty("total", "total", jsonReader);
        } else {
            throw Util.missingProperty("count", "count", jsonReader);
        }
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable DappListingsDTO dappListingsDTO) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (dappListingsDTO != null) {
            jsonWriter.beginObject();
            jsonWriter.name("listings");
            this.mapOfStringListingDTOAdapter.toJson(jsonWriter, dappListingsDTO.getListings());
            jsonWriter.name("count");
            this.intAdapter.toJson(jsonWriter, Integer.valueOf(dappListingsDTO.getCount()));
            jsonWriter.name("total");
            this.intAdapter.toJson(jsonWriter, Integer.valueOf(dappListingsDTO.getTotal()));
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
