package com.reown.sign.engine.use_case.responses;

import com.reown.android.internal.common.di.CoreNetworkModuleKt;
import com.reown.android.internal.common.model.WCResponse;
import com.reown.android.internal.common.model.type.EngineEvent;
import com.reown.android.pulse.domain.InsertEventUseCase;
import com.reown.foundation.util.Logger;
import com.reown.sign.common.model.vo.clientsync.session.params.SignParams;
import com.reown.sign.json_rpc.domain.GetSessionRequestByIdUseCase;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u001e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018HB¢\u0006\u0002\u0010\u0019R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001a"}, d2 = {"Lcom/reown/sign/engine/use_case/responses/OnSessionRequestResponseUseCase;", "", "logger", "Lcom/reown/foundation/util/Logger;", "insertEventUseCase", "Lcom/reown/android/pulse/domain/InsertEventUseCase;", "getSessionRequestByIdUseCase", "Lcom/reown/sign/json_rpc/domain/GetSessionRequestByIdUseCase;", "clientId", "", "<init>", "(Lcom/reown/foundation/util/Logger;Lcom/reown/android/pulse/domain/InsertEventUseCase;Lcom/reown/sign/json_rpc/domain/GetSessionRequestByIdUseCase;Ljava/lang/String;)V", "_events", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/reown/android/internal/common/model/type/EngineEvent;", "events", "Lkotlinx/coroutines/flow/SharedFlow;", "getEvents", "()Lkotlinx/coroutines/flow/SharedFlow;", "invoke", "", "wcResponse", "Lcom/reown/android/internal/common/model/WCResponse;", "params", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionRequestParams;", "(Lcom/reown/android/internal/common/model/WCResponse;Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionRequestParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class OnSessionRequestResponseUseCase {
    /* access modifiers changed from: private */
    @NotNull
    public final MutableSharedFlow<EngineEvent> _events;
    /* access modifiers changed from: private */
    @NotNull
    public final String clientId;
    @NotNull
    private final SharedFlow<EngineEvent> events;
    /* access modifiers changed from: private */
    @NotNull
    public final GetSessionRequestByIdUseCase getSessionRequestByIdUseCase;
    /* access modifiers changed from: private */
    @NotNull
    public final InsertEventUseCase insertEventUseCase;
    /* access modifiers changed from: private */
    @NotNull
    public final Logger logger;

    public OnSessionRequestResponseUseCase(@NotNull Logger logger2, @NotNull InsertEventUseCase insertEventUseCase2, @NotNull GetSessionRequestByIdUseCase getSessionRequestByIdUseCase2, @NotNull String str) {
        Intrinsics.checkNotNullParameter(logger2, "logger");
        Intrinsics.checkNotNullParameter(insertEventUseCase2, "insertEventUseCase");
        Intrinsics.checkNotNullParameter(getSessionRequestByIdUseCase2, "getSessionRequestByIdUseCase");
        Intrinsics.checkNotNullParameter(str, CoreNetworkModuleKt.KEY_CLIENT_ID);
        this.logger = logger2;
        this.insertEventUseCase = insertEventUseCase2;
        this.getSessionRequestByIdUseCase = getSessionRequestByIdUseCase2;
        this.clientId = str;
        MutableSharedFlow<EngineEvent> MutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(0, 0, (BufferOverflow) null, 7, (Object) null);
        this._events = MutableSharedFlow$default;
        this.events = FlowKt.asSharedFlow(MutableSharedFlow$default);
    }

    @NotNull
    public final SharedFlow<EngineEvent> getEvents() {
        return this.events;
    }

    @Nullable
    public final Object invoke(@NotNull WCResponse wCResponse, @NotNull SignParams.SessionRequestParams sessionRequestParams, @NotNull Continuation<? super Unit> continuation) {
        Object supervisorScope = SupervisorKt.supervisorScope(new OnSessionRequestResponseUseCase$invoke$2(this, wCResponse, sessionRequestParams, (Continuation<? super OnSessionRequestResponseUseCase$invoke$2>) null), continuation);
        return supervisorScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? supervisorScope : Unit.INSTANCE;
    }
}
