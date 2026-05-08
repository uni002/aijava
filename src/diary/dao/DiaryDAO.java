package diary.dao;

import diary.dto.DiaryEntry;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiaryDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/diary_db?serverTimezone=UTC&useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "mysql";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public List<DiaryEntry> findAll() throws SQLException {
        List<DiaryEntry> entries = new ArrayList<>();
        String sql = "SELECT * FROM entries ORDER BY date DESC";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                entries.add(new DiaryEntry(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getDate("date").toLocalDate()
                ));
            }
        }
        return entries;
    }

    public void save(DiaryEntry entry) throws SQLException {
        String sql = "INSERT INTO entries (title, content, date) VALUES (?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, entry.getTitle());
            stmt.setString(2, entry.getContent());
            stmt.setDate(3, Date.valueOf(java.time.LocalDate.now()));
            stmt.executeUpdate();
        }
    }
}
