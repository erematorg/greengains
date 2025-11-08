package com.reown.sign.di;

import com.reown.android.internal.common.di.DatabaseConfigKt;
import com.reown.sign.SignDatabase;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.koin.core.scope.Scope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.di.StorageModuleKt$storageModule$1$1$1$1", f = "StorageModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class StorageModuleKt$storageModule$1$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $dbName;
    final /* synthetic */ SignDatabase $signDatabase;
    final /* synthetic */ Scope $this_single;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public StorageModuleKt$storageModule$1$1$1$1(SignDatabase signDatabase, Scope scope, String str, Continuation<? super StorageModuleKt$storageModule$1$1$1$1> continuation) {
        super(2, continuation);
        this.$signDatabase = signDatabase;
        this.$this_single = scope;
        this.$dbName = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new StorageModuleKt$storageModule$1$1$1$1(this.$signDatabase, this.$this_single, this.$dbName, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            try {
                this.$signDatabase.getSessionDaoQueries().lastInsertedRow().executeAsOneOrNull();
            } catch (Exception unused) {
                DatabaseConfigKt.deleteDatabase(this.$this_single, this.$dbName);
                SignDatabase unused2 = StorageModuleKt.storageModule$lambda$14$createSignDB(this.$this_single, this.$dbName);
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((StorageModuleKt$storageModule$1$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
