package com.reown.android.internal.common.storage.verify;

import android.database.sqlite.SQLiteException;
import com.reown.android.internal.common.model.Validation;
import com.reown.android.sdk.storage.data.dao.VerifyContextQueries;
import com.reown.android.verify.model.VerifyContext;
import com.reown.foundation.util.Logger;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH@¢\u0006\u0002\u0010\fJ\u0018\u0010\r\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u000e\u001a\u00020\u000fH@¢\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0012H@¢\u0006\u0002\u0010\u0013J\u0016\u0010\u0014\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fH@¢\u0006\u0002\u0010\u0010J7\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00172\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0002¢\u0006\u0002\u0010\u001dR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/reown/android/internal/common/storage/verify/VerifyContextStorageRepository;", "", "verifyContextQueries", "Lcom/reown/android/sdk/storage/data/dao/VerifyContextQueries;", "logger", "Lcom/reown/foundation/util/Logger;", "<init>", "(Lcom/reown/android/sdk/storage/data/dao/VerifyContextQueries;Lcom/reown/foundation/util/Logger;)V", "insertOrAbort", "", "verifyContext", "Lcom/reown/android/verify/model/VerifyContext;", "(Lcom/reown/android/verify/model/VerifyContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "get", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAll", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delete", "toVerifyContext", "origin", "", "validation", "Lcom/reown/android/internal/common/model/Validation;", "verifyUrl", "isScam", "", "(JLjava/lang/String;Lcom/reown/android/internal/common/model/Validation;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/reown/android/verify/model/VerifyContext;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VerifyContextStorageRepository {
    @NotNull
    private final Logger logger;
    @NotNull
    private final VerifyContextQueries verifyContextQueries;

    public VerifyContextStorageRepository(@NotNull VerifyContextQueries verifyContextQueries2, @NotNull Logger logger2) {
        Intrinsics.checkNotNullParameter(verifyContextQueries2, "verifyContextQueries");
        Intrinsics.checkNotNullParameter(logger2, "logger");
        this.verifyContextQueries = verifyContextQueries2;
        this.logger = logger2;
    }

    /* access modifiers changed from: private */
    public final VerifyContext toVerifyContext(long j2, String str, Validation validation, String str2, Boolean bool) {
        return new VerifyContext(j2, str, validation, str2, bool);
    }

    @Nullable
    public final Object delete(long j2, @NotNull Continuation<? super Unit> continuation) {
        try {
            this.verifyContextQueries.deleteVerifyContext(j2);
        } catch (Exception e3) {
            this.logger.error((Throwable) e3);
        }
        return Unit.INSTANCE;
    }

    @Nullable
    public final Object get(long j2, @NotNull Continuation<? super VerifyContext> continuation) {
        try {
            return (VerifyContext) this.verifyContextQueries.getVerifyContextById(j2, new VerifyContextStorageRepository$get$2(this)).executeAsOneOrNull();
        } catch (Exception unused) {
            return null;
        }
    }

    @Nullable
    public final Object getAll(@NotNull Continuation<? super List<VerifyContext>> continuation) throws SQLiteException {
        return this.verifyContextQueries.geListOfVerifyContexts(new VerifyContextStorageRepository$getAll$2(this)).executeAsList();
    }

    @Nullable
    public final Object insertOrAbort(@NotNull VerifyContext verifyContext, @NotNull Continuation<? super Unit> continuation) throws SQLiteException {
        this.verifyContextQueries.insertOrAbortVerifyContext(Boxing.boxLong(verifyContext.getId()), verifyContext.getOrigin(), verifyContext.getValidation(), verifyContext.getVerifyUrl(), verifyContext.isScam());
        return Unit.INSTANCE;
    }
}
