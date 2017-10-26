package com.github.messenger4j.setup;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Andriy Koretskyy
 * @author Max Grabenhorst
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode
public abstract class CallToAction {

    private final Type type;
    private final String title;

    CallToAction(Type type, String title) {
        this.type = type;
        this.title = title;
    }

    public Type type() {
        return type;
    }

    public String title() {
        return title;
    }

    /**
     * @author Andriy Koretskyy
     * @since 0.8.0
     */
    public enum Type {

        WEB_URL,
        POSTBACK,
        NESTED
    }
}
