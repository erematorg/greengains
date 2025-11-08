package com.reown.android.internal.common.explorer.data.network.model;

import A.a;
import com.amplitude.api.DeviceInfo;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u000b\u001a\u00020\nH\u0016J\u0010\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u001a\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/reown/android/internal/common/explorer/data/network/model/AppDTOJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/android/internal/common/explorer/data/network/model/AppDTO;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "nullableStringAdapter", "", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AppDTOJsonAdapter extends JsonAdapter<AppDTO> {
    @NotNull
    private final JsonAdapter<String> nullableStringAdapter;
    @NotNull
    private final JsonReader.Options options;

    public AppDTOJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of("browser", "ios", DeviceInfo.OS_NAME, "mac", "windows", "linux", "chrome", "firefox", "safari", "edge", "opera");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        this.nullableStringAdapter = a.h(moshi, String.class, "browser", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(28, "GeneratedJsonAdapter(AppDTO)");
    }

    @NotNull
    public AppDTO fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        jsonReader.beginObject();
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        String str9 = null;
        String str10 = null;
        String str11 = null;
        while (jsonReader.hasNext()) {
            switch (jsonReader.selectName(this.options)) {
                case -1:
                    jsonReader.skipName();
                    jsonReader.skipValue();
                    break;
                case 0:
                    str = this.nullableStringAdapter.fromJson(jsonReader);
                    break;
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
                    str6 = this.nullableStringAdapter.fromJson(jsonReader);
                    break;
                case 6:
                    str7 = this.nullableStringAdapter.fromJson(jsonReader);
                    break;
                case 7:
                    str8 = this.nullableStringAdapter.fromJson(jsonReader);
                    break;
                case 8:
                    str9 = this.nullableStringAdapter.fromJson(jsonReader);
                    break;
                case 9:
                    str10 = this.nullableStringAdapter.fromJson(jsonReader);
                    break;
                case 10:
                    str11 = this.nullableStringAdapter.fromJson(jsonReader);
                    break;
            }
        }
        jsonReader.endObject();
        return new AppDTO(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11);
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable AppDTO appDTO) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (appDTO != null) {
            jsonWriter.beginObject();
            jsonWriter.name("browser");
            this.nullableStringAdapter.toJson(jsonWriter, appDTO.getBrowser());
            jsonWriter.name("ios");
            this.nullableStringAdapter.toJson(jsonWriter, appDTO.getIos());
            jsonWriter.name(DeviceInfo.OS_NAME);
            this.nullableStringAdapter.toJson(jsonWriter, appDTO.getAndroid());
            jsonWriter.name("mac");
            this.nullableStringAdapter.toJson(jsonWriter, appDTO.getMac());
            jsonWriter.name("windows");
            this.nullableStringAdapter.toJson(jsonWriter, appDTO.getWindows());
            jsonWriter.name("linux");
            this.nullableStringAdapter.toJson(jsonWriter, appDTO.getLinux());
            jsonWriter.name("chrome");
            this.nullableStringAdapter.toJson(jsonWriter, appDTO.getChrome());
            jsonWriter.name("firefox");
            this.nullableStringAdapter.toJson(jsonWriter, appDTO.getFirefox());
            jsonWriter.name("safari");
            this.nullableStringAdapter.toJson(jsonWriter, appDTO.getSafari());
            jsonWriter.name("edge");
            this.nullableStringAdapter.toJson(jsonWriter, appDTO.getEdge());
            jsonWriter.name("opera");
            this.nullableStringAdapter.toJson(jsonWriter, appDTO.getOpera());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
