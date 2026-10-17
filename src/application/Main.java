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
                    printGames(GameDAO.findAll());
                    System.out.println("======================================================================================\n");
                    break;

                case 3:
                    System.out.println("\n================================= FILTRAR POR STATUS =================================");
                    System.out.println("Zerado / Em progresso");
                    System.out.print("Digite um status: ");
                    sc.nextLine();
                    String statusGame = sc.nextLine();
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
                    printGames(GameDAO.findAll());
                    System.out.print("Qual id voce quer atualizar? ");
                    int id = sc.nextInt();
                    System.out.print("Zerado / Em progresso: ");
                    sc.nextLine();
                    String statusUpdate = sc.nextLine();
                    Game gameUpdate = new Game(id, null, null, null, statusUpdate);
                    GameDAO.update(gameUpdate);
                    System.out.println("=====================================================================================\n");
                    break;

                case 7:
                    System.out.println("\n=====" +
                            "============================ DELETAR POR ID =================================");
                    printGames(GameDAO.findAll());
                    System.out.print("Digita o Id que voce quer deletar: ");
                    int deleteNumber = sc.nextInt();
                    GameDAO.deleteById(deleteNumber);
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

}