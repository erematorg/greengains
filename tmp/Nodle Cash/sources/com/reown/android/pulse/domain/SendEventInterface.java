package com.reown.android.pulse.domain;

import com.reown.android.internal.utils.Time;
import com.reown.android.pulse.model.SDKType;
import com.reown.android.pulse.model.properties.Props;
import com.reown.util.UtilFunctionsKt;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J7\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\tH&¢\u0006\u0002\u0010\u000b¨\u0006\fÀ\u0006\u0003"}, d2 = {"Lcom/reown/android/pulse/domain/SendEventInterface;", "", "send", "", "props", "Lcom/reown/android/pulse/model/properties/Props;", "sdkType", "Lcom/reown/android/pulse/model/SDKType;", "timestamp", "", "id", "(Lcom/reown/android/pulse/model/properties/Props;Lcom/reown/android/pulse/model/SDKType;Ljava/lang/Long;Ljava/lang/Long;)V", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface SendEventInterface {

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    static /* synthetic */ void send$default(SendEventInterface sendEventInterface, Props props, SDKType sDKType, Long l2, Long l3, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                sDKType = SDKType.APPKIT;
            }
            if ((i3 & 4) != 0) {
                l2 = Long.valueOf(Time.getCurrentTimeInSeconds());
            }
            if ((i3 & 8) != 0) {
                l3 = Long.valueOf(UtilFunctionsKt.generateId());
            }
            sendEventInterface.send(props, sDKType, l2, l3);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: send");
    }

    void send(@NotNull Props props, @NotNull SDKType sDKType, @Nullable Long l2, @Nullable Long l3);
}
