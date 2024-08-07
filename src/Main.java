import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("Pendanaan", "Terproteksi", "Untuk", "Dampak", "Berarti");
        var groupResult = grouping(words);
        var sortResult = sortResult(groupResult);
        System.out.println(sortResult);
    }

    private static HashMap<Character, Integer> grouping(List<String> words) {
        HashMap<Character, Integer> result = new HashMap<>();
        for (int i = 0; i < words.size(); i++) {
            var word = words.get(i);
            for (int j = 0; j < word.length(); j++) {
                result.merge(word.charAt(j), 1, Integer::sum);
            }
        }
        return result;
    }

    private static String sortResult(HashMap<Character, Integer> groupResult) {
        StringBuilder sbResult = new StringBuilder();
        HashMap<Character, Integer> result = groupResult
                .entrySet().stream()
                .sorted((a, b) -> {
                    int valueCompare = b.getValue().compareTo(a.getValue());
                    if (valueCompare != 0) {
                        return valueCompare;
                    } else {
                        return Character.compare(a.getKey(), b.getKey());
                    }
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        for (Character key : result.keySet()) {
            sbResult.append(key);
        }

        return sbResult.toString();
    }
}