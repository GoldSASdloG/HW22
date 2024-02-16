import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static List<String> toDos = new ArrayList<>();

    private static final String INDEX_AND_TODO_REGEX = "(\\d+)(\\s+)(.+)";
    private static final String INDEX_REGEX = "^\\d+";


    public static void main(String[] args) {
        //todo для выполнения дз создавать классы не нужно. Просто создавайте
        // необходимые вам методы и переменные. Основная логика программы будет в методе main

        System.out.println("\t\tДобро пожаловать в программу-ваш помошник по составлению списка дел!");
        String info = "Доступные команды:\n" +
                "Добавить {дело}\n" +
                "Добавить {номер} {дело}\n" +
                "Удалить {номер}\n" +
                "Изменить {номер} {новое дело}\n" +
                "Печать\n" +
                "Выход\n" +
                "Инфо";
        System.out.println(info + "\n");
        while (true){
            System.out.println("Введите команду:");
            String input = new Scanner(System.in).nextLine();

            String comandSlova = input;
            String playLoad = "";

            if (input.contains(" ")) {
                String[] chastVoprosaLexema = input.split("\\s+", 2);
                comandSlova = chastVoprosaLexema[0];
                playLoad = chastVoprosaLexema[1].trim();
            }


            if(comandSlova.toLowerCase().equals("добавить")){
                if (playLoad.matches(INDEX_AND_TODO_REGEX)){
                    Integer index = Integer.parseInt(playLoad.replaceAll(INDEX_AND_TODO_REGEX, "$1"));
                    String todo = playLoad.replaceAll(INDEX_REGEX, "").trim();
                    add(todo, index);

                } else {
                    add(playLoad);
                }

            }else if (comandSlova.toLowerCase().equals("удалить")){
                Integer index = Integer.parseInt(playLoad);
                delete(index);

            }else if (comandSlova.toLowerCase().equals("изменить")){
                Integer index = Integer.parseInt(playLoad.replaceAll(INDEX_AND_TODO_REGEX, "$1"));
                String newTodo = playLoad.replaceAll(INDEX_REGEX, "").trim();
                addIndex(newTodo, index);

            }else if (comandSlova.toLowerCase().equals("печать")){
                print();
            }else if (comandSlova.toLowerCase().equals("выход")){
                System.out.println("Пока-Пока!!!");
                return;
            }else{
                System.out.println("Ввод не верен!\n\n\n" + info);
            }

        }
    }

    public static void add(String todo){
        toDos.add(todo);
        System.out.println("Дело \"" + todo + "\" успешно добавлено!");
    }
    public static void add(String todo, Integer index){
        if (index >= toDos.size()) {
            toDos.add(todo);
            System.out.println("Нет места под номером " + index + ". " +
                    "Дело \"" + todo + "\" добавлено в конец списка!");
            return;
        }
        toDos.add(index, todo);
        System.out.println("На " + index + " место добавлено дело \"" + todo + "\"");


    }
    public static void delete (Integer index){
        if (index >= toDos.size()) {
            System.out.println("Дело под номером \"" + index + "\" нет в списке!");
            return;
        }
        String todo = toDos.get(index);
        toDos.remove(todo);
        System.out.println("Дело \"" + todo + "\" под номером \"" + index + "\" успешно УДАЛЕНО!");

    }
    public static void addIndex(String newTodo, Integer index){
        if (index >= toDos.size()) {
            System.out.println("Дело под номером \"" + index + "\" нет в списке!");
            return;
        }
        String oldTodo = toDos.get(index);
        toDos.set(index, newTodo);
        System.out.println("Дело \"" + oldTodo + "\" изменено на \"" + newTodo + "\"");

    }
    public static void print(){
        if (toDos.isEmpty()){
            System.out.println("Список дел пуст!");
            return;
        }
        for (String todo : toDos){
            System.out.println(todo);
        }
    }
}