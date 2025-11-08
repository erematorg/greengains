package com.reown.android.pulse.model.properties;

import A.a;
import com.amplitude.api.Constants;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.reown.android.push.notifications.PushMessagingService;
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

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0013\u001a\u00020\nH\u0016J\u0010\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u001a\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0001X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u000e0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0012X\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/reown/android/pulse/model/properties/PropertiesJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/android/pulse/model/properties/Properties;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "nullableStringAdapter", "", "nullableBooleanAdapter", "", "nullableListOfStringAdapter", "", "nullableLongAdapter", "", "constructorRef", "Ljava/lang/reflect/Constructor;", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPropertiesJsonAdapter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PropertiesJsonAdapter.kt\ncom/reown/android/pulse/model/properties/PropertiesJsonAdapter\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,214:1\n1#2:215\n*E\n"})
public final class PropertiesJsonAdapter extends JsonAdapter<Properties> {
    @Nullable
    private volatile Constructor<Properties> constructorRef;
    @NotNull
    private final JsonAdapter<Boolean> nullableBooleanAdapter;
    @NotNull
    private final JsonAdapter<List<String>> nullableListOfStringAdapter;
    @NotNull
    private final JsonAdapter<Long> nullableLongAdapter;
    @NotNull
    private final JsonAdapter<String> nullableStringAdapter;
    @NotNull
    private final JsonReader.Options options;

    public PropertiesJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of(PushMessagingService.KEY_MESSAGE, "name", FirebaseAnalytics.Param.METHOD, "connected", "network", Constants.AMP_TRACKING_OPTION_PLATFORM, "trace", PushMessagingService.KEY_TOPIC, "correlation_id", "client_id", "direction", "user_agent");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        Class<String> cls = String.class;
        this.nullableStringAdapter = a.h(moshi, cls, PushMessagingService.KEY_MESSAGE, "adapter(...)");
        this.nullableBooleanAdapter = a.h(moshi, Boolean.class, "connected", "adapter(...)");
        this.nullableListOfStringAdapter = a.i(moshi, Types.newParameterizedType(List.class, cls), "trace", "adapter(...)");
        this.nullableLongAdapter = a.h(moshi, Long.class, "correlationId", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(32, "GeneratedJsonAdapter(Properties)");
    }

    @NotNull
    public Properties fromJson(@NotNull JsonReader jsonReader) {
        JsonReader jsonReader2 = jsonReader;
        Intrinsics.checkNotNullParameter(jsonReader2, "reader");
        jsonReader.beginObject();
        String str = null;
        int i3 = -1;
        String str2 = null;
        String str3 = null;
        Boolean bool = null;
        String str4 = null;
        String str5 = null;
        List list = null;
        String str6 = null;
        Long l2 = null;
        String str7 = null;
        String str8 = null;
        String str9 = null;
        while (jsonReader.hasNext()) {
            switch (jsonReader2.selectName(this.options)) {
                case -1:
                    jsonReader.skipName();
                    jsonReader.skipValue();
                    break;
                case 0:
                    str = this.nullableStringAdapter.fromJson(jsonReader2);
                    i3 &= -2;
                    break;
                case 1:
                    str2 = this.nullableStringAdapter.fromJson(jsonReader2);
                    i3 &= -3;
                    break;
                case 2:
                    str3 = this.nullableStringAdapter.fromJson(jsonReader2);
                    i3 &= -5;
                    break;
                case 3:
                    bool = this.nullableBooleanAdapter.fromJson(jsonReader2);
                    i3 &= -9;
                    break;
                case 4:
                    str4 = this.nullableStringAdapter.fromJson(jsonReader2);
                    i3 &= -17;
                    break;
                case 5:
                    str5 = this.nullableStringAdapter.fromJson(jsonReader2);
                    i3 &= -33;
                    break;
                case 6:
                    list = this.nullableListOfStringAdapter.fromJson(jsonReader2);
                    i3 &= -65;
                    break;
                case 7:
                    str6 = this.nullableStringAdapter.fromJson(jsonReader2);
                    i3 &= -129;
                    break;
                case 8:
                    l2 = this.nullableLongAdapter.fromJson(jsonReader2);
                    i3 &= -257;
                    break;
                case 9:
                    str7 = this.nullableStringAdapter.fromJson(jsonReader2);
                    i3 &= -513;
                    break;
                case 10:
                    str8 = this.nullableStringAdapter.fromJson(jsonReader2);
                    i3 &= -1025;
                    break;
                case 11:
                    str9 = this.nullableStringAdapter.fromJson(jsonReader2);
                    i3 &= -2049;
                    break;
            }
        }
        jsonReader.endObject();
        if (i3 == -4096) {
            return new Properties(str, str2, str3, bool, str4, str5, list, str6, l2, str7, str8, str9);
        }
        Constructor<Properties> constructor = this.constructorRef;
        if (constructor == null) {
            constructor = Properties.class.getDeclaredConstructor(new Class[]{String.class, String.class, String.class, Boolean.class, String.class, String.class, List.class, String.class, Long.class, String.class, String.class, String.class, Integer.TYPE, Util.DEFAULT_CONSTRUCTOR_MARKER});
            this.constructorRef = constructor;
            Intrinsics.checkNotNullExpressionValue(constructor, "also(...)");
        }
        Properties newInstance = constructor.newInstance(new Object[]{str, str2, str3, bool, str4, str5, list, str6, l2, str7, str8, str9, Integer.valueOf(i3), null});
        Intrinsics.checkNotNullExpressionValue(newInstance, "newInstance(...)");
        return newInstance;
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable Properties properties) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (properties != null) {
            jsonWriter.beginObject();
            jsonWriter.name(PushMessagingService.KEY_MESSAGE);
            this.nullableStringAdapter.toJson(jsonWriter, properties.getMessage());
            jsonWriter.name("name");
            this.nullableStringAdapter.toJson(jsonWriter, properties.getName());
            jsonWriter.name(FirebaseAnalytics.Param.METHOD);
            this.nullableStringAdapter.toJson(jsonWriter, properties.getMethod());
            jsonWriter.name("connected");
            this.nullableBooleanAdapter.toJson(jsonWriter, properties.getConnected());
            jsonWriter.name("network");
            this.nullableStringAdapter.toJson(jsonWriter, properties.getNetwork());
            jsonWriter.name(Constants.AMP_TRACKING_OPTION_PLATFORM);
            this.nullableStringAdapter.toJson(jsonWriter, properties.getPlatform());
            jsonWriter.name("trace");
            this.nullableListOfStringAdapter.toJson(jsonWriter, properties.getTrace());
            jsonWriter.name(PushMessagingService.KEY_TOPIC);
            this.nullableStringAdapter.toJson(jsonWriter, properties.getTopic());
            jsonWriter.name("correlation_id");
            this.nullableLongAdapter.toJson(jsonWriter, properties.getCorrelationId());
            jsonWriter.name("client_id");
            this.nullableStringAdapter.toJson(jsonWriter, properties.getClientId());
            jsonWriter.name("direction");
            this.nullableStringAdapter.toJson(jsonWriter, properties.getDirection());
            jsonWriter.name("user_agent");
            this.nullableStringAdapter.toJson(jsonWriter, properties.getUserAgent());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
