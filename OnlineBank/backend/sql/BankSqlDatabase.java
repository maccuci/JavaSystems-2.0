package OnlineBank.backend.sql;

import OnlineBank.backend.Database;
import OnlineBank.backend.queries.BankSqlQueries;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class BankSqlDatabase implements Database {

    private static final Executor asyncExecutor = Executors.newSingleThreadExecutor((new ThreadFactoryBuilder()).setNameFormat("Async Thread").build());

    private Connection connection;
    private final String hostname, database, username, password;
    private final int port;

    public BankSqlDatabase(String hostname, String database, String username, String password, int port) {
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
        loadTables();

        connection.createStatement().executeUpdate("CREATE SCHEMA IF NOT EXISTS `" + database + "` DEFAULT CHARACTER SET utf8 ;");
    }

    boolean loadTables() {
        AtomicBoolean finish = new AtomicBoolean(false);

        try {
            for(BankSqlQueries queries : BankSqlQueries.values()) {
                String[] name = queries.toString().split("`");
                if (queries.name().startsWith("TABLE")) {
                    System.out.println("Trying to create the table " + name[1] + " to the mysql database.");
                    executeUpdate(queries.toString());
                }
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
        if(isConnected())
            getConnection().close();
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public boolean isConnected() throws Exception {
        return connection != null && !connection.isClosed();
    }
}
