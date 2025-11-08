package com.reown.android.internal.common.explorer;

import com.reown.android.internal.common.explorer.data.model.Project;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J<\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH¦@¢\u0006\u0004\b\f\u0010\r¨\u0006\u000eÀ\u0006\u0003"}, d2 = {"Lcom/reown/android/internal/common/explorer/ExplorerInterface;", "", "getProjects", "Lkotlin/Result;", "", "Lcom/reown/android/internal/common/explorer/data/model/Project;", "page", "", "entries", "isVerified", "", "isFeatured", "getProjects-yxL6bBk", "(IIZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface ExplorerInterface {
    @Nullable
    /* renamed from: getProjects-yxL6bBk  reason: not valid java name */
    Object m8733getProjectsyxL6bBk(int i3, int i4, boolean z2, boolean z3, @NotNull Continuation<? super Result<? extends List<Project>>> continuation);
}
