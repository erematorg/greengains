package com.google.firebase.crashlytics.internal.stacktrace;

public class MiddleOutStrategy implements StackTraceTrimmingStrategy {
    private final int trimmedSize;

    public MiddleOutStrategy(int i3) {
        this.trimmedSize = i3;
    }

    public StackTraceElement[] getTrimmedStackTrace(StackTraceElement[] stackTraceElementArr) {
        int length = stackTraceElementArr.length;
        int i3 = this.trimmedSize;
        if (length <= i3) {
            return stackTraceElementArr;
        }
        int i4 = i3 / 2;
        int i5 = i3 - i4;
        StackTraceElement[] stackTraceElementArr2 = new StackTraceElement[i3];
        System.arraycopy(stackTraceElementArr, 0, stackTraceElementArr2, 0, i5);
        System.arraycopy(stackTraceElementArr, stackTraceElementArr.length - i4, stackTraceElementArr2, i5, i4);
        return stackTraceElementArr2;
    }
}
