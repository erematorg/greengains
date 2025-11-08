package com.reown.android.pulse.domain;

import com.reown.android.pulse.model.properties.Props;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H¦B¢\u0006\u0002\u0010\u0006¨\u0006\u0007À\u0006\u0003"}, d2 = {"Lcom/reown/android/pulse/domain/InsertEventUseCaseInterface;", "", "invoke", "", "props", "Lcom/reown/android/pulse/model/properties/Props;", "(Lcom/reown/android/pulse/model/properties/Props;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface InsertEventUseCaseInterface {
    @Nullable
    Object invoke(@NotNull Props props, @NotNull Continuation<? super Unit> continuation);
}
