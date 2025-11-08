package com.reown.util;

import A.a;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.security.SecureRandom;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u001a\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u0006\u0010\u0007\u001a\u00020\u0006\u001a\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b\u001a\n\u0010\f\u001a\u00020\u0001*\u00020\t\u001a\u0012\u0010\r\u001a\u00020\u000b*\u00020\t2\u0006\u0010\n\u001a\u00020\u000b\u001a\n\u0010\u000e\u001a\u00020\t*\u00020\u0001\u001a\u0014\u0010\u000f\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0001H\u0000\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0011"}, d2 = {"Empty", "", "Lkotlin/String$Companion;", "getEmpty", "(Lkotlin/jvm/internal/StringCompanionObject;)Ljava/lang/String;", "generateId", "", "generateClientToServerId", "randomBytes", "", "size", "", "bytesToHex", "bytesToInt", "hexToBytes", "addUserAgent", "sdkVersion", "foundation"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nUtilFunctions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UtilFunctions.kt\ncom/reown/util/UtilFunctionsKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,62:1\n1869#2,2:63\n1803#2,3:66\n1#3:65\n*S KotlinDebug\n*F\n+ 1 UtilFunctions.kt\ncom/reown/util/UtilFunctionsKt\n*L\n26#1:63,2\n42#1:66,3\n*E\n"})
public final class UtilFunctionsKt {
    public static final /* synthetic */ String addUserAgent(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(str2, RemoteConfigConstants.RequestFieldKey.SDK_VERSION);
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        return a.n(sb, "&ua=wc-2/kotlin-", str2);
    }

    @NotNull
    public static final String bytesToHex(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        Iterator it = ArraysKt.getIndices(bArr).iterator();
        while (it.hasNext()) {
            String hexString = Integer.toHexString(bArr[((IntIterator) it).nextInt()] & 255);
            if (hexString.length() == 1) {
                sb.append('0');
            }
            sb.append(hexString);
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    public static final int bytesToInt(@NotNull byte[] bArr, int i3) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (bArr.length <= 4) {
            int i4 = 0;
            Iterator it = RangesKt.until(0, i3).iterator();
            while (it.hasNext()) {
                int nextInt = ((IntIterator) it).nextInt();
                i4 |= bArr[nextInt] << (((i3 - 1) - nextInt) * 8);
            }
            return i4;
        }
        throw new IllegalArgumentException("Byte array size must be less than 5");
    }

    public static final long generateClientToServerId() {
        return (long) ((Math.pow(10.0d, 6.0d) * ((double) System.currentTimeMillis())) + ((double) RangesKt___RangesKt.random(new IntRange(100000, 999999), (Random) Random.Default)));
    }

    public static final long generateId() {
        long currentTimeMillis = System.currentTimeMillis();
        int f2 = RangesKt___RangesKt.random(new IntRange(100, 999), (Random) Random.Default);
        StringBuilder sb = new StringBuilder();
        sb.append(currentTimeMillis);
        sb.append(f2);
        return Long.parseLong(sb.toString());
    }

    @NotNull
    public static final byte[] hexToBytes(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i3 = 0; i3 < length; i3 += 2) {
            bArr[i3 / 2] = (byte) (Character.digit(str.charAt(i3 + 1), 16) + (Character.digit(str.charAt(i3), 16) << 4));
        }
        return bArr;
    }

    @NotNull
    public static final byte[] randomBytes(int i3) {
        byte[] bArr = new byte[i3];
        new SecureRandom().nextBytes(bArr);
        return bArr;
    }
}
