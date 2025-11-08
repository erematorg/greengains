package com.reown.android.pulse.data;

import com.reown.android.pulse.model.Event;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\bH§@¢\u0006\u0002\u0010\tJ.\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u000e\b\u0001\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u000bH§@¢\u0006\u0002\u0010\f¨\u0006\rÀ\u0006\u0003"}, d2 = {"Lcom/reown/android/pulse/data/PulseService;", "", "sendEvent", "Lretrofit2/Response;", "", "sdkType", "", "body", "Lcom/reown/android/pulse/model/Event;", "(Ljava/lang/String;Lcom/reown/android/pulse/model/Event;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendEventBatch", "", "(Ljava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface PulseService {
    @Nullable
    @POST("/e")
    @Headers({"Content-Type: application/json"})
    Object sendEvent(@NotNull @Header("x-sdk-type") String str, @NotNull @Body Event event, @NotNull Continuation<? super Response<Unit>> continuation);

    @Nullable
    @POST("/batch")
    @Headers({"Content-Type: application/json"})
    Object sendEventBatch(@NotNull @Header("x-sdk-type") String str, @NotNull @Body List<Event> list, @NotNull Continuation<? super Response<Unit>> continuation);
}
