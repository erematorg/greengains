package com.bumptech.glide.load.model;

import androidx.annotation.NonNull;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.signature.ObjectKey;

public class UnitModelLoader<Model> implements ModelLoader<Model, Model> {
    private static final UnitModelLoader<?> INSTANCE = new UnitModelLoader<>();

    public static class Factory<Model> implements ModelLoaderFactory<Model, Model> {
        private static final Factory<?> FACTORY = new Factory<>();

        public static <T> Factory<T> getInstance() {
            return FACTORY;
        }

        @NonNull
        public ModelLoader<Model, Model> build(MultiModelLoaderFactory multiModelLoaderFactory) {
            return UnitModelLoader.getInstance();
        }

        public void teardown() {
        }
    }

    public static class UnitFetcher<Model> implements DataFetcher<Model> {
        private final Model resource;

        public UnitFetcher(Model model) {
            this.resource = model;
        }

        public void cancel() {
        }

        public void cleanup() {
        }

        @NonNull
        public Class<Model> getDataClass() {
            return this.resource.getClass();
        }

        @NonNull
        public DataSource getDataSource() {
            return DataSource.LOCAL;
        }

        public void loadData(@NonNull Priority priority, @NonNull DataFetcher.DataCallback<? super Model> dataCallback) {
            dataCallback.onDataReady(this.resource);
        }
    }

    public static <T> UnitModelLoader<T> getInstance() {
        return INSTANCE;
    }

    public ModelLoader.LoadData<Model> buildLoadData(@NonNull Model model, int i3, int i4, @NonNull Options options) {
        return new ModelLoader.LoadData<>(new ObjectKey(model), new UnitFetcher(model));
    }

    public boolean handles(@NonNull Model model) {
        return true;
    }
}
