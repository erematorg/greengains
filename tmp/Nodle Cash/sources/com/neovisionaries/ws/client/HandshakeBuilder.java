package com.neovisionaries.ws.client;

import A.a;
import com.google.common.net.HttpHeaders;
import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

class HandshakeBuilder {
    private static final String[] CONNECTION_HEADER = {HttpHeaders.CONNECTION, HttpHeaders.UPGRADE};
    private static final String RN = "\r\n";
    private static final String[] UPGRADE_HEADER = {HttpHeaders.UPGRADE, "websocket"};
    private static final String[] VERSION_HEADER = {HttpHeaders.SEC_WEBSOCKET_VERSION, "13"};
    private List<WebSocketExtension> mExtensions;
    private List<String[]> mHeaders;
    private final String mHost;
    private String mKey;
    private final String mPath;
    private Set<String> mProtocols;
    private boolean mSecure;
    private final URI mUri;
    private String mUserInfo;

    public HandshakeBuilder(boolean z2, String str, String str2, String str3) {
        this.mSecure = z2;
        this.mUserInfo = str;
        this.mHost = str2;
        this.mPath = str3;
        String str4 = z2 ? "wss" : "ws";
        this.mUri = URI.create(str4 + "://" + str2 + str3);
    }

    public static String build(String str, List<String[]> list) {
        StringBuilder q2 = a.q(str, "\r\n");
        for (String[] next : list) {
            q2.append(next[0]);
            q2.append(": ");
            q2.append(next[1]);
            q2.append("\r\n");
        }
        q2.append("\r\n");
        return q2.toString();
    }

    private static List<WebSocketExtension> copyExtensions(List<WebSocketExtension> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (WebSocketExtension webSocketExtension : list) {
            arrayList.add(new WebSocketExtension(webSocketExtension));
        }
        return arrayList;
    }

    private static String[] copyHeader(String[] strArr) {
        return new String[]{strArr[0], strArr[1]};
    }

    private static List<String[]> copyHeaders(List<String[]> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (String[] copyHeader : list) {
            arrayList.add(copyHeader(copyHeader));
        }
        return arrayList;
    }

    private static Set<String> copyProtocols(Set<String> set) {
        if (set == null) {
            return null;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(set.size());
        linkedHashSet.addAll(set);
        return linkedHashSet;
    }

    private static boolean isValidProtocol(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        int length = str.length();
        for (int i3 = 0; i3 < length; i3++) {
            char charAt = str.charAt(i3);
            if (charAt < '!' || '~' < charAt || Token.isSeparator(charAt)) {
                return false;
            }
        }
        return true;
    }

    public void addExtension(WebSocketExtension webSocketExtension) {
        if (webSocketExtension != null) {
            synchronized (this) {
                try {
                    if (this.mExtensions == null) {
                        this.mExtensions = new ArrayList();
                    }
                    this.mExtensions.add(webSocketExtension);
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public void addHeader(String str, String str2) {
        if (str != null && str.length() != 0) {
            if (str2 == null) {
                str2 = "";
            }
            synchronized (this) {
                try {
                    if (this.mHeaders == null) {
                        this.mHeaders = new ArrayList();
                    }
                    this.mHeaders.add(new String[]{str, str2});
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public void addProtocol(String str) {
        if (isValidProtocol(str)) {
            synchronized (this) {
                try {
                    if (this.mProtocols == null) {
                        this.mProtocols = new LinkedHashSet();
                    }
                    this.mProtocols.add(str);
                } catch (Throwable th) {
                    throw th;
                }
            }
            return;
        }
        throw new IllegalArgumentException("'protocol' must be a non-empty string with characters in the range U+0021 to U+007E not including separator characters.");
    }

    public List<String[]> buildHeaders() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new String[]{HttpHeaders.HOST, this.mHost});
        arrayList.add(CONNECTION_HEADER);
        arrayList.add(UPGRADE_HEADER);
        arrayList.add(VERSION_HEADER);
        arrayList.add(new String[]{HttpHeaders.SEC_WEBSOCKET_KEY, this.mKey});
        Set<String> set = this.mProtocols;
        if (!(set == null || set.size() == 0)) {
            arrayList.add(new String[]{HttpHeaders.SEC_WEBSOCKET_PROTOCOL, Misc.join(this.mProtocols, ", ")});
        }
        List<WebSocketExtension> list = this.mExtensions;
        if (!(list == null || list.size() == 0)) {
            arrayList.add(new String[]{HttpHeaders.SEC_WEBSOCKET_EXTENSIONS, Misc.join(this.mExtensions, ", ")});
        }
        String str = this.mUserInfo;
        if (!(str == null || str.length() == 0)) {
            arrayList.add(new String[]{HttpHeaders.AUTHORIZATION, "Basic " + Base64.encode(this.mUserInfo)});
        }
        List<String[]> list2 = this.mHeaders;
        if (!(list2 == null || list2.size() == 0)) {
            arrayList.addAll(this.mHeaders);
        }
        return arrayList;
    }

    public String buildRequestLine() {
        return a.l("GET ", this.mPath, " HTTP/1.1");
    }

    public void clearExtensions() {
        synchronized (this) {
            this.mExtensions = null;
        }
    }

    public void clearHeaders() {
        synchronized (this) {
            this.mHeaders = null;
        }
    }

    public void clearProtocols() {
        synchronized (this) {
            this.mProtocols = null;
        }
    }

    public void clearUserInfo() {
        synchronized (this) {
            this.mUserInfo = null;
        }
    }

    public boolean containsExtension(WebSocketExtension webSocketExtension) {
        if (webSocketExtension == null) {
            return false;
        }
        synchronized (this) {
            try {
                List<WebSocketExtension> list = this.mExtensions;
                if (list == null) {
                    return false;
                }
                boolean contains = list.contains(webSocketExtension);
                return contains;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public boolean containsProtocol(String str) {
        synchronized (this) {
            try {
                Set<String> set = this.mProtocols;
                if (set == null) {
                    return false;
                }
                boolean contains = set.contains(str);
                return contains;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public URI getURI() {
        return this.mUri;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void removeExtension(com.neovisionaries.ws.client.WebSocketExtension r2) {
        /*
            r1 = this;
            if (r2 != 0) goto L_0x0003
            return
        L_0x0003:
            monitor-enter(r1)
            java.util.List<com.neovisionaries.ws.client.WebSocketExtension> r0 = r1.mExtensions     // Catch:{ all -> 0x000a }
            if (r0 != 0) goto L_0x000c
            monitor-exit(r1)     // Catch:{ all -> 0x000a }
            return
        L_0x000a:
            r2 = move-exception
            goto L_0x001c
        L_0x000c:
            r0.remove(r2)     // Catch:{ all -> 0x000a }
            java.util.List<com.neovisionaries.ws.client.WebSocketExtension> r2 = r1.mExtensions     // Catch:{ all -> 0x000a }
            int r2 = r2.size()     // Catch:{ all -> 0x000a }
            if (r2 != 0) goto L_0x001a
            r2 = 0
            r1.mExtensions = r2     // Catch:{ all -> 0x000a }
        L_0x001a:
            monitor-exit(r1)     // Catch:{ all -> 0x000a }
            return
        L_0x001c:
            monitor-exit(r1)     // Catch:{ all -> 0x000a }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neovisionaries.ws.client.HandshakeBuilder.removeExtension(com.neovisionaries.ws.client.WebSocketExtension):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0053, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void removeExtensions(java.lang.String r5) {
        /*
            r4 = this;
            if (r5 != 0) goto L_0x0003
            return
        L_0x0003:
            monitor-enter(r4)
            java.util.List<com.neovisionaries.ws.client.WebSocketExtension> r0 = r4.mExtensions     // Catch:{ all -> 0x000a }
            if (r0 != 0) goto L_0x000c
            monitor-exit(r4)     // Catch:{ all -> 0x000a }
            return
        L_0x000a:
            r5 = move-exception
            goto L_0x0054
        L_0x000c:
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x000a }
            r0.<init>()     // Catch:{ all -> 0x000a }
            java.util.List<com.neovisionaries.ws.client.WebSocketExtension> r1 = r4.mExtensions     // Catch:{ all -> 0x000a }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x000a }
        L_0x0017:
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x000a }
            if (r2 == 0) goto L_0x0031
            java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x000a }
            com.neovisionaries.ws.client.WebSocketExtension r2 = (com.neovisionaries.ws.client.WebSocketExtension) r2     // Catch:{ all -> 0x000a }
            java.lang.String r3 = r2.getName()     // Catch:{ all -> 0x000a }
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x000a }
            if (r3 == 0) goto L_0x0017
            r0.add(r2)     // Catch:{ all -> 0x000a }
            goto L_0x0017
        L_0x0031:
            java.util.Iterator r5 = r0.iterator()     // Catch:{ all -> 0x000a }
        L_0x0035:
            boolean r0 = r5.hasNext()     // Catch:{ all -> 0x000a }
            if (r0 == 0) goto L_0x0047
            java.lang.Object r0 = r5.next()     // Catch:{ all -> 0x000a }
            com.neovisionaries.ws.client.WebSocketExtension r0 = (com.neovisionaries.ws.client.WebSocketExtension) r0     // Catch:{ all -> 0x000a }
            java.util.List<com.neovisionaries.ws.client.WebSocketExtension> r1 = r4.mExtensions     // Catch:{ all -> 0x000a }
            r1.remove(r0)     // Catch:{ all -> 0x000a }
            goto L_0x0035
        L_0x0047:
            java.util.List<com.neovisionaries.ws.client.WebSocketExtension> r5 = r4.mExtensions     // Catch:{ all -> 0x000a }
            int r5 = r5.size()     // Catch:{ all -> 0x000a }
            if (r5 != 0) goto L_0x0052
            r5 = 0
            r4.mExtensions = r5     // Catch:{ all -> 0x000a }
        L_0x0052:
            monitor-exit(r4)     // Catch:{ all -> 0x000a }
            return
        L_0x0054:
            monitor-exit(r4)     // Catch:{ all -> 0x000a }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neovisionaries.ws.client.HandshakeBuilder.removeExtensions(java.lang.String):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0058, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void removeHeaders(java.lang.String r5) {
        /*
            r4 = this;
            if (r5 == 0) goto L_0x005b
            int r0 = r5.length()
            if (r0 != 0) goto L_0x0009
            goto L_0x005b
        L_0x0009:
            monitor-enter(r4)
            java.util.List<java.lang.String[]> r0 = r4.mHeaders     // Catch:{ all -> 0x0010 }
            if (r0 != 0) goto L_0x0012
            monitor-exit(r4)     // Catch:{ all -> 0x0010 }
            return
        L_0x0010:
            r5 = move-exception
            goto L_0x0059
        L_0x0012:
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x0010 }
            r0.<init>()     // Catch:{ all -> 0x0010 }
            java.util.List<java.lang.String[]> r1 = r4.mHeaders     // Catch:{ all -> 0x0010 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0010 }
        L_0x001d:
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x0010 }
            if (r2 == 0) goto L_0x0036
            java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x0010 }
            java.lang.String[] r2 = (java.lang.String[]) r2     // Catch:{ all -> 0x0010 }
            r3 = 0
            r3 = r2[r3]     // Catch:{ all -> 0x0010 }
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x0010 }
            if (r3 == 0) goto L_0x001d
            r0.add(r2)     // Catch:{ all -> 0x0010 }
            goto L_0x001d
        L_0x0036:
            java.util.Iterator r5 = r0.iterator()     // Catch:{ all -> 0x0010 }
        L_0x003a:
            boolean r0 = r5.hasNext()     // Catch:{ all -> 0x0010 }
            if (r0 == 0) goto L_0x004c
            java.lang.Object r0 = r5.next()     // Catch:{ all -> 0x0010 }
            java.lang.String[] r0 = (java.lang.String[]) r0     // Catch:{ all -> 0x0010 }
            java.util.List<java.lang.String[]> r1 = r4.mHeaders     // Catch:{ all -> 0x0010 }
            r1.remove(r0)     // Catch:{ all -> 0x0010 }
            goto L_0x003a
        L_0x004c:
            java.util.List<java.lang.String[]> r5 = r4.mHeaders     // Catch:{ all -> 0x0010 }
            int r5 = r5.size()     // Catch:{ all -> 0x0010 }
            if (r5 != 0) goto L_0x0057
            r5 = 0
            r4.mHeaders = r5     // Catch:{ all -> 0x0010 }
        L_0x0057:
            monitor-exit(r4)     // Catch:{ all -> 0x0010 }
            return
        L_0x0059:
            monitor-exit(r4)     // Catch:{ all -> 0x0010 }
            throw r5
        L_0x005b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neovisionaries.ws.client.HandshakeBuilder.removeHeaders(java.lang.String):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void removeProtocol(java.lang.String r2) {
        /*
            r1 = this;
            if (r2 != 0) goto L_0x0003
            return
        L_0x0003:
            monitor-enter(r1)
            java.util.Set<java.lang.String> r0 = r1.mProtocols     // Catch:{ all -> 0x000a }
            if (r0 != 0) goto L_0x000c
            monitor-exit(r1)     // Catch:{ all -> 0x000a }
            return
        L_0x000a:
            r2 = move-exception
            goto L_0x001c
        L_0x000c:
            r0.remove(r2)     // Catch:{ all -> 0x000a }
            java.util.Set<java.lang.String> r2 = r1.mProtocols     // Catch:{ all -> 0x000a }
            int r2 = r2.size()     // Catch:{ all -> 0x000a }
            if (r2 != 0) goto L_0x001a
            r2 = 0
            r1.mProtocols = r2     // Catch:{ all -> 0x000a }
        L_0x001a:
            monitor-exit(r1)     // Catch:{ all -> 0x000a }
            return
        L_0x001c:
            monitor-exit(r1)     // Catch:{ all -> 0x000a }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neovisionaries.ws.client.HandshakeBuilder.removeProtocol(java.lang.String):void");
    }

    public void setKey(String str) {
        this.mKey = str;
    }

    public void setUserInfo(String str) {
        synchronized (this) {
            this.mUserInfo = str;
        }
    }

    public void setUserInfo(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        setUserInfo(android.support.v4.media.session.a.n(str, ":", str2));
    }

    public void addExtension(String str) {
        addExtension(WebSocketExtension.parse(str));
    }

    public boolean containsExtension(String str) {
        if (str == null) {
            return false;
        }
        synchronized (this) {
            try {
                List<WebSocketExtension> list = this.mExtensions;
                if (list == null) {
                    return false;
                }
                for (WebSocketExtension name : list) {
                    if (name.getName().equals(str)) {
                        return true;
                    }
                }
                return false;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public HandshakeBuilder(HandshakeBuilder handshakeBuilder) {
        this.mSecure = handshakeBuilder.mSecure;
        this.mUserInfo = handshakeBuilder.mUserInfo;
        this.mHost = handshakeBuilder.mHost;
        this.mPath = handshakeBuilder.mPath;
        this.mUri = handshakeBuilder.mUri;
        this.mKey = handshakeBuilder.mKey;
        this.mProtocols = copyProtocols(handshakeBuilder.mProtocols);
        this.mExtensions = copyExtensions(handshakeBuilder.mExtensions);
        this.mHeaders = copyHeaders(handshakeBuilder.mHeaders);
    }
}
