import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

//        DB.getConnection();
//        if (DB.getConnection() != null){
//            System.out.println("Conectado");
//        }
//
//        Game game1 = new Game(null,"Blasphemous2", "Metroidvania", 29.9, "Em progresso");
//        GameDAO gameDAO = new GameDAO();
//        gameDAO.insert(game1);
//
//        Game game2 = new Game(null,"GTA5", "Acao", 42.5, "Zerado");
//        gameDAO = new GameDAO();
//        gameDAO.insert(game2);

        GameDAO gameDAO = new GameDAO();
        List<Game> gamesList = gameDAO.findAll();
        for (Game game : gamesList){
            System.out.println(game);
        }

    }
}