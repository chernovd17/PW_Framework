package management.playwright;


import com.microsoft.playwright.Playwright;
import lombok.Getter;

public class PlaywrightSession implements AutoCloseable {

    private static final PlaywrightSession _instance = new PlaywrightSession();
    @Getter
    private final Playwright playwright;

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                _instance.close();
            } catch (Exception ignore) {
            }
        }));
    }

    public static PlaywrightSession getInstance() {
        return _instance;
    }

    public PlaywrightSession() {
        this.playwright = Playwright.create();
    }

    @Override
    public void close() {
        if(playwright != null)
            playwright.close();
    }
}
