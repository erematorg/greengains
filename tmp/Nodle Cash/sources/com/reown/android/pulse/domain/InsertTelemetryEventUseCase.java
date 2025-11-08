package com.reown.android.pulse.domain;

import com.reown.android.internal.common.storage.events.EventsRepository;
import com.reown.android.pulse.model.properties.Props;
import com.reown.foundation.util.Logger;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bHB¢\u0006\u0002\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/reown/android/pulse/domain/InsertTelemetryEventUseCase;", "Lcom/reown/android/pulse/domain/InsertEventUseCaseInterface;", "eventsRepository", "Lcom/reown/android/internal/common/storage/events/EventsRepository;", "logger", "Lcom/reown/foundation/util/Logger;", "<init>", "(Lcom/reown/android/internal/common/storage/events/EventsRepository;Lcom/reown/foundation/util/Logger;)V", "invoke", "", "props", "Lcom/reown/android/pulse/model/properties/Props;", "(Lcom/reown/android/pulse/model/properties/Props;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class InsertTelemetryEventUseCase implements InsertEventUseCaseInterface {
    /* access modifiers changed from: private */
    @NotNull
    public final EventsRepository eventsRepository;
    /* access modifiers changed from: private */
    @NotNull
    public final Logger logger;

    public InsertTelemetryEventUseCase(@NotNull EventsRepository eventsRepository2, @NotNull Logger logger2) {
        Intrinsics.checkNotNullParameter(eventsRepository2, "eventsRepository");
        Intrinsics.checkNotNullParameter(logger2, "logger");
        this.eventsRepository = eventsRepository2;
        this.logger = logger2;
    }

    @Nullable
    public Object invoke(@NotNull Props props, @NotNull Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new InsertTelemetryEventUseCase$invoke$2(this, props, (Continuation<? super InsertTelemetryEventUseCase$invoke$2>) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }
}
