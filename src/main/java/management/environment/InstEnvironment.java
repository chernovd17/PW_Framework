package management.environment;


public class InstEnvironment extends BaseEnv {

    private static InstEnvironment _instance = new InstEnvironment();
    private String imagesPath;
    private String tempTagsFilePath;
    private String cookieFilePath;
    private String defTagsFilePath;
    private int countOfPostUpdates;
    private int countOfRequiredLikes;
    private int updatingPause;
    private int countOfTags;
    private String username;
    private String password;

    private InstEnvironment() {
        super("/properties/inst.properties");

        imagesPath = getProperty("storage.imagesStorage");
        tempTagsFilePath = getProperty("storage.tempTagsFileStorage");
        cookieFilePath = getProperty("file.cookies");
        defTagsFilePath = getProperty("file.tagsFile");
        countOfPostUpdates = Integer.parseInt(getProperty("post.max_count_of_updates"));
        countOfRequiredLikes = Integer.parseInt(getProperty("post.count_of_likes"));
        updatingPause = Integer.parseInt(getProperty("post.minimal_updating_pause_in_minutes"));
        countOfTags = Integer.parseInt(getProperty("post.count_of_tags"));
        username = getProperty("username");
        password = getProperty("password");
    }

    public static InstEnvironment get() {
        if (_instance == null) {
            _instance = new InstEnvironment();
        }
        return _instance;
    }

    public String getImagesPath() {
        return imagesPath;
    }

    public String getTempTagsFilePath() {
        return tempTagsFilePath;
    }
    public String getCookieFilePath() {
        return cookieFilePath;
    }

    public String getDefTagsFilePath() {
        return defTagsFilePath;
    }

    public int getCountOfPostUpdates() {
        return countOfPostUpdates;
    }

    public int getCountOfRequiredLikes() {
        return countOfRequiredLikes;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getUpdatingPause(){
        return updatingPause;
    }

    public int getCountOfTags() {
        return countOfTags;
    }
}
