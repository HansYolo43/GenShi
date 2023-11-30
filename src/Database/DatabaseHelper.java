package Database;

import Entities.Card;
import Entities.Stats;

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

        System.out.println("stat");
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

        System.out.println("maybe");
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

            System.out.println("Done");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void insertStatsIntoSQLite( Stats stats, int cardId) {
        String sql = "INSERT INTO Stats (CardID, Level, Affinity, BaseHP, BaseDEF, BaseATK, BaseCRIT) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, cardId);
            pstmt.setInt(2, stats.getLevel());
            pstmt.setString(3, stats.getAffinity());
            pstmt.setInt(4, stats.getBaseHP());
            pstmt.setInt(5, stats.getBaseDEF());
            pstmt.setInt(6, stats.getBaseATK());
            pstmt.setInt(7, stats.getBaseCRIT());
            // Set other stats fields...

            pstmt.executeUpdate();

            System.out.println("done");
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
                        rs.getInt("BaseATK"), rs.getInt("BaseCRIT"));

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
            pstmt.setString(3, card.getimgpath());
            pstmt.setString(4, card.getDesc());
            pstmt.setInt(5, card.getId());
            pstmt.executeUpdate();
            updateStatsInDatabase(card.getStats(), card.getId());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateStatsInDatabase(Stats stats, int cardId) {
        String sql = "UPDATE Stats SET Level = ?, Affinity = ?, BaseHP = ?, BaseDEF = ?, BaseATK = ?, BaseCRIT = ? WHERE CardID = ?";
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, cardId);
            pstmt.setInt(2, stats.getLevel());
            pstmt.setString(3, stats.getAffinity());
            pstmt.setInt(4, stats.getBaseHP());
            pstmt.setInt(5, stats.getBaseDEF());
            pstmt.setInt(6, stats.getBaseATK());
            pstmt.setInt(7, stats.getBaseCRIT());
            // Set other stats fields...

            pstmt.executeUpdate();

            System.out.println("done");
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }
}





