package com.neovisionaries.ws.client;

import androidx.browser.trusted.c;
import androidx.camera.camera2.internal.C0118y;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class HandshakeReader {
    private static final String ACCEPT_MAGIC = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
    private final WebSocket mWebSocket;

    public HandshakeReader(WebSocket webSocket) {
        this.mWebSocket = webSocket;
    }

    private int getContentLength(Map<String, List<String>> map) {
        try {
            return Integer.parseInt((String) map.get(HttpHeaders.CONTENT_LENGTH).get(0));
        } catch (Exception unused) {
            return -1;
        }
    }

    private void parseHttpHeader(Map<String, List<String>> map, String str) {
        String[] split = str.split(":", 2);
        if (split.length >= 2) {
            String trim = split[0].trim();
            String trim2 = split[1].trim();
            List list = map.get(trim);
            if (list == null) {
                list = new ArrayList();
                map.put(trim, list);
            }
            list.add(trim2);
        }
    }

    private byte[] readBody(Map<String, List<String>> map, WebSocketInputStream webSocketInputStream) {
        int contentLength = getContentLength(map);
        if (contentLength <= 0) {
            return null;
        }
        try {
            byte[] bArr = new byte[contentLength];
            webSocketInputStream.readBytes(bArr, contentLength);
            return bArr;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0043  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Map<java.lang.String, java.util.List<java.lang.String>> readHttpHeaders(com.neovisionaries.ws.client.WebSocketInputStream r6) throws com.neovisionaries.ws.client.WebSocketException {
        /*
            r5 = this;
            java.util.TreeMap r0 = new java.util.TreeMap
            java.util.Comparator r1 = java.lang.String.CASE_INSENSITIVE_ORDER
            r0.<init>(r1)
            r1 = 0
        L_0x0008:
            java.lang.String r2 = r6.readLine()     // Catch:{ IOException -> 0x004b }
            if (r2 == 0) goto L_0x0041
            int r3 = r2.length()
            if (r3 != 0) goto L_0x0015
            goto L_0x0041
        L_0x0015:
            r3 = 0
            char r3 = r2.charAt(r3)
            r4 = 32
            if (r3 == r4) goto L_0x0032
            r4 = 9
            if (r3 != r4) goto L_0x0023
            goto L_0x0032
        L_0x0023:
            if (r1 == 0) goto L_0x002c
            java.lang.String r1 = r1.toString()
            r5.parseHttpHeader(r0, r1)
        L_0x002c:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r2)
            goto L_0x0008
        L_0x0032:
            if (r1 != 0) goto L_0x0035
            goto L_0x0008
        L_0x0035:
            java.lang.String r3 = "^[ \t]+"
            java.lang.String r4 = " "
            java.lang.String r2 = r2.replaceAll(r3, r4)
            r1.append(r2)
            goto L_0x0008
        L_0x0041:
            if (r1 == 0) goto L_0x004a
            java.lang.String r6 = r1.toString()
            r5.parseHttpHeader(r0, r6)
        L_0x004a:
            return r0
        L_0x004b:
            r5 = move-exception
            com.neovisionaries.ws.client.WebSocketException r6 = new com.neovisionaries.ws.client.WebSocketException
            com.neovisionaries.ws.client.WebSocketError r0 = com.neovisionaries.ws.client.WebSocketError.HTTP_HEADER_FAILURE
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "An error occurred while HTTP header section was being read: "
            r1.<init>(r2)
            java.lang.String r1 = com.appsamurai.storyly.exoplayer2.core.source.chunk.a.h(r5, r1)
            r6.<init>(r0, r1, r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neovisionaries.ws.client.HandshakeReader.readHttpHeaders(com.neovisionaries.ws.client.WebSocketInputStream):java.util.Map");
    }

    private StatusLine readStatusLine(WebSocketInputStream webSocketInputStream) throws WebSocketException {
        try {
            String readLine = webSocketInputStream.readLine();
            if (readLine == null || readLine.length() == 0) {
                throw new WebSocketException(WebSocketError.STATUS_LINE_EMPTY, "The status line of the opening handshake response is empty.");
            }
            try {
                return new StatusLine(readLine);
            } catch (Exception unused) {
                throw new WebSocketException(WebSocketError.STATUS_LINE_BAD_FORMAT, "The status line of the opening handshake response is badly formatted. The status line is: ".concat(readLine));
            }
        } catch (IOException e3) {
            throw new WebSocketException(WebSocketError.OPENING_HANDSHAKE_RESPONSE_FAILURE, a.h(e3, new StringBuilder("Failed to read an opening handshake response from the server: ")), e3);
        }
    }

    private void validateAccept(StatusLine statusLine, Map<String, List<String>> map, String str) throws WebSocketException {
        List list = map.get(HttpHeaders.SEC_WEBSOCKET_ACCEPT);
        if (list != null) {
            try {
                if (!Base64.encode(MessageDigest.getInstance("SHA-1").digest(Misc.getBytesUTF8(android.support.v4.media.session.a.m(str, "258EAFA5-E914-47DA-95CA-C5AB0DC85B11")))).equals((String) list.get(0))) {
                    throw new OpeningHandshakeException(WebSocketError.UNEXPECTED_SEC_WEBSOCKET_ACCEPT_HEADER, "The value of 'Sec-WebSocket-Accept' header is different from the expected one.", statusLine, map);
                }
            } catch (Exception unused) {
            }
        } else {
            throw new OpeningHandshakeException(WebSocketError.NO_SEC_WEBSOCKET_ACCEPT_HEADER, "The opening handshake response does not contain 'Sec-WebSocket-Accept' header.", statusLine, map);
        }
    }

    private void validateConnection(StatusLine statusLine, Map<String, List<String>> map) throws WebSocketException {
        List<String> list = map.get(HttpHeaders.CONNECTION);
        if (list == null || list.size() == 0) {
            throw new OpeningHandshakeException(WebSocketError.NO_CONNECTION_HEADER, "The opening handshake response does not contain 'Connection' header.", statusLine, map);
        }
        for (String split : list) {
            String[] split2 = split.split("\\s*,\\s*");
            int length = split2.length;
            int i3 = 0;
            while (true) {
                if (i3 < length) {
                    if (!HttpHeaders.UPGRADE.equalsIgnoreCase(split2[i3])) {
                        i3++;
                    } else {
                        return;
                    }
                }
            }
        }
        throw new OpeningHandshakeException(WebSocketError.NO_UPGRADE_IN_CONNECTION_HEADER, "'Upgrade' was not found in 'Connection' header.", statusLine, map);
    }

    private void validateExtensionCombination(StatusLine statusLine, Map<String, List<String>> map, List<WebSocketExtension> list) throws WebSocketException {
        WebSocketExtension webSocketExtension = null;
        for (WebSocketExtension next : list) {
            if (next instanceof PerMessageCompressionExtension) {
                if (webSocketExtension == null) {
                    webSocketExtension = next;
                } else {
                    throw new OpeningHandshakeException(WebSocketError.EXTENSIONS_CONFLICT, C0118y.g("'", webSocketExtension.getName(), "' extension and '", next.getName(), "' extension conflict with each other."), statusLine, map);
                }
            }
        }
    }

    private void validateExtensions(StatusLine statusLine, Map<String, List<String>> map) throws WebSocketException {
        List<String> list = map.get(HttpHeaders.SEC_WEBSOCKET_EXTENSIONS);
        if (list != null && list.size() != 0) {
            ArrayList arrayList = new ArrayList();
            for (String split : list) {
                String[] split2 = split.split("\\s*,\\s*");
                int length = split2.length;
                int i3 = 0;
                while (true) {
                    if (i3 < length) {
                        String str = split2[i3];
                        WebSocketExtension parse = WebSocketExtension.parse(str);
                        if (parse != null) {
                            String name = parse.getName();
                            if (this.mWebSocket.getHandshakeBuilder().containsExtension(name)) {
                                parse.validate();
                                arrayList.add(parse);
                                i3++;
                            } else {
                                throw new OpeningHandshakeException(WebSocketError.UNSUPPORTED_EXTENSION, c.a("The extension contained in the Sec-WebSocket-Extensions header is not supported: ", name), statusLine, map);
                            }
                        } else {
                            throw new OpeningHandshakeException(WebSocketError.EXTENSION_PARSE_ERROR, c.a("The value in 'Sec-WebSocket-Extensions' failed to be parsed: ", str), statusLine, map);
                        }
                    }
                }
            }
            validateExtensionCombination(statusLine, map, arrayList);
            this.mWebSocket.setAgreedExtensions(arrayList);
        }
    }

    private void validateProtocol(StatusLine statusLine, Map<String, List<String>> map) throws WebSocketException {
        String str;
        List list = map.get(HttpHeaders.SEC_WEBSOCKET_PROTOCOL);
        if (list != null && (str = (String) list.get(0)) != null && str.length() != 0) {
            if (this.mWebSocket.getHandshakeBuilder().containsProtocol(str)) {
                this.mWebSocket.setAgreedProtocol(str);
                return;
            }
            throw new OpeningHandshakeException(WebSocketError.UNSUPPORTED_PROTOCOL, "The protocol contained in the Sec-WebSocket-Protocol header is not supported: ".concat(str), statusLine, map);
        }
    }

    private void validateStatusLine(StatusLine statusLine, Map<String, List<String>> map, WebSocketInputStream webSocketInputStream) throws WebSocketException {
        if (statusLine.getStatusCode() != 101) {
            byte[] readBody = readBody(map, webSocketInputStream);
            WebSocketError webSocketError = WebSocketError.NOT_SWITCHING_PROTOCOLS;
            throw new OpeningHandshakeException(webSocketError, "The status code of the opening handshake response is not '101 Switching Protocols'. The status line is: " + statusLine, statusLine, map, readBody);
        }
    }

    private void validateUpgrade(StatusLine statusLine, Map<String, List<String>> map) throws WebSocketException {
        List<String> list = map.get(HttpHeaders.UPGRADE);
        if (list == null || list.size() == 0) {
            throw new OpeningHandshakeException(WebSocketError.NO_UPGRADE_HEADER, "The opening handshake response does not contain 'Upgrade' header.", statusLine, map);
        }
        for (String split : list) {
            String[] split2 = split.split("\\s*,\\s*");
            int length = split2.length;
            int i3 = 0;
            while (true) {
                if (i3 < length) {
                    if (!"websocket".equalsIgnoreCase(split2[i3])) {
                        i3++;
                    } else {
                        return;
                    }
                }
            }
        }
        throw new OpeningHandshakeException(WebSocketError.NO_WEBSOCKET_IN_UPGRADE_HEADER, "'websocket' was not found in 'Upgrade' header.", statusLine, map);
    }

    public Map<String, List<String>> readHandshake(WebSocketInputStream webSocketInputStream, String str) throws WebSocketException {
        StatusLine readStatusLine = readStatusLine(webSocketInputStream);
        Map<String, List<String>> readHttpHeaders = readHttpHeaders(webSocketInputStream);
        validateStatusLine(readStatusLine, readHttpHeaders, webSocketInputStream);
        validateUpgrade(readStatusLine, readHttpHeaders);
        validateConnection(readStatusLine, readHttpHeaders);
        validateAccept(readStatusLine, readHttpHeaders, str);
        validateExtensions(readStatusLine, readHttpHeaders);
        validateProtocol(readStatusLine, readHttpHeaders);
        return readHttpHeaders;
    }
}
