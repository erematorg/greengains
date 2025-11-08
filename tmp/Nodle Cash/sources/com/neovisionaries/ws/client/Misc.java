package com.neovisionaries.ws.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URI;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Misc {
    private static final SecureRandom sRandom = new SecureRandom();

    private Misc() {
    }

    public static String extractHost(URI uri) {
        String host = uri.getHost();
        if (host != null) {
            return host;
        }
        String extractHostFromAuthorityPart = extractHostFromAuthorityPart(uri.getRawAuthority());
        return extractHostFromAuthorityPart != null ? extractHostFromAuthorityPart : extractHostFromEntireUri(uri.toString());
    }

    public static String extractHostFromAuthorityPart(String str) {
        Matcher matcher;
        if (str == null || (matcher = Pattern.compile("^(.*@)?([^:]+)(:\\d+)?$").matcher(str)) == null || !matcher.matches()) {
            return null;
        }
        return matcher.group(2);
    }

    public static String extractHostFromEntireUri(String str) {
        Matcher matcher;
        if (str == null || (matcher = Pattern.compile("^\\w+://([^@/]*@)?([^:/]+)(:\\d+)?(/.*)?$").matcher(str)) == null || !matcher.matches()) {
            return null;
        }
        return matcher.group(2);
    }

    public static byte[] getBytesUTF8(String str) {
        if (str == null) {
            return null;
        }
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    public static Constructor<?> getConstructor(String str, Class<?>[] clsArr) {
        try {
            return Class.forName(str).getConstructor(clsArr);
        } catch (Exception unused) {
            return null;
        }
    }

    public static Method getMethod(String str, String str2, Class<?>[] clsArr) {
        try {
            return Class.forName(str).getMethod(str2, clsArr);
        } catch (Exception unused) {
            return null;
        }
    }

    public static Object invoke(Method method, Object obj, Object... objArr) {
        if (method == null) {
            return null;
        }
        try {
            return method.invoke(obj, objArr);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String join(Collection<?> collection, String str) {
        StringBuilder sb = new StringBuilder();
        join(sb, collection, str);
        return sb.toString();
    }

    public static int max(int[] iArr) {
        int i3 = Integer.MIN_VALUE;
        for (int i4 : iArr) {
            if (i3 < i4) {
                i3 = i4;
            }
        }
        return i3;
    }

    public static int min(int[] iArr) {
        int i3 = Integer.MAX_VALUE;
        for (int i4 : iArr) {
            if (i4 < i3) {
                i3 = i4;
            }
        }
        return i3;
    }

    public static Object newInstance(Constructor<?> constructor, Object... objArr) {
        if (constructor == null) {
            return null;
        }
        try {
            return constructor.newInstance(objArr);
        } catch (Exception unused) {
            return null;
        }
    }

    public static byte[] nextBytes(byte[] bArr) {
        sRandom.nextBytes(bArr);
        return bArr;
    }

    public static String readLine(InputStream inputStream, String str) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read = inputStream.read();
            if (read != -1) {
                if (read == 10) {
                    break;
                } else if (read != 13) {
                    byteArrayOutputStream.write(read);
                } else {
                    int read2 = inputStream.read();
                    if (read2 == -1) {
                        byteArrayOutputStream.write(read);
                        break;
                    } else if (read2 == 10) {
                        break;
                    } else {
                        byteArrayOutputStream.write(read);
                        byteArrayOutputStream.write(read2);
                    }
                }
            } else if (byteArrayOutputStream.size() == 0) {
                return null;
            }
        }
        return byteArrayOutputStream.toString(str);
    }

    public static String toOpcodeName(int i3) {
        if (i3 == 0) {
            return "CONTINUATION";
        }
        if (i3 == 1) {
            return "TEXT";
        }
        if (i3 == 2) {
            return "BINARY";
        }
        switch (i3) {
            case 8:
                return "CLOSE";
            case 9:
                return "PING";
            case 10:
                return "PONG";
            default:
                return (1 > i3 || i3 > 7) ? (8 > i3 || i3 > 15) ? String.format("0x%X", new Object[]{Integer.valueOf(i3)}) : String.format("CONTROL(0x%X)", new Object[]{Integer.valueOf(i3)}) : String.format("DATA(0x%X)", new Object[]{Integer.valueOf(i3)});
        }
    }

    public static String toStringUTF8(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return toStringUTF8(bArr, 0, bArr.length);
    }

    public static byte[] nextBytes(int i3) {
        return nextBytes(new byte[i3]);
    }

    public static String toStringUTF8(byte[] bArr, int i3, int i4) {
        if (bArr == null) {
            return null;
        }
        try {
            return new String(bArr, i3, i4, "UTF-8");
        } catch (UnsupportedEncodingException | IndexOutOfBoundsException unused) {
            return null;
        }
    }

    private static void join(StringBuilder sb, Collection<?> collection, String str) {
        boolean z2 = true;
        for (Object next : collection) {
            if (z2) {
                z2 = false;
            } else {
                sb.append(str);
            }
            sb.append(next.toString());
        }
    }
}
