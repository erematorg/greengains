package com.reown.android.verify.data;

import com.reown.android.verify.model.Origin;
import com.reown.android.verify.model.RegisterAttestationBody;
import com.reown.android.verify.model.VerifyServerPublicKey;
import com.reown.android.verify.model.VerifyServerResponse;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H§@¢\u0006\u0002\u0010\u0007J\u001e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010\n\u001a\u00020\u000bH§@¢\u0006\u0002\u0010\fJ\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0003H§@¢\u0006\u0002\u0010\u000f¨\u0006\u0010À\u0006\u0003"}, d2 = {"Lcom/reown/android/verify/data/VerifyService;", "", "registerAttestation", "Lretrofit2/Response;", "Lcom/reown/android/verify/model/VerifyServerResponse$RegisterAttestation;", "body", "Lcom/reown/android/verify/model/RegisterAttestationBody;", "(Lcom/reown/android/verify/model/RegisterAttestationBody;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resolveAttestation", "Lcom/reown/android/verify/model/Origin;", "attestationId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPublicKey", "Lcom/reown/android/verify/model/VerifyServerPublicKey;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface VerifyService {
    @Nullable
    @GET("v2/public-key")
    Object getPublicKey(@NotNull Continuation<? super Response<VerifyServerPublicKey>> continuation);

    @Nullable
    @POST("attestation")
    @Headers({"Content-Type: application/json"})
    Object registerAttestation(@NotNull @Body RegisterAttestationBody registerAttestationBody, @NotNull Continuation<? super Response<VerifyServerResponse.RegisterAttestation>> continuation);

    @Nullable
    @GET("attestation/{attestationId}?v2Supported=true")
    @Headers({"Content-Type: application/json"})
    Object resolveAttestation(@NotNull @Path("attestationId") String str, @NotNull Continuation<? super Response<Origin>> continuation);
}
