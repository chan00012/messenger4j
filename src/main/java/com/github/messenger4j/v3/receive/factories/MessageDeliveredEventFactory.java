package com.github.messenger4j.v3.receive.factories;

import static com.github.messenger4j.internal.JsonHelper.Constants.PROP_DELIVERY;
import static com.github.messenger4j.internal.JsonHelper.Constants.PROP_ID;
import static com.github.messenger4j.internal.JsonHelper.Constants.PROP_MIDS;
import static com.github.messenger4j.internal.JsonHelper.Constants.PROP_RECIPIENT;
import static com.github.messenger4j.internal.JsonHelper.Constants.PROP_SENDER;
import static com.github.messenger4j.internal.JsonHelper.Constants.PROP_TIMESTAMP;
import static com.github.messenger4j.internal.JsonHelper.Constants.PROP_WATERMARK;
import static com.github.messenger4j.internal.JsonHelper.getPropertyAsInstant;
import static com.github.messenger4j.internal.JsonHelper.getPropertyAsJsonArray;
import static com.github.messenger4j.internal.JsonHelper.getPropertyAsString;
import static com.github.messenger4j.internal.JsonHelper.hasProperty;

import com.github.messenger4j.v3.receive.MessageDeliveredEvent;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;

/**
 * @author Max Grabenhorst
 * @since 1.0.0
 */
public final class MessageDeliveredEventFactory implements BaseEventFactory<MessageDeliveredEvent> {

    @Override
    public boolean isResponsible(@NonNull JsonObject messagingEvent) {
        return hasProperty(messagingEvent, PROP_DELIVERY);
    }

    @Override
    public MessageDeliveredEvent createEventFromJson(@NonNull JsonObject messagingEvent) {
        final String senderId = getPropertyAsString(messagingEvent, PROP_SENDER, PROP_ID)
                .orElseThrow(IllegalArgumentException::new);
        final String recipientId = getPropertyAsString(messagingEvent, PROP_RECIPIENT, PROP_ID)
                .orElseThrow(IllegalArgumentException::new);
        final Instant timestamp = getPropertyAsInstant(messagingEvent, PROP_TIMESTAMP).orElse(Instant.now());
        final Instant watermark = getPropertyAsInstant(messagingEvent, PROP_DELIVERY, PROP_WATERMARK)
                .orElseThrow(IllegalArgumentException::new);
        final Optional<List<String>> messageIds = getPropertyAsJsonArray(messagingEvent, PROP_DELIVERY, PROP_MIDS)
                .map(jsonArray -> {
                    final List<String> messageIdList = new ArrayList<>(jsonArray.size());
                    for (JsonElement messageIdJsonElement : jsonArray) {
                        messageIdList.add(messageIdJsonElement.getAsString());
                    }
                    return messageIdList;
                });

        return new MessageDeliveredEvent(senderId, recipientId, timestamp, watermark, messageIds);
    }
}
