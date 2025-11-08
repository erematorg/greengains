package com.reown.sign.di;

import kotlin.jvm.functions.Function2;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.scope.Scope;

public final /* synthetic */ class b implements Function2 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7476a;

    public /* synthetic */ b(int i3) {
        this.f7476a = i3;
    }

    public final Object invoke(Object obj, Object obj2) {
        Scope scope = (Scope) obj;
        ParametersHolder parametersHolder = (ParametersHolder) obj2;
        switch (this.f7476a) {
            case 0:
                return EngineModuleKt.engineModule$lambda$12$lambda$3(scope, parametersHolder);
            case 1:
                return EngineModuleKt.engineModule$lambda$12$lambda$4(scope, parametersHolder);
            case 2:
                return EngineModuleKt.engineModule$lambda$12$lambda$5(scope, parametersHolder);
            case 3:
                return EngineModuleKt.engineModule$lambda$12$lambda$6(scope, parametersHolder);
            case 4:
                return EngineModuleKt.engineModule$lambda$12$lambda$7(scope, parametersHolder);
            case 5:
                return RequestsModuleKt.requestsModule$lambda$9$lambda$0(scope, parametersHolder);
            case 6:
                return RequestsModuleKt.requestsModule$lambda$9$lambda$1(scope, parametersHolder);
            case 7:
                return RequestsModuleKt.requestsModule$lambda$9$lambda$2(scope, parametersHolder);
            case 8:
                return RequestsModuleKt.requestsModule$lambda$9$lambda$3(scope, parametersHolder);
            case 9:
                return RequestsModuleKt.requestsModule$lambda$9$lambda$4(scope, parametersHolder);
            case 10:
                return RequestsModuleKt.requestsModule$lambda$9$lambda$5(scope, parametersHolder);
            case 11:
                return RequestsModuleKt.requestsModule$lambda$9$lambda$6(scope, parametersHolder);
            case 12:
                return RequestsModuleKt.requestsModule$lambda$9$lambda$7(scope, parametersHolder);
            case 13:
                return RequestsModuleKt.requestsModule$lambda$9$lambda$8(scope, parametersHolder);
            case 14:
                return ResponsesModuleKt.responsesModule$lambda$5$lambda$0(scope, parametersHolder);
            case 15:
                return ResponsesModuleKt.responsesModule$lambda$5$lambda$1(scope, parametersHolder);
            case 16:
                return ResponsesModuleKt.responsesModule$lambda$5$lambda$2(scope, parametersHolder);
            case 17:
                return ResponsesModuleKt.responsesModule$lambda$5$lambda$3(scope, parametersHolder);
            case 18:
                return ResponsesModuleKt.responsesModule$lambda$5$lambda$4(scope, parametersHolder);
            case 19:
                return StorageModuleKt.storageModule$lambda$14$lambda$7(scope, parametersHolder);
            case 20:
                return StorageModuleKt.storageModule$lambda$14$lambda$8(scope, parametersHolder);
            case 21:
                return StorageModuleKt.storageModule$lambda$14$lambda$9(scope, parametersHolder);
            case 22:
                return StorageModuleKt.storageModule$lambda$14$lambda$10(scope, parametersHolder);
            case 23:
                return StorageModuleKt.storageModule$lambda$14$lambda$11(scope, parametersHolder);
            case 24:
                return StorageModuleKt.storageModule$lambda$14$lambda$12(scope, parametersHolder);
            case 25:
                return StorageModuleKt.storageModule$lambda$14$lambda$13(scope, parametersHolder);
            case 26:
                return StorageModuleKt.storageModule$lambda$14$lambda$2(scope, parametersHolder);
            case 27:
                return StorageModuleKt.storageModule$lambda$14$lambda$3(scope, parametersHolder);
            case 28:
                return StorageModuleKt.storageModule$lambda$14$lambda$4(scope, parametersHolder);
            default:
                return StorageModuleKt.storageModule$lambda$14$lambda$5(scope, parametersHolder);
        }
    }
}
