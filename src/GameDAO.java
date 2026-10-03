import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Game> findAll(){
        List<Game> list = new ArrayList<>();
        try {
            PreparedStatement st = DB.getConnection().prepareStatement(
                    "SELECT * FROM jogos"
            );

            ResultSet rs = st.executeQuery();
            while (rs.next()){
                Game game = new Game(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("estilo"),
                rs.getDouble("horas"),
                rs.getString("Status")
                );
                list.add(game);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
