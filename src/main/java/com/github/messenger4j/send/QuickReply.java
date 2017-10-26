package com.github.messenger4j.send;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Max Grabenhorst
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode
public abstract class QuickReply {

    private final ContentType contentType;

    QuickReply(ContentType contentType) {
        this.contentType = contentType;
    }

    public ContentType contentType() {
        return contentType;
    }

    /**
     * @author Max Grabenhorst
     * @since 0.6.0
     */
    public enum ContentType {
        TEXT,
        LOCATION
    }
}