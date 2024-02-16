package management.playwright;


import com.microsoft.playwright.Playwright;

public class PlaywrightSession implements AutoCloseable {

    private static final PlaywrightSession _instance = new PlaywrightSession();
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

    private PlaywrightSession() {
        this.playwright = Playwright.create();
    }

    public Playwright getPlaywright() {
        return playwright;
    }


    @Override
    public void close() {
        playwright.close();
    }
}
