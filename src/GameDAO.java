import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GameDAO {

    public void insert(Game game){
        try {
            PreparedStatement st = DB.getConnection().prepareStatement(
                    "INSERT INTO jogos (nome, estilo, horas, status)" +
                        "VALUES (?,?,?,?)"
            );

            st.setString(1, game.getName());
            st.setString(2, game.getStyle());
            st.setDouble(3, game.getHoursPlayed());
            st.setString(4, game.getStatus());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
