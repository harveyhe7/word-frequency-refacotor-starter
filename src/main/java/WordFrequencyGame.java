import java.util.*;

public class WordFrequencyGame {
    public String getResult(String inputStr) {

        String[] words = getSplitWords(inputStr);
        if (words.length == 1) {
            return inputStr + " 1";
        } else try {
            //split the input string with 1 to n pieces of spaces
            List<Input> frequencies = getStringPair(words);
            return formatAndSortedWords(frequencies);
        } catch (Exception e) {
            return "Calculate Error";
        }
    }

    private String formatAndSortedWords(List<Input> frequencies) {
        //get the map for the next step of sizing the same word

        Map<String, List<Input>> map = getListMap(frequencies);

        List<Input> list = new ArrayList<>();
        for (Map.Entry<String, List<Input>> entry : map.entrySet()) {
            Input input = new Input(entry.getKey(), entry.getValue().size());
            list.add(input);
        }
        frequencies = list;

        frequencies.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

        StringJoiner joiner = new StringJoiner("\n");
        for (Input w : frequencies) {
            String s = w.getValue() + " " + w.getWordCount();
            joiner.add(s);
        }
        return joiner.toString();
    }

    private List<Input> getStringPair(String[] words) {
        return Arrays.stream(words)
                .map(s -> new Input(s, 1))
                .toList();
    }

    private String[] getSplitWords(String inputStr) {
        String[] words = inputStr.split("\\s+");
        return words;
    }


    private Map<String, List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input : inputList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getValue(), arr);
            } else {
                map.get(input.getValue()).add(input);
            }
        }


        return map;
    }


}