package com.google.android.datatransport.runtime;

import android.support.v4.media.session.a;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.runtime.SendRequest;
import org.apache.commons.text.StringSubstitutor;

final class AutoValue_SendRequest extends SendRequest {
    private final Encoding encoding;
    private final Event<?> event;
    private final Transformer<?, byte[]> transformer;
    private final TransportContext transportContext;
    private final String transportName;

    public static final class Builder extends SendRequest.Builder {
        private Encoding encoding;
        private Event<?> event;
        private Transformer<?, byte[]> transformer;
        private TransportContext transportContext;
        private String transportName;

        public SendRequest build() {
            String str = this.transportContext == null ? " transportContext" : "";
            if (this.transportName == null) {
                str = a.m(str, " transportName");
            }
            if (this.event == null) {
                str = a.m(str, " event");
            }
            if (this.transformer == null) {
                str = a.m(str, " transformer");
            }
            if (this.encoding == null) {
                str = a.m(str, " encoding");
            }
            if (str.isEmpty()) {
                return new AutoValue_SendRequest(this.transportContext, this.transportName, this.event, this.transformer, this.encoding);
            }
            throw new IllegalStateException("Missing required properties:".concat(str));
        }

        public SendRequest.Builder setEncoding(Encoding encoding2) {
            if (encoding2 != null) {
                this.encoding = encoding2;
                return this;
            }
            throw new NullPointerException("Null encoding");
        }

        public SendRequest.Builder setEvent(Event<?> event2) {
            if (event2 != null) {
                this.event = event2;
                return this;
            }
            throw new NullPointerException("Null event");
        }

        public SendRequest.Builder setTransformer(Transformer<?, byte[]> transformer2) {
            if (transformer2 != null) {
                this.transformer = transformer2;
                return this;
            }
            throw new NullPointerException("Null transformer");
        }

        public SendRequest.Builder setTransportContext(TransportContext transportContext2) {
            if (transportContext2 != null) {
                this.transportContext = transportContext2;
                return this;
            }
            throw new NullPointerException("Null transportContext");
        }

        public SendRequest.Builder setTransportName(String str) {
            if (str != null) {
                this.transportName = str;
                return this;
            }
            throw new NullPointerException("Null transportName");
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SendRequest)) {
            return false;
        }
        SendRequest sendRequest = (SendRequest) obj;
        return this.transportContext.equals(sendRequest.getTransportContext()) && this.transportName.equals(sendRequest.getTransportName()) && this.event.equals(sendRequest.getEvent()) && this.transformer.equals(sendRequest.getTransformer()) && this.encoding.equals(sendRequest.getEncoding());
    }

    public Encoding getEncoding() {
        return this.encoding;
    }

    public Event<?> getEvent() {
        return this.event;
    }

    public Transformer<?, byte[]> getTransformer() {
        return this.transformer;
    }

    public TransportContext getTransportContext() {
        return this.transportContext;
    }

    public String getTransportName() {
        return this.transportName;
    }

    public int hashCode() {
        return this.encoding.hashCode() ^ ((((((((this.transportContext.hashCode() ^ 1000003) * 1000003) ^ this.transportName.hashCode()) * 1000003) ^ this.event.hashCode()) * 1000003) ^ this.transformer.hashCode()) * 1000003);
    }

    public String toString() {
        return "SendRequest{transportContext=" + this.transportContext + ", transportName=" + this.transportName + ", event=" + this.event + ", transformer=" + this.transformer + ", encoding=" + this.encoding + StringSubstitutor.DEFAULT_VAR_END;
    }

    private AutoValue_SendRequest(TransportContext transportContext2, String str, Event<?> event2, Transformer<?, byte[]> transformer2, Encoding encoding2) {
        this.transportContext = transportContext2;
        this.transportName = str;
        this.event = event2;
        this.transformer = transformer2;
        this.encoding = encoding2;
    }
}
