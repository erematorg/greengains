package com.appsamurai.storyly.exoplayer2.datasource.upstream;

import android.content.Context;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSource;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DefaultHttpDataSource;

@Deprecated
public final class DefaultDataSourceFactory implements DataSource.Factory {
    private final DataSource.Factory baseDataSourceFactory;
    private final Context context;
    @Nullable
    private final TransferListener listener;

    public DefaultDataSourceFactory(Context context2) {
        this(context2, (String) null, (TransferListener) null);
    }

    public DefaultDataSourceFactory(Context context2, @Nullable String str) {
        this(context2, str, (TransferListener) null);
    }

    public DefaultDataSource createDataSource() {
        DefaultDataSource defaultDataSource = new DefaultDataSource(this.context, this.baseDataSourceFactory.createDataSource());
        TransferListener transferListener = this.listener;
        if (transferListener != null) {
            defaultDataSource.addTransferListener(transferListener);
        }
        return defaultDataSource;
    }

    public DefaultDataSourceFactory(Context context2, @Nullable String str, @Nullable TransferListener transferListener) {
        this(context2, transferListener, (DataSource.Factory) new DefaultHttpDataSource.Factory().setUserAgent(str));
    }

    public DefaultDataSourceFactory(Context context2, DataSource.Factory factory) {
        this(context2, (TransferListener) null, factory);
    }

    public DefaultDataSourceFactory(Context context2, @Nullable TransferListener transferListener, DataSource.Factory factory) {
        this.context = context2.getApplicationContext();
        this.listener = transferListener;
        this.baseDataSourceFactory = factory;
    }
}
