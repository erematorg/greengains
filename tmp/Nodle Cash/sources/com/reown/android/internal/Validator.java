package com.reown.android.internal;

import androidx.compose.animation.core.a;
import androidx.emoji2.emojipicker.StickyVariantProvider;
import com.reown.android.internal.common.model.Expiry;
import com.reown.android.internal.common.model.RelayProtocolOptions;
import com.reown.android.internal.common.model.SymmetricKey;
import com.reown.android.internal.common.model.WalletConnectUri;
import com.reown.foundation.common.model.Topic;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\tJ\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/reown/android/internal/Validator;", "", "<init>", "()V", "WC_URI_QUERY_KEY", "", "validateWCUri", "Lcom/reown/android/internal/common/model/WalletConnectUri;", "uri", "validateWCUri$android_release", "getWcUri", "uriScheme", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nValidator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Validator.kt\ncom/reown/android/internal/Validator\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,72:1\n1193#2,2:73\n1267#2,4:75\n1#3:79\n*S KotlinDebug\n*F\n+ 1 Validator.kt\ncom/reown/android/internal/Validator\n*L\n37#1:73,2\n37#1:75,4\n*E\n"})
public final class Validator {
    @NotNull
    public static final Validator INSTANCE = new Validator();
    @NotNull
    private static final String WC_URI_QUERY_KEY = "wc?uri=";

    private Validator() {
    }

    private final String getWcUri(String str) {
        try {
            String decode = URLDecoder.decode(StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) WC_URI_QUERY_KEY, false, 2, (Object) null) ? (String) StringsKt__StringsKt.split$default((CharSequence) str, new String[]{WC_URI_QUERY_KEY}, false, 0, 6, (Object) null).get(1) : str, "UTF-8");
            Intrinsics.checkNotNull(decode);
            return decode;
        } catch (Throwable unused) {
            return str;
        }
    }

    public final /* synthetic */ WalletConnectUri validateWCUri$android_release(String str) {
        Intrinsics.checkNotNullParameter(str, "uri");
        String wcUri = getWcUri(str);
        Expiry expiry = null;
        if (!StringsKt__StringsJVMKt.startsWith$default(wcUri, "wc:", false, 2, (Object) null)) {
            return null;
        }
        if (!StringsKt__StringsKt.contains$default((CharSequence) wcUri, (CharSequence) "wc://", false, 2, (Object) null)) {
            wcUri = StringsKt__StringsKt.contains$default((CharSequence) wcUri, (CharSequence) "wc:/", false, 2, (Object) null) ? StringsKt__StringsJVMKt.replace$default(wcUri, "wc:/", "wc://", false, 4, (Object) null) : StringsKt__StringsJVMKt.replace$default(wcUri, "wc:", "wc://", false, 4, (Object) null);
        }
        try {
            URI uri = new URI(wcUri);
            String userInfo = uri.getUserInfo();
            Intrinsics.checkNotNullExpressionValue(userInfo, "getUserInfo(...)");
            if (userInfo.length() == 0) {
                return null;
            }
            String query = uri.getQuery();
            Intrinsics.checkNotNullExpressionValue(query, "getQuery(...)");
            Iterable<String> Z2 = StringsKt__StringsKt.split$default((CharSequence) query, new String[]{"&"}, false, 0, 6, (Object) null);
            LinkedHashMap linkedHashMap = new LinkedHashMap(a.h(Z2, 16));
            for (String str2 : Z2) {
                Pair pair = TuplesKt.to(StringsKt__StringsKt.substringBefore$default(str2, StickyVariantProvider.KEY_VALUE_DELIMITER, (String) null, 2, (Object) null), StringsKt__StringsKt.substringAfter$default(str2, StickyVariantProvider.KEY_VALUE_DELIMITER, (String) null, 2, (Object) null));
                linkedHashMap.put(pair.getFirst(), pair.getSecond());
            }
            String str3 = (String) linkedHashMap.get("relay-protocol");
            if (str3 == null || str3.length() == 0) {
                return null;
            }
            String str4 = (String) linkedHashMap.get("relay-data");
            String str5 = (String) linkedHashMap.get("expiryTimestamp");
            String str6 = (String) linkedHashMap.get("methods");
            String str7 = (String) linkedHashMap.get("symKey");
            if (str7 == null || str7.length() == 0) {
                return null;
            }
            String userInfo2 = uri.getUserInfo();
            Intrinsics.checkNotNullExpressionValue(userInfo2, "getUserInfo(...)");
            Topic topic = new Topic(userInfo2);
            RelayProtocolOptions relayProtocolOptions = new RelayProtocolOptions(str3, str4);
            String r10 = SymmetricKey.m8778constructorimpl(str7);
            if (str5 != null) {
                expiry = new Expiry(Long.parseLong(str5));
            }
            return new WalletConnectUri(topic, r10, relayProtocolOptions, (String) null, expiry, str6, 8, (DefaultConstructorMarker) null);
        } catch (URISyntaxException unused) {
        }
        return null;
    }
}
