package com.appsamurai.storyly.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import androidx.core.app.NotificationCompat;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class n extends Handler {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ o f6344a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public n(o oVar, Looper looper) {
        super(looper);
        this.f6344a = oVar;
    }

    public void handleMessage(@NotNull Message message) {
        Intrinsics.checkNotNullParameter(message, NotificationCompat.CATEGORY_MESSAGE);
        o oVar = this.f6344a;
        synchronized (oVar) {
            try {
                if (!oVar.f6354i) {
                    long elapsedRealtime = oVar.f6351f - SystemClock.elapsedRealtime();
                    if (elapsedRealtime <= 0) {
                        Function0<Unit> function0 = oVar.f6350e;
                        if (function0 != null) {
                            function0.invoke();
                        }
                    } else if (elapsedRealtime < oVar.f6348c) {
                        sendMessageDelayed(obtainMessage(1), elapsedRealtime);
                    } else {
                        long elapsedRealtime2 = SystemClock.elapsedRealtime();
                        Function1<? super Long, Unit> function1 = oVar.f6349d;
                        if (function1 != null) {
                            function1.invoke(Long.valueOf(oVar.f6347b - elapsedRealtime));
                        }
                        long elapsedRealtime3 = elapsedRealtime2 - SystemClock.elapsedRealtime();
                        long j2 = oVar.f6348c;
                        while (true) {
                            elapsedRealtime3 += j2;
                            if (elapsedRealtime3 >= 0) {
                                break;
                            }
                            j2 = oVar.f6348c;
                        }
                        if (!oVar.f6353h) {
                            sendMessageDelayed(obtainMessage(1), elapsedRealtime3);
                        }
                    }
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
