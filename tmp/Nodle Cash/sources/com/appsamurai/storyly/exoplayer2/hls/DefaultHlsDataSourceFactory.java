package com.appsamurai.storyly.exoplayer2.hls;

import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSource;

public final class DefaultHlsDataSourceFactory implements HlsDataSourceFactory {
    private final DataSource.Factory dataSourceFactory;

    public DefaultHlsDataSourceFactory(DataSource.Factory factory) {
        this.dataSourceFactory = factory;
    }

    public DataSource createDataSource(int i3) {
        return this.dataSourceFactory.createDataSource();
    }
}
