package com.google.crypto.tink.internal;

import com.google.crypto.tink.monitoring.MonitoringClient;
import com.google.crypto.tink.monitoring.MonitoringKeysetInfo;
import java.util.concurrent.atomic.AtomicReference;

public final class MutableMonitoringRegistry {
    private static final DoNothingClient DO_NOTHING_CLIENT = new DoNothingClient();
    private static final MutableMonitoringRegistry GLOBAL_INSTANCE = new MutableMonitoringRegistry();
    private final AtomicReference<MonitoringClient> monitoringClient = new AtomicReference<>();

    public static class DoNothingClient implements MonitoringClient {
        private DoNothingClient() {
        }

        public MonitoringClient.Logger createLogger(MonitoringKeysetInfo monitoringKeysetInfo, String str, String str2) {
            return MonitoringUtil.DO_NOTHING_LOGGER;
        }
    }

    public static MutableMonitoringRegistry globalInstance() {
        return GLOBAL_INSTANCE;
    }

    public synchronized void clear() {
        this.monitoringClient.set((Object) null);
    }

    public MonitoringClient getMonitoringClient() {
        MonitoringClient monitoringClient2 = this.monitoringClient.get();
        return monitoringClient2 == null ? DO_NOTHING_CLIENT : monitoringClient2;
    }

    public synchronized void registerMonitoringClient(MonitoringClient monitoringClient2) {
        if (this.monitoringClient.get() == null) {
            this.monitoringClient.set(monitoringClient2);
        } else {
            throw new IllegalStateException("a monitoring client has already been registered");
        }
    }
}
