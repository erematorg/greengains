package com.airbnb.lottie.model.layer;

import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;

public final /* synthetic */ class a implements BaseKeyframeAnimation.AnimationListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ BaseLayer f3380a;

    public /* synthetic */ a(BaseLayer baseLayer) {
        this.f3380a = baseLayer;
    }

    public final void onValueChanged() {
        this.f3380a.lambda$setupInOutAnimations$0();
    }
}
