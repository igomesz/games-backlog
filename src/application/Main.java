package application;

import dao.GameDAO;
import entities.Game;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        boolean running = true;
        while (running) {

            int n = 0;
            try {
                System.out.println("\n================================= GAMES BACKLOG =================================");
                System.out.println("0 - Sair");
                System.out.println("1 - Cadastrar Jogo");
                System.out.println("2 - Listar Jogos");
                System.out.println("3 - Filtrar por Status");
                System.out.println("4 - Ver Estilos");
                System.out.println("5 - Horas por Estilo");
                System.out.println("6 - Atualizar Status");
                System.out.println("7 - Deletar por Id");
                System.out.print("Digite: ");
                n = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Digite um numero!");
                sc.nextLine();
                continue;
            }

            switch (n) {

                case 0:
                    System.out.println("\n================================= ENCERRANDO... =================================");
                    running = false;
                    break;

                case 1:
                    System.out.println("\n================================= CADASTRO DE JOGO =================================");
                    System.out.print("Qual nome do jogo? ");
                    String name = sc.nextLine();
                    System.out.print("Qual o estilo do jogo? ");
                    String style = capitalize(sc.nextLine());
                    Double hoursPlayed = readDouble(sc, "Quantas horas jogadas? ");
                    sc.nextLine();
                    String status = readStatus(sc, "Quero jogar | Em progresso | Zerado \nQual status do jogo?  ");
                    Game game = new Game(null, name, style, hoursPlayed, status);
                    GameDAO.insert(game);
                    System.out.println("=====================================================================================\n");
                    break;

                case 2:
                    System.out.println("\n================================= LISTAGEM DE JOGOS =================================");
                    printGames(GameDAO.findAll());
                    System.out.println("======================================================================================\n");
                    break;

                case 3:
                    System.out.println("\n================================= FILTRAR POR STATUS =================================");
                    System.out.println("Quero jogar | Em progresso | Zerado:");
                    String statusGame = readStatus(sc, "Qual status voce quer filtrar? ");
                    System.out.println();
                    List<Game> allGames = GameDAO.findAll();
                    List<Game> listFilter = allGames.stream().filter(game1 -> game1.getStatus()
                            .equalsIgnoreCase(statusGame))
                            .toList();
                    printGames(listFilter);
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

                case 5:
                    System.out.println("\n================================= HORAS POR ESTILO =================================");
                    List<Game> listHourPerStyle = GameDAO.findAll();
                    Map<String, Double> hoursPerStyle = new HashMap<>();
                    for (Game styleHour : listHourPerStyle) {
                        Double hours = styleHour.getHoursPlayed();
                        Double current = hoursPerStyle.getOrDefault(styleHour.getStyle(), 0.0);
                        hoursPerStyle.put(styleHour.getStyle(), hours + current);
                    }
                    System.out.println(hoursPerStyle);
                    System.out.println("=====================================================================================\n");
                    break;

                case 6:
                    System.out.println("\n================================= ATUALIZAR STATUS =================================");
                    List<Game> listGames = GameDAO.findAll();
                    printGames(listGames);
                    int id = readInt(sc,"Qual ID voce quer atualizar? ");
                    sc.nextLine();
                    boolean exist = listGames.stream().anyMatch(g-> g.getId() == id);
                    if (exist){
                        String statusUpdate = readStatus(sc, "Quero jogar | Em progresso | Zerado: ");
                        Game gameUpdate = new Game(id, null, null, null, statusUpdate);
                        GameDAO.update(gameUpdate);
                        System.out.println("Jogo ID:" + id + " atualizado.");
                    } else {
                        System.out.println("Nao existe esse ID.");
                    }
                    System.out.println("=====================================================================================\n");
                    break;

                case 7:
                    System.out.println("\n================================= DELETAR POR ID =================================");
                    printGames(GameDAO.findAll());
                    int deleteNumber = readInt(sc, "Qual ID voce quer deletar? ");
                    if (GameDAO.deleteById(deleteNumber) > 0){
                        System.out.println("Jogo ID:" + deleteNumber + " deletado.");
                    } else {
                        System.out.println("Nao existe esse ID.");
                    }
                    System.out.println("=====================================================================================\n");
                    break;

                default:
                    System.out.println("Numero invalido");

            }

        }

    }

    private static void printGames (List<Game> findGames){
        for (Game games : findGames){
            System.out.println(games);
        }
    }

    private static int readInt (Scanner sc, String message){
        while (true){
            System.out.print(message);
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Digite um numero!");
                sc.nextLine();
            }
        }
    }

    private static Double readDouble (Scanner sc, String message){
        while (true){
            System.out.print(message);
            try {
                return sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Digite um numero!");
                sc.nextLine();
            }
        }
    }

    private static String readStatus (Scanner sc, String message){
        while (true){
            System.out.print(message);
            String status = capitalize(sc.nextLine());
            switch (status){
                case "Quero jogar", "Em progresso" , "Zerado" -> {
                    return status;
                }
                default -> System.out.println("Escolha um status valido!\nQuero jogar | Em progresso | Zerado\n");
            }
        }
    }

    private static String capitalize (String text){
        if (text == null || text.isBlank()){
            return text;
        }
        text = text.trim();
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }

}