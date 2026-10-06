import java.util.HashSet;
import java.util.List;
import java.util.Set;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

//        DB.getConnection();
//        if (DB.getConnection() != null){
//            System.out.println("Conectado");
//        }
//
        Game game1 = new Game(null, "Bully", "Adventure", 20.0, "Zerado");
        GameDAO gameDAO = new GameDAO();
        gameDAO.insert(game1);
////
////        Game game2 = new Game(null,"GTA5", "Acao", 42.5, "Zerado");
////        gameDAO = new GameDAO();
////        gameDAO.insert(game2);
//
//        GameDAO gameDAO = new GameDAO();
//        gameDAO.deleteById(7);
//
//        gameDAO = new GameDAO();
//        gameDAO.update(new Game(8,"GTA5", "Acao", 42.5, "Em progresso"));
//
//        List<Game> gamesList = gameDAO.findAll();
//        for (Game game : gamesList){
//            System.out.println(game);
//        }

        gameDAO = new GameDAO();
        List<Game> listGame = gameDAO.findAll();
        listGame.stream()
                .filter(game -> game.getStatus().equalsIgnoreCase("Em progresso"))
                .forEach(System.out::println);

        List<Game> listGames = gameDAO.findAll();
        Set<String> styles = new HashSet<>();
        for (Game game : listGames) {
            styles.add(game.getStyle());
        }
        System.out.println(styles);
    }
}