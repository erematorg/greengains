package com.reown.sign.storage.link_mode;

import android.database.sqlite.SQLiteException;
import com.reown.sign.storage.data.dao.linkmode.LinkModeDaoQueries;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH@¢\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\tH@¢\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/reown/sign/storage/link_mode/LinkModeStorageRepository;", "", "linkModeDaoQueries", "Lcom/reown/sign/storage/data/dao/linkmode/LinkModeDaoQueries;", "<init>", "(Lcom/reown/sign/storage/data/dao/linkmode/LinkModeDaoQueries;)V", "insert", "", "appLink", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isEnabled", "", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LinkModeStorageRepository {
    /* access modifiers changed from: private */
    @NotNull
    public final LinkModeDaoQueries linkModeDaoQueries;

    public LinkModeStorageRepository(@NotNull LinkModeDaoQueries linkModeDaoQueries2) {
        Intrinsics.checkNotNullParameter(linkModeDaoQueries2, "linkModeDaoQueries");
        this.linkModeDaoQueries = linkModeDaoQueries2;
    }

    public final /* synthetic */ Object insert(String str, Continuation continuation) throws SQLiteException {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new LinkModeStorageRepository$insert$2(this, str, (Continuation<? super LinkModeStorageRepository$insert$2>) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    public final /* synthetic */ Object isEnabled(String str, Continuation continuation) throws SQLiteException {
        return BuildersKt.withContext(Dispatchers.getIO(), new LinkModeStorageRepository$isEnabled$2(this, str, (Continuation<? super LinkModeStorageRepository$isEnabled$2>) null), continuation);
    }
}
