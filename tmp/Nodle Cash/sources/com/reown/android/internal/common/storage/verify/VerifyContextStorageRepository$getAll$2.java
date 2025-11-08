package com.reown.android.internal.common.storage.verify;

import com.reown.android.internal.common.model.Validation;
import com.reown.android.verify.model.VerifyContext;
import kotlin.Metadata;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
public final /* synthetic */ class VerifyContextStorageRepository$getAll$2 extends FunctionReferenceImpl implements Function5<Long, String, Validation, String, Boolean, VerifyContext> {
    public VerifyContextStorageRepository$getAll$2(Object obj) {
        super(5, obj, VerifyContextStorageRepository.class, "toVerifyContext", "toVerifyContext(JLjava/lang/String;Lcom/reown/android/internal/common/model/Validation;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/reown/android/verify/model/VerifyContext;", 0);
    }

    public final VerifyContext invoke(long j2, String str, Validation validation, String str2, Boolean bool) {
        Intrinsics.checkNotNullParameter(str, "p1");
        Intrinsics.checkNotNullParameter(validation, "p2");
        Intrinsics.checkNotNullParameter(str2, "p3");
        return ((VerifyContextStorageRepository) this.receiver).toVerifyContext(j2, str, validation, str2, bool);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        return invoke(((Number) obj).longValue(), (String) obj2, (Validation) obj3, (String) obj4, (Boolean) obj5);
    }
}
