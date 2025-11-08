package com.bumptech.glide;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import androidx.annotation.Nullable;
import androidx.tracing.Trace;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.data.DataRewinder;
import com.bumptech.glide.load.data.InputStreamRewinder;
import com.bumptech.glide.load.data.ParcelFileDescriptorRewinder;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.model.AssetUriLoader;
import com.bumptech.glide.load.model.ByteArrayLoader;
import com.bumptech.glide.load.model.ByteBufferEncoder;
import com.bumptech.glide.load.model.ByteBufferFileLoader;
import com.bumptech.glide.load.model.DataUrlLoader;
import com.bumptech.glide.load.model.DirectResourceLoader;
import com.bumptech.glide.load.model.FileLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.MediaStoreFileLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.ResourceLoader;
import com.bumptech.glide.load.model.ResourceUriLoader;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.model.StringLoader;
import com.bumptech.glide.load.model.UnitModelLoader;
import com.bumptech.glide.load.model.UriLoader;
import com.bumptech.glide.load.model.UrlUriLoader;
import com.bumptech.glide.load.model.stream.HttpGlideUrlLoader;
import com.bumptech.glide.load.model.stream.MediaStoreImageThumbLoader;
import com.bumptech.glide.load.model.stream.MediaStoreVideoThumbLoader;
import com.bumptech.glide.load.model.stream.QMediaStoreUriLoader;
import com.bumptech.glide.load.model.stream.UrlLoader;
import com.bumptech.glide.load.resource.bitmap.BitmapDrawableDecoder;
import com.bumptech.glide.load.resource.bitmap.BitmapDrawableEncoder;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;
import com.bumptech.glide.load.resource.bitmap.ByteBufferBitmapDecoder;
import com.bumptech.glide.load.resource.bitmap.ByteBufferBitmapImageDecoderResourceDecoder;
import com.bumptech.glide.load.resource.bitmap.DefaultImageHeaderParser;
import com.bumptech.glide.load.resource.bitmap.Downsampler;
import com.bumptech.glide.load.resource.bitmap.ExifInterfaceImageHeaderParser;
import com.bumptech.glide.load.resource.bitmap.InputStreamBitmapImageDecoderResourceDecoder;
import com.bumptech.glide.load.resource.bitmap.ParcelFileDescriptorBitmapDecoder;
import com.bumptech.glide.load.resource.bitmap.ResourceBitmapDecoder;
import com.bumptech.glide.load.resource.bitmap.StreamBitmapDecoder;
import com.bumptech.glide.load.resource.bitmap.UnitBitmapDecoder;
import com.bumptech.glide.load.resource.bitmap.VideoDecoder;
import com.bumptech.glide.load.resource.bytes.ByteBufferRewinder;
import com.bumptech.glide.load.resource.drawable.AnimatedImageDecoder;
import com.bumptech.glide.load.resource.drawable.ResourceDrawableDecoder;
import com.bumptech.glide.load.resource.drawable.UnitDrawableDecoder;
import com.bumptech.glide.load.resource.file.FileDecoder;
import com.bumptech.glide.load.resource.gif.ByteBufferGifDecoder;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawableEncoder;
import com.bumptech.glide.load.resource.gif.GifFrameResourceDecoder;
import com.bumptech.glide.load.resource.gif.StreamGifDecoder;
import com.bumptech.glide.load.resource.transcode.BitmapBytesTranscoder;
import com.bumptech.glide.load.resource.transcode.BitmapDrawableTranscoder;
import com.bumptech.glide.load.resource.transcode.DrawableBytesTranscoder;
import com.bumptech.glide.load.resource.transcode.GifDrawableBytesTranscoder;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.module.GlideModule;
import com.bumptech.glide.util.GlideSuppliers;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.List;

final class RegistryFactory {
    private RegistryFactory() {
    }

    public static Registry createAndInitRegistry(Glide glide, List<GlideModule> list, @Nullable AppGlideModule appGlideModule) {
        BitmapPool bitmapPool = glide.getBitmapPool();
        ArrayPool arrayPool = glide.getArrayPool();
        Context applicationContext = glide.getGlideContext().getApplicationContext();
        GlideExperiments experiments = glide.getGlideContext().getExperiments();
        Registry registry = new Registry();
        initializeDefaults(applicationContext, registry, bitmapPool, arrayPool, experiments);
        initializeModules(applicationContext, glide, registry, list, appGlideModule);
        return registry;
    }

    private static void initializeDefaults(Context context, Registry registry, BitmapPool bitmapPool, ArrayPool arrayPool, GlideExperiments glideExperiments) {
        ResourceDecoder resourceDecoder;
        ResourceDecoder resourceDecoder2;
        Class<BitmapDrawable> cls;
        Registry registry2;
        Context context2 = context;
        Registry registry3 = registry;
        BitmapPool bitmapPool2 = bitmapPool;
        ArrayPool arrayPool2 = arrayPool;
        registry3.register((ImageHeaderParser) new DefaultImageHeaderParser());
        registry3.register((ImageHeaderParser) new ExifInterfaceImageHeaderParser());
        Resources resources = context.getResources();
        List<ImageHeaderParser> imageHeaderParsers = registry.getImageHeaderParsers();
        ByteBufferGifDecoder byteBufferGifDecoder = new ByteBufferGifDecoder(context2, imageHeaderParsers, bitmapPool2, arrayPool2);
        ResourceDecoder<ParcelFileDescriptor, Bitmap> parcel = VideoDecoder.parcel(bitmapPool);
        Downsampler downsampler = new Downsampler(registry.getImageHeaderParsers(), resources.getDisplayMetrics(), bitmapPool2, arrayPool2);
        if (glideExperiments.isEnabled(GlideBuilder.EnableImageDecoderForBitmaps.class)) {
            resourceDecoder2 = new InputStreamBitmapImageDecoderResourceDecoder();
            resourceDecoder = new ByteBufferBitmapImageDecoderResourceDecoder();
        } else {
            resourceDecoder = new ByteBufferBitmapDecoder(downsampler);
            resourceDecoder2 = new StreamBitmapDecoder(downsampler, arrayPool2);
        }
        Class<InputStream> cls2 = InputStream.class;
        Class<Drawable> cls3 = Drawable.class;
        registry3.append("Animation", cls2, cls3, AnimatedImageDecoder.streamDecoder(imageHeaderParsers, arrayPool2));
        Class<ByteBuffer> cls4 = ByteBuffer.class;
        registry3.append("Animation", cls4, cls3, AnimatedImageDecoder.byteBufferDecoder(imageHeaderParsers, arrayPool2));
        ResourceDrawableDecoder resourceDrawableDecoder = new ResourceDrawableDecoder(context2);
        BitmapEncoder bitmapEncoder = new BitmapEncoder(arrayPool2);
        ResourceDrawableDecoder resourceDrawableDecoder2 = resourceDrawableDecoder;
        BitmapBytesTranscoder bitmapBytesTranscoder = new BitmapBytesTranscoder();
        GifDrawableBytesTranscoder gifDrawableBytesTranscoder = new GifDrawableBytesTranscoder();
        ContentResolver contentResolver = context.getContentResolver();
        Class<Drawable> cls5 = cls3;
        String str = "Animation";
        Class<Bitmap> cls6 = Bitmap.class;
        registry3.append(cls4, new ByteBufferEncoder()).append(cls2, new StreamEncoder(arrayPool2)).append(Registry.BUCKET_BITMAP, cls4, cls6, resourceDecoder).append(Registry.BUCKET_BITMAP, cls2, cls6, resourceDecoder2);
        Class<ParcelFileDescriptor> cls7 = ParcelFileDescriptor.class;
        if (ParcelFileDescriptorRewinder.isSupported()) {
            registry3.append(Registry.BUCKET_BITMAP, cls7, cls6, new ParcelFileDescriptorBitmapDecoder(downsampler));
        }
        Class<AssetFileDescriptor> cls8 = AssetFileDescriptor.class;
        registry3.append(Registry.BUCKET_BITMAP, cls8, cls6, VideoDecoder.asset(bitmapPool));
        Class<AssetFileDescriptor> cls9 = cls8;
        Class<BitmapDrawable> cls10 = BitmapDrawable.class;
        Registry append = registry3.append(Registry.BUCKET_BITMAP, cls7, cls6, parcel).append(cls6, cls6, UnitModelLoader.Factory.getInstance()).append(Registry.BUCKET_BITMAP, cls6, cls6, new UnitBitmapDecoder()).append(cls6, bitmapEncoder).append(Registry.BUCKET_BITMAP_DRAWABLE, cls4, cls10, new BitmapDrawableDecoder(resources, resourceDecoder)).append(Registry.BUCKET_BITMAP_DRAWABLE, cls2, cls10, new BitmapDrawableDecoder(resources, resourceDecoder2)).append(Registry.BUCKET_BITMAP_DRAWABLE, cls7, cls10, new BitmapDrawableDecoder(resources, parcel)).append(cls10, new BitmapDrawableEncoder(bitmapPool2, bitmapEncoder));
        Class<ParcelFileDescriptor> cls11 = cls7;
        ArrayPool arrayPool3 = arrayPool;
        StreamGifDecoder streamGifDecoder = new StreamGifDecoder(imageHeaderParsers, byteBufferGifDecoder, arrayPool3);
        Class<GifDrawable> cls12 = GifDrawable.class;
        String str2 = str;
        Class<GifDecoder> cls13 = GifDecoder.class;
        Registry append2 = append.append(str2, cls2, cls12, streamGifDecoder).append(str2, cls4, cls12, byteBufferGifDecoder).append(cls12, new GifDrawableEncoder()).append(cls13, cls13, UnitModelLoader.Factory.getInstance()).append(Registry.BUCKET_BITMAP, cls13, cls6, new GifFrameResourceDecoder(bitmapPool2));
        Class<Uri> cls14 = Uri.class;
        ResourceDrawableDecoder resourceDrawableDecoder3 = resourceDrawableDecoder2;
        Class<Drawable> cls15 = cls5;
        Class<File> cls16 = File.class;
        append2.append(cls14, cls15, resourceDrawableDecoder3).append(cls14, cls6, new ResourceBitmapDecoder(resourceDrawableDecoder3, bitmapPool2)).register((DataRewinder.Factory<?>) new ByteBufferRewinder.Factory()).append(cls16, cls4, new ByteBufferFileLoader.Factory()).append(cls16, cls2, new FileLoader.StreamFactory()).append(cls16, cls16, new FileDecoder()).append(cls16, cls11, new FileLoader.FileDescriptorFactory()).append(cls16, cls16, UnitModelLoader.Factory.getInstance()).register((DataRewinder.Factory<?>) new InputStreamRewinder.Factory(arrayPool3));
        if (ParcelFileDescriptorRewinder.isSupported()) {
            cls = cls10;
            registry2 = registry;
            registry2.register((DataRewinder.Factory<?>) new ParcelFileDescriptorRewinder.Factory());
        } else {
            cls = cls10;
            registry2 = registry;
        }
        ModelLoaderFactory<Integer, InputStream> inputStreamFactory = DirectResourceLoader.inputStreamFactory(context);
        ModelLoaderFactory<Integer, AssetFileDescriptor> assetFileDescriptorFactory = DirectResourceLoader.assetFileDescriptorFactory(context);
        ModelLoaderFactory<Integer, Drawable> drawableFactory = DirectResourceLoader.drawableFactory(context);
        Class cls17 = Integer.TYPE;
        Class<GifDrawable> cls18 = cls12;
        Class<Integer> cls19 = Integer.class;
        Class<AssetFileDescriptor> cls20 = cls9;
        registry2.append(cls17, cls2, inputStreamFactory).append(cls19, cls2, inputStreamFactory).append(cls17, cls20, assetFileDescriptorFactory).append(cls19, cls20, assetFileDescriptorFactory).append(cls17, cls15, drawableFactory).append(cls19, cls15, drawableFactory).append(cls14, cls2, ResourceUriLoader.newStreamFactory(context)).append(cls14, cls20, ResourceUriLoader.newAssetFileDescriptorFactory(context));
        ResourceLoader.UriFactory uriFactory = new ResourceLoader.UriFactory(resources);
        ResourceLoader.AssetFileDescriptorFactory assetFileDescriptorFactory2 = new ResourceLoader.AssetFileDescriptorFactory(resources);
        ResourceLoader.StreamFactory streamFactory = new ResourceLoader.StreamFactory(resources);
        Class<BitmapDrawable> cls21 = cls;
        registry2.append(cls19, cls14, uriFactory).append(cls17, cls14, uriFactory).append(cls19, cls20, assetFileDescriptorFactory2).append(cls17, cls20, assetFileDescriptorFactory2).append(cls19, cls2, streamFactory).append(cls17, cls2, streamFactory);
        Class<String> cls22 = String.class;
        Context context3 = context;
        registry2.append(cls22, cls2, new DataUrlLoader.StreamFactory()).append(cls14, cls2, new DataUrlLoader.StreamFactory()).append(cls22, cls2, new StringLoader.StreamFactory()).append(cls22, cls11, new StringLoader.FileDescriptorFactory()).append(cls22, cls20, new StringLoader.AssetFileDescriptorFactory()).append(cls14, cls2, new AssetUriLoader.StreamFactory(context.getAssets())).append(cls14, cls20, new AssetUriLoader.FileDescriptorFactory(context.getAssets())).append(cls14, cls2, new MediaStoreImageThumbLoader.Factory(context3)).append(cls14, cls2, new MediaStoreVideoThumbLoader.Factory(context3));
        registry2.append(cls14, cls2, new QMediaStoreUriLoader.InputStreamFactory(context3));
        registry2.append(cls14, cls11, new QMediaStoreUriLoader.FileDescriptorFactory(context3));
        ContentResolver contentResolver2 = contentResolver;
        Class<GlideUrl> cls23 = GlideUrl.class;
        Class<byte[]> cls24 = byte[].class;
        Class<BitmapDrawable> cls25 = cls21;
        Registry register = registry2.append(cls14, cls2, new UriLoader.StreamFactory(contentResolver2)).append(cls14, cls11, new UriLoader.FileDescriptorFactory(contentResolver2)).append(cls14, cls20, new UriLoader.AssetFileDescriptorFactory(contentResolver2)).append(cls14, cls2, new UrlUriLoader.StreamFactory()).append(URL.class, cls2, new UrlLoader.StreamFactory()).append(cls14, cls16, new MediaStoreFileLoader.Factory(context3)).append(cls23, cls2, new HttpGlideUrlLoader.Factory()).append(cls24, cls4, new ByteArrayLoader.ByteBufferFactory()).append(cls24, cls2, new ByteArrayLoader.StreamFactory()).append(cls14, cls14, UnitModelLoader.Factory.getInstance()).append(cls15, cls15, UnitModelLoader.Factory.getInstance()).append(cls15, cls15, new UnitDrawableDecoder()).register(cls6, cls25, new BitmapDrawableTranscoder(resources));
        BitmapBytesTranscoder bitmapBytesTranscoder2 = bitmapBytesTranscoder;
        GifDrawableBytesTranscoder gifDrawableBytesTranscoder2 = gifDrawableBytesTranscoder;
        register.register(cls6, cls24, bitmapBytesTranscoder2).register(cls15, cls24, new DrawableBytesTranscoder(bitmapPool, bitmapBytesTranscoder2, gifDrawableBytesTranscoder2)).register(cls18, cls24, gifDrawableBytesTranscoder2);
        ResourceDecoder<ByteBuffer, Bitmap> byteBuffer = VideoDecoder.byteBuffer(bitmapPool);
        registry2.append(cls4, cls6, byteBuffer);
        registry2.append(cls4, cls25, new BitmapDrawableDecoder(resources, byteBuffer));
    }

    private static void initializeModules(Context context, Glide glide, Registry registry, List<GlideModule> list, @Nullable AppGlideModule appGlideModule) {
        for (GlideModule next : list) {
            try {
                next.registerComponents(context, glide, registry);
            } catch (AbstractMethodError e3) {
                throw new IllegalStateException("Attempting to register a Glide v3 module. If you see this, you or one of your dependencies may be including Glide v3 even though you're using Glide v4. You'll need to find and remove (or update) the offending dependency. The v3 module name is: ".concat(next.getClass().getName()), e3);
            }
        }
        if (appGlideModule != null) {
            appGlideModule.registerComponents(context, glide, registry);
        }
    }

    public static GlideSuppliers.GlideSupplier<Registry> lazilyCreateAndInitializeRegistry(final Glide glide, final List<GlideModule> list, @Nullable final AppGlideModule appGlideModule) {
        return new GlideSuppliers.GlideSupplier<Registry>() {
            private boolean isInitializing;

            public Registry get() {
                if (!this.isInitializing) {
                    Trace.beginSection("Glide registry");
                    this.isInitializing = true;
                    try {
                        return RegistryFactory.createAndInitRegistry(Glide.this, list, appGlideModule);
                    } finally {
                        this.isInitializing = false;
                        Trace.endSection();
                    }
                } else {
                    throw new IllegalStateException("Recursive Registry initialization! In your AppGlideModule and LibraryGlideModules, Make sure you're using the provided Registry rather calling glide.getRegistry()!");
                }
            }
        };
    }
}
