package com.google.firebase.remoteconfig.interop.rollouts;

import androidx.annotation.NonNull;
import com.google.auto.value.AutoValue;
import java.util.Set;

@AutoValue
public abstract class RolloutsState {
    @NonNull
    public static RolloutsState create(@NonNull Set<RolloutAssignment> set) {
        return new AutoValue_RolloutsState(set);
    }

    @NonNull
    public abstract Set<RolloutAssignment> getRolloutAssignments();
}
