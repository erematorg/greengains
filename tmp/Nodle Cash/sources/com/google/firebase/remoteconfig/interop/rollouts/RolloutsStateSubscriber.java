package com.google.firebase.remoteconfig.interop.rollouts;

import androidx.annotation.NonNull;

public interface RolloutsStateSubscriber {
    void onRolloutsStateChanged(@NonNull RolloutsState rolloutsState);
}
