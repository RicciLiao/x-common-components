package ricciliao.x.component.cache;

public class CacheConstants {

    private CacheConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String HTTP_HEADER_FOR_CACHE_CUSTOMER = "Cache-Customer";
    public static final String HTTP_HEADER_FOR_CACHE_STORE = "Cache-Store";
    public static final String CACHE_STORE_NAME_FOR_EMAIL = "email";
    public static final String CACHE_STORE_NAME_FOR_CAPTCHA = "captcha";

}
