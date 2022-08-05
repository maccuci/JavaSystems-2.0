package LibraryManagement.backend.sql;

import LibraryManagement.backend.Database;
import LibraryManagement.backend.queries.LibrarySqlQueries;
import OnlineBank.backend.queries.BankSqlQueries;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class SqlDatabase implements Database {

    private static final Executor asyncExecutor = Executors.newSingleThreadExecutor((new ThreadFactoryBuilder()).setNameFormat("Async Thread").build());

    private Connection connection;
    private final String hostname, database, username, password;
    private final int port;

    public SqlDatabase(String hostname, String database, String username, String password, int port) {
        this.hostname = hostname;
        this.database = database;
        this.username = username;
        this.password = password;
        this.port = port;
    }

    @Override
    public void connect() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s", hostname, port, database), username, password);
        if (loadTables())
            System.out.println("Trying to create tables.");
        else
            System.out.println("Error when create all tables.");

        connection.createStatement().executeUpdate("CREATE SCHEMA IF NOT EXISTS `" + database + "` DEFAULT CHARACTER SET utf8 ;");
    }

    boolean loadTables() {
        AtomicBoolean finish = new AtomicBoolean(false);

        try {
            for(LibrarySqlQueries queries : LibrarySqlQueries.values()) {
                executeUpdate(queries.toString());
            }
            finish.set(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return finish.get();
    }

    public ResultSet executeQuery(String query) {
        try {
            return getConnection().createStatement().executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Impossible to execute the mysql query (" + query + ").");
        }
        return null;
    }

    public boolean executeAsyncUpdate(String update) {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        asyncExecutor.execute(() -> {
            try {
                PreparedStatement statement = getConnection().prepareStatement(update);
                statement.execute();
                statement.close();
                atomicBoolean.set(true);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.printf("Impossible to execute a async mysql update (%s). %s", update, e.getMessage());
            }
        });
        return atomicBoolean.get();
    }

    public boolean executeUpdate(String update) {
        AtomicBoolean callback = new AtomicBoolean(false);
        try {
            PreparedStatement statement = getConnection().prepareStatement(update);
            statement.execute();
            statement.close();
            callback.set(true);
        } catch (Exception e) {
            System.out.println("Impossible to execute a sync mysql update (" + update + ").");
        }
        return callback.get();
    }

    @Override
    public void disconnect() throws Exception {

    }

    @Override
    public boolean isConnected() throws Exception {
        return false;
    }

    public Connection getConnection() {
        return connection;
    }
}
