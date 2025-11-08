package com.reown.android.internal.common.model.type;

import com.reown.android.internal.common.model.Expiry;
import com.reown.foundation.common.model.Topic;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\nÀ\u0006\u0003"}, d2 = {"Lcom/reown/android/internal/common/model/type/Sequence;", "", "topic", "Lcom/reown/foundation/common/model/Topic;", "getTopic", "()Lcom/reown/foundation/common/model/Topic;", "expiry", "Lcom/reown/android/internal/common/model/Expiry;", "getExpiry", "()Lcom/reown/android/internal/common/model/Expiry;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface Sequence {
    @NotNull
    Expiry getExpiry();

    @NotNull
    Topic getTopic();
}
