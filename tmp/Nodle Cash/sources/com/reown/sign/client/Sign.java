package com.reown.sign.client;

import A.a;
import androidx.annotation.Keep;
import androidx.camera.camera2.internal.C0118y;
import androidx.constraintlayout.core.state.b;
import androidx.core.app.NotificationCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import com.reown.android.Core;
import com.reown.android.CoreInterface;
import com.reown.android.cacao.SignatureInterface;
import com.reown.android.internal.common.signing.cacao.Issuer;
import com.reown.android.push.notifications.PushMessagingService;
import java.net.URI;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Duration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\b"}, d2 = {"Lcom/reown/sign/client/Sign;", "", "<init>", "()V", "Listeners", "ConnectionType", "Model", "Params", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Sign {
    @NotNull
    public static final Sign INSTANCE = new Sign();

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/reown/sign/client/Sign$ConnectionType;", "", "<init>", "(Ljava/lang/String;I)V", "AUTOMATIC", "MANUAL", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @Deprecated(message = "ConnectionType for the relay is moved to CoreClient", replaceWith = @ReplaceWith(expression = "ConnectionType", imports = {"com.reown.android.relay"}))
    public enum ConnectionType {
        AUTOMATIC,
        MANUAL;

        static {
            ConnectionType[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries((E[]) $values);
        }

        @NotNull
        public static EnumEntries<ConnectionType> getEntries() {
            return $ENTRIES;
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0001\u0002\u0001\u0001\u0003¨\u0006\u0004À\u0006\u0003"}, d2 = {"Lcom/reown/sign/client/Sign$Listeners;", "", "SessionPing", "Lcom/reown/sign/client/Sign$Listeners$SessionPing;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Listeners {

        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\tÀ\u0006\u0003"}, d2 = {"Lcom/reown/sign/client/Sign$Listeners$SessionPing;", "Lcom/reown/sign/client/Sign$Listeners;", "onSuccess", "", "pingSuccess", "Lcom/reown/sign/client/Sign$Model$Ping$Success;", "onError", "pingError", "Lcom/reown/sign/client/Sign$Model$Ping$Error;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public interface SessionPing extends Listeners {
            void onError(@NotNull Model.Ping.Error error);

            void onSuccess(@NotNull Model.Ping.Success success);
        }
    }

    @Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u001f\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\"#$%&'()*+,-./0123456789:;<=>?@ABCD¨\u0006E"}, d2 = {"Lcom/reown/sign/client/Sign$Model;", "", "<init>", "()V", "Error", "ProposedSequence", "SessionProposal", "ExpiredProposal", "ExpiredRequest", "VerifyContext", "Validation", "SessionRequest", "SentRequest", "Namespace", "RelayProtocolOptions", "Pairing", "SettledSessionResponse", "SessionUpdateResponse", "SessionAuthenticateResponse", "DeletedSession", "Ping", "RejectedSession", "UpdatedSession", "ApprovedSession", "Session", "SessionEvent", "Event", "SessionRequestResponse", "JsonRpcResponse", "PendingRequest", "ConnectionState", "Message", "SessionAuthenticate", "PayloadParams", "Cacao", "Lcom/reown/sign/client/Sign$Model$ApprovedSession;", "Lcom/reown/sign/client/Sign$Model$Cacao;", "Lcom/reown/sign/client/Sign$Model$Cacao$Header;", "Lcom/reown/sign/client/Sign$Model$Cacao$Payload;", "Lcom/reown/sign/client/Sign$Model$Cacao$Signature;", "Lcom/reown/sign/client/Sign$Model$ConnectionState;", "Lcom/reown/sign/client/Sign$Model$ConnectionState$Reason;", "Lcom/reown/sign/client/Sign$Model$DeletedSession;", "Lcom/reown/sign/client/Sign$Model$Error;", "Lcom/reown/sign/client/Sign$Model$Event;", "Lcom/reown/sign/client/Sign$Model$ExpiredProposal;", "Lcom/reown/sign/client/Sign$Model$ExpiredRequest;", "Lcom/reown/sign/client/Sign$Model$JsonRpcResponse;", "Lcom/reown/sign/client/Sign$Model$Message;", "Lcom/reown/sign/client/Sign$Model$Namespace;", "Lcom/reown/sign/client/Sign$Model$Pairing;", "Lcom/reown/sign/client/Sign$Model$PayloadParams;", "Lcom/reown/sign/client/Sign$Model$PendingRequest;", "Lcom/reown/sign/client/Sign$Model$Ping;", "Lcom/reown/sign/client/Sign$Model$ProposedSequence;", "Lcom/reown/sign/client/Sign$Model$RejectedSession;", "Lcom/reown/sign/client/Sign$Model$RelayProtocolOptions;", "Lcom/reown/sign/client/Sign$Model$Session;", "Lcom/reown/sign/client/Sign$Model$SessionAuthenticate$Participant;", "Lcom/reown/sign/client/Sign$Model$SessionAuthenticateResponse;", "Lcom/reown/sign/client/Sign$Model$SessionEvent;", "Lcom/reown/sign/client/Sign$Model$SessionProposal;", "Lcom/reown/sign/client/Sign$Model$SessionRequest;", "Lcom/reown/sign/client/Sign$Model$SessionRequest$JSONRPCRequest;", "Lcom/reown/sign/client/Sign$Model$SessionRequestResponse;", "Lcom/reown/sign/client/Sign$Model$SessionUpdateResponse;", "Lcom/reown/sign/client/Sign$Model$SettledSessionResponse;", "Lcom/reown/sign/client/Sign$Model$UpdatedSession;", "Lcom/reown/sign/client/Sign$Model$VerifyContext;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class Model {

        @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0015\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\nHÆ\u0003JE\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u00072\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\nHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dHÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006!"}, d2 = {"Lcom/reown/sign/client/Sign$Model$ApprovedSession;", "Lcom/reown/sign/client/Sign$Model;", "topic", "", "metaData", "Lcom/reown/android/Core$Model$AppMetaData;", "namespaces", "", "Lcom/reown/sign/client/Sign$Model$Namespace$Session;", "accounts", "", "<init>", "(Ljava/lang/String;Lcom/reown/android/Core$Model$AppMetaData;Ljava/util/Map;Ljava/util/List;)V", "getTopic", "()Ljava/lang/String;", "getMetaData", "()Lcom/reown/android/Core$Model$AppMetaData;", "getNamespaces", "()Ljava/util/Map;", "getAccounts", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class ApprovedSession extends Model {
            @NotNull
            private final List<String> accounts;
            @Nullable
            private final Core.Model.AppMetaData metaData;
            @NotNull
            private final Map<String, Namespace.Session> namespaces;
            @NotNull
            private final String topic;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public ApprovedSession(@NotNull String str, @Nullable Core.Model.AppMetaData appMetaData, @NotNull Map<String, Namespace.Session> map, @NotNull List<String> list) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                Intrinsics.checkNotNullParameter(map, "namespaces");
                Intrinsics.checkNotNullParameter(list, "accounts");
                this.topic = str;
                this.metaData = appMetaData;
                this.namespaces = map;
                this.accounts = list;
            }

            public static /* synthetic */ ApprovedSession copy$default(ApprovedSession approvedSession, String str, Core.Model.AppMetaData appMetaData, Map<String, Namespace.Session> map, List<String> list, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = approvedSession.topic;
                }
                if ((i3 & 2) != 0) {
                    appMetaData = approvedSession.metaData;
                }
                if ((i3 & 4) != 0) {
                    map = approvedSession.namespaces;
                }
                if ((i3 & 8) != 0) {
                    list = approvedSession.accounts;
                }
                return approvedSession.copy(str, appMetaData, map, list);
            }

            @NotNull
            public final String component1() {
                return this.topic;
            }

            @Nullable
            public final Core.Model.AppMetaData component2() {
                return this.metaData;
            }

            @NotNull
            public final Map<String, Namespace.Session> component3() {
                return this.namespaces;
            }

            @NotNull
            public final List<String> component4() {
                return this.accounts;
            }

            @NotNull
            public final ApprovedSession copy(@NotNull String str, @Nullable Core.Model.AppMetaData appMetaData, @NotNull Map<String, Namespace.Session> map, @NotNull List<String> list) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                Intrinsics.checkNotNullParameter(map, "namespaces");
                Intrinsics.checkNotNullParameter(list, "accounts");
                return new ApprovedSession(str, appMetaData, map, list);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof ApprovedSession)) {
                    return false;
                }
                ApprovedSession approvedSession = (ApprovedSession) obj;
                return Intrinsics.areEqual((Object) this.topic, (Object) approvedSession.topic) && Intrinsics.areEqual((Object) this.metaData, (Object) approvedSession.metaData) && Intrinsics.areEqual((Object) this.namespaces, (Object) approvedSession.namespaces) && Intrinsics.areEqual((Object) this.accounts, (Object) approvedSession.accounts);
            }

            @NotNull
            public final List<String> getAccounts() {
                return this.accounts;
            }

            @Nullable
            public final Core.Model.AppMetaData getMetaData() {
                return this.metaData;
            }

            @NotNull
            public final Map<String, Namespace.Session> getNamespaces() {
                return this.namespaces;
            }

            @NotNull
            public final String getTopic() {
                return this.topic;
            }

            public int hashCode() {
                int hashCode = this.topic.hashCode() * 31;
                Core.Model.AppMetaData appMetaData = this.metaData;
                return this.accounts.hashCode() + b.d(this.namespaces, (hashCode + (appMetaData == null ? 0 : appMetaData.hashCode())) * 31, 31);
            }

            @NotNull
            public String toString() {
                String str = this.topic;
                Core.Model.AppMetaData appMetaData = this.metaData;
                Map<String, Namespace.Session> map = this.namespaces;
                List<String> list = this.accounts;
                return "ApprovedSession(topic=" + str + ", metaData=" + appMetaData + ", namespaces=" + map + ", accounts=" + list + ")";
            }
        }

        @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001:\u0003\u001c\u001d\u001eB\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001f"}, d2 = {"Lcom/reown/sign/client/Sign$Model$Cacao;", "Lcom/reown/sign/client/Sign$Model;", "header", "Lcom/reown/sign/client/Sign$Model$Cacao$Header;", "payload", "Lcom/reown/sign/client/Sign$Model$Cacao$Payload;", "signature", "Lcom/reown/sign/client/Sign$Model$Cacao$Signature;", "<init>", "(Lcom/reown/sign/client/Sign$Model$Cacao$Header;Lcom/reown/sign/client/Sign$Model$Cacao$Payload;Lcom/reown/sign/client/Sign$Model$Cacao$Signature;)V", "getHeader", "()Lcom/reown/sign/client/Sign$Model$Cacao$Header;", "getPayload", "()Lcom/reown/sign/client/Sign$Model$Cacao$Payload;", "getSignature", "()Lcom/reown/sign/client/Sign$Model$Cacao$Signature;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "Signature", "Header", "Payload", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Cacao extends Model {
            @NotNull
            private final Header header;
            @NotNull
            private final Payload payload;
            @NotNull
            private final Signature signature;

            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/sign/client/Sign$Model$Cacao$Header;", "Lcom/reown/sign/client/Sign$Model;", "t", "", "<init>", "(Ljava/lang/String;)V", "getT", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Header extends Model {
                @NotNull

                /* renamed from: t  reason: collision with root package name */
                private final String f7459t;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Header(@NotNull String str) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, "t");
                    this.f7459t = str;
                }

                public static /* synthetic */ Header copy$default(Header header, String str, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        str = header.f7459t;
                    }
                    return header.copy(str);
                }

                @NotNull
                public final String component1() {
                    return this.f7459t;
                }

                @NotNull
                public final Header copy(@NotNull String str) {
                    Intrinsics.checkNotNullParameter(str, "t");
                    return new Header(str);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return (obj instanceof Header) && Intrinsics.areEqual((Object) this.f7459t, (Object) ((Header) obj).f7459t);
                }

                @NotNull
                public final String getT() {
                    return this.f7459t;
                }

                public int hashCode() {
                    return this.f7459t.hashCode();
                }

                @NotNull
                public String toString() {
                    return a.l("Header(t=", this.f7459t, ")");
                }
            }

            @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010 \n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001Bo\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010*\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000eHÆ\u0003J\u0001\u0010+\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000eHÆ\u0001J\u0013\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010/HÖ\u0003J\t\u00100\u001a\u000201HÖ\u0001J\t\u00102\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0012R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0012R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0012R\u0019\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u001e\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0012¨\u00063"}, d2 = {"Lcom/reown/sign/client/Sign$Model$Cacao$Payload;", "Lcom/reown/sign/client/Sign$Model;", "iss", "", "domain", "aud", "version", "nonce", "iat", "nbf", "exp", "statement", "requestId", "resources", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getIss", "()Ljava/lang/String;", "getDomain", "getAud", "getVersion", "getNonce", "getIat", "getNbf", "getExp", "getStatement", "getRequestId", "getResources", "()Ljava/util/List;", "address", "getAddress", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Payload extends Model {
                @NotNull
                private final String aud;
                @NotNull
                private final String domain;
                @Nullable
                private final String exp;
                @NotNull
                private final String iat;
                @NotNull
                private final String iss;
                @Nullable
                private final String nbf;
                @NotNull
                private final String nonce;
                @Nullable
                private final String requestId;
                @Nullable
                private final List<String> resources;
                @Nullable
                private final String statement;
                @NotNull
                private final String version;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Payload(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull String str5, @NotNull String str6, @Nullable String str7, @Nullable String str8, @Nullable String str9, @Nullable String str10, @Nullable List<String> list) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, "iss");
                    Intrinsics.checkNotNullParameter(str2, "domain");
                    Intrinsics.checkNotNullParameter(str3, "aud");
                    Intrinsics.checkNotNullParameter(str4, "version");
                    Intrinsics.checkNotNullParameter(str5, "nonce");
                    Intrinsics.checkNotNullParameter(str6, "iat");
                    this.iss = str;
                    this.domain = str2;
                    this.aud = str3;
                    this.version = str4;
                    this.nonce = str5;
                    this.iat = str6;
                    this.nbf = str7;
                    this.exp = str8;
                    this.statement = str9;
                    this.requestId = str10;
                    this.resources = list;
                }

                public static /* synthetic */ Payload copy$default(Payload payload, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, List list, int i3, Object obj) {
                    Payload payload2 = payload;
                    int i4 = i3;
                    return payload.copy((i4 & 1) != 0 ? payload2.iss : str, (i4 & 2) != 0 ? payload2.domain : str2, (i4 & 4) != 0 ? payload2.aud : str3, (i4 & 8) != 0 ? payload2.version : str4, (i4 & 16) != 0 ? payload2.nonce : str5, (i4 & 32) != 0 ? payload2.iat : str6, (i4 & 64) != 0 ? payload2.nbf : str7, (i4 & 128) != 0 ? payload2.exp : str8, (i4 & 256) != 0 ? payload2.statement : str9, (i4 & 512) != 0 ? payload2.requestId : str10, (i4 & 1024) != 0 ? payload2.resources : list);
                }

                @NotNull
                public final String component1() {
                    return this.iss;
                }

                @Nullable
                public final String component10() {
                    return this.requestId;
                }

                @Nullable
                public final List<String> component11() {
                    return this.resources;
                }

                @NotNull
                public final String component2() {
                    return this.domain;
                }

                @NotNull
                public final String component3() {
                    return this.aud;
                }

                @NotNull
                public final String component4() {
                    return this.version;
                }

                @NotNull
                public final String component5() {
                    return this.nonce;
                }

                @NotNull
                public final String component6() {
                    return this.iat;
                }

                @Nullable
                public final String component7() {
                    return this.nbf;
                }

                @Nullable
                public final String component8() {
                    return this.exp;
                }

                @Nullable
                public final String component9() {
                    return this.statement;
                }

                @NotNull
                public final Payload copy(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull String str5, @NotNull String str6, @Nullable String str7, @Nullable String str8, @Nullable String str9, @Nullable String str10, @Nullable List<String> list) {
                    Intrinsics.checkNotNullParameter(str, "iss");
                    Intrinsics.checkNotNullParameter(str2, "domain");
                    String str11 = str3;
                    Intrinsics.checkNotNullParameter(str11, "aud");
                    String str12 = str4;
                    Intrinsics.checkNotNullParameter(str12, "version");
                    String str13 = str5;
                    Intrinsics.checkNotNullParameter(str13, "nonce");
                    String str14 = str6;
                    Intrinsics.checkNotNullParameter(str14, "iat");
                    return new Payload(str, str2, str11, str12, str13, str14, str7, str8, str9, str10, list);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof Payload)) {
                        return false;
                    }
                    Payload payload = (Payload) obj;
                    return Intrinsics.areEqual((Object) this.iss, (Object) payload.iss) && Intrinsics.areEqual((Object) this.domain, (Object) payload.domain) && Intrinsics.areEqual((Object) this.aud, (Object) payload.aud) && Intrinsics.areEqual((Object) this.version, (Object) payload.version) && Intrinsics.areEqual((Object) this.nonce, (Object) payload.nonce) && Intrinsics.areEqual((Object) this.iat, (Object) payload.iat) && Intrinsics.areEqual((Object) this.nbf, (Object) payload.nbf) && Intrinsics.areEqual((Object) this.exp, (Object) payload.exp) && Intrinsics.areEqual((Object) this.statement, (Object) payload.statement) && Intrinsics.areEqual((Object) this.requestId, (Object) payload.requestId) && Intrinsics.areEqual((Object) this.resources, (Object) payload.resources);
                }

                @NotNull
                public final String getAddress() {
                    return new Issuer(this.iss).getAddress();
                }

                @NotNull
                public final String getAud() {
                    return this.aud;
                }

                @NotNull
                public final String getDomain() {
                    return this.domain;
                }

                @Nullable
                public final String getExp() {
                    return this.exp;
                }

                @NotNull
                public final String getIat() {
                    return this.iat;
                }

                @NotNull
                public final String getIss() {
                    return this.iss;
                }

                @Nullable
                public final String getNbf() {
                    return this.nbf;
                }

                @NotNull
                public final String getNonce() {
                    return this.nonce;
                }

                @Nullable
                public final String getRequestId() {
                    return this.requestId;
                }

                @Nullable
                public final List<String> getResources() {
                    return this.resources;
                }

                @Nullable
                public final String getStatement() {
                    return this.statement;
                }

                @NotNull
                public final String getVersion() {
                    return this.version;
                }

                public int hashCode() {
                    int i3 = androidx.compose.animation.core.a.i(this.iat, androidx.compose.animation.core.a.i(this.nonce, androidx.compose.animation.core.a.i(this.version, androidx.compose.animation.core.a.i(this.aud, androidx.compose.animation.core.a.i(this.domain, this.iss.hashCode() * 31, 31), 31), 31), 31), 31);
                    String str = this.nbf;
                    int i4 = 0;
                    int hashCode = (i3 + (str == null ? 0 : str.hashCode())) * 31;
                    String str2 = this.exp;
                    int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
                    String str3 = this.statement;
                    int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
                    String str4 = this.requestId;
                    int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
                    List<String> list = this.resources;
                    if (list != null) {
                        i4 = list.hashCode();
                    }
                    return hashCode4 + i4;
                }

                @NotNull
                public String toString() {
                    String str = this.iss;
                    String str2 = this.domain;
                    String str3 = this.aud;
                    String str4 = this.version;
                    String str5 = this.nonce;
                    String str6 = this.iat;
                    String str7 = this.nbf;
                    String str8 = this.exp;
                    String str9 = this.statement;
                    String str10 = this.requestId;
                    List<String> list = this.resources;
                    StringBuilder l2 = C0118y.l("Payload(iss=", str, ", domain=", str2, ", aud=");
                    b.w(l2, str3, ", version=", str4, ", nonce=");
                    b.w(l2, str5, ", iat=", str6, ", nbf=");
                    b.w(l2, str7, ", exp=", str8, ", statement=");
                    b.w(l2, str9, ", requestId=", str10, ", resources=");
                    return C0118y.h(")", list, l2);
                }
            }

            @Keep
            @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u0002B#\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0004HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0004HÆ\u0003J)\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0004HÖ\u0001R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0005\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\n¨\u0006\u0018"}, d2 = {"Lcom/reown/sign/client/Sign$Model$Cacao$Signature;", "Lcom/reown/sign/client/Sign$Model;", "Lcom/reown/android/cacao/SignatureInterface;", "t", "", "s", "m", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getT", "()Ljava/lang/String;", "getS", "getM", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Signature extends Model implements SignatureInterface {
                @Nullable

                /* renamed from: m  reason: collision with root package name */
                private final String f7460m;
                @NotNull

                /* renamed from: s  reason: collision with root package name */
                private final String f7461s;
                @NotNull

                /* renamed from: t  reason: collision with root package name */
                private final String f7462t;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Signature(@NotNull String str, @NotNull String str2, @Nullable String str3) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, "t");
                    Intrinsics.checkNotNullParameter(str2, "s");
                    this.f7462t = str;
                    this.f7461s = str2;
                    this.f7460m = str3;
                }

                public static /* synthetic */ Signature copy$default(Signature signature, String str, String str2, String str3, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        str = signature.f7462t;
                    }
                    if ((i3 & 2) != 0) {
                        str2 = signature.f7461s;
                    }
                    if ((i3 & 4) != 0) {
                        str3 = signature.f7460m;
                    }
                    return signature.copy(str, str2, str3);
                }

                @NotNull
                public final String component1() {
                    return this.f7462t;
                }

                @NotNull
                public final String component2() {
                    return this.f7461s;
                }

                @Nullable
                public final String component3() {
                    return this.f7460m;
                }

                @NotNull
                public final Signature copy(@NotNull String str, @NotNull String str2, @Nullable String str3) {
                    Intrinsics.checkNotNullParameter(str, "t");
                    Intrinsics.checkNotNullParameter(str2, "s");
                    return new Signature(str, str2, str3);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof Signature)) {
                        return false;
                    }
                    Signature signature = (Signature) obj;
                    return Intrinsics.areEqual((Object) this.f7462t, (Object) signature.f7462t) && Intrinsics.areEqual((Object) this.f7461s, (Object) signature.f7461s) && Intrinsics.areEqual((Object) this.f7460m, (Object) signature.f7460m);
                }

                @Nullable
                public String getM() {
                    return this.f7460m;
                }

                @NotNull
                public String getS() {
                    return this.f7461s;
                }

                @NotNull
                public String getT() {
                    return this.f7462t;
                }

                public int hashCode() {
                    int i3 = androidx.compose.animation.core.a.i(this.f7461s, this.f7462t.hashCode() * 31, 31);
                    String str = this.f7460m;
                    return i3 + (str == null ? 0 : str.hashCode());
                }

                @NotNull
                public String toString() {
                    String str = this.f7462t;
                    String str2 = this.f7461s;
                    return a.n(C0118y.l("Signature(t=", str, ", s=", str2, ", m="), this.f7460m, ")");
                }

                /* JADX INFO: this call moved to the top of the method (can break code semantics) */
                public /* synthetic */ Signature(String str, String str2, String str3, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                    this(str, str2, (i3 & 4) != 0 ? null : str3);
                }
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Cacao(@NotNull Header header2, @NotNull Payload payload2, @NotNull Signature signature2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(header2, "header");
                Intrinsics.checkNotNullParameter(payload2, "payload");
                Intrinsics.checkNotNullParameter(signature2, "signature");
                this.header = header2;
                this.payload = payload2;
                this.signature = signature2;
            }

            public static /* synthetic */ Cacao copy$default(Cacao cacao, Header header2, Payload payload2, Signature signature2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    header2 = cacao.header;
                }
                if ((i3 & 2) != 0) {
                    payload2 = cacao.payload;
                }
                if ((i3 & 4) != 0) {
                    signature2 = cacao.signature;
                }
                return cacao.copy(header2, payload2, signature2);
            }

            @NotNull
            public final Header component1() {
                return this.header;
            }

            @NotNull
            public final Payload component2() {
                return this.payload;
            }

            @NotNull
            public final Signature component3() {
                return this.signature;
            }

            @NotNull
            public final Cacao copy(@NotNull Header header2, @NotNull Payload payload2, @NotNull Signature signature2) {
                Intrinsics.checkNotNullParameter(header2, "header");
                Intrinsics.checkNotNullParameter(payload2, "payload");
                Intrinsics.checkNotNullParameter(signature2, "signature");
                return new Cacao(header2, payload2, signature2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Cacao)) {
                    return false;
                }
                Cacao cacao = (Cacao) obj;
                return Intrinsics.areEqual((Object) this.header, (Object) cacao.header) && Intrinsics.areEqual((Object) this.payload, (Object) cacao.payload) && Intrinsics.areEqual((Object) this.signature, (Object) cacao.signature);
            }

            @NotNull
            public final Header getHeader() {
                return this.header;
            }

            @NotNull
            public final Payload getPayload() {
                return this.payload;
            }

            @NotNull
            public final Signature getSignature() {
                return this.signature;
            }

            public int hashCode() {
                int hashCode = this.payload.hashCode();
                return this.signature.hashCode() + ((hashCode + (this.header.hashCode() * 31)) * 31);
            }

            @NotNull
            public String toString() {
                Header header2 = this.header;
                Payload payload2 = this.payload;
                Signature signature2 = this.signature;
                return "Cacao(header=" + header2 + ", payload=" + payload2 + ", signature=" + signature2 + ")";
            }
        }

        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001:\u0001\u0015B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lcom/reown/sign/client/Sign$Model$ConnectionState;", "Lcom/reown/sign/client/Sign$Model;", "isAvailable", "", "reason", "Lcom/reown/sign/client/Sign$Model$ConnectionState$Reason;", "<init>", "(ZLcom/reown/sign/client/Sign$Model$ConnectionState$Reason;)V", "()Z", "getReason", "()Lcom/reown/sign/client/Sign$Model$ConnectionState$Reason;", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "", "Reason", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class ConnectionState extends Model {
            private final boolean isAvailable;
            @Nullable
            private final Reason reason;

            @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/sign/client/Sign$Model$ConnectionState$Reason;", "Lcom/reown/sign/client/Sign$Model;", "<init>", "()V", "ConnectionClosed", "ConnectionFailed", "Lcom/reown/sign/client/Sign$Model$ConnectionState$Reason$ConnectionClosed;", "Lcom/reown/sign/client/Sign$Model$ConnectionState$Reason$ConnectionFailed;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static abstract class Reason extends Model {

                @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/sign/client/Sign$Model$ConnectionState$Reason$ConnectionClosed;", "Lcom/reown/sign/client/Sign$Model$ConnectionState$Reason;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
                public static final class ConnectionClosed extends Reason {
                    @NotNull
                    private final String message;

                    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                    public ConnectionClosed(@NotNull String str) {
                        super((DefaultConstructorMarker) null);
                        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                        this.message = str;
                    }

                    public static /* synthetic */ ConnectionClosed copy$default(ConnectionClosed connectionClosed, String str, int i3, Object obj) {
                        if ((i3 & 1) != 0) {
                            str = connectionClosed.message;
                        }
                        return connectionClosed.copy(str);
                    }

                    @NotNull
                    public final String component1() {
                        return this.message;
                    }

                    @NotNull
                    public final ConnectionClosed copy(@NotNull String str) {
                        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                        return new ConnectionClosed(str);
                    }

                    public boolean equals(@Nullable Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return (obj instanceof ConnectionClosed) && Intrinsics.areEqual((Object) this.message, (Object) ((ConnectionClosed) obj).message);
                    }

                    @NotNull
                    public final String getMessage() {
                        return this.message;
                    }

                    public int hashCode() {
                        return this.message.hashCode();
                    }

                    @NotNull
                    public String toString() {
                        return a.l("ConnectionClosed(message=", this.message, ")");
                    }
                }

                @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/reown/sign/client/Sign$Model$ConnectionState$Reason$ConnectionFailed;", "Lcom/reown/sign/client/Sign$Model$ConnectionState$Reason;", "throwable", "", "<init>", "(Ljava/lang/Throwable;)V", "getThrowable", "()Ljava/lang/Throwable;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
                public static final class ConnectionFailed extends Reason {
                    @NotNull
                    private final Throwable throwable;

                    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                    public ConnectionFailed(@NotNull Throwable th) {
                        super((DefaultConstructorMarker) null);
                        Intrinsics.checkNotNullParameter(th, "throwable");
                        this.throwable = th;
                    }

                    public static /* synthetic */ ConnectionFailed copy$default(ConnectionFailed connectionFailed, Throwable th, int i3, Object obj) {
                        if ((i3 & 1) != 0) {
                            th = connectionFailed.throwable;
                        }
                        return connectionFailed.copy(th);
                    }

                    @NotNull
                    public final Throwable component1() {
                        return this.throwable;
                    }

                    @NotNull
                    public final ConnectionFailed copy(@NotNull Throwable th) {
                        Intrinsics.checkNotNullParameter(th, "throwable");
                        return new ConnectionFailed(th);
                    }

                    public boolean equals(@Nullable Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return (obj instanceof ConnectionFailed) && Intrinsics.areEqual((Object) this.throwable, (Object) ((ConnectionFailed) obj).throwable);
                    }

                    @NotNull
                    public final Throwable getThrowable() {
                        return this.throwable;
                    }

                    public int hashCode() {
                        return this.throwable.hashCode();
                    }

                    @NotNull
                    public String toString() {
                        Throwable th = this.throwable;
                        return "ConnectionFailed(throwable=" + th + ")";
                    }
                }

                public /* synthetic */ Reason(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                private Reason() {
                    super((DefaultConstructorMarker) null);
                }
            }

            /* JADX INFO: this call moved to the top of the method (can break code semantics) */
            public /* synthetic */ ConnectionState(boolean z2, Reason reason2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                this(z2, (i3 & 2) != 0 ? null : reason2);
            }

            public static /* synthetic */ ConnectionState copy$default(ConnectionState connectionState, boolean z2, Reason reason2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    z2 = connectionState.isAvailable;
                }
                if ((i3 & 2) != 0) {
                    reason2 = connectionState.reason;
                }
                return connectionState.copy(z2, reason2);
            }

            public final boolean component1() {
                return this.isAvailable;
            }

            @Nullable
            public final Reason component2() {
                return this.reason;
            }

            @NotNull
            public final ConnectionState copy(boolean z2, @Nullable Reason reason2) {
                return new ConnectionState(z2, reason2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof ConnectionState)) {
                    return false;
                }
                ConnectionState connectionState = (ConnectionState) obj;
                return this.isAvailable == connectionState.isAvailable && Intrinsics.areEqual((Object) this.reason, (Object) connectionState.reason);
            }

            @Nullable
            public final Reason getReason() {
                return this.reason;
            }

            public int hashCode() {
                int hashCode = Boolean.hashCode(this.isAvailable) * 31;
                Reason reason2 = this.reason;
                return hashCode + (reason2 == null ? 0 : reason2.hashCode());
            }

            public final boolean isAvailable() {
                return this.isAvailable;
            }

            @NotNull
            public String toString() {
                boolean z2 = this.isAvailable;
                Reason reason2 = this.reason;
                return "ConnectionState(isAvailable=" + z2 + ", reason=" + reason2 + ")";
            }

            public ConnectionState(boolean z2, @Nullable Reason reason2) {
                super((DefaultConstructorMarker) null);
                this.isAvailable = z2;
                this.reason = reason2;
            }
        }

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/sign/client/Sign$Model$DeletedSession;", "Lcom/reown/sign/client/Sign$Model;", "<init>", "()V", "Success", "Error", "Lcom/reown/sign/client/Sign$Model$DeletedSession$Error;", "Lcom/reown/sign/client/Sign$Model$DeletedSession$Success;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static abstract class DeletedSession extends Model {

            @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/reown/sign/client/Sign$Model$DeletedSession$Error;", "Lcom/reown/sign/client/Sign$Model$DeletedSession;", "error", "", "<init>", "(Ljava/lang/Throwable;)V", "getError", "()Ljava/lang/Throwable;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Error extends DeletedSession {
                @NotNull
                private final Throwable error;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Error(@NotNull Throwable th) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(th, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
                    this.error = th;
                }

                public static /* synthetic */ Error copy$default(Error error2, Throwable th, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        th = error2.error;
                    }
                    return error2.copy(th);
                }

                @NotNull
                public final Throwable component1() {
                    return this.error;
                }

                @NotNull
                public final Error copy(@NotNull Throwable th) {
                    Intrinsics.checkNotNullParameter(th, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
                    return new Error(th);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return (obj instanceof Error) && Intrinsics.areEqual((Object) this.error, (Object) ((Error) obj).error);
                }

                @NotNull
                public final Throwable getError() {
                    return this.error;
                }

                public int hashCode() {
                    return this.error.hashCode();
                }

                @NotNull
                public String toString() {
                    Throwable th = this.error;
                    return "Error(error=" + th + ")";
                }
            }

            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/reown/sign/client/Sign$Model$DeletedSession$Success;", "Lcom/reown/sign/client/Sign$Model$DeletedSession;", "topic", "", "reason", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getTopic", "()Ljava/lang/String;", "getReason", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Success extends DeletedSession {
                @NotNull
                private final String reason;
                @NotNull
                private final String topic;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Success(@NotNull String str, @NotNull String str2) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                    Intrinsics.checkNotNullParameter(str2, "reason");
                    this.topic = str;
                    this.reason = str2;
                }

                public static /* synthetic */ Success copy$default(Success success, String str, String str2, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        str = success.topic;
                    }
                    if ((i3 & 2) != 0) {
                        str2 = success.reason;
                    }
                    return success.copy(str, str2);
                }

                @NotNull
                public final String component1() {
                    return this.topic;
                }

                @NotNull
                public final String component2() {
                    return this.reason;
                }

                @NotNull
                public final Success copy(@NotNull String str, @NotNull String str2) {
                    Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                    Intrinsics.checkNotNullParameter(str2, "reason");
                    return new Success(str, str2);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof Success)) {
                        return false;
                    }
                    Success success = (Success) obj;
                    return Intrinsics.areEqual((Object) this.topic, (Object) success.topic) && Intrinsics.areEqual((Object) this.reason, (Object) success.reason);
                }

                @NotNull
                public final String getReason() {
                    return this.reason;
                }

                @NotNull
                public final String getTopic() {
                    return this.topic;
                }

                public int hashCode() {
                    return this.reason.hashCode() + (this.topic.hashCode() * 31);
                }

                @NotNull
                public String toString() {
                    return C0118y.g("Success(topic=", this.topic, ", reason=", this.reason, ")");
                }
            }

            public /* synthetic */ DeletedSession(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private DeletedSession() {
                super((DefaultConstructorMarker) null);
            }
        }

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/reown/sign/client/Sign$Model$Error;", "Lcom/reown/sign/client/Sign$Model;", "throwable", "", "<init>", "(Ljava/lang/Throwable;)V", "getThrowable", "()Ljava/lang/Throwable;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Error extends Model {
            @NotNull
            private final Throwable throwable;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Error(@NotNull Throwable th) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(th, "throwable");
                this.throwable = th;
            }

            public static /* synthetic */ Error copy$default(Error error, Throwable th, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    th = error.throwable;
                }
                return error.copy(th);
            }

            @NotNull
            public final Throwable component1() {
                return this.throwable;
            }

            @NotNull
            public final Error copy(@NotNull Throwable th) {
                Intrinsics.checkNotNullParameter(th, "throwable");
                return new Error(th);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof Error) && Intrinsics.areEqual((Object) this.throwable, (Object) ((Error) obj).throwable);
            }

            @NotNull
            public final Throwable getThrowable() {
                return this.throwable;
            }

            public int hashCode() {
                return this.throwable.hashCode();
            }

            @NotNull
            public String toString() {
                Throwable th = this.throwable;
                return "Error(throwable=" + th + ")";
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J1\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u001a"}, d2 = {"Lcom/reown/sign/client/Sign$Model$Event;", "Lcom/reown/sign/client/Sign$Model;", "topic", "", "name", "data", "chainId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getTopic", "()Ljava/lang/String;", "getName", "getData", "getChainId", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Event extends Model {
            @NotNull
            private final String chainId;
            @NotNull
            private final String data;
            @NotNull
            private final String name;
            @NotNull
            private final String topic;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Event(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                Intrinsics.checkNotNullParameter(str2, "name");
                Intrinsics.checkNotNullParameter(str3, "data");
                Intrinsics.checkNotNullParameter(str4, "chainId");
                this.topic = str;
                this.name = str2;
                this.data = str3;
                this.chainId = str4;
            }

            public static /* synthetic */ Event copy$default(Event event, String str, String str2, String str3, String str4, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = event.topic;
                }
                if ((i3 & 2) != 0) {
                    str2 = event.name;
                }
                if ((i3 & 4) != 0) {
                    str3 = event.data;
                }
                if ((i3 & 8) != 0) {
                    str4 = event.chainId;
                }
                return event.copy(str, str2, str3, str4);
            }

            @NotNull
            public final String component1() {
                return this.topic;
            }

            @NotNull
            public final String component2() {
                return this.name;
            }

            @NotNull
            public final String component3() {
                return this.data;
            }

            @NotNull
            public final String component4() {
                return this.chainId;
            }

            @NotNull
            public final Event copy(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                Intrinsics.checkNotNullParameter(str2, "name");
                Intrinsics.checkNotNullParameter(str3, "data");
                Intrinsics.checkNotNullParameter(str4, "chainId");
                return new Event(str, str2, str3, str4);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Event)) {
                    return false;
                }
                Event event = (Event) obj;
                return Intrinsics.areEqual((Object) this.topic, (Object) event.topic) && Intrinsics.areEqual((Object) this.name, (Object) event.name) && Intrinsics.areEqual((Object) this.data, (Object) event.data) && Intrinsics.areEqual((Object) this.chainId, (Object) event.chainId);
            }

            @NotNull
            public final String getChainId() {
                return this.chainId;
            }

            @NotNull
            public final String getData() {
                return this.data;
            }

            @NotNull
            public final String getName() {
                return this.name;
            }

            @NotNull
            public final String getTopic() {
                return this.topic;
            }

            public int hashCode() {
                return this.chainId.hashCode() + androidx.compose.animation.core.a.i(this.data, androidx.compose.animation.core.a.i(this.name, this.topic.hashCode() * 31, 31), 31);
            }

            @NotNull
            public String toString() {
                String str = this.topic;
                String str2 = this.name;
                return android.support.v4.media.session.a.r(C0118y.l("Event(topic=", str, ", name=", str2, ", data="), this.data, ", chainId=", this.chainId, ")");
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/reown/sign/client/Sign$Model$ExpiredProposal;", "Lcom/reown/sign/client/Sign$Model;", "pairingTopic", "", "proposerPublicKey", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getPairingTopic", "()Ljava/lang/String;", "getProposerPublicKey", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class ExpiredProposal extends Model {
            @NotNull
            private final String pairingTopic;
            @NotNull
            private final String proposerPublicKey;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public ExpiredProposal(@NotNull String str, @NotNull String str2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "pairingTopic");
                Intrinsics.checkNotNullParameter(str2, "proposerPublicKey");
                this.pairingTopic = str;
                this.proposerPublicKey = str2;
            }

            public static /* synthetic */ ExpiredProposal copy$default(ExpiredProposal expiredProposal, String str, String str2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = expiredProposal.pairingTopic;
                }
                if ((i3 & 2) != 0) {
                    str2 = expiredProposal.proposerPublicKey;
                }
                return expiredProposal.copy(str, str2);
            }

            @NotNull
            public final String component1() {
                return this.pairingTopic;
            }

            @NotNull
            public final String component2() {
                return this.proposerPublicKey;
            }

            @NotNull
            public final ExpiredProposal copy(@NotNull String str, @NotNull String str2) {
                Intrinsics.checkNotNullParameter(str, "pairingTopic");
                Intrinsics.checkNotNullParameter(str2, "proposerPublicKey");
                return new ExpiredProposal(str, str2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof ExpiredProposal)) {
                    return false;
                }
                ExpiredProposal expiredProposal = (ExpiredProposal) obj;
                return Intrinsics.areEqual((Object) this.pairingTopic, (Object) expiredProposal.pairingTopic) && Intrinsics.areEqual((Object) this.proposerPublicKey, (Object) expiredProposal.proposerPublicKey);
            }

            @NotNull
            public final String getPairingTopic() {
                return this.pairingTopic;
            }

            @NotNull
            public final String getProposerPublicKey() {
                return this.proposerPublicKey;
            }

            public int hashCode() {
                return this.proposerPublicKey.hashCode() + (this.pairingTopic.hashCode() * 31);
            }

            @NotNull
            public String toString() {
                return C0118y.g("ExpiredProposal(pairingTopic=", this.pairingTopic, ", proposerPublicKey=", this.proposerPublicKey, ")");
            }
        }

        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/reown/sign/client/Sign$Model$ExpiredRequest;", "Lcom/reown/sign/client/Sign$Model;", "topic", "", "id", "", "<init>", "(Ljava/lang/String;J)V", "getTopic", "()Ljava/lang/String;", "getId", "()J", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class ExpiredRequest extends Model {
            private final long id;
            @NotNull
            private final String topic;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public ExpiredRequest(@NotNull String str, long j2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                this.topic = str;
                this.id = j2;
            }

            public static /* synthetic */ ExpiredRequest copy$default(ExpiredRequest expiredRequest, String str, long j2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = expiredRequest.topic;
                }
                if ((i3 & 2) != 0) {
                    j2 = expiredRequest.id;
                }
                return expiredRequest.copy(str, j2);
            }

            @NotNull
            public final String component1() {
                return this.topic;
            }

            public final long component2() {
                return this.id;
            }

            @NotNull
            public final ExpiredRequest copy(@NotNull String str, long j2) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                return new ExpiredRequest(str, j2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof ExpiredRequest)) {
                    return false;
                }
                ExpiredRequest expiredRequest = (ExpiredRequest) obj;
                return Intrinsics.areEqual((Object) this.topic, (Object) expiredRequest.topic) && this.id == expiredRequest.id;
            }

            public final long getId() {
                return this.id;
            }

            @NotNull
            public final String getTopic() {
                return this.topic;
            }

            public int hashCode() {
                return Long.hashCode(this.id) + (this.topic.hashCode() * 31);
            }

            @NotNull
            public String toString() {
                StringBuilder i3 = com.appsamurai.storyly.exoplayer2.core.source.chunk.a.i(this.id, "ExpiredRequest(topic=", this.topic, ", id=");
                i3.append(")");
                return i3.toString();
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\f\rB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tXD¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u0001\u0002\u000e\u000f¨\u0006\u0010"}, d2 = {"Lcom/reown/sign/client/Sign$Model$JsonRpcResponse;", "Lcom/reown/sign/client/Sign$Model;", "<init>", "()V", "id", "", "getId", "()J", "jsonrpc", "", "getJsonrpc", "()Ljava/lang/String;", "JsonRpcResult", "JsonRpcError", "Lcom/reown/sign/client/Sign$Model$JsonRpcResponse$JsonRpcError;", "Lcom/reown/sign/client/Sign$Model$JsonRpcResponse$JsonRpcResult;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static abstract class JsonRpcResponse extends Model {
            @NotNull
            private final String jsonrpc;

            @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0007HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/reown/sign/client/Sign$Model$JsonRpcResponse$JsonRpcError;", "Lcom/reown/sign/client/Sign$Model$JsonRpcResponse;", "id", "", "code", "", "message", "", "<init>", "(JILjava/lang/String;)V", "getId", "()J", "getCode", "()I", "getMessage", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class JsonRpcError extends JsonRpcResponse {
                private final int code;
                private final long id;
                @NotNull
                private final String message;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public JsonRpcError(long j2, int i3, @NotNull String str) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                    this.id = j2;
                    this.code = i3;
                    this.message = str;
                }

                public static /* synthetic */ JsonRpcError copy$default(JsonRpcError jsonRpcError, long j2, int i3, String str, int i4, Object obj) {
                    if ((i4 & 1) != 0) {
                        j2 = jsonRpcError.id;
                    }
                    if ((i4 & 2) != 0) {
                        i3 = jsonRpcError.code;
                    }
                    if ((i4 & 4) != 0) {
                        str = jsonRpcError.message;
                    }
                    return jsonRpcError.copy(j2, i3, str);
                }

                public final long component1() {
                    return this.id;
                }

                public final int component2() {
                    return this.code;
                }

                @NotNull
                public final String component3() {
                    return this.message;
                }

                @NotNull
                public final JsonRpcError copy(long j2, int i3, @NotNull String str) {
                    Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                    return new JsonRpcError(j2, i3, str);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof JsonRpcError)) {
                        return false;
                    }
                    JsonRpcError jsonRpcError = (JsonRpcError) obj;
                    return this.id == jsonRpcError.id && this.code == jsonRpcError.code && Intrinsics.areEqual((Object) this.message, (Object) jsonRpcError.message);
                }

                public final int getCode() {
                    return this.code;
                }

                public long getId() {
                    return this.id;
                }

                @NotNull
                public final String getMessage() {
                    return this.message;
                }

                public int hashCode() {
                    return this.message.hashCode() + androidx.compose.animation.core.a.c(this.code, Long.hashCode(this.id) * 31, 31);
                }

                @NotNull
                public String toString() {
                    long j2 = this.id;
                    int i3 = this.code;
                    String str = this.message;
                    StringBuilder sb = new StringBuilder("JsonRpcError(id=");
                    sb.append(j2);
                    sb.append(", code=");
                    sb.append(i3);
                    return C0118y.j(sb, ", message=", str, ")");
                }
            }

            @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/reown/sign/client/Sign$Model$JsonRpcResponse$JsonRpcResult;", "Lcom/reown/sign/client/Sign$Model$JsonRpcResponse;", "id", "", "result", "", "<init>", "(JLjava/lang/String;)V", "getId", "()J", "getResult", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class JsonRpcResult extends JsonRpcResponse {
                private final long id;
                @Nullable
                private final String result;

                public JsonRpcResult(long j2, @Nullable String str) {
                    super((DefaultConstructorMarker) null);
                    this.id = j2;
                    this.result = str;
                }

                public static /* synthetic */ JsonRpcResult copy$default(JsonRpcResult jsonRpcResult, long j2, String str, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        j2 = jsonRpcResult.id;
                    }
                    if ((i3 & 2) != 0) {
                        str = jsonRpcResult.result;
                    }
                    return jsonRpcResult.copy(j2, str);
                }

                public final long component1() {
                    return this.id;
                }

                @Nullable
                public final String component2() {
                    return this.result;
                }

                @NotNull
                public final JsonRpcResult copy(long j2, @Nullable String str) {
                    return new JsonRpcResult(j2, str);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof JsonRpcResult)) {
                        return false;
                    }
                    JsonRpcResult jsonRpcResult = (JsonRpcResult) obj;
                    return this.id == jsonRpcResult.id && Intrinsics.areEqual((Object) this.result, (Object) jsonRpcResult.result);
                }

                public long getId() {
                    return this.id;
                }

                @Nullable
                public final String getResult() {
                    return this.result;
                }

                public int hashCode() {
                    int hashCode = Long.hashCode(this.id) * 31;
                    String str = this.result;
                    return hashCode + (str == null ? 0 : str.hashCode());
                }

                @NotNull
                public String toString() {
                    StringBuilder v2 = androidx.work.impl.a.v(this.id, "JsonRpcResult(id=", ", result=", this.result);
                    v2.append(")");
                    return v2.toString();
                }
            }

            public /* synthetic */ JsonRpcResponse(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public abstract long getId();

            @NotNull
            public final String getJsonrpc() {
                return this.jsonrpc;
            }

            private JsonRpcResponse() {
                super((DefaultConstructorMarker) null);
                this.jsonrpc = "2.0";
            }
        }

        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0004\u0007\b\t\n¨\u0006\u000b"}, d2 = {"Lcom/reown/sign/client/Sign$Model$Message;", "Lcom/reown/sign/client/Sign$Model;", "<init>", "()V", "SessionProposal", "SessionRequest", "SessionAuthenticate", "Lcom/reown/sign/client/Sign$Model$Message$SessionAuthenticate;", "Lcom/reown/sign/client/Sign$Model$Message$SessionProposal;", "Lcom/reown/sign/client/Sign$Model$Message$SessionRequest;", "Lcom/reown/sign/client/Sign$Model$Message$SessionRequest$JSONRPCRequest;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static abstract class Message extends Model {

            @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0019\u001a\u00020\tHÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J;\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fHÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000e¨\u0006#"}, d2 = {"Lcom/reown/sign/client/Sign$Model$Message$SessionAuthenticate;", "Lcom/reown/sign/client/Sign$Model$Message;", "id", "", "topic", "", "metadata", "Lcom/reown/android/Core$Model$AppMetaData;", "payloadParams", "Lcom/reown/sign/client/Sign$Model$PayloadParams;", "expiry", "<init>", "(JLjava/lang/String;Lcom/reown/android/Core$Model$AppMetaData;Lcom/reown/sign/client/Sign$Model$PayloadParams;J)V", "getId", "()J", "getTopic", "()Ljava/lang/String;", "getMetadata", "()Lcom/reown/android/Core$Model$AppMetaData;", "getPayloadParams", "()Lcom/reown/sign/client/Sign$Model$PayloadParams;", "getExpiry", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class SessionAuthenticate extends Message {
                private final long expiry;
                private final long id;
                @NotNull
                private final Core.Model.AppMetaData metadata;
                @NotNull
                private final PayloadParams payloadParams;
                @NotNull
                private final String topic;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public SessionAuthenticate(long j2, @NotNull String str, @NotNull Core.Model.AppMetaData appMetaData, @NotNull PayloadParams payloadParams2, long j3) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                    Intrinsics.checkNotNullParameter(appMetaData, TtmlNode.TAG_METADATA);
                    Intrinsics.checkNotNullParameter(payloadParams2, "payloadParams");
                    this.id = j2;
                    this.topic = str;
                    this.metadata = appMetaData;
                    this.payloadParams = payloadParams2;
                    this.expiry = j3;
                }

                public static /* synthetic */ SessionAuthenticate copy$default(SessionAuthenticate sessionAuthenticate, long j2, String str, Core.Model.AppMetaData appMetaData, PayloadParams payloadParams2, long j3, int i3, Object obj) {
                    SessionAuthenticate sessionAuthenticate2 = sessionAuthenticate;
                    return sessionAuthenticate.copy((i3 & 1) != 0 ? sessionAuthenticate2.id : j2, (i3 & 2) != 0 ? sessionAuthenticate2.topic : str, (i3 & 4) != 0 ? sessionAuthenticate2.metadata : appMetaData, (i3 & 8) != 0 ? sessionAuthenticate2.payloadParams : payloadParams2, (i3 & 16) != 0 ? sessionAuthenticate2.expiry : j3);
                }

                public final long component1() {
                    return this.id;
                }

                @NotNull
                public final String component2() {
                    return this.topic;
                }

                @NotNull
                public final Core.Model.AppMetaData component3() {
                    return this.metadata;
                }

                @NotNull
                public final PayloadParams component4() {
                    return this.payloadParams;
                }

                public final long component5() {
                    return this.expiry;
                }

                @NotNull
                public final SessionAuthenticate copy(long j2, @NotNull String str, @NotNull Core.Model.AppMetaData appMetaData, @NotNull PayloadParams payloadParams2, long j3) {
                    Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                    Intrinsics.checkNotNullParameter(appMetaData, TtmlNode.TAG_METADATA);
                    Intrinsics.checkNotNullParameter(payloadParams2, "payloadParams");
                    return new SessionAuthenticate(j2, str, appMetaData, payloadParams2, j3);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof SessionAuthenticate)) {
                        return false;
                    }
                    SessionAuthenticate sessionAuthenticate = (SessionAuthenticate) obj;
                    return this.id == sessionAuthenticate.id && Intrinsics.areEqual((Object) this.topic, (Object) sessionAuthenticate.topic) && Intrinsics.areEqual((Object) this.metadata, (Object) sessionAuthenticate.metadata) && Intrinsics.areEqual((Object) this.payloadParams, (Object) sessionAuthenticate.payloadParams) && this.expiry == sessionAuthenticate.expiry;
                }

                public final long getExpiry() {
                    return this.expiry;
                }

                public final long getId() {
                    return this.id;
                }

                @NotNull
                public final Core.Model.AppMetaData getMetadata() {
                    return this.metadata;
                }

                @NotNull
                public final PayloadParams getPayloadParams() {
                    return this.payloadParams;
                }

                @NotNull
                public final String getTopic() {
                    return this.topic;
                }

                public int hashCode() {
                    int i3 = androidx.compose.animation.core.a.i(this.topic, Long.hashCode(this.id) * 31, 31);
                    int hashCode = this.payloadParams.hashCode();
                    return Long.hashCode(this.expiry) + ((hashCode + ((this.metadata.hashCode() + i3) * 31)) * 31);
                }

                @NotNull
                public String toString() {
                    long j2 = this.id;
                    String str = this.topic;
                    Core.Model.AppMetaData appMetaData = this.metadata;
                    PayloadParams payloadParams2 = this.payloadParams;
                    long j3 = this.expiry;
                    StringBuilder v2 = androidx.work.impl.a.v(j2, "SessionAuthenticate(id=", ", topic=", str);
                    v2.append(", metadata=");
                    v2.append(appMetaData);
                    v2.append(", payloadParams=");
                    v2.append(payloadParams2);
                    v2.append(", expiry=");
                    v2.append(j3);
                    v2.append(")");
                    return v2.toString();
                }
            }

            @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b'\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0014\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\r\u0012\u0006\u0010\u0011\u001a\u00020\u0005\u0012\u0006\u0010\u0012\u001a\u00020\u0005\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0014\u0010\u0015J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0005HÆ\u0003J\t\u0010)\u001a\u00020\u0005HÆ\u0003J\t\u0010*\u001a\u00020\u0005HÆ\u0003J\t\u0010+\u001a\u00020\u0005HÆ\u0003J\u000f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00050\nHÆ\u0003J\t\u0010-\u001a\u00020\u0005HÆ\u0003J\u0015\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000e0\rHÆ\u0003J\u0015\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000e0\rHÆ\u0003J\u0017\u00100\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\rHÆ\u0003J\t\u00101\u001a\u00020\u0005HÆ\u0003J\t\u00102\u001a\u00020\u0005HÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0005HÆ\u0003J¹\u0001\u00104\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00052\u0014\b\u0002\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000e0\r2\u0014\b\u0002\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000e0\r2\u0016\b\u0002\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\r2\b\b\u0002\u0010\u0011\u001a\u00020\u00052\b\b\u0002\u0010\u0012\u001a\u00020\u00052\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u00105\u001a\u0002062\b\u00107\u001a\u0004\u0018\u000108HÖ\u0003J\t\u00109\u001a\u00020:HÖ\u0001J\t\u0010;\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0019R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0019R\u001d\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u001d\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010!R\u001f\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b#\u0010!R\u0011\u0010\u0011\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0019R\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0019R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0019¨\u0006<"}, d2 = {"Lcom/reown/sign/client/Sign$Model$Message$SessionProposal;", "Lcom/reown/sign/client/Sign$Model$Message;", "id", "", "pairingTopic", "", "name", "description", "url", "icons", "", "redirect", "requiredNamespaces", "", "Lcom/reown/sign/client/Sign$Model$Namespace$Proposal;", "optionalNamespaces", "properties", "proposerPublicKey", "relayProtocol", "relayData", "<init>", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()J", "getPairingTopic", "()Ljava/lang/String;", "getName", "getDescription", "getUrl", "getIcons", "()Ljava/util/List;", "getRedirect", "getRequiredNamespaces", "()Ljava/util/Map;", "getOptionalNamespaces", "getProperties", "getProposerPublicKey", "getRelayProtocol", "getRelayData", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class SessionProposal extends Message {
                @NotNull
                private final String description;
                @NotNull
                private final List<String> icons;
                private final long id;
                @NotNull
                private final String name;
                @NotNull
                private final Map<String, Namespace.Proposal> optionalNamespaces;
                @NotNull
                private final String pairingTopic;
                @Nullable
                private final Map<String, String> properties;
                @NotNull
                private final String proposerPublicKey;
                @NotNull
                private final String redirect;
                @Nullable
                private final String relayData;
                @NotNull
                private final String relayProtocol;
                @NotNull
                private final Map<String, Namespace.Proposal> requiredNamespaces;
                @NotNull
                private final String url;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public SessionProposal(long j2, @NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull List<String> list, @NotNull String str5, @NotNull Map<String, Namespace.Proposal> map, @NotNull Map<String, Namespace.Proposal> map2, @Nullable Map<String, String> map3, @NotNull String str6, @NotNull String str7, @Nullable String str8) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, "pairingTopic");
                    Intrinsics.checkNotNullParameter(str2, "name");
                    Intrinsics.checkNotNullParameter(str3, "description");
                    Intrinsics.checkNotNullParameter(str4, "url");
                    Intrinsics.checkNotNullParameter(list, "icons");
                    Intrinsics.checkNotNullParameter(str5, "redirect");
                    Intrinsics.checkNotNullParameter(map, "requiredNamespaces");
                    Intrinsics.checkNotNullParameter(map2, "optionalNamespaces");
                    Intrinsics.checkNotNullParameter(str6, "proposerPublicKey");
                    Intrinsics.checkNotNullParameter(str7, "relayProtocol");
                    this.id = j2;
                    this.pairingTopic = str;
                    this.name = str2;
                    this.description = str3;
                    this.url = str4;
                    this.icons = list;
                    this.redirect = str5;
                    this.requiredNamespaces = map;
                    this.optionalNamespaces = map2;
                    this.properties = map3;
                    this.proposerPublicKey = str6;
                    this.relayProtocol = str7;
                    this.relayData = str8;
                }

                public static /* synthetic */ SessionProposal copy$default(SessionProposal sessionProposal, long j2, String str, String str2, String str3, String str4, List list, String str5, Map map, Map map2, Map map3, String str6, String str7, String str8, int i3, Object obj) {
                    SessionProposal sessionProposal2 = sessionProposal;
                    int i4 = i3;
                    return sessionProposal.copy((i4 & 1) != 0 ? sessionProposal2.id : j2, (i4 & 2) != 0 ? sessionProposal2.pairingTopic : str, (i4 & 4) != 0 ? sessionProposal2.name : str2, (i4 & 8) != 0 ? sessionProposal2.description : str3, (i4 & 16) != 0 ? sessionProposal2.url : str4, (i4 & 32) != 0 ? sessionProposal2.icons : list, (i4 & 64) != 0 ? sessionProposal2.redirect : str5, (i4 & 128) != 0 ? sessionProposal2.requiredNamespaces : map, (i4 & 256) != 0 ? sessionProposal2.optionalNamespaces : map2, (i4 & 512) != 0 ? sessionProposal2.properties : map3, (i4 & 1024) != 0 ? sessionProposal2.proposerPublicKey : str6, (i4 & 2048) != 0 ? sessionProposal2.relayProtocol : str7, (i4 & 4096) != 0 ? sessionProposal2.relayData : str8);
                }

                public final long component1() {
                    return this.id;
                }

                @Nullable
                public final Map<String, String> component10() {
                    return this.properties;
                }

                @NotNull
                public final String component11() {
                    return this.proposerPublicKey;
                }

                @NotNull
                public final String component12() {
                    return this.relayProtocol;
                }

                @Nullable
                public final String component13() {
                    return this.relayData;
                }

                @NotNull
                public final String component2() {
                    return this.pairingTopic;
                }

                @NotNull
                public final String component3() {
                    return this.name;
                }

                @NotNull
                public final String component4() {
                    return this.description;
                }

                @NotNull
                public final String component5() {
                    return this.url;
                }

                @NotNull
                public final List<String> component6() {
                    return this.icons;
                }

                @NotNull
                public final String component7() {
                    return this.redirect;
                }

                @NotNull
                public final Map<String, Namespace.Proposal> component8() {
                    return this.requiredNamespaces;
                }

                @NotNull
                public final Map<String, Namespace.Proposal> component9() {
                    return this.optionalNamespaces;
                }

                @NotNull
                public final SessionProposal copy(long j2, @NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull List<String> list, @NotNull String str5, @NotNull Map<String, Namespace.Proposal> map, @NotNull Map<String, Namespace.Proposal> map2, @Nullable Map<String, String> map3, @NotNull String str6, @NotNull String str7, @Nullable String str8) {
                    String str9 = str;
                    Intrinsics.checkNotNullParameter(str9, "pairingTopic");
                    String str10 = str2;
                    Intrinsics.checkNotNullParameter(str10, "name");
                    String str11 = str3;
                    Intrinsics.checkNotNullParameter(str11, "description");
                    String str12 = str4;
                    Intrinsics.checkNotNullParameter(str12, "url");
                    List<String> list2 = list;
                    Intrinsics.checkNotNullParameter(list2, "icons");
                    String str13 = str5;
                    Intrinsics.checkNotNullParameter(str13, "redirect");
                    Map<String, Namespace.Proposal> map4 = map;
                    Intrinsics.checkNotNullParameter(map4, "requiredNamespaces");
                    Map<String, Namespace.Proposal> map5 = map2;
                    Intrinsics.checkNotNullParameter(map5, "optionalNamespaces");
                    String str14 = str6;
                    Intrinsics.checkNotNullParameter(str14, "proposerPublicKey");
                    String str15 = str7;
                    Intrinsics.checkNotNullParameter(str15, "relayProtocol");
                    return new SessionProposal(j2, str9, str10, str11, str12, list2, str13, map4, map5, map3, str14, str15, str8);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof SessionProposal)) {
                        return false;
                    }
                    SessionProposal sessionProposal = (SessionProposal) obj;
                    return this.id == sessionProposal.id && Intrinsics.areEqual((Object) this.pairingTopic, (Object) sessionProposal.pairingTopic) && Intrinsics.areEqual((Object) this.name, (Object) sessionProposal.name) && Intrinsics.areEqual((Object) this.description, (Object) sessionProposal.description) && Intrinsics.areEqual((Object) this.url, (Object) sessionProposal.url) && Intrinsics.areEqual((Object) this.icons, (Object) sessionProposal.icons) && Intrinsics.areEqual((Object) this.redirect, (Object) sessionProposal.redirect) && Intrinsics.areEqual((Object) this.requiredNamespaces, (Object) sessionProposal.requiredNamespaces) && Intrinsics.areEqual((Object) this.optionalNamespaces, (Object) sessionProposal.optionalNamespaces) && Intrinsics.areEqual((Object) this.properties, (Object) sessionProposal.properties) && Intrinsics.areEqual((Object) this.proposerPublicKey, (Object) sessionProposal.proposerPublicKey) && Intrinsics.areEqual((Object) this.relayProtocol, (Object) sessionProposal.relayProtocol) && Intrinsics.areEqual((Object) this.relayData, (Object) sessionProposal.relayData);
                }

                @NotNull
                public final String getDescription() {
                    return this.description;
                }

                @NotNull
                public final List<String> getIcons() {
                    return this.icons;
                }

                public final long getId() {
                    return this.id;
                }

                @NotNull
                public final String getName() {
                    return this.name;
                }

                @NotNull
                public final Map<String, Namespace.Proposal> getOptionalNamespaces() {
                    return this.optionalNamespaces;
                }

                @NotNull
                public final String getPairingTopic() {
                    return this.pairingTopic;
                }

                @Nullable
                public final Map<String, String> getProperties() {
                    return this.properties;
                }

                @NotNull
                public final String getProposerPublicKey() {
                    return this.proposerPublicKey;
                }

                @NotNull
                public final String getRedirect() {
                    return this.redirect;
                }

                @Nullable
                public final String getRelayData() {
                    return this.relayData;
                }

                @NotNull
                public final String getRelayProtocol() {
                    return this.relayProtocol;
                }

                @NotNull
                public final Map<String, Namespace.Proposal> getRequiredNamespaces() {
                    return this.requiredNamespaces;
                }

                @NotNull
                public final String getUrl() {
                    return this.url;
                }

                public int hashCode() {
                    int d2 = b.d(this.optionalNamespaces, b.d(this.requiredNamespaces, androidx.compose.animation.core.a.i(this.redirect, androidx.compose.animation.core.a.j(this.icons, androidx.compose.animation.core.a.i(this.url, androidx.compose.animation.core.a.i(this.description, androidx.compose.animation.core.a.i(this.name, androidx.compose.animation.core.a.i(this.pairingTopic, Long.hashCode(this.id) * 31, 31), 31), 31), 31), 31), 31), 31), 31);
                    Map<String, String> map = this.properties;
                    int i3 = 0;
                    int i4 = androidx.compose.animation.core.a.i(this.relayProtocol, androidx.compose.animation.core.a.i(this.proposerPublicKey, (d2 + (map == null ? 0 : map.hashCode())) * 31, 31), 31);
                    String str = this.relayData;
                    if (str != null) {
                        i3 = str.hashCode();
                    }
                    return i4 + i3;
                }

                @NotNull
                public String toString() {
                    long j2 = this.id;
                    String str = this.pairingTopic;
                    String str2 = this.name;
                    String str3 = this.description;
                    String str4 = this.url;
                    List<String> list = this.icons;
                    String str5 = this.redirect;
                    Map<String, Namespace.Proposal> map = this.requiredNamespaces;
                    Map<String, Namespace.Proposal> map2 = this.optionalNamespaces;
                    Map<String, String> map3 = this.properties;
                    String str6 = this.proposerPublicKey;
                    String str7 = this.relayProtocol;
                    String str8 = this.relayData;
                    StringBuilder v2 = androidx.work.impl.a.v(j2, "SessionProposal(id=", ", pairingTopic=", str);
                    b.w(v2, ", name=", str2, ", description=", str3);
                    v2.append(", url=");
                    v2.append(str4);
                    v2.append(", icons=");
                    v2.append(list);
                    v2.append(", redirect=");
                    v2.append(str5);
                    v2.append(", requiredNamespaces=");
                    v2.append(map);
                    v2.append(", optionalNamespaces=");
                    v2.append(map2);
                    v2.append(", properties=");
                    v2.append(map3);
                    b.w(v2, ", proposerPublicKey=", str6, ", relayProtocol=", str7);
                    return C0118y.j(v2, ", relayData=", str8, ")");
                }
            }

            @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001:\u0001\u001eB+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J5\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001f"}, d2 = {"Lcom/reown/sign/client/Sign$Model$Message$SessionRequest;", "Lcom/reown/sign/client/Sign$Model$Message;", "topic", "", "chainId", "peerMetaData", "Lcom/reown/android/Core$Model$AppMetaData;", "request", "Lcom/reown/sign/client/Sign$Model$Message$SessionRequest$JSONRPCRequest;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/reown/android/Core$Model$AppMetaData;Lcom/reown/sign/client/Sign$Model$Message$SessionRequest$JSONRPCRequest;)V", "getTopic", "()Ljava/lang/String;", "getChainId", "getPeerMetaData", "()Lcom/reown/android/Core$Model$AppMetaData;", "getRequest", "()Lcom/reown/sign/client/Sign$Model$Message$SessionRequest$JSONRPCRequest;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "JSONRPCRequest", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class SessionRequest extends Message {
                @Nullable
                private final String chainId;
                @Nullable
                private final Core.Model.AppMetaData peerMetaData;
                @NotNull
                private final JSONRPCRequest request;
                @NotNull
                private final String topic;

                @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0019"}, d2 = {"Lcom/reown/sign/client/Sign$Model$Message$SessionRequest$JSONRPCRequest;", "Lcom/reown/sign/client/Sign$Model$Message;", "id", "", "method", "", "params", "<init>", "(JLjava/lang/String;Ljava/lang/String;)V", "getId", "()J", "getMethod", "()Ljava/lang/String;", "getParams", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
                public static final class JSONRPCRequest extends Message {
                    private final long id;
                    @NotNull
                    private final String method;
                    @NotNull
                    private final String params;

                    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                    public JSONRPCRequest(long j2, @NotNull String str, @NotNull String str2) {
                        super((DefaultConstructorMarker) null);
                        Intrinsics.checkNotNullParameter(str, FirebaseAnalytics.Param.METHOD);
                        Intrinsics.checkNotNullParameter(str2, "params");
                        this.id = j2;
                        this.method = str;
                        this.params = str2;
                    }

                    public static /* synthetic */ JSONRPCRequest copy$default(JSONRPCRequest jSONRPCRequest, long j2, String str, String str2, int i3, Object obj) {
                        if ((i3 & 1) != 0) {
                            j2 = jSONRPCRequest.id;
                        }
                        if ((i3 & 2) != 0) {
                            str = jSONRPCRequest.method;
                        }
                        if ((i3 & 4) != 0) {
                            str2 = jSONRPCRequest.params;
                        }
                        return jSONRPCRequest.copy(j2, str, str2);
                    }

                    public final long component1() {
                        return this.id;
                    }

                    @NotNull
                    public final String component2() {
                        return this.method;
                    }

                    @NotNull
                    public final String component3() {
                        return this.params;
                    }

                    @NotNull
                    public final JSONRPCRequest copy(long j2, @NotNull String str, @NotNull String str2) {
                        Intrinsics.checkNotNullParameter(str, FirebaseAnalytics.Param.METHOD);
                        Intrinsics.checkNotNullParameter(str2, "params");
                        return new JSONRPCRequest(j2, str, str2);
                    }

                    public boolean equals(@Nullable Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (!(obj instanceof JSONRPCRequest)) {
                            return false;
                        }
                        JSONRPCRequest jSONRPCRequest = (JSONRPCRequest) obj;
                        return this.id == jSONRPCRequest.id && Intrinsics.areEqual((Object) this.method, (Object) jSONRPCRequest.method) && Intrinsics.areEqual((Object) this.params, (Object) jSONRPCRequest.params);
                    }

                    public final long getId() {
                        return this.id;
                    }

                    @NotNull
                    public final String getMethod() {
                        return this.method;
                    }

                    @NotNull
                    public final String getParams() {
                        return this.params;
                    }

                    public int hashCode() {
                        return this.params.hashCode() + androidx.compose.animation.core.a.i(this.method, Long.hashCode(this.id) * 31, 31);
                    }

                    @NotNull
                    public String toString() {
                        long j2 = this.id;
                        String str = this.method;
                        return C0118y.j(androidx.work.impl.a.v(j2, "JSONRPCRequest(id=", ", method=", str), ", params=", this.params, ")");
                    }
                }

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public SessionRequest(@NotNull String str, @Nullable String str2, @Nullable Core.Model.AppMetaData appMetaData, @NotNull JSONRPCRequest jSONRPCRequest) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                    Intrinsics.checkNotNullParameter(jSONRPCRequest, "request");
                    this.topic = str;
                    this.chainId = str2;
                    this.peerMetaData = appMetaData;
                    this.request = jSONRPCRequest;
                }

                public static /* synthetic */ SessionRequest copy$default(SessionRequest sessionRequest, String str, String str2, Core.Model.AppMetaData appMetaData, JSONRPCRequest jSONRPCRequest, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        str = sessionRequest.topic;
                    }
                    if ((i3 & 2) != 0) {
                        str2 = sessionRequest.chainId;
                    }
                    if ((i3 & 4) != 0) {
                        appMetaData = sessionRequest.peerMetaData;
                    }
                    if ((i3 & 8) != 0) {
                        jSONRPCRequest = sessionRequest.request;
                    }
                    return sessionRequest.copy(str, str2, appMetaData, jSONRPCRequest);
                }

                @NotNull
                public final String component1() {
                    return this.topic;
                }

                @Nullable
                public final String component2() {
                    return this.chainId;
                }

                @Nullable
                public final Core.Model.AppMetaData component3() {
                    return this.peerMetaData;
                }

                @NotNull
                public final JSONRPCRequest component4() {
                    return this.request;
                }

                @NotNull
                public final SessionRequest copy(@NotNull String str, @Nullable String str2, @Nullable Core.Model.AppMetaData appMetaData, @NotNull JSONRPCRequest jSONRPCRequest) {
                    Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                    Intrinsics.checkNotNullParameter(jSONRPCRequest, "request");
                    return new SessionRequest(str, str2, appMetaData, jSONRPCRequest);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof SessionRequest)) {
                        return false;
                    }
                    SessionRequest sessionRequest = (SessionRequest) obj;
                    return Intrinsics.areEqual((Object) this.topic, (Object) sessionRequest.topic) && Intrinsics.areEqual((Object) this.chainId, (Object) sessionRequest.chainId) && Intrinsics.areEqual((Object) this.peerMetaData, (Object) sessionRequest.peerMetaData) && Intrinsics.areEqual((Object) this.request, (Object) sessionRequest.request);
                }

                @Nullable
                public final String getChainId() {
                    return this.chainId;
                }

                @Nullable
                public final Core.Model.AppMetaData getPeerMetaData() {
                    return this.peerMetaData;
                }

                @NotNull
                public final JSONRPCRequest getRequest() {
                    return this.request;
                }

                @NotNull
                public final String getTopic() {
                    return this.topic;
                }

                public int hashCode() {
                    int hashCode = this.topic.hashCode() * 31;
                    String str = this.chainId;
                    int i3 = 0;
                    int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
                    Core.Model.AppMetaData appMetaData = this.peerMetaData;
                    if (appMetaData != null) {
                        i3 = appMetaData.hashCode();
                    }
                    return this.request.hashCode() + ((hashCode2 + i3) * 31);
                }

                @NotNull
                public String toString() {
                    String str = this.topic;
                    String str2 = this.chainId;
                    Core.Model.AppMetaData appMetaData = this.peerMetaData;
                    JSONRPCRequest jSONRPCRequest = this.request;
                    StringBuilder l2 = C0118y.l("SessionRequest(topic=", str, ", chainId=", str2, ", peerMetaData=");
                    l2.append(appMetaData);
                    l2.append(", request=");
                    l2.append(jSONRPCRequest);
                    l2.append(")");
                    return l2.toString();
                }
            }

            public /* synthetic */ Message(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Message() {
                super((DefaultConstructorMarker) null);
            }
        }

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/sign/client/Sign$Model$Namespace;", "Lcom/reown/sign/client/Sign$Model;", "<init>", "()V", "Proposal", "Session", "Lcom/reown/sign/client/Sign$Model$Namespace$Proposal;", "Lcom/reown/sign/client/Sign$Model$Namespace$Session;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static abstract class Namespace extends Model {

            @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B5\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0011\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J;\u0010\u0010\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0004HÖ\u0001R\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\n¨\u0006\u0018"}, d2 = {"Lcom/reown/sign/client/Sign$Model$Namespace$Proposal;", "Lcom/reown/sign/client/Sign$Model$Namespace;", "chains", "", "", "methods", "events", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getChains", "()Ljava/util/List;", "getMethods", "getEvents", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Proposal extends Namespace {
                @Nullable
                private final List<String> chains;
                @NotNull
                private final List<String> events;
                @NotNull
                private final List<String> methods;

                /* JADX INFO: this call moved to the top of the method (can break code semantics) */
                public /* synthetic */ Proposal(List list, List list2, List list3, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                    this((i3 & 1) != 0 ? null : list, list2, list3);
                }

                public static /* synthetic */ Proposal copy$default(Proposal proposal, List<String> list, List<String> list2, List<String> list3, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        list = proposal.chains;
                    }
                    if ((i3 & 2) != 0) {
                        list2 = proposal.methods;
                    }
                    if ((i3 & 4) != 0) {
                        list3 = proposal.events;
                    }
                    return proposal.copy(list, list2, list3);
                }

                @Nullable
                public final List<String> component1() {
                    return this.chains;
                }

                @NotNull
                public final List<String> component2() {
                    return this.methods;
                }

                @NotNull
                public final List<String> component3() {
                    return this.events;
                }

                @NotNull
                public final Proposal copy(@Nullable List<String> list, @NotNull List<String> list2, @NotNull List<String> list3) {
                    Intrinsics.checkNotNullParameter(list2, "methods");
                    Intrinsics.checkNotNullParameter(list3, "events");
                    return new Proposal(list, list2, list3);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof Proposal)) {
                        return false;
                    }
                    Proposal proposal = (Proposal) obj;
                    return Intrinsics.areEqual((Object) this.chains, (Object) proposal.chains) && Intrinsics.areEqual((Object) this.methods, (Object) proposal.methods) && Intrinsics.areEqual((Object) this.events, (Object) proposal.events);
                }

                @Nullable
                public final List<String> getChains() {
                    return this.chains;
                }

                @NotNull
                public final List<String> getEvents() {
                    return this.events;
                }

                @NotNull
                public final List<String> getMethods() {
                    return this.methods;
                }

                public int hashCode() {
                    List<String> list = this.chains;
                    return this.events.hashCode() + androidx.compose.animation.core.a.j(this.methods, (list == null ? 0 : list.hashCode()) * 31, 31);
                }

                @NotNull
                public String toString() {
                    List<String> list = this.chains;
                    List<String> list2 = this.methods;
                    List<String> list3 = this.events;
                    StringBuilder sb = new StringBuilder("Proposal(chains=");
                    sb.append(list);
                    sb.append(", methods=");
                    sb.append(list2);
                    sb.append(", events=");
                    return C0118y.h(")", list3, sb);
                }

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Proposal(@Nullable List<String> list, @NotNull List<String> list2, @NotNull List<String> list3) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(list2, "methods");
                    Intrinsics.checkNotNullParameter(list3, "events");
                    this.chains = list;
                    this.methods = list2;
                    this.events = list3;
                }
            }

            @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BC\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\b\u0010\tJ\u0011\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003JK\u0010\u0013\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0004HÖ\u0001R\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000b¨\u0006\u001b"}, d2 = {"Lcom/reown/sign/client/Sign$Model$Namespace$Session;", "Lcom/reown/sign/client/Sign$Model$Namespace;", "chains", "", "", "accounts", "methods", "events", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getChains", "()Ljava/util/List;", "getAccounts", "getMethods", "getEvents", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Session extends Namespace {
                @NotNull
                private final List<String> accounts;
                @Nullable
                private final List<String> chains;
                @NotNull
                private final List<String> events;
                @NotNull
                private final List<String> methods;

                /* JADX INFO: this call moved to the top of the method (can break code semantics) */
                public /* synthetic */ Session(List list, List list2, List list3, List list4, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                    this((i3 & 1) != 0 ? null : list, list2, list3, list4);
                }

                public static /* synthetic */ Session copy$default(Session session, List<String> list, List<String> list2, List<String> list3, List<String> list4, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        list = session.chains;
                    }
                    if ((i3 & 2) != 0) {
                        list2 = session.accounts;
                    }
                    if ((i3 & 4) != 0) {
                        list3 = session.methods;
                    }
                    if ((i3 & 8) != 0) {
                        list4 = session.events;
                    }
                    return session.copy(list, list2, list3, list4);
                }

                @Nullable
                public final List<String> component1() {
                    return this.chains;
                }

                @NotNull
                public final List<String> component2() {
                    return this.accounts;
                }

                @NotNull
                public final List<String> component3() {
                    return this.methods;
                }

                @NotNull
                public final List<String> component4() {
                    return this.events;
                }

                @NotNull
                public final Session copy(@Nullable List<String> list, @NotNull List<String> list2, @NotNull List<String> list3, @NotNull List<String> list4) {
                    Intrinsics.checkNotNullParameter(list2, "accounts");
                    Intrinsics.checkNotNullParameter(list3, "methods");
                    Intrinsics.checkNotNullParameter(list4, "events");
                    return new Session(list, list2, list3, list4);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof Session)) {
                        return false;
                    }
                    Session session = (Session) obj;
                    return Intrinsics.areEqual((Object) this.chains, (Object) session.chains) && Intrinsics.areEqual((Object) this.accounts, (Object) session.accounts) && Intrinsics.areEqual((Object) this.methods, (Object) session.methods) && Intrinsics.areEqual((Object) this.events, (Object) session.events);
                }

                @NotNull
                public final List<String> getAccounts() {
                    return this.accounts;
                }

                @Nullable
                public final List<String> getChains() {
                    return this.chains;
                }

                @NotNull
                public final List<String> getEvents() {
                    return this.events;
                }

                @NotNull
                public final List<String> getMethods() {
                    return this.methods;
                }

                public int hashCode() {
                    List<String> list = this.chains;
                    return this.events.hashCode() + androidx.compose.animation.core.a.j(this.methods, androidx.compose.animation.core.a.j(this.accounts, (list == null ? 0 : list.hashCode()) * 31, 31), 31);
                }

                @NotNull
                public String toString() {
                    List<String> list = this.chains;
                    List<String> list2 = this.accounts;
                    List<String> list3 = this.methods;
                    List<String> list4 = this.events;
                    return "Session(chains=" + list + ", accounts=" + list2 + ", methods=" + list3 + ", events=" + list4 + ")";
                }

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Session(@Nullable List<String> list, @NotNull List<String> list2, @NotNull List<String> list3, @NotNull List<String> list4) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(list2, "accounts");
                    Intrinsics.checkNotNullParameter(list3, "methods");
                    Intrinsics.checkNotNullParameter(list4, "events");
                    this.chains = list;
                    this.accounts = list2;
                    this.methods = list3;
                    this.events = list4;
                }
            }

            public /* synthetic */ Namespace(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Namespace() {
                super((DefaultConstructorMarker) null);
            }
        }

        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/reown/sign/client/Sign$Model$Pairing;", "Lcom/reown/sign/client/Sign$Model;", "topic", "", "metaData", "Lcom/reown/android/Core$Model$AppMetaData;", "<init>", "(Ljava/lang/String;Lcom/reown/android/Core$Model$AppMetaData;)V", "getTopic", "()Ljava/lang/String;", "getMetaData", "()Lcom/reown/android/Core$Model$AppMetaData;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Pairing extends Model {
            @Nullable
            private final Core.Model.AppMetaData metaData;
            @NotNull
            private final String topic;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Pairing(@NotNull String str, @Nullable Core.Model.AppMetaData appMetaData) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                this.topic = str;
                this.metaData = appMetaData;
            }

            public static /* synthetic */ Pairing copy$default(Pairing pairing, String str, Core.Model.AppMetaData appMetaData, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = pairing.topic;
                }
                if ((i3 & 2) != 0) {
                    appMetaData = pairing.metaData;
                }
                return pairing.copy(str, appMetaData);
            }

            @NotNull
            public final String component1() {
                return this.topic;
            }

            @Nullable
            public final Core.Model.AppMetaData component2() {
                return this.metaData;
            }

            @NotNull
            public final Pairing copy(@NotNull String str, @Nullable Core.Model.AppMetaData appMetaData) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                return new Pairing(str, appMetaData);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Pairing)) {
                    return false;
                }
                Pairing pairing = (Pairing) obj;
                return Intrinsics.areEqual((Object) this.topic, (Object) pairing.topic) && Intrinsics.areEqual((Object) this.metaData, (Object) pairing.metaData);
            }

            @Nullable
            public final Core.Model.AppMetaData getMetaData() {
                return this.metaData;
            }

            @NotNull
            public final String getTopic() {
                return this.topic;
            }

            public int hashCode() {
                int hashCode = this.topic.hashCode() * 31;
                Core.Model.AppMetaData appMetaData = this.metaData;
                return hashCode + (appMetaData == null ? 0 : appMetaData.hashCode());
            }

            @NotNull
            public String toString() {
                String str = this.topic;
                Core.Model.AppMetaData appMetaData = this.metaData;
                return "Pairing(topic=" + str + ", metaData=" + appMetaData + ")";
            }
        }

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b(\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001Bw\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u000b\u001a\u00020\u0004\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0004\u0012\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0004HÆ\u0003J\t\u0010\"\u001a\u00020\u0004HÆ\u0003J\t\u0010#\u001a\u00020\u0004HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\t\u0010'\u001a\u00020\u0004HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0011\u0010*\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0001\u0010+\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u000b\u001a\u00020\u00042\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00042\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010/HÖ\u0003J\t\u00100\u001a\u000201HÖ\u0001J\t\u00102\u001a\u00020\u0004HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\"\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0012\"\u0004\b\u001e\u0010\u001f¨\u00063"}, d2 = {"Lcom/reown/sign/client/Sign$Model$PayloadParams;", "Lcom/reown/sign/client/Sign$Model;", "chains", "", "", "domain", "nonce", "aud", "type", "nbf", "exp", "iat", "statement", "requestId", "resources", "<init>", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getChains", "()Ljava/util/List;", "getDomain", "()Ljava/lang/String;", "getNonce", "getAud", "getType", "getNbf", "getExp", "getIat", "getStatement", "getRequestId", "getResources", "setResources", "(Ljava/util/List;)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class PayloadParams extends Model {
            @NotNull
            private final String aud;
            @NotNull
            private final List<String> chains;
            @NotNull
            private final String domain;
            @Nullable
            private final String exp;
            @NotNull
            private final String iat;
            @Nullable
            private final String nbf;
            @NotNull
            private final String nonce;
            @Nullable
            private final String requestId;
            @Nullable
            private List<String> resources;
            @Nullable
            private final String statement;
            @Nullable
            private final String type;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public PayloadParams(@NotNull List<String> list, @NotNull String str, @NotNull String str2, @NotNull String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @NotNull String str7, @Nullable String str8, @Nullable String str9, @Nullable List<String> list2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(list, "chains");
                Intrinsics.checkNotNullParameter(str, "domain");
                Intrinsics.checkNotNullParameter(str2, "nonce");
                Intrinsics.checkNotNullParameter(str3, "aud");
                Intrinsics.checkNotNullParameter(str7, "iat");
                this.chains = list;
                this.domain = str;
                this.nonce = str2;
                this.aud = str3;
                this.type = str4;
                this.nbf = str5;
                this.exp = str6;
                this.iat = str7;
                this.statement = str8;
                this.requestId = str9;
                this.resources = list2;
            }

            public static /* synthetic */ PayloadParams copy$default(PayloadParams payloadParams, List list, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, List list2, int i3, Object obj) {
                PayloadParams payloadParams2 = payloadParams;
                int i4 = i3;
                return payloadParams.copy((i4 & 1) != 0 ? payloadParams2.chains : list, (i4 & 2) != 0 ? payloadParams2.domain : str, (i4 & 4) != 0 ? payloadParams2.nonce : str2, (i4 & 8) != 0 ? payloadParams2.aud : str3, (i4 & 16) != 0 ? payloadParams2.type : str4, (i4 & 32) != 0 ? payloadParams2.nbf : str5, (i4 & 64) != 0 ? payloadParams2.exp : str6, (i4 & 128) != 0 ? payloadParams2.iat : str7, (i4 & 256) != 0 ? payloadParams2.statement : str8, (i4 & 512) != 0 ? payloadParams2.requestId : str9, (i4 & 1024) != 0 ? payloadParams2.resources : list2);
            }

            @NotNull
            public final List<String> component1() {
                return this.chains;
            }

            @Nullable
            public final String component10() {
                return this.requestId;
            }

            @Nullable
            public final List<String> component11() {
                return this.resources;
            }

            @NotNull
            public final String component2() {
                return this.domain;
            }

            @NotNull
            public final String component3() {
                return this.nonce;
            }

            @NotNull
            public final String component4() {
                return this.aud;
            }

            @Nullable
            public final String component5() {
                return this.type;
            }

            @Nullable
            public final String component6() {
                return this.nbf;
            }

            @Nullable
            public final String component7() {
                return this.exp;
            }

            @NotNull
            public final String component8() {
                return this.iat;
            }

            @Nullable
            public final String component9() {
                return this.statement;
            }

            @NotNull
            public final PayloadParams copy(@NotNull List<String> list, @NotNull String str, @NotNull String str2, @NotNull String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @NotNull String str7, @Nullable String str8, @Nullable String str9, @Nullable List<String> list2) {
                Intrinsics.checkNotNullParameter(list, "chains");
                Intrinsics.checkNotNullParameter(str, "domain");
                String str10 = str2;
                Intrinsics.checkNotNullParameter(str10, "nonce");
                String str11 = str3;
                Intrinsics.checkNotNullParameter(str11, "aud");
                String str12 = str7;
                Intrinsics.checkNotNullParameter(str12, "iat");
                return new PayloadParams(list, str, str10, str11, str4, str5, str6, str12, str8, str9, list2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof PayloadParams)) {
                    return false;
                }
                PayloadParams payloadParams = (PayloadParams) obj;
                return Intrinsics.areEqual((Object) this.chains, (Object) payloadParams.chains) && Intrinsics.areEqual((Object) this.domain, (Object) payloadParams.domain) && Intrinsics.areEqual((Object) this.nonce, (Object) payloadParams.nonce) && Intrinsics.areEqual((Object) this.aud, (Object) payloadParams.aud) && Intrinsics.areEqual((Object) this.type, (Object) payloadParams.type) && Intrinsics.areEqual((Object) this.nbf, (Object) payloadParams.nbf) && Intrinsics.areEqual((Object) this.exp, (Object) payloadParams.exp) && Intrinsics.areEqual((Object) this.iat, (Object) payloadParams.iat) && Intrinsics.areEqual((Object) this.statement, (Object) payloadParams.statement) && Intrinsics.areEqual((Object) this.requestId, (Object) payloadParams.requestId) && Intrinsics.areEqual((Object) this.resources, (Object) payloadParams.resources);
            }

            @NotNull
            public final String getAud() {
                return this.aud;
            }

            @NotNull
            public final List<String> getChains() {
                return this.chains;
            }

            @NotNull
            public final String getDomain() {
                return this.domain;
            }

            @Nullable
            public final String getExp() {
                return this.exp;
            }

            @NotNull
            public final String getIat() {
                return this.iat;
            }

            @Nullable
            public final String getNbf() {
                return this.nbf;
            }

            @NotNull
            public final String getNonce() {
                return this.nonce;
            }

            @Nullable
            public final String getRequestId() {
                return this.requestId;
            }

            @Nullable
            public final List<String> getResources() {
                return this.resources;
            }

            @Nullable
            public final String getStatement() {
                return this.statement;
            }

            @Nullable
            public final String getType() {
                return this.type;
            }

            public int hashCode() {
                int i3 = androidx.compose.animation.core.a.i(this.aud, androidx.compose.animation.core.a.i(this.nonce, androidx.compose.animation.core.a.i(this.domain, this.chains.hashCode() * 31, 31), 31), 31);
                String str = this.type;
                int i4 = 0;
                int hashCode = (i3 + (str == null ? 0 : str.hashCode())) * 31;
                String str2 = this.nbf;
                int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
                String str3 = this.exp;
                int i5 = androidx.compose.animation.core.a.i(this.iat, (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31, 31);
                String str4 = this.statement;
                int hashCode3 = (i5 + (str4 == null ? 0 : str4.hashCode())) * 31;
                String str5 = this.requestId;
                int hashCode4 = (hashCode3 + (str5 == null ? 0 : str5.hashCode())) * 31;
                List<String> list = this.resources;
                if (list != null) {
                    i4 = list.hashCode();
                }
                return hashCode4 + i4;
            }

            public final void setResources(@Nullable List<String> list) {
                this.resources = list;
            }

            @NotNull
            public String toString() {
                List<String> list = this.chains;
                String str = this.domain;
                String str2 = this.nonce;
                String str3 = this.aud;
                String str4 = this.type;
                String str5 = this.nbf;
                String str6 = this.exp;
                String str7 = this.iat;
                String str8 = this.statement;
                String str9 = this.requestId;
                List<String> list2 = this.resources;
                StringBuilder sb = new StringBuilder("PayloadParams(chains=");
                sb.append(list);
                sb.append(", domain=");
                sb.append(str);
                sb.append(", nonce=");
                b.w(sb, str2, ", aud=", str3, ", type=");
                b.w(sb, str4, ", nbf=", str5, ", exp=");
                b.w(sb, str6, ", iat=", str7, ", statement=");
                b.w(sb, str8, ", requestId=", str9, ", resources=");
                return C0118y.h(")", list2, sb);
            }
        }

        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J=\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bHÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000e¨\u0006\u001f"}, d2 = {"Lcom/reown/sign/client/Sign$Model$PendingRequest;", "Lcom/reown/sign/client/Sign$Model;", "requestId", "", "topic", "", "method", "chainId", "params", "<init>", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getRequestId", "()J", "getTopic", "()Ljava/lang/String;", "getMethod", "getChainId", "getParams", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class PendingRequest extends Model {
            @Nullable
            private final String chainId;
            @NotNull
            private final String method;
            @NotNull
            private final String params;
            private final long requestId;
            @NotNull
            private final String topic;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public PendingRequest(long j2, @NotNull String str, @NotNull String str2, @Nullable String str3, @NotNull String str4) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
                Intrinsics.checkNotNullParameter(str4, "params");
                this.requestId = j2;
                this.topic = str;
                this.method = str2;
                this.chainId = str3;
                this.params = str4;
            }

            public static /* synthetic */ PendingRequest copy$default(PendingRequest pendingRequest, long j2, String str, String str2, String str3, String str4, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    j2 = pendingRequest.requestId;
                }
                long j3 = j2;
                if ((i3 & 2) != 0) {
                    str = pendingRequest.topic;
                }
                String str5 = str;
                if ((i3 & 4) != 0) {
                    str2 = pendingRequest.method;
                }
                String str6 = str2;
                if ((i3 & 8) != 0) {
                    str3 = pendingRequest.chainId;
                }
                String str7 = str3;
                if ((i3 & 16) != 0) {
                    str4 = pendingRequest.params;
                }
                return pendingRequest.copy(j3, str5, str6, str7, str4);
            }

            public final long component1() {
                return this.requestId;
            }

            @NotNull
            public final String component2() {
                return this.topic;
            }

            @NotNull
            public final String component3() {
                return this.method;
            }

            @Nullable
            public final String component4() {
                return this.chainId;
            }

            @NotNull
            public final String component5() {
                return this.params;
            }

            @NotNull
            public final PendingRequest copy(long j2, @NotNull String str, @NotNull String str2, @Nullable String str3, @NotNull String str4) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
                Intrinsics.checkNotNullParameter(str4, "params");
                return new PendingRequest(j2, str, str2, str3, str4);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof PendingRequest)) {
                    return false;
                }
                PendingRequest pendingRequest = (PendingRequest) obj;
                return this.requestId == pendingRequest.requestId && Intrinsics.areEqual((Object) this.topic, (Object) pendingRequest.topic) && Intrinsics.areEqual((Object) this.method, (Object) pendingRequest.method) && Intrinsics.areEqual((Object) this.chainId, (Object) pendingRequest.chainId) && Intrinsics.areEqual((Object) this.params, (Object) pendingRequest.params);
            }

            @Nullable
            public final String getChainId() {
                return this.chainId;
            }

            @NotNull
            public final String getMethod() {
                return this.method;
            }

            @NotNull
            public final String getParams() {
                return this.params;
            }

            public final long getRequestId() {
                return this.requestId;
            }

            @NotNull
            public final String getTopic() {
                return this.topic;
            }

            public int hashCode() {
                int i3 = androidx.compose.animation.core.a.i(this.method, androidx.compose.animation.core.a.i(this.topic, Long.hashCode(this.requestId) * 31, 31), 31);
                String str = this.chainId;
                return this.params.hashCode() + ((i3 + (str == null ? 0 : str.hashCode())) * 31);
            }

            @NotNull
            public String toString() {
                long j2 = this.requestId;
                String str = this.topic;
                String str2 = this.method;
                String str3 = this.chainId;
                String str4 = this.params;
                StringBuilder v2 = androidx.work.impl.a.v(j2, "PendingRequest(requestId=", ", topic=", str);
                b.w(v2, ", method=", str2, ", chainId=", str3);
                return C0118y.j(v2, ", params=", str4, ")");
            }
        }

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/sign/client/Sign$Model$Ping;", "Lcom/reown/sign/client/Sign$Model;", "<init>", "()V", "Success", "Error", "Lcom/reown/sign/client/Sign$Model$Ping$Error;", "Lcom/reown/sign/client/Sign$Model$Ping$Success;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static abstract class Ping extends Model {

            @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/reown/sign/client/Sign$Model$Ping$Error;", "Lcom/reown/sign/client/Sign$Model$Ping;", "error", "", "<init>", "(Ljava/lang/Throwable;)V", "getError", "()Ljava/lang/Throwable;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Error extends Ping {
                @NotNull
                private final Throwable error;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Error(@NotNull Throwable th) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(th, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
                    this.error = th;
                }

                public static /* synthetic */ Error copy$default(Error error2, Throwable th, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        th = error2.error;
                    }
                    return error2.copy(th);
                }

                @NotNull
                public final Throwable component1() {
                    return this.error;
                }

                @NotNull
                public final Error copy(@NotNull Throwable th) {
                    Intrinsics.checkNotNullParameter(th, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
                    return new Error(th);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return (obj instanceof Error) && Intrinsics.areEqual((Object) this.error, (Object) ((Error) obj).error);
                }

                @NotNull
                public final Throwable getError() {
                    return this.error;
                }

                public int hashCode() {
                    return this.error.hashCode();
                }

                @NotNull
                public String toString() {
                    Throwable th = this.error;
                    return "Error(error=" + th + ")";
                }
            }

            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/sign/client/Sign$Model$Ping$Success;", "Lcom/reown/sign/client/Sign$Model$Ping;", "topic", "", "<init>", "(Ljava/lang/String;)V", "getTopic", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Success extends Ping {
                @NotNull
                private final String topic;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Success(@NotNull String str) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                    this.topic = str;
                }

                public static /* synthetic */ Success copy$default(Success success, String str, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        str = success.topic;
                    }
                    return success.copy(str);
                }

                @NotNull
                public final String component1() {
                    return this.topic;
                }

                @NotNull
                public final Success copy(@NotNull String str) {
                    Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                    return new Success(str);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return (obj instanceof Success) && Intrinsics.areEqual((Object) this.topic, (Object) ((Success) obj).topic);
                }

                @NotNull
                public final String getTopic() {
                    return this.topic;
                }

                public int hashCode() {
                    return this.topic.hashCode();
                }

                @NotNull
                public String toString() {
                    return a.l("Success(topic=", this.topic, ")");
                }
            }

            public /* synthetic */ Ping(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Ping() {
                super((DefaultConstructorMarker) null);
            }
        }

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/sign/client/Sign$Model$ProposedSequence;", "Lcom/reown/sign/client/Sign$Model;", "<init>", "()V", "Pairing", "Session", "Lcom/reown/sign/client/Sign$Model$ProposedSequence$Pairing;", "Lcom/reown/sign/client/Sign$Model$ProposedSequence$Session;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static abstract class ProposedSequence extends Model {

            @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/reown/sign/client/Sign$Model$ProposedSequence$Pairing;", "Lcom/reown/sign/client/Sign$Model$ProposedSequence;", "uri", "", "<init>", "(Ljava/lang/String;)V", "getUri", "()Ljava/lang/String;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Pairing extends ProposedSequence {
                @NotNull
                private final String uri;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Pairing(@NotNull String str) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, "uri");
                    this.uri = str;
                }

                @NotNull
                public final String getUri() {
                    return this.uri;
                }
            }

            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/reown/sign/client/Sign$Model$ProposedSequence$Session;", "Lcom/reown/sign/client/Sign$Model$ProposedSequence;", "<init>", "()V", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Session extends ProposedSequence {
                @NotNull
                public static final Session INSTANCE = new Session();

                private Session() {
                    super((DefaultConstructorMarker) null);
                }
            }

            public /* synthetic */ ProposedSequence(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private ProposedSequence() {
                super((DefaultConstructorMarker) null);
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/reown/sign/client/Sign$Model$RejectedSession;", "Lcom/reown/sign/client/Sign$Model;", "topic", "", "reason", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getTopic", "()Ljava/lang/String;", "getReason", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class RejectedSession extends Model {
            @NotNull
            private final String reason;
            @NotNull
            private final String topic;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public RejectedSession(@NotNull String str, @NotNull String str2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                Intrinsics.checkNotNullParameter(str2, "reason");
                this.topic = str;
                this.reason = str2;
            }

            public static /* synthetic */ RejectedSession copy$default(RejectedSession rejectedSession, String str, String str2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = rejectedSession.topic;
                }
                if ((i3 & 2) != 0) {
                    str2 = rejectedSession.reason;
                }
                return rejectedSession.copy(str, str2);
            }

            @NotNull
            public final String component1() {
                return this.topic;
            }

            @NotNull
            public final String component2() {
                return this.reason;
            }

            @NotNull
            public final RejectedSession copy(@NotNull String str, @NotNull String str2) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                Intrinsics.checkNotNullParameter(str2, "reason");
                return new RejectedSession(str, str2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof RejectedSession)) {
                    return false;
                }
                RejectedSession rejectedSession = (RejectedSession) obj;
                return Intrinsics.areEqual((Object) this.topic, (Object) rejectedSession.topic) && Intrinsics.areEqual((Object) this.reason, (Object) rejectedSession.reason);
            }

            @NotNull
            public final String getReason() {
                return this.reason;
            }

            @NotNull
            public final String getTopic() {
                return this.topic;
            }

            public int hashCode() {
                return this.reason.hashCode() + (this.topic.hashCode() * 31);
            }

            @NotNull
            public String toString() {
                return C0118y.g("RejectedSession(topic=", this.topic, ", reason=", this.reason, ")");
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u001f\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/reown/sign/client/Sign$Model$RelayProtocolOptions;", "Lcom/reown/sign/client/Sign$Model;", "protocol", "", "data", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getProtocol", "()Ljava/lang/String;", "getData", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        @Deprecated(message = "RelayProtocolOptions is deprecated")
        public static final class RelayProtocolOptions extends Model {
            @Nullable
            private final String data;
            @NotNull
            private final String protocol;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public RelayProtocolOptions(@NotNull String str, @Nullable String str2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "protocol");
                this.protocol = str;
                this.data = str2;
            }

            public static /* synthetic */ RelayProtocolOptions copy$default(RelayProtocolOptions relayProtocolOptions, String str, String str2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = relayProtocolOptions.protocol;
                }
                if ((i3 & 2) != 0) {
                    str2 = relayProtocolOptions.data;
                }
                return relayProtocolOptions.copy(str, str2);
            }

            @NotNull
            public final String component1() {
                return this.protocol;
            }

            @Nullable
            public final String component2() {
                return this.data;
            }

            @NotNull
            public final RelayProtocolOptions copy(@NotNull String str, @Nullable String str2) {
                Intrinsics.checkNotNullParameter(str, "protocol");
                return new RelayProtocolOptions(str, str2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof RelayProtocolOptions)) {
                    return false;
                }
                RelayProtocolOptions relayProtocolOptions = (RelayProtocolOptions) obj;
                return Intrinsics.areEqual((Object) this.protocol, (Object) relayProtocolOptions.protocol) && Intrinsics.areEqual((Object) this.data, (Object) relayProtocolOptions.data);
            }

            @Nullable
            public final String getData() {
                return this.data;
            }

            @NotNull
            public final String getProtocol() {
                return this.protocol;
            }

            public int hashCode() {
                int hashCode = this.protocol.hashCode() * 31;
                String str = this.data;
                return hashCode + (str == null ? 0 : str.hashCode());
            }

            @NotNull
            public String toString() {
                return C0118y.g("RelayProtocolOptions(protocol=", this.protocol, ", data=", this.data, ")");
            }

            /* JADX INFO: this call moved to the top of the method (can break code semantics) */
            public /* synthetic */ RelayProtocolOptions(String str, String str2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                this(str, (i3 & 2) != 0 ? null : str2);
            }
        }

        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J;\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bHÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000e¨\u0006\u001f"}, d2 = {"Lcom/reown/sign/client/Sign$Model$SentRequest;", "Lcom/reown/sign/client/Sign$Params;", "requestId", "", "sessionTopic", "", "method", "params", "chainId", "<init>", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getRequestId", "()J", "getSessionTopic", "()Ljava/lang/String;", "getMethod", "getParams", "getChainId", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class SentRequest extends Params {
            @NotNull
            private final String chainId;
            @NotNull
            private final String method;
            @NotNull
            private final String params;
            private final long requestId;
            @NotNull
            private final String sessionTopic;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public SentRequest(long j2, @NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "sessionTopic");
                Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
                Intrinsics.checkNotNullParameter(str3, "params");
                Intrinsics.checkNotNullParameter(str4, "chainId");
                this.requestId = j2;
                this.sessionTopic = str;
                this.method = str2;
                this.params = str3;
                this.chainId = str4;
            }

            public static /* synthetic */ SentRequest copy$default(SentRequest sentRequest, long j2, String str, String str2, String str3, String str4, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    j2 = sentRequest.requestId;
                }
                long j3 = j2;
                if ((i3 & 2) != 0) {
                    str = sentRequest.sessionTopic;
                }
                String str5 = str;
                if ((i3 & 4) != 0) {
                    str2 = sentRequest.method;
                }
                String str6 = str2;
                if ((i3 & 8) != 0) {
                    str3 = sentRequest.params;
                }
                String str7 = str3;
                if ((i3 & 16) != 0) {
                    str4 = sentRequest.chainId;
                }
                return sentRequest.copy(j3, str5, str6, str7, str4);
            }

            public final long component1() {
                return this.requestId;
            }

            @NotNull
            public final String component2() {
                return this.sessionTopic;
            }

            @NotNull
            public final String component3() {
                return this.method;
            }

            @NotNull
            public final String component4() {
                return this.params;
            }

            @NotNull
            public final String component5() {
                return this.chainId;
            }

            @NotNull
            public final SentRequest copy(long j2, @NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4) {
                Intrinsics.checkNotNullParameter(str, "sessionTopic");
                Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
                Intrinsics.checkNotNullParameter(str3, "params");
                Intrinsics.checkNotNullParameter(str4, "chainId");
                return new SentRequest(j2, str, str2, str3, str4);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof SentRequest)) {
                    return false;
                }
                SentRequest sentRequest = (SentRequest) obj;
                return this.requestId == sentRequest.requestId && Intrinsics.areEqual((Object) this.sessionTopic, (Object) sentRequest.sessionTopic) && Intrinsics.areEqual((Object) this.method, (Object) sentRequest.method) && Intrinsics.areEqual((Object) this.params, (Object) sentRequest.params) && Intrinsics.areEqual((Object) this.chainId, (Object) sentRequest.chainId);
            }

            @NotNull
            public final String getChainId() {
                return this.chainId;
            }

            @NotNull
            public final String getMethod() {
                return this.method;
            }

            @NotNull
            public final String getParams() {
                return this.params;
            }

            public final long getRequestId() {
                return this.requestId;
            }

            @NotNull
            public final String getSessionTopic() {
                return this.sessionTopic;
            }

            public int hashCode() {
                return this.chainId.hashCode() + androidx.compose.animation.core.a.i(this.params, androidx.compose.animation.core.a.i(this.method, androidx.compose.animation.core.a.i(this.sessionTopic, Long.hashCode(this.requestId) * 31, 31), 31), 31);
            }

            @NotNull
            public String toString() {
                long j2 = this.requestId;
                String str = this.sessionTopic;
                String str2 = this.method;
                String str3 = this.params;
                String str4 = this.chainId;
                StringBuilder v2 = androidx.work.impl.a.v(j2, "SentRequest(requestId=", ", sessionTopic=", str);
                b.w(v2, ", method=", str2, ", params=", str3);
                return C0118y.j(v2, ", chainId=", str4, ")");
            }
        }

        @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001Bg\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t0\b\u0012\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\f0\b\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0006HÆ\u0003J\u0015\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t0\bHÆ\u0003J\u0017\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t\u0018\u00010\bHÆ\u0003J\u0015\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\f0\bHÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u000eHÆ\u0003Jw\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t0\b2\u0016\b\u0002\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\u0014\b\u0002\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\f0\b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eHÆ\u0001J\u0013\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010)HÖ\u0003J\t\u0010*\u001a\u00020+HÖ\u0001J\t\u0010,\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001f\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u001d\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\f0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0012¨\u0006-"}, d2 = {"Lcom/reown/sign/client/Sign$Model$Session;", "Lcom/reown/sign/client/Sign$Model;", "pairingTopic", "", "topic", "expiry", "", "requiredNamespaces", "", "Lcom/reown/sign/client/Sign$Model$Namespace$Proposal;", "optionalNamespaces", "namespaces", "Lcom/reown/sign/client/Sign$Model$Namespace$Session;", "metaData", "Lcom/reown/android/Core$Model$AppMetaData;", "<init>", "(Ljava/lang/String;Ljava/lang/String;JLjava/util/Map;Ljava/util/Map;Ljava/util/Map;Lcom/reown/android/Core$Model$AppMetaData;)V", "getPairingTopic", "()Ljava/lang/String;", "getTopic", "getExpiry", "()J", "getRequiredNamespaces", "()Ljava/util/Map;", "getOptionalNamespaces", "getNamespaces", "getMetaData", "()Lcom/reown/android/Core$Model$AppMetaData;", "redirect", "getRedirect", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Session extends Model {
            private final long expiry;
            @Nullable
            private final Core.Model.AppMetaData metaData;
            @NotNull
            private final Map<String, Namespace.Session> namespaces;
            @Nullable
            private final Map<String, Namespace.Proposal> optionalNamespaces;
            @NotNull
            private final String pairingTopic;
            @Nullable
            private final String redirect;
            @NotNull
            private final Map<String, Namespace.Proposal> requiredNamespaces;
            @NotNull
            private final String topic;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Session(@NotNull String str, @NotNull String str2, long j2, @NotNull Map<String, Namespace.Proposal> map, @Nullable Map<String, Namespace.Proposal> map2, @NotNull Map<String, Namespace.Session> map3, @Nullable Core.Model.AppMetaData appMetaData) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "pairingTopic");
                Intrinsics.checkNotNullParameter(str2, PushMessagingService.KEY_TOPIC);
                Intrinsics.checkNotNullParameter(map, "requiredNamespaces");
                Intrinsics.checkNotNullParameter(map3, "namespaces");
                String str3 = null;
                this.pairingTopic = str;
                this.topic = str2;
                this.expiry = j2;
                this.requiredNamespaces = map;
                this.optionalNamespaces = map2;
                this.namespaces = map3;
                this.metaData = appMetaData;
                this.redirect = appMetaData != null ? appMetaData.getRedirect() : str3;
            }

            public static /* synthetic */ Session copy$default(Session session, String str, String str2, long j2, Map map, Map map2, Map map3, Core.Model.AppMetaData appMetaData, int i3, Object obj) {
                Session session2 = session;
                return session.copy((i3 & 1) != 0 ? session2.pairingTopic : str, (i3 & 2) != 0 ? session2.topic : str2, (i3 & 4) != 0 ? session2.expiry : j2, (i3 & 8) != 0 ? session2.requiredNamespaces : map, (i3 & 16) != 0 ? session2.optionalNamespaces : map2, (i3 & 32) != 0 ? session2.namespaces : map3, (i3 & 64) != 0 ? session2.metaData : appMetaData);
            }

            @NotNull
            public final String component1() {
                return this.pairingTopic;
            }

            @NotNull
            public final String component2() {
                return this.topic;
            }

            public final long component3() {
                return this.expiry;
            }

            @NotNull
            public final Map<String, Namespace.Proposal> component4() {
                return this.requiredNamespaces;
            }

            @Nullable
            public final Map<String, Namespace.Proposal> component5() {
                return this.optionalNamespaces;
            }

            @NotNull
            public final Map<String, Namespace.Session> component6() {
                return this.namespaces;
            }

            @Nullable
            public final Core.Model.AppMetaData component7() {
                return this.metaData;
            }

            @NotNull
            public final Session copy(@NotNull String str, @NotNull String str2, long j2, @NotNull Map<String, Namespace.Proposal> map, @Nullable Map<String, Namespace.Proposal> map2, @NotNull Map<String, Namespace.Session> map3, @Nullable Core.Model.AppMetaData appMetaData) {
                Intrinsics.checkNotNullParameter(str, "pairingTopic");
                Intrinsics.checkNotNullParameter(str2, PushMessagingService.KEY_TOPIC);
                Intrinsics.checkNotNullParameter(map, "requiredNamespaces");
                Map<String, Namespace.Session> map4 = map3;
                Intrinsics.checkNotNullParameter(map4, "namespaces");
                return new Session(str, str2, j2, map, map2, map4, appMetaData);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Session)) {
                    return false;
                }
                Session session = (Session) obj;
                return Intrinsics.areEqual((Object) this.pairingTopic, (Object) session.pairingTopic) && Intrinsics.areEqual((Object) this.topic, (Object) session.topic) && this.expiry == session.expiry && Intrinsics.areEqual((Object) this.requiredNamespaces, (Object) session.requiredNamespaces) && Intrinsics.areEqual((Object) this.optionalNamespaces, (Object) session.optionalNamespaces) && Intrinsics.areEqual((Object) this.namespaces, (Object) session.namespaces) && Intrinsics.areEqual((Object) this.metaData, (Object) session.metaData);
            }

            public final long getExpiry() {
                return this.expiry;
            }

            @Nullable
            public final Core.Model.AppMetaData getMetaData() {
                return this.metaData;
            }

            @NotNull
            public final Map<String, Namespace.Session> getNamespaces() {
                return this.namespaces;
            }

            @Nullable
            public final Map<String, Namespace.Proposal> getOptionalNamespaces() {
                return this.optionalNamespaces;
            }

            @NotNull
            public final String getPairingTopic() {
                return this.pairingTopic;
            }

            @Nullable
            public final String getRedirect() {
                return this.redirect;
            }

            @NotNull
            public final Map<String, Namespace.Proposal> getRequiredNamespaces() {
                return this.requiredNamespaces;
            }

            @NotNull
            public final String getTopic() {
                return this.topic;
            }

            public int hashCode() {
                int d2 = b.d(this.requiredNamespaces, androidx.compose.animation.core.a.D(this.expiry, androidx.compose.animation.core.a.i(this.topic, this.pairingTopic.hashCode() * 31, 31), 31), 31);
                Map<String, Namespace.Proposal> map = this.optionalNamespaces;
                int i3 = 0;
                int d3 = b.d(this.namespaces, (d2 + (map == null ? 0 : map.hashCode())) * 31, 31);
                Core.Model.AppMetaData appMetaData = this.metaData;
                if (appMetaData != null) {
                    i3 = appMetaData.hashCode();
                }
                return d3 + i3;
            }

            @NotNull
            public String toString() {
                String str = this.pairingTopic;
                String str2 = this.topic;
                long j2 = this.expiry;
                Map<String, Namespace.Proposal> map = this.requiredNamespaces;
                Map<String, Namespace.Proposal> map2 = this.optionalNamespaces;
                Map<String, Namespace.Session> map3 = this.namespaces;
                Core.Model.AppMetaData appMetaData = this.metaData;
                StringBuilder l2 = C0118y.l("Session(pairingTopic=", str, ", topic=", str2, ", expiry=");
                l2.append(j2);
                l2.append(", requiredNamespaces=");
                l2.append(map);
                l2.append(", optionalNamespaces=");
                l2.append(map2);
                l2.append(", namespaces=");
                l2.append(map3);
                l2.append(", metaData=");
                l2.append(appMetaData);
                l2.append(")");
                return l2.toString();
            }
        }

        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001:\u0001\"B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0019\u001a\u00020\tHÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J;\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\t\u0010!\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000e¨\u0006#"}, d2 = {"Lcom/reown/sign/client/Sign$Model$SessionAuthenticate;", "", "id", "", "topic", "", "participant", "Lcom/reown/sign/client/Sign$Model$SessionAuthenticate$Participant;", "payloadParams", "Lcom/reown/sign/client/Sign$Model$PayloadParams;", "expiry", "<init>", "(JLjava/lang/String;Lcom/reown/sign/client/Sign$Model$SessionAuthenticate$Participant;Lcom/reown/sign/client/Sign$Model$PayloadParams;J)V", "getId", "()J", "getTopic", "()Ljava/lang/String;", "getParticipant", "()Lcom/reown/sign/client/Sign$Model$SessionAuthenticate$Participant;", "getPayloadParams", "()Lcom/reown/sign/client/Sign$Model$PayloadParams;", "getExpiry", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "Participant", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class SessionAuthenticate {
            private final long expiry;
            private final long id;
            @NotNull
            private final Participant participant;
            @NotNull
            private final PayloadParams payloadParams;
            @NotNull
            private final String topic;

            @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/reown/sign/client/Sign$Model$SessionAuthenticate$Participant;", "Lcom/reown/sign/client/Sign$Model;", "publicKey", "", "metadata", "Lcom/reown/android/Core$Model$AppMetaData;", "<init>", "(Ljava/lang/String;Lcom/reown/android/Core$Model$AppMetaData;)V", "getPublicKey", "()Ljava/lang/String;", "getMetadata", "()Lcom/reown/android/Core$Model$AppMetaData;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Participant extends Model {
                @Nullable
                private final Core.Model.AppMetaData metadata;
                @NotNull
                private final String publicKey;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Participant(@NotNull String str, @Nullable Core.Model.AppMetaData appMetaData) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, "publicKey");
                    this.publicKey = str;
                    this.metadata = appMetaData;
                }

                public static /* synthetic */ Participant copy$default(Participant participant, String str, Core.Model.AppMetaData appMetaData, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        str = participant.publicKey;
                    }
                    if ((i3 & 2) != 0) {
                        appMetaData = participant.metadata;
                    }
                    return participant.copy(str, appMetaData);
                }

                @NotNull
                public final String component1() {
                    return this.publicKey;
                }

                @Nullable
                public final Core.Model.AppMetaData component2() {
                    return this.metadata;
                }

                @NotNull
                public final Participant copy(@NotNull String str, @Nullable Core.Model.AppMetaData appMetaData) {
                    Intrinsics.checkNotNullParameter(str, "publicKey");
                    return new Participant(str, appMetaData);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof Participant)) {
                        return false;
                    }
                    Participant participant = (Participant) obj;
                    return Intrinsics.areEqual((Object) this.publicKey, (Object) participant.publicKey) && Intrinsics.areEqual((Object) this.metadata, (Object) participant.metadata);
                }

                @Nullable
                public final Core.Model.AppMetaData getMetadata() {
                    return this.metadata;
                }

                @NotNull
                public final String getPublicKey() {
                    return this.publicKey;
                }

                public int hashCode() {
                    int hashCode = this.publicKey.hashCode() * 31;
                    Core.Model.AppMetaData appMetaData = this.metadata;
                    return hashCode + (appMetaData == null ? 0 : appMetaData.hashCode());
                }

                @NotNull
                public String toString() {
                    String str = this.publicKey;
                    Core.Model.AppMetaData appMetaData = this.metadata;
                    return "Participant(publicKey=" + str + ", metadata=" + appMetaData + ")";
                }
            }

            public SessionAuthenticate(long j2, @NotNull String str, @NotNull Participant participant2, @NotNull PayloadParams payloadParams2, long j3) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                Intrinsics.checkNotNullParameter(participant2, "participant");
                Intrinsics.checkNotNullParameter(payloadParams2, "payloadParams");
                this.id = j2;
                this.topic = str;
                this.participant = participant2;
                this.payloadParams = payloadParams2;
                this.expiry = j3;
            }

            public static /* synthetic */ SessionAuthenticate copy$default(SessionAuthenticate sessionAuthenticate, long j2, String str, Participant participant2, PayloadParams payloadParams2, long j3, int i3, Object obj) {
                SessionAuthenticate sessionAuthenticate2 = sessionAuthenticate;
                return sessionAuthenticate.copy((i3 & 1) != 0 ? sessionAuthenticate2.id : j2, (i3 & 2) != 0 ? sessionAuthenticate2.topic : str, (i3 & 4) != 0 ? sessionAuthenticate2.participant : participant2, (i3 & 8) != 0 ? sessionAuthenticate2.payloadParams : payloadParams2, (i3 & 16) != 0 ? sessionAuthenticate2.expiry : j3);
            }

            public final long component1() {
                return this.id;
            }

            @NotNull
            public final String component2() {
                return this.topic;
            }

            @NotNull
            public final Participant component3() {
                return this.participant;
            }

            @NotNull
            public final PayloadParams component4() {
                return this.payloadParams;
            }

            public final long component5() {
                return this.expiry;
            }

            @NotNull
            public final SessionAuthenticate copy(long j2, @NotNull String str, @NotNull Participant participant2, @NotNull PayloadParams payloadParams2, long j3) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                Intrinsics.checkNotNullParameter(participant2, "participant");
                Intrinsics.checkNotNullParameter(payloadParams2, "payloadParams");
                return new SessionAuthenticate(j2, str, participant2, payloadParams2, j3);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof SessionAuthenticate)) {
                    return false;
                }
                SessionAuthenticate sessionAuthenticate = (SessionAuthenticate) obj;
                return this.id == sessionAuthenticate.id && Intrinsics.areEqual((Object) this.topic, (Object) sessionAuthenticate.topic) && Intrinsics.areEqual((Object) this.participant, (Object) sessionAuthenticate.participant) && Intrinsics.areEqual((Object) this.payloadParams, (Object) sessionAuthenticate.payloadParams) && this.expiry == sessionAuthenticate.expiry;
            }

            public final long getExpiry() {
                return this.expiry;
            }

            public final long getId() {
                return this.id;
            }

            @NotNull
            public final Participant getParticipant() {
                return this.participant;
            }

            @NotNull
            public final PayloadParams getPayloadParams() {
                return this.payloadParams;
            }

            @NotNull
            public final String getTopic() {
                return this.topic;
            }

            public int hashCode() {
                int i3 = androidx.compose.animation.core.a.i(this.topic, Long.hashCode(this.id) * 31, 31);
                int hashCode = this.payloadParams.hashCode();
                return Long.hashCode(this.expiry) + ((hashCode + ((this.participant.hashCode() + i3) * 31)) * 31);
            }

            @NotNull
            public String toString() {
                long j2 = this.id;
                String str = this.topic;
                Participant participant2 = this.participant;
                PayloadParams payloadParams2 = this.payloadParams;
                long j3 = this.expiry;
                StringBuilder v2 = androidx.work.impl.a.v(j2, "SessionAuthenticate(id=", ", topic=", str);
                v2.append(", participant=");
                v2.append(participant2);
                v2.append(", payloadParams=");
                v2.append(payloadParams2);
                v2.append(", expiry=");
                v2.append(j3);
                v2.append(")");
                return v2.toString();
            }
        }

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/sign/client/Sign$Model$SessionAuthenticateResponse;", "Lcom/reown/sign/client/Sign$Model;", "<init>", "()V", "Result", "Error", "Lcom/reown/sign/client/Sign$Model$SessionAuthenticateResponse$Error;", "Lcom/reown/sign/client/Sign$Model$SessionAuthenticateResponse$Result;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static abstract class SessionAuthenticateResponse extends Model {

            @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/reown/sign/client/Sign$Model$SessionAuthenticateResponse$Error;", "Lcom/reown/sign/client/Sign$Model$SessionAuthenticateResponse;", "id", "", "code", "", "message", "", "<init>", "(JILjava/lang/String;)V", "getId", "()J", "getCode", "()I", "getMessage", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Error extends SessionAuthenticateResponse {
                private final int code;
                private final long id;
                @NotNull
                private final String message;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Error(long j2, int i3, @NotNull String str) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                    this.id = j2;
                    this.code = i3;
                    this.message = str;
                }

                public static /* synthetic */ Error copy$default(Error error, long j2, int i3, String str, int i4, Object obj) {
                    if ((i4 & 1) != 0) {
                        j2 = error.id;
                    }
                    if ((i4 & 2) != 0) {
                        i3 = error.code;
                    }
                    if ((i4 & 4) != 0) {
                        str = error.message;
                    }
                    return error.copy(j2, i3, str);
                }

                public final long component1() {
                    return this.id;
                }

                public final int component2() {
                    return this.code;
                }

                @NotNull
                public final String component3() {
                    return this.message;
                }

                @NotNull
                public final Error copy(long j2, int i3, @NotNull String str) {
                    Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                    return new Error(j2, i3, str);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof Error)) {
                        return false;
                    }
                    Error error = (Error) obj;
                    return this.id == error.id && this.code == error.code && Intrinsics.areEqual((Object) this.message, (Object) error.message);
                }

                public final int getCode() {
                    return this.code;
                }

                public final long getId() {
                    return this.id;
                }

                @NotNull
                public final String getMessage() {
                    return this.message;
                }

                public int hashCode() {
                    return this.message.hashCode() + androidx.compose.animation.core.a.c(this.code, Long.hashCode(this.id) * 31, 31);
                }

                @NotNull
                public String toString() {
                    long j2 = this.id;
                    int i3 = this.code;
                    String str = this.message;
                    StringBuilder sb = new StringBuilder("Error(id=");
                    sb.append(j2);
                    sb.append(", code=");
                    sb.append(i3);
                    return C0118y.j(sb, ", message=", str, ")");
                }
            }

            @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\bHÆ\u0003J/\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001d"}, d2 = {"Lcom/reown/sign/client/Sign$Model$SessionAuthenticateResponse$Result;", "Lcom/reown/sign/client/Sign$Model$SessionAuthenticateResponse;", "id", "", "cacaos", "", "Lcom/reown/sign/client/Sign$Model$Cacao;", "session", "Lcom/reown/sign/client/Sign$Model$Session;", "<init>", "(JLjava/util/List;Lcom/reown/sign/client/Sign$Model$Session;)V", "getId", "()J", "getCacaos", "()Ljava/util/List;", "getSession", "()Lcom/reown/sign/client/Sign$Model$Session;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Result extends SessionAuthenticateResponse {
                @NotNull
                private final List<Cacao> cacaos;
                private final long id;
                @Nullable
                private final Session session;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Result(long j2, @NotNull List<Cacao> list, @Nullable Session session2) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(list, "cacaos");
                    this.id = j2;
                    this.cacaos = list;
                    this.session = session2;
                }

                public static /* synthetic */ Result copy$default(Result result, long j2, List<Cacao> list, Session session2, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        j2 = result.id;
                    }
                    if ((i3 & 2) != 0) {
                        list = result.cacaos;
                    }
                    if ((i3 & 4) != 0) {
                        session2 = result.session;
                    }
                    return result.copy(j2, list, session2);
                }

                public final long component1() {
                    return this.id;
                }

                @NotNull
                public final List<Cacao> component2() {
                    return this.cacaos;
                }

                @Nullable
                public final Session component3() {
                    return this.session;
                }

                @NotNull
                public final Result copy(long j2, @NotNull List<Cacao> list, @Nullable Session session2) {
                    Intrinsics.checkNotNullParameter(list, "cacaos");
                    return new Result(j2, list, session2);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof Result)) {
                        return false;
                    }
                    Result result = (Result) obj;
                    return this.id == result.id && Intrinsics.areEqual((Object) this.cacaos, (Object) result.cacaos) && Intrinsics.areEqual((Object) this.session, (Object) result.session);
                }

                @NotNull
                public final List<Cacao> getCacaos() {
                    return this.cacaos;
                }

                public final long getId() {
                    return this.id;
                }

                @Nullable
                public final Session getSession() {
                    return this.session;
                }

                public int hashCode() {
                    int j2 = androidx.compose.animation.core.a.j(this.cacaos, Long.hashCode(this.id) * 31, 31);
                    Session session2 = this.session;
                    return j2 + (session2 == null ? 0 : session2.hashCode());
                }

                @NotNull
                public String toString() {
                    long j2 = this.id;
                    List<Cacao> list = this.cacaos;
                    Session session2 = this.session;
                    return "Result(id=" + j2 + ", cacaos=" + list + ", session=" + session2 + ")";
                }
            }

            public /* synthetic */ SessionAuthenticateResponse(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private SessionAuthenticateResponse() {
                super((DefaultConstructorMarker) null);
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/reown/sign/client/Sign$Model$SessionEvent;", "Lcom/reown/sign/client/Sign$Model;", "name", "", "data", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getData", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class SessionEvent extends Model {
            @NotNull
            private final String data;
            @NotNull
            private final String name;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public SessionEvent(@NotNull String str, @NotNull String str2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "name");
                Intrinsics.checkNotNullParameter(str2, "data");
                this.name = str;
                this.data = str2;
            }

            public static /* synthetic */ SessionEvent copy$default(SessionEvent sessionEvent, String str, String str2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = sessionEvent.name;
                }
                if ((i3 & 2) != 0) {
                    str2 = sessionEvent.data;
                }
                return sessionEvent.copy(str, str2);
            }

            @NotNull
            public final String component1() {
                return this.name;
            }

            @NotNull
            public final String component2() {
                return this.data;
            }

            @NotNull
            public final SessionEvent copy(@NotNull String str, @NotNull String str2) {
                Intrinsics.checkNotNullParameter(str, "name");
                Intrinsics.checkNotNullParameter(str2, "data");
                return new SessionEvent(str, str2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof SessionEvent)) {
                    return false;
                }
                SessionEvent sessionEvent = (SessionEvent) obj;
                return Intrinsics.areEqual((Object) this.name, (Object) sessionEvent.name) && Intrinsics.areEqual((Object) this.data, (Object) sessionEvent.data);
            }

            @NotNull
            public final String getData() {
                return this.data;
            }

            @NotNull
            public final String getName() {
                return this.name;
            }

            public int hashCode() {
                return this.data.hashCode() + (this.name.hashCode() * 31);
            }

            @NotNull
            public String toString() {
                return C0118y.g("SessionEvent(name=", this.name, ", data=", this.data, ")");
            }
        }

        @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b'\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B«\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\r0\f\u0012\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\r0\f\u0012\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\u0014\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0004\b\u0014\u0010\u0015J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\u000f\u0010*\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\u0015\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\r0\fHÆ\u0003J\u0015\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\r0\fHÆ\u0003J\u0017\u0010.\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\fHÆ\u0003J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\t\u00100\u001a\u00020\u0003HÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0017\u00102\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\fHÆ\u0003JÇ\u0001\u00103\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\b\u0002\u0010\n\u001a\u00020\u00032\u0014\b\u0002\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\r0\f2\u0014\b\u0002\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\r0\f2\u0016\b\u0002\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f2\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\fHÆ\u0001J\u0013\u00104\u001a\u0002052\b\u00106\u001a\u0004\u0018\u000107HÖ\u0003J\t\u00108\u001a\u000209HÖ\u0001J\t\u0010:\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0017R\u001d\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\r0\f¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u001d\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\r0\f¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001fR\u001f\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001fR\u0011\u0010\u0010\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0017R\u0011\u0010\u0011\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0017R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0017R\u001f\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001f¨\u0006;"}, d2 = {"Lcom/reown/sign/client/Sign$Model$SessionProposal;", "Lcom/reown/sign/client/Sign$Model;", "pairingTopic", "", "name", "description", "url", "icons", "", "Ljava/net/URI;", "redirect", "requiredNamespaces", "", "Lcom/reown/sign/client/Sign$Model$Namespace$Proposal;", "optionalNamespaces", "properties", "proposerPublicKey", "relayProtocol", "relayData", "scopedProperties", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "getPairingTopic", "()Ljava/lang/String;", "getName", "getDescription", "getUrl", "getIcons", "()Ljava/util/List;", "getRedirect", "getRequiredNamespaces", "()Ljava/util/Map;", "getOptionalNamespaces", "getProperties", "getProposerPublicKey", "getRelayProtocol", "getRelayData", "getScopedProperties", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class SessionProposal extends Model {
            @NotNull
            private final String description;
            @NotNull
            private final List<URI> icons;
            @NotNull
            private final String name;
            @NotNull
            private final Map<String, Namespace.Proposal> optionalNamespaces;
            @NotNull
            private final String pairingTopic;
            @Nullable
            private final Map<String, String> properties;
            @NotNull
            private final String proposerPublicKey;
            @NotNull
            private final String redirect;
            @Nullable
            private final String relayData;
            @NotNull
            private final String relayProtocol;
            @NotNull
            private final Map<String, Namespace.Proposal> requiredNamespaces;
            @Nullable
            private final Map<String, String> scopedProperties;
            @NotNull
            private final String url;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public SessionProposal(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull List<URI> list, @NotNull String str5, @NotNull Map<String, Namespace.Proposal> map, @NotNull Map<String, Namespace.Proposal> map2, @Nullable Map<String, String> map3, @NotNull String str6, @NotNull String str7, @Nullable String str8, @Nullable Map<String, String> map4) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "pairingTopic");
                Intrinsics.checkNotNullParameter(str2, "name");
                Intrinsics.checkNotNullParameter(str3, "description");
                Intrinsics.checkNotNullParameter(str4, "url");
                Intrinsics.checkNotNullParameter(list, "icons");
                Intrinsics.checkNotNullParameter(str5, "redirect");
                Intrinsics.checkNotNullParameter(map, "requiredNamespaces");
                Intrinsics.checkNotNullParameter(map2, "optionalNamespaces");
                Intrinsics.checkNotNullParameter(str6, "proposerPublicKey");
                Intrinsics.checkNotNullParameter(str7, "relayProtocol");
                this.pairingTopic = str;
                this.name = str2;
                this.description = str3;
                this.url = str4;
                this.icons = list;
                this.redirect = str5;
                this.requiredNamespaces = map;
                this.optionalNamespaces = map2;
                this.properties = map3;
                this.proposerPublicKey = str6;
                this.relayProtocol = str7;
                this.relayData = str8;
                this.scopedProperties = map4;
            }

            public static /* synthetic */ SessionProposal copy$default(SessionProposal sessionProposal, String str, String str2, String str3, String str4, List list, String str5, Map map, Map map2, Map map3, String str6, String str7, String str8, Map map4, int i3, Object obj) {
                SessionProposal sessionProposal2 = sessionProposal;
                int i4 = i3;
                return sessionProposal.copy((i4 & 1) != 0 ? sessionProposal2.pairingTopic : str, (i4 & 2) != 0 ? sessionProposal2.name : str2, (i4 & 4) != 0 ? sessionProposal2.description : str3, (i4 & 8) != 0 ? sessionProposal2.url : str4, (i4 & 16) != 0 ? sessionProposal2.icons : list, (i4 & 32) != 0 ? sessionProposal2.redirect : str5, (i4 & 64) != 0 ? sessionProposal2.requiredNamespaces : map, (i4 & 128) != 0 ? sessionProposal2.optionalNamespaces : map2, (i4 & 256) != 0 ? sessionProposal2.properties : map3, (i4 & 512) != 0 ? sessionProposal2.proposerPublicKey : str6, (i4 & 1024) != 0 ? sessionProposal2.relayProtocol : str7, (i4 & 2048) != 0 ? sessionProposal2.relayData : str8, (i4 & 4096) != 0 ? sessionProposal2.scopedProperties : map4);
            }

            @NotNull
            public final String component1() {
                return this.pairingTopic;
            }

            @NotNull
            public final String component10() {
                return this.proposerPublicKey;
            }

            @NotNull
            public final String component11() {
                return this.relayProtocol;
            }

            @Nullable
            public final String component12() {
                return this.relayData;
            }

            @Nullable
            public final Map<String, String> component13() {
                return this.scopedProperties;
            }

            @NotNull
            public final String component2() {
                return this.name;
            }

            @NotNull
            public final String component3() {
                return this.description;
            }

            @NotNull
            public final String component4() {
                return this.url;
            }

            @NotNull
            public final List<URI> component5() {
                return this.icons;
            }

            @NotNull
            public final String component6() {
                return this.redirect;
            }

            @NotNull
            public final Map<String, Namespace.Proposal> component7() {
                return this.requiredNamespaces;
            }

            @NotNull
            public final Map<String, Namespace.Proposal> component8() {
                return this.optionalNamespaces;
            }

            @Nullable
            public final Map<String, String> component9() {
                return this.properties;
            }

            @NotNull
            public final SessionProposal copy(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull List<URI> list, @NotNull String str5, @NotNull Map<String, Namespace.Proposal> map, @NotNull Map<String, Namespace.Proposal> map2, @Nullable Map<String, String> map3, @NotNull String str6, @NotNull String str7, @Nullable String str8, @Nullable Map<String, String> map4) {
                String str9 = str;
                Intrinsics.checkNotNullParameter(str9, "pairingTopic");
                String str10 = str2;
                Intrinsics.checkNotNullParameter(str10, "name");
                String str11 = str3;
                Intrinsics.checkNotNullParameter(str11, "description");
                String str12 = str4;
                Intrinsics.checkNotNullParameter(str12, "url");
                List<URI> list2 = list;
                Intrinsics.checkNotNullParameter(list2, "icons");
                String str13 = str5;
                Intrinsics.checkNotNullParameter(str13, "redirect");
                Map<String, Namespace.Proposal> map5 = map;
                Intrinsics.checkNotNullParameter(map5, "requiredNamespaces");
                Map<String, Namespace.Proposal> map6 = map2;
                Intrinsics.checkNotNullParameter(map6, "optionalNamespaces");
                String str14 = str6;
                Intrinsics.checkNotNullParameter(str14, "proposerPublicKey");
                String str15 = str7;
                Intrinsics.checkNotNullParameter(str15, "relayProtocol");
                return new SessionProposal(str9, str10, str11, str12, list2, str13, map5, map6, map3, str14, str15, str8, map4);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof SessionProposal)) {
                    return false;
                }
                SessionProposal sessionProposal = (SessionProposal) obj;
                return Intrinsics.areEqual((Object) this.pairingTopic, (Object) sessionProposal.pairingTopic) && Intrinsics.areEqual((Object) this.name, (Object) sessionProposal.name) && Intrinsics.areEqual((Object) this.description, (Object) sessionProposal.description) && Intrinsics.areEqual((Object) this.url, (Object) sessionProposal.url) && Intrinsics.areEqual((Object) this.icons, (Object) sessionProposal.icons) && Intrinsics.areEqual((Object) this.redirect, (Object) sessionProposal.redirect) && Intrinsics.areEqual((Object) this.requiredNamespaces, (Object) sessionProposal.requiredNamespaces) && Intrinsics.areEqual((Object) this.optionalNamespaces, (Object) sessionProposal.optionalNamespaces) && Intrinsics.areEqual((Object) this.properties, (Object) sessionProposal.properties) && Intrinsics.areEqual((Object) this.proposerPublicKey, (Object) sessionProposal.proposerPublicKey) && Intrinsics.areEqual((Object) this.relayProtocol, (Object) sessionProposal.relayProtocol) && Intrinsics.areEqual((Object) this.relayData, (Object) sessionProposal.relayData) && Intrinsics.areEqual((Object) this.scopedProperties, (Object) sessionProposal.scopedProperties);
            }

            @NotNull
            public final String getDescription() {
                return this.description;
            }

            @NotNull
            public final List<URI> getIcons() {
                return this.icons;
            }

            @NotNull
            public final String getName() {
                return this.name;
            }

            @NotNull
            public final Map<String, Namespace.Proposal> getOptionalNamespaces() {
                return this.optionalNamespaces;
            }

            @NotNull
            public final String getPairingTopic() {
                return this.pairingTopic;
            }

            @Nullable
            public final Map<String, String> getProperties() {
                return this.properties;
            }

            @NotNull
            public final String getProposerPublicKey() {
                return this.proposerPublicKey;
            }

            @NotNull
            public final String getRedirect() {
                return this.redirect;
            }

            @Nullable
            public final String getRelayData() {
                return this.relayData;
            }

            @NotNull
            public final String getRelayProtocol() {
                return this.relayProtocol;
            }

            @NotNull
            public final Map<String, Namespace.Proposal> getRequiredNamespaces() {
                return this.requiredNamespaces;
            }

            @Nullable
            public final Map<String, String> getScopedProperties() {
                return this.scopedProperties;
            }

            @NotNull
            public final String getUrl() {
                return this.url;
            }

            public int hashCode() {
                int d2 = b.d(this.optionalNamespaces, b.d(this.requiredNamespaces, androidx.compose.animation.core.a.i(this.redirect, androidx.compose.animation.core.a.j(this.icons, androidx.compose.animation.core.a.i(this.url, androidx.compose.animation.core.a.i(this.description, androidx.compose.animation.core.a.i(this.name, this.pairingTopic.hashCode() * 31, 31), 31), 31), 31), 31), 31), 31);
                Map<String, String> map = this.properties;
                int i3 = 0;
                int i4 = androidx.compose.animation.core.a.i(this.relayProtocol, androidx.compose.animation.core.a.i(this.proposerPublicKey, (d2 + (map == null ? 0 : map.hashCode())) * 31, 31), 31);
                String str = this.relayData;
                int hashCode = (i4 + (str == null ? 0 : str.hashCode())) * 31;
                Map<String, String> map2 = this.scopedProperties;
                if (map2 != null) {
                    i3 = map2.hashCode();
                }
                return hashCode + i3;
            }

            @NotNull
            public String toString() {
                String str = this.pairingTopic;
                String str2 = this.name;
                String str3 = this.description;
                String str4 = this.url;
                List<URI> list = this.icons;
                String str5 = this.redirect;
                Map<String, Namespace.Proposal> map = this.requiredNamespaces;
                Map<String, Namespace.Proposal> map2 = this.optionalNamespaces;
                Map<String, String> map3 = this.properties;
                String str6 = this.proposerPublicKey;
                String str7 = this.relayProtocol;
                String str8 = this.relayData;
                Map<String, String> map4 = this.scopedProperties;
                StringBuilder l2 = C0118y.l("SessionProposal(pairingTopic=", str, ", name=", str2, ", description=");
                b.w(l2, str3, ", url=", str4, ", icons=");
                l2.append(list);
                l2.append(", redirect=");
                l2.append(str5);
                l2.append(", requiredNamespaces=");
                l2.append(map);
                l2.append(", optionalNamespaces=");
                l2.append(map2);
                l2.append(", properties=");
                l2.append(map3);
                l2.append(", proposerPublicKey=");
                l2.append(str6);
                l2.append(", relayProtocol=");
                b.w(l2, str7, ", relayData=", str8, ", scopedProperties=");
                l2.append(map4);
                l2.append(")");
                return l2.toString();
            }
        }

        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001:\u0001\u001eB+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J5\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001f"}, d2 = {"Lcom/reown/sign/client/Sign$Model$SessionRequest;", "Lcom/reown/sign/client/Sign$Model;", "topic", "", "chainId", "peerMetaData", "Lcom/reown/android/Core$Model$AppMetaData;", "request", "Lcom/reown/sign/client/Sign$Model$SessionRequest$JSONRPCRequest;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/reown/android/Core$Model$AppMetaData;Lcom/reown/sign/client/Sign$Model$SessionRequest$JSONRPCRequest;)V", "getTopic", "()Ljava/lang/String;", "getChainId", "getPeerMetaData", "()Lcom/reown/android/Core$Model$AppMetaData;", "getRequest", "()Lcom/reown/sign/client/Sign$Model$SessionRequest$JSONRPCRequest;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "JSONRPCRequest", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class SessionRequest extends Model {
            @Nullable
            private final String chainId;
            @Nullable
            private final Core.Model.AppMetaData peerMetaData;
            @NotNull
            private final JSONRPCRequest request;
            @NotNull
            private final String topic;

            @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0019"}, d2 = {"Lcom/reown/sign/client/Sign$Model$SessionRequest$JSONRPCRequest;", "Lcom/reown/sign/client/Sign$Model;", "id", "", "method", "", "params", "<init>", "(JLjava/lang/String;Ljava/lang/String;)V", "getId", "()J", "getMethod", "()Ljava/lang/String;", "getParams", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class JSONRPCRequest extends Model {
                private final long id;
                @NotNull
                private final String method;
                @NotNull
                private final String params;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public JSONRPCRequest(long j2, @NotNull String str, @NotNull String str2) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, FirebaseAnalytics.Param.METHOD);
                    Intrinsics.checkNotNullParameter(str2, "params");
                    this.id = j2;
                    this.method = str;
                    this.params = str2;
                }

                public static /* synthetic */ JSONRPCRequest copy$default(JSONRPCRequest jSONRPCRequest, long j2, String str, String str2, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        j2 = jSONRPCRequest.id;
                    }
                    if ((i3 & 2) != 0) {
                        str = jSONRPCRequest.method;
                    }
                    if ((i3 & 4) != 0) {
                        str2 = jSONRPCRequest.params;
                    }
                    return jSONRPCRequest.copy(j2, str, str2);
                }

                public final long component1() {
                    return this.id;
                }

                @NotNull
                public final String component2() {
                    return this.method;
                }

                @NotNull
                public final String component3() {
                    return this.params;
                }

                @NotNull
                public final JSONRPCRequest copy(long j2, @NotNull String str, @NotNull String str2) {
                    Intrinsics.checkNotNullParameter(str, FirebaseAnalytics.Param.METHOD);
                    Intrinsics.checkNotNullParameter(str2, "params");
                    return new JSONRPCRequest(j2, str, str2);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof JSONRPCRequest)) {
                        return false;
                    }
                    JSONRPCRequest jSONRPCRequest = (JSONRPCRequest) obj;
                    return this.id == jSONRPCRequest.id && Intrinsics.areEqual((Object) this.method, (Object) jSONRPCRequest.method) && Intrinsics.areEqual((Object) this.params, (Object) jSONRPCRequest.params);
                }

                public final long getId() {
                    return this.id;
                }

                @NotNull
                public final String getMethod() {
                    return this.method;
                }

                @NotNull
                public final String getParams() {
                    return this.params;
                }

                public int hashCode() {
                    return this.params.hashCode() + androidx.compose.animation.core.a.i(this.method, Long.hashCode(this.id) * 31, 31);
                }

                @NotNull
                public String toString() {
                    long j2 = this.id;
                    String str = this.method;
                    return C0118y.j(androidx.work.impl.a.v(j2, "JSONRPCRequest(id=", ", method=", str), ", params=", this.params, ")");
                }
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public SessionRequest(@NotNull String str, @Nullable String str2, @Nullable Core.Model.AppMetaData appMetaData, @NotNull JSONRPCRequest jSONRPCRequest) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                Intrinsics.checkNotNullParameter(jSONRPCRequest, "request");
                this.topic = str;
                this.chainId = str2;
                this.peerMetaData = appMetaData;
                this.request = jSONRPCRequest;
            }

            public static /* synthetic */ SessionRequest copy$default(SessionRequest sessionRequest, String str, String str2, Core.Model.AppMetaData appMetaData, JSONRPCRequest jSONRPCRequest, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = sessionRequest.topic;
                }
                if ((i3 & 2) != 0) {
                    str2 = sessionRequest.chainId;
                }
                if ((i3 & 4) != 0) {
                    appMetaData = sessionRequest.peerMetaData;
                }
                if ((i3 & 8) != 0) {
                    jSONRPCRequest = sessionRequest.request;
                }
                return sessionRequest.copy(str, str2, appMetaData, jSONRPCRequest);
            }

            @NotNull
            public final String component1() {
                return this.topic;
            }

            @Nullable
            public final String component2() {
                return this.chainId;
            }

            @Nullable
            public final Core.Model.AppMetaData component3() {
                return this.peerMetaData;
            }

            @NotNull
            public final JSONRPCRequest component4() {
                return this.request;
            }

            @NotNull
            public final SessionRequest copy(@NotNull String str, @Nullable String str2, @Nullable Core.Model.AppMetaData appMetaData, @NotNull JSONRPCRequest jSONRPCRequest) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                Intrinsics.checkNotNullParameter(jSONRPCRequest, "request");
                return new SessionRequest(str, str2, appMetaData, jSONRPCRequest);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof SessionRequest)) {
                    return false;
                }
                SessionRequest sessionRequest = (SessionRequest) obj;
                return Intrinsics.areEqual((Object) this.topic, (Object) sessionRequest.topic) && Intrinsics.areEqual((Object) this.chainId, (Object) sessionRequest.chainId) && Intrinsics.areEqual((Object) this.peerMetaData, (Object) sessionRequest.peerMetaData) && Intrinsics.areEqual((Object) this.request, (Object) sessionRequest.request);
            }

            @Nullable
            public final String getChainId() {
                return this.chainId;
            }

            @Nullable
            public final Core.Model.AppMetaData getPeerMetaData() {
                return this.peerMetaData;
            }

            @NotNull
            public final JSONRPCRequest getRequest() {
                return this.request;
            }

            @NotNull
            public final String getTopic() {
                return this.topic;
            }

            public int hashCode() {
                int hashCode = this.topic.hashCode() * 31;
                String str = this.chainId;
                int i3 = 0;
                int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
                Core.Model.AppMetaData appMetaData = this.peerMetaData;
                if (appMetaData != null) {
                    i3 = appMetaData.hashCode();
                }
                return this.request.hashCode() + ((hashCode2 + i3) * 31);
            }

            @NotNull
            public String toString() {
                String str = this.topic;
                String str2 = this.chainId;
                Core.Model.AppMetaData appMetaData = this.peerMetaData;
                JSONRPCRequest jSONRPCRequest = this.request;
                StringBuilder l2 = C0118y.l("SessionRequest(topic=", str, ", chainId=", str2, ", peerMetaData=");
                l2.append(appMetaData);
                l2.append(", request=");
                l2.append(jSONRPCRequest);
                l2.append(")");
                return l2.toString();
            }
        }

        @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0007HÆ\u0003J3\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001c"}, d2 = {"Lcom/reown/sign/client/Sign$Model$SessionRequestResponse;", "Lcom/reown/sign/client/Sign$Model;", "topic", "", "chainId", "method", "result", "Lcom/reown/sign/client/Sign$Model$JsonRpcResponse;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/reown/sign/client/Sign$Model$JsonRpcResponse;)V", "getTopic", "()Ljava/lang/String;", "getChainId", "getMethod", "getResult", "()Lcom/reown/sign/client/Sign$Model$JsonRpcResponse;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class SessionRequestResponse extends Model {
            @Nullable
            private final String chainId;
            @NotNull
            private final String method;
            @NotNull
            private final JsonRpcResponse result;
            @NotNull
            private final String topic;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public SessionRequestResponse(@NotNull String str, @Nullable String str2, @NotNull String str3, @NotNull JsonRpcResponse jsonRpcResponse) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                Intrinsics.checkNotNullParameter(str3, FirebaseAnalytics.Param.METHOD);
                Intrinsics.checkNotNullParameter(jsonRpcResponse, "result");
                this.topic = str;
                this.chainId = str2;
                this.method = str3;
                this.result = jsonRpcResponse;
            }

            public static /* synthetic */ SessionRequestResponse copy$default(SessionRequestResponse sessionRequestResponse, String str, String str2, String str3, JsonRpcResponse jsonRpcResponse, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = sessionRequestResponse.topic;
                }
                if ((i3 & 2) != 0) {
                    str2 = sessionRequestResponse.chainId;
                }
                if ((i3 & 4) != 0) {
                    str3 = sessionRequestResponse.method;
                }
                if ((i3 & 8) != 0) {
                    jsonRpcResponse = sessionRequestResponse.result;
                }
                return sessionRequestResponse.copy(str, str2, str3, jsonRpcResponse);
            }

            @NotNull
            public final String component1() {
                return this.topic;
            }

            @Nullable
            public final String component2() {
                return this.chainId;
            }

            @NotNull
            public final String component3() {
                return this.method;
            }

            @NotNull
            public final JsonRpcResponse component4() {
                return this.result;
            }

            @NotNull
            public final SessionRequestResponse copy(@NotNull String str, @Nullable String str2, @NotNull String str3, @NotNull JsonRpcResponse jsonRpcResponse) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                Intrinsics.checkNotNullParameter(str3, FirebaseAnalytics.Param.METHOD);
                Intrinsics.checkNotNullParameter(jsonRpcResponse, "result");
                return new SessionRequestResponse(str, str2, str3, jsonRpcResponse);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof SessionRequestResponse)) {
                    return false;
                }
                SessionRequestResponse sessionRequestResponse = (SessionRequestResponse) obj;
                return Intrinsics.areEqual((Object) this.topic, (Object) sessionRequestResponse.topic) && Intrinsics.areEqual((Object) this.chainId, (Object) sessionRequestResponse.chainId) && Intrinsics.areEqual((Object) this.method, (Object) sessionRequestResponse.method) && Intrinsics.areEqual((Object) this.result, (Object) sessionRequestResponse.result);
            }

            @Nullable
            public final String getChainId() {
                return this.chainId;
            }

            @NotNull
            public final String getMethod() {
                return this.method;
            }

            @NotNull
            public final JsonRpcResponse getResult() {
                return this.result;
            }

            @NotNull
            public final String getTopic() {
                return this.topic;
            }

            public int hashCode() {
                int hashCode = this.topic.hashCode() * 31;
                String str = this.chainId;
                return this.result.hashCode() + androidx.compose.animation.core.a.i(this.method, (hashCode + (str == null ? 0 : str.hashCode())) * 31, 31);
            }

            @NotNull
            public String toString() {
                String str = this.topic;
                String str2 = this.chainId;
                String str3 = this.method;
                JsonRpcResponse jsonRpcResponse = this.result;
                StringBuilder l2 = C0118y.l("SessionRequestResponse(topic=", str, ", chainId=", str2, ", method=");
                l2.append(str3);
                l2.append(", result=");
                l2.append(jsonRpcResponse);
                l2.append(")");
                return l2.toString();
            }
        }

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/sign/client/Sign$Model$SessionUpdateResponse;", "Lcom/reown/sign/client/Sign$Model;", "<init>", "()V", "Result", "Error", "Lcom/reown/sign/client/Sign$Model$SessionUpdateResponse$Error;", "Lcom/reown/sign/client/Sign$Model$SessionUpdateResponse$Result;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static abstract class SessionUpdateResponse extends Model {

            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/sign/client/Sign$Model$SessionUpdateResponse$Error;", "Lcom/reown/sign/client/Sign$Model$SessionUpdateResponse;", "errorMessage", "", "<init>", "(Ljava/lang/String;)V", "getErrorMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Error extends SessionUpdateResponse {
                @NotNull
                private final String errorMessage;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Error(@NotNull String str) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, "errorMessage");
                    this.errorMessage = str;
                }

                public static /* synthetic */ Error copy$default(Error error, String str, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        str = error.errorMessage;
                    }
                    return error.copy(str);
                }

                @NotNull
                public final String component1() {
                    return this.errorMessage;
                }

                @NotNull
                public final Error copy(@NotNull String str) {
                    Intrinsics.checkNotNullParameter(str, "errorMessage");
                    return new Error(str);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return (obj instanceof Error) && Intrinsics.areEqual((Object) this.errorMessage, (Object) ((Error) obj).errorMessage);
                }

                @NotNull
                public final String getErrorMessage() {
                    return this.errorMessage;
                }

                public int hashCode() {
                    return this.errorMessage.hashCode();
                }

                @NotNull
                public String toString() {
                    return a.l("Error(errorMessage=", this.errorMessage, ")");
                }
            }

            @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u0015\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J)\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/reown/sign/client/Sign$Model$SessionUpdateResponse$Result;", "Lcom/reown/sign/client/Sign$Model$SessionUpdateResponse;", "topic", "", "namespaces", "", "Lcom/reown/sign/client/Sign$Model$Namespace$Session;", "<init>", "(Ljava/lang/String;Ljava/util/Map;)V", "getTopic", "()Ljava/lang/String;", "getNamespaces", "()Ljava/util/Map;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Result extends SessionUpdateResponse {
                @NotNull
                private final Map<String, Namespace.Session> namespaces;
                @NotNull
                private final String topic;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Result(@NotNull String str, @NotNull Map<String, Namespace.Session> map) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                    Intrinsics.checkNotNullParameter(map, "namespaces");
                    this.topic = str;
                    this.namespaces = map;
                }

                public static /* synthetic */ Result copy$default(Result result, String str, Map<String, Namespace.Session> map, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        str = result.topic;
                    }
                    if ((i3 & 2) != 0) {
                        map = result.namespaces;
                    }
                    return result.copy(str, map);
                }

                @NotNull
                public final String component1() {
                    return this.topic;
                }

                @NotNull
                public final Map<String, Namespace.Session> component2() {
                    return this.namespaces;
                }

                @NotNull
                public final Result copy(@NotNull String str, @NotNull Map<String, Namespace.Session> map) {
                    Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                    Intrinsics.checkNotNullParameter(map, "namespaces");
                    return new Result(str, map);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof Result)) {
                        return false;
                    }
                    Result result = (Result) obj;
                    return Intrinsics.areEqual((Object) this.topic, (Object) result.topic) && Intrinsics.areEqual((Object) this.namespaces, (Object) result.namespaces);
                }

                @NotNull
                public final Map<String, Namespace.Session> getNamespaces() {
                    return this.namespaces;
                }

                @NotNull
                public final String getTopic() {
                    return this.topic;
                }

                public int hashCode() {
                    return this.namespaces.hashCode() + (this.topic.hashCode() * 31);
                }

                @NotNull
                public String toString() {
                    String str = this.topic;
                    Map<String, Namespace.Session> map = this.namespaces;
                    return "Result(topic=" + str + ", namespaces=" + map + ")";
                }
            }

            public /* synthetic */ SessionUpdateResponse(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private SessionUpdateResponse() {
                super((DefaultConstructorMarker) null);
            }
        }

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/sign/client/Sign$Model$SettledSessionResponse;", "Lcom/reown/sign/client/Sign$Model;", "<init>", "()V", "Result", "Error", "Lcom/reown/sign/client/Sign$Model$SettledSessionResponse$Error;", "Lcom/reown/sign/client/Sign$Model$SettledSessionResponse$Result;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static abstract class SettledSessionResponse extends Model {

            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/sign/client/Sign$Model$SettledSessionResponse$Error;", "Lcom/reown/sign/client/Sign$Model$SettledSessionResponse;", "errorMessage", "", "<init>", "(Ljava/lang/String;)V", "getErrorMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Error extends SettledSessionResponse {
                @NotNull
                private final String errorMessage;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Error(@NotNull String str) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, "errorMessage");
                    this.errorMessage = str;
                }

                public static /* synthetic */ Error copy$default(Error error, String str, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        str = error.errorMessage;
                    }
                    return error.copy(str);
                }

                @NotNull
                public final String component1() {
                    return this.errorMessage;
                }

                @NotNull
                public final Error copy(@NotNull String str) {
                    Intrinsics.checkNotNullParameter(str, "errorMessage");
                    return new Error(str);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return (obj instanceof Error) && Intrinsics.areEqual((Object) this.errorMessage, (Object) ((Error) obj).errorMessage);
                }

                @NotNull
                public final String getErrorMessage() {
                    return this.errorMessage;
                }

                public int hashCode() {
                    return this.errorMessage.hashCode();
                }

                @NotNull
                public String toString() {
                    return a.l("Error(errorMessage=", this.errorMessage, ")");
                }
            }

            @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/reown/sign/client/Sign$Model$SettledSessionResponse$Result;", "Lcom/reown/sign/client/Sign$Model$SettledSessionResponse;", "session", "Lcom/reown/sign/client/Sign$Model$Session;", "<init>", "(Lcom/reown/sign/client/Sign$Model$Session;)V", "getSession", "()Lcom/reown/sign/client/Sign$Model$Session;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Result extends SettledSessionResponse {
                @NotNull
                private final Session session;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Result(@NotNull Session session2) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(session2, "session");
                    this.session = session2;
                }

                public static /* synthetic */ Result copy$default(Result result, Session session2, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        session2 = result.session;
                    }
                    return result.copy(session2);
                }

                @NotNull
                public final Session component1() {
                    return this.session;
                }

                @NotNull
                public final Result copy(@NotNull Session session2) {
                    Intrinsics.checkNotNullParameter(session2, "session");
                    return new Result(session2);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return (obj instanceof Result) && Intrinsics.areEqual((Object) this.session, (Object) ((Result) obj).session);
                }

                @NotNull
                public final Session getSession() {
                    return this.session;
                }

                public int hashCode() {
                    return this.session.hashCode();
                }

                @NotNull
                public String toString() {
                    Session session2 = this.session;
                    return "Result(session=" + session2 + ")";
                }
            }

            public /* synthetic */ SettledSessionResponse(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private SettledSessionResponse() {
                super((DefaultConstructorMarker) null);
            }
        }

        @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u0015\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J)\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/reown/sign/client/Sign$Model$UpdatedSession;", "Lcom/reown/sign/client/Sign$Model;", "topic", "", "namespaces", "", "Lcom/reown/sign/client/Sign$Model$Namespace$Session;", "<init>", "(Ljava/lang/String;Ljava/util/Map;)V", "getTopic", "()Ljava/lang/String;", "getNamespaces", "()Ljava/util/Map;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class UpdatedSession extends Model {
            @NotNull
            private final Map<String, Namespace.Session> namespaces;
            @NotNull
            private final String topic;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public UpdatedSession(@NotNull String str, @NotNull Map<String, Namespace.Session> map) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                Intrinsics.checkNotNullParameter(map, "namespaces");
                this.topic = str;
                this.namespaces = map;
            }

            public static /* synthetic */ UpdatedSession copy$default(UpdatedSession updatedSession, String str, Map<String, Namespace.Session> map, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = updatedSession.topic;
                }
                if ((i3 & 2) != 0) {
                    map = updatedSession.namespaces;
                }
                return updatedSession.copy(str, map);
            }

            @NotNull
            public final String component1() {
                return this.topic;
            }

            @NotNull
            public final Map<String, Namespace.Session> component2() {
                return this.namespaces;
            }

            @NotNull
            public final UpdatedSession copy(@NotNull String str, @NotNull Map<String, Namespace.Session> map) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                Intrinsics.checkNotNullParameter(map, "namespaces");
                return new UpdatedSession(str, map);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof UpdatedSession)) {
                    return false;
                }
                UpdatedSession updatedSession = (UpdatedSession) obj;
                return Intrinsics.areEqual((Object) this.topic, (Object) updatedSession.topic) && Intrinsics.areEqual((Object) this.namespaces, (Object) updatedSession.namespaces);
            }

            @NotNull
            public final Map<String, Namespace.Session> getNamespaces() {
                return this.namespaces;
            }

            @NotNull
            public final String getTopic() {
                return this.topic;
            }

            public int hashCode() {
                return this.namespaces.hashCode() + (this.topic.hashCode() * 31);
            }

            @NotNull
            public String toString() {
                String str = this.topic;
                Map<String, Namespace.Session> map = this.namespaces;
                return "UpdatedSession(topic=" + str + ", namespaces=" + map + ")";
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/reown/sign/client/Sign$Model$Validation;", "", "<init>", "(Ljava/lang/String;I)V", "VALID", "INVALID", "UNKNOWN", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public enum Validation {
            VALID,
            INVALID,
            UNKNOWN;

            static {
                Validation[] $values;
                $ENTRIES = EnumEntriesKt.enumEntries((E[]) $values);
            }

            @NotNull
            public static EnumEntries<Validation> getEntries() {
                return $ENTRIES;
            }
        }

        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u0014JB\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nHÆ\u0001¢\u0006\u0002\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\n2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fHÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0015\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\t\u0010\u0014¨\u0006#"}, d2 = {"Lcom/reown/sign/client/Sign$Model$VerifyContext;", "Lcom/reown/sign/client/Sign$Model;", "id", "", "origin", "", "validation", "Lcom/reown/sign/client/Sign$Model$Validation;", "verifyUrl", "isScam", "", "<init>", "(JLjava/lang/String;Lcom/reown/sign/client/Sign$Model$Validation;Ljava/lang/String;Ljava/lang/Boolean;)V", "getId", "()J", "getOrigin", "()Ljava/lang/String;", "getValidation", "()Lcom/reown/sign/client/Sign$Model$Validation;", "getVerifyUrl", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "component1", "component2", "component3", "component4", "component5", "copy", "(JLjava/lang/String;Lcom/reown/sign/client/Sign$Model$Validation;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/reown/sign/client/Sign$Model$VerifyContext;", "equals", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class VerifyContext extends Model {
            private final long id;
            @Nullable
            private final Boolean isScam;
            @NotNull
            private final String origin;
            @NotNull
            private final Validation validation;
            @NotNull
            private final String verifyUrl;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public VerifyContext(long j2, @NotNull String str, @NotNull Validation validation2, @NotNull String str2, @Nullable Boolean bool) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "origin");
                Intrinsics.checkNotNullParameter(validation2, org.apache.xerces.impl.Constants.VALIDATION_FEATURE);
                Intrinsics.checkNotNullParameter(str2, "verifyUrl");
                this.id = j2;
                this.origin = str;
                this.validation = validation2;
                this.verifyUrl = str2;
                this.isScam = bool;
            }

            public static /* synthetic */ VerifyContext copy$default(VerifyContext verifyContext, long j2, String str, Validation validation2, String str2, Boolean bool, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    j2 = verifyContext.id;
                }
                long j3 = j2;
                if ((i3 & 2) != 0) {
                    str = verifyContext.origin;
                }
                String str3 = str;
                if ((i3 & 4) != 0) {
                    validation2 = verifyContext.validation;
                }
                Validation validation3 = validation2;
                if ((i3 & 8) != 0) {
                    str2 = verifyContext.verifyUrl;
                }
                String str4 = str2;
                if ((i3 & 16) != 0) {
                    bool = verifyContext.isScam;
                }
                return verifyContext.copy(j3, str3, validation3, str4, bool);
            }

            public final long component1() {
                return this.id;
            }

            @NotNull
            public final String component2() {
                return this.origin;
            }

            @NotNull
            public final Validation component3() {
                return this.validation;
            }

            @NotNull
            public final String component4() {
                return this.verifyUrl;
            }

            @Nullable
            public final Boolean component5() {
                return this.isScam;
            }

            @NotNull
            public final VerifyContext copy(long j2, @NotNull String str, @NotNull Validation validation2, @NotNull String str2, @Nullable Boolean bool) {
                Intrinsics.checkNotNullParameter(str, "origin");
                Intrinsics.checkNotNullParameter(validation2, org.apache.xerces.impl.Constants.VALIDATION_FEATURE);
                Intrinsics.checkNotNullParameter(str2, "verifyUrl");
                return new VerifyContext(j2, str, validation2, str2, bool);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof VerifyContext)) {
                    return false;
                }
                VerifyContext verifyContext = (VerifyContext) obj;
                return this.id == verifyContext.id && Intrinsics.areEqual((Object) this.origin, (Object) verifyContext.origin) && this.validation == verifyContext.validation && Intrinsics.areEqual((Object) this.verifyUrl, (Object) verifyContext.verifyUrl) && Intrinsics.areEqual((Object) this.isScam, (Object) verifyContext.isScam);
            }

            public final long getId() {
                return this.id;
            }

            @NotNull
            public final String getOrigin() {
                return this.origin;
            }

            @NotNull
            public final Validation getValidation() {
                return this.validation;
            }

            @NotNull
            public final String getVerifyUrl() {
                return this.verifyUrl;
            }

            public int hashCode() {
                int i3 = androidx.compose.animation.core.a.i(this.verifyUrl, (this.validation.hashCode() + androidx.compose.animation.core.a.i(this.origin, Long.hashCode(this.id) * 31, 31)) * 31, 31);
                Boolean bool = this.isScam;
                return i3 + (bool == null ? 0 : bool.hashCode());
            }

            @Nullable
            public final Boolean isScam() {
                return this.isScam;
            }

            @NotNull
            public String toString() {
                long j2 = this.id;
                String str = this.origin;
                Validation validation2 = this.validation;
                String str2 = this.verifyUrl;
                Boolean bool = this.isScam;
                StringBuilder v2 = androidx.work.impl.a.v(j2, "VerifyContext(id=", ", origin=", str);
                v2.append(", validation=");
                v2.append(validation2);
                v2.append(", verifyUrl=");
                v2.append(str2);
                v2.append(", isScam=");
                v2.append(bool);
                v2.append(")");
                return v2.toString();
            }
        }

        public /* synthetic */ Model(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Model() {
        }
    }

    @Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0012\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0013\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#$%&'(¨\u0006)"}, d2 = {"Lcom/reown/sign/client/Sign$Params;", "", "<init>", "()V", "Init", "Connect", "ConnectParams", "Authenticate", "FormatMessage", "Pair", "Approve", "Reject", "ApproveAuthenticate", "RejectAuthenticate", "Disconnect", "Response", "Request", "Update", "Ping", "Emit", "Extend", "DecryptMessage", "Lcom/reown/sign/client/Sign$Model$SentRequest;", "Lcom/reown/sign/client/Sign$Params$Approve;", "Lcom/reown/sign/client/Sign$Params$ApproveAuthenticate;", "Lcom/reown/sign/client/Sign$Params$Authenticate;", "Lcom/reown/sign/client/Sign$Params$Connect;", "Lcom/reown/sign/client/Sign$Params$ConnectParams;", "Lcom/reown/sign/client/Sign$Params$DecryptMessage;", "Lcom/reown/sign/client/Sign$Params$Disconnect;", "Lcom/reown/sign/client/Sign$Params$Emit;", "Lcom/reown/sign/client/Sign$Params$Extend;", "Lcom/reown/sign/client/Sign$Params$FormatMessage;", "Lcom/reown/sign/client/Sign$Params$Init;", "Lcom/reown/sign/client/Sign$Params$Pair;", "Lcom/reown/sign/client/Sign$Params$Ping;", "Lcom/reown/sign/client/Sign$Params$Reject;", "Lcom/reown/sign/client/Sign$Params$RejectAuthenticate;", "Lcom/reown/sign/client/Sign$Params$Request;", "Lcom/reown/sign/client/Sign$Params$Response;", "Lcom/reown/sign/client/Sign$Params$Update;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class Params {

        @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B_\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005\u0012\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\u0015\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u0017\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005HÆ\u0003J\u0017\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003Je\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u00052\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00052\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001f\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u001f\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\r¨\u0006 "}, d2 = {"Lcom/reown/sign/client/Sign$Params$Approve;", "Lcom/reown/sign/client/Sign$Params;", "proposerPublicKey", "", "namespaces", "", "Lcom/reown/sign/client/Sign$Model$Namespace$Session;", "properties", "scopedProperties", "relayProtocol", "<init>", "(Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/lang/String;)V", "getProposerPublicKey", "()Ljava/lang/String;", "getNamespaces", "()Ljava/util/Map;", "getProperties", "getScopedProperties", "getRelayProtocol", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Approve extends Params {
            @NotNull
            private final Map<String, Model.Namespace.Session> namespaces;
            @Nullable
            private final Map<String, String> properties;
            @NotNull
            private final String proposerPublicKey;
            @Nullable
            private final String relayProtocol;
            @Nullable
            private final Map<String, String> scopedProperties;

            /* JADX INFO: this call moved to the top of the method (can break code semantics) */
            public /* synthetic */ Approve(String str, Map map, Map map2, Map map3, String str2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                this(str, map, (i3 & 4) != 0 ? null : map2, (i3 & 8) != 0 ? null : map3, (i3 & 16) != 0 ? null : str2);
            }

            public static /* synthetic */ Approve copy$default(Approve approve, String str, Map<String, Model.Namespace.Session> map, Map<String, String> map2, Map<String, String> map3, String str2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = approve.proposerPublicKey;
                }
                if ((i3 & 2) != 0) {
                    map = approve.namespaces;
                }
                Map<String, Model.Namespace.Session> map4 = map;
                if ((i3 & 4) != 0) {
                    map2 = approve.properties;
                }
                Map<String, String> map5 = map2;
                if ((i3 & 8) != 0) {
                    map3 = approve.scopedProperties;
                }
                Map<String, String> map6 = map3;
                if ((i3 & 16) != 0) {
                    str2 = approve.relayProtocol;
                }
                return approve.copy(str, map4, map5, map6, str2);
            }

            @NotNull
            public final String component1() {
                return this.proposerPublicKey;
            }

            @NotNull
            public final Map<String, Model.Namespace.Session> component2() {
                return this.namespaces;
            }

            @Nullable
            public final Map<String, String> component3() {
                return this.properties;
            }

            @Nullable
            public final Map<String, String> component4() {
                return this.scopedProperties;
            }

            @Nullable
            public final String component5() {
                return this.relayProtocol;
            }

            @NotNull
            public final Approve copy(@NotNull String str, @NotNull Map<String, Model.Namespace.Session> map, @Nullable Map<String, String> map2, @Nullable Map<String, String> map3, @Nullable String str2) {
                Intrinsics.checkNotNullParameter(str, "proposerPublicKey");
                Intrinsics.checkNotNullParameter(map, "namespaces");
                return new Approve(str, map, map2, map3, str2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Approve)) {
                    return false;
                }
                Approve approve = (Approve) obj;
                return Intrinsics.areEqual((Object) this.proposerPublicKey, (Object) approve.proposerPublicKey) && Intrinsics.areEqual((Object) this.namespaces, (Object) approve.namespaces) && Intrinsics.areEqual((Object) this.properties, (Object) approve.properties) && Intrinsics.areEqual((Object) this.scopedProperties, (Object) approve.scopedProperties) && Intrinsics.areEqual((Object) this.relayProtocol, (Object) approve.relayProtocol);
            }

            @NotNull
            public final Map<String, Model.Namespace.Session> getNamespaces() {
                return this.namespaces;
            }

            @Nullable
            public final Map<String, String> getProperties() {
                return this.properties;
            }

            @NotNull
            public final String getProposerPublicKey() {
                return this.proposerPublicKey;
            }

            @Nullable
            public final String getRelayProtocol() {
                return this.relayProtocol;
            }

            @Nullable
            public final Map<String, String> getScopedProperties() {
                return this.scopedProperties;
            }

            public int hashCode() {
                int d2 = b.d(this.namespaces, this.proposerPublicKey.hashCode() * 31, 31);
                Map<String, String> map = this.properties;
                int i3 = 0;
                int hashCode = (d2 + (map == null ? 0 : map.hashCode())) * 31;
                Map<String, String> map2 = this.scopedProperties;
                int hashCode2 = (hashCode + (map2 == null ? 0 : map2.hashCode())) * 31;
                String str = this.relayProtocol;
                if (str != null) {
                    i3 = str.hashCode();
                }
                return hashCode2 + i3;
            }

            @NotNull
            public String toString() {
                String str = this.proposerPublicKey;
                Map<String, Model.Namespace.Session> map = this.namespaces;
                Map<String, String> map2 = this.properties;
                Map<String, String> map3 = this.scopedProperties;
                String str2 = this.relayProtocol;
                StringBuilder sb = new StringBuilder("Approve(proposerPublicKey=");
                sb.append(str);
                sb.append(", namespaces=");
                sb.append(map);
                sb.append(", properties=");
                sb.append(map2);
                sb.append(", scopedProperties=");
                sb.append(map3);
                sb.append(", relayProtocol=");
                return a.n(sb, str2, ")");
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Approve(@NotNull String str, @NotNull Map<String, Model.Namespace.Session> map, @Nullable Map<String, String> map2, @Nullable Map<String, String> map3, @Nullable String str2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "proposerPublicKey");
                Intrinsics.checkNotNullParameter(map, "namespaces");
                this.proposerPublicKey = str;
                this.namespaces = map;
                this.properties = map2;
                this.scopedProperties = map3;
                this.relayProtocol = str2;
            }
        }

        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/reown/sign/client/Sign$Params$ApproveAuthenticate;", "Lcom/reown/sign/client/Sign$Params;", "id", "", "cacaos", "", "Lcom/reown/sign/client/Sign$Model$Cacao;", "<init>", "(JLjava/util/List;)V", "getId", "()J", "getCacaos", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class ApproveAuthenticate extends Params {
            @NotNull
            private final List<Model.Cacao> cacaos;
            private final long id;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public ApproveAuthenticate(long j2, @NotNull List<Model.Cacao> list) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(list, "cacaos");
                this.id = j2;
                this.cacaos = list;
            }

            public static /* synthetic */ ApproveAuthenticate copy$default(ApproveAuthenticate approveAuthenticate, long j2, List<Model.Cacao> list, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    j2 = approveAuthenticate.id;
                }
                if ((i3 & 2) != 0) {
                    list = approveAuthenticate.cacaos;
                }
                return approveAuthenticate.copy(j2, list);
            }

            public final long component1() {
                return this.id;
            }

            @NotNull
            public final List<Model.Cacao> component2() {
                return this.cacaos;
            }

            @NotNull
            public final ApproveAuthenticate copy(long j2, @NotNull List<Model.Cacao> list) {
                Intrinsics.checkNotNullParameter(list, "cacaos");
                return new ApproveAuthenticate(j2, list);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof ApproveAuthenticate)) {
                    return false;
                }
                ApproveAuthenticate approveAuthenticate = (ApproveAuthenticate) obj;
                return this.id == approveAuthenticate.id && Intrinsics.areEqual((Object) this.cacaos, (Object) approveAuthenticate.cacaos);
            }

            @NotNull
            public final List<Model.Cacao> getCacaos() {
                return this.cacaos;
            }

            public final long getId() {
                return this.id;
            }

            public int hashCode() {
                return this.cacaos.hashCode() + (Long.hashCode(this.id) * 31);
            }

            @NotNull
            public String toString() {
                long j2 = this.id;
                List<Model.Cacao> list = this.cacaos;
                return "ApproveAuthenticate(id=" + j2 + ", cacaos=" + list + ")";
            }
        }

        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0010\t\n\u0002\b!\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005\u0012\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\u0011\u0010\u0012J\u000b\u0010#\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010,\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005HÆ\u0003J\u0011\u0010-\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005HÆ\u0003J\u0010\u0010.\u001a\u0004\u0018\u00010\u0010HÆ\u0003¢\u0006\u0002\u0010!J¨\u0001\u0010/\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00052\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÆ\u0001¢\u0006\u0002\u00100J\u0013\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u000104HÖ\u0003J\t\u00105\u001a\u000206HÖ\u0001J\t\u00107\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0014R\u0019\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0016R\u0019\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0016R\u0015\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\n\n\u0002\u0010\"\u001a\u0004\b \u0010!¨\u00068"}, d2 = {"Lcom/reown/sign/client/Sign$Params$Authenticate;", "Lcom/reown/sign/client/Sign$Params;", "pairingTopic", "", "chains", "", "domain", "nonce", "uri", "nbf", "exp", "statement", "requestId", "resources", "methods", "expiry", "", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/Long;)V", "getPairingTopic", "()Ljava/lang/String;", "getChains", "()Ljava/util/List;", "getDomain", "getNonce", "getUri", "getNbf", "getExp", "getStatement", "getRequestId", "getResources", "getMethods", "getExpiry", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/Long;)Lcom/reown/sign/client/Sign$Params$Authenticate;", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Authenticate extends Params {
            @NotNull
            private final List<String> chains;
            @NotNull
            private final String domain;
            @Nullable
            private final String exp;
            @Nullable
            private final Long expiry;
            @Nullable
            private final List<String> methods;
            @Nullable
            private final String nbf;
            @NotNull
            private final String nonce;
            @Nullable
            private final String pairingTopic;
            @Nullable
            private final String requestId;
            @Nullable
            private final List<String> resources;
            @Nullable
            private final String statement;
            @NotNull
            private final String uri;

            /* JADX WARNING: Illegal instructions before constructor call */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public /* synthetic */ Authenticate(java.lang.String r17, java.util.List r18, java.lang.String r19, java.lang.String r20, java.lang.String r21, java.lang.String r22, java.lang.String r23, java.lang.String r24, java.lang.String r25, java.util.List r26, java.util.List r27, java.lang.Long r28, int r29, kotlin.jvm.internal.DefaultConstructorMarker r30) {
                /*
                    r16 = this;
                    r0 = r29
                    r1 = r0 & 1
                    r2 = 0
                    if (r1 == 0) goto L_0x0009
                    r4 = r2
                    goto L_0x000b
                L_0x0009:
                    r4 = r17
                L_0x000b:
                    r1 = r0 & 32
                    if (r1 == 0) goto L_0x0011
                    r9 = r2
                    goto L_0x0013
                L_0x0011:
                    r9 = r22
                L_0x0013:
                    r1 = r0 & 64
                    if (r1 == 0) goto L_0x0019
                    r10 = r2
                    goto L_0x001b
                L_0x0019:
                    r10 = r23
                L_0x001b:
                    r1 = r0 & 128(0x80, float:1.794E-43)
                    if (r1 == 0) goto L_0x0021
                    r11 = r2
                    goto L_0x0023
                L_0x0021:
                    r11 = r24
                L_0x0023:
                    r1 = r0 & 256(0x100, float:3.59E-43)
                    if (r1 == 0) goto L_0x0029
                    r12 = r2
                    goto L_0x002b
                L_0x0029:
                    r12 = r25
                L_0x002b:
                    r1 = r0 & 512(0x200, float:7.175E-43)
                    if (r1 == 0) goto L_0x0031
                    r13 = r2
                    goto L_0x0033
                L_0x0031:
                    r13 = r26
                L_0x0033:
                    r1 = r0 & 1024(0x400, float:1.435E-42)
                    if (r1 == 0) goto L_0x0039
                    r14 = r2
                    goto L_0x003b
                L_0x0039:
                    r14 = r27
                L_0x003b:
                    r0 = r0 & 2048(0x800, float:2.87E-42)
                    if (r0 == 0) goto L_0x0041
                    r15 = r2
                    goto L_0x0043
                L_0x0041:
                    r15 = r28
                L_0x0043:
                    r3 = r16
                    r5 = r18
                    r6 = r19
                    r7 = r20
                    r8 = r21
                    r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.client.Sign.Params.Authenticate.<init>(java.lang.String, java.util.List, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.List, java.util.List, java.lang.Long, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
            }

            public static /* synthetic */ Authenticate copy$default(Authenticate authenticate, String str, List list, String str2, String str3, String str4, String str5, String str6, String str7, String str8, List list2, List list3, Long l2, int i3, Object obj) {
                Authenticate authenticate2 = authenticate;
                int i4 = i3;
                return authenticate.copy((i4 & 1) != 0 ? authenticate2.pairingTopic : str, (i4 & 2) != 0 ? authenticate2.chains : list, (i4 & 4) != 0 ? authenticate2.domain : str2, (i4 & 8) != 0 ? authenticate2.nonce : str3, (i4 & 16) != 0 ? authenticate2.uri : str4, (i4 & 32) != 0 ? authenticate2.nbf : str5, (i4 & 64) != 0 ? authenticate2.exp : str6, (i4 & 128) != 0 ? authenticate2.statement : str7, (i4 & 256) != 0 ? authenticate2.requestId : str8, (i4 & 512) != 0 ? authenticate2.resources : list2, (i4 & 1024) != 0 ? authenticate2.methods : list3, (i4 & 2048) != 0 ? authenticate2.expiry : l2);
            }

            @Nullable
            public final String component1() {
                return this.pairingTopic;
            }

            @Nullable
            public final List<String> component10() {
                return this.resources;
            }

            @Nullable
            public final List<String> component11() {
                return this.methods;
            }

            @Nullable
            public final Long component12() {
                return this.expiry;
            }

            @NotNull
            public final List<String> component2() {
                return this.chains;
            }

            @NotNull
            public final String component3() {
                return this.domain;
            }

            @NotNull
            public final String component4() {
                return this.nonce;
            }

            @NotNull
            public final String component5() {
                return this.uri;
            }

            @Nullable
            public final String component6() {
                return this.nbf;
            }

            @Nullable
            public final String component7() {
                return this.exp;
            }

            @Nullable
            public final String component8() {
                return this.statement;
            }

            @Nullable
            public final String component9() {
                return this.requestId;
            }

            @NotNull
            public final Authenticate copy(@Nullable String str, @NotNull List<String> list, @NotNull String str2, @NotNull String str3, @NotNull String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable List<String> list2, @Nullable List<String> list3, @Nullable Long l2) {
                List<String> list4 = list;
                Intrinsics.checkNotNullParameter(list4, "chains");
                String str9 = str2;
                Intrinsics.checkNotNullParameter(str9, "domain");
                String str10 = str3;
                Intrinsics.checkNotNullParameter(str10, "nonce");
                String str11 = str4;
                Intrinsics.checkNotNullParameter(str11, "uri");
                return new Authenticate(str, list4, str9, str10, str11, str5, str6, str7, str8, list2, list3, l2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Authenticate)) {
                    return false;
                }
                Authenticate authenticate = (Authenticate) obj;
                return Intrinsics.areEqual((Object) this.pairingTopic, (Object) authenticate.pairingTopic) && Intrinsics.areEqual((Object) this.chains, (Object) authenticate.chains) && Intrinsics.areEqual((Object) this.domain, (Object) authenticate.domain) && Intrinsics.areEqual((Object) this.nonce, (Object) authenticate.nonce) && Intrinsics.areEqual((Object) this.uri, (Object) authenticate.uri) && Intrinsics.areEqual((Object) this.nbf, (Object) authenticate.nbf) && Intrinsics.areEqual((Object) this.exp, (Object) authenticate.exp) && Intrinsics.areEqual((Object) this.statement, (Object) authenticate.statement) && Intrinsics.areEqual((Object) this.requestId, (Object) authenticate.requestId) && Intrinsics.areEqual((Object) this.resources, (Object) authenticate.resources) && Intrinsics.areEqual((Object) this.methods, (Object) authenticate.methods) && Intrinsics.areEqual((Object) this.expiry, (Object) authenticate.expiry);
            }

            @NotNull
            public final List<String> getChains() {
                return this.chains;
            }

            @NotNull
            public final String getDomain() {
                return this.domain;
            }

            @Nullable
            public final String getExp() {
                return this.exp;
            }

            @Nullable
            public final Long getExpiry() {
                return this.expiry;
            }

            @Nullable
            public final List<String> getMethods() {
                return this.methods;
            }

            @Nullable
            public final String getNbf() {
                return this.nbf;
            }

            @NotNull
            public final String getNonce() {
                return this.nonce;
            }

            @Nullable
            public final String getPairingTopic() {
                return this.pairingTopic;
            }

            @Nullable
            public final String getRequestId() {
                return this.requestId;
            }

            @Nullable
            public final List<String> getResources() {
                return this.resources;
            }

            @Nullable
            public final String getStatement() {
                return this.statement;
            }

            @NotNull
            public final String getUri() {
                return this.uri;
            }

            public int hashCode() {
                String str = this.pairingTopic;
                int i3 = 0;
                int i4 = androidx.compose.animation.core.a.i(this.uri, androidx.compose.animation.core.a.i(this.nonce, androidx.compose.animation.core.a.i(this.domain, androidx.compose.animation.core.a.j(this.chains, (str == null ? 0 : str.hashCode()) * 31, 31), 31), 31), 31);
                String str2 = this.nbf;
                int hashCode = (i4 + (str2 == null ? 0 : str2.hashCode())) * 31;
                String str3 = this.exp;
                int hashCode2 = (hashCode + (str3 == null ? 0 : str3.hashCode())) * 31;
                String str4 = this.statement;
                int hashCode3 = (hashCode2 + (str4 == null ? 0 : str4.hashCode())) * 31;
                String str5 = this.requestId;
                int hashCode4 = (hashCode3 + (str5 == null ? 0 : str5.hashCode())) * 31;
                List<String> list = this.resources;
                int hashCode5 = (hashCode4 + (list == null ? 0 : list.hashCode())) * 31;
                List<String> list2 = this.methods;
                int hashCode6 = (hashCode5 + (list2 == null ? 0 : list2.hashCode())) * 31;
                Long l2 = this.expiry;
                if (l2 != null) {
                    i3 = l2.hashCode();
                }
                return hashCode6 + i3;
            }

            @NotNull
            public String toString() {
                String str = this.pairingTopic;
                List<String> list = this.chains;
                String str2 = this.domain;
                String str3 = this.nonce;
                String str4 = this.uri;
                String str5 = this.nbf;
                String str6 = this.exp;
                String str7 = this.statement;
                String str8 = this.requestId;
                List<String> list2 = this.resources;
                List<String> list3 = this.methods;
                Long l2 = this.expiry;
                StringBuilder sb = new StringBuilder("Authenticate(pairingTopic=");
                sb.append(str);
                sb.append(", chains=");
                sb.append(list);
                sb.append(", domain=");
                b.w(sb, str2, ", nonce=", str3, ", uri=");
                b.w(sb, str4, ", nbf=", str5, ", exp=");
                b.w(sb, str6, ", statement=", str7, ", requestId=");
                sb.append(str8);
                sb.append(", resources=");
                sb.append(list2);
                sb.append(", methods=");
                sb.append(list3);
                sb.append(", expiry=");
                sb.append(l2);
                sb.append(")");
                return sb.toString();
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Authenticate(@Nullable String str, @NotNull List<String> list, @NotNull String str2, @NotNull String str3, @NotNull String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable List<String> list2, @Nullable List<String> list3, @Nullable Long l2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(list, "chains");
                Intrinsics.checkNotNullParameter(str2, "domain");
                Intrinsics.checkNotNullParameter(str3, "nonce");
                Intrinsics.checkNotNullParameter(str4, "uri");
                this.pairingTopic = str;
                this.chains = list;
                this.domain = str2;
                this.nonce = str3;
                this.uri = str4;
                this.nbf = str5;
                this.exp = str6;
                this.statement = str7;
                this.requestId = str8;
                this.resources = list2;
                this.methods = list3;
                this.expiry = l2;
            }
        }

        @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001Bo\u0012\u0016\b\u0002\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003\u0012\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003\u0012\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003HÆ\u0003J\u0017\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003HÆ\u0003J\u0017\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0017\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\nHÆ\u0003Js\u0010\u0019\u001a\u00020\u00002\u0016\b\u0002\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00032\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00032\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dHÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\u0004HÖ\u0001R\u001f\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001f\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u001f\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u001f\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006!"}, d2 = {"Lcom/reown/sign/client/Sign$Params$Connect;", "Lcom/reown/sign/client/Sign$Params;", "namespaces", "", "", "Lcom/reown/sign/client/Sign$Model$Namespace$Proposal;", "optionalNamespaces", "properties", "scopedProperties", "pairing", "Lcom/reown/android/Core$Model$Pairing;", "<init>", "(Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Lcom/reown/android/Core$Model$Pairing;)V", "getNamespaces", "()Ljava/util/Map;", "getOptionalNamespaces", "getProperties", "getScopedProperties", "getPairing", "()Lcom/reown/android/Core$Model$Pairing;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Connect extends Params {
            @Nullable
            private final Map<String, Model.Namespace.Proposal> namespaces;
            @Nullable
            private final Map<String, Model.Namespace.Proposal> optionalNamespaces;
            @NotNull
            private final Core.Model.Pairing pairing;
            @Nullable
            private final Map<String, String> properties;
            @Nullable
            private final Map<String, String> scopedProperties;

            /* JADX INFO: this call moved to the top of the method (can break code semantics) */
            public /* synthetic */ Connect(Map map, Map map2, Map map3, Map map4, Core.Model.Pairing pairing2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                this((i3 & 1) != 0 ? null : map, (i3 & 2) != 0 ? null : map2, (i3 & 4) != 0 ? null : map3, (i3 & 8) != 0 ? null : map4, pairing2);
            }

            public static /* synthetic */ Connect copy$default(Connect connect, Map<String, Model.Namespace.Proposal> map, Map<String, Model.Namespace.Proposal> map2, Map<String, String> map3, Map<String, String> map4, Core.Model.Pairing pairing2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    map = connect.namespaces;
                }
                if ((i3 & 2) != 0) {
                    map2 = connect.optionalNamespaces;
                }
                Map<String, Model.Namespace.Proposal> map5 = map2;
                if ((i3 & 4) != 0) {
                    map3 = connect.properties;
                }
                Map<String, String> map6 = map3;
                if ((i3 & 8) != 0) {
                    map4 = connect.scopedProperties;
                }
                Map<String, String> map7 = map4;
                if ((i3 & 16) != 0) {
                    pairing2 = connect.pairing;
                }
                return connect.copy(map, map5, map6, map7, pairing2);
            }

            @Nullable
            public final Map<String, Model.Namespace.Proposal> component1() {
                return this.namespaces;
            }

            @Nullable
            public final Map<String, Model.Namespace.Proposal> component2() {
                return this.optionalNamespaces;
            }

            @Nullable
            public final Map<String, String> component3() {
                return this.properties;
            }

            @Nullable
            public final Map<String, String> component4() {
                return this.scopedProperties;
            }

            @NotNull
            public final Core.Model.Pairing component5() {
                return this.pairing;
            }

            @NotNull
            public final Connect copy(@Nullable Map<String, Model.Namespace.Proposal> map, @Nullable Map<String, Model.Namespace.Proposal> map2, @Nullable Map<String, String> map3, @Nullable Map<String, String> map4, @NotNull Core.Model.Pairing pairing2) {
                Intrinsics.checkNotNullParameter(pairing2, "pairing");
                return new Connect(map, map2, map3, map4, pairing2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Connect)) {
                    return false;
                }
                Connect connect = (Connect) obj;
                return Intrinsics.areEqual((Object) this.namespaces, (Object) connect.namespaces) && Intrinsics.areEqual((Object) this.optionalNamespaces, (Object) connect.optionalNamespaces) && Intrinsics.areEqual((Object) this.properties, (Object) connect.properties) && Intrinsics.areEqual((Object) this.scopedProperties, (Object) connect.scopedProperties) && Intrinsics.areEqual((Object) this.pairing, (Object) connect.pairing);
            }

            @Nullable
            public final Map<String, Model.Namespace.Proposal> getNamespaces() {
                return this.namespaces;
            }

            @Nullable
            public final Map<String, Model.Namespace.Proposal> getOptionalNamespaces() {
                return this.optionalNamespaces;
            }

            @NotNull
            public final Core.Model.Pairing getPairing() {
                return this.pairing;
            }

            @Nullable
            public final Map<String, String> getProperties() {
                return this.properties;
            }

            @Nullable
            public final Map<String, String> getScopedProperties() {
                return this.scopedProperties;
            }

            public int hashCode() {
                Map<String, Model.Namespace.Proposal> map = this.namespaces;
                int i3 = 0;
                int hashCode = (map == null ? 0 : map.hashCode()) * 31;
                Map<String, Model.Namespace.Proposal> map2 = this.optionalNamespaces;
                int hashCode2 = (hashCode + (map2 == null ? 0 : map2.hashCode())) * 31;
                Map<String, String> map3 = this.properties;
                int hashCode3 = (hashCode2 + (map3 == null ? 0 : map3.hashCode())) * 31;
                Map<String, String> map4 = this.scopedProperties;
                if (map4 != null) {
                    i3 = map4.hashCode();
                }
                return this.pairing.hashCode() + ((hashCode3 + i3) * 31);
            }

            @NotNull
            public String toString() {
                Map<String, Model.Namespace.Proposal> map = this.namespaces;
                Map<String, Model.Namespace.Proposal> map2 = this.optionalNamespaces;
                Map<String, String> map3 = this.properties;
                Map<String, String> map4 = this.scopedProperties;
                Core.Model.Pairing pairing2 = this.pairing;
                return "Connect(namespaces=" + map + ", optionalNamespaces=" + map2 + ", properties=" + map3 + ", scopedProperties=" + map4 + ", pairing=" + pairing2 + ")";
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Connect(@Nullable Map<String, Model.Namespace.Proposal> map, @Nullable Map<String, Model.Namespace.Proposal> map2, @Nullable Map<String, String> map3, @Nullable Map<String, String> map4, @NotNull Core.Model.Pairing pairing2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(pairing2, "pairing");
                this.namespaces = map;
                this.optionalNamespaces = map2;
                this.properties = map3;
                this.scopedProperties = map4;
                this.pairing = pairing2;
            }
        }

        @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BW\u0012\u0016\b\u0002\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003\u0012\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003HÆ\u0003J\u0017\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0017\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\tHÆ\u0003J[\u0010\u0016\u001a\u00020\u00002\u0016\b\u0002\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00032\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0004HÖ\u0001R\u001f\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001f\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u001f\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, d2 = {"Lcom/reown/sign/client/Sign$Params$ConnectParams;", "Lcom/reown/sign/client/Sign$Params;", "sessionNamespaces", "", "", "Lcom/reown/sign/client/Sign$Model$Namespace$Proposal;", "properties", "scopedProperties", "pairing", "Lcom/reown/android/Core$Model$Pairing;", "<init>", "(Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Lcom/reown/android/Core$Model$Pairing;)V", "getSessionNamespaces", "()Ljava/util/Map;", "getProperties", "getScopedProperties", "getPairing", "()Lcom/reown/android/Core$Model$Pairing;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class ConnectParams extends Params {
            @NotNull
            private final Core.Model.Pairing pairing;
            @Nullable
            private final Map<String, String> properties;
            @Nullable
            private final Map<String, String> scopedProperties;
            @Nullable
            private final Map<String, Model.Namespace.Proposal> sessionNamespaces;

            /* JADX INFO: this call moved to the top of the method (can break code semantics) */
            public /* synthetic */ ConnectParams(Map map, Map map2, Map map3, Core.Model.Pairing pairing2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                this((i3 & 1) != 0 ? null : map, (i3 & 2) != 0 ? null : map2, (i3 & 4) != 0 ? null : map3, pairing2);
            }

            public static /* synthetic */ ConnectParams copy$default(ConnectParams connectParams, Map<String, Model.Namespace.Proposal> map, Map<String, String> map2, Map<String, String> map3, Core.Model.Pairing pairing2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    map = connectParams.sessionNamespaces;
                }
                if ((i3 & 2) != 0) {
                    map2 = connectParams.properties;
                }
                if ((i3 & 4) != 0) {
                    map3 = connectParams.scopedProperties;
                }
                if ((i3 & 8) != 0) {
                    pairing2 = connectParams.pairing;
                }
                return connectParams.copy(map, map2, map3, pairing2);
            }

            @Nullable
            public final Map<String, Model.Namespace.Proposal> component1() {
                return this.sessionNamespaces;
            }

            @Nullable
            public final Map<String, String> component2() {
                return this.properties;
            }

            @Nullable
            public final Map<String, String> component3() {
                return this.scopedProperties;
            }

            @NotNull
            public final Core.Model.Pairing component4() {
                return this.pairing;
            }

            @NotNull
            public final ConnectParams copy(@Nullable Map<String, Model.Namespace.Proposal> map, @Nullable Map<String, String> map2, @Nullable Map<String, String> map3, @NotNull Core.Model.Pairing pairing2) {
                Intrinsics.checkNotNullParameter(pairing2, "pairing");
                return new ConnectParams(map, map2, map3, pairing2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof ConnectParams)) {
                    return false;
                }
                ConnectParams connectParams = (ConnectParams) obj;
                return Intrinsics.areEqual((Object) this.sessionNamespaces, (Object) connectParams.sessionNamespaces) && Intrinsics.areEqual((Object) this.properties, (Object) connectParams.properties) && Intrinsics.areEqual((Object) this.scopedProperties, (Object) connectParams.scopedProperties) && Intrinsics.areEqual((Object) this.pairing, (Object) connectParams.pairing);
            }

            @NotNull
            public final Core.Model.Pairing getPairing() {
                return this.pairing;
            }

            @Nullable
            public final Map<String, String> getProperties() {
                return this.properties;
            }

            @Nullable
            public final Map<String, String> getScopedProperties() {
                return this.scopedProperties;
            }

            @Nullable
            public final Map<String, Model.Namespace.Proposal> getSessionNamespaces() {
                return this.sessionNamespaces;
            }

            public int hashCode() {
                Map<String, Model.Namespace.Proposal> map = this.sessionNamespaces;
                int i3 = 0;
                int hashCode = (map == null ? 0 : map.hashCode()) * 31;
                Map<String, String> map2 = this.properties;
                int hashCode2 = (hashCode + (map2 == null ? 0 : map2.hashCode())) * 31;
                Map<String, String> map3 = this.scopedProperties;
                if (map3 != null) {
                    i3 = map3.hashCode();
                }
                return this.pairing.hashCode() + ((hashCode2 + i3) * 31);
            }

            @NotNull
            public String toString() {
                Map<String, Model.Namespace.Proposal> map = this.sessionNamespaces;
                Map<String, String> map2 = this.properties;
                Map<String, String> map3 = this.scopedProperties;
                Core.Model.Pairing pairing2 = this.pairing;
                return "ConnectParams(sessionNamespaces=" + map + ", properties=" + map2 + ", scopedProperties=" + map3 + ", pairing=" + pairing2 + ")";
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public ConnectParams(@Nullable Map<String, Model.Namespace.Proposal> map, @Nullable Map<String, String> map2, @Nullable Map<String, String> map3, @NotNull Core.Model.Pairing pairing2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(pairing2, "pairing");
                this.sessionNamespaces = map;
                this.properties = map2;
                this.scopedProperties = map3;
                this.pairing = pairing2;
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/reown/sign/client/Sign$Params$DecryptMessage;", "Lcom/reown/sign/client/Sign$Params;", "topic", "", "encryptedMessage", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getTopic", "()Ljava/lang/String;", "getEncryptedMessage", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class DecryptMessage extends Params {
            @NotNull
            private final String encryptedMessage;
            @NotNull
            private final String topic;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public DecryptMessage(@NotNull String str, @NotNull String str2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                Intrinsics.checkNotNullParameter(str2, "encryptedMessage");
                this.topic = str;
                this.encryptedMessage = str2;
            }

            public static /* synthetic */ DecryptMessage copy$default(DecryptMessage decryptMessage, String str, String str2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = decryptMessage.topic;
                }
                if ((i3 & 2) != 0) {
                    str2 = decryptMessage.encryptedMessage;
                }
                return decryptMessage.copy(str, str2);
            }

            @NotNull
            public final String component1() {
                return this.topic;
            }

            @NotNull
            public final String component2() {
                return this.encryptedMessage;
            }

            @NotNull
            public final DecryptMessage copy(@NotNull String str, @NotNull String str2) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                Intrinsics.checkNotNullParameter(str2, "encryptedMessage");
                return new DecryptMessage(str, str2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof DecryptMessage)) {
                    return false;
                }
                DecryptMessage decryptMessage = (DecryptMessage) obj;
                return Intrinsics.areEqual((Object) this.topic, (Object) decryptMessage.topic) && Intrinsics.areEqual((Object) this.encryptedMessage, (Object) decryptMessage.encryptedMessage);
            }

            @NotNull
            public final String getEncryptedMessage() {
                return this.encryptedMessage;
            }

            @NotNull
            public final String getTopic() {
                return this.topic;
            }

            public int hashCode() {
                return this.encryptedMessage.hashCode() + (this.topic.hashCode() * 31);
            }

            @NotNull
            public String toString() {
                return C0118y.g("DecryptMessage(topic=", this.topic, ", encryptedMessage=", this.encryptedMessage, ")");
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/sign/client/Sign$Params$Disconnect;", "Lcom/reown/sign/client/Sign$Params;", "sessionTopic", "", "<init>", "(Ljava/lang/String;)V", "getSessionTopic", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Disconnect extends Params {
            @NotNull
            private final String sessionTopic;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Disconnect(@NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "sessionTopic");
                this.sessionTopic = str;
            }

            public static /* synthetic */ Disconnect copy$default(Disconnect disconnect, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = disconnect.sessionTopic;
                }
                return disconnect.copy(str);
            }

            @NotNull
            public final String component1() {
                return this.sessionTopic;
            }

            @NotNull
            public final Disconnect copy(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "sessionTopic");
                return new Disconnect(str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof Disconnect) && Intrinsics.areEqual((Object) this.sessionTopic, (Object) ((Disconnect) obj).sessionTopic);
            }

            @NotNull
            public final String getSessionTopic() {
                return this.sessionTopic;
            }

            public int hashCode() {
                return this.sessionTopic.hashCode();
            }

            @NotNull
            public String toString() {
                return a.l("Disconnect(sessionTopic=", this.sessionTopic, ")");
            }
        }

        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/reown/sign/client/Sign$Params$Emit;", "Lcom/reown/sign/client/Sign$Params;", "topic", "", "event", "Lcom/reown/sign/client/Sign$Model$SessionEvent;", "chainId", "<init>", "(Ljava/lang/String;Lcom/reown/sign/client/Sign$Model$SessionEvent;Ljava/lang/String;)V", "getTopic", "()Ljava/lang/String;", "getEvent", "()Lcom/reown/sign/client/Sign$Model$SessionEvent;", "getChainId", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Emit extends Params {
            @NotNull
            private final String chainId;
            @NotNull
            private final Model.SessionEvent event;
            @NotNull
            private final String topic;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Emit(@NotNull String str, @NotNull Model.SessionEvent sessionEvent, @NotNull String str2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                Intrinsics.checkNotNullParameter(sessionEvent, NotificationCompat.CATEGORY_EVENT);
                Intrinsics.checkNotNullParameter(str2, "chainId");
                this.topic = str;
                this.event = sessionEvent;
                this.chainId = str2;
            }

            public static /* synthetic */ Emit copy$default(Emit emit, String str, Model.SessionEvent sessionEvent, String str2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = emit.topic;
                }
                if ((i3 & 2) != 0) {
                    sessionEvent = emit.event;
                }
                if ((i3 & 4) != 0) {
                    str2 = emit.chainId;
                }
                return emit.copy(str, sessionEvent, str2);
            }

            @NotNull
            public final String component1() {
                return this.topic;
            }

            @NotNull
            public final Model.SessionEvent component2() {
                return this.event;
            }

            @NotNull
            public final String component3() {
                return this.chainId;
            }

            @NotNull
            public final Emit copy(@NotNull String str, @NotNull Model.SessionEvent sessionEvent, @NotNull String str2) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                Intrinsics.checkNotNullParameter(sessionEvent, NotificationCompat.CATEGORY_EVENT);
                Intrinsics.checkNotNullParameter(str2, "chainId");
                return new Emit(str, sessionEvent, str2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Emit)) {
                    return false;
                }
                Emit emit = (Emit) obj;
                return Intrinsics.areEqual((Object) this.topic, (Object) emit.topic) && Intrinsics.areEqual((Object) this.event, (Object) emit.event) && Intrinsics.areEqual((Object) this.chainId, (Object) emit.chainId);
            }

            @NotNull
            public final String getChainId() {
                return this.chainId;
            }

            @NotNull
            public final Model.SessionEvent getEvent() {
                return this.event;
            }

            @NotNull
            public final String getTopic() {
                return this.topic;
            }

            public int hashCode() {
                int hashCode = this.event.hashCode();
                return this.chainId.hashCode() + ((hashCode + (this.topic.hashCode() * 31)) * 31);
            }

            @NotNull
            public String toString() {
                String str = this.topic;
                Model.SessionEvent sessionEvent = this.event;
                String str2 = this.chainId;
                StringBuilder sb = new StringBuilder("Emit(topic=");
                sb.append(str);
                sb.append(", event=");
                sb.append(sessionEvent);
                sb.append(", chainId=");
                return a.n(sb, str2, ")");
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/sign/client/Sign$Params$Extend;", "Lcom/reown/sign/client/Sign$Params;", "topic", "", "<init>", "(Ljava/lang/String;)V", "getTopic", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Extend extends Params {
            @NotNull
            private final String topic;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Extend(@NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                this.topic = str;
            }

            public static /* synthetic */ Extend copy$default(Extend extend, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = extend.topic;
                }
                return extend.copy(str);
            }

            @NotNull
            public final String component1() {
                return this.topic;
            }

            @NotNull
            public final Extend copy(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                return new Extend(str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof Extend) && Intrinsics.areEqual((Object) this.topic, (Object) ((Extend) obj).topic);
            }

            @NotNull
            public final String getTopic() {
                return this.topic;
            }

            public int hashCode() {
                return this.topic.hashCode();
            }

            @NotNull
            public String toString() {
                return a.l("Extend(topic=", this.topic, ")");
            }
        }

        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/reown/sign/client/Sign$Params$FormatMessage;", "Lcom/reown/sign/client/Sign$Params;", "payloadParams", "Lcom/reown/sign/client/Sign$Model$PayloadParams;", "iss", "", "<init>", "(Lcom/reown/sign/client/Sign$Model$PayloadParams;Ljava/lang/String;)V", "getPayloadParams", "()Lcom/reown/sign/client/Sign$Model$PayloadParams;", "getIss", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class FormatMessage extends Params {
            @NotNull
            private final String iss;
            @NotNull
            private final Model.PayloadParams payloadParams;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public FormatMessage(@NotNull Model.PayloadParams payloadParams2, @NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(payloadParams2, "payloadParams");
                Intrinsics.checkNotNullParameter(str, "iss");
                this.payloadParams = payloadParams2;
                this.iss = str;
            }

            public static /* synthetic */ FormatMessage copy$default(FormatMessage formatMessage, Model.PayloadParams payloadParams2, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    payloadParams2 = formatMessage.payloadParams;
                }
                if ((i3 & 2) != 0) {
                    str = formatMessage.iss;
                }
                return formatMessage.copy(payloadParams2, str);
            }

            @NotNull
            public final Model.PayloadParams component1() {
                return this.payloadParams;
            }

            @NotNull
            public final String component2() {
                return this.iss;
            }

            @NotNull
            public final FormatMessage copy(@NotNull Model.PayloadParams payloadParams2, @NotNull String str) {
                Intrinsics.checkNotNullParameter(payloadParams2, "payloadParams");
                Intrinsics.checkNotNullParameter(str, "iss");
                return new FormatMessage(payloadParams2, str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof FormatMessage)) {
                    return false;
                }
                FormatMessage formatMessage = (FormatMessage) obj;
                return Intrinsics.areEqual((Object) this.payloadParams, (Object) formatMessage.payloadParams) && Intrinsics.areEqual((Object) this.iss, (Object) formatMessage.iss);
            }

            @NotNull
            public final String getIss() {
                return this.iss;
            }

            @NotNull
            public final Model.PayloadParams getPayloadParams() {
                return this.payloadParams;
            }

            public int hashCode() {
                return this.iss.hashCode() + (this.payloadParams.hashCode() * 31);
            }

            @NotNull
            public String toString() {
                Model.PayloadParams payloadParams2 = this.payloadParams;
                String str = this.iss;
                return "FormatMessage(payloadParams=" + payloadParams2 + ", iss=" + str + ")";
            }
        }

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/reown/sign/client/Sign$Params$Init;", "Lcom/reown/sign/client/Sign$Params;", "core", "Lcom/reown/android/CoreInterface;", "<init>", "(Lcom/reown/android/CoreInterface;)V", "getCore", "()Lcom/reown/android/CoreInterface;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Init extends Params {
            @NotNull
            private final CoreInterface core;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Init(@NotNull CoreInterface coreInterface) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(coreInterface, "core");
                this.core = coreInterface;
            }

            public static /* synthetic */ Init copy$default(Init init, CoreInterface coreInterface, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    coreInterface = init.core;
                }
                return init.copy(coreInterface);
            }

            @NotNull
            public final CoreInterface component1() {
                return this.core;
            }

            @NotNull
            public final Init copy(@NotNull CoreInterface coreInterface) {
                Intrinsics.checkNotNullParameter(coreInterface, "core");
                return new Init(coreInterface);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof Init) && Intrinsics.areEqual((Object) this.core, (Object) ((Init) obj).core);
            }

            @NotNull
            public final CoreInterface getCore() {
                return this.core;
            }

            public int hashCode() {
                return this.core.hashCode();
            }

            @NotNull
            public String toString() {
                CoreInterface coreInterface = this.core;
                return "Init(core=" + coreInterface + ")";
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/sign/client/Sign$Params$Pair;", "Lcom/reown/sign/client/Sign$Params;", "uri", "", "<init>", "(Ljava/lang/String;)V", "getUri", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Pair extends Params {
            @NotNull
            private final String uri;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Pair(@NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "uri");
                this.uri = str;
            }

            public static /* synthetic */ Pair copy$default(Pair pair, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = pair.uri;
                }
                return pair.copy(str);
            }

            @NotNull
            public final String component1() {
                return this.uri;
            }

            @NotNull
            public final Pair copy(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "uri");
                return new Pair(str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof Pair) && Intrinsics.areEqual((Object) this.uri, (Object) ((Pair) obj).uri);
            }

            @NotNull
            public final String getUri() {
                return this.uri;
            }

            public int hashCode() {
                return this.uri.hashCode();
            }

            @NotNull
            public String toString() {
                return a.l("Pair(uri=", this.uri, ")");
            }
        }

        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u000e\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b\u000f\u0010\u000bJ$\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b¨\u0006\u001a"}, d2 = {"Lcom/reown/sign/client/Sign$Params$Ping;", "Lcom/reown/sign/client/Sign$Params;", "topic", "", "timeout", "Lkotlin/time/Duration;", "<init>", "(Ljava/lang/String;JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getTopic", "()Ljava/lang/String;", "getTimeout-UwyO8pc", "()J", "J", "component1", "component2", "component2-UwyO8pc", "copy", "copy-HG0u8IE", "(Ljava/lang/String;J)Lcom/reown/sign/client/Sign$Params$Ping;", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Ping extends Params {
            private final long timeout;
            @NotNull
            private final String topic;

            public /* synthetic */ Ping(String str, long j2, DefaultConstructorMarker defaultConstructorMarker) {
                this(str, j2);
            }

            /* renamed from: copy-HG0u8IE$default  reason: not valid java name */
            public static /* synthetic */ Ping m8867copyHG0u8IE$default(Ping ping, String str, long j2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = ping.topic;
                }
                if ((i3 & 2) != 0) {
                    j2 = ping.timeout;
                }
                return ping.m8869copyHG0u8IE(str, j2);
            }

            @NotNull
            public final String component1() {
                return this.topic;
            }

            /* renamed from: component2-UwyO8pc  reason: not valid java name */
            public final long m8868component2UwyO8pc() {
                return this.timeout;
            }

            @NotNull
            /* renamed from: copy-HG0u8IE  reason: not valid java name */
            public final Ping m8869copyHG0u8IE(@NotNull String str, long j2) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                return new Ping(str, j2, (DefaultConstructorMarker) null);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Ping)) {
                    return false;
                }
                Ping ping = (Ping) obj;
                return Intrinsics.areEqual((Object) this.topic, (Object) ping.topic) && Duration.m10319equalsimpl0(this.timeout, ping.timeout);
            }

            /* renamed from: getTimeout-UwyO8pc  reason: not valid java name */
            public final long m8870getTimeoutUwyO8pc() {
                return this.timeout;
            }

            @NotNull
            public final String getTopic() {
                return this.topic;
            }

            public int hashCode() {
                return Duration.m10335hashCodeimpl(this.timeout) + (this.topic.hashCode() * 31);
            }

            @NotNull
            public String toString() {
                return C0118y.g("Ping(topic=", this.topic, ", timeout=", Duration.m10354toStringimpl(this.timeout), ")");
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            private Ping(String str, long j2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                this.topic = str;
                this.timeout = j2;
            }

            /* JADX WARNING: Illegal instructions before constructor call */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public /* synthetic */ Ping(java.lang.String r1, long r2, int r4, kotlin.jvm.internal.DefaultConstructorMarker r5) {
                /*
                    r0 = this;
                    r4 = r4 & 2
                    if (r4 == 0) goto L_0x000e
                    kotlin.time.Duration$Companion r2 = kotlin.time.Duration.Companion
                    r2 = 30
                    kotlin.time.DurationUnit r3 = kotlin.time.DurationUnit.SECONDS
                    long r2 = kotlin.time.DurationKt.toDuration((int) r2, (kotlin.time.DurationUnit) r3)
                L_0x000e:
                    r4 = 0
                    r0.<init>(r1, r2, r4)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.client.Sign.Params.Ping.<init>(java.lang.String, long, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/reown/sign/client/Sign$Params$Reject;", "Lcom/reown/sign/client/Sign$Params;", "proposerPublicKey", "", "reason", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getProposerPublicKey", "()Ljava/lang/String;", "getReason", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Reject extends Params {
            @NotNull
            private final String proposerPublicKey;
            @NotNull
            private final String reason;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Reject(@NotNull String str, @NotNull String str2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "proposerPublicKey");
                Intrinsics.checkNotNullParameter(str2, "reason");
                this.proposerPublicKey = str;
                this.reason = str2;
            }

            public static /* synthetic */ Reject copy$default(Reject reject, String str, String str2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = reject.proposerPublicKey;
                }
                if ((i3 & 2) != 0) {
                    str2 = reject.reason;
                }
                return reject.copy(str, str2);
            }

            @NotNull
            public final String component1() {
                return this.proposerPublicKey;
            }

            @NotNull
            public final String component2() {
                return this.reason;
            }

            @NotNull
            public final Reject copy(@NotNull String str, @NotNull String str2) {
                Intrinsics.checkNotNullParameter(str, "proposerPublicKey");
                Intrinsics.checkNotNullParameter(str2, "reason");
                return new Reject(str, str2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Reject)) {
                    return false;
                }
                Reject reject = (Reject) obj;
                return Intrinsics.areEqual((Object) this.proposerPublicKey, (Object) reject.proposerPublicKey) && Intrinsics.areEqual((Object) this.reason, (Object) reject.reason);
            }

            @NotNull
            public final String getProposerPublicKey() {
                return this.proposerPublicKey;
            }

            @NotNull
            public final String getReason() {
                return this.reason;
            }

            public int hashCode() {
                return this.reason.hashCode() + (this.proposerPublicKey.hashCode() * 31);
            }

            @NotNull
            public String toString() {
                return C0118y.g("Reject(proposerPublicKey=", this.proposerPublicKey, ", reason=", this.reason, ")");
            }
        }

        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/reown/sign/client/Sign$Params$RejectAuthenticate;", "Lcom/reown/sign/client/Sign$Params;", "id", "", "reason", "", "<init>", "(JLjava/lang/String;)V", "getId", "()J", "getReason", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class RejectAuthenticate extends Params {
            private final long id;
            @NotNull
            private final String reason;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public RejectAuthenticate(long j2, @NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "reason");
                this.id = j2;
                this.reason = str;
            }

            public static /* synthetic */ RejectAuthenticate copy$default(RejectAuthenticate rejectAuthenticate, long j2, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    j2 = rejectAuthenticate.id;
                }
                if ((i3 & 2) != 0) {
                    str = rejectAuthenticate.reason;
                }
                return rejectAuthenticate.copy(j2, str);
            }

            public final long component1() {
                return this.id;
            }

            @NotNull
            public final String component2() {
                return this.reason;
            }

            @NotNull
            public final RejectAuthenticate copy(long j2, @NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "reason");
                return new RejectAuthenticate(j2, str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof RejectAuthenticate)) {
                    return false;
                }
                RejectAuthenticate rejectAuthenticate = (RejectAuthenticate) obj;
                return this.id == rejectAuthenticate.id && Intrinsics.areEqual((Object) this.reason, (Object) rejectAuthenticate.reason);
            }

            public final long getId() {
                return this.id;
            }

            @NotNull
            public final String getReason() {
                return this.reason;
            }

            public int hashCode() {
                return this.reason.hashCode() + (Long.hashCode(this.id) * 31);
            }

            @NotNull
            public String toString() {
                StringBuilder v2 = androidx.work.impl.a.v(this.id, "RejectAuthenticate(id=", ", reason=", this.reason);
                v2.append(")");
                return v2.toString();
            }
        }

        @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0017\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u0011JB\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u0010\u0019J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dHÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011¨\u0006!"}, d2 = {"Lcom/reown/sign/client/Sign$Params$Request;", "Lcom/reown/sign/client/Sign$Params;", "sessionTopic", "", "method", "params", "chainId", "expiry", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;)V", "getSessionTopic", "()Ljava/lang/String;", "getMethod", "getParams", "getChainId", "getExpiry", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;)Lcom/reown/sign/client/Sign$Params$Request;", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Request extends Params {
            @NotNull
            private final String chainId;
            @Nullable
            private final Long expiry;
            @NotNull
            private final String method;
            @NotNull
            private final String params;
            @NotNull
            private final String sessionTopic;

            /* JADX INFO: this call moved to the top of the method (can break code semantics) */
            public /* synthetic */ Request(String str, String str2, String str3, String str4, Long l2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                this(str, str2, str3, str4, (i3 & 16) != 0 ? null : l2);
            }

            public static /* synthetic */ Request copy$default(Request request, String str, String str2, String str3, String str4, Long l2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = request.sessionTopic;
                }
                if ((i3 & 2) != 0) {
                    str2 = request.method;
                }
                String str5 = str2;
                if ((i3 & 4) != 0) {
                    str3 = request.params;
                }
                String str6 = str3;
                if ((i3 & 8) != 0) {
                    str4 = request.chainId;
                }
                String str7 = str4;
                if ((i3 & 16) != 0) {
                    l2 = request.expiry;
                }
                return request.copy(str, str5, str6, str7, l2);
            }

            @NotNull
            public final String component1() {
                return this.sessionTopic;
            }

            @NotNull
            public final String component2() {
                return this.method;
            }

            @NotNull
            public final String component3() {
                return this.params;
            }

            @NotNull
            public final String component4() {
                return this.chainId;
            }

            @Nullable
            public final Long component5() {
                return this.expiry;
            }

            @NotNull
            public final Request copy(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @Nullable Long l2) {
                Intrinsics.checkNotNullParameter(str, "sessionTopic");
                Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
                Intrinsics.checkNotNullParameter(str3, "params");
                Intrinsics.checkNotNullParameter(str4, "chainId");
                return new Request(str, str2, str3, str4, l2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Request)) {
                    return false;
                }
                Request request = (Request) obj;
                return Intrinsics.areEqual((Object) this.sessionTopic, (Object) request.sessionTopic) && Intrinsics.areEqual((Object) this.method, (Object) request.method) && Intrinsics.areEqual((Object) this.params, (Object) request.params) && Intrinsics.areEqual((Object) this.chainId, (Object) request.chainId) && Intrinsics.areEqual((Object) this.expiry, (Object) request.expiry);
            }

            @NotNull
            public final String getChainId() {
                return this.chainId;
            }

            @Nullable
            public final Long getExpiry() {
                return this.expiry;
            }

            @NotNull
            public final String getMethod() {
                return this.method;
            }

            @NotNull
            public final String getParams() {
                return this.params;
            }

            @NotNull
            public final String getSessionTopic() {
                return this.sessionTopic;
            }

            public int hashCode() {
                int i3 = androidx.compose.animation.core.a.i(this.chainId, androidx.compose.animation.core.a.i(this.params, androidx.compose.animation.core.a.i(this.method, this.sessionTopic.hashCode() * 31, 31), 31), 31);
                Long l2 = this.expiry;
                return i3 + (l2 == null ? 0 : l2.hashCode());
            }

            @NotNull
            public String toString() {
                String str = this.sessionTopic;
                String str2 = this.method;
                String str3 = this.params;
                String str4 = this.chainId;
                Long l2 = this.expiry;
                StringBuilder l3 = C0118y.l("Request(sessionTopic=", str, ", method=", str2, ", params=");
                b.w(l3, str3, ", chainId=", str4, ", expiry=");
                l3.append(l2);
                l3.append(")");
                return l3.toString();
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Request(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @Nullable Long l2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "sessionTopic");
                Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
                Intrinsics.checkNotNullParameter(str3, "params");
                Intrinsics.checkNotNullParameter(str4, "chainId");
                this.sessionTopic = str;
                this.method = str2;
                this.params = str3;
                this.chainId = str4;
                this.expiry = l2;
            }
        }

        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/reown/sign/client/Sign$Params$Response;", "Lcom/reown/sign/client/Sign$Params;", "sessionTopic", "", "jsonRpcResponse", "Lcom/reown/sign/client/Sign$Model$JsonRpcResponse;", "<init>", "(Ljava/lang/String;Lcom/reown/sign/client/Sign$Model$JsonRpcResponse;)V", "getSessionTopic", "()Ljava/lang/String;", "getJsonRpcResponse", "()Lcom/reown/sign/client/Sign$Model$JsonRpcResponse;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Response extends Params {
            @NotNull
            private final Model.JsonRpcResponse jsonRpcResponse;
            @NotNull
            private final String sessionTopic;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Response(@NotNull String str, @NotNull Model.JsonRpcResponse jsonRpcResponse2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "sessionTopic");
                Intrinsics.checkNotNullParameter(jsonRpcResponse2, "jsonRpcResponse");
                this.sessionTopic = str;
                this.jsonRpcResponse = jsonRpcResponse2;
            }

            public static /* synthetic */ Response copy$default(Response response, String str, Model.JsonRpcResponse jsonRpcResponse2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = response.sessionTopic;
                }
                if ((i3 & 2) != 0) {
                    jsonRpcResponse2 = response.jsonRpcResponse;
                }
                return response.copy(str, jsonRpcResponse2);
            }

            @NotNull
            public final String component1() {
                return this.sessionTopic;
            }

            @NotNull
            public final Model.JsonRpcResponse component2() {
                return this.jsonRpcResponse;
            }

            @NotNull
            public final Response copy(@NotNull String str, @NotNull Model.JsonRpcResponse jsonRpcResponse2) {
                Intrinsics.checkNotNullParameter(str, "sessionTopic");
                Intrinsics.checkNotNullParameter(jsonRpcResponse2, "jsonRpcResponse");
                return new Response(str, jsonRpcResponse2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Response)) {
                    return false;
                }
                Response response = (Response) obj;
                return Intrinsics.areEqual((Object) this.sessionTopic, (Object) response.sessionTopic) && Intrinsics.areEqual((Object) this.jsonRpcResponse, (Object) response.jsonRpcResponse);
            }

            @NotNull
            public final Model.JsonRpcResponse getJsonRpcResponse() {
                return this.jsonRpcResponse;
            }

            @NotNull
            public final String getSessionTopic() {
                return this.sessionTopic;
            }

            public int hashCode() {
                return this.jsonRpcResponse.hashCode() + (this.sessionTopic.hashCode() * 31);
            }

            @NotNull
            public String toString() {
                String str = this.sessionTopic;
                Model.JsonRpcResponse jsonRpcResponse2 = this.jsonRpcResponse;
                return "Response(sessionTopic=" + str + ", jsonRpcResponse=" + jsonRpcResponse2 + ")";
            }
        }

        @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u0015\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J)\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/reown/sign/client/Sign$Params$Update;", "Lcom/reown/sign/client/Sign$Params;", "sessionTopic", "", "namespaces", "", "Lcom/reown/sign/client/Sign$Model$Namespace$Session;", "<init>", "(Ljava/lang/String;Ljava/util/Map;)V", "getSessionTopic", "()Ljava/lang/String;", "getNamespaces", "()Ljava/util/Map;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Update extends Params {
            @NotNull
            private final Map<String, Model.Namespace.Session> namespaces;
            @NotNull
            private final String sessionTopic;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Update(@NotNull String str, @NotNull Map<String, Model.Namespace.Session> map) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "sessionTopic");
                Intrinsics.checkNotNullParameter(map, "namespaces");
                this.sessionTopic = str;
                this.namespaces = map;
            }

            public static /* synthetic */ Update copy$default(Update update, String str, Map<String, Model.Namespace.Session> map, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = update.sessionTopic;
                }
                if ((i3 & 2) != 0) {
                    map = update.namespaces;
                }
                return update.copy(str, map);
            }

            @NotNull
            public final String component1() {
                return this.sessionTopic;
            }

            @NotNull
            public final Map<String, Model.Namespace.Session> component2() {
                return this.namespaces;
            }

            @NotNull
            public final Update copy(@NotNull String str, @NotNull Map<String, Model.Namespace.Session> map) {
                Intrinsics.checkNotNullParameter(str, "sessionTopic");
                Intrinsics.checkNotNullParameter(map, "namespaces");
                return new Update(str, map);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Update)) {
                    return false;
                }
                Update update = (Update) obj;
                return Intrinsics.areEqual((Object) this.sessionTopic, (Object) update.sessionTopic) && Intrinsics.areEqual((Object) this.namespaces, (Object) update.namespaces);
            }

            @NotNull
            public final Map<String, Model.Namespace.Session> getNamespaces() {
                return this.namespaces;
            }

            @NotNull
            public final String getSessionTopic() {
                return this.sessionTopic;
            }

            public int hashCode() {
                return this.namespaces.hashCode() + (this.sessionTopic.hashCode() * 31);
            }

            @NotNull
            public String toString() {
                String str = this.sessionTopic;
                Map<String, Model.Namespace.Session> map = this.namespaces;
                return "Update(sessionTopic=" + str + ", namespaces=" + map + ")";
            }
        }

        public /* synthetic */ Params(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Params() {
        }
    }

    private Sign() {
    }
}
