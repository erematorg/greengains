package com.google.common.net;

import androidx.constraintlayout.core.state.b;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.CharMatcher;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.hash.Hashing;
import com.google.common.io.ByteStreams;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Locale;
import javax.annotation.CheckForNull;

@GwtIncompatible
@ElementTypesAreNonnullByDefault
@J2ktIncompatible
public final class InetAddresses {
    /* access modifiers changed from: private */
    public static final Inet4Address ANY4 = ((Inet4Address) forString("0.0.0.0"));
    private static final char IPV4_DELIMITER = '.';
    private static final CharMatcher IPV4_DELIMITER_MATCHER = CharMatcher.is('.');
    private static final int IPV4_PART_COUNT = 4;
    private static final char IPV6_DELIMITER = ':';
    private static final CharMatcher IPV6_DELIMITER_MATCHER = CharMatcher.is(':');
    private static final int IPV6_PART_COUNT = 8;
    private static final Inet4Address LOOPBACK4 = ((Inet4Address) forString("127.0.0.1"));

    public static final class TeredoInfo {
        private final Inet4Address client;
        private final int flags;
        private final int port;
        private final Inet4Address server;

        public TeredoInfo(@CheckForNull Inet4Address inet4Address, @CheckForNull Inet4Address inet4Address2, int i3, int i4) {
            boolean z2 = false;
            Preconditions.checkArgument(i3 >= 0 && i3 <= 65535, "port '%s' is out of range (0 <= port <= 0xffff)", i3);
            if (i4 >= 0 && i4 <= 65535) {
                z2 = true;
            }
            Preconditions.checkArgument(z2, "flags '%s' is out of range (0 <= flags <= 0xffff)", i4);
            this.server = (Inet4Address) MoreObjects.firstNonNull(inet4Address, InetAddresses.ANY4);
            this.client = (Inet4Address) MoreObjects.firstNonNull(inet4Address2, InetAddresses.ANY4);
            this.port = i3;
            this.flags = i4;
        }

        public Inet4Address getClient() {
            return this.client;
        }

        public int getFlags() {
            return this.flags;
        }

        public int getPort() {
            return this.port;
        }

        public Inet4Address getServer() {
            return this.server;
        }
    }

    private InetAddresses() {
    }

    private static InetAddress bytesToInetAddress(byte[] bArr) {
        try {
            return InetAddress.getByAddress(bArr);
        } catch (UnknownHostException e3) {
            throw new AssertionError(e3);
        }
    }

    public static int coerceToInteger(InetAddress inetAddress) {
        return ByteStreams.newDataInput(getCoercedIPv4Address(inetAddress).getAddress()).readInt();
    }

    private static void compressLongestRunOfZeroes(int[] iArr) {
        int i3 = -1;
        int i4 = -1;
        int i5 = -1;
        for (int i6 = 0; i6 < iArr.length + 1; i6++) {
            if (i6 >= iArr.length || iArr[i6] != 0) {
                if (i5 >= 0) {
                    int i7 = i6 - i5;
                    if (i7 > i3) {
                        i4 = i5;
                        i3 = i7;
                    }
                    i5 = -1;
                }
            } else if (i5 < 0) {
                i5 = i6;
            }
        }
        if (i3 >= 2) {
            Arrays.fill(iArr, i4, i3 + i4, -1);
        }
    }

    @CheckForNull
    private static String convertDottedQuadToHex(String str) {
        int lastIndexOf = str.lastIndexOf(58) + 1;
        String substring = str.substring(0, lastIndexOf);
        byte[] textToNumericFormatV4 = textToNumericFormatV4(str.substring(lastIndexOf));
        if (textToNumericFormatV4 == null) {
            return null;
        }
        return b.m(substring, Integer.toHexString(((textToNumericFormatV4[0] & 255) << 8) | (textToNumericFormatV4[1] & 255)), ":", Integer.toHexString((textToNumericFormatV4[3] & 255) | ((textToNumericFormatV4[2] & 255) << 8)));
    }

    public static InetAddress decrement(InetAddress inetAddress) {
        byte[] address = inetAddress.getAddress();
        int length = address.length - 1;
        while (length >= 0 && address[length] == 0) {
            address[length] = -1;
            length--;
        }
        Preconditions.checkArgument(length >= 0, "Decrementing %s would wrap.", (Object) inetAddress);
        address[length] = (byte) (address[length] - 1);
        return bytesToInetAddress(address);
    }

    @CanIgnoreReturnValue
    public static InetAddress forString(String str) {
        byte[] ipStringToBytes = ipStringToBytes(str);
        if (ipStringToBytes != null) {
            return bytesToInetAddress(ipStringToBytes);
        }
        throw formatIllegalArgumentException("'%s' is not an IP string literal.", str);
    }

    public static InetAddress forUriString(String str) {
        InetAddress forUriStringNoThrow = forUriStringNoThrow(str);
        if (forUriStringNoThrow != null) {
            return forUriStringNoThrow;
        }
        throw formatIllegalArgumentException("Not a valid URI IP literal: '%s'", str);
    }

    @CheckForNull
    private static InetAddress forUriStringNoThrow(String str) {
        int i3;
        Preconditions.checkNotNull(str);
        if (!str.startsWith("[") || !str.endsWith("]")) {
            i3 = 4;
        } else {
            str = b.y(str, 1, 1);
            i3 = 16;
        }
        byte[] ipStringToBytes = ipStringToBytes(str);
        if (ipStringToBytes == null || ipStringToBytes.length != i3) {
            return null;
        }
        return bytesToInetAddress(ipStringToBytes);
    }

    private static IllegalArgumentException formatIllegalArgumentException(String str, Object... objArr) {
        return new IllegalArgumentException(String.format(Locale.ROOT, str, objArr));
    }

    private static InetAddress fromBigInteger(BigInteger bigInteger, boolean z2) {
        int i3 = 0;
        Preconditions.checkArgument(bigInteger.signum() >= 0, "BigInteger must be greater than or equal to 0");
        int i4 = z2 ? 16 : 4;
        byte[] byteArray = bigInteger.toByteArray();
        byte[] bArr = new byte[i4];
        int max = Math.max(0, byteArray.length - i4);
        int length = byteArray.length - max;
        int i5 = i4 - length;
        while (i3 < max) {
            if (byteArray[i3] == 0) {
                i3++;
            } else {
                throw formatIllegalArgumentException("BigInteger cannot be converted to InetAddress because it has more than %d bytes: %s", Integer.valueOf(i4), bigInteger);
            }
        }
        System.arraycopy(byteArray, max, bArr, i5, length);
        try {
            return InetAddress.getByAddress(bArr);
        } catch (UnknownHostException e3) {
            throw new AssertionError(e3);
        }
    }

    public static Inet4Address fromIPv4BigInteger(BigInteger bigInteger) {
        return (Inet4Address) fromBigInteger(bigInteger, false);
    }

    public static Inet6Address fromIPv6BigInteger(BigInteger bigInteger) {
        return (Inet6Address) fromBigInteger(bigInteger, true);
    }

    public static Inet4Address fromInteger(int i3) {
        return getInet4Address(Ints.toByteArray(i3));
    }

    public static InetAddress fromLittleEndianByteArray(byte[] bArr) throws UnknownHostException {
        byte[] bArr2 = new byte[bArr.length];
        for (int i3 = 0; i3 < bArr.length; i3++) {
            bArr2[i3] = bArr[(bArr.length - i3) - 1];
        }
        return InetAddress.getByAddress(bArr2);
    }

    public static Inet4Address get6to4IPv4Address(Inet6Address inet6Address) {
        Preconditions.checkArgument(is6to4Address(inet6Address), "Address '%s' is not a 6to4 address.", (Object) toAddrString(inet6Address));
        return getInet4Address(Arrays.copyOfRange(inet6Address.getAddress(), 2, 6));
    }

    public static Inet4Address getCoercedIPv4Address(InetAddress inetAddress) {
        boolean z2;
        if (inetAddress instanceof Inet4Address) {
            return (Inet4Address) inetAddress;
        }
        byte[] address = inetAddress.getAddress();
        int i3 = 0;
        while (true) {
            if (i3 >= 15) {
                z2 = true;
                break;
            } else if (address[i3] != 0) {
                z2 = false;
                break;
            } else {
                i3++;
            }
        }
        if (z2 && address[15] == 1) {
            return LOOPBACK4;
        }
        if (z2 && address[15] == 0) {
            return ANY4;
        }
        Inet6Address inet6Address = (Inet6Address) inetAddress;
        int asInt = Hashing.murmur3_32_fixed().hashLong(hasEmbeddedIPv4ClientAddress(inet6Address) ? (long) getEmbeddedIPv4ClientAddress(inet6Address).hashCode() : ByteBuffer.wrap(inet6Address.getAddress(), 0, 8).getLong()).asInt() | -536870912;
        if (asInt == -1) {
            asInt = -2;
        }
        return getInet4Address(Ints.toByteArray(asInt));
    }

    public static Inet4Address getCompatIPv4Address(Inet6Address inet6Address) {
        Preconditions.checkArgument(isCompatIPv4Address(inet6Address), "Address '%s' is not IPv4-compatible.", (Object) toAddrString(inet6Address));
        return getInet4Address(Arrays.copyOfRange(inet6Address.getAddress(), 12, 16));
    }

    public static Inet4Address getEmbeddedIPv4ClientAddress(Inet6Address inet6Address) {
        if (isCompatIPv4Address(inet6Address)) {
            return getCompatIPv4Address(inet6Address);
        }
        if (is6to4Address(inet6Address)) {
            return get6to4IPv4Address(inet6Address);
        }
        if (isTeredoAddress(inet6Address)) {
            return getTeredoInfo(inet6Address).getClient();
        }
        throw formatIllegalArgumentException("'%s' has no embedded IPv4 address.", toAddrString(inet6Address));
    }

    private static Inet4Address getInet4Address(byte[] bArr) {
        Preconditions.checkArgument(bArr.length == 4, "Byte array has invalid length for an IPv4 address: %s != 4.", bArr.length);
        return (Inet4Address) bytesToInetAddress(bArr);
    }

    public static Inet4Address getIsatapIPv4Address(Inet6Address inet6Address) {
        Preconditions.checkArgument(isIsatapAddress(inet6Address), "Address '%s' is not an ISATAP address.", (Object) toAddrString(inet6Address));
        return getInet4Address(Arrays.copyOfRange(inet6Address.getAddress(), 12, 16));
    }

    public static TeredoInfo getTeredoInfo(Inet6Address inet6Address) {
        Preconditions.checkArgument(isTeredoAddress(inet6Address), "Address '%s' is not a Teredo address.", (Object) toAddrString(inet6Address));
        byte[] address = inet6Address.getAddress();
        Inet4Address inet4Address = getInet4Address(Arrays.copyOfRange(address, 4, 8));
        short readShort = ByteStreams.newDataInput(address, 8).readShort() & 65535;
        int i3 = 65535 & (~ByteStreams.newDataInput(address, 10).readShort());
        byte[] copyOfRange = Arrays.copyOfRange(address, 12, 16);
        for (int i4 = 0; i4 < copyOfRange.length; i4++) {
            copyOfRange[i4] = (byte) (~copyOfRange[i4]);
        }
        return new TeredoInfo(inet4Address, getInet4Address(copyOfRange), i3, readShort);
    }

    public static boolean hasEmbeddedIPv4ClientAddress(Inet6Address inet6Address) {
        return isCompatIPv4Address(inet6Address) || is6to4Address(inet6Address) || isTeredoAddress(inet6Address);
    }

    private static String hextetsToIPv6String(int[] iArr) {
        StringBuilder sb = new StringBuilder(39);
        int i3 = 0;
        boolean z2 = false;
        while (i3 < iArr.length) {
            boolean z3 = iArr[i3] >= 0;
            if (z3) {
                if (z2) {
                    sb.append(':');
                }
                sb.append(Integer.toHexString(iArr[i3]));
            } else if (i3 == 0 || z2) {
                sb.append("::");
            }
            i3++;
            z2 = z3;
        }
        return sb.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:7:0x0016  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.net.InetAddress increment(java.net.InetAddress r6) {
        /*
            byte[] r0 = r6.getAddress()
            int r1 = r0.length
            r2 = 1
            int r1 = r1 - r2
        L_0x0007:
            r3 = 0
            if (r1 < 0) goto L_0x0014
            byte r4 = r0[r1]
            r5 = -1
            if (r4 != r5) goto L_0x0014
            r0[r1] = r3
            int r1 = r1 + -1
            goto L_0x0007
        L_0x0014:
            if (r1 < 0) goto L_0x0017
            r3 = r2
        L_0x0017:
            java.lang.String r4 = "Incrementing %s would wrap."
            com.google.common.base.Preconditions.checkArgument((boolean) r3, (java.lang.String) r4, (java.lang.Object) r6)
            byte r6 = r0[r1]
            int r6 = r6 + r2
            byte r6 = (byte) r6
            r0[r1] = r6
            java.net.InetAddress r6 = bytesToInetAddress(r0)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.net.InetAddresses.increment(java.net.InetAddress):java.net.InetAddress");
    }

    @CheckForNull
    private static byte[] ipStringToBytes(String str) {
        int i3 = 0;
        boolean z2 = false;
        boolean z3 = false;
        while (true) {
            if (i3 >= str.length()) {
                i3 = -1;
                break;
            }
            char charAt = str.charAt(i3);
            if (charAt == '.') {
                z2 = true;
            } else if (charAt == ':') {
                if (z2) {
                    return null;
                }
                z3 = true;
            } else if (charAt == '%') {
                break;
            } else if (Character.digit(charAt, 16) == -1) {
                return null;
            }
            i3++;
        }
        if (z3) {
            if (z2 && (str = convertDottedQuadToHex(str)) == null) {
                return null;
            }
            if (i3 != -1) {
                str = str.substring(0, i3);
            }
            return textToNumericFormatV6(str);
        } else if (!z2 || i3 != -1) {
            return null;
        } else {
            return textToNumericFormatV4(str);
        }
    }

    public static boolean is6to4Address(Inet6Address inet6Address) {
        byte[] address = inet6Address.getAddress();
        return address[0] == 32 && address[1] == 2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001f, code lost:
        r3 = r3[15];
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isCompatIPv4Address(java.net.Inet6Address r3) {
        /*
            boolean r0 = r3.isIPv4CompatibleAddress()
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            byte[] r3 = r3.getAddress()
            r0 = 12
            byte r0 = r3[r0]
            r2 = 1
            if (r0 != 0) goto L_0x0028
            r0 = 13
            byte r0 = r3[r0]
            if (r0 != 0) goto L_0x0028
            r0 = 14
            byte r0 = r3[r0]
            if (r0 != 0) goto L_0x0028
            r0 = 15
            byte r3 = r3[r0]
            if (r3 == 0) goto L_0x0027
            if (r3 != r2) goto L_0x0028
        L_0x0027:
            return r1
        L_0x0028:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.net.InetAddresses.isCompatIPv4Address(java.net.Inet6Address):boolean");
    }

    public static boolean isInetAddress(String str) {
        return ipStringToBytes(str) != null;
    }

    public static boolean isIsatapAddress(Inet6Address inet6Address) {
        if (isTeredoAddress(inet6Address)) {
            return false;
        }
        byte[] address = inet6Address.getAddress();
        return (address[8] | 3) == 3 && address[9] == 0 && address[10] == 94 && address[11] == -2;
    }

    public static boolean isMappedIPv4Address(String str) {
        byte[] ipStringToBytes = ipStringToBytes(str);
        if (ipStringToBytes == null || ipStringToBytes.length != 16) {
            return false;
        }
        int i3 = 0;
        while (true) {
            if (i3 >= 10) {
                for (int i4 = 10; i4 < 12; i4++) {
                    if (ipStringToBytes[i4] != -1) {
                        return false;
                    }
                }
                return true;
            } else if (ipStringToBytes[i3] != 0) {
                return false;
            } else {
                i3++;
            }
        }
    }

    public static boolean isMaximum(InetAddress inetAddress) {
        for (byte b3 : inetAddress.getAddress()) {
            if (b3 != -1) {
                return false;
            }
        }
        return true;
    }

    public static boolean isTeredoAddress(Inet6Address inet6Address) {
        byte[] address = inet6Address.getAddress();
        return address[0] == 32 && address[1] == 1 && address[2] == 0 && address[3] == 0;
    }

    public static boolean isUriInetAddress(String str) {
        return forUriStringNoThrow(str) != null;
    }

    private static short parseHextet(String str, int i3, int i4) {
        int i5 = i4 - i3;
        if (i5 <= 0 || i5 > 4) {
            throw new NumberFormatException();
        }
        int i6 = 0;
        while (i3 < i4) {
            i6 = (i6 << 4) | Character.digit(str.charAt(i3), 16);
            i3++;
        }
        return (short) i6;
    }

    private static byte parseOctet(String str, int i3, int i4) {
        int i5 = i4 - i3;
        if (i5 <= 0 || i5 > 3) {
            throw new NumberFormatException();
        } else if (i5 <= 1 || str.charAt(i3) != '0') {
            int i6 = 0;
            while (i3 < i4) {
                int i7 = i6 * 10;
                int digit = Character.digit(str.charAt(i3), 10);
                if (digit >= 0) {
                    i6 = i7 + digit;
                    i3++;
                } else {
                    throw new NumberFormatException();
                }
            }
            if (i6 <= 255) {
                return (byte) i6;
            }
            throw new NumberFormatException();
        } else {
            throw new NumberFormatException();
        }
    }

    @CheckForNull
    private static byte[] textToNumericFormatV4(String str) {
        if (IPV4_DELIMITER_MATCHER.countIn(str) + 1 != 4) {
            return null;
        }
        byte[] bArr = new byte[4];
        int i3 = 0;
        int i4 = 0;
        while (i3 < 4) {
            int indexOf = str.indexOf(46, i4);
            if (indexOf == -1) {
                indexOf = str.length();
            }
            try {
                bArr[i3] = parseOctet(str, i4, indexOf);
                i4 = indexOf + 1;
                i3++;
            } catch (NumberFormatException unused) {
                return null;
            }
        }
        return bArr;
    }

    @CheckForNull
    private static byte[] textToNumericFormatV6(String str) {
        int countIn = IPV6_DELIMITER_MATCHER.countIn(str);
        if (countIn >= 2 && countIn <= 8) {
            int i3 = 1;
            int i4 = countIn + 1;
            int i5 = 8 - i4;
            boolean z2 = false;
            for (int i6 = 0; i6 < str.length() - 1; i6++) {
                if (str.charAt(i6) == ':' && str.charAt(i6 + 1) == ':') {
                    if (z2) {
                        return null;
                    }
                    int i7 = i5 + 1;
                    if (i6 == 0) {
                        i7 = i5 + 2;
                    }
                    if (i6 == str.length() - 2) {
                        i7++;
                    }
                    i5 = i7;
                    z2 = true;
                }
            }
            if (str.charAt(0) == ':' && str.charAt(1) != ':') {
                return null;
            }
            if (b.a(1, str) == ':' && b.a(2, str) != ':') {
                return null;
            }
            if (z2 && i5 <= 0) {
                return null;
            }
            if (!z2 && i4 != 8) {
                return null;
            }
            ByteBuffer allocate = ByteBuffer.allocate(16);
            try {
                if (str.charAt(0) != ':') {
                    i3 = 0;
                }
                while (i3 < str.length()) {
                    int indexOf = str.indexOf(58, i3);
                    if (indexOf == -1) {
                        indexOf = str.length();
                    }
                    if (str.charAt(i3) == ':') {
                        for (int i8 = 0; i8 < i5; i8++) {
                            allocate.putShort(0);
                        }
                    } else {
                        allocate.putShort(parseHextet(str, i3, indexOf));
                    }
                    i3 = indexOf + 1;
                }
                return allocate.array();
            } catch (NumberFormatException unused) {
            }
        }
        return null;
    }

    public static String toAddrString(InetAddress inetAddress) {
        Preconditions.checkNotNull(inetAddress);
        if (inetAddress instanceof Inet4Address) {
            return inetAddress.getHostAddress();
        }
        Preconditions.checkArgument(inetAddress instanceof Inet6Address);
        byte[] address = inetAddress.getAddress();
        int[] iArr = new int[8];
        for (int i3 = 0; i3 < 8; i3++) {
            int i4 = i3 * 2;
            iArr[i3] = Ints.fromBytes((byte) 0, (byte) 0, address[i4], address[i4 + 1]);
        }
        compressLongestRunOfZeroes(iArr);
        return hextetsToIPv6String(iArr);
    }

    public static BigInteger toBigInteger(InetAddress inetAddress) {
        return new BigInteger(1, inetAddress.getAddress());
    }

    public static String toUriString(InetAddress inetAddress) {
        if (!(inetAddress instanceof Inet6Address)) {
            return toAddrString(inetAddress);
        }
        return "[" + toAddrString(inetAddress) + "]";
    }
}
