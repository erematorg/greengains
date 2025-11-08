package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.Version;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Pattern;

public class VersionUtil {
    private static final Pattern V_SEP = Pattern.compile("[-_./;:]");

    private static final void _close(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException unused) {
        }
    }

    @Deprecated
    public static Version mavenVersionFor(ClassLoader classLoader, String str, String str2) {
        InputStream resourceAsStream = classLoader.getResourceAsStream("META-INF/maven/" + str.replaceAll("\\.", "/") + "/" + str2 + "/pom.properties");
        if (resourceAsStream != null) {
            try {
                Properties properties = new Properties();
                properties.load(resourceAsStream);
                return parseVersion(properties.getProperty("version"), properties.getProperty("groupId"), properties.getProperty("artifactId"));
            } catch (IOException unused) {
            } finally {
                _close(resourceAsStream);
            }
        }
        return Version.unknownVersion();
    }

    @Deprecated
    public static Version packageVersionFor(Class<?> cls) {
        return versionFor(cls);
    }

    public static Version parseVersion(String str, String str2, String str3) {
        if (str != null) {
            String trim = str.trim();
            if (trim.length() > 0) {
                String[] split = V_SEP.split(trim);
                int i3 = 0;
                int parseVersionPart = parseVersionPart(split[0]);
                int parseVersionPart2 = split.length > 1 ? parseVersionPart(split[1]) : 0;
                if (split.length > 2) {
                    i3 = parseVersionPart(split[2]);
                }
                return new Version(parseVersionPart, parseVersionPart2, i3, split.length > 3 ? split[3] : null, str2, str3);
            }
        }
        return Version.unknownVersion();
    }

    public static int parseVersionPart(String str) {
        int length = str.length();
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            char charAt = str.charAt(i4);
            if (charAt > '9' || charAt < '0') {
                break;
            }
            i3 = (i3 * 10) + (charAt - '0');
        }
        return i3;
    }

    public static final void throwInternal() {
        throw new RuntimeException("Internal error: this code path should never get executed");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:5|6|7) */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0045, code lost:
        throw new java.lang.IllegalArgumentException("Failed to get Versioned out of " + r4);
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0034 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.fasterxml.jackson.core.Version versionFor(java.lang.Class<?> r4) {
        /*
            java.lang.String r0 = "Failed to get Versioned out of "
            r1 = 0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0046 }
            r2.<init>()     // Catch:{ Exception -> 0x0046 }
            java.lang.Package r3 = r4.getPackage()     // Catch:{ Exception -> 0x0046 }
            java.lang.String r3 = r3.getName()     // Catch:{ Exception -> 0x0046 }
            r2.append(r3)     // Catch:{ Exception -> 0x0046 }
            java.lang.String r3 = ".PackageVersion"
            r2.append(r3)     // Catch:{ Exception -> 0x0046 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0046 }
            java.lang.ClassLoader r4 = r4.getClassLoader()     // Catch:{ Exception -> 0x0046 }
            r3 = 1
            java.lang.Class r4 = java.lang.Class.forName(r2, r3, r4)     // Catch:{ Exception -> 0x0046 }
            java.lang.reflect.Constructor r2 = r4.getDeclaredConstructor(r1)     // Catch:{ Exception -> 0x0034 }
            java.lang.Object r2 = r2.newInstance(r1)     // Catch:{ Exception -> 0x0034 }
            com.fasterxml.jackson.core.Versioned r2 = (com.fasterxml.jackson.core.Versioned) r2     // Catch:{ Exception -> 0x0034 }
            com.fasterxml.jackson.core.Version r1 = r2.version()     // Catch:{ Exception -> 0x0034 }
            goto L_0x0046
        L_0x0034:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException     // Catch:{ Exception -> 0x0046 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0046 }
            r3.<init>(r0)     // Catch:{ Exception -> 0x0046 }
            r3.append(r4)     // Catch:{ Exception -> 0x0046 }
            java.lang.String r4 = r3.toString()     // Catch:{ Exception -> 0x0046 }
            r2.<init>(r4)     // Catch:{ Exception -> 0x0046 }
            throw r2     // Catch:{ Exception -> 0x0046 }
        L_0x0046:
            if (r1 != 0) goto L_0x004c
            com.fasterxml.jackson.core.Version r1 = com.fasterxml.jackson.core.Version.unknownVersion()
        L_0x004c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.util.VersionUtil.versionFor(java.lang.Class):com.fasterxml.jackson.core.Version");
    }

    @Deprecated
    public Version version() {
        return Version.unknownVersion();
    }
}
