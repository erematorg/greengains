package com.tinder.scarlet.messageadapter.builtin;

import com.reown.android.push.notifications.PushMessagingService;
import com.tinder.scarlet.Message;
import com.tinder.scarlet.MessageAdapter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0002H\u0016¨\u0006\t"}, d2 = {"Lcom/tinder/scarlet/messageadapter/builtin/ByteArrayMessageAdapter;", "Lcom/tinder/scarlet/MessageAdapter;", "", "()V", "fromMessage", "message", "Lcom/tinder/scarlet/Message;", "toMessage", "data", "scarlet-message-adapter-builtin"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ByteArrayMessageAdapter implements MessageAdapter<byte[]> {
    @NotNull
    public byte[] fromMessage(@NotNull Message message) {
        Intrinsics.checkNotNullParameter(message, PushMessagingService.KEY_MESSAGE);
        if (message instanceof Message.Bytes) {
            return ((Message.Bytes) message).getValue();
        }
        throw new IllegalArgumentException("This Message Adapter only supports bytes Messages");
    }

    @NotNull
    public Message toMessage(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "data");
        return new Message.Bytes(bArr);
    }
}
