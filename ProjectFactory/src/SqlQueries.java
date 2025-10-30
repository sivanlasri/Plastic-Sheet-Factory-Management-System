import java.sql.*;
import java.util.ArrayList;

public class SqlQueries {
    private static Connection connection ;
    private static Statement statement;
    SqlQueries(Connection connection,Statement statement)
    //Constructor for reuse, no need to send the Connection and the statement everytime
    {
        SqlQueries.connection = connection;
        SqlQueries.statement = statement;
    }

    public static String executeMaxLoggedValueDetails()
    //Query number 1 - show the LogID, LineID, LogTime of the max LoggedValue
    {
        String results = "";
        String sqlQuery = "SELECT LogID, LineID, LogTime, LoggedValue "
                + "FROM factory "
                + "ORDER BY LoggedValue DESC "
                + "LIMIT 1;";
        //sorting from high to low showing the highest LoggedValue with LogID, LineID, LogTime and takes only the first row
        try{
            ResultSet resultSet = SqlQueries.statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                int logID = resultSet.getInt("LogID");
                String lineID = resultSet.getString("LineID");
                String logTime = resultSet.getString("LogTime");
                double loggedValue = resultSet.getDouble("LoggedValue");
                results+="The Details of maximum LoggedValue are: \n";
                results+="LogID: " + logID + "  ";
                results+=" ,LineID: " + lineID + "  ";
                results+=" ,LogTime: " + logTime + "  ";
                results+=" ,LoggedValue: " + loggedValue + "\n" ;
                }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
    public static String executeTotalLoggedValueQuery(int userInputLogID)
    //Query number 2 - Total sum of given LogID
        {
        String results = "";
        String sqlQuery = "SELECT ROUND(SUM(LoggedValue), 2) AS total FROM factory WHERE LogID = ?";
        try (PreparedStatement preparedStatement = SqlQueries.connection.prepareStatement(sqlQuery)) {
            // Set the user input LogID as a parameter in the prepared statement
            preparedStatement.setInt(1, userInputLogID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                double totalLoggedValue = resultSet.getDouble("total");
                results+="Total LoggedValue for LogID " + userInputLogID + ": " + totalLoggedValue + "\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
    public static String executeMinimumLoggedValueQuery()
    //Query number 3 - LineID where minimum LoggedValue
        {
            String results = "";
            String sqlQuery = "SELECT LineID, LoggedValue "
                + "FROM factory "
                + "WHERE LoggedValue != 0 "
                + "ORDER BY LoggedValue ASC "
                + "LIMIT 1;";
            //sorting from low to high according to LoggedValue displaying LineID
        try {
            //execute the query with the connection
            ResultSet resultSet = SqlQueries.statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                String lineID = resultSet.getString("LineID");
                results += "LineID of minimum LoggedValue is: " + lineID +"\n";
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }


    public static String executeMaxLoggedValueBetweenDatesQuery(String startDateStr, String endDateStr) {
        String results = "";
        String sqlQuery = "SELECT LoggedValue, LineID " +
                "FROM factory " +
                "WHERE LoggedValue = ( " +
                "SELECT MAX(LoggedValue) " +
                "FROM factory " +
                "WHERE LogTime BETWEEN STR_TO_DATE(?, '%d/%m/%Y %H:%i:%s') AND STR_TO_DATE(?, '%d/%m/%Y %H:%i:%s'))";
        try (PreparedStatement preparedStatement = SqlQueries.connection.prepareStatement(sqlQuery)) {
            // Set the parameters for the start and end dates
            preparedStatement.setString(1, startDateStr + " 00:00:00"); // Start of the day
            preparedStatement.setString(2, endDateStr + " 23:59:59"); // End of the day
            try (ResultSet resultSet = preparedStatement.executeQuery()) { //execute the query with the connection
                while (resultSet.next()) {
                    String LineID = resultSet.getString("LineID");
                    double LoggedValue = resultSet.getDouble("LoggedValue");
                    results += "LineID of the max LoggedValue between the dates is: " + LineID + "\n \n" + "Max LoggedValue between the dates is: " + LoggedValue + "\n" ;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }


    public static ArrayList <String> executeLoggedValueZeroTimesQuery()
    //Query number 5 - When Loggedvalue was 0
        {
        ArrayList<String> zeroTimes = new ArrayList<>();
        String sqlQuery = "SELECT LogTime, LoggedValue "
                + "FROM factory "
                + "WHERE LoggedValue = '0' ";
        try {
            //execute the query with the connection
            ResultSet resultSet = SqlQueries.statement.executeQuery(sqlQuery);
            zeroTimes.add("Times when Logged Value was 0: ");
            while (resultSet.next()) {
                zeroTimes.add(resultSet.getString("LogTime"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return zeroTimes;
    }
}
