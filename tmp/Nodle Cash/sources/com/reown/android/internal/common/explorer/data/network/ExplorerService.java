package com.reown.android.internal.common.explorer.data.network;

import com.reown.android.internal.common.explorer.data.network.model.DappListingsDTO;
import com.reown.android.internal.common.explorer.data.network.model.NotifyConfigDTO;
import com.reown.android.internal.common.explorer.data.network.model.ProjectListingDTO;
import com.reown.android.internal.common.explorer.data.network.model.WalletListingDTO;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H§@¢\u0006\u0002\u0010\u0007JF\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\n\u001a\u00020\u000b2\b\b\u0001\u0010\f\u001a\u00020\u000b2\b\b\u0001\u0010\r\u001a\u00020\u000e2\b\b\u0001\u0010\u000f\u001a\u00020\u000eH§@¢\u0006\u0002\u0010\u0010J(\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0013\u001a\u00020\u0006H§@¢\u0006\u0002\u0010\u0014JV\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\n\b\u0001\u0010\u0017\u001a\u0004\u0018\u00010\u00062\b\b\u0001\u0010\u0018\u001a\u00020\u00062\b\b\u0001\u0010\u0019\u001a\u00020\u00062\n\b\u0001\u0010\u001a\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u001b\u001a\u0004\u0018\u00010\u0006H§@¢\u0006\u0002\u0010\u001c¨\u0006\u001dÀ\u0006\u0003"}, d2 = {"Lcom/reown/android/internal/common/explorer/data/network/ExplorerService;", "", "getAllDapps", "Lretrofit2/Response;", "Lcom/reown/android/internal/common/explorer/data/network/model/DappListingsDTO;", "projectId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getProjects", "Lcom/reown/android/internal/common/explorer/data/network/model/ProjectListingDTO;", "entries", "", "page", "isVerified", "", "isFeatured", "(Ljava/lang/String;IIZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getNotifyConfig", "Lcom/reown/android/internal/common/explorer/data/network/model/NotifyConfigDTO;", "appDomain", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAndroidWallets", "Lcom/reown/android/internal/common/explorer/data/network/model/WalletListingDTO;", "chains", "sdkType", "sdkVersion", "excludedIds", "recommendedIds", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface ExplorerService {
    @Nullable
    @GET("v3/dapps")
    Object getAllDapps(@NotNull @Query("projectId") String str, @NotNull Continuation<? super Response<DappListingsDTO>> continuation);

    @Nullable
    @GET("w3m/v1/getAndroidListings")
    Object getAndroidWallets(@NotNull @Query("projectId") String str, @Nullable @Query("chains") String str2, @NotNull @Query("sdkType") String str3, @NotNull @Query("sdkVersion") String str4, @Nullable @Query("excludedIds") String str5, @Nullable @Query("recommendedIds") String str6, @NotNull Continuation<? super Response<WalletListingDTO>> continuation);

    @Nullable
    @GET("w3i/v1/notify-config")
    Object getNotifyConfig(@NotNull @Query("projectId") String str, @NotNull @Query("appDomain") String str2, @NotNull Continuation<? super Response<NotifyConfigDTO>> continuation);

    @Nullable
    @GET("w3i/v1/projects")
    Object getProjects(@NotNull @Query("projectId") String str, @Query("entries") int i3, @Query("page") int i4, @Query("isVerified") boolean z2, @Query("isFeatured") boolean z3, @NotNull Continuation<? super Response<ProjectListingDTO>> continuation);
}
