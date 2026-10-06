import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

//        DB.getConnection();
//        if (DB.getConnection() != null){
//            System.out.println("Conectado");
//        }
//
        Game game1 = new Game(null, "Madagascar", "Adventure", 15.0, "Zerado");
        GameDAO gameDAO = new GameDAO();
        gameDAO.insert(game1);


//        Game game2 = new Game(null, "GTA5", "Acao", 42.5, "Zerado");
//        gameDAO = new GameDAO();
//        gameDAO.insert(game2);
//
//        GameDAO gameDAO = new GameDAO();
//        gameDAO.deleteById(7);
//
//        gameDAO = new GameDAO();
//        gameDAO.update(new Game(8, "GTA5", "Acao", 42.5, "Em progresso"));
//
//        List<Game> gamesList = gameDAO.findAll();
//        for (Game game : gamesList) {
//            System.out.println(game);
//        }

        gameDAO = new GameDAO();
        List<Game> listGame = gameDAO.findAll();
        listGame.stream().filter(game -> game.getStatus().equalsIgnoreCase("Em progresso")).forEach(System.out::println);

        Set<String> styles = new HashSet<>();
        for (Game game : listGame) {
            styles.add(game.getStyle());
        }
        System.out.println(styles);

        Map<String, Double> hoursPerStyle = new HashMap<>();
        for (Game game : listGame) {
            Double hours = game.getHoursPlayed();
            Double current = hoursPerStyle.getOrDefault(game.getStyle(), 0.0);
            hoursPerStyle.put(game.getStyle(), hours + current);
        }
        System.out.println(hoursPerStyle);

    }

}