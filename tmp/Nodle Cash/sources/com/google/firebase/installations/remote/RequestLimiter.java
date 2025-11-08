package com.google.firebase.installations.remote;

import androidx.annotation.GuardedBy;
import com.google.firebase.installations.Utils;
import java.util.concurrent.TimeUnit;

class RequestLimiter {
    private static final long MAXIMUM_BACKOFF_DURATION_FOR_CONFIGURATION_ERRORS = TimeUnit.HOURS.toMillis(24);
    private static final long MAXIMUM_BACKOFF_DURATION_FOR_SERVER_ERRORS = TimeUnit.MINUTES.toMillis(30);
    @GuardedBy("this")
    private int attemptCount;
    @GuardedBy("this")
    private long nextRequestTime;
    private final Utils utils;

    public RequestLimiter(Utils utils2) {
        this.utils = utils2;
    }

    private synchronized long getBackoffDuration(int i3) {
        if (!isRetryableError(i3)) {
            return MAXIMUM_BACKOFF_DURATION_FOR_CONFIGURATION_ERRORS;
        }
        return (long) Math.min(Math.pow(2.0d, (double) this.attemptCount) + ((double) this.utils.getRandomDelayForSyncPrevention()), (double) MAXIMUM_BACKOFF_DURATION_FOR_SERVER_ERRORS);
    }

    private static boolean isRetryableError(int i3) {
        return i3 == 429 || (i3 >= 500 && i3 < 600);
    }

    private static boolean isSuccessfulOrRequiresNewFidCreation(int i3) {
        return (i3 >= 200 && i3 < 300) || i3 == 401 || i3 == 404;
    }

    private synchronized void resetBackoffStrategy() {
        this.attemptCount = 0;
    }

    public synchronized boolean isRequestAllowed() {
        return this.attemptCount == 0 || this.utils.currentTimeInMillis() > this.nextRequestTime;
    }

    public synchronized void setNextRequestTime(int i3) {
        if (isSuccessfulOrRequiresNewFidCreation(i3)) {
            resetBackoffStrategy();
            return;
        }
        this.attemptCount++;
        this.nextRequestTime = this.utils.currentTimeInMillis() + getBackoffDuration(i3);
    }

    public RequestLimiter() {
        this.utils = Utils.getInstance();
    }
}
