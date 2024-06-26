package JavaRush.tasks.Quest3.task22.task2210;

import java.util.StringTokenizer;

/*
StringTokenizer

Используя StringTokenizer разделить query на части по разделителю delimiter.

Пример
getTokens("level22.lesson13.task01", ".")

Возвращает
{"level22", "lesson13", "task01"}


Требования:
1. Метод getTokens должен использовать StringTokenizer.
2. Метод getTokens должен быть публичным.
3. Метод getTokens должен принимать два параметра типа String.
4. Массив типа String возвращенный методом getTokens должен быть заполнен правильно(согласно условию задачи).

*/
public class Solution {
    public static void main(String[] args) {
        getTokens("level22.lesson13.task01", ".");
    }
    public static String [] getTokens(String query, String delimiter) {
        StringTokenizer tokenizer = new StringTokenizer(query,delimiter);
        String[] arr = new String[tokenizer.countTokens()];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = tokenizer.nextToken();
        }

        return arr;
    }
}
