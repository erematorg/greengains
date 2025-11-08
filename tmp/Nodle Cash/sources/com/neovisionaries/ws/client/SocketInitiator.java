package com.neovisionaries.ws.client;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;

public class SocketInitiator {
    private final Address mAddress;
    private final int mConnectTimeout;
    private final int mFallbackDelay;
    private final DualStackMode mMode;
    private final String[] mServerNames;
    private final SocketFactory mSocketFactory;

    public class Signal {
        private final CountDownLatch mLatch = new CountDownLatch(1);
        private final int mMaxDelay;

        public Signal(int i3) {
            this.mMaxDelay = i3;
        }

        public void await() throws InterruptedException {
            this.mLatch.await((long) this.mMaxDelay, TimeUnit.MILLISECONDS);
        }

        public void done() {
            this.mLatch.countDown();
        }

        public boolean isDone() {
            return this.mLatch.getCount() == 0;
        }
    }

    public class SocketFuture {
        private Exception mException;
        private CountDownLatch mLatch;
        private List<SocketRacer> mRacers;
        private Socket mSocket;

        private SocketFuture() {
        }

        public Socket await(List<SocketRacer> list) throws Exception {
            this.mRacers = list;
            this.mLatch = new CountDownLatch(this.mRacers.size());
            for (SocketRacer start : this.mRacers) {
                start.start();
            }
            this.mLatch.await();
            Socket socket = this.mSocket;
            if (socket != null) {
                return socket;
            }
            Exception exc = this.mException;
            if (exc != null) {
                throw exc;
            }
            throw new WebSocketException(WebSocketError.SOCKET_CONNECT_ERROR, "No viable interface to connect");
        }

        public synchronized boolean hasSocket() {
            return this.mSocket != null;
        }

        public synchronized void setException(Exception exc) {
            try {
                CountDownLatch countDownLatch = this.mLatch;
                if (countDownLatch == null || this.mRacers == null) {
                    throw new IllegalStateException("Cannot set exception before awaiting!");
                }
                if (this.mException == null) {
                    this.mException = exc;
                }
                countDownLatch.countDown();
            } catch (Throwable th) {
                throw th;
            }
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(5:6|(2:8|(4:11|(2:13|28)(2:14|29)|27|9))(2:16|17)|18|19|20) */
        /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x0033 */
        /* JADX WARNING: Unknown top exception splitter block from list: {B:18:0x0033=Splitter:B:18:0x0033, B:22:0x003a=Splitter:B:22:0x003a} */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public synchronized void setSocket(com.neovisionaries.ws.client.SocketInitiator.SocketRacer r3, java.net.Socket r4) {
            /*
                r2 = this;
                monitor-enter(r2)
                java.util.concurrent.CountDownLatch r0 = r2.mLatch     // Catch:{ all -> 0x002e }
                if (r0 == 0) goto L_0x003a
                java.util.List<com.neovisionaries.ws.client.SocketInitiator$SocketRacer> r0 = r2.mRacers     // Catch:{ all -> 0x002e }
                if (r0 == 0) goto L_0x003a
                java.net.Socket r1 = r2.mSocket     // Catch:{ all -> 0x002e }
                if (r1 != 0) goto L_0x0030
                r2.mSocket = r4     // Catch:{ all -> 0x002e }
                java.util.Iterator r4 = r0.iterator()     // Catch:{ all -> 0x002e }
            L_0x0013:
                boolean r0 = r4.hasNext()     // Catch:{ all -> 0x002e }
                if (r0 == 0) goto L_0x0033
                java.lang.Object r0 = r4.next()     // Catch:{ all -> 0x002e }
                com.neovisionaries.ws.client.SocketInitiator$SocketRacer r0 = (com.neovisionaries.ws.client.SocketInitiator.SocketRacer) r0     // Catch:{ all -> 0x002e }
                if (r0 != r3) goto L_0x0022
                goto L_0x0013
            L_0x0022:
                java.lang.InterruptedException r1 = new java.lang.InterruptedException     // Catch:{ all -> 0x002e }
                r1.<init>()     // Catch:{ all -> 0x002e }
                r0.abort(r1)     // Catch:{ all -> 0x002e }
                r0.interrupt()     // Catch:{ all -> 0x002e }
                goto L_0x0013
            L_0x002e:
                r3 = move-exception
                goto L_0x0042
            L_0x0030:
                r4.close()     // Catch:{ IOException -> 0x0033 }
            L_0x0033:
                java.util.concurrent.CountDownLatch r3 = r2.mLatch     // Catch:{ all -> 0x002e }
                r3.countDown()     // Catch:{ all -> 0x002e }
                monitor-exit(r2)
                return
            L_0x003a:
                java.lang.IllegalStateException r3 = new java.lang.IllegalStateException     // Catch:{ all -> 0x002e }
                java.lang.String r4 = "Cannot set socket before awaiting!"
                r3.<init>(r4)     // Catch:{ all -> 0x002e }
                throw r3     // Catch:{ all -> 0x002e }
            L_0x0042:
                monitor-exit(r2)     // Catch:{ all -> 0x002e }
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.neovisionaries.ws.client.SocketInitiator.SocketFuture.setSocket(com.neovisionaries.ws.client.SocketInitiator$SocketRacer, java.net.Socket):void");
        }
    }

    public class SocketRacer extends Thread {
        private final int mConnectTimeout;
        private final Signal mDoneSignal;
        private final SocketFuture mFuture;
        private String[] mServerNames;
        private final SocketAddress mSocketAddress;
        private final SocketFactory mSocketFactory;
        private final Signal mStartSignal;

        public SocketRacer(SocketFuture socketFuture, SocketFactory socketFactory, SocketAddress socketAddress, String[] strArr, int i3, Signal signal, Signal signal2) {
            this.mFuture = socketFuture;
            this.mSocketFactory = socketFactory;
            this.mSocketAddress = socketAddress;
            this.mServerNames = strArr;
            this.mConnectTimeout = i3;
            this.mStartSignal = signal;
            this.mDoneSignal = signal2;
        }

        private void complete(Socket socket) {
            synchronized (this.mFuture) {
                try {
                    if (!this.mDoneSignal.isDone()) {
                        this.mFuture.setSocket(this, socket);
                        this.mDoneSignal.done();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public void abort(Exception exc) {
            synchronized (this.mFuture) {
                try {
                    if (!this.mDoneSignal.isDone()) {
                        this.mFuture.setException(exc);
                        this.mDoneSignal.done();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public void run() {
            Socket socket = null;
            try {
                Signal signal = this.mStartSignal;
                if (signal != null) {
                    signal.await();
                }
                if (!this.mFuture.hasSocket()) {
                    Socket createSocket = this.mSocketFactory.createSocket();
                    SNIHelper.setServerNames(createSocket, this.mServerNames);
                    createSocket.connect(this.mSocketAddress, this.mConnectTimeout);
                    complete(createSocket);
                }
            } catch (Exception e3) {
                abort(e3);
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException unused) {
                    }
                }
            }
        }
    }

    public SocketInitiator(SocketFactory socketFactory, Address address, int i3, String[] strArr, DualStackMode dualStackMode, int i4) {
        this.mSocketFactory = socketFactory;
        this.mAddress = address;
        this.mConnectTimeout = i3;
        this.mServerNames = strArr;
        this.mMode = dualStackMode;
        this.mFallbackDelay = i4;
    }

    public Socket establish(InetAddress[] inetAddressArr) throws Exception {
        SocketInitiator socketInitiator = this;
        InetAddress[] inetAddressArr2 = inetAddressArr;
        SocketFuture socketFuture = new SocketFuture();
        ArrayList arrayList = new ArrayList(inetAddressArr2.length);
        int length = inetAddressArr2.length;
        int i3 = 0;
        Signal signal = null;
        int i4 = 0;
        while (i4 < length) {
            InetAddress inetAddress = inetAddressArr2[i4];
            DualStackMode dualStackMode = socketInitiator.mMode;
            if ((dualStackMode != DualStackMode.IPV4_ONLY || (inetAddress instanceof Inet4Address)) && (dualStackMode != DualStackMode.IPV6_ONLY || (inetAddress instanceof Inet6Address))) {
                int i5 = i3 + socketInitiator.mFallbackDelay;
                Signal signal2 = new Signal(i5);
                InetSocketAddress inetSocketAddress = new InetSocketAddress(inetAddress, socketInitiator.mAddress.getPort());
                SocketFactory socketFactory = socketInitiator.mSocketFactory;
                String[] strArr = socketInitiator.mServerNames;
                int i6 = socketInitiator.mConnectTimeout;
                SocketRacer socketRacer = r0;
                SocketRacer socketRacer2 = new SocketRacer(socketFuture, socketFactory, inetSocketAddress, strArr, i6, signal, signal2);
                arrayList.add(socketRacer);
                i3 = i5;
                signal = signal2;
            }
            i4++;
            socketInitiator = this;
        }
        return socketFuture.await(arrayList);
    }
}
