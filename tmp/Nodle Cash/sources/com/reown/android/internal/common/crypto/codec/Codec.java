package com.reown.android.internal.common.crypto.codec;

import com.reown.android.internal.common.model.EnvelopeType;
import com.reown.android.internal.common.model.Participants;
import com.reown.foundation.common.model.Topic;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J,\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bH&J\u0018\u0010\f\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0003H&¨\u0006\u000eÀ\u0006\u0003"}, d2 = {"Lcom/reown/android/internal/common/crypto/codec/Codec;", "", "encrypt", "", "topic", "Lcom/reown/foundation/common/model/Topic;", "payload", "", "envelopeType", "Lcom/reown/android/internal/common/model/EnvelopeType;", "participants", "Lcom/reown/android/internal/common/model/Participants;", "decrypt", "cipherText", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface Codec {

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    static /* synthetic */ byte[] encrypt$default(Codec codec, Topic topic, String str, EnvelopeType envelopeType, Participants participants, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 8) != 0) {
                participants = null;
            }
            return codec.encrypt(topic, str, envelopeType, participants);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: encrypt");
    }

    @NotNull
    String decrypt(@NotNull Topic topic, @NotNull byte[] bArr);

    @NotNull
    byte[] encrypt(@NotNull Topic topic, @NotNull String str, @NotNull EnvelopeType envelopeType, @Nullable Participants participants);
}
