package com.reown.sign.engine.model.tvf;

import androidx.compose.animation.core.a;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.CharsKt;
import kotlin.text.Charsets;
import org.bouncycastle.crypto.digests.SHA512tDigest;
import org.bouncycastle.util.encoders.Base64;
import org.jetbrains.annotations.NotNull;
import org.msgpack.core.MessagePack;
import org.msgpack.core.MessagePacker;
import org.msgpack.core.MessageUnpacker;
import org.msgpack.value.ImmutableMapValue;
import org.msgpack.value.Value;
import org.msgpack.value.ValueFactory;
import uniffi.xmtpv3.j;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0003\u001a\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001\u001a\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0002\u001a\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0002¨\u0006\t"}, d2 = {"calculateTxIDs", "", "", "signedTxnBase64List", "bytesToBase32", "bytes", "", "extractCanonicalTransaction", "signedTxnBytes", "sign_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAlgorand.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Algorand.kt\ncom/reown/sign/engine/model/tvf/AlgorandKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,70:1\n1563#2:71\n1634#2,3:72\n*S KotlinDebug\n*F\n+ 1 Algorand.kt\ncom/reown/sign/engine/model/tvf/AlgorandKt\n*L\n11#1:71\n11#1:72,3\n*E\n"})
public final class AlgorandKt {
    private static final String bytesToBase32(byte[] bArr) {
        String E2 = ArraysKt___ArraysKt.joinToString$default(bArr, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) new j(6), 30, (Object) null);
        StringBuilder sb = new StringBuilder();
        int i3 = 0;
        while (i3 < E2.length()) {
            int i4 = i3 + 5;
            String substring = E2.substring(i3, Math.min(i4, E2.length()));
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            if (substring.length() == 5) {
                sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ234567".charAt(Integer.parseInt(substring, CharsKt.checkRadix(2))));
            } else if (substring.length() > 0) {
                sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ234567".charAt(Integer.parseInt(StringsKt__StringsKt.padEnd(substring, 5, '0'), CharsKt.checkRadix(2))));
            }
            i3 = i4;
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    /* access modifiers changed from: private */
    public static final CharSequence bytesToBase32$lambda$1(byte b3) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        return StringsKt__StringsJVMKt.replace$default(a.t(new Object[]{Integer.toBinaryString(b3 & 255)}, 1, "%8s", "format(...)"), ' ', '0', false, 4, (Object) null);
    }

    @NotNull
    public static final List<String> calculateTxIDs(@NotNull List<String> list) {
        Intrinsics.checkNotNullParameter(list, "signedTxnBase64List");
        try {
            Iterable<String> iterable = list;
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10));
            for (String decode : iterable) {
                byte[] decode2 = Base64.decode(decode);
                Intrinsics.checkNotNull(decode2);
                byte[] extractCanonicalTransaction = extractCanonicalTransaction(decode2);
                byte[] bytes = "TX".getBytes(Charsets.US_ASCII);
                Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                byte[] plus = ArraysKt.plus(bytes, extractCanonicalTransaction);
                SHA512tDigest sHA512tDigest = new SHA512tDigest(256);
                sHA512tDigest.update(plus, 0, plus.length);
                byte[] bArr = new byte[32];
                sHA512tDigest.doFinal(bArr, 0);
                arrayList.add(bytesToBase32(bArr));
            }
            return arrayList;
        } catch (Exception unused) {
            return CollectionsKt.listOf("");
        }
    }

    private static final byte[] extractCanonicalTransaction(byte[] bArr) {
        MessageUnpacker newDefaultUnpacker = MessagePack.newDefaultUnpacker(bArr);
        try {
            ImmutableMapValue asMapValue = newDefaultUnpacker.unpackValue().asMapValue();
            Value value = asMapValue.map().get(ValueFactory.newString("txn"));
            if (value != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                MessagePacker newDefaultPacker = MessagePack.newDefaultPacker((OutputStream) byteArrayOutputStream);
                newDefaultPacker.packValue(value);
                newDefaultPacker.close();
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                Intrinsics.checkNotNullExpressionValue(byteArray, "toByteArray(...)");
                newDefaultUnpacker.close();
                return byteArray;
            }
            throw new IllegalArgumentException("No 'txn' field found in signed transaction");
        } catch (Exception e3) {
            throw new IllegalArgumentException("Failed to parse signed transaction MessagePack", e3);
        } catch (Throwable th) {
            newDefaultUnpacker.close();
            throw th;
        }
    }
}
