package LibraryManagement.backend.queries;

public enum LibrarySqlQueries {

    DATABASE("library_management"),

    LAST_ID("SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='" + DATABASE.toString() + "' AND TABLE_NAME=?;");

    private final String query;

    private LibrarySqlQueries(String query) {
        this.query = query;
    }

    public String toString() {
        return query;
    }
}
