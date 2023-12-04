package Database;

import Entities.Card;
import Entities.Stats;
import Entities.User;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseHelper {

    private static String dbPath;

    public DatabaseHelper(String dbPath){
        this.dbPath = dbPath;
    }

    public static void createNewDatabase(String fileName) {

        String url = "jdbc:sqlite:src/db/" + fileName;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");

                createTables();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection connect() {
        String url = "jdbc:sqlite:" + dbPath;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public static void createTables() {
        createStatsTable();
        createCardsTable();
        createuserTables();
    }

    private static void createStatsTable() {
        String sql = "CREATE TABLE IF NOT EXISTS Stats (" +
                " CardID INTEGER," +
                " Level INTEGER," +
                " Affinity TEXT," +
                " BaseHP INTEGER," +
                " BaseDEF INTEGER," +
                " BaseATK INTEGER," +
                " BaseCRIT INTEGER," +
                " Rarity TEXT"+
                ");";

        executeSql(sql);


    }

    private static void createCardsTable() {
        String sql = "CREATE TABLE IF NOT EXISTS Cards (" +
                " Id INTEGER PRIMARY KEY," +
                " Name TEXT," +
                " ImageID INTEGER," +
                " ImgPath TEXT," +
                " Description TEXT," +
                " StatsID INTEGER," +
                " FOREIGN KEY (StatsID) REFERENCES Stats (StatsID)" +
                ");";

        executeSql(sql);


    }

    private static void executeSql( String sql) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertNewCardIntoSQLite( Card card) {
        String sql = "INSERT INTO Cards (Id, Name, ImageID, ImgPath, Description) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, card.getId());
            pstmt.setString(2, card.getName());
            pstmt.setInt(3, card.getImageID());
            pstmt.setString(4, card.getimgpath());
            pstmt.setString(5, card.getDesc());
            pstmt.executeUpdate();

            insertStatsIntoSQLite(card.getStats(), card.getId());


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void insertStatsIntoSQLite( Stats stats, int cardId) {
        String sql = "INSERT INTO Stats (CardID, Level, Affinity, BaseHP, BaseDEF, BaseATK, BaseCRIT,Rarity) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, cardId);
            pstmt.setInt(2, stats.getLevel());
            pstmt.setString(3, stats.getAffinity());
            pstmt.setInt(4, stats.getBaseHP());
            pstmt.setInt(5, stats.getBaseDEF());
            pstmt.setInt(6, stats.getBaseATK());
            pstmt.setInt(7, stats.getBaseCRIT());
            pstmt.setString(8, stats.getRarity());
            // Set other stats fields...

            pstmt.executeUpdate();


        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    public void insertCardIntoSQLite(Card card){
        if (cardExistsInDatabase(card.getId())) {
            updateCardInDatabase(card);
        } else {
            insertNewCardIntoSQLite(card);
        }
    }

    public static ArrayList<Card> loadCards() {
        ArrayList<Card> cards = new ArrayList<>();
        String sql = "SELECT * FROM Cards LEFT JOIN Stats ON Cards.Id = Stats.CardID";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // Extracting Card and Stats data
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                int imageId = rs.getInt("ImageID");
                String imgPath = rs.getString("ImgPath");
                String desc = rs.getString("Description");

                Stats stats = new Stats(rs.getInt("Level"), rs.getString("Affinity"),
                        rs.getInt("BaseHP"), rs.getInt("BaseDEF"),
                        rs.getInt("BaseATK"), rs.getInt("BaseCRIT"), rs.getString("Rarity"));


                Card card = new Card(id, name, imageId, imgPath, desc, stats);
                cards.add(card);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return cards;
    }

    public boolean idExistsInDatabase(int id) {
        String sql = "SELECT Id FROM Cards WHERE Id = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // Returns true if ID already exists in the database
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    private boolean cardExistsInDatabase(int id) {
        String sql = "SELECT Id FROM Cards WHERE Id = ?";
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    private void updateCardInDatabase(Card card) {
        String sql = "UPDATE Cards SET Name = ?, ImageID = ?, ImgPath = ?, Description = ? WHERE Id = ?";
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, card.getName());
            pstmt.setInt(2, card.getImageID());
            pstmt.setString(3, card.getDesc());
            pstmt.setString(4, card.getimgpath());
            pstmt.setInt(5, card.getId());
            pstmt.executeUpdate();
            updateStatsInDatabase(card.getStats(), card.getId());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateStatsInDatabase(Stats stats, int cardId) {
        String sql = "UPDATE Stats SET Level = ?, Affinity = ?, BaseHP = ?, BaseDEF = ?, BaseATK = ?, BaseCRIT = ?, Rarity = ? WHERE CardID = ?";
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, stats.getLevel());
            pstmt.setString(2, stats.getAffinity());
            pstmt.setInt(3, stats.getBaseHP());
            pstmt.setInt(4, stats.getBaseDEF());
            pstmt.setInt(5, stats.getBaseATK());
            pstmt.setInt(6, stats.getBaseCRIT());
            pstmt.setString(7, stats.getRarity());
            pstmt.setInt(8, cardId);
            // Set other stats fields...


            pstmt.executeUpdate();


        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    //User Implementation

    private static void createuserTables() {
        String createUserTableSQL = "CREATE TABLE IF NOT EXISTS Users ("
                + "UserID INT PRIMARY KEY, "
                + "Username VARCHAR(255), "
                + "Password VARCHAR(255), "
                + "UserLevel INT, "
                + "Currency DECIMAL);";

        String createUserCardsTableSQL = "CREATE TABLE IF NOT EXISTS UserCards ("
                + "UserID INT, "
                + "CardID INT, "
                + "FOREIGN KEY (UserID) REFERENCES Users(UserID));";

        executeSql(createUserTableSQL);
        executeSql(createUserCardsTableSQL);
    }

    public static void saveUser(User user) {
        String sql = "INSERT INTO Users (UserID, Username, Password, UserLevel, Currency) VALUES (?, ?, ?, ?, ?) "
                + "ON CONFLICT(UserID) DO UPDATE SET "
                + "Username = excluded.Username, "
                + "Password = excluded.Password, "
                + "UserLevel = excluded.UserLevel, "
                + "Currency = excluded.Currency;";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, user.getUserid());
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, user.getPassword());
            pstmt.setInt(4, user.getUserlevel());
            pstmt.setInt(5, user.getCurrency());

            pstmt.executeUpdate();

            // Save the user's cards
            saveUserCards(user.getUserid(), user.getCards_owned());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static User loadUser(String Username) {
        String sql = "SELECT * FROM Users WHERE Username = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)) {

            pstmt.setString(1, Username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Integer userId = rs.getInt("UserID");
                    String username = rs.getString("Username");
                    String password = rs.getString("Password");
                    int userLevel = rs.getInt("UserLevel");
                    int currency = rs.getInt("Currency");

                    ArrayList<Integer> cardsOwned = loadUserCards(userId);


                    return new User(userId, cardsOwned, userLevel, currency, username, password);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static void saveUserCards(int userId, ArrayList<Integer> cardsOwned) {
        // Delete existing records for the user


        // Insert new records
        String sqlInsert = "INSERT INTO UserCards (UserID, CardID) VALUES (?, ?)";
        String sqlCheck = "SELECT COUNT(*) FROM UserCards WHERE UserID = ? AND CardID = ?";

        try (Connection conn = connect();
             PreparedStatement pstmtInsert = conn.prepareStatement(sqlInsert);
             PreparedStatement pstmtCheck = conn.prepareStatement(sqlCheck)) {

            for (Integer cardId : cardsOwned) {
                // Check if the card already exists for the user
                pstmtCheck.setInt(1, userId);
                pstmtCheck.setInt(2, cardId);
                ResultSet rs = pstmtCheck.executeQuery();
                if (rs.next() && rs.getInt(1) == 0) {
                    // If the card is not already owned by the user, insert it
                    pstmtInsert.setInt(1, userId);
                    pstmtInsert.setInt(2, cardId);
                    pstmtInsert.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static ArrayList<Integer> loadUserCards(int userId) {
        ArrayList<Integer> cardsOwned = new ArrayList<>();
        String sql = "SELECT CardID FROM UserCards WHERE UserID = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int cardId = rs.getInt("CardID");
                    cardsOwned.add(cardId);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cardsOwned;
    }

}





