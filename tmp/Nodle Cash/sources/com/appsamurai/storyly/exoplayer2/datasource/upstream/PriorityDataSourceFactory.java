package com.appsamurai.storyly.exoplayer2.datasource.upstream;

import com.appsamurai.storyly.exoplayer2.common.util.PriorityTaskManager;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSource;

@Deprecated
public final class PriorityDataSourceFactory implements DataSource.Factory {
    private final int priority;
    private final PriorityTaskManager priorityTaskManager;
    private final DataSource.Factory upstreamFactory;

    public PriorityDataSourceFactory(DataSource.Factory factory, PriorityTaskManager priorityTaskManager2, int i3) {
        this.upstreamFactory = factory;
        this.priorityTaskManager = priorityTaskManager2;
        this.priority = i3;
    }

    public PriorityDataSource createDataSource() {
        return new PriorityDataSource(this.upstreamFactory.createDataSource(), this.priorityTaskManager, this.priority);
    }
}
