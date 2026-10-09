import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        boolean running = true;
        while (running) {

            System.out.println("=== GAMES BACKLOG ===");
            System.out.println("0 - Sair");
            System.out.println("1 - Cadastrar jogo");
            System.out.println("2 - Listar jogos");
            System.out.println("3 - Filtrar por status");
            System.out.println("4 - Ver estilos");
            int n = sc.nextInt();

            switch (n) {

                case 0:
                    System.out.println("\n================================= ENCERRANDO... =================================");
                    running = false;
                    break;

                case 1:
                    System.out.println("\n================================= CADASTRO DE JOGO =================================");
                    sc.nextLine();
                    System.out.print("Qual nome do jogo? ");
                    String name = sc.nextLine();
                    System.out.print("Qual o estilo do jogo? ");
                    String style = sc.nextLine();
                    System.out.print("Quantas horas jogadas? ");
                    Double hoursPlayed = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Qual o status do jogo? ");
                    String status = sc.nextLine();
                    Game game = new Game(null, name, style, hoursPlayed, status);
                    GameDAO.insert(game);
                    System.out.println("=====================================================================================\n");
                    break;

                case 2:
                    System.out.println("\n================================= LISTAGEM DE JOGOS =================================");
                    List<Game> gamesList = GameDAO.findAll();
                    for (Game games : gamesList) {
                        System.out.println(games);
                    }
                    System.out.println("======================================================================================\n");
                    break;

                case 3:
                    System.out.println("\n================================= FILTRAR POR STATUS =================================");
                    System.out.print("Digite um status: ");
                    sc.nextLine();
                    String statusGame = sc.nextLine();
                    List<Game> allGames = GameDAO.findAll();
                    List<Game> listFilter = allGames.stream().filter(game1 -> game1.getStatus().equalsIgnoreCase(statusGame)).toList();
                    for (Game gameFilter : listFilter) {
                        System.out.println(gameFilter);
                    }
                    System.out.println("=====================================================================================\n");
                    break;

                case 4:
                    System.out.println("\n================================= VER ESTILOS =================================");
                    List<Game> listStyle = GameDAO.findAll();
                    Set<String> setStyles = new HashSet<>();
                    for (Game gameStyle : listStyle) {
                        setStyles.add(gameStyle.getStyle());
                    }
                    System.out.println(setStyles);
                    System.out.println("=====================================================================================\n");
                    break;

                default:
                    System.out.println("Numero invalido");

            }

        }

//        Map<String, Double> hoursPerStyle = new HashMap<>();
//        for (Game game : listGame) {
//            Double hours = game.getHoursPlayed();
//            Double current = hoursPerStyle.getOrDefault(game.getStyle(), 0.0);
//            hoursPerStyle.put(game.getStyle(), hours + current);
//        }
//        System.out.println(hoursPerStyle);

    }

}