package com.reown.android.internal.utils;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.JvmName;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\t\n\u0002\b\u000f\"\u0011\u0010\u0000\u001a\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0003\"\u0011\u0010\u0004\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0003\"\u0011\u0010\u0006\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0003\"\u0011\u0010\b\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0003\"\u0011\u0010\n\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0003\"\u0011\u0010\f\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0003\"\u0011\u0010\u000e\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0003¨\u0006\u0010"}, d2 = {"currentTimeInSeconds", "", "getCurrentTimeInSeconds", "()J", "thirtySeconds", "getThirtySeconds", "fiveMinutesInSeconds", "getFiveMinutesInSeconds", "oneHourInSeconds", "getOneHourInSeconds", "dayInSeconds", "getDayInSeconds", "weekInSeconds", "getWeekInSeconds", "monthInSeconds", "getMonthInSeconds", "android_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
@JvmName(name = "Time")
public final class Time {
    private static final long dayInSeconds;
    private static final long fiveMinutesInSeconds;
    private static final long monthInSeconds;
    private static final long oneHourInSeconds;
    private static final long thirtySeconds;
    private static final long weekInSeconds;

    static {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        thirtySeconds = timeUnit.convert(30, timeUnit);
        fiveMinutesInSeconds = timeUnit.convert(5, TimeUnit.MINUTES);
        oneHourInSeconds = timeUnit.convert(1, TimeUnit.HOURS);
        TimeUnit timeUnit2 = TimeUnit.DAYS;
        dayInSeconds = timeUnit.convert(1, timeUnit2);
        weekInSeconds = timeUnit.convert(7, timeUnit2);
        monthInSeconds = timeUnit.convert(30, timeUnit2);
    }

    public static final long getCurrentTimeInSeconds() {
        return System.currentTimeMillis() / ((long) 1000);
    }

    public static final long getDayInSeconds() {
        return dayInSeconds;
    }

    public static final long getFiveMinutesInSeconds() {
        return fiveMinutesInSeconds;
    }

    public static final long getMonthInSeconds() {
        return monthInSeconds;
    }

    public static final long getOneHourInSeconds() {
        return oneHourInSeconds;
    }

    public static final long getThirtySeconds() {
        return thirtySeconds;
    }

    public static final long getWeekInSeconds() {
        return weekInSeconds;
    }
}
