package com.google.firebase.crashlytics.internal.common;

public class ResponseParser {
    public static final int ResponseActionDiscard = 0;
    public static final int ResponseActionRetry = 1;

    public static int parse(int i3) {
        if (i3 < 200 || i3 > 299) {
            return ((i3 < 300 || i3 > 399) && i3 >= 400 && i3 <= 499) ? 0 : 1;
        }
        return 0;
    }
}
