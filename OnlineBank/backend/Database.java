package OnlineBank.backend;

public interface Database {

    void connect() throws Exception;

    void disconnect() throws Exception;

    boolean isConnected() throws Exception;
}
