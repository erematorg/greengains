package com.reown.sign.engine.use_case.calls;

import com.reown.android.pairing.client.PairingInterface;
import com.reown.sign.engine.model.EngineDO;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.SupervisorKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H@¢\u0006\u0002\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/reown/sign/engine/use_case/calls/GetPairingsUseCase;", "Lcom/reown/sign/engine/use_case/calls/GetPairingsUseCaseInterface;", "pairingInterface", "Lcom/reown/android/pairing/client/PairingInterface;", "<init>", "(Lcom/reown/android/pairing/client/PairingInterface;)V", "getListOfSettledPairings", "", "Lcom/reown/sign/engine/model/EngineDO$PairingSettle;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class GetPairingsUseCase implements GetPairingsUseCaseInterface {
    /* access modifiers changed from: private */
    @NotNull
    public final PairingInterface pairingInterface;

    public GetPairingsUseCase(@NotNull PairingInterface pairingInterface2) {
        Intrinsics.checkNotNullParameter(pairingInterface2, "pairingInterface");
        this.pairingInterface = pairingInterface2;
    }

    @Nullable
    public Object getListOfSettledPairings(@NotNull Continuation<? super List<EngineDO.PairingSettle>> continuation) {
        return SupervisorKt.supervisorScope(new GetPairingsUseCase$getListOfSettledPairings$2(this, (Continuation<? super GetPairingsUseCase$getListOfSettledPairings$2>) null), continuation);
    }
}
