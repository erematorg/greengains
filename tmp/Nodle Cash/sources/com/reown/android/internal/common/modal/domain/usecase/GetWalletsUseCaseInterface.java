package com.reown.android.internal.common.modal.domain.usecase;

import com.reown.android.internal.common.modal.data.model.WalletListing;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001JN\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\n2\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\nH¦B¢\u0006\u0002\u0010\f¨\u0006\rÀ\u0006\u0003"}, d2 = {"Lcom/reown/android/internal/common/modal/domain/usecase/GetWalletsUseCaseInterface;", "", "invoke", "Lcom/reown/android/internal/common/modal/data/model/WalletListing;", "sdkType", "", "page", "", "search", "excludeIds", "", "includeIds", "(Ljava/lang/String;ILjava/lang/String;Ljava/util/List;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface GetWalletsUseCaseInterface {

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    static /* synthetic */ Object invoke$default(GetWalletsUseCaseInterface getWalletsUseCaseInterface, String str, int i3, String str2, List list, List list2, Continuation continuation, int i4, Object obj) {
        if (obj == null) {
            return getWalletsUseCaseInterface.invoke(str, i3, (i4 & 4) != 0 ? null : str2, (i4 & 8) != 0 ? null : list, (i4 & 16) != 0 ? null : list2, continuation);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: invoke");
    }

    @Nullable
    Object invoke(@NotNull String str, int i3, @Nullable String str2, @Nullable List<String> list, @Nullable List<String> list2, @NotNull Continuation<? super WalletListing> continuation);
}
