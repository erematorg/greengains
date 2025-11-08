package com.kenai.jffi;

import java.util.ArrayList;
import java.util.Arrays;

public final class FaultException extends RuntimeException {
    private final int signal;

    public FaultException(int i3, long[] jArr, long[] jArr2, long[] jArr3) {
        super(String.format("Received signal %d", new Object[]{Integer.valueOf(i3)}));
        setStackTrace(createStackTrace(jArr, jArr2, jArr3, fillInStackTrace().getStackTrace()));
        this.signal = i3;
    }

    private static StackTraceElement[] createStackTrace(long[] jArr, long[] jArr2, long[] jArr3, StackTraceElement[] stackTraceElementArr) {
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < jArr.length; i3++) {
            arrayList.add(new StackTraceElement("native", new String(Foreign.getZeroTerminatedByteArray(jArr2[i3])), new String(Foreign.getZeroTerminatedByteArray(jArr3[i3])), -1));
        }
        arrayList.addAll(Arrays.asList(stackTraceElementArr));
        return (StackTraceElement[]) arrayList.toArray(new StackTraceElement[arrayList.size()]);
    }

    public int getSignal() {
        return this.signal;
    }
}
