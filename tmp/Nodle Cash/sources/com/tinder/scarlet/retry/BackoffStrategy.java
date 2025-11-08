package com.tinder.scarlet.retry;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&R\u0018\u0010\u0002\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\f"}, d2 = {"Lcom/tinder/scarlet/retry/BackoffStrategy;", "", "shouldBackoff", "", "getShouldBackoff", "()Z", "setShouldBackoff", "(Z)V", "backoffDurationMillisAt", "", "retryCount", "", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface BackoffStrategy {
    long backoffDurationMillisAt(int i3);

    boolean getShouldBackoff();

    void setShouldBackoff(boolean z2);
}
