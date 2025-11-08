package com.adjust.sdk.scheduler;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executors;

public abstract class AsyncTaskExecutor<Params, Result> {

    public class a implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Object[] f3326a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ Handler f3327b;

        /* renamed from: com.adjust.sdk.scheduler.AsyncTaskExecutor$a$a  reason: collision with other inner class name */
        public class C0005a implements Runnable {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ Object f3329a;

            public C0005a(Object obj) {
                this.f3329a = obj;
            }

            public final void run() {
                AsyncTaskExecutor.this.onPostExecute(this.f3329a);
            }
        }

        public a(Object[] objArr, Handler handler) {
            this.f3326a = objArr;
            this.f3327b = handler;
        }

        public final void run() {
            this.f3327b.post(new C0005a(AsyncTaskExecutor.this.doInBackground(this.f3326a)));
        }
    }

    public abstract Result doInBackground(Params[] paramsArr);

    @SafeVarargs
    public final AsyncTaskExecutor<Params, Result> execute(Params... paramsArr) {
        onPreExecute();
        Executors.newSingleThreadExecutor().execute(new a(paramsArr, new Handler(Looper.getMainLooper())));
        return this;
    }

    public void onPostExecute(Result result) {
    }

    public void onPreExecute() {
    }
}
