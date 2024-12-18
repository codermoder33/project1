import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class Main{



    public static void main(String[] args) {
        Random randomObject = new Random();
        int[] IntegerMass = new int[10_000];
        for(int g=0;g<IntegerMass.length;g++){
            IntegerMass[g]=randomObject.nextInt(60);
        }
        Arrays.stream(IntegerMass).filter(a->a%2==0).map(a->a*2).distinct().forEach(System.out::println); // вывели четные уникальные числа умноженные на 2
        if(Arrays.stream(IntegerMass).allMatch(a->a==0)) System.out.println("невероятный случай");
        if(Arrays.stream(IntegerMass).anyMatch(a->a==0)) System.out.println("обычный случай");

        System.out.println(Arrays.stream(IntegerMass).reduce((a,e)->a*e).getAsInt());  // вывели произведение всех чисел

        Map<Boolean,List<Integer>> mapOfNumberTypes = Arrays.stream(IntegerMass).boxed().collect(Collectors.partitioningBy(a->a%2==0));  // разделили числа на четные и нечетные
        List<Integer> Sortedlist = Arrays.stream(IntegerMass).boxed().sorted().collect(Collectors.toList()); // собрали массив в отсортированный лист


        Optional<String> foundName = findNameInMassive("Вика",new String[]{"Настя","Оля","Вика"});
        if(foundName.isPresent()) System.out.println("имя "+foundName.get()+" есть");
        System.out.println(foundName.orElse("Нет такого имени"));



        List<Integer> integerList = new ArrayList<>();
        Predicate<Integer> isEvenNumber = x -> x % 2==0;
        for(int g=0;g<10_000;g++)
            if(isEvenNumber.test(IntegerMass[g])) integerList.add(IntegerMass[g]);         //  добавляем в лист только четные числа

        Consumer<Integer> printEvenNums = x -> System.out.println("Even num:"+x);    //  выводим эти четные числа
        for(int h:integerList)
         printEvenNums.accept(h);

        Function<Integer, String> convertToString = g->String.valueOf(g);
        List<String> listSTRING = new ArrayList<>();
        for(int h:IntegerMass) listSTRING.add(convertToString.apply(h));    //  конвертируем числа в строки и добавляем в новый лист строк

        UnaryOperator<Integer> mapToNegativeInt = x -> -x;
        for(int h=0;h<IntegerMass.length;h++) IntegerMass[h] = mapToNegativeInt.apply(IntegerMass[h]);     // заменяем все числа массива на отрицительные аналоги

        Supplier<String> randomName = () -> {
            if(randomObject.nextInt(6)==3) return "выбралось три";
            return "";

        };

        for(int g=0;g<60;g++)
            System.out.println(randomName.get());    // если выбирается 3 в диапазоне 0,5  - выводим это








    }
    static Optional<String> findNameInMassive(String name, String[] names){
        for(String temp:names)
            if(temp.equals(name)) return Optional.of(name);
        return Optional.empty();




    }
}

