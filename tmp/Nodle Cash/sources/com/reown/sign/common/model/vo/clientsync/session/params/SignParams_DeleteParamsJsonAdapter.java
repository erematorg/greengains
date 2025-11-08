package com.reown.sign.common.model.vo.clientsync.session.params;

import A.a;
import com.reown.android.push.notifications.PushMessagingService;
import com.reown.sign.common.model.vo.clientsync.session.params.SignParams;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.internal.Util;
import java.lang.reflect.Constructor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u000f\u001a\u00020\fH\u0016J\u0010\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001a\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams_DeleteParamsJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$DeleteParams;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "intAdapter", "", "stringAdapter", "", "constructorRef", "Ljava/lang/reflect/Constructor;", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SignParams_DeleteParamsJsonAdapter extends JsonAdapter<SignParams.DeleteParams> {
    @Nullable
    private volatile Constructor<SignParams.DeleteParams> constructorRef;
    @NotNull
    private final JsonAdapter<Integer> intAdapter;
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<String> stringAdapter;

    public SignParams_DeleteParamsJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of("code", PushMessagingService.KEY_MESSAGE);
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        this.intAdapter = a.h(moshi, Integer.TYPE, "code", "adapter(...)");
        this.stringAdapter = a.h(moshi, String.class, PushMessagingService.KEY_MESSAGE, "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(45, "GeneratedJsonAdapter(SignParams.DeleteParams)");
    }

    @NotNull
    public SignParams.DeleteParams fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        Integer num = 0;
        jsonReader.beginObject();
        String str = null;
        int i3 = -1;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(this.options);
            if (selectName == -1) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else if (selectName == 0) {
                num = this.intAdapter.fromJson(jsonReader);
                if (num != null) {
                    i3 = -2;
                } else {
                    throw Util.unexpectedNull("code", "code", jsonReader);
                }
            } else if (selectName == 1 && (str = this.stringAdapter.fromJson(jsonReader)) == null) {
                throw Util.unexpectedNull(PushMessagingService.KEY_MESSAGE, PushMessagingService.KEY_MESSAGE, jsonReader);
            }
        }
        jsonReader.endObject();
        if (i3 == -2) {
            int intValue = num.intValue();
            if (str != null) {
                return new SignParams.DeleteParams(intValue, str);
            }
            throw Util.missingProperty(PushMessagingService.KEY_MESSAGE, PushMessagingService.KEY_MESSAGE, jsonReader);
        }
        Constructor<SignParams.DeleteParams> constructor = this.constructorRef;
        if (constructor == null) {
            Class cls = Integer.TYPE;
            constructor = SignParams.DeleteParams.class.getDeclaredConstructor(new Class[]{cls, String.class, cls, Util.DEFAULT_CONSTRUCTOR_MARKER});
            this.constructorRef = constructor;
            Intrinsics.checkNotNullExpressionValue(constructor, "also(...)");
        }
        if (str != null) {
            SignParams.DeleteParams newInstance = constructor.newInstance(new Object[]{num, str, Integer.valueOf(i3), null});
            Intrinsics.checkNotNullExpressionValue(newInstance, "newInstance(...)");
            return newInstance;
        }
        throw Util.missingProperty(PushMessagingService.KEY_MESSAGE, PushMessagingService.KEY_MESSAGE, jsonReader);
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable SignParams.DeleteParams deleteParams) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (deleteParams != null) {
            jsonWriter.beginObject();
            jsonWriter.name("code");
            this.intAdapter.toJson(jsonWriter, Integer.valueOf(deleteParams.getCode()));
            jsonWriter.name(PushMessagingService.KEY_MESSAGE);
            this.stringAdapter.toJson(jsonWriter, deleteParams.getMessage());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
