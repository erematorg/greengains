package com.appsamurai.storyly.data.managers.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Base64;
import com.amplitude.api.Constants;
import com.appsamurai.storyly.StorylyInit;
import com.appsamurai.storyly.config.StorylyConfig;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonArrayBuilder;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementBuildersKt;
import kotlinx.serialization.json.JsonNull;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonObjectBuilder;

public final class g {
    public static JsonObject a(Context context, StorylyInit storylyInit, String str, String str2, JsonObject jsonObject, Map map, int i3) {
        String str3;
        JsonElement jsonElement;
        Set<Map.Entry<String, JsonElement>> entrySet;
        StorylyConfig config;
        StorylyConfig config2;
        StorylyConfig config3;
        Context context2 = context;
        StorylyInit storylyInit2 = (i3 & 2) != 0 ? null : storylyInit;
        String str4 = (i3 & 4) != 0 ? null : str;
        String str5 = (i3 & 8) != 0 ? null : str2;
        JsonObject jsonObject2 = (i3 & 16) != 0 ? null : jsonObject;
        Map map2 = (i3 & 32) != 0 ? null : map;
        Intrinsics.checkNotNullParameter(context2, "context");
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
        JsonObjectBuilder jsonObjectBuilder2 = new JsonObjectBuilder();
        String packageName = context.getPackageName();
        String str6 = "";
        if (packageName == null) {
            packageName = str6;
        }
        JsonElementBuildersKt.put(jsonObjectBuilder2, "bundle", packageName);
        String str7 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        if (str7 != null) {
            str6 = str7;
        }
        JsonElementBuildersKt.put(jsonObjectBuilder2, "version", str6);
        jsonObjectBuilder.put("app", jsonObjectBuilder2.build());
        JsonObjectBuilder jsonObjectBuilder3 = new JsonObjectBuilder();
        JsonElementBuildersKt.put(jsonObjectBuilder3, "is_test", Boolean.valueOf((storylyInit2 == null || (config3 = storylyInit2.getConfig()) == null) ? false : config3.isTestMode$storyly_release()));
        Charset charset = Charsets.UTF_8;
        byte[] bytes = "stryly-was-here".getBytes(charset);
        Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
        String encodeToString = Base64.encodeToString(bytes, 2);
        SharedPreferences sharedPreferences = context2.getSharedPreferences("stryly-id", 0);
        String string = sharedPreferences.getString(encodeToString, (String) null);
        if (string == null) {
            String stringPlus = Intrinsics.stringPlus("stryly-", UUID.randomUUID());
            if (stringPlus != null) {
                byte[] bytes2 = stringPlus.getBytes(charset);
                Intrinsics.checkNotNullExpressionValue(bytes2, "(this as java.lang.String).getBytes(charset)");
                str3 = Base64.encodeToString(bytes2, 2);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString(encodeToString, str3);
                edit.apply();
            } else {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
        } else {
            str3 = string;
        }
        JsonElementBuildersKt.put(jsonObjectBuilder3, "unique_id", str3);
        String locale = context.getResources().getConfiguration().getLocales().get(0).toString();
        Intrinsics.checkNotNullExpressionValue(locale, "context.resources.config…ion.locales[0].toString()");
        JsonElementBuildersKt.put(jsonObjectBuilder3, "locale", locale);
        String str8 = Build.BRAND;
        Intrinsics.checkNotNullExpressionValue(str8, "BRAND");
        JsonElementBuildersKt.put(jsonObjectBuilder3, "make", str8);
        String str9 = Build.MODEL;
        Intrinsics.checkNotNullExpressionValue(str9, "MODEL");
        JsonElementBuildersKt.put(jsonObjectBuilder3, "model", str9);
        JsonObjectBuilder jsonObjectBuilder4 = new JsonObjectBuilder();
        JsonElementBuildersKt.put(jsonObjectBuilder4, "name", "ANDROID");
        String str10 = Build.VERSION.RELEASE;
        Intrinsics.checkNotNullExpressionValue(str10, "RELEASE");
        JsonElementBuildersKt.put(jsonObjectBuilder4, "version", str10);
        jsonObjectBuilder3.put("os", jsonObjectBuilder4.build());
        jsonObjectBuilder.put("device", jsonObjectBuilder3.build());
        JsonObjectBuilder jsonObjectBuilder5 = new JsonObjectBuilder();
        String country = context.getResources().getConfiguration().getLocales().get(0).getCountry();
        Intrinsics.checkNotNullExpressionValue(country, "context.resources.configuration.locales[0].country");
        Locale locale2 = Locale.ENGLISH;
        Intrinsics.checkNotNullExpressionValue(locale2, "ENGLISH");
        String upperCase = country.toUpperCase(locale2);
        Intrinsics.checkNotNullExpressionValue(upperCase, "(this as java.lang.String).toUpperCase(locale)");
        JsonElementBuildersKt.put(jsonObjectBuilder5, Constants.AMP_TRACKING_OPTION_COUNTRY, upperCase);
        jsonObjectBuilder.put("geo", jsonObjectBuilder5.build());
        JsonElementBuildersKt.put(jsonObjectBuilder, "sdk_version", "2.4.0");
        JsonObjectBuilder jsonObjectBuilder6 = new JsonObjectBuilder();
        if (((storylyInit2 == null || (config2 = storylyInit2.getConfig()) == null) ? null : config2.getLabels()) == null) {
            jsonElement = JsonNull.INSTANCE;
        } else {
            Set<String> labels = storylyInit2.getConfig().getLabels();
            if (labels != null && labels.isEmpty()) {
                jsonElement = new JsonArray(CollectionsKt.emptyList());
            } else {
                JsonArrayBuilder jsonArrayBuilder = new JsonArrayBuilder();
                Set<String> labels2 = storylyInit2.getConfig().getLabels();
                if (labels2 != null) {
                    for (String add : labels2) {
                        JsonElementBuildersKt.add(jsonArrayBuilder, add);
                    }
                }
                Unit unit = Unit.INSTANCE;
                jsonElement = jsonArrayBuilder.build();
            }
        }
        jsonObjectBuilder6.put("segments", jsonElement);
        if (map2 != null && !map2.isEmpty()) {
            JsonObjectBuilder jsonObjectBuilder7 = new JsonObjectBuilder();
            for (Map.Entry entry : map2.entrySet()) {
                JsonElementBuildersKt.put(jsonObjectBuilder7, (String) entry.getKey(), (String) entry.getValue());
            }
            Unit unit2 = Unit.INSTANCE;
            jsonObjectBuilder6.put("ab_sets", jsonObjectBuilder7.build());
        }
        jsonObjectBuilder.put("user", jsonObjectBuilder6.build());
        JsonElementBuildersKt.put(jsonObjectBuilder, "custom_parameter", (storylyInit2 == null || (config = storylyInit2.getConfig()) == null) ? null : config.getCustomParameter$storyly_release());
        JsonElementBuildersKt.put(jsonObjectBuilder, "session_id", str4);
        JsonElementBuildersKt.put(jsonObjectBuilder, "preview_session_id", str5);
        if (!(jsonObject2 == null || (entrySet = jsonObject2.entrySet()) == null)) {
            for (Map.Entry entry2 : entrySet) {
                jsonObjectBuilder.put((String) entry2.getKey(), (JsonElement) entry2.getValue());
            }
        }
        return jsonObjectBuilder.build();
    }
}
