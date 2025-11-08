package com.google.firebase.messaging;

import androidx.core.app.NotificationCompat;
import com.google.android.datatransport.runtime.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.config.Configurator;
import com.google.firebase.encoders.config.EncoderConfig;
import com.google.firebase.messaging.reporting.MessagingClientEvent;
import com.google.firebase.messaging.reporting.MessagingClientEventExtension;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.reown.android.push.notifications.PushMessagingService;
import java.io.IOException;

public final class AutoProtoEncoderDoNotUseEncoder implements Configurator {
    public static final int CODEGEN_VERSION = 2;
    public static final Configurator CONFIG = new AutoProtoEncoderDoNotUseEncoder();

    public static final class MessagingClientEventEncoder implements ObjectEncoder<MessagingClientEvent> {
        private static final FieldDescriptor ANALYTICSLABEL_DESCRIPTOR = a.a(13, FieldDescriptor.builder("analyticsLabel"));
        private static final FieldDescriptor BULKID_DESCRIPTOR = a.a(11, FieldDescriptor.builder("bulkId"));
        private static final FieldDescriptor CAMPAIGNID_DESCRIPTOR = a.a(14, FieldDescriptor.builder("campaignId"));
        private static final FieldDescriptor COLLAPSEKEY_DESCRIPTOR = a.a(7, FieldDescriptor.builder("collapseKey"));
        private static final FieldDescriptor COMPOSERLABEL_DESCRIPTOR = a.a(15, FieldDescriptor.builder("composerLabel"));
        private static final FieldDescriptor EVENT_DESCRIPTOR = a.a(12, FieldDescriptor.builder(NotificationCompat.CATEGORY_EVENT));
        static final MessagingClientEventEncoder INSTANCE = new MessagingClientEventEncoder();
        private static final FieldDescriptor INSTANCEID_DESCRIPTOR = a.a(3, FieldDescriptor.builder("instanceId"));
        private static final FieldDescriptor MESSAGEID_DESCRIPTOR = a.a(2, FieldDescriptor.builder("messageId"));
        private static final FieldDescriptor MESSAGETYPE_DESCRIPTOR = a.a(4, FieldDescriptor.builder("messageType"));
        private static final FieldDescriptor PACKAGENAME_DESCRIPTOR = a.a(6, FieldDescriptor.builder(RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME));
        private static final FieldDescriptor PRIORITY_DESCRIPTOR = a.a(8, FieldDescriptor.builder("priority"));
        private static final FieldDescriptor PROJECTNUMBER_DESCRIPTOR = a.a(1, FieldDescriptor.builder("projectNumber"));
        private static final FieldDescriptor SDKPLATFORM_DESCRIPTOR = a.a(5, FieldDescriptor.builder("sdkPlatform"));
        private static final FieldDescriptor TOPIC_DESCRIPTOR = a.a(10, FieldDescriptor.builder(PushMessagingService.KEY_TOPIC));
        private static final FieldDescriptor TTL_DESCRIPTOR = a.a(9, FieldDescriptor.builder("ttl"));

        private MessagingClientEventEncoder() {
        }

        public void encode(MessagingClientEvent messagingClientEvent, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(PROJECTNUMBER_DESCRIPTOR, messagingClientEvent.getProjectNumber());
            objectEncoderContext.add(MESSAGEID_DESCRIPTOR, (Object) messagingClientEvent.getMessageId());
            objectEncoderContext.add(INSTANCEID_DESCRIPTOR, (Object) messagingClientEvent.getInstanceId());
            objectEncoderContext.add(MESSAGETYPE_DESCRIPTOR, (Object) messagingClientEvent.getMessageType());
            objectEncoderContext.add(SDKPLATFORM_DESCRIPTOR, (Object) messagingClientEvent.getSdkPlatform());
            objectEncoderContext.add(PACKAGENAME_DESCRIPTOR, (Object) messagingClientEvent.getPackageName());
            objectEncoderContext.add(COLLAPSEKEY_DESCRIPTOR, (Object) messagingClientEvent.getCollapseKey());
            objectEncoderContext.add(PRIORITY_DESCRIPTOR, messagingClientEvent.getPriority());
            objectEncoderContext.add(TTL_DESCRIPTOR, messagingClientEvent.getTtl());
            objectEncoderContext.add(TOPIC_DESCRIPTOR, (Object) messagingClientEvent.getTopic());
            objectEncoderContext.add(BULKID_DESCRIPTOR, messagingClientEvent.getBulkId());
            objectEncoderContext.add(EVENT_DESCRIPTOR, (Object) messagingClientEvent.getEvent());
            objectEncoderContext.add(ANALYTICSLABEL_DESCRIPTOR, (Object) messagingClientEvent.getAnalyticsLabel());
            objectEncoderContext.add(CAMPAIGNID_DESCRIPTOR, messagingClientEvent.getCampaignId());
            objectEncoderContext.add(COMPOSERLABEL_DESCRIPTOR, (Object) messagingClientEvent.getComposerLabel());
        }
    }

    public static final class MessagingClientEventExtensionEncoder implements ObjectEncoder<MessagingClientEventExtension> {
        static final MessagingClientEventExtensionEncoder INSTANCE = new MessagingClientEventExtensionEncoder();
        private static final FieldDescriptor MESSAGINGCLIENTEVENT_DESCRIPTOR = a.a(1, FieldDescriptor.builder("messagingClientEvent"));

        private MessagingClientEventExtensionEncoder() {
        }

        public void encode(MessagingClientEventExtension messagingClientEventExtension, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(MESSAGINGCLIENTEVENT_DESCRIPTOR, (Object) messagingClientEventExtension.getMessagingClientEventInternal());
        }
    }

    public static final class ProtoEncoderDoNotUseEncoder implements ObjectEncoder<ProtoEncoderDoNotUse> {
        static final ProtoEncoderDoNotUseEncoder INSTANCE = new ProtoEncoderDoNotUseEncoder();
        private static final FieldDescriptor MESSAGINGCLIENTEVENTEXTENSION_DESCRIPTOR = FieldDescriptor.of("messagingClientEventExtension");

        private ProtoEncoderDoNotUseEncoder() {
        }

        public void encode(ProtoEncoderDoNotUse protoEncoderDoNotUse, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(MESSAGINGCLIENTEVENTEXTENSION_DESCRIPTOR, (Object) protoEncoderDoNotUse.getMessagingClientEventExtension());
        }
    }

    private AutoProtoEncoderDoNotUseEncoder() {
    }

    public void configure(EncoderConfig<?> encoderConfig) {
        encoderConfig.registerEncoder((Class<U>) ProtoEncoderDoNotUse.class, (ObjectEncoder<? super U>) ProtoEncoderDoNotUseEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) MessagingClientEventExtension.class, (ObjectEncoder<? super U>) MessagingClientEventExtensionEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) MessagingClientEvent.class, (ObjectEncoder<? super U>) MessagingClientEventEncoder.INSTANCE);
    }
}
