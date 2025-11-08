package com.reown.foundation.common;

import com.reown.foundation.network.model.Relay;
import com.tinder.scarlet.Message;
import com.tinder.scarlet.WebSocket;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\f\u0010\u0003\u001a\u00020\u0004*\u00020\u0005H\u0000\u001a\f\u0010\u0003\u001a\u00020\u0006*\u00020\u0007H\u0000\u001a\f\u0010\u0003\u001a\u00020\b*\u00020\tH\u0000\u001a\f\u0010\u0003\u001a\u00020\n*\u00020\u000bH\u0000\u001a\n\u0010\u0003\u001a\u00020\f*\u00020\r\u001a\n\u0010\u0003\u001a\u00020\u000e*\u00020\u000f\u001a\n\u0010\u0003\u001a\u00020\u0010*\u00020\u0011\u001a\n\u0010\u0003\u001a\u00020\u0012*\u00020\u0013\u001a\n\u0010\u0003\u001a\u00020\u0014*\u00020\u0015\u001a\n\u0010\u0003\u001a\u00020\u0016*\u00020\u0017\u001a\n\u0010\u0003\u001a\u00020\u0018*\u00020\u0019¨\u0006\u001a"}, d2 = {"toRelayEvent", "Lcom/reown/foundation/network/model/Relay$Model$Event;", "Lcom/tinder/scarlet/WebSocket$Event;", "toRelay", "Lcom/reown/foundation/network/model/Relay$Model$Message;", "Lcom/tinder/scarlet/Message;", "Lcom/reown/foundation/network/model/Relay$Model$ShutdownReason;", "Lcom/tinder/scarlet/ShutdownReason;", "Lcom/reown/foundation/network/model/Relay$Model$Call$Subscription$Request$Params$SubscriptionData;", "Lcom/reown/foundation/network/model/RelayDTO$Subscription$Request$Params$SubscriptionData;", "Lcom/reown/foundation/network/model/Relay$Model$Call$Subscription$Request$Params;", "Lcom/reown/foundation/network/model/RelayDTO$Subscription$Request$Params;", "Lcom/reown/foundation/network/model/Relay$Model$Call$Subscription$Request;", "Lcom/reown/foundation/network/model/RelayDTO$Subscription$Request;", "Lcom/reown/foundation/network/model/Relay$Model$Call$Publish$Acknowledgement;", "Lcom/reown/foundation/network/model/RelayDTO$Publish$Result$Acknowledgement;", "Lcom/reown/foundation/network/model/Relay$Model$Call$Subscribe$Acknowledgement;", "Lcom/reown/foundation/network/model/RelayDTO$Subscribe$Result$Acknowledgement;", "Lcom/reown/foundation/network/model/Relay$Model$Call$BatchSubscribe$Acknowledgement;", "Lcom/reown/foundation/network/model/RelayDTO$BatchSubscribe$Result$Acknowledgement;", "Lcom/reown/foundation/network/model/Relay$Model$Call$Unsubscribe$Acknowledgement;", "Lcom/reown/foundation/network/model/RelayDTO$Unsubscribe$Result$Acknowledgement;", "Lcom/reown/foundation/network/model/Relay$Model$Call$ProposeSession$Acknowledgement;", "Lcom/reown/foundation/network/model/RelayDTO$ProposeSession$Result$Acknowledgement;", "Lcom/reown/foundation/network/model/Relay$Model$Call$ApproveSession$Acknowledgement;", "Lcom/reown/foundation/network/model/RelayDTO$ApproveSession$Result$Acknowledgement;", "foundation"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final /* synthetic */ class RelayMapperKt {
    public static final /* synthetic */ Relay.Model.Message toRelay(Message message) {
        Intrinsics.checkNotNullParameter(message, "<this>");
        if (message instanceof Message.Text) {
            return new Relay.Model.Message.Text(((Message.Text) message).getValue());
        }
        if (message instanceof Message.Bytes) {
            return new Relay.Model.Message.Bytes(((Message.Bytes) message).getValue());
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final /* synthetic */ Relay.Model.Event toRelayEvent(WebSocket.Event event) {
        Intrinsics.checkNotNullParameter(event, "<this>");
        if (event instanceof WebSocket.Event.OnConnectionOpened) {
            return new Relay.Model.Event.OnConnectionOpened(((WebSocket.Event.OnConnectionOpened) event).getWebSocket());
        }
        if (event instanceof WebSocket.Event.OnMessageReceived) {
            return new Relay.Model.Event.OnMessageReceived(toRelay(((WebSocket.Event.OnMessageReceived) event).getMessage()));
        }
        if (event instanceof WebSocket.Event.OnConnectionClosing) {
            return new Relay.Model.Event.OnConnectionClosing(Intrinsics.checkNotNullParameter(((WebSocket.Event.OnConnectionClosing) event).getShutdownReason(), "<this>"));
        }
        if (event instanceof WebSocket.Event.OnConnectionClosed) {
            return new Relay.Model.Event.OnConnectionClosed(Intrinsics.checkNotNullParameter(((WebSocket.Event.OnConnectionClosed) event).getShutdownReason(), "<this>"));
        }
        if (event instanceof WebSocket.Event.OnConnectionFailed) {
            return new Relay.Model.Event.OnConnectionFailed(((WebSocket.Event.OnConnectionFailed) event).getThrowable());
        }
        throw new NoWhenBranchMatchedException();
    }
}
