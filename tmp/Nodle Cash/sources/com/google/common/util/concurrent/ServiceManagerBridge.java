package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.util.concurrent.Service;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
@J2ktIncompatible
interface ServiceManagerBridge {
    ImmutableMultimap<Service.State, Service> servicesByState();
}
