
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
*  промежуточные:
*  filter
*  map
*  distinct
*  boxed
*  sorted
*  range
*
*  терминальные:
*  forEach
*  allMath
*  anyMatch
*  reduce
*
*  создание стримов:
*  Arrays.stream()
*  list.stream()
*  IntStream.
*
*  Optinal:
*  Optional.of()
*  Optional.empty()
*  Optional<String> o
*  o.map().orElse()
*
* stream api методы:
* reduce
*
*
*
*
*
* */


public class Main {
    static Random randomObject = new Random();


    public static void main(String[] args) {

        int[] IntegerMass = new int[10_000];

        IntegerMass = fillMassWithRandomNums(IntegerMass);

        Arrays.stream(IntegerMass)
                .filter(a->a%2==0)
                .map(a->a*2)
                .distinct()
                .forEach(System.out::println);       // вывели четные уникальные числа умноженные на 2

        if(Arrays.stream(IntegerMass)
                .allMatch(a->a==0)){
            System.out.println("невероятный случай");
        }
        if(Arrays.stream(IntegerMass)
                .anyMatch(a->a==0)){
            System.out.println("обычный случай");
        }

        System.out.println(Arrays.stream(IntegerMass)
                .reduce((a,e)->a*e)
                .getAsInt());         // вывели произведение всех чисел

        Map<Boolean,List<Integer>> mapOfNumberTypes = evenAndNotEven(IntegerMass);    // разделили числа на четные и нечетные

        List<Integer> Sortedlist = Arrays.stream(IntegerMass)
                .boxed()
                .sorted()
                .collect(Collectors.toList());        // собрали массив в отсортированный лист


        Optional<String> foundName = findNameInMassive("вика",new String[]{"настя","оля","вика"});

        System.out.println(foundName
                .map(a->Character.toUpperCase(a.charAt(0))+a.substring(1))
                .orElse("нет такого имени"));


        Predicate<Integer> isEvenNumber = x -> x % 2==0;
        List<Integer> integerList = new ArrayList<>(Arrays.stream(IntegerMass)
                .filter(isEvenNumber::test)                       //  добавляем в лист только четные числа
                .boxed()
                .toList());

        Consumer<Integer> printEvenNums = x -> System.out.println("Even num:"+x);
        integerList.stream().forEach(printEvenNums);                 //  выводим эти четные числа

        Function<Integer, String> convertToString = g->String.valueOf(g);
        List<String> listSTRING = Arrays.stream(IntegerMass)
                .boxed()              //  конвертируем числа в строки и добавляем в новый лист строк
                .map(convertToString)
                .toList();

        IntegerMass = fillMassWithNegativeAnalogs(IntegerMass);
                                                                 // заменяем все числа массива на отрицительные аналоги


        Supplier<String> randomName = () -> {
            if(randomObject.nextInt(6)==3){
                return "выбралось три";
            }
            return "";
        };

        IntStream.range(0,60)
                .forEach(a->System.out.println(randomName.get()));  // если выбирается 3 в диапазоне 0,5  - выводим это

        System.out.println(joinMassOfNumbers(IntegerMass));    //  выводим строку представляющую объединение всех чисел

        Set<Integer> setOfNums = getSetOfUniqueNums(IntegerMass);
        System.out.println(setOfNums);         //  получаем и выводим сет уникальных значений из нашего массива

        Map<Integer,List<Integer>> groupedNums = groupingByUniqueNums(fillMassWithRandomNums(IntegerMass));
        groupedNums
                .entrySet()
                .stream()
                .forEach(e-> System.out.println(e.getKey()+": "+e.getValue()));    // получаем листы из повторяющихся значений и выводим

        Map<Integer, String> map = mapOfEvenAndOdd(IntegerMass);
        groupedNums
                .entrySet()
                .stream()
                .forEach(e-> System.out.println(e.getKey()+": "+e.getValue()));   //  выводим число в качестве ключа, и строку "even/odd" в качестве значение

        System.out.println(findSumOfNums(IntegerMass));        //   вывели сумму всех чисел через Collectors.reducing

        System.out.println(findAveraging(IntegerMass));    // вывели среднее число всех чисел массива через Collectors.averagingInt

        System.out.println(findSumOfNums2(IntegerMass));      //   вывели сумму всех чисел через Collectors.summingInt

        IntSummaryStatistics ins = findAllData(IntegerMass);
        System.out.println("sum:"+ins.getSum()
                + ", average: "+ins.getAverage()
                + ", max:"+ins.getMax()                        // вывели данные по массиву чисел через Collectors.summarizing
                +", min:"+ins.getMin()+
                ", count:" +ins.getCount());


    }

     static Optional<String> findNameInMassive(String name, String[] names){
        if(Arrays.stream(names)
                .anyMatch(name::equals)) return Optional.of(name);
        return Optional.empty();

    }
    static int[] fillMassWithRandomNums(int[] mass){
        return Arrays.stream(mass)
                .map(a->randomObject.nextInt(60))
                .toArray();

    }
    static int[] fillMassWithNegativeAnalogs(int[] mass){
        UnaryOperator<Integer> mapToNegativeInt = x -> -x;
        return Arrays.stream(mass)
                .map(mapToNegativeInt::apply)
                .toArray();
    }
    static Map<Boolean, List<Integer>> evenAndNotEven (int[] mass){
        return Arrays.stream(mass)
                .boxed()
                .collect(Collectors.partitioningBy(a->a%2==0));
    }
    static String joinMassOfNumbers(int[] mass){
        return Arrays.stream(mass)
                .boxed().map(String::valueOf)
                .collect(Collectors.joining(", "));
    }
    static Set<Integer> getSetOfUniqueNums(int[] mass){
        return Arrays.stream(mass)
                .boxed()
                .collect(Collectors.toSet());
    }
    static Map<Integer,List<Integer>> groupingByUniqueNums(int[] mass){
        return Arrays.stream(mass)
                .boxed()
                .collect(Collectors.groupingBy(a->a));

    }
    static Map<Integer,String> mapOfEvenAndOdd(int[] mass){
        return Arrays.stream(mass)
                .boxed()
                .collect(Collectors.toMap(Function.identity(),a->a%2==0?"even":"odd"));

    }
    static int findSumOfNums(int[] mass){
        return Arrays.stream(mass)
                .boxed()
                .collect(Collectors.reducing(0,(a,b)->a+b));
    }
    static double findAveraging(int[] mass){
        return Arrays.stream(mass)
                .boxed().collect(Collectors.averagingInt(a->a));

    }
    static long findSumOfNums2(int[] mass){
        return Arrays.stream(mass)
                .boxed().collect(Collectors.summingInt(a->a));

    }
    static IntSummaryStatistics findAllData(int[] mass){
        return Arrays.stream(mass)
                .boxed()
                .collect(Collectors.summarizingInt(a->a));
    }


}



