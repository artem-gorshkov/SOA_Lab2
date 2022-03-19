package itmo.gorshkov;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class ContentTypeInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        if (template.headers().containsKey("X-Content-Type2")) {
            template.removeHeader("Content-Type");
            template.header("Content-Type", template.headers().get("X-Content-Type2"));
            template.removeHeader("X-Content-Type2");
        }
    }
}
