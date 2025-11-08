package com.reown.android.push.network;

import com.reown.android.push.network.model.PushBody;
import com.reown.android.push.network.model.PushResponse;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J2\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u00062\b\b\u0001\u0010\b\u001a\u00020\tH§@¢\u0006\u0002\u0010\nJ(\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u0006H§@¢\u0006\u0002\u0010\f¨\u0006\rÀ\u0006\u0003"}, d2 = {"Lcom/reown/android/push/network/PushService;", "", "register", "Lretrofit2/Response;", "Lcom/reown/android/push/network/model/PushResponse;", "projectId", "", "clientID", "echoClientsBody", "Lcom/reown/android/push/network/model/PushBody;", "(Ljava/lang/String;Ljava/lang/String;Lcom/reown/android/push/network/model/PushBody;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "unregister", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface PushService {
    @POST("{projectId}/clients")
    @Nullable
    Object register(@NotNull @Path("projectId") String str, @NotNull @Query("auth") String str2, @NotNull @Body PushBody pushBody, @NotNull Continuation<? super Response<PushResponse>> continuation);

    @DELETE("{projectId}/clients/{clientId}")
    @Nullable
    Object unregister(@NotNull @Path("projectId") String str, @NotNull @Path("clientId") String str2, @NotNull Continuation<? super Response<PushResponse>> continuation);
}
