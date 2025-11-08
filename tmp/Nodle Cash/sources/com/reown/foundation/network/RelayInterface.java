package com.reown.foundation.network;

import com.reown.android.sdk.storage.data.dao.k;
import com.reown.foundation.common.model.Topic;
import com.reown.foundation.network.model.Relay;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.SharedFlow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001JK\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00182\u0018\u0010\u001a\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u001c\u0012\u0004\u0012\u00020\u00120\u001bH&¢\u0006\u0002\u0010\u001eJ[\u0010\u001f\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010 \u001a\u00020\u00142\u0006\u0010!\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00182\u0018\u0010\u001a\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\u001c\u0012\u0004\u0012\u00020\u00120\u001bH&¢\u0006\u0002\u0010$JM\u0010%\u001a\u00020\u00122\u0006\u0010&\u001a\u00020\u00162\u0006\u0010'\u001a\u00020\u00162\u0006\u0010(\u001a\u00020)2\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00182\u001a\b\u0002\u0010\u001a\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0\u001c\u0012\u0004\u0012\u00020\u00120\u001bH&¢\u0006\u0002\u0010+J;\u0010,\u001a\u00020\u00122\u0006\u0010&\u001a\u00020\u00162\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00182\u0018\u0010\u001a\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020-0\u001c\u0012\u0004\u0012\u00020\u00120\u001bH&¢\u0006\u0002\u0010.JA\u0010/\u001a\u00020\u00122\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u0016012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00182\u0018\u0010\u001a\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002020\u001c\u0012\u0004\u0012\u00020\u00120\u001bH&¢\u0006\u0002\u00103JC\u00104\u001a\u00020\u00122\u0006\u0010&\u001a\u00020\u00162\u0006\u00105\u001a\u00020\u00162\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00182\u0018\u0010\u001a\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002060\u001c\u0012\u0004\u0012\u00020\u00120\u001bH&¢\u0006\u0002\u00107R\u0018\u0010\u0002\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0002\u0010\u0004\"\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u00068À\u0006\u0003"}, d2 = {"Lcom/reown/foundation/network/RelayInterface;", "", "isLoggingEnabled", "", "()Z", "setLoggingEnabled", "(Z)V", "eventsFlow", "Lkotlinx/coroutines/flow/SharedFlow;", "Lcom/reown/foundation/network/model/Relay$Model$Event;", "getEventsFlow", "()Lkotlinx/coroutines/flow/SharedFlow;", "subscriptionRequest", "Lkotlinx/coroutines/flow/Flow;", "Lcom/reown/foundation/network/model/Relay$Model$Call$Subscription$Request;", "getSubscriptionRequest", "()Lkotlinx/coroutines/flow/Flow;", "proposeSession", "", "pairingTopic", "Lcom/reown/foundation/common/model/Topic;", "sessionProposal", "", "correlationId", "", "id", "onResult", "Lkotlin/Function1;", "Lkotlin/Result;", "Lcom/reown/foundation/network/model/Relay$Model$Call$ProposeSession$Acknowledgement;", "(Lcom/reown/foundation/common/model/Topic;Ljava/lang/String;JLjava/lang/Long;Lkotlin/jvm/functions/Function1;)V", "approveSession", "sessionTopic", "sessionProposalResponse", "sessionSettlementRequest", "Lcom/reown/foundation/network/model/Relay$Model$Call$ApproveSession$Acknowledgement;", "(Lcom/reown/foundation/common/model/Topic;Lcom/reown/foundation/common/model/Topic;Ljava/lang/String;Ljava/lang/String;JLjava/lang/Long;Lkotlin/jvm/functions/Function1;)V", "publish", "topic", "message", "params", "Lcom/reown/foundation/network/model/Relay$Model$IrnParams;", "Lcom/reown/foundation/network/model/Relay$Model$Call$Publish$Acknowledgement;", "(Ljava/lang/String;Ljava/lang/String;Lcom/reown/foundation/network/model/Relay$Model$IrnParams;Ljava/lang/Long;Lkotlin/jvm/functions/Function1;)V", "subscribe", "Lcom/reown/foundation/network/model/Relay$Model$Call$Subscribe$Acknowledgement;", "(Ljava/lang/String;Ljava/lang/Long;Lkotlin/jvm/functions/Function1;)V", "batchSubscribe", "topics", "", "Lcom/reown/foundation/network/model/Relay$Model$Call$BatchSubscribe$Acknowledgement;", "(Ljava/util/List;Ljava/lang/Long;Lkotlin/jvm/functions/Function1;)V", "unsubscribe", "subscriptionId", "Lcom/reown/foundation/network/model/Relay$Model$Call$Unsubscribe$Acknowledgement;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Lkotlin/jvm/functions/Function1;)V", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface RelayInterface {

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    static /* synthetic */ void approveSession$default(RelayInterface relayInterface, Topic topic, Topic topic2, String str, String str2, long j2, Long l2, Function1 function1, int i3, Object obj) {
        if (obj == null) {
            relayInterface.approveSession(topic, topic2, str, str2, j2, (i3 & 32) != 0 ? null : l2, function1);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: approveSession");
    }

    static /* synthetic */ void batchSubscribe$default(RelayInterface relayInterface, List list, Long l2, Function1 function1, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                l2 = null;
            }
            relayInterface.batchSubscribe(list, l2, function1);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: batchSubscribe");
    }

    static /* synthetic */ void proposeSession$default(RelayInterface relayInterface, Topic topic, String str, long j2, Long l2, Function1 function1, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 8) != 0) {
                l2 = null;
            }
            relayInterface.proposeSession(topic, str, j2, l2, function1);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: proposeSession");
    }

    static /* synthetic */ void publish$default(RelayInterface relayInterface, String str, String str2, Relay.Model.IrnParams irnParams, Long l2, Function1 function1, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 8) != 0) {
                l2 = null;
            }
            Long l3 = l2;
            if ((i3 & 16) != 0) {
                function1 = new k(16);
            }
            relayInterface.publish(str, str2, irnParams, l3, function1);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: publish");
    }

    /* access modifiers changed from: private */
    static Unit publish$lambda$0(Result result) {
        return Unit.INSTANCE;
    }

    static /* synthetic */ void subscribe$default(RelayInterface relayInterface, String str, Long l2, Function1 function1, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                l2 = null;
            }
            relayInterface.subscribe(str, l2, function1);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: subscribe");
    }

    static /* synthetic */ void unsubscribe$default(RelayInterface relayInterface, String str, String str2, Long l2, Function1 function1, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 4) != 0) {
                l2 = null;
            }
            relayInterface.unsubscribe(str, str2, l2, function1);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: unsubscribe");
    }

    void approveSession(@NotNull Topic topic, @NotNull Topic topic2, @NotNull String str, @NotNull String str2, long j2, @Nullable Long l2, @NotNull Function1<? super Result<Relay.Model.Call.ApproveSession.Acknowledgement>, Unit> function1);

    void batchSubscribe(@NotNull List<String> list, @Nullable Long l2, @NotNull Function1<? super Result<Relay.Model.Call.BatchSubscribe.Acknowledgement>, Unit> function1);

    @NotNull
    SharedFlow<Relay.Model.Event> getEventsFlow();

    @NotNull
    Flow<Relay.Model.Call.Subscription.Request> getSubscriptionRequest();

    boolean isLoggingEnabled();

    void proposeSession(@NotNull Topic topic, @NotNull String str, long j2, @Nullable Long l2, @NotNull Function1<? super Result<Relay.Model.Call.ProposeSession.Acknowledgement>, Unit> function1);

    void publish(@NotNull String str, @NotNull String str2, @NotNull Relay.Model.IrnParams irnParams, @Nullable Long l2, @NotNull Function1<? super Result<Relay.Model.Call.Publish.Acknowledgement>, Unit> function1);

    void setLoggingEnabled(boolean z2);

    void subscribe(@NotNull String str, @Nullable Long l2, @NotNull Function1<? super Result<Relay.Model.Call.Subscribe.Acknowledgement>, Unit> function1);

    void unsubscribe(@NotNull String str, @NotNull String str2, @Nullable Long l2, @NotNull Function1<? super Result<Relay.Model.Call.Unsubscribe.Acknowledgement>, Unit> function1);
}
