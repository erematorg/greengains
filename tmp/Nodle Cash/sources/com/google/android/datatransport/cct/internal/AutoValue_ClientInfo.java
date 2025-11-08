package com.google.android.datatransport.cct.internal;

import androidx.annotation.Nullable;
import com.google.android.datatransport.cct.internal.ClientInfo;
import org.apache.commons.text.StringSubstitutor;

final class AutoValue_ClientInfo extends ClientInfo {
    private final AndroidClientInfo androidClientInfo;
    private final ClientInfo.ClientType clientType;

    public static final class Builder extends ClientInfo.Builder {
        private AndroidClientInfo androidClientInfo;
        private ClientInfo.ClientType clientType;

        public ClientInfo build() {
            return new AutoValue_ClientInfo(this.clientType, this.androidClientInfo);
        }

        public ClientInfo.Builder setAndroidClientInfo(@Nullable AndroidClientInfo androidClientInfo2) {
            this.androidClientInfo = androidClientInfo2;
            return this;
        }

        public ClientInfo.Builder setClientType(@Nullable ClientInfo.ClientType clientType2) {
            this.clientType = clientType2;
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ClientInfo)) {
            return false;
        }
        ClientInfo clientInfo = (ClientInfo) obj;
        ClientInfo.ClientType clientType2 = this.clientType;
        if (clientType2 != null ? clientType2.equals(clientInfo.getClientType()) : clientInfo.getClientType() == null) {
            AndroidClientInfo androidClientInfo2 = this.androidClientInfo;
            if (androidClientInfo2 == null) {
                if (clientInfo.getAndroidClientInfo() == null) {
                    return true;
                }
            } else if (androidClientInfo2.equals(clientInfo.getAndroidClientInfo())) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public AndroidClientInfo getAndroidClientInfo() {
        return this.androidClientInfo;
    }

    @Nullable
    public ClientInfo.ClientType getClientType() {
        return this.clientType;
    }

    public int hashCode() {
        ClientInfo.ClientType clientType2 = this.clientType;
        int i3 = 0;
        int hashCode = ((clientType2 == null ? 0 : clientType2.hashCode()) ^ 1000003) * 1000003;
        AndroidClientInfo androidClientInfo2 = this.androidClientInfo;
        if (androidClientInfo2 != null) {
            i3 = androidClientInfo2.hashCode();
        }
        return hashCode ^ i3;
    }

    public String toString() {
        return "ClientInfo{clientType=" + this.clientType + ", androidClientInfo=" + this.androidClientInfo + StringSubstitutor.DEFAULT_VAR_END;
    }

    private AutoValue_ClientInfo(@Nullable ClientInfo.ClientType clientType2, @Nullable AndroidClientInfo androidClientInfo2) {
        this.clientType = clientType2;
        this.androidClientInfo = androidClientInfo2;
    }
}
