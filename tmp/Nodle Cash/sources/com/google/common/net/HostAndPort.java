package com.google.common.net;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import javax.annotation.CheckForNull;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@GwtCompatible
@Immutable
@ElementTypesAreNonnullByDefault
public final class HostAndPort implements Serializable {
    private static final int NO_PORT = -1;
    private static final long serialVersionUID = 0;
    private final boolean hasBracketlessColons;
    private final String host;
    private final int port;

    private HostAndPort(String str, int i3, boolean z2) {
        this.host = str;
        this.port = i3;
        this.hasBracketlessColons = z2;
    }

    public static HostAndPort fromHost(String str) {
        HostAndPort fromString = fromString(str);
        Preconditions.checkArgument(!fromString.hasPort(), "Host has a port: %s", (Object) str);
        return fromString;
    }

    public static HostAndPort fromParts(String str, int i3) {
        Preconditions.checkArgument(isValidPort(i3), "Port out of range: %s", i3);
        HostAndPort fromString = fromString(str);
        Preconditions.checkArgument(!fromString.hasPort(), "Host has a port: %s", (Object) str);
        return new HostAndPort(fromString.host, i3, fromString.hasBracketlessColons);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0042  */
    @com.google.errorprone.annotations.CanIgnoreReturnValue
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.common.net.HostAndPort fromString(java.lang.String r8) {
        /*
            com.google.common.base.Preconditions.checkNotNull(r8)
            java.lang.String r0 = "["
            boolean r0 = r8.startsWith(r0)
            r1 = -1
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L_0x0019
            java.lang.String[] r0 = getHostAndPortFromBracketedHost(r8)
            r4 = r0[r3]
            r0 = r0[r2]
        L_0x0016:
            r5 = r4
            r4 = r3
            goto L_0x003c
        L_0x0019:
            r0 = 58
            int r4 = r8.indexOf(r0)
            if (r4 < 0) goto L_0x0032
            int r5 = r4 + 1
            int r0 = r8.indexOf(r0, r5)
            if (r0 != r1) goto L_0x0032
            java.lang.String r4 = r8.substring(r3, r4)
            java.lang.String r0 = r8.substring(r5)
            goto L_0x0016
        L_0x0032:
            if (r4 < 0) goto L_0x0036
            r0 = r2
            goto L_0x0037
        L_0x0036:
            r0 = r3
        L_0x0037:
            r4 = 0
            r5 = r8
            r7 = r4
            r4 = r0
            r0 = r7
        L_0x003c:
            boolean r6 = com.google.common.base.Strings.isNullOrEmpty(r0)
            if (r6 != 0) goto L_0x0075
            java.lang.String r1 = "+"
            boolean r1 = r0.startsWith(r1)
            if (r1 != 0) goto L_0x0055
            com.google.common.base.CharMatcher r1 = com.google.common.base.CharMatcher.ascii()
            boolean r1 = r1.matchesAllOf(r0)
            if (r1 == 0) goto L_0x0055
            goto L_0x0056
        L_0x0055:
            r2 = r3
        L_0x0056:
            java.lang.String r1 = "Unparseable port number: %s"
            com.google.common.base.Preconditions.checkArgument((boolean) r2, (java.lang.String) r1, (java.lang.Object) r8)
            int r1 = java.lang.Integer.parseInt(r0)     // Catch:{ NumberFormatException -> 0x0069 }
            boolean r0 = isValidPort(r1)
            java.lang.String r2 = "Port number out of range: %s"
            com.google.common.base.Preconditions.checkArgument((boolean) r0, (java.lang.String) r2, (java.lang.Object) r8)
            goto L_0x0075
        L_0x0069:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Unparseable port number: "
            java.lang.String r8 = r1.concat(r8)
            r0.<init>(r8)
            throw r0
        L_0x0075:
            com.google.common.net.HostAndPort r8 = new com.google.common.net.HostAndPort
            r8.<init>(r5, r1, r4)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.net.HostAndPort.fromString(java.lang.String):com.google.common.net.HostAndPort");
    }

    private static String[] getHostAndPortFromBracketedHost(String str) {
        boolean z2 = false;
        Preconditions.checkArgument(str.charAt(0) == '[', "Bracketed host-port string must start with a bracket: %s", (Object) str);
        int indexOf = str.indexOf(58);
        int lastIndexOf = str.lastIndexOf(93);
        Preconditions.checkArgument(indexOf > -1 && lastIndexOf > indexOf, "Invalid bracketed host/port: %s", (Object) str);
        String substring = str.substring(1, lastIndexOf);
        int i3 = lastIndexOf + 1;
        if (i3 == str.length()) {
            return new String[]{substring, ""};
        }
        if (str.charAt(i3) == ':') {
            z2 = true;
        }
        Preconditions.checkArgument(z2, "Only a colon may follow a close bracket: %s", (Object) str);
        int i4 = lastIndexOf + 2;
        for (int i5 = i4; i5 < str.length(); i5++) {
            Preconditions.checkArgument(Character.isDigit(str.charAt(i5)), "Port must be numeric: %s", (Object) str);
        }
        return new String[]{substring, str.substring(i4)};
    }

    private static boolean isValidPort(int i3) {
        return i3 >= 0 && i3 <= 65535;
    }

    public boolean equals(@CheckForNull Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HostAndPort)) {
            return false;
        }
        HostAndPort hostAndPort = (HostAndPort) obj;
        return Objects.equal(this.host, hostAndPort.host) && this.port == hostAndPort.port;
    }

    public String getHost() {
        return this.host;
    }

    public int getPort() {
        Preconditions.checkState(hasPort());
        return this.port;
    }

    public int getPortOrDefault(int i3) {
        return hasPort() ? this.port : i3;
    }

    public boolean hasPort() {
        return this.port >= 0;
    }

    public int hashCode() {
        return Objects.hashCode(this.host, Integer.valueOf(this.port));
    }

    @CanIgnoreReturnValue
    public HostAndPort requireBracketsForIPv6() {
        Preconditions.checkArgument(!this.hasBracketlessColons, "Possible bracketless IPv6 literal: %s", (Object) this.host);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.host.length() + 8);
        if (this.host.indexOf(58) >= 0) {
            sb.append(AbstractJsonLexerKt.BEGIN_LIST);
            sb.append(this.host);
            sb.append(AbstractJsonLexerKt.END_LIST);
        } else {
            sb.append(this.host);
        }
        if (hasPort()) {
            sb.append(AbstractJsonLexerKt.COLON);
            sb.append(this.port);
        }
        return sb.toString();
    }

    public HostAndPort withDefaultPort(int i3) {
        Preconditions.checkArgument(isValidPort(i3));
        return hasPort() ? this : new HostAndPort(this.host, i3, this.hasBracketlessColons);
    }
}
