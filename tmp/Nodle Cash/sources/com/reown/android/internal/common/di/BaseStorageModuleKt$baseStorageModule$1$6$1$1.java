package com.reown.android.internal.common.di;

import com.reown.android.sdk.core.AndroidCoreDatabase;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;

@SourceDebugExtension({"SMAP\nBaseStorageModule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BaseStorageModule.kt\ncom/reown/android/internal/common/di/BaseStorageModuleKt$baseStorageModule$1$6$1$1\n+ 2 Scope.kt\norg/koin/core/scope/Scope\n*L\n1#1,140:1\n138#2,5:141\n*S KotlinDebug\n*F\n+ 1 BaseStorageModule.kt\ncom/reown/android/internal/common/di/BaseStorageModuleKt$baseStorageModule$1$6$1$1\n*L\n97#1:141,5\n*E\n"})
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.internal.common.di.BaseStorageModuleKt$baseStorageModule$1$6$1$1", f = "BaseStorageModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class BaseStorageModuleKt$baseStorageModule$1$6$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ AndroidCoreDatabase $database;
    final /* synthetic */ Scope $this_single;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BaseStorageModuleKt$baseStorageModule$1$6$1$1(AndroidCoreDatabase androidCoreDatabase, Scope scope, Continuation<? super BaseStorageModuleKt$baseStorageModule$1$6$1$1> continuation) {
        super(2, continuation);
        this.$database = androidCoreDatabase;
        this.$this_single = scope;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BaseStorageModuleKt$baseStorageModule$1$6$1$1(this.$database, this.$this_single, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            try {
                this.$database.getJsonRpcHistoryQueries().selectLastInsertedRowId().executeAsOneOrNull();
            } catch (Exception unused) {
                Scope scope = this.$this_single;
                DatabaseConfigKt.deleteDatabase(scope, ((DatabaseConfig) scope.get(Reflection.getOrCreateKotlinClass(DatabaseConfig.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null)).getANDROID_CORE_DB_NAME());
                AndroidCoreDatabase unused2 = BaseStorageModuleKt.baseStorageModule$lambda$23$createCoreDB(this.$this_single);
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BaseStorageModuleKt$baseStorageModule$1$6$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
