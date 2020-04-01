package tchorzyksen.entity;

public class GreetingEntity {
    private final long id;
    private final String content;

    public GreetingEntity(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
