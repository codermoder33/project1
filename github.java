import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class github {



    public static void main(String[] args) {
        Random r = new Random();
        int[] mass = new int[10_000];
        for(int g=0;g<mass.length;g++){
            mass[g]=r.nextInt(60);
        }
        Arrays.stream(mass).filter(a->a%2==0).map(a->a*2).distinct().forEach(System.out::println); // вывели четные уникальные числа умноженные на 2
        if(Arrays.stream(mass).allMatch(a->a==0)) System.out.println("невероятный случай");
        if(Arrays.stream(mass).anyMatch(a->a==0)) System.out.println("обычный случай");

        System.out.println(Arrays.stream(mass).reduce((a,e)->a*e).getAsInt());  // вывели произведение всех чисел

        Map<Boolean,List<Integer>> map = Arrays.stream(mass).boxed().collect(Collectors.partitioningBy(a->a%2==0));  // разделили числа на четные и нечетные
        List<Integer> list = Arrays.stream(mass).boxed().sorted().collect(Collectors.toList()); // собрали массив в отсортированный лист


        Optional<String> name = findName("Вика",new String[]{"Настя","Оля","Вика"});
        if(name.isPresent()) System.out.println("имя "+name.get()+" есть");
        System.out.println(name.orElse("Нет такого имени"));



        List<Integer> l = new ArrayList<>();
        Predicate<Integer> isEven = x -> x % 2==0;
        for(int g=0;g<10_000;g++)
            if(isEven.test(mass[g])) l.add(mass[g]);         //  добавляем в лист только четные числа

        Consumer<Integer> greetings = x -> System.out.println("Even num:"+x);    //  выводим эти четные числа
        for(int h:l)
         greetings.accept(h);

        Function<Integer, String> f = g->String.valueOf(g);
        List<String> listSTRING = new ArrayList<>();
        for(int h:mass) listSTRING.add(f.apply(h));    //  конвертируем числа в строки и добавляем в новый лист строк

        UnaryOperator<Integer> squareValue = x -> -x;
        for(int h=0;h<mass.length;h++) mass[h] = squareValue.apply(mass[h]);     // заменяем все числа массива на отрицительные аналоги

        Supplier<String> randomName = () -> {
            if(r.nextInt(6)==3) return "выбралось три";
            return "";

        };

        for(int g=0;g<60;g++)
            System.out.println(randomName.get());    // если выбирается 3 в диапазоне 0,5  - выводим это








    }
    static Optional<String> findName(String name, String[] names){
        for(String temp:names)
            if(temp.equals(name)) return Optional.of(name);
        return Optional.empty();




    }
}
