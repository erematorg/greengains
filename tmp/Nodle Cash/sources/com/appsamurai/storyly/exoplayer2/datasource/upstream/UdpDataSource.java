package com.appsamurai.storyly.exoplayer2.datasource.upstream;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.PlaybackException;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.SocketTimeoutException;

public final class UdpDataSource extends BaseDataSource {
    public static final int DEFAULT_MAX_PACKET_SIZE = 2000;
    public static final int DEFAULT_SOCKET_TIMEOUT_MILLIS = 8000;
    public static final int UDP_PORT_UNSET = -1;
    @Nullable
    private InetAddress address;
    @Nullable
    private MulticastSocket multicastSocket;
    private boolean opened;
    private final DatagramPacket packet;
    private final byte[] packetBuffer;
    private int packetRemaining;
    @Nullable
    private DatagramSocket socket;
    private final int socketTimeoutMillis;
    @Nullable
    private Uri uri;

    public static final class UdpDataSourceException extends DataSourceException {
        public UdpDataSourceException(Throwable th, int i3) {
            super(th, i3);
        }
    }

    public UdpDataSource() {
        this(2000);
    }

    public void close() {
        this.uri = null;
        MulticastSocket multicastSocket2 = this.multicastSocket;
        if (multicastSocket2 != null) {
            try {
                multicastSocket2.leaveGroup((InetAddress) Assertions.checkNotNull(this.address));
            } catch (IOException unused) {
            }
            this.multicastSocket = null;
        }
        DatagramSocket datagramSocket = this.socket;
        if (datagramSocket != null) {
            datagramSocket.close();
            this.socket = null;
        }
        this.address = null;
        this.packetRemaining = 0;
        if (this.opened) {
            this.opened = false;
            transferEnded();
        }
    }

    public int getLocalPort() {
        DatagramSocket datagramSocket = this.socket;
        if (datagramSocket == null) {
            return -1;
        }
        return datagramSocket.getLocalPort();
    }

    @Nullable
    public Uri getUri() {
        return this.uri;
    }

    public long open(DataSpec dataSpec) throws UdpDataSourceException {
        Uri uri2 = dataSpec.uri;
        this.uri = uri2;
        String str = (String) Assertions.checkNotNull(uri2.getHost());
        int port = this.uri.getPort();
        transferInitializing(dataSpec);
        try {
            this.address = InetAddress.getByName(str);
            InetSocketAddress inetSocketAddress = new InetSocketAddress(this.address, port);
            if (this.address.isMulticastAddress()) {
                MulticastSocket multicastSocket2 = new MulticastSocket(inetSocketAddress);
                this.multicastSocket = multicastSocket2;
                multicastSocket2.joinGroup(this.address);
                this.socket = this.multicastSocket;
            } else {
                this.socket = new DatagramSocket(inetSocketAddress);
            }
            this.socket.setSoTimeout(this.socketTimeoutMillis);
            this.opened = true;
            transferStarted(dataSpec);
            return -1;
        } catch (SecurityException e3) {
            throw new UdpDataSourceException(e3, PlaybackException.ERROR_CODE_IO_NO_PERMISSION);
        } catch (IOException e4) {
            throw new UdpDataSourceException(e4, PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED);
        }
    }

    public int read(byte[] bArr, int i3, int i4) throws UdpDataSourceException {
        if (i4 == 0) {
            return 0;
        }
        if (this.packetRemaining == 0) {
            try {
                ((DatagramSocket) Assertions.checkNotNull(this.socket)).receive(this.packet);
                int length = this.packet.getLength();
                this.packetRemaining = length;
                bytesTransferred(length);
            } catch (SocketTimeoutException e3) {
                throw new UdpDataSourceException(e3, PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_TIMEOUT);
            } catch (IOException e4) {
                throw new UdpDataSourceException(e4, PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED);
            }
        }
        int length2 = this.packet.getLength();
        int i5 = this.packetRemaining;
        int min = Math.min(i5, i4);
        System.arraycopy(this.packetBuffer, length2 - i5, bArr, i3, min);
        this.packetRemaining -= min;
        return min;
    }

    public UdpDataSource(int i3) {
        this(i3, 8000);
    }

    public UdpDataSource(int i3, int i4) {
        super(true);
        this.socketTimeoutMillis = i4;
        byte[] bArr = new byte[i3];
        this.packetBuffer = bArr;
        this.packet = new DatagramPacket(bArr, 0, i3);
    }
}
