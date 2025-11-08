package com.appsamurai.storyly.exoplayer2.core.util;

import A.a;
import android.os.SystemClock;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.core.upstream.Loader;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.ConcurrentModificationException;

public final class SntpClient {
    public static final String DEFAULT_NTP_HOST = "time.android.com";
    private static final int NTP_LEAP_NOSYNC = 3;
    private static final int NTP_MODE_BROADCAST = 5;
    private static final int NTP_MODE_CLIENT = 3;
    private static final int NTP_MODE_SERVER = 4;
    private static final int NTP_PACKET_SIZE = 48;
    private static final int NTP_PORT = 123;
    private static final int NTP_STRATUM_DEATH = 0;
    private static final int NTP_STRATUM_MAX = 15;
    private static final int NTP_VERSION = 3;
    private static final long OFFSET_1900_TO_1970 = 2208988800L;
    private static final int ORIGINATE_TIME_OFFSET = 24;
    private static final int RECEIVE_TIME_OFFSET = 32;
    private static final int TIMEOUT_MS = 10000;
    private static final int TRANSMIT_TIME_OFFSET = 40;
    /* access modifiers changed from: private */
    @GuardedBy("valueLock")
    public static long elapsedRealtimeOffsetMs = 0;
    /* access modifiers changed from: private */
    @GuardedBy("valueLock")
    public static boolean isInitialized = false;
    /* access modifiers changed from: private */
    public static final Object loaderLock = new Object();
    @GuardedBy("valueLock")
    private static String ntpHost = "time.android.com";
    /* access modifiers changed from: private */
    public static final Object valueLock = new Object();

    public interface InitializationCallback {
        void onInitializationFailed(IOException iOException);

        void onInitialized();
    }

    public static final class NtpTimeCallback implements Loader.Callback<Loader.Loadable> {
        @Nullable
        private final InitializationCallback callback;

        public NtpTimeCallback(@Nullable InitializationCallback initializationCallback) {
            this.callback = initializationCallback;
        }

        public void onLoadCanceled(Loader.Loadable loadable, long j2, long j3, boolean z2) {
        }

        public void onLoadCompleted(Loader.Loadable loadable, long j2, long j3) {
            if (this.callback == null) {
                return;
            }
            if (!SntpClient.isInitialized()) {
                this.callback.onInitializationFailed(new IOException(new ConcurrentModificationException()));
            } else {
                this.callback.onInitialized();
            }
        }

        public Loader.LoadErrorAction onLoadError(Loader.Loadable loadable, long j2, long j3, IOException iOException, int i3) {
            InitializationCallback initializationCallback = this.callback;
            if (initializationCallback != null) {
                initializationCallback.onInitializationFailed(iOException);
            }
            return Loader.DONT_RETRY;
        }
    }

    public static final class NtpTimeLoadable implements Loader.Loadable {
        private NtpTimeLoadable() {
        }

        public void cancelLoad() {
        }

        public void load() throws IOException {
            synchronized (SntpClient.loaderLock) {
                synchronized (SntpClient.valueLock) {
                    if (!SntpClient.isInitialized) {
                        long access$400 = SntpClient.loadNtpTimeOffsetMs();
                        synchronized (SntpClient.valueLock) {
                            long unused = SntpClient.elapsedRealtimeOffsetMs = access$400;
                            boolean unused2 = SntpClient.isInitialized = true;
                        }
                    }
                }
            }
        }
    }

    private SntpClient() {
    }

    private static void checkValidServerReply(byte b3, byte b4, int i3, long j2) throws IOException {
        if (b3 == 3) {
            throw new IOException("SNTP: Unsynchronized server");
        } else if (b4 != 4 && b4 != 5) {
            throw new IOException(a.k("SNTP: Untrusted mode: ", b4));
        } else if (i3 == 0 || i3 > 15) {
            throw new IOException(a.k("SNTP: Untrusted stratum: ", i3));
        } else if (j2 == 0) {
            throw new IOException("SNTP: Zero transmitTime");
        }
    }

    public static long getElapsedRealtimeOffsetMs() {
        long j2;
        synchronized (valueLock) {
            try {
                j2 = isInitialized ? elapsedRealtimeOffsetMs : C.TIME_UNSET;
            } catch (Throwable th) {
                throw th;
            }
        }
        return j2;
    }

    public static String getNtpHost() {
        String str;
        synchronized (valueLock) {
            str = ntpHost;
        }
        return str;
    }

    public static void initialize(@Nullable Loader loader, @Nullable InitializationCallback initializationCallback) {
        if (!isInitialized()) {
            if (loader == null) {
                loader = new Loader("SntpClient");
            }
            loader.startLoading(new NtpTimeLoadable(), new NtpTimeCallback(initializationCallback), 1);
        } else if (initializationCallback != null) {
            initializationCallback.onInitialized();
        }
    }

    public static boolean isInitialized() {
        boolean z2;
        synchronized (valueLock) {
            z2 = isInitialized;
        }
        return z2;
    }

    /* access modifiers changed from: private */
    public static long loadNtpTimeOffsetMs() throws IOException {
        InetAddress byName = InetAddress.getByName(getNtpHost());
        DatagramSocket datagramSocket = new DatagramSocket();
        try {
            datagramSocket.setSoTimeout(10000);
            byte[] bArr = new byte[48];
            DatagramPacket datagramPacket = new DatagramPacket(bArr, 48, byName, 123);
            bArr[0] = Ascii.ESC;
            long currentTimeMillis = System.currentTimeMillis();
            long elapsedRealtime = SystemClock.elapsedRealtime();
            writeTimestamp(bArr, 40, currentTimeMillis);
            datagramSocket.send(datagramPacket);
            datagramSocket.receive(new DatagramPacket(bArr, 48));
            long elapsedRealtime2 = SystemClock.elapsedRealtime();
            long j2 = (elapsedRealtime2 - elapsedRealtime) + currentTimeMillis;
            byte b3 = bArr[0];
            long readTimestamp = readTimestamp(bArr, 24);
            long readTimestamp2 = readTimestamp(bArr, 32);
            long readTimestamp3 = readTimestamp(bArr, 40);
            checkValidServerReply((byte) ((b3 >> 6) & 3), (byte) (b3 & 7), bArr[1] & 255, readTimestamp3);
            long j3 = (j2 + (((readTimestamp3 - j2) + (readTimestamp2 - readTimestamp)) / 2)) - elapsedRealtime2;
            datagramSocket.close();
            return j3;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private static long read32(byte[] bArr, int i3) {
        byte b3 = bArr[i3];
        byte b4 = bArr[i3 + 1];
        byte b5 = bArr[i3 + 2];
        byte b6 = bArr[i3 + 3];
        byte b7 = b3 & true;
        int i4 = b3;
        if (b7 == true) {
            i4 = (b3 & Byte.MAX_VALUE) + 128;
        }
        byte b8 = b4 & true;
        int i5 = b4;
        if (b8 == true) {
            i5 = (b4 & Byte.MAX_VALUE) + 128;
        }
        byte b9 = b5 & true;
        int i6 = b5;
        if (b9 == true) {
            i6 = (b5 & Byte.MAX_VALUE) + 128;
        }
        byte b10 = b6 & true;
        int i7 = b6;
        if (b10 == true) {
            i7 = (b6 & Byte.MAX_VALUE) + 128;
        }
        return (((long) i4) << 24) + (((long) i5) << 16) + (((long) i6) << 8) + ((long) i7);
    }

    private static long readTimestamp(byte[] bArr, int i3) {
        long read32 = read32(bArr, i3);
        long read322 = read32(bArr, i3 + 4);
        if (read32 == 0 && read322 == 0) {
            return 0;
        }
        return ((read322 * 1000) / 4294967296L) + ((read32 - OFFSET_1900_TO_1970) * 1000);
    }

    public static void setNtpHost(String str) {
        synchronized (valueLock) {
            try {
                if (!ntpHost.equals(str)) {
                    ntpHost = str;
                    isInitialized = false;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private static void writeTimestamp(byte[] bArr, int i3, long j2) {
        if (j2 == 0) {
            Arrays.fill(bArr, i3, i3 + 8, (byte) 0);
            return;
        }
        long j3 = j2 / 1000;
        long j4 = j2 - (j3 * 1000);
        long j5 = j3 + OFFSET_1900_TO_1970;
        bArr[i3] = (byte) ((int) (j5 >> 24));
        bArr[i3 + 1] = (byte) ((int) (j5 >> 16));
        bArr[i3 + 2] = (byte) ((int) (j5 >> 8));
        bArr[i3 + 3] = (byte) ((int) j5);
        long j6 = (j4 * 4294967296L) / 1000;
        bArr[i3 + 4] = (byte) ((int) (j6 >> 24));
        bArr[i3 + 5] = (byte) ((int) (j6 >> 16));
        bArr[i3 + 6] = (byte) ((int) (j6 >> 8));
        bArr[i3 + 7] = (byte) ((int) (Math.random() * 255.0d));
    }
}
