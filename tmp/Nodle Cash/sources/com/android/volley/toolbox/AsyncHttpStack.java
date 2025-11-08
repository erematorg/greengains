package com.android.volley.toolbox;

import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyLog;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicReference;

public abstract class AsyncHttpStack extends BaseHttpStack {
    private ExecutorService mBlockingExecutor;
    private ExecutorService mNonBlockingExecutor;

    public interface OnRequestComplete {
        void onAuthError(AuthFailureError authFailureError);

        void onError(IOException iOException);

        void onSuccess(HttpResponse httpResponse);
    }

    public static class Response {
        AuthFailureError authFailureError;
        HttpResponse httpResponse;
        IOException ioException;

        private Response(@Nullable HttpResponse httpResponse2, @Nullable IOException iOException, @Nullable AuthFailureError authFailureError2) {
            this.httpResponse = httpResponse2;
            this.ioException = iOException;
            this.authFailureError = authFailureError2;
        }
    }

    public final HttpResponse executeRequest(Request<?> request, Map<String, String> map) throws IOException, AuthFailureError {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final AtomicReference atomicReference = new AtomicReference();
        executeRequest(request, map, new OnRequestComplete() {
            public void onAuthError(AuthFailureError authFailureError) {
                atomicReference.set(new Response((HttpResponse) null, (IOException) null, authFailureError));
                countDownLatch.countDown();
            }

            public void onError(IOException iOException) {
                atomicReference.set(new Response((HttpResponse) null, iOException, (AuthFailureError) null));
                countDownLatch.countDown();
            }

            public void onSuccess(HttpResponse httpResponse) {
                atomicReference.set(new Response(httpResponse, (IOException) null, (AuthFailureError) null));
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
            Response response = (Response) atomicReference.get();
            HttpResponse httpResponse = response.httpResponse;
            if (httpResponse != null) {
                return httpResponse;
            }
            IOException iOException = response.ioException;
            if (iOException != null) {
                throw iOException;
            }
            throw response.authFailureError;
        } catch (InterruptedException e3) {
            VolleyLog.e(e3, "while waiting for CountDownLatch", new Object[0]);
            Thread.currentThread().interrupt();
            throw new InterruptedIOException(e3.toString());
        }
    }

    public abstract void executeRequest(Request<?> request, Map<String, String> map, OnRequestComplete onRequestComplete);

    public ExecutorService getBlockingExecutor() {
        return this.mBlockingExecutor;
    }

    public ExecutorService getNonBlockingExecutor() {
        return this.mNonBlockingExecutor;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setBlockingExecutor(ExecutorService executorService) {
        this.mBlockingExecutor = executorService;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setNonBlockingExecutor(ExecutorService executorService) {
        this.mNonBlockingExecutor = executorService;
    }
}
