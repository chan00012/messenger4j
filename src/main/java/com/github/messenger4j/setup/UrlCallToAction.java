package com.github.messenger4j.setup;

import static java.util.Optional.empty;

import com.github.messenger4j.common.WebviewHeightRatio;
import java.net.URL;
import java.util.Optional;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * @author Max Grabenhorst
 * @since 1.0.0
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class UrlCallToAction extends CallToAction {

    private final URL url;
    private final Optional<WebviewHeightRatio> webviewHeightRatio;
    private final Optional<Boolean> messengerExtensions;
    private final Optional<URL> fallbackUrl;

    public static UrlCallToAction create(@NonNull String title, @NonNull URL url) {
        return create(title, url, empty(), empty(), empty());
    }

    public static UrlCallToAction create(@NonNull String title, @NonNull URL url,
                                         @NonNull Optional<WebviewHeightRatio> webviewHeightRatio,
                                         @NonNull Optional<Boolean> messengerExtensions,
                                         @NonNull Optional<URL> fallbackUrl) {
        return new UrlCallToAction(title, url, webviewHeightRatio, messengerExtensions, fallbackUrl);
    }

    private UrlCallToAction(String title, URL url, Optional<WebviewHeightRatio> webviewHeightRatio,
                            Optional<Boolean> messengerExtensions, Optional<URL> fallbackUrl) {
        super(Type.WEB_URL, title);
        this.url = url;
        this.webviewHeightRatio = webviewHeightRatio;
        this.messengerExtensions = messengerExtensions;
        this.fallbackUrl = fallbackUrl;
    }

    public URL url() {
        return url;
    }

    public Optional<WebviewHeightRatio> webviewHeightRatio() {
        return webviewHeightRatio;
    }

    public Optional<Boolean> messengerExtensions() {
        return messengerExtensions;
    }

    public Optional<URL> fallbackUrl() {
        return fallbackUrl;
    }
}
