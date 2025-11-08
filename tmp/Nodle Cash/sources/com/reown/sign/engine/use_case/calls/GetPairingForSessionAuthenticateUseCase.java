package com.reown.sign.engine.use_case.calls;

import com.google.firebase.messaging.Constants;
import com.reown.android.Core;
import com.reown.android.pairing.client.PairingInterface;
import com.reown.sign.json_rpc.model.JsonRpcMethod;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/reown/sign/engine/use_case/calls/GetPairingForSessionAuthenticateUseCase;", "", "pairingProtocol", "Lcom/reown/android/pairing/client/PairingInterface;", "<init>", "(Lcom/reown/android/pairing/client/PairingInterface;)V", "invoke", "Lcom/reown/android/Core$Model$Pairing;", "pairingTopic", "", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nGetPairingForSessionAuthenticateUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GetPairingForSessionAuthenticateUseCase.kt\ncom/reown/sign/engine/use_case/calls/GetPairingForSessionAuthenticateUseCase\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,19:1\n1#2:20\n*E\n"})
public final class GetPairingForSessionAuthenticateUseCase {
    @NotNull
    private PairingInterface pairingProtocol;

    public GetPairingForSessionAuthenticateUseCase(@NotNull PairingInterface pairingInterface) {
        Intrinsics.checkNotNullParameter(pairingInterface, "pairingProtocol");
        this.pairingProtocol = pairingInterface;
    }

    /* access modifiers changed from: private */
    public static final Unit invoke$lambda$1(Core.Model.Error error) {
        Intrinsics.checkNotNullParameter(error, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        throw error.getThrowable();
    }

    @NotNull
    public final Core.Model.Pairing invoke(@Nullable String str) {
        Core.Model.Pairing pairing;
        Object obj;
        if (str != null) {
            Iterator it = this.pairingProtocol.getPairings().iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (Intrinsics.areEqual((Object) ((Core.Model.Pairing) obj).getTopic(), (Object) str)) {
                    break;
                }
            }
            pairing = (Core.Model.Pairing) obj;
            if (pairing == null) {
                throw new Exception("Pairing does not exist");
            }
        } else {
            pairing = this.pairingProtocol.create(new g(1), JsonRpcMethod.WC_SESSION_AUTHENTICATE);
            if (pairing == null) {
                throw new Exception("Cannot create a pairing");
            }
        }
        if (StringsKt__StringsKt.contains$default((CharSequence) pairing.getRegisteredMethods(), (CharSequence) JsonRpcMethod.WC_SESSION_AUTHENTICATE, false, 2, (Object) null)) {
            return pairing;
        }
        throw new Exception("Pairing does not support wc_sessionAuthenticate");
    }
}
