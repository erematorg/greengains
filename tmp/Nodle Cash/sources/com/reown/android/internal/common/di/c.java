package com.reown.android.internal.common.di;

import kotlin.jvm.functions.Function2;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.scope.Scope;

public final /* synthetic */ class c implements Function2 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7304a;

    public /* synthetic */ c(int i3) {
        this.f7304a = i3;
    }

    public final Object invoke(Object obj, Object obj2) {
        Scope scope = (Scope) obj;
        ParametersHolder parametersHolder = (ParametersHolder) obj2;
        switch (this.f7304a) {
            case 0:
                return BaseStorageModuleKt.baseStorageModule$lambda$23$lambda$15(scope, parametersHolder);
            case 1:
                return BaseStorageModuleKt.baseStorageModule$lambda$23$lambda$16(scope, parametersHolder);
            case 2:
                return BaseStorageModuleKt.baseStorageModule$lambda$23$lambda$17(scope, parametersHolder);
            case 3:
                return BaseStorageModuleKt.baseStorageModule$lambda$23$lambda$18(scope, parametersHolder);
            case 4:
                return BaseStorageModuleKt.baseStorageModule$lambda$23$lambda$19(scope, parametersHolder);
            case 5:
                return CoreCommonModuleKt.coreCommonModule$lambda$5$lambda$0(scope, parametersHolder);
            case 6:
                return CoreCommonModuleKt.coreCommonModule$lambda$5$lambda$2(scope, parametersHolder);
            case 7:
                return CoreCommonModuleKt.coreCommonModule$lambda$5$lambda$3(scope, parametersHolder);
            case 8:
                return CoreCommonModuleKt.coreCommonModule$lambda$5$lambda$4(scope, parametersHolder);
            case 9:
                return CoreCryptoModuleKt.coreCryptoModule$lambda$7$lambda$3(scope, parametersHolder);
            case 10:
                return CoreCryptoModuleKt.coreCryptoModule$lambda$7$lambda$4(scope, parametersHolder);
            case 11:
                return CoreCryptoModuleKt.coreCryptoModule$lambda$7$lambda$5(scope, parametersHolder);
            case 12:
                return CoreCryptoModuleKt.coreCryptoModule$lambda$7$lambda$6(scope, parametersHolder);
            case 13:
                return CoreJsonRpcModuleKt.coreJsonRpcModule$lambda$3$lambda$0(scope, parametersHolder);
            case 14:
                return CoreJsonRpcModuleKt.coreJsonRpcModule$lambda$3$lambda$1(scope, parametersHolder);
            case 15:
                return CoreJsonRpcModuleKt.coreJsonRpcModule$lambda$3$lambda$2(scope, parametersHolder);
            case 16:
                return CoreNetworkModuleKt.coreAndroidNetworkModule$lambda$20$lambda$6(scope, parametersHolder);
            case 17:
                return CoreNetworkModuleKt.coreAndroidNetworkModule$lambda$20$lambda$12(scope, parametersHolder);
            case 18:
                return CoreNetworkModuleKt.coreAndroidNetworkModule$lambda$20$lambda$13(scope, parametersHolder);
            case 19:
                return CoreNetworkModuleKt.coreAndroidNetworkModule$lambda$20$lambda$14(scope, parametersHolder);
            case 20:
                return CoreNetworkModuleKt.coreAndroidNetworkModule$lambda$20$lambda$16(scope, parametersHolder);
            case 21:
                return CoreNetworkModuleKt.coreAndroidNetworkModule$lambda$20$lambda$18(scope, parametersHolder);
            case 22:
                return CoreNetworkModuleKt.coreAndroidNetworkModule$lambda$20$lambda$19(scope, parametersHolder);
            case 23:
                return CoreNetworkModuleKt.coreAndroidNetworkModule$lambda$20$lambda$2(scope, parametersHolder);
            case 24:
                return CoreNetworkModuleKt.coreAndroidNetworkModule$lambda$20$lambda$4(scope, parametersHolder);
            case 25:
                return CorePairingModuleKt.corePairingModule$lambda$3$lambda$0(scope, parametersHolder);
            case 26:
                return ExplorerModuleKt.explorerModule$lambda$6$lambda$0(scope, parametersHolder);
            case 27:
                return ExplorerModuleKt.explorerModule$lambda$6$lambda$1(scope, parametersHolder);
            case 28:
                return ExplorerModuleKt.explorerModule$lambda$6$lambda$2(scope, parametersHolder);
            default:
                return ExplorerModuleKt.explorerModule$lambda$6$lambda$3(scope, parametersHolder);
        }
    }
}
