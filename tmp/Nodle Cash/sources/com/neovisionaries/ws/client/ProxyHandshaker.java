package com.neovisionaries.ws.client;

import androidx.constraintlayout.core.state.b;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;

class ProxyHandshaker {
    private static final String RN = "\r\n";
    private final String mHost;
    private final int mPort;
    private final ProxySettings mSettings;

    public ProxyHandshaker(String str, int i3, ProxySettings proxySettings) {
        this.mHost = str;
        this.mPort = i3;
        this.mSettings = proxySettings;
    }

    private void addHeaders(StringBuilder sb) {
        for (Map.Entry next : this.mSettings.getHeaders().entrySet()) {
            String str = (String) next.getKey();
            for (String str2 : (List) next.getValue()) {
                if (str2 == null) {
                    str2 = "";
                }
                b.w(sb, str, ": ", str2, "\r\n");
            }
        }
    }

    private void addProxyAuthorization(StringBuilder sb) {
        String id = this.mSettings.getId();
        if (id != null && id.length() != 0) {
            String password = this.mSettings.getPassword();
            if (password == null) {
                password = "";
            }
            sb.append("Proxy-Authorization: Basic ");
            sb.append(Base64.encode(id + ":" + password));
            sb.append("\r\n");
        }
    }

    private String buildRequest() {
        String format = String.format("%s:%d", new Object[]{this.mHost, Integer.valueOf(this.mPort)});
        StringBuilder sb = new StringBuilder();
        sb.append("CONNECT ");
        sb.append(format);
        sb.append(" HTTP/1.1\r\nHost: ");
        sb.append(format);
        sb.append("\r\n");
        addHeaders(sb);
        addProxyAuthorization(sb);
        sb.append("\r\n");
        return sb.toString();
    }

    private void readStatusLine(InputStream inputStream) throws IOException {
        String readLine = Misc.readLine(inputStream, "UTF-8");
        if (readLine == null || readLine.length() == 0) {
            throw new IOException("The response from the proxy server does not contain a status line.");
        }
        String[] split = readLine.split(" +", 3);
        if (split.length < 2) {
            throw new IOException("The status line in the response from the proxy server is badly formatted. The status line is: ".concat(readLine));
        } else if (!"200".equals(split[1])) {
            throw new IOException("The status code in the response from the proxy server is not '200 Connection established'. The status line is: ".concat(readLine));
        }
    }

    private void receiveResponse(Socket socket) throws IOException {
        InputStream inputStream = socket.getInputStream();
        readStatusLine(inputStream);
        skipHeaders(inputStream);
    }

    private void sendRequest(Socket socket) throws IOException {
        byte[] bytesUTF8 = Misc.getBytesUTF8(buildRequest());
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(bytesUTF8);
        outputStream.flush();
    }

    private void skipHeaders(InputStream inputStream) throws IOException {
        while (true) {
            int i3 = 0;
            while (true) {
                int read = inputStream.read();
                if (read == -1) {
                    throw new EOFException("The end of the stream from the proxy server was reached unexpectedly.");
                } else if (read == 10) {
                    if (i3 == 0) {
                        return;
                    }
                } else if (read != 13) {
                    i3++;
                } else {
                    int read2 = inputStream.read();
                    if (read2 == -1) {
                        throw new EOFException("The end of the stream from the proxy server was reached unexpectedly after a carriage return.");
                    } else if (read2 != 10) {
                        i3 += 2;
                    } else if (i3 == 0) {
                        return;
                    }
                }
            }
        }
    }

    public String getProxiedHostname() {
        return this.mHost;
    }

    public void perform(Socket socket) throws IOException {
        sendRequest(socket);
        receiveResponse(socket);
    }
}
