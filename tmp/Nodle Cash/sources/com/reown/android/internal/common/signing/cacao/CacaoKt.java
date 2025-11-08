package com.reown.android.internal.common.signing.cacao;

import android.support.v4.media.session.a;
import com.fasterxml.jackson.core.JsonPointer;
import com.reown.android.internal.common.di.b;
import com.reown.android.internal.common.signing.cacao.Cacao;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\u0014\u0010\u0003\u001a\u00020\u0004*\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0004\u001a\"\u0010\u0007\u001a\u0004\u0018\u00010\u0004*\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\t0\b\u001a\u0014\u0010\n\u001a\u00020\u0004*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\tH\u0002\"\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"toSignature", "Lcom/reown/android/internal/common/signing/signature/Signature;", "Lcom/reown/android/internal/common/signing/cacao/Cacao$Signature;", "toCAIP222Message", "", "Lcom/reown/android/internal/common/signing/cacao/Cacao$Payload;", "chainName", "getStatement", "Lkotlin/Pair;", "", "getActionsString", "RECAPS_STATEMENT", "android_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCacao.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Cacao.kt\ncom/reown/android/internal/common/signing/cacao/CacaoKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,137:1\n1#2:138\n1869#3,2:139\n216#4,2:141\n*S KotlinDebug\n*F\n+ 1 Cacao.kt\ncom/reown/android/internal/common/signing/cacao/CacaoKt\n*L\n98#1:139,2\n122#1:141,2\n*E\n"})
public final class CacaoKt {
    @NotNull
    public static final String RECAPS_STATEMENT = "I further authorize the stated URI to perform the following actions on my behalf";

    /* access modifiers changed from: private */
    public static final String getActionsString(List<String> list) {
        String str;
        String str2;
        Map parseReCaps = UtilsKt.parseReCaps(UtilsKt.decodeReCaps(list));
        if (!parseReCaps.isEmpty()) {
            int i3 = 1;
            String str3 = "";
            for (Map.Entry entry : parseReCaps.entrySet()) {
                String str4 = (String) entry.getKey();
                Map map = (Map) entry.getValue();
                String str5 = (String) CollectionsKt.firstOrNull(map.keySet());
                if (str5 == null || (str = StringsKt__StringsKt.substringBefore$default(str5, (char) JsonPointer.SEPARATOR, (String) null, 2, (Object) null)) == null) {
                    str = "";
                }
                String n2 = CollectionsKt___CollectionsKt.joinToString$default(CollectionsKt.sorted(map.keySet()), ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new b(7), 30, (Object) null);
                if (i3 == parseReCaps.size()) {
                    StringBuilder sb = new StringBuilder("(");
                    sb.append(i3);
                    sb.append(") '");
                    sb.append(str);
                    sb.append("': ");
                    str2 = a.r(sb, n2, " for '", str4, "'");
                } else {
                    StringBuilder sb2 = new StringBuilder("(");
                    sb2.append(i3);
                    sb2.append(") '");
                    sb2.append(str);
                    sb2.append("': ");
                    str2 = a.r(sb2, n2, " for '", str4, "'. ");
                }
                str3 = str3 + str2;
                i3++;
            }
            return str3;
        }
        throw new Exception("Decoded ReCaps map is empty");
    }

    /* access modifiers changed from: private */
    public static final CharSequence getActionsString$lambda$4$lambda$3(String str) {
        Intrinsics.checkNotNullParameter(str, "it");
        return A.a.l("'", StringsKt__StringsKt.substringAfter$default(str, (char) JsonPointer.SEPARATOR, (String) null, 2, (Object) null), "'");
    }

    @Nullable
    public static final String getStatement(@NotNull Pair<String, ? extends List<String>> pair) {
        String str;
        Object obj;
        Intrinsics.checkNotNullParameter(pair, "<this>");
        String component1 = pair.component1();
        List list = (List) pair.component2();
        String str2 = component1 != null ? component1 : "";
        if (list != null) {
            Iterator it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (StringsKt__StringsJVMKt.startsWith$default((String) obj, Cacao.Payload.RECAPS_PREFIX, false, 2, (Object) null)) {
                    break;
                }
            }
            str = (String) obj;
        } else {
            str = null;
        }
        if (str != null) {
            str2 = androidx.constraintlayout.core.state.b.m(str2.concat(component1 != null ? StringUtils.SPACE : ""), "I further authorize the stated URI to perform the following actions on my behalf: ", getActionsString(list), JwtUtilsKt.JWT_DELIMITER);
        }
        if (Intrinsics.areEqual((Object) str2, (Object) "")) {
            return null;
        }
        return str2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.String toCAIP222Message(@org.jetbrains.annotations.NotNull com.reown.android.internal.common.signing.cacao.Cacao.Payload r6, @org.jetbrains.annotations.NotNull java.lang.String r7) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.String r0 = "chainName"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.lang.String r0 = r6.getDomain()
            com.reown.android.internal.common.signing.cacao.Issuer r1 = new com.reown.android.internal.common.signing.cacao.Issuer
            java.lang.String r2 = r6.getIss()
            r1.<init>(r2)
            java.lang.String r1 = r1.getAddress()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            java.lang.String r0 = " wants you to sign in with your "
            r2.append(r0)
            r2.append(r7)
            java.lang.String r7 = " account:\n"
            r2.append(r7)
            r2.append(r1)
            java.lang.String r7 = "\n\n"
            r2.append(r7)
            java.lang.String r7 = r2.toString()
            java.lang.String r0 = r6.getStatement()
            java.lang.String r1 = "\n"
            if (r0 == 0) goto L_0x0065
            java.lang.String r2 = "I further authorize the stated URI to perform the following actions on my behalf"
            boolean r0 = kotlin.text.StringsKt__StringsKt.contains$default((java.lang.CharSequence) r0, (java.lang.CharSequence) r2, false, 2, (java.lang.Object) null)
            r2 = 1
            if (r0 != r2) goto L_0x0065
            java.lang.String r0 = r6.getStatement()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r7)
            r2.append(r0)
            r2.append(r1)
            java.lang.String r7 = r2.toString()
            goto L_0x00f6
        L_0x0065:
            java.lang.String r0 = r6.getStatement()
            if (r0 == 0) goto L_0x007e
            java.lang.String r0 = r6.getStatement()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r7)
            r2.append(r0)
            java.lang.String r7 = r2.toString()
        L_0x007e:
            java.util.List r0 = r6.getResources()
            r2 = 0
            if (r0 == 0) goto L_0x00a3
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Iterator r0 = r0.iterator()
        L_0x008b:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x00a1
            java.lang.Object r3 = r0.next()
            r4 = r3
            java.lang.String r4 = (java.lang.String) r4
            java.lang.String r5 = "urn:recap:"
            boolean r4 = kotlin.text.StringsKt__StringsJVMKt.startsWith$default(r4, r5, false, 2, (java.lang.Object) null)
            if (r4 == 0) goto L_0x008b
            r2 = r3
        L_0x00a1:
            java.lang.String r2 = (java.lang.String) r2
        L_0x00a3:
            if (r2 == 0) goto L_0x00e1
            java.lang.String r0 = r6.getStatement()
            if (r0 == 0) goto L_0x00ae
            java.lang.String r0 = " "
            goto L_0x00b0
        L_0x00ae:
            java.lang.String r0 = ""
        L_0x00b0:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r7)
            r1.append(r0)
            java.lang.String r7 = r1.toString()
            java.util.List r0 = r6.getResources()
            java.lang.String r0 = getActionsString(r0)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r7)
            java.lang.String r7 = "I further authorize the stated URI to perform the following actions on my behalf: "
            r1.append(r7)
            r1.append(r0)
            java.lang.String r7 = ".\n"
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            goto L_0x00f6
        L_0x00e1:
            java.lang.String r0 = r6.getStatement()
            if (r0 == 0) goto L_0x00f6
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r7)
            r0.append(r1)
            java.lang.String r7 = r0.toString()
        L_0x00f6:
            java.lang.String r0 = r6.getAud()
            java.lang.String r1 = r6.getVersion()
            com.reown.android.internal.common.signing.cacao.Issuer r2 = new com.reown.android.internal.common.signing.cacao.Issuer
            java.lang.String r3 = r6.getIss()
            r2.<init>(r3)
            java.lang.String r2 = r2.getChainIdReference()
            java.lang.String r3 = r6.getNonce()
            java.lang.String r4 = r6.getIat()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r7)
            java.lang.String r7 = "\nURI: "
            r5.append(r7)
            r5.append(r0)
            java.lang.String r7 = "\nVersion: "
            r5.append(r7)
            r5.append(r1)
            java.lang.String r7 = "\nChain ID: "
            java.lang.String r0 = "\nNonce: "
            androidx.constraintlayout.core.state.b.w(r5, r7, r2, r0, r3)
            java.lang.String r7 = "\nIssued At: "
            java.lang.String r7 = A.a.n(r5, r7, r4)
            java.lang.String r0 = r6.getExp()
            if (r0 == 0) goto L_0x0156
            java.lang.String r0 = r6.getExp()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r7)
            java.lang.String r7 = "\nExpiration Time: "
            r1.append(r7)
            r1.append(r0)
            java.lang.String r7 = r1.toString()
        L_0x0156:
            java.lang.String r0 = r6.getNbf()
            if (r0 == 0) goto L_0x0174
            java.lang.String r0 = r6.getNbf()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r7)
            java.lang.String r7 = "\nNot Before: "
            r1.append(r7)
            r1.append(r0)
            java.lang.String r7 = r1.toString()
        L_0x0174:
            java.lang.String r0 = r6.getRequestId()
            if (r0 == 0) goto L_0x0192
            java.lang.String r0 = r6.getRequestId()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r7)
            java.lang.String r7 = "\nRequest ID: "
            r1.append(r7)
            r1.append(r0)
            java.lang.String r7 = r1.toString()
        L_0x0192:
            java.util.List r0 = r6.getResources()
            if (r0 == 0) goto L_0x01d4
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r7)
            java.lang.String r7 = "\nResources:"
            r0.append(r7)
            java.lang.String r7 = r0.toString()
            java.util.List r6 = r6.getResources()
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.Iterator r6 = r6.iterator()
        L_0x01b3:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto L_0x01d4
            java.lang.Object r0 = r6.next()
            java.lang.String r0 = (java.lang.String) r0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r7)
            java.lang.String r7 = "\n- "
            r1.append(r7)
            r1.append(r0)
            java.lang.String r7 = r1.toString()
            goto L_0x01b3
        L_0x01d4:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.internal.common.signing.cacao.CacaoKt.toCAIP222Message(com.reown.android.internal.common.signing.cacao.Cacao$Payload, java.lang.String):java.lang.String");
    }

    public static /* synthetic */ String toCAIP222Message$default(Cacao.Payload payload, String str, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = "Ethereum";
        }
        return toCAIP222Message(payload, str);
    }
}
