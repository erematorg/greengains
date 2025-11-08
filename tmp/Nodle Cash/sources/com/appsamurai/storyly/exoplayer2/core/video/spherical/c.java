package com.appsamurai.storyly.exoplayer2.core.video.spherical;

import android.app.job.JobParameters;
import android.graphics.SurfaceTexture;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.JobInfoSchedulerService;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.android.HandlerContext;
import org.web3j.protocol.core.filters.Filter;
import org.web3j.protocol.core.methods.response.EthFilter;
import org.web3j.utils.Async;

public final /* synthetic */ class c implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4606a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f4607b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f4608c;

    public /* synthetic */ c(Object obj, Object obj2, int i3) {
        this.f4606a = i3;
        this.f4607b = obj;
        this.f4608c = obj2;
    }

    public final void run() {
        switch (this.f4606a) {
            case 0:
                ((SphericalGLSurfaceView) this.f4607b).lambda$onSurfaceTextureAvailable$1((SurfaceTexture) this.f4608c);
                return;
            case 1:
                ((JobInfoSchedulerService) this.f4607b).lambda$onStartJob$0((JobParameters) this.f4608c);
                return;
            case 2:
                ((RemovalListener) this.f4607b).onRemoval((RemovalNotification) this.f4608c);
                return;
            case 3:
                HandlerContext.scheduleResumeAfterDelay$lambda$1((CancellableContinuation) this.f4607b, (HandlerContext) this.f4608c);
                return;
            case 4:
                ((Filter) this.f4607b).lambda$run$0((EthFilter) this.f4608c);
                return;
            default:
                Async.lambda$run$1((CompletableFuture) this.f4607b, (Callable) this.f4608c);
                return;
        }
    }
}
