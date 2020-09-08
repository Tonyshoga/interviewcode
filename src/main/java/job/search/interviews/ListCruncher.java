package job.search.interviews;

import java.util.*;
import java.util.stream.Collectors;

public class ListCruncher {
    private final List<List<String>> listOfWordList;

    public ListCruncher(List<List<String>> listOfWordList) {
        this.listOfWordList = listOfWordList;
    }

    private List<String> mergeWordsListToSingleList() {
        return listOfWordList.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private Map<String, Long> getWordsOccurrences(List<String> tempList) {
        return tempList.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
    }

    public List<String> wordsWithMoreOccurrenceCount() {
        List<String> result = new ArrayList<>();

        List<String> tempList = mergeWordsListToSingleList();

        Map<String, Long> wordCounts = getWordsOccurrences(tempList);
        Map<String, Long> collect = wordCounts.entrySet().stream()
                .filter(x -> x.getValue()>1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        result = collect.keySet().stream().collect(Collectors.toList());
        return result;
    }

    public List<String> getUniqueWordsAcrossList() {
        List<String> result = new ArrayList<>();

        List<String> tempList = mergeWordsListToSingleList();

        Map<String, Long> wordCounts = getWordsOccurrences(tempList);
        Map<String, Long> collect = wordCounts.entrySet().stream()
                .filter(x -> x.getValue()==1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        result = collect.keySet().stream().collect(Collectors.toList());
        return result;
    }

    public List<String> getTopFiveFrequentWordsInList() {
        List<String> tempList = mergeWordsListToSingleList();
        Map<String, Long> wordCountsMap = getWordsOccurrences(tempList);

        Map<String,Long> sortedMap = wordCountsMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        List<String> result = sortedMap.keySet().stream().collect(Collectors.toList());
        Collections.reverse(result);
        return result.size() > 5 ? result.subList(0,5) : result;
    }

    public static void main(String[] args) {
        List<String> listOfDaysOfWeek = Arrays.asList("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday");
        List<String> listOfWeekDays = Arrays.asList("Tuesday","Wednesday","Friday");
        List<String> listOfWeekends = Arrays.asList("Saturday");
        List<String> listOfDaysOff = Arrays.asList("Friday","Tuesday");
        List<List<String>> listOfWords = new ArrayList<>();
        listOfWords.add(listOfDaysOff);
        listOfWords.add(listOfWeekDays);
        listOfWords.add(listOfWeekends);
        listOfWords.add(listOfDaysOfWeek);
        ListCruncher listCruncher = new ListCruncher(listOfWords);
        System.out.println(String.format("input list of size (%s)",listOfWords.size()));
        System.out.println(listOfWords);
        System.out.println("====================================================================================");
        System.out.println(">>>>>>>>---list of words with more than one occurrence across lists--------<<<<<<<");
        System.out.println(listCruncher.wordsWithMoreOccurrenceCount());
        System.out.println(">>>>>>>>-------------------------------------------------------------------<<<<<<<");
        System.out.println("");
        System.out.println(">>>>>>>>---Total count of unique words across lists--------<<<<<<<");
        System.out.println(listCruncher.getUniqueWordsAcrossList().size());
        System.out.println(">>>>>>>>-------------------------------------------------------------------<<<<<<<");
        System.out.println("");
        System.out.println(">>>>>>>>---Top 5 words across List-------<<<<<<<");
        System.out.println(listCruncher.getTopFiveFrequentWordsInList());
        System.out.println(">>>>>>>>-------------------------------------------------------------------<<<<<<<");
        System.out.println("");
    }
}
