package com.reown.foundation.network.data.service;

import com.reown.foundation.network.model.RelayDTO;
import com.tinder.scarlet.WebSocket;
import com.tinder.scarlet.ws.Receive;
import com.tinder.scarlet.ws.Send;
import kotlin.Metadata;
import kotlinx.coroutines.flow.Flow;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H'J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH'J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003H'J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0003H'J\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u000eH'J\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0003H'J\u000e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u0003H'J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0013H'J\u000e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0003H'J\u000e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0003H'J\u0010\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0019H'J\u000e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0003H'J\u000e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u0003H'J\u0010\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u001fH'J\u000e\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u0003H'J\u000e\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u0003H'J\u000e\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u0003H'J\u0010\u0010&\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020'H'J\u0010\u0010(\u001a\u00020\u00062\u0006\u0010(\u001a\u00020)H'J\u000e\u0010*\u001a\b\u0012\u0004\u0012\u00020+0\u0003H'J\u000e\u0010,\u001a\b\u0012\u0004\u0012\u00020-0\u0003H'¨\u0006.À\u0006\u0003"}, d2 = {"Lcom/reown/foundation/network/data/service/RelayService;", "", "observeWebSocketEvent", "Lkotlinx/coroutines/flow/Flow;", "Lcom/tinder/scarlet/WebSocket$Event;", "proposeSessionRequest", "", "publishRequest", "Lcom/reown/foundation/network/model/RelayDTO$ProposeSession$Request;", "observeProposeSessionAcknowledgement", "Lcom/reown/foundation/network/model/RelayDTO$ProposeSession$Result$Acknowledgement;", "observeProposeSessionError", "Lcom/reown/foundation/network/model/RelayDTO$ProposeSession$Result$JsonRpcError;", "approveSessionRequest", "Lcom/reown/foundation/network/model/RelayDTO$ApproveSession$Request;", "observeApproveSessionAcknowledgement", "Lcom/reown/foundation/network/model/RelayDTO$ApproveSession$Result$Acknowledgement;", "observeApproveSessionError", "Lcom/reown/foundation/network/model/RelayDTO$ApproveSession$Result$JsonRpcError;", "Lcom/reown/foundation/network/model/RelayDTO$Publish$Request;", "observePublishAcknowledgement", "Lcom/reown/foundation/network/model/RelayDTO$Publish$Result$Acknowledgement;", "observePublishError", "Lcom/reown/foundation/network/model/RelayDTO$Publish$Result$JsonRpcError;", "subscribeRequest", "Lcom/reown/foundation/network/model/RelayDTO$Subscribe$Request;", "observeSubscribeAcknowledgement", "Lcom/reown/foundation/network/model/RelayDTO$Subscribe$Result$Acknowledgement;", "observeSubscribeError", "Lcom/reown/foundation/network/model/RelayDTO$Subscribe$Result$JsonRpcError;", "batchSubscribeRequest", "Lcom/reown/foundation/network/model/RelayDTO$BatchSubscribe$Request;", "observeBatchSubscribeAcknowledgement", "Lcom/reown/foundation/network/model/RelayDTO$BatchSubscribe$Result$Acknowledgement;", "observeBatchSubscribeError", "Lcom/reown/foundation/network/model/RelayDTO$BatchSubscribe$Result$JsonRpcError;", "observeSubscriptionRequest", "Lcom/reown/foundation/network/model/RelayDTO$Subscription$Request;", "publishSubscriptionAcknowledgement", "Lcom/reown/foundation/network/model/RelayDTO$Subscription$Result$Acknowledgement;", "unsubscribeRequest", "Lcom/reown/foundation/network/model/RelayDTO$Unsubscribe$Request;", "observeUnsubscribeAcknowledgement", "Lcom/reown/foundation/network/model/RelayDTO$Unsubscribe$Result$Acknowledgement;", "observeUnsubscribeError", "Lcom/reown/foundation/network/model/RelayDTO$Unsubscribe$Result$JsonRpcError;", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface RelayService {
    @Send
    void approveSessionRequest(@NotNull RelayDTO.ApproveSession.Request request);

    @Send
    void batchSubscribeRequest(@NotNull RelayDTO.BatchSubscribe.Request request);

    @NotNull
    @Receive
    Flow<RelayDTO.ApproveSession.Result.Acknowledgement> observeApproveSessionAcknowledgement();

    @NotNull
    @Receive
    Flow<RelayDTO.ApproveSession.Result.JsonRpcError> observeApproveSessionError();

    @NotNull
    @Receive
    Flow<RelayDTO.BatchSubscribe.Result.Acknowledgement> observeBatchSubscribeAcknowledgement();

    @NotNull
    @Receive
    Flow<RelayDTO.BatchSubscribe.Result.JsonRpcError> observeBatchSubscribeError();

    @NotNull
    @Receive
    Flow<RelayDTO.ProposeSession.Result.Acknowledgement> observeProposeSessionAcknowledgement();

    @NotNull
    @Receive
    Flow<RelayDTO.ProposeSession.Result.JsonRpcError> observeProposeSessionError();

    @NotNull
    @Receive
    Flow<RelayDTO.Publish.Result.Acknowledgement> observePublishAcknowledgement();

    @NotNull
    @Receive
    Flow<RelayDTO.Publish.Result.JsonRpcError> observePublishError();

    @NotNull
    @Receive
    Flow<RelayDTO.Subscribe.Result.Acknowledgement> observeSubscribeAcknowledgement();

    @NotNull
    @Receive
    Flow<RelayDTO.Subscribe.Result.JsonRpcError> observeSubscribeError();

    @NotNull
    @Receive
    Flow<RelayDTO.Subscription.Request> observeSubscriptionRequest();

    @NotNull
    @Receive
    Flow<RelayDTO.Unsubscribe.Result.Acknowledgement> observeUnsubscribeAcknowledgement();

    @NotNull
    @Receive
    Flow<RelayDTO.Unsubscribe.Result.JsonRpcError> observeUnsubscribeError();

    @NotNull
    @Receive
    Flow<WebSocket.Event> observeWebSocketEvent();

    @Send
    void proposeSessionRequest(@NotNull RelayDTO.ProposeSession.Request request);

    @Send
    void publishRequest(@NotNull RelayDTO.Publish.Request request);

    @Send
    void publishSubscriptionAcknowledgement(@NotNull RelayDTO.Subscription.Result.Acknowledgement acknowledgement);

    @Send
    void subscribeRequest(@NotNull RelayDTO.Subscribe.Request request);

    @Send
    void unsubscribeRequest(@NotNull RelayDTO.Unsubscribe.Request request);
}
