package com.airbnb.lottie.model.animatable;

import com.airbnb.lottie.value.Keyframe;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

abstract class BaseAnimatableValue<V, O> implements AnimatableValue<V, O> {
    final List<Keyframe<V>> keyframes;

    public BaseAnimatableValue(V v2) {
        this(Collections.singletonList(new Keyframe(v2)));
    }

    public List<Keyframe<V>> getKeyframes() {
        return this.keyframes;
    }

    public boolean isStatic() {
        if (!this.keyframes.isEmpty()) {
            return this.keyframes.size() == 1 && this.keyframes.get(0).isStatic();
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!this.keyframes.isEmpty()) {
            sb.append("values=");
            sb.append(Arrays.toString(this.keyframes.toArray()));
        }
        return sb.toString();
    }

    public BaseAnimatableValue(List<Keyframe<V>> list) {
        this.keyframes = list;
    }
}
