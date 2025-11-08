package com.benasher44.uuid;

import A.a;
import java.nio.ByteBuffer;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\u001a\r\u0010\u000b\u001a\u00060\u0002j\u0002`\fH\b\u001a\u0012\u0010\r\u001a\u00060\u0002j\u0002`\f2\u0006\u0010\u000e\u001a\u00020\u000f\u001a\u0012\u0010\u0010\u001a\u00060\u0002j\u0002`\f2\u0006\u0010\u0000\u001a\u00020\u0001\u001a\u001c\u0010\u0011\u001a\u00020\u0012*\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006H\u0002\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0016\u0010\u0005\u001a\u00020\u0006*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\"\u0016\u0010\t\u001a\u00020\u0006*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\n\u0010\b*\n\u0010\u0015\"\u00020\u00022\u00020\u0002¨\u0006\u0016"}, d2 = {"bytes", "", "Ljava/util/UUID;", "getBytes", "(Ljava/util/UUID;)[B", "variant", "", "getVariant", "(Ljava/util/UUID;)I", "version", "getVersion", "uuid4", "Lcom/benasher44/uuid/Uuid;", "uuidFrom", "string", "", "uuidOf", "segmentToLong", "", "start", "end", "Uuid", "uuid"}, k = 2, mv = {1, 5, 1}, xi = 48)
public final class UuidKt {
    public static /* synthetic */ void Uuid$annotations() {
    }

    @NotNull
    public static final byte[] getBytes(@NotNull UUID uuid) {
        Intrinsics.checkNotNullParameter(uuid, "<this>");
        byte[] array = ByteBuffer.allocate(16).putLong(uuid.getMostSignificantBits()).putLong(uuid.getLeastSignificantBits()).array();
        Intrinsics.checkNotNullExpressionValue(array, "allocate(16).putLong(mos…tSignificantBits).array()");
        return array;
    }

    public static final int getVariant(@NotNull UUID uuid) {
        Intrinsics.checkNotNullParameter(uuid, "<this>");
        return uuid.variant();
    }

    public static final int getVersion(@NotNull UUID uuid) {
        Intrinsics.checkNotNullParameter(uuid, "<this>");
        return uuid.version();
    }

    private static final long segmentToLong(String str, int i3, int i4) {
        long j2;
        long j3 = 0;
        do {
            if (str.charAt(i3) == '-') {
                if (!(i3 == 8 || i3 == 13 || i3 == 18 || i3 == 23)) {
                    throw new IllegalArgumentException(("Invalid UUID string, encountered dash at index " + i3 + " but it can occur only at 8, 13, 18, or 23: " + str).toString());
                }
            } else {
                j3 *= (long) 16;
                char charAt = str.charAt(i3);
                if (charAt != '0') {
                    if (charAt == '1') {
                        j2 = 1;
                    } else if (charAt == '2') {
                        j2 = 2;
                    } else if (charAt == '3') {
                        j2 = 3;
                    } else if (charAt == '4') {
                        j2 = 4;
                    } else if (charAt == '5') {
                        j2 = 5;
                    } else if (charAt == '6') {
                        j2 = 6;
                    } else if (charAt == '7') {
                        j2 = 7;
                    } else if (charAt == '8') {
                        j2 = 8;
                    } else if (charAt == '9') {
                        j2 = 9;
                    } else if (charAt == 'a' || charAt == 'A') {
                        j2 = 10;
                    } else if (charAt == 'b' || charAt == 'B') {
                        j2 = 11;
                    } else if (charAt == 'c' || charAt == 'C') {
                        j2 = 12;
                    } else if (charAt == 'd' || charAt == 'D') {
                        j2 = 13;
                    } else if (charAt == 'e' || charAt == 'E') {
                        j2 = 14;
                    } else if (charAt == 'f' || charAt == 'F') {
                        j2 = 15;
                    } else {
                        StringBuilder sb = new StringBuilder("Invalid UUID string, encountered non-hexadecimal digit `");
                        sb.append(str.charAt(i3));
                        sb.append("` at index ");
                        sb.append(i3);
                        throw new IllegalArgumentException(a.n(sb, " in: ", str));
                    }
                    j3 += j2;
                }
            }
            i3++;
        } while (i3 < i4);
        return j3;
    }

    @NotNull
    public static final UUID uuid4() {
        UUID randomUUID = UUID.randomUUID();
        Intrinsics.checkNotNullExpressionValue(randomUUID, "randomUUID()");
        return randomUUID;
    }

    @NotNull
    public static final UUID uuidFrom(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "string");
        if (str.length() == 36) {
            return new UUID(segmentToLong(str, 0, 19), segmentToLong(str, 19, 36));
        }
        throw new IllegalArgumentException("Invalid UUID string, expected exactly 36 characters but got " + str.length() + ": " + str);
    }

    @NotNull
    public static final UUID uuidOf(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "bytes");
        if (bArr.length == 16) {
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            return new UUID(wrap.getLong(), wrap.getLong());
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("Invalid UUID bytes. Expected 16 bytes; found ", Integer.valueOf(bArr.length)).toString());
    }
}
