package com.reown.android.internal.common.storage.pairing;

import com.reown.android.internal.common.model.Expiry;
import com.reown.android.internal.common.model.Pairing;
import com.reown.foundation.common.model.Topic;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bH&J\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\fH¦@¢\u0006\u0002\u0010\rJ\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\fH¦@¢\u0006\u0002\u0010\rJ\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0018\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0012H&J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\u0014À\u0006\u0003"}, d2 = {"Lcom/reown/android/internal/common/storage/pairing/PairingStorageRepositoryInterface;", "", "insertPairing", "", "pairing", "Lcom/reown/android/internal/common/model/Pairing;", "deletePairing", "topic", "Lcom/reown/foundation/common/model/Topic;", "hasTopic", "", "getListOfPairings", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getListOfPairingsWithoutRequestReceived", "setRequestReceived", "updateExpiry", "expiry", "Lcom/reown/android/internal/common/model/Expiry;", "getPairingOrNullByTopic", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface PairingStorageRepositoryInterface {
    void deletePairing(@NotNull Topic topic);

    @Nullable
    Object getListOfPairings(@NotNull Continuation<? super List<Pairing>> continuation);

    @Nullable
    Object getListOfPairingsWithoutRequestReceived(@NotNull Continuation<? super List<Pairing>> continuation);

    @Nullable
    Pairing getPairingOrNullByTopic(@NotNull Topic topic);

    boolean hasTopic(@NotNull Topic topic);

    void insertPairing(@NotNull Pairing pairing);

    void setRequestReceived(@NotNull Topic topic);

    void updateExpiry(@NotNull Topic topic, @NotNull Expiry expiry);
}
