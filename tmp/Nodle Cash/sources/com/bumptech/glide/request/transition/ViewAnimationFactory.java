package com.bumptech.glide.request.transition;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.request.transition.ViewTransition;

public class ViewAnimationFactory<R> implements TransitionFactory<R> {
    private Transition<R> transition;
    private final ViewTransition.ViewTransitionAnimationFactory viewTransitionAnimationFactory;

    public static class ConcreteViewTransitionAnimationFactory implements ViewTransition.ViewTransitionAnimationFactory {
        private final Animation animation;

        public ConcreteViewTransitionAnimationFactory(Animation animation2) {
            this.animation = animation2;
        }

        public Animation build(Context context) {
            return this.animation;
        }
    }

    public static class ResourceViewTransitionAnimationFactory implements ViewTransition.ViewTransitionAnimationFactory {
        private final int animationId;

        public ResourceViewTransitionAnimationFactory(int i3) {
            this.animationId = i3;
        }

        public Animation build(Context context) {
            return AnimationUtils.loadAnimation(context, this.animationId);
        }
    }

    public ViewAnimationFactory(Animation animation) {
        this((ViewTransition.ViewTransitionAnimationFactory) new ConcreteViewTransitionAnimationFactory(animation));
    }

    public Transition<R> build(DataSource dataSource, boolean z2) {
        if (dataSource == DataSource.MEMORY_CACHE || !z2) {
            return NoTransition.get();
        }
        if (this.transition == null) {
            this.transition = new ViewTransition(this.viewTransitionAnimationFactory);
        }
        return this.transition;
    }

    public ViewAnimationFactory(int i3) {
        this((ViewTransition.ViewTransitionAnimationFactory) new ResourceViewTransitionAnimationFactory(i3));
    }

    public ViewAnimationFactory(ViewTransition.ViewTransitionAnimationFactory viewTransitionAnimationFactory2) {
        this.viewTransitionAnimationFactory = viewTransitionAnimationFactory2;
    }
}
