package JavaRush.MiniProjects.others.SomeDialog.service;

import JavaRush.MiniProjects.others.SomeDialog.connection.Connection;

abstract class AbstractChatJoiner {
    private volatile boolean userJoined;
    private Connection connection;
    abstract void join();
    abstract void exit();

    public boolean isUserJoined() {
      return userJoined;
   }

    void setUserJoined(boolean userJoined) {
      this.userJoined = userJoined;
   }

    Connection getConnection() {
      return connection;
   }

    void setConnection(Connection connection) {
      this.connection = connection;
   }
}
