package com.airbnb.lottie.animation.keyframe;

import android.support.v4.media.session.a;
import android.view.animation.Interpolator;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.airbnb.lottie.L;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseKeyframeAnimation<K, A> {
    private float cachedEndProgress = -1.0f;
    @Nullable
    private A cachedGetValue = null;
    private float cachedStartDelayProgress = -1.0f;
    private boolean isDiscrete = false;
    private final KeyframesWrapper<K> keyframesWrapper;
    final List<AnimationListener> listeners = new ArrayList(1);
    protected float progress = 0.0f;
    @Nullable
    protected LottieValueCallback<A> valueCallback;

    public interface AnimationListener {
        void onValueChanged();
    }

    public static final class EmptyKeyframeWrapper<T> implements KeyframesWrapper<T> {
        private EmptyKeyframeWrapper() {
        }

        public Keyframe<T> getCurrentKeyframe() {
            throw new IllegalStateException("not implemented");
        }

        public float getEndProgress() {
            return 1.0f;
        }

        public float getStartDelayProgress() {
            return 0.0f;
        }

        public boolean isCachedValueEnabled(float f2) {
            throw new IllegalStateException("not implemented");
        }

        public boolean isEmpty() {
            return true;
        }

        public boolean isValueChanged(float f2) {
            return false;
        }
    }

    public interface KeyframesWrapper<T> {
        Keyframe<T> getCurrentKeyframe();

        @FloatRange(from = 0.0d, to = 1.0d)
        float getEndProgress();

        @FloatRange(from = 0.0d, to = 1.0d)
        float getStartDelayProgress();

        boolean isCachedValueEnabled(float f2);

        boolean isEmpty();

        boolean isValueChanged(float f2);
    }

    public static final class KeyframesWrapperImpl<T> implements KeyframesWrapper<T> {
        private Keyframe<T> cachedCurrentKeyframe = null;
        private float cachedInterpolatedProgress = -1.0f;
        @NonNull
        private Keyframe<T> currentKeyframe;
        private final List<? extends Keyframe<T>> keyframes;

        public KeyframesWrapperImpl(List<? extends Keyframe<T>> list) {
            this.keyframes = list;
            this.currentKeyframe = findKeyframe(0.0f);
        }

        private Keyframe<T> findKeyframe(float f2) {
            Keyframe<T> keyframe = (Keyframe) a.h(this.keyframes, 1);
            if (f2 >= keyframe.getStartProgress()) {
                return keyframe;
            }
            for (int size = this.keyframes.size() - 2; size >= 1; size--) {
                Keyframe<T> keyframe2 = (Keyframe) this.keyframes.get(size);
                if (this.currentKeyframe != keyframe2 && keyframe2.containsProgress(f2)) {
                    return keyframe2;
                }
            }
            return (Keyframe) this.keyframes.get(0);
        }

        @NonNull
        public Keyframe<T> getCurrentKeyframe() {
            return this.currentKeyframe;
        }

        public float getEndProgress() {
            return ((Keyframe) a.h(this.keyframes, 1)).getEndProgress();
        }

        public float getStartDelayProgress() {
            return ((Keyframe) this.keyframes.get(0)).getStartProgress();
        }

        public boolean isCachedValueEnabled(float f2) {
            Keyframe<T> keyframe = this.cachedCurrentKeyframe;
            Keyframe<T> keyframe2 = this.currentKeyframe;
            if (keyframe == keyframe2 && this.cachedInterpolatedProgress == f2) {
                return true;
            }
            this.cachedCurrentKeyframe = keyframe2;
            this.cachedInterpolatedProgress = f2;
            return false;
        }

        public boolean isEmpty() {
            return false;
        }

        public boolean isValueChanged(float f2) {
            if (this.currentKeyframe.containsProgress(f2)) {
                return !this.currentKeyframe.isStatic();
            }
            this.currentKeyframe = findKeyframe(f2);
            return true;
        }
    }

    public static final class SingleKeyframeWrapper<T> implements KeyframesWrapper<T> {
        private float cachedInterpolatedProgress = -1.0f;
        @NonNull
        private final Keyframe<T> keyframe;

        public SingleKeyframeWrapper(List<? extends Keyframe<T>> list) {
            this.keyframe = (Keyframe) list.get(0);
        }

        public Keyframe<T> getCurrentKeyframe() {
            return this.keyframe;
        }

        public float getEndProgress() {
            return this.keyframe.getEndProgress();
        }

        public float getStartDelayProgress() {
            return this.keyframe.getStartProgress();
        }

        public boolean isCachedValueEnabled(float f2) {
            if (this.cachedInterpolatedProgress == f2) {
                return true;
            }
            this.cachedInterpolatedProgress = f2;
            return false;
        }

        public boolean isEmpty() {
            return false;
        }

        public boolean isValueChanged(float f2) {
            return !this.keyframe.isStatic();
        }
    }

    public BaseKeyframeAnimation(List<? extends Keyframe<K>> list) {
        this.keyframesWrapper = wrap(list);
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    private float getStartDelayProgress() {
        if (this.cachedStartDelayProgress == -1.0f) {
            this.cachedStartDelayProgress = this.keyframesWrapper.getStartDelayProgress();
        }
        return this.cachedStartDelayProgress;
    }

    private static <T> KeyframesWrapper<T> wrap(List<? extends Keyframe<T>> list) {
        return list.isEmpty() ? new EmptyKeyframeWrapper() : list.size() == 1 ? new SingleKeyframeWrapper(list) : new KeyframesWrapperImpl(list);
    }

    public void addUpdateListener(AnimationListener animationListener) {
        this.listeners.add(animationListener);
    }

    public Keyframe<K> getCurrentKeyframe() {
        L.beginSection("BaseKeyframeAnimation#getCurrentKeyframe");
        Keyframe<K> currentKeyframe = this.keyframesWrapper.getCurrentKeyframe();
        L.endSection("BaseKeyframeAnimation#getCurrentKeyframe");
        return currentKeyframe;
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public float getEndProgress() {
        if (this.cachedEndProgress == -1.0f) {
            this.cachedEndProgress = this.keyframesWrapper.getEndProgress();
        }
        return this.cachedEndProgress;
    }

    public float getInterpolatedCurrentKeyframeProgress() {
        Keyframe currentKeyframe = getCurrentKeyframe();
        if (currentKeyframe == null || currentKeyframe.isStatic()) {
            return 0.0f;
        }
        return currentKeyframe.interpolator.getInterpolation(getLinearCurrentKeyframeProgress());
    }

    public float getLinearCurrentKeyframeProgress() {
        if (this.isDiscrete) {
            return 0.0f;
        }
        Keyframe currentKeyframe = getCurrentKeyframe();
        if (currentKeyframe.isStatic()) {
            return 0.0f;
        }
        return (this.progress - currentKeyframe.getStartProgress()) / (currentKeyframe.getEndProgress() - currentKeyframe.getStartProgress());
    }

    public float getProgress() {
        return this.progress;
    }

    public A getValue() {
        A a2;
        float linearCurrentKeyframeProgress = getLinearCurrentKeyframeProgress();
        if (this.valueCallback == null && this.keyframesWrapper.isCachedValueEnabled(linearCurrentKeyframeProgress)) {
            return this.cachedGetValue;
        }
        Keyframe currentKeyframe = getCurrentKeyframe();
        Interpolator interpolator = currentKeyframe.xInterpolator;
        if (interpolator == null || currentKeyframe.yInterpolator == null) {
            a2 = getValue(currentKeyframe, getInterpolatedCurrentKeyframeProgress());
        } else {
            a2 = getValue(currentKeyframe, linearCurrentKeyframeProgress, interpolator.getInterpolation(linearCurrentKeyframeProgress), currentKeyframe.yInterpolator.getInterpolation(linearCurrentKeyframeProgress));
        }
        this.cachedGetValue = a2;
        return a2;
    }

    public abstract A getValue(Keyframe<K> keyframe, float f2);

    public void notifyListeners() {
        L.beginSection("BaseKeyframeAnimation#notifyListeners");
        for (int i3 = 0; i3 < this.listeners.size(); i3++) {
            this.listeners.get(i3).onValueChanged();
        }
        L.endSection("BaseKeyframeAnimation#notifyListeners");
    }

    public void setIsDiscrete() {
        this.isDiscrete = true;
    }

    public void setProgress(@FloatRange(from = 0.0d, to = 1.0d) float f2) {
        L.beginSection("BaseKeyframeAnimation#setProgress");
        if (this.keyframesWrapper.isEmpty()) {
            L.endSection("BaseKeyframeAnimation#setProgress");
            return;
        }
        if (f2 < getStartDelayProgress()) {
            f2 = getStartDelayProgress();
        } else if (f2 > getEndProgress()) {
            f2 = getEndProgress();
        }
        if (f2 == this.progress) {
            L.endSection("BaseKeyframeAnimation#setProgress");
            return;
        }
        this.progress = f2;
        if (this.keyframesWrapper.isValueChanged(f2)) {
            notifyListeners();
        }
        L.endSection("BaseKeyframeAnimation#setProgress");
    }

    public void setValueCallback(@Nullable LottieValueCallback<A> lottieValueCallback) {
        LottieValueCallback<A> lottieValueCallback2 = this.valueCallback;
        if (lottieValueCallback2 != null) {
            lottieValueCallback2.setAnimation((BaseKeyframeAnimation<?, ?>) null);
        }
        this.valueCallback = lottieValueCallback;
        if (lottieValueCallback != null) {
            lottieValueCallback.setAnimation(this);
        }
    }

    public A getValue(Keyframe<K> keyframe, float f2, float f3, float f4) {
        throw new UnsupportedOperationException("This animation does not support split dimensions!");
    }
}
