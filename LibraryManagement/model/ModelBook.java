package LibraryManagement.model;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class ModelBook {

    private int id;
    private final String name;
    private final String[] description, authors;
    private final boolean available;

    public ModelBook(String name, String[] description, String[] authors, boolean available) {
        this.name = name;
        this.description = description;
        this.authors = authors;
        this.available = available;
    }

    public boolean createAndLoadBook() {
        AtomicBoolean finish = new AtomicBoolean(false);

        try {

        } catch (Exception e) {

        }
        return finish.get();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String[] getDescription() {
        return description;
    }

    public String[] getAuthors() {
        return authors;
    }

    public boolean isAvailable() {
        return available;
    }

    @Override
    public String toString() {
        return "ModelBook{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description=" + Arrays.toString(description) +
                ", authors=" + Arrays.toString(authors) +
                ", available=" + available +
                '}';
    }
}
