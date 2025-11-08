package com.google.firebase.crashlytics.internal.stacktrace;

import java.util.HashMap;

public class RemoveRepeatsStrategy implements StackTraceTrimmingStrategy {
    private final int maxRepetitions;

    public RemoveRepeatsStrategy() {
        this(1);
    }

    private static boolean isRepeatingSequence(StackTraceElement[] stackTraceElementArr, int i3, int i4) {
        int i5 = i4 - i3;
        if (i4 + i5 > stackTraceElementArr.length) {
            return false;
        }
        for (int i6 = 0; i6 < i5; i6++) {
            if (!stackTraceElementArr[i3 + i6].equals(stackTraceElementArr[i4 + i6])) {
                return false;
            }
        }
        return true;
    }

    private static StackTraceElement[] trimRepeats(StackTraceElement[] stackTraceElementArr, int i3) {
        int i4;
        HashMap hashMap = new HashMap();
        StackTraceElement[] stackTraceElementArr2 = new StackTraceElement[stackTraceElementArr.length];
        int i5 = 0;
        int i6 = 0;
        int i7 = 1;
        while (i5 < stackTraceElementArr.length) {
            StackTraceElement stackTraceElement = stackTraceElementArr[i5];
            Integer num = (Integer) hashMap.get(stackTraceElement);
            if (num == null || !isRepeatingSequence(stackTraceElementArr, num.intValue(), i5)) {
                stackTraceElementArr2[i6] = stackTraceElementArr[i5];
                i6++;
                i7 = 1;
                i4 = i5;
            } else {
                int intValue = i5 - num.intValue();
                if (i7 < i3) {
                    System.arraycopy(stackTraceElementArr, i5, stackTraceElementArr2, i6, intValue);
                    i6 += intValue;
                    i7++;
                }
                i4 = (intValue - 1) + i5;
            }
            hashMap.put(stackTraceElement, Integer.valueOf(i5));
            i5 = i4 + 1;
        }
        StackTraceElement[] stackTraceElementArr3 = new StackTraceElement[i6];
        System.arraycopy(stackTraceElementArr2, 0, stackTraceElementArr3, 0, i6);
        return stackTraceElementArr3;
    }

    public StackTraceElement[] getTrimmedStackTrace(StackTraceElement[] stackTraceElementArr) {
        StackTraceElement[] trimRepeats = trimRepeats(stackTraceElementArr, this.maxRepetitions);
        return trimRepeats.length < stackTraceElementArr.length ? trimRepeats : stackTraceElementArr;
    }

    public RemoveRepeatsStrategy(int i3) {
        this.maxRepetitions = i3;
    }
}
