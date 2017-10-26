package com.github.messenger4j.send;

import static com.github.messenger4j.internal.JsonHelper.Constants.PROP_ATTACHMENT_ID;
import static com.github.messenger4j.internal.JsonHelper.Constants.PROP_MESSAGE_ID;
import static com.github.messenger4j.internal.JsonHelper.Constants.PROP_RECIPIENT_ID;
import static com.github.messenger4j.internal.JsonHelper.getPropertyAsString;

import com.google.gson.JsonObject;
import java.util.Optional;

/**
 * @author Max Grabenhorst
 * @since 1.0.0
 */
public final class MessageResponseFactory {

    private MessageResponseFactory() {
    }

    public static MessageResponse create(JsonObject jsonObject) {
        final String recipientId = getPropertyAsString(jsonObject, PROP_RECIPIENT_ID)
                .orElseThrow(IllegalArgumentException::new);
        final String messageId = getPropertyAsString(jsonObject, PROP_MESSAGE_ID)
                .orElseThrow(IllegalArgumentException::new);
        final Optional<String> attachmentId = getPropertyAsString(jsonObject, PROP_ATTACHMENT_ID);
        return new MessageResponse(recipientId, messageId, attachmentId);
    }
}
