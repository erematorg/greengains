package com.google.android.gms.common.util;

import androidx.annotation.NonNull;
import androidx.compose.ui.autofill.a;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.HashMap;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.text.StringSubstitutor;

@KeepForSdk
public class MapUtils {
    @KeepForSdk
    public static void writeStringMapToJson(@NonNull StringBuilder sb, @NonNull HashMap<String, String> hashMap) {
        sb.append("{");
        boolean z2 = true;
        for (String next : hashMap.keySet()) {
            if (!z2) {
                sb.append(",");
            }
            String str = hashMap.get(next);
            a.o(sb, "\"", next, "\":");
            if (str == null) {
                sb.append(AbstractJsonLexerKt.NULL);
            } else {
                a.o(sb, "\"", str, "\"");
            }
            z2 = false;
        }
        sb.append(StringSubstitutor.DEFAULT_VAR_END);
    }
}
