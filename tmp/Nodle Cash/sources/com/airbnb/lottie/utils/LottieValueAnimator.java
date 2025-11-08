package com.airbnb.lottie.utils;

import android.view.Choreographer;
import androidx.annotation.FloatRange;
import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieComposition;

public class LottieValueAnimator extends BaseLottieAnimator implements Choreographer.FrameCallback {
    @Nullable
    private LottieComposition composition;
    private float frame = 0.0f;
    private float frameRaw = 0.0f;
    private long lastFrameTimeNs = 0;
    private float maxFrame = 2.14748365E9f;
    private float minFrame = -2.14748365E9f;
    private int repeatCount = 0;
    @VisibleForTesting
    protected boolean running = false;
    private float speed = 1.0f;
    private boolean speedReversedForRepeatMode = false;
    private boolean useCompositionFrameRate = false;

    private float getFrameDurationNs() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            return Float.MAX_VALUE;
        }
        return (1.0E9f / lottieComposition.getFrameRate()) / Math.abs(this.speed);
    }

    private boolean isReversed() {
        return getSpeed() < 0.0f;
    }

    private void verifyFrame() {
        if (this.composition != null) {
            float f2 = this.frame;
            if (f2 < this.minFrame || f2 > this.maxFrame) {
                throw new IllegalStateException(String.format("Frame must be [%f,%f]. It is %f", new Object[]{Float.valueOf(this.minFrame), Float.valueOf(this.maxFrame), Float.valueOf(this.frame)}));
            }
        }
    }

    @MainThread
    public void cancel() {
        notifyCancel();
        removeFrameCallback();
    }

    public void clearComposition() {
        this.composition = null;
        this.minFrame = -2.14748365E9f;
        this.maxFrame = 2.14748365E9f;
    }

    public void doFrame(long j2) {
        postFrameCallback();
        if (this.composition != null && isRunning()) {
            L.beginSection("LottieValueAnimator#doFrame");
            long j3 = this.lastFrameTimeNs;
            long j4 = 0;
            if (j3 != 0) {
                j4 = j2 - j3;
            }
            float frameDurationNs = ((float) j4) / getFrameDurationNs();
            float f2 = this.frameRaw;
            if (isReversed()) {
                frameDurationNs = -frameDurationNs;
            }
            float f3 = f2 + frameDurationNs;
            boolean contains = MiscUtils.contains(f3, getMinFrame(), getMaxFrame());
            float f4 = this.frameRaw;
            float clamp = MiscUtils.clamp(f3, getMinFrame(), getMaxFrame());
            this.frameRaw = clamp;
            if (this.useCompositionFrameRate) {
                clamp = (float) Math.floor((double) clamp);
            }
            this.frame = clamp;
            this.lastFrameTimeNs = j2;
            if (!this.useCompositionFrameRate || this.frameRaw != f4) {
                notifyUpdate();
            }
            if (!contains) {
                if (getRepeatCount() == -1 || this.repeatCount < getRepeatCount()) {
                    notifyRepeat();
                    this.repeatCount++;
                    if (getRepeatMode() == 2) {
                        this.speedReversedForRepeatMode = !this.speedReversedForRepeatMode;
                        reverseAnimationSpeed();
                    } else {
                        float maxFrame2 = isReversed() ? getMaxFrame() : getMinFrame();
                        this.frameRaw = maxFrame2;
                        this.frame = maxFrame2;
                    }
                    this.lastFrameTimeNs = j2;
                } else {
                    float minFrame2 = this.speed < 0.0f ? getMinFrame() : getMaxFrame();
                    this.frameRaw = minFrame2;
                    this.frame = minFrame2;
                    removeFrameCallback();
                    notifyEnd(isReversed());
                }
            }
            verifyFrame();
            L.endSection("LottieValueAnimator#doFrame");
        }
    }

    @MainThread
    public void endAnimation() {
        removeFrameCallback();
        notifyEnd(isReversed());
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public float getAnimatedFraction() {
        float minFrame2;
        float maxFrame2;
        float minFrame3;
        if (this.composition == null) {
            return 0.0f;
        }
        if (isReversed()) {
            minFrame2 = getMaxFrame() - this.frame;
            maxFrame2 = getMaxFrame();
            minFrame3 = getMinFrame();
        } else {
            minFrame2 = this.frame - getMinFrame();
            maxFrame2 = getMaxFrame();
            minFrame3 = getMinFrame();
        }
        return minFrame2 / (maxFrame2 - minFrame3);
    }

    public Object getAnimatedValue() {
        return Float.valueOf(getAnimatedValueAbsolute());
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public float getAnimatedValueAbsolute() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            return 0.0f;
        }
        return (this.frame - lottieComposition.getStartFrame()) / (this.composition.getEndFrame() - this.composition.getStartFrame());
    }

    public long getDuration() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            return 0;
        }
        return (long) lottieComposition.getDuration();
    }

    public float getFrame() {
        return this.frame;
    }

    public float getMaxFrame() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            return 0.0f;
        }
        float f2 = this.maxFrame;
        return f2 == 2.14748365E9f ? lottieComposition.getEndFrame() : f2;
    }

    public float getMinFrame() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            return 0.0f;
        }
        float f2 = this.minFrame;
        return f2 == -2.14748365E9f ? lottieComposition.getStartFrame() : f2;
    }

    public float getSpeed() {
        return this.speed;
    }

    public boolean isRunning() {
        return this.running;
    }

    public void notifyCancel() {
        super.notifyCancel();
        notifyEnd(isReversed());
    }

    @MainThread
    public void pauseAnimation() {
        removeFrameCallback();
        notifyPause();
    }

    @MainThread
    public void playAnimation() {
        this.running = true;
        notifyStart(isReversed());
        setFrame((float) ((int) (isReversed() ? getMaxFrame() : getMinFrame())));
        this.lastFrameTimeNs = 0;
        this.repeatCount = 0;
        postFrameCallback();
    }

    public void postFrameCallback() {
        if (isRunning()) {
            removeFrameCallback(false);
            Choreographer.getInstance().postFrameCallback(this);
        }
    }

    @MainThread
    public void removeFrameCallback() {
        removeFrameCallback(true);
    }

    @MainThread
    public void resumeAnimation() {
        this.running = true;
        postFrameCallback();
        this.lastFrameTimeNs = 0;
        if (isReversed() && getFrame() == getMinFrame()) {
            setFrame(getMaxFrame());
        } else if (!isReversed() && getFrame() == getMaxFrame()) {
            setFrame(getMinFrame());
        }
        notifyResume();
    }

    public void reverseAnimationSpeed() {
        setSpeed(-getSpeed());
    }

    public void setComposition(LottieComposition lottieComposition) {
        boolean z2 = this.composition == null;
        this.composition = lottieComposition;
        if (z2) {
            setMinAndMaxFrames(Math.max(this.minFrame, lottieComposition.getStartFrame()), Math.min(this.maxFrame, lottieComposition.getEndFrame()));
        } else {
            setMinAndMaxFrames((float) ((int) lottieComposition.getStartFrame()), (float) ((int) lottieComposition.getEndFrame()));
        }
        float f2 = this.frame;
        this.frame = 0.0f;
        this.frameRaw = 0.0f;
        setFrame((float) ((int) f2));
        notifyUpdate();
    }

    public void setFrame(float f2) {
        if (this.frameRaw != f2) {
            float clamp = MiscUtils.clamp(f2, getMinFrame(), getMaxFrame());
            this.frameRaw = clamp;
            if (this.useCompositionFrameRate) {
                clamp = (float) Math.floor((double) clamp);
            }
            this.frame = clamp;
            this.lastFrameTimeNs = 0;
            notifyUpdate();
        }
    }

    public void setMaxFrame(float f2) {
        setMinAndMaxFrames(this.minFrame, f2);
    }

    public void setMinAndMaxFrames(float f2, float f3) {
        if (f2 <= f3) {
            LottieComposition lottieComposition = this.composition;
            float startFrame = lottieComposition == null ? -3.4028235E38f : lottieComposition.getStartFrame();
            LottieComposition lottieComposition2 = this.composition;
            float endFrame = lottieComposition2 == null ? Float.MAX_VALUE : lottieComposition2.getEndFrame();
            float clamp = MiscUtils.clamp(f2, startFrame, endFrame);
            float clamp2 = MiscUtils.clamp(f3, startFrame, endFrame);
            if (clamp != this.minFrame || clamp2 != this.maxFrame) {
                this.minFrame = clamp;
                this.maxFrame = clamp2;
                setFrame((float) ((int) MiscUtils.clamp(this.frame, clamp, clamp2)));
                return;
            }
            return;
        }
        throw new IllegalArgumentException("minFrame (" + f2 + ") must be <= maxFrame (" + f3 + ")");
    }

    public void setMinFrame(int i3) {
        setMinAndMaxFrames((float) i3, (float) ((int) this.maxFrame));
    }

    public void setRepeatMode(int i3) {
        super.setRepeatMode(i3);
        if (i3 != 2 && this.speedReversedForRepeatMode) {
            this.speedReversedForRepeatMode = false;
            reverseAnimationSpeed();
        }
    }

    public void setSpeed(float f2) {
        this.speed = f2;
    }

    public void setUseCompositionFrameRate(boolean z2) {
        this.useCompositionFrameRate = z2;
    }

    @MainThread
    public void removeFrameCallback(boolean z2) {
        Choreographer.getInstance().removeFrameCallback(this);
        if (z2) {
            this.running = false;
        }
    }
}
