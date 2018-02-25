import java.sql.*;

public class DatabaseManager
{
    protected static Connection cnx = null;

    public ResultSet query(String SQL) throws SQLException {           // this function returns result of our query

        Statement stmt = cnx.createStatement();                         // state of DB connection

        ResultSet result = stmt.executeQuery(SQL);

        return result;
    }
    public void insert(String SQL) throws SQLException {           // this function returns result of our query

        Statement stmt = cnx.createStatement();                         // state of DB connection
        stmt.executeQuery(SQL);
    }

        public int update(String SQL) throws SQLException {                 //we update our database
        Statement stmt = cnx.createStatement();                         // state of DB connection
        int done = stmt.executeUpdate(SQL);
        return done;                                                    // we return an integer to say update is done
    }

    public int updateReturnID(String SQL) throws SQLException {         //this function allows us to get back the ID of the last item that was updated
        Statement stmt = cnx.createStatement();                         // state of DB connection
        int id = -1;
        stmt.executeUpdate(SQL, Statement.RETURN_GENERATED_KEYS);
        ResultSet result = stmt.getGeneratedKeys();
        if (result.next()) {
            id = result.getInt(1);
        }
        return id;
    }

    public boolean disconnect() {                                       // this function allows us to disconnect
        try {
            if (cnx != null) {
                cnx.close();
            }
        } catch (SQLException e) {
            System.out.println("Connexion error : " + e.getMessage());
            return false;
        }
        return true;
    }

}
