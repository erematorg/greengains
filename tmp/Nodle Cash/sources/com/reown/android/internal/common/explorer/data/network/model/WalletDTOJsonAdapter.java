package com.reown.android.internal.common.explorer.data.network.model;

import A.a;
import com.reown.android.pulse.model.ConnectionMethod;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.internal.Util;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0010\u001a\u00020\nH\u0016J\u0010\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u001a\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/reown/android/internal/common/explorer/data/network/model/WalletDTOJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/android/internal/common/explorer/data/network/model/WalletDTO;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "stringAdapter", "", "nullableStringAdapter", "mobileDTOAdapter", "Lcom/reown/android/internal/common/explorer/data/network/model/MobileDTO;", "appDTOAdapter", "Lcom/reown/android/internal/common/explorer/data/network/model/AppDTO;", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class WalletDTOJsonAdapter extends JsonAdapter<WalletDTO> {
    @NotNull
    private final JsonAdapter<AppDTO> appDTOAdapter;
    @NotNull
    private final JsonAdapter<MobileDTO> mobileDTOAdapter;
    @NotNull
    private final JsonAdapter<String> nullableStringAdapter;
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<String> stringAdapter;

    public WalletDTOJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of(TtmlNode.ATTR_ID, "name", "description", "homepage", "image_id", ConnectionMethod.MOBILE, "app");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        Class<String> cls = String.class;
        this.stringAdapter = a.h(moshi, cls, TtmlNode.ATTR_ID, "adapter(...)");
        this.nullableStringAdapter = a.h(moshi, cls, "description", "adapter(...)");
        this.mobileDTOAdapter = a.h(moshi, MobileDTO.class, ConnectionMethod.MOBILE, "adapter(...)");
        this.appDTOAdapter = a.h(moshi, AppDTO.class, "app", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(31, "GeneratedJsonAdapter(WalletDTO)");
    }

    @NotNull
    public WalletDTO fromJson(@NotNull JsonReader jsonReader) {
        JsonReader jsonReader2 = jsonReader;
        Intrinsics.checkNotNullParameter(jsonReader2, "reader");
        jsonReader.beginObject();
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        MobileDTO mobileDTO = null;
        AppDTO appDTO = null;
        while (true) {
            String str6 = str3;
            AppDTO appDTO2 = appDTO;
            if (jsonReader.hasNext()) {
                switch (jsonReader2.selectName(this.options)) {
                    case -1:
                        jsonReader.skipName();
                        jsonReader.skipValue();
                        break;
                    case 0:
                        str = this.stringAdapter.fromJson(jsonReader2);
                        if (str == null) {
                            throw Util.unexpectedNull(TtmlNode.ATTR_ID, TtmlNode.ATTR_ID, jsonReader2);
                        }
                        break;
                    case 1:
                        str2 = this.stringAdapter.fromJson(jsonReader2);
                        if (str2 == null) {
                            throw Util.unexpectedNull("name", "name", jsonReader2);
                        }
                        break;
                    case 2:
                        str3 = this.nullableStringAdapter.fromJson(jsonReader2);
                        break;
                    case 3:
                        str4 = this.stringAdapter.fromJson(jsonReader2);
                        if (str4 == null) {
                            throw Util.unexpectedNull("homePage", "homepage", jsonReader2);
                        }
                        break;
                    case 4:
                        str5 = this.stringAdapter.fromJson(jsonReader2);
                        if (str5 == null) {
                            throw Util.unexpectedNull("imageId", "image_id", jsonReader2);
                        }
                        break;
                    case 5:
                        mobileDTO = this.mobileDTOAdapter.fromJson(jsonReader2);
                        if (mobileDTO == null) {
                            throw Util.unexpectedNull(ConnectionMethod.MOBILE, ConnectionMethod.MOBILE, jsonReader2);
                        }
                        break;
                    case 6:
                        AppDTO fromJson = this.appDTOAdapter.fromJson(jsonReader2);
                        if (fromJson != null) {
                            appDTO = fromJson;
                            str3 = str6;
                            continue;
                        } else {
                            throw Util.unexpectedNull("app", "app", jsonReader2);
                        }
                }
                str3 = str6;
                appDTO = appDTO2;
            } else {
                jsonReader.endObject();
                if (str == null) {
                    throw Util.missingProperty(TtmlNode.ATTR_ID, TtmlNode.ATTR_ID, jsonReader2);
                } else if (str2 == null) {
                    throw Util.missingProperty("name", "name", jsonReader2);
                } else if (str4 == null) {
                    throw Util.missingProperty("homePage", "homepage", jsonReader2);
                } else if (str5 == null) {
                    throw Util.missingProperty("imageId", "image_id", jsonReader2);
                } else if (mobileDTO == null) {
                    throw Util.missingProperty(ConnectionMethod.MOBILE, ConnectionMethod.MOBILE, jsonReader2);
                } else if (appDTO2 != null) {
                    return new WalletDTO(str, str2, str6, str4, str5, mobileDTO, appDTO2);
                } else {
                    throw Util.missingProperty("app", "app", jsonReader2);
                }
            }
        }
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable WalletDTO walletDTO) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (walletDTO != null) {
            jsonWriter.beginObject();
            jsonWriter.name(TtmlNode.ATTR_ID);
            this.stringAdapter.toJson(jsonWriter, walletDTO.getId());
            jsonWriter.name("name");
            this.stringAdapter.toJson(jsonWriter, walletDTO.getName());
            jsonWriter.name("description");
            this.nullableStringAdapter.toJson(jsonWriter, walletDTO.getDescription());
            jsonWriter.name("homepage");
            this.stringAdapter.toJson(jsonWriter, walletDTO.getHomePage());
            jsonWriter.name("image_id");
            this.stringAdapter.toJson(jsonWriter, walletDTO.getImageId());
            jsonWriter.name(ConnectionMethod.MOBILE);
            this.mobileDTOAdapter.toJson(jsonWriter, walletDTO.getMobile());
            jsonWriter.name("app");
            this.appDTOAdapter.toJson(jsonWriter, walletDTO.getApp());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
