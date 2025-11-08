package android.support.v4.media.session;

import android.media.session.MediaSession;
import androidx.annotation.RequiresApi;

@RequiresApi(22)
class MediaSessionCompatApi22 {
    private MediaSessionCompatApi22() {
    }

    public static void setRatingType(Object obj, int i3) {
        ((MediaSession) obj).setRatingType(i3);
    }
}
