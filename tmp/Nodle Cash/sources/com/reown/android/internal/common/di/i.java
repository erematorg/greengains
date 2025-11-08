package com.reown.android.internal.common.di;

import com.reown.android.sdk.storage.data.dao.VerifyPublicKeyQueries;
import com.reown.sign.di.CallsModuleKt;
import kotlin.jvm.functions.Function2;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.scope.Scope;

public final /* synthetic */ class i implements Function2 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7319a;

    public /* synthetic */ i(int i3) {
        this.f7319a = i3;
    }

    public final Object invoke(Object obj, Object obj2) {
        switch (this.f7319a) {
            case 0:
                return ExplorerModuleKt.explorerModule$lambda$6$lambda$4((Scope) obj, (ParametersHolder) obj2);
            case 1:
                return ExplorerModuleKt.explorerModule$lambda$6$lambda$5((Scope) obj, (ParametersHolder) obj2);
            case 2:
                return KeyServerModuleKt.keyServerModule$lambda$10$lambda$2((Scope) obj, (ParametersHolder) obj2);
            case 3:
                return KeyServerModuleKt.keyServerModule$lambda$10$lambda$3((Scope) obj, (ParametersHolder) obj2);
            case 4:
                return KeyServerModuleKt.keyServerModule$lambda$10$lambda$4((Scope) obj, (ParametersHolder) obj2);
            case 5:
                return KeyServerModuleKt.keyServerModule$lambda$10$lambda$5((Scope) obj, (ParametersHolder) obj2);
            case 6:
                return KeyServerModuleKt.keyServerModule$lambda$10$lambda$6((Scope) obj, (ParametersHolder) obj2);
            case 7:
                return KeyServerModuleKt.keyServerModule$lambda$10$lambda$7((Scope) obj, (ParametersHolder) obj2);
            case 8:
                return KeyServerModuleKt.keyServerModule$lambda$10$lambda$8((Scope) obj, (ParametersHolder) obj2);
            case 9:
                return KeyServerModuleKt.keyServerModule$lambda$10$lambda$9((Scope) obj, (ParametersHolder) obj2);
            case 10:
                return PulseModuleKt.pulseModule$lambda$7$lambda$0((Scope) obj, (ParametersHolder) obj2);
            case 11:
                return PulseModuleKt.pulseModule$lambda$7$lambda$1((Scope) obj, (ParametersHolder) obj2);
            case 12:
                return PulseModuleKt.pulseModule$lambda$7$lambda$2((Scope) obj, (ParametersHolder) obj2);
            case 13:
                return PulseModuleKt.pulseModule$lambda$7$lambda$4((Scope) obj, (ParametersHolder) obj2);
            case 14:
                return PulseModuleKt.pulseModule$lambda$7$lambda$5((Scope) obj, (ParametersHolder) obj2);
            case 15:
                return PulseModuleKt.pulseModule$lambda$7$lambda$6((Scope) obj, (ParametersHolder) obj2);
            case 16:
                return PushModuleKt.pushModule$lambda$4$lambda$0((Scope) obj, (ParametersHolder) obj2);
            case 17:
                return PushModuleKt.pushModule$lambda$4$lambda$1((Scope) obj, (ParametersHolder) obj2);
            case 18:
                return PushModuleKt.pushModule$lambda$4$lambda$2((Scope) obj, (ParametersHolder) obj2);
            case 19:
                return PushModuleKt.pushModule$lambda$4$lambda$3((Scope) obj, (ParametersHolder) obj2);
            case 20:
                return VerifyModuleKt.verifyModule$lambda$7$lambda$0((Scope) obj, (ParametersHolder) obj2);
            case 21:
                return VerifyModuleKt.verifyModule$lambda$7$lambda$1((Scope) obj, (ParametersHolder) obj2);
            case 22:
                return VerifyModuleKt.verifyModule$lambda$7$lambda$2((Scope) obj, (ParametersHolder) obj2);
            case 23:
                return VerifyModuleKt.verifyModule$lambda$7$lambda$3((Scope) obj, (ParametersHolder) obj2);
            case 24:
                return VerifyModuleKt.verifyModule$lambda$7$lambda$4((Scope) obj, (ParametersHolder) obj2);
            case 25:
                return VerifyModuleKt.verifyModule$lambda$7$lambda$5((Scope) obj, (ParametersHolder) obj2);
            case 26:
                return VerifyModuleKt.verifyModule$lambda$7$lambda$6((Scope) obj, (ParametersHolder) obj2);
            case 27:
                return VerifyPublicKeyQueries.getKey$lambda$1((String) obj, ((Long) obj2).longValue());
            case 28:
                return CallsModuleKt.callsModule$lambda$25$lambda$0((Scope) obj, (ParametersHolder) obj2);
            default:
                return CallsModuleKt.callsModule$lambda$25$lambda$19((Scope) obj, (ParametersHolder) obj2);
        }
    }
}
