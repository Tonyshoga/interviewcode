package job.search.interviews;


import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ListCruncherTest {

    ListCruncher tut;

    List<String> listOfDaysOfWeek = Arrays.asList("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday");
    List<String> listOfWeekDays = Arrays.asList("Tuesday","Wednesday","Friday");
    List<String> listOfWeekends = Arrays.asList("Saturday");
    List<String> listOfDaysOff = Arrays.asList("Friday","Tuesday");

    @Before
    public void setUp(){
    }

    @After
    public void tearDown(){}

    private List<List<String>> createListOfWords() {
        List<List<String>> listOfWords = new ArrayList<>();
        listOfWords.add(listOfDaysOff);
        listOfWords.add(listOfWeekDays);
        listOfWords.add(listOfWeekends);
        listOfWords.add(listOfDaysOfWeek);
        return listOfWords;
    }

    @Test
    public void acceptListAsParameter(){
        List<List<String>> listOfWords = createListOfWords();
        tut = new ListCruncher(listOfWords);
    }

    @Test
    public void givenAListOfListOfwordsThenReturnWordsWithMoreThanOneOccurrence(){
        List<List<String>> listOfWords = createListOfWords();
        tut = new ListCruncher(listOfWords);
        List<String> result = tut.wordsWithMoreOccurrenceCount();
        Assert.assertTrue(result.contains("Tuesday"));
        Assert.assertTrue(result.contains("Wednesday"));
        Assert.assertTrue(result.contains("Friday"));
        Assert.assertTrue(result.contains("Saturday"));
        Assert.assertThat(result.contains("Monday"),CoreMatchers.is(false));
        Assert.assertThat(result.contains("Thursday"),CoreMatchers.is(false));
    }

    @Test
    public void givenAListOfListOfWordsThenReturnTotalCountofUniqueWordsAcrossAllLists(){
        List<List<String>> listOfWords = createListOfWords();
        tut = new ListCruncher(listOfWords);
        List<String> result = tut.getUniqueWordsAcrossList();
        Assert.assertTrue(result.size()==3);
    }

    @Test
    public void givenAListOfListOfWordsThenReturnTopFiveFrequentWordsInAllList(){
        List<List<String>> listOfWords = createListOfWords();
        tut = new ListCruncher(listOfWords);
        List<String> result = tut.getTopFiveFrequentWordsInList();
        Assert.assertTrue(result.size()<=5);
        Assert.assertTrue(result.get(0).equalsIgnoreCase("Tuesday"));
    }
}