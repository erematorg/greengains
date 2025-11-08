package com.google.android.gms.maps.model;

import androidx.annotation.NonNull;
import com.google.android.gms.maps.model.StampStyle;

public class TextureStyle extends StampStyle {

    public static final class Builder extends StampStyle.Builder<Builder> {
        private Builder() {
            throw null;
        }

        @NonNull
        public TextureStyle build() {
            return new TextureStyle(this.zza, (zzag) null);
        }

        public /* synthetic */ Builder(zzaf zzaf) {
        }

        @NonNull
        public Builder self() {
            return this;
        }
    }

    public /* synthetic */ TextureStyle(BitmapDescriptor bitmapDescriptor, zzag zzag) {
        super(bitmapDescriptor);
    }

    @NonNull
    public static Builder newBuilder(@NonNull BitmapDescriptor bitmapDescriptor) {
        return (Builder) new Builder((zzaf) null).stamp(bitmapDescriptor);
    }
}
