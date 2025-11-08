package com.google.android.datatransport.runtime;

import android.annotation.SuppressLint;
import androidx.annotation.WorkerThread;
import com.google.android.datatransport.Priority;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.runtime.logging.Logging;

public final class ForcedSender {
    private static final String LOG_TAG = "ForcedSender";

    private ForcedSender() {
    }

    @WorkerThread
    @SuppressLint({"DiscouragedApi"})
    public static void sendBlocking(Transport<?> transport, Priority priority) {
        if (transport instanceof TransportImpl) {
            TransportRuntime.getInstance().getUploader().logAndUpdateState(((TransportImpl) transport).getTransportContext().withPriority(priority), 1);
            return;
        }
        Logging.w(LOG_TAG, "Expected instance of `TransportImpl`, got `%s`.", transport);
    }
}
