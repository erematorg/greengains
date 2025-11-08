package com.neovisionaries.ws.client;

import com.neovisionaries.ws.client.StateManager;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

class ReadingThread extends WebSocketThread {
    private long mCloseDelay;
    private WebSocketFrame mCloseFrame;
    private Object mCloseLock = new Object();
    private CloseTask mCloseTask;
    private Timer mCloseTimer;
    private List<WebSocketFrame> mContinuation = new ArrayList();
    private boolean mNotWaitForCloseFrame;
    private final PerMessageCompressionExtension mPMCE;
    private boolean mStopRequested;

    /* renamed from: com.neovisionaries.ws.client.ReadingThread$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$neovisionaries$ws$client$WebSocketError;

        /* JADX WARNING: Can't wrap try/catch for region: R(32:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|32) */
        /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0090 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x009c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x00a8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.neovisionaries.ws.client.WebSocketError[] r0 = com.neovisionaries.ws.client.WebSocketError.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$neovisionaries$ws$client$WebSocketError = r0
                com.neovisionaries.ws.client.WebSocketError r1 = com.neovisionaries.ws.client.WebSocketError.INSUFFICENT_DATA     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$neovisionaries$ws$client$WebSocketError     // Catch:{ NoSuchFieldError -> 0x001d }
                com.neovisionaries.ws.client.WebSocketError r1 = com.neovisionaries.ws.client.WebSocketError.INVALID_PAYLOAD_LENGTH     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$neovisionaries$ws$client$WebSocketError     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.neovisionaries.ws.client.WebSocketError r1 = com.neovisionaries.ws.client.WebSocketError.NO_MORE_FRAME     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$neovisionaries$ws$client$WebSocketError     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.neovisionaries.ws.client.WebSocketError r1 = com.neovisionaries.ws.client.WebSocketError.TOO_LONG_PAYLOAD     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$neovisionaries$ws$client$WebSocketError     // Catch:{ NoSuchFieldError -> 0x003e }
                com.neovisionaries.ws.client.WebSocketError r1 = com.neovisionaries.ws.client.WebSocketError.INSUFFICIENT_MEMORY_FOR_PAYLOAD     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$neovisionaries$ws$client$WebSocketError     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.neovisionaries.ws.client.WebSocketError r1 = com.neovisionaries.ws.client.WebSocketError.NON_ZERO_RESERVED_BITS     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$neovisionaries$ws$client$WebSocketError     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.neovisionaries.ws.client.WebSocketError r1 = com.neovisionaries.ws.client.WebSocketError.UNEXPECTED_RESERVED_BIT     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$neovisionaries$ws$client$WebSocketError     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.neovisionaries.ws.client.WebSocketError r1 = com.neovisionaries.ws.client.WebSocketError.UNKNOWN_OPCODE     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$com$neovisionaries$ws$client$WebSocketError     // Catch:{ NoSuchFieldError -> 0x006c }
                com.neovisionaries.ws.client.WebSocketError r1 = com.neovisionaries.ws.client.WebSocketError.FRAME_MASKED     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$com$neovisionaries$ws$client$WebSocketError     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.neovisionaries.ws.client.WebSocketError r1 = com.neovisionaries.ws.client.WebSocketError.FRAGMENTED_CONTROL_FRAME     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$com$neovisionaries$ws$client$WebSocketError     // Catch:{ NoSuchFieldError -> 0x0084 }
                com.neovisionaries.ws.client.WebSocketError r1 = com.neovisionaries.ws.client.WebSocketError.UNEXPECTED_CONTINUATION_FRAME     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = $SwitchMap$com$neovisionaries$ws$client$WebSocketError     // Catch:{ NoSuchFieldError -> 0x0090 }
                com.neovisionaries.ws.client.WebSocketError r1 = com.neovisionaries.ws.client.WebSocketError.CONTINUATION_NOT_CLOSED     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                int[] r0 = $SwitchMap$com$neovisionaries$ws$client$WebSocketError     // Catch:{ NoSuchFieldError -> 0x009c }
                com.neovisionaries.ws.client.WebSocketError r1 = com.neovisionaries.ws.client.WebSocketError.TOO_LONG_CONTROL_FRAME_PAYLOAD     // Catch:{ NoSuchFieldError -> 0x009c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009c }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009c }
            L_0x009c:
                int[] r0 = $SwitchMap$com$neovisionaries$ws$client$WebSocketError     // Catch:{ NoSuchFieldError -> 0x00a8 }
                com.neovisionaries.ws.client.WebSocketError r1 = com.neovisionaries.ws.client.WebSocketError.INTERRUPTED_IN_READING     // Catch:{ NoSuchFieldError -> 0x00a8 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a8 }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00a8 }
            L_0x00a8:
                int[] r0 = $SwitchMap$com$neovisionaries$ws$client$WebSocketError     // Catch:{ NoSuchFieldError -> 0x00b4 }
                com.neovisionaries.ws.client.WebSocketError r1 = com.neovisionaries.ws.client.WebSocketError.IO_ERROR_IN_READING     // Catch:{ NoSuchFieldError -> 0x00b4 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b4 }
                r2 = 15
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00b4 }
            L_0x00b4:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.neovisionaries.ws.client.ReadingThread.AnonymousClass1.<clinit>():void");
        }
    }

    public class CloseTask extends TimerTask {
        private CloseTask() {
        }

        public void run() {
            try {
                Socket socket = ReadingThread.this.mWebSocket.getSocket();
                if (socket != null) {
                    socket.close();
                }
            } catch (Throwable unused) {
            }
        }

        public /* synthetic */ CloseTask(ReadingThread readingThread, AnonymousClass1 r2) {
            this();
        }
    }

    public ReadingThread(WebSocket webSocket) {
        super("ReadingThread", webSocket, ThreadType.READING_THREAD);
        this.mPMCE = webSocket.getPerMessageCompressionExtension();
    }

    private void callOnBinaryFrame(WebSocketFrame webSocketFrame) {
        this.mWebSocket.getListenerManager().callOnBinaryFrame(webSocketFrame);
    }

    private void callOnBinaryMessage(byte[] bArr) {
        this.mWebSocket.getListenerManager().callOnBinaryMessage(bArr);
    }

    private void callOnCloseFrame(WebSocketFrame webSocketFrame) {
        this.mWebSocket.getListenerManager().callOnCloseFrame(webSocketFrame);
    }

    private void callOnContinuationFrame(WebSocketFrame webSocketFrame) {
        this.mWebSocket.getListenerManager().callOnContinuationFrame(webSocketFrame);
    }

    private void callOnError(WebSocketException webSocketException) {
        this.mWebSocket.getListenerManager().callOnError(webSocketException);
    }

    private void callOnFrame(WebSocketFrame webSocketFrame) {
        this.mWebSocket.getListenerManager().callOnFrame(webSocketFrame);
    }

    private void callOnFrameError(WebSocketException webSocketException, WebSocketFrame webSocketFrame) {
        this.mWebSocket.getListenerManager().callOnFrameError(webSocketException, webSocketFrame);
    }

    private void callOnMessageDecompressionError(WebSocketException webSocketException, byte[] bArr) {
        this.mWebSocket.getListenerManager().callOnMessageDecompressionError(webSocketException, bArr);
    }

    private void callOnMessageError(WebSocketException webSocketException, List<WebSocketFrame> list) {
        this.mWebSocket.getListenerManager().callOnMessageError(webSocketException, list);
    }

    private void callOnPingFrame(WebSocketFrame webSocketFrame) {
        this.mWebSocket.getListenerManager().callOnPingFrame(webSocketFrame);
    }

    private void callOnPongFrame(WebSocketFrame webSocketFrame) {
        this.mWebSocket.getListenerManager().callOnPongFrame(webSocketFrame);
    }

    private void callOnTextFrame(WebSocketFrame webSocketFrame) {
        this.mWebSocket.getListenerManager().callOnTextFrame(webSocketFrame);
    }

    private void callOnTextMessage(byte[] bArr) {
        if (this.mWebSocket.isDirectTextMessage()) {
            this.mWebSocket.getListenerManager().callOnTextMessage(bArr);
            return;
        }
        try {
            callOnTextMessage(Misc.toStringUTF8(bArr));
        } catch (Throwable th) {
            WebSocketError webSocketError = WebSocketError.TEXT_MESSAGE_CONSTRUCTION_ERROR;
            WebSocketException webSocketException = new WebSocketException(webSocketError, "Failed to convert payload data into a string: " + th.getMessage(), th);
            callOnError(webSocketException);
            callOnTextMessageError(webSocketException, bArr);
        }
    }

    private void callOnTextMessageError(WebSocketException webSocketException, byte[] bArr) {
        this.mWebSocket.getListenerManager().callOnTextMessageError(webSocketException, bArr);
    }

    private void cancelClose() {
        synchronized (this.mCloseLock) {
            cancelCloseTask();
        }
    }

    private void cancelCloseTask() {
        Timer timer = this.mCloseTimer;
        if (timer != null) {
            timer.cancel();
            this.mCloseTimer = null;
        }
        CloseTask closeTask = this.mCloseTask;
        if (closeTask != null) {
            closeTask.cancel();
            this.mCloseTask = null;
        }
    }

    private byte[] concatenatePayloads(List<WebSocketFrame> list) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            for (WebSocketFrame payload : list) {
                byte[] payload2 = payload.getPayload();
                if (payload2 != null) {
                    if (payload2.length != 0) {
                        byteArrayOutputStream.write(payload2);
                    }
                }
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException | OutOfMemoryError e3) {
            WebSocketError webSocketError = WebSocketError.MESSAGE_CONSTRUCTION_ERROR;
            WebSocketException webSocketException = new WebSocketException(webSocketError, "Failed to concatenate payloads of multiple frames to construct a message: " + e3.getMessage(), e3);
            callOnError(webSocketException);
            callOnMessageError(webSocketException, list);
            this.mWebSocket.sendFrame(WebSocketFrame.createCloseFrame(1009, webSocketException.getMessage()));
            return null;
        }
    }

    private WebSocketFrame createCloseFrame(WebSocketException webSocketException) {
        int i3 = 1002;
        switch (AnonymousClass1.$SwitchMap$com$neovisionaries$ws$client$WebSocketError[webSocketException.getError().ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
                break;
            case 4:
            case 5:
                i3 = 1009;
                break;
            default:
                i3 = 1008;
                break;
        }
        return WebSocketFrame.createCloseFrame(i3, webSocketException.getMessage());
    }

    private byte[] decompress(byte[] bArr) {
        try {
            return this.mPMCE.decompress(bArr);
        } catch (WebSocketException e3) {
            callOnError(e3);
            callOnMessageDecompressionError(e3, bArr);
            this.mWebSocket.sendFrame(WebSocketFrame.createCloseFrame(1003, e3.getMessage()));
            return null;
        }
    }

    private byte[] getMessage(List<WebSocketFrame> list) {
        byte[] concatenatePayloads = concatenatePayloads(this.mContinuation);
        if (concatenatePayloads == null) {
            return null;
        }
        return (this.mPMCE == null || !list.get(0).getRsv1()) ? concatenatePayloads : decompress(concatenatePayloads);
    }

    private boolean handleBinaryFrame(WebSocketFrame webSocketFrame) {
        callOnBinaryFrame(webSocketFrame);
        if (!webSocketFrame.getFin()) {
            this.mContinuation.add(webSocketFrame);
            return true;
        }
        callOnBinaryMessage(getMessage(webSocketFrame));
        return true;
    }

    private boolean handleCloseFrame(WebSocketFrame webSocketFrame) {
        WebSocketState webSocketState;
        boolean z2;
        StateManager stateManager = this.mWebSocket.getStateManager();
        this.mCloseFrame = webSocketFrame;
        synchronized (stateManager) {
            try {
                WebSocketState state = stateManager.getState();
                webSocketState = WebSocketState.CLOSING;
                if (state == webSocketState || state == WebSocketState.CLOSED) {
                    z2 = false;
                } else {
                    stateManager.changeToClosing(StateManager.CloseInitiator.SERVER);
                    this.mWebSocket.sendFrame(webSocketFrame);
                    z2 = true;
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        if (z2) {
            this.mWebSocket.getListenerManager().callOnStateChanged(webSocketState);
        }
        callOnCloseFrame(webSocketFrame);
        return false;
    }

    private boolean handleContinuationFrame(WebSocketFrame webSocketFrame) {
        callOnContinuationFrame(webSocketFrame);
        this.mContinuation.add(webSocketFrame);
        if (!webSocketFrame.getFin()) {
            return true;
        }
        byte[] message = getMessage(this.mContinuation);
        if (message == null) {
            return false;
        }
        if (this.mContinuation.get(0).isTextFrame()) {
            callOnTextMessage(message);
        } else {
            callOnBinaryMessage(message);
        }
        this.mContinuation.clear();
        return true;
    }

    private boolean handleFrame(WebSocketFrame webSocketFrame) {
        callOnFrame(webSocketFrame);
        int opcode = webSocketFrame.getOpcode();
        if (opcode == 0) {
            return handleContinuationFrame(webSocketFrame);
        }
        if (opcode == 1) {
            return handleTextFrame(webSocketFrame);
        }
        if (opcode == 2) {
            return handleBinaryFrame(webSocketFrame);
        }
        switch (opcode) {
            case 8:
                return handleCloseFrame(webSocketFrame);
            case 9:
                return handlePingFrame(webSocketFrame);
            case 10:
                return handlePongFrame(webSocketFrame);
            default:
                return true;
        }
    }

    private boolean handlePingFrame(WebSocketFrame webSocketFrame) {
        callOnPingFrame(webSocketFrame);
        this.mWebSocket.sendFrame(WebSocketFrame.createPongFrame(webSocketFrame.getPayload()));
        return true;
    }

    private boolean handlePongFrame(WebSocketFrame webSocketFrame) {
        callOnPongFrame(webSocketFrame);
        return true;
    }

    private boolean handleTextFrame(WebSocketFrame webSocketFrame) {
        callOnTextFrame(webSocketFrame);
        if (!webSocketFrame.getFin()) {
            this.mContinuation.add(webSocketFrame);
            return true;
        }
        callOnTextMessage(getMessage(webSocketFrame));
        return true;
    }

    private void main() {
        this.mWebSocket.onReadingThreadStarted();
        while (true) {
            synchronized (this) {
                try {
                    if (!this.mStopRequested) {
                        WebSocketFrame readFrame = readFrame();
                        if (readFrame != null) {
                            if (!handleFrame(readFrame)) {
                                break;
                            }
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            }
        }
        waitForCloseFrame();
        cancelClose();
    }

    private void notifyFinished() {
        this.mWebSocket.onReadingThreadFinished(this.mCloseFrame);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0069, code lost:
        if (r7.mWebSocket.isMissingCloseFrameAllowed() != false) goto L_0x0072;
     */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0028 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0029  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0041 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0060  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.neovisionaries.ws.client.WebSocketFrame readFrame() {
        /*
            r7 = this;
            r0 = 0
            com.neovisionaries.ws.client.WebSocket r1 = r7.mWebSocket     // Catch:{ InterruptedIOException -> 0x001b, IOException -> 0x0018, WebSocketException -> 0x0015 }
            com.neovisionaries.ws.client.WebSocketInputStream r1 = r1.getInput()     // Catch:{ InterruptedIOException -> 0x001b, IOException -> 0x0018, WebSocketException -> 0x0015 }
            com.neovisionaries.ws.client.WebSocketFrame r1 = r1.readFrame()     // Catch:{ InterruptedIOException -> 0x001b, IOException -> 0x0018, WebSocketException -> 0x0015 }
            r7.verifyFrame(r1)     // Catch:{ InterruptedIOException -> 0x0013, IOException -> 0x0011, WebSocketException -> 0x000f }
            return r1
        L_0x000f:
            r2 = move-exception
            goto L_0x005c
        L_0x0011:
            r2 = move-exception
            goto L_0x001e
        L_0x0013:
            r2 = move-exception
            goto L_0x003d
        L_0x0015:
            r2 = move-exception
            r1 = r0
            goto L_0x005c
        L_0x0018:
            r2 = move-exception
            r1 = r0
            goto L_0x001e
        L_0x001b:
            r2 = move-exception
            r1 = r0
            goto L_0x003d
        L_0x001e:
            boolean r3 = r7.mStopRequested
            if (r3 == 0) goto L_0x0029
            boolean r3 = r7.isInterrupted()
            if (r3 == 0) goto L_0x0029
            return r0
        L_0x0029:
            com.neovisionaries.ws.client.WebSocketException r3 = new com.neovisionaries.ws.client.WebSocketException
            com.neovisionaries.ws.client.WebSocketError r4 = com.neovisionaries.ws.client.WebSocketError.IO_ERROR_IN_READING
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "An I/O error occurred while a frame was being read from the web socket: "
            r5.<init>(r6)
            java.lang.String r5 = com.appsamurai.storyly.exoplayer2.core.source.chunk.a.h(r2, r5)
            r3.<init>(r4, r5, r2)
        L_0x003b:
            r2 = r3
            goto L_0x005c
        L_0x003d:
            boolean r3 = r7.mStopRequested
            if (r3 == 0) goto L_0x0042
            return r0
        L_0x0042:
            com.neovisionaries.ws.client.WebSocketException r3 = new com.neovisionaries.ws.client.WebSocketException
            com.neovisionaries.ws.client.WebSocketError r4 = com.neovisionaries.ws.client.WebSocketError.INTERRUPTED_IN_READING
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "Interruption occurred while a frame was being read from the web socket: "
            r5.<init>(r6)
            java.lang.String r6 = r2.getMessage()
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r3.<init>(r4, r5, r2)
            goto L_0x003b
        L_0x005c:
            boolean r3 = r2 instanceof com.neovisionaries.ws.client.NoMoreFrameException
            if (r3 == 0) goto L_0x006c
            r3 = 1
            r7.mNotWaitForCloseFrame = r3
            com.neovisionaries.ws.client.WebSocket r3 = r7.mWebSocket
            boolean r3 = r3.isMissingCloseFrameAllowed()
            if (r3 == 0) goto L_0x006c
            goto L_0x0072
        L_0x006c:
            r7.callOnError(r2)
            r7.callOnFrameError(r2, r1)
        L_0x0072:
            com.neovisionaries.ws.client.WebSocketFrame r1 = r7.createCloseFrame(r2)
            com.neovisionaries.ws.client.WebSocket r7 = r7.mWebSocket
            r7.sendFrame(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neovisionaries.ws.client.ReadingThread.readFrame():com.neovisionaries.ws.client.WebSocketFrame");
    }

    private void scheduleClose() {
        synchronized (this.mCloseLock) {
            cancelCloseTask();
            scheduleCloseTask();
        }
    }

    private void scheduleCloseTask() {
        this.mCloseTask = new CloseTask(this, (AnonymousClass1) null);
        Timer timer = new Timer("ReadingThreadCloseTimer");
        this.mCloseTimer = timer;
        timer.schedule(this.mCloseTask, this.mCloseDelay);
    }

    private void verifyFrame(WebSocketFrame webSocketFrame) throws WebSocketException {
        verifyReservedBits(webSocketFrame);
        verifyFrameOpcode(webSocketFrame);
        verifyFrameMask(webSocketFrame);
        verifyFrameFragmentation(webSocketFrame);
        verifyFrameSize(webSocketFrame);
    }

    private void verifyFrameFragmentation(WebSocketFrame webSocketFrame) throws WebSocketException {
        if (!webSocketFrame.isControlFrame()) {
            boolean z2 = this.mContinuation.size() != 0;
            if (webSocketFrame.isContinuationFrame()) {
                if (!z2) {
                    throw new WebSocketException(WebSocketError.UNEXPECTED_CONTINUATION_FRAME, "A continuation frame was detected although a continuation had not started.");
                }
            } else if (z2) {
                throw new WebSocketException(WebSocketError.CONTINUATION_NOT_CLOSED, "A non-control frame was detected although the existing continuation had not been closed.");
            }
        } else if (!webSocketFrame.getFin()) {
            throw new WebSocketException(WebSocketError.FRAGMENTED_CONTROL_FRAME, "A control frame is fragmented.");
        }
    }

    private void verifyFrameMask(WebSocketFrame webSocketFrame) throws WebSocketException {
        if (webSocketFrame.getMask()) {
            throw new WebSocketException(WebSocketError.FRAME_MASKED, "A frame from the server is masked.");
        }
    }

    private void verifyFrameOpcode(WebSocketFrame webSocketFrame) throws WebSocketException {
        int opcode = webSocketFrame.getOpcode();
        if (opcode != 0 && opcode != 1 && opcode != 2) {
            switch (opcode) {
                case 8:
                case 9:
                case 10:
                    return;
                default:
                    if (!this.mWebSocket.isExtended()) {
                        WebSocketError webSocketError = WebSocketError.UNKNOWN_OPCODE;
                        throw new WebSocketException(webSocketError, "A frame has an unknown opcode: 0x" + Integer.toHexString(webSocketFrame.getOpcode()));
                    }
                    return;
            }
        }
    }

    private void verifyFrameSize(WebSocketFrame webSocketFrame) throws WebSocketException {
        byte[] payload;
        if (webSocketFrame.isControlFrame() && (payload = webSocketFrame.getPayload()) != null && 125 < payload.length) {
            WebSocketError webSocketError = WebSocketError.TOO_LONG_CONTROL_FRAME_PAYLOAD;
            throw new WebSocketException(webSocketError, "The payload size of a control frame exceeds the maximum size (125 bytes): " + payload.length);
        }
    }

    private void verifyReservedBit1(WebSocketFrame webSocketFrame) throws WebSocketException {
        if ((this.mPMCE == null || !verifyReservedBit1ForPMCE(webSocketFrame)) && webSocketFrame.getRsv1()) {
            throw new WebSocketException(WebSocketError.UNEXPECTED_RESERVED_BIT, "The RSV1 bit of a frame is set unexpectedly.");
        }
    }

    private boolean verifyReservedBit1ForPMCE(WebSocketFrame webSocketFrame) throws WebSocketException {
        return webSocketFrame.isTextFrame() || webSocketFrame.isBinaryFrame();
    }

    private void verifyReservedBit2(WebSocketFrame webSocketFrame) throws WebSocketException {
        if (webSocketFrame.getRsv2()) {
            throw new WebSocketException(WebSocketError.UNEXPECTED_RESERVED_BIT, "The RSV2 bit of a frame is set unexpectedly.");
        }
    }

    private void verifyReservedBit3(WebSocketFrame webSocketFrame) throws WebSocketException {
        if (webSocketFrame.getRsv3()) {
            throw new WebSocketException(WebSocketError.UNEXPECTED_RESERVED_BIT, "The RSV3 bit of a frame is set unexpectedly.");
        }
    }

    private void verifyReservedBits(WebSocketFrame webSocketFrame) throws WebSocketException {
        if (!this.mWebSocket.isExtended()) {
            verifyReservedBit1(webSocketFrame);
            verifyReservedBit2(webSocketFrame);
            verifyReservedBit3(webSocketFrame);
        }
    }

    private void waitForCloseFrame() {
        if (!this.mNotWaitForCloseFrame && this.mCloseFrame == null) {
            scheduleClose();
            do {
                try {
                    WebSocketFrame readFrame = this.mWebSocket.getInput().readFrame();
                    if (readFrame.isCloseFrame()) {
                        this.mCloseFrame = readFrame;
                        return;
                    }
                } catch (Throwable unused) {
                    return;
                }
            } while (!isInterrupted());
        }
    }

    public void requestStop(long j2) {
        synchronized (this) {
            try {
                if (!this.mStopRequested) {
                    this.mStopRequested = true;
                    interrupt();
                    this.mCloseDelay = j2;
                    scheduleClose();
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    public void runMain() {
        try {
            main();
        } catch (Throwable th) {
            WebSocketError webSocketError = WebSocketError.UNEXPECTED_ERROR_IN_READING_THREAD;
            WebSocketException webSocketException = new WebSocketException(webSocketError, "An uncaught throwable was detected in the reading thread: " + th.getMessage(), th);
            ListenerManager listenerManager = this.mWebSocket.getListenerManager();
            listenerManager.callOnError(webSocketException);
            listenerManager.callOnUnexpectedError(webSocketException);
        }
        notifyFinished();
    }

    private byte[] getMessage(WebSocketFrame webSocketFrame) {
        byte[] payload = webSocketFrame.getPayload();
        return (this.mPMCE == null || !webSocketFrame.getRsv1()) ? payload : decompress(payload);
    }

    private void callOnTextMessage(String str) {
        this.mWebSocket.getListenerManager().callOnTextMessage(str);
    }
}
