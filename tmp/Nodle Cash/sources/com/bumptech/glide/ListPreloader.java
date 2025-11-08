package com.bumptech.glide;

import android.graphics.drawable.Drawable;
import android.widget.AbsListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.util.Util;
import java.util.List;
import java.util.Queue;

public class ListPreloader<T> implements AbsListView.OnScrollListener {
    private boolean isIncreasing = true;
    private int lastEnd;
    private int lastFirstVisible = -1;
    private int lastStart;
    private final int maxPreload;
    private final PreloadSizeProvider<T> preloadDimensionProvider;
    private final PreloadModelProvider<T> preloadModelProvider;
    private final PreloadTargetQueue preloadTargetQueue;
    private final RequestManager requestManager;
    private int totalItemCount;

    public interface PreloadModelProvider<U> {
        @NonNull
        List<U> getPreloadItems(int i3);

        @Nullable
        RequestBuilder<?> getPreloadRequestBuilder(@NonNull U u3);
    }

    public interface PreloadSizeProvider<T> {
        @Nullable
        int[] getPreloadSize(@NonNull T t2, int i3, int i4);
    }

    public static final class PreloadTarget implements Target<Object> {
        int photoHeight;
        int photoWidth;
        @Nullable
        private Request request;

        @Nullable
        public Request getRequest() {
            return this.request;
        }

        public void getSize(@NonNull SizeReadyCallback sizeReadyCallback) {
            sizeReadyCallback.onSizeReady(this.photoWidth, this.photoHeight);
        }

        public void onDestroy() {
        }

        public void onLoadCleared(@Nullable Drawable drawable) {
        }

        public void onLoadFailed(@Nullable Drawable drawable) {
        }

        public void onLoadStarted(@Nullable Drawable drawable) {
        }

        public void onResourceReady(@NonNull Object obj, @Nullable Transition<? super Object> transition) {
        }

        public void onStart() {
        }

        public void onStop() {
        }

        public void removeCallback(@NonNull SizeReadyCallback sizeReadyCallback) {
        }

        public void setRequest(@Nullable Request request2) {
            this.request = request2;
        }
    }

    public static final class PreloadTargetQueue {
        final Queue<PreloadTarget> queue;

        public PreloadTargetQueue(int i3) {
            this.queue = Util.createQueue(i3);
            for (int i4 = 0; i4 < i3; i4++) {
                this.queue.offer(new PreloadTarget());
            }
        }

        public PreloadTarget next(int i3, int i4) {
            PreloadTarget poll = this.queue.poll();
            this.queue.offer(poll);
            poll.photoWidth = i3;
            poll.photoHeight = i4;
            return poll;
        }
    }

    public ListPreloader(@NonNull RequestManager requestManager2, @NonNull PreloadModelProvider<T> preloadModelProvider2, @NonNull PreloadSizeProvider<T> preloadSizeProvider, int i3) {
        this.requestManager = requestManager2;
        this.preloadModelProvider = preloadModelProvider2;
        this.preloadDimensionProvider = preloadSizeProvider;
        this.maxPreload = i3;
        this.preloadTargetQueue = new PreloadTargetQueue(i3 + 1);
    }

    private void cancelAll() {
        for (int i3 = 0; i3 < this.preloadTargetQueue.queue.size(); i3++) {
            this.requestManager.clear((Target<?>) this.preloadTargetQueue.next(0, 0));
        }
    }

    private void preload(int i3, boolean z2) {
        if (this.isIncreasing != z2) {
            this.isIncreasing = z2;
            cancelAll();
        }
        preload(i3, (z2 ? this.maxPreload : -this.maxPreload) + i3);
    }

    private void preloadAdapterPosition(List<T> list, int i3, boolean z2) {
        int size = list.size();
        if (z2) {
            for (int i4 = 0; i4 < size; i4++) {
                preloadItem(list.get(i4), i3, i4);
            }
            return;
        }
        for (int i5 = size - 1; i5 >= 0; i5--) {
            preloadItem(list.get(i5), i3, i5);
        }
    }

    private void preloadItem(@Nullable T t2, int i3, int i4) {
        int[] preloadSize;
        RequestBuilder<?> preloadRequestBuilder;
        if (t2 != null && (preloadSize = this.preloadDimensionProvider.getPreloadSize(t2, i3, i4)) != null && (preloadRequestBuilder = this.preloadModelProvider.getPreloadRequestBuilder(t2)) != null) {
            preloadRequestBuilder.into(this.preloadTargetQueue.next(preloadSize[0], preloadSize[1]));
        }
    }

    public void onScroll(AbsListView absListView, int i3, int i4, int i5) {
        if (this.totalItemCount != 0 || i5 != 0) {
            this.totalItemCount = i5;
            int i6 = this.lastFirstVisible;
            if (i3 > i6) {
                preload(i4 + i3, true);
            } else if (i3 < i6) {
                preload(i3, false);
            }
            this.lastFirstVisible = i3;
        }
    }

    public void onScrollStateChanged(AbsListView absListView, int i3) {
    }

    private void preload(int i3, int i4) {
        int i5;
        int i6;
        if (i3 < i4) {
            i5 = Math.max(this.lastEnd, i3);
            i6 = i4;
        } else {
            i6 = Math.min(this.lastStart, i3);
            i5 = i4;
        }
        int min = Math.min(this.totalItemCount, i6);
        int min2 = Math.min(this.totalItemCount, Math.max(0, i5));
        if (i3 < i4) {
            for (int i7 = min2; i7 < min; i7++) {
                preloadAdapterPosition(this.preloadModelProvider.getPreloadItems(i7), i7, true);
            }
        } else {
            for (int i8 = min - 1; i8 >= min2; i8--) {
                preloadAdapterPosition(this.preloadModelProvider.getPreloadItems(i8), i8, false);
            }
        }
        this.lastStart = min2;
        this.lastEnd = min;
    }
}
