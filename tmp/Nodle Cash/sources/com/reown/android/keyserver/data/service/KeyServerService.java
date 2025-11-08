package com.reown.android.keyserver.data.service;

import com.reown.android.keyserver.model.KeyServerBody;
import com.reown.android.keyserver.model.KeyServerHttpResponse;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H§@¢\u0006\u0002\u0010\u0007J\u001e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\nH§@¢\u0006\u0002\u0010\u000bJ\u001e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00032\b\b\u0001\u0010\u000e\u001a\u00020\u000fH§@¢\u0006\u0002\u0010\u0010J\u001e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00032\b\b\u0001\u0010\u0013\u001a\u00020\u000fH§@¢\u0006\u0002\u0010\u0010J\u001e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0016H§@¢\u0006\u0002\u0010\u0017J\u001e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u001aH§@¢\u0006\u0002\u0010\u001b¨\u0006\u001cÀ\u0006\u0003"}, d2 = {"Lcom/reown/android/keyserver/data/service/KeyServerService;", "", "registerInvite", "Lretrofit2/Response;", "Lcom/reown/android/keyserver/model/KeyServerHttpResponse$RegisterInvite;", "body", "Lcom/reown/android/keyserver/model/KeyServerBody$RegisterInvite;", "(Lcom/reown/android/keyserver/model/KeyServerBody$RegisterInvite;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "unregisterInvite", "Lcom/reown/android/keyserver/model/KeyServerHttpResponse$UnregisterInvite;", "Lcom/reown/android/keyserver/model/KeyServerBody$UnregisterInvite;", "(Lcom/reown/android/keyserver/model/KeyServerBody$UnregisterInvite;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resolveInvite", "Lcom/reown/android/keyserver/model/KeyServerHttpResponse$ResolveInvite;", "account", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resolveIdentity", "Lcom/reown/android/keyserver/model/KeyServerHttpResponse$ResolveIdentity;", "publicKey", "registerIdentity", "Lcom/reown/android/keyserver/model/KeyServerHttpResponse$RegisterIdentity;", "Lcom/reown/android/keyserver/model/KeyServerBody$RegisterIdentity;", "(Lcom/reown/android/keyserver/model/KeyServerBody$RegisterIdentity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "unregisterIdentity", "Lcom/reown/android/keyserver/model/KeyServerHttpResponse$UnregisterIdentity;", "Lcom/reown/android/keyserver/model/KeyServerBody$UnregisterIdentity;", "(Lcom/reown/android/keyserver/model/KeyServerBody$UnregisterIdentity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface KeyServerService {
    @Nullable
    @POST("identity")
    @Headers({"Content-Type: application/json"})
    Object registerIdentity(@NotNull @Body KeyServerBody.RegisterIdentity registerIdentity, @NotNull Continuation<? super Response<KeyServerHttpResponse.RegisterIdentity>> continuation);

    @Nullable
    @POST("invite")
    @Headers({"Content-Type: application/json"})
    Object registerInvite(@NotNull @Body KeyServerBody.RegisterInvite registerInvite, @NotNull Continuation<? super Response<KeyServerHttpResponse.RegisterInvite>> continuation);

    @Nullable
    @GET("identity")
    Object resolveIdentity(@NotNull @Query("publicKey") String str, @NotNull Continuation<? super Response<KeyServerHttpResponse.ResolveIdentity>> continuation);

    @Nullable
    @GET("invite")
    Object resolveInvite(@NotNull @Query("account") String str, @NotNull Continuation<? super Response<KeyServerHttpResponse.ResolveInvite>> continuation);

    @HTTP(hasBody = true, method = "DELETE", path = "identity")
    @Nullable
    @Headers({"Content-Type: application/json"})
    Object unregisterIdentity(@NotNull @Body KeyServerBody.UnregisterIdentity unregisterIdentity, @NotNull Continuation<? super Response<KeyServerHttpResponse.UnregisterIdentity>> continuation);

    @Nullable
    @DELETE("invite")
    @Headers({"Content-Type: application/json"})
    Object unregisterInvite(@NotNull @Body KeyServerBody.UnregisterInvite unregisterInvite, @NotNull Continuation<? super Response<KeyServerHttpResponse.UnregisterInvite>> continuation);
}
