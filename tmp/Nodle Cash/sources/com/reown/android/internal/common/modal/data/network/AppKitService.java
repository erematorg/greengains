package com.reown.android.internal.common.modal.data.network;

import com.amplitude.api.DeviceInfo;
import com.reown.android.internal.common.modal.data.network.model.EnableAnalyticsDTO;
import com.reown.android.internal.common.modal.data.network.model.GetAndroidDataDTO;
import com.reown.android.internal.common.modal.data.network.model.GetWalletsDTO;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J`\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\b2\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u00062\b\b\u0003\u0010\f\u001a\u00020\b2\b\b\u0003\u0010\r\u001a\u00020\u0006H§@¢\u0006\u0002\u0010\u000eJ\u001e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H§@¢\u0006\u0002\u0010\u0011J\u001e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H§@¢\u0006\u0002\u0010\u0011¨\u0006\u0014À\u0006\u0003"}, d2 = {"Lcom/reown/android/internal/common/modal/data/network/AppKitService;", "", "getWallets", "Lretrofit2/Response;", "Lcom/reown/android/internal/common/modal/data/network/model/GetWalletsDTO;", "sdkType", "", "page", "", "search", "exclude", "include", "entries", "platform", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAndroidData", "Lcom/reown/android/internal/common/modal/data/network/model/GetAndroidDataDTO;", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAnalyticsConfig", "Lcom/reown/android/internal/common/modal/data/network/model/EnableAnalyticsDTO;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface AppKitService {

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    static /* synthetic */ Object getWallets$default(AppKitService appKitService, String str, int i3, String str2, String str3, String str4, int i4, String str5, Continuation continuation, int i5, Object obj) {
        if (obj == null) {
            return appKitService.getWallets(str, i3, (i5 & 4) != 0 ? null : str2, (i5 & 8) != 0 ? null : str3, (i5 & 16) != 0 ? null : str4, (i5 & 32) != 0 ? 100 : i4, (i5 & 64) != 0 ? DeviceInfo.OS_NAME : str5, continuation);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getWallets");
    }

    @Nullable
    @GET("getAnalyticsConfig")
    Object getAnalyticsConfig(@NotNull @Header("x-sdk-type") String str, @NotNull Continuation<? super Response<EnableAnalyticsDTO>> continuation);

    @Nullable
    @GET("getAndroidData")
    Object getAndroidData(@NotNull @Header("x-sdk-type") String str, @NotNull Continuation<? super Response<GetAndroidDataDTO>> continuation);

    @Nullable
    @GET("getWallets")
    Object getWallets(@NotNull @Header("x-sdk-type") String str, @Query("page") int i3, @Nullable @Query("search") String str2, @Nullable @Query("exclude") String str3, @Nullable @Query("include") String str4, @Query("entries") int i4, @NotNull @Query("platform") String str5, @NotNull Continuation<? super Response<GetWalletsDTO>> continuation);
}
