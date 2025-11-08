package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;
import org.apache.commons.text.StringSubstitutor;

final class AutoValue_PersistedEvent extends PersistedEvent {
    private final EventInternal event;
    private final long id;
    private final TransportContext transportContext;

    public AutoValue_PersistedEvent(long j2, TransportContext transportContext2, EventInternal eventInternal) {
        this.id = j2;
        if (transportContext2 != null) {
            this.transportContext = transportContext2;
            if (eventInternal != null) {
                this.event = eventInternal;
                return;
            }
            throw new NullPointerException("Null event");
        }
        throw new NullPointerException("Null transportContext");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PersistedEvent)) {
            return false;
        }
        PersistedEvent persistedEvent = (PersistedEvent) obj;
        return this.id == persistedEvent.getId() && this.transportContext.equals(persistedEvent.getTransportContext()) && this.event.equals(persistedEvent.getEvent());
    }

    public EventInternal getEvent() {
        return this.event;
    }

    public long getId() {
        return this.id;
    }

    public TransportContext getTransportContext() {
        return this.transportContext;
    }

    public int hashCode() {
        long j2 = this.id;
        return this.event.hashCode() ^ ((((((int) (j2 ^ (j2 >>> 32))) ^ 1000003) * 1000003) ^ this.transportContext.hashCode()) * 1000003);
    }

    public String toString() {
        return "PersistedEvent{id=" + this.id + ", transportContext=" + this.transportContext + ", event=" + this.event + StringSubstitutor.DEFAULT_VAR_END;
    }
}
