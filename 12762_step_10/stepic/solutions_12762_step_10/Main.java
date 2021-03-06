package stepic.solutions_12762_step_10;

/**
 * Created by ipetrash on 11.09.2016.
 */


import java.util.*;

public class Main {

/*
Вам дан список ролей и сценарий пьесы в виде массива строчек.

Каждая строчка сценария пьесы дана в следующем виде:
Роль: текст

Текст может содержать любые символы.

Напишите метод, который будет группировать строчки по ролям, пронумеровывать их и возвращать результат в
виде готового текста (см. пример). Каждая группа распечатывается в следующем виде:

Роль:
i) текст
j) текст2
...
==перевод строки==

i и j -- номера строк в сценарии. Индексация строчек начинается с единицы, выводить группы следует в соответствии с
порядком ролей. Переводы строк между группами обязательны, переводы строк в конце текста не учитываются.

Заметим, что вам предстоит обработка огромной пьесы в 50 000 строк для 10 ролей – соответственно, неправильная
сборка результирующей строчки может выйти за ограничение по времени.

Обратите внимание еще на три нюанса:
    * имя персонажа может встречаться в строке более одного раза, в том числе с двоеточием;
    * роль, у которой нет реплик, тоже должна присутствовать в выходном файле;
    * в качестве перевода строки надо использовать символ '\n' (перевод строки в стиле UNIX).

Sample Input:
roles:
Городничий
Аммос Федорович
Артемий Филиппович
Лука Лукич

textLines:
Городничий: Я пригласил вас, господа, с тем, чтобы сообщить вам пренеприятное известие: к нам едет ревизор.
Аммос Федорович: Как ревизор?
Артемий Филиппович: Как ревизор?
Городничий: Ревизор из Петербурга, инкогнито. И еще с секретным предписаньем.
Аммос Федорович: Вот те на!
Артемий Филиппович: Вот не было заботы, так подай!
Лука Лукич: Господи боже! еще и с секретным предписаньем!

Sample Output:
Городничий:
1) Я пригласил вас, господа, с тем, чтобы сообщить вам пренеприятное известие: к нам едет ревизор.
4) Ревизор из Петербурга, инкогнито. И еще с секретным предписаньем.

Аммос Федорович:
2) Как ревизор?
5) Вот те на!

Артемий Филиппович:
3) Как ревизор?
6) Вот не было заботы, так подай!

Лука Лукич:
7) Господи боже! еще и с секретным предписаньем!

*/

    static
    private String printTextPerRole(String[] roles, String[] textLines) {
        // Словарь, ключом которого будет имя персонажа, а значением -- фразы.
        // LinkedHashMap -- словарь, сохраняющий порядок добавленных ключей, в отличии от HashMap
        Map<String, StringBuilder> roleByDialogs = new LinkedHashMap<>();

        for (String role : roles) {
            roleByDialogs.put(role, new StringBuilder());
        }

        for (int i = 0; i < textLines.length; i++) {
            // Разбор строки, чтобы получить имя персонажа и его диалог.
            String[] parts = textLines[i].split(":", 2);
            String name = parts[0];
            String text = parts[1];

            // Добавление фразы вида: "<номер>)<текст>\n"
            roleByDialogs.get(name).append(i + 1).append(")").append(text).append("\n");
        }

        StringBuilder builder = new StringBuilder();

        // Для каждой пары ключ-значение вызываем
        roleByDialogs.forEach(
            (role, dialogs) -> {
                builder.append(role).append(":\n")
                       .append(dialogs)
                       .append("\n");
            }
        );

        return builder.toString();
    }

    public static void main(String[] args) {
        String[] roles = {
            "Городничий",
            "Аммос Федорович",
            "Артемий Филиппович",
            "Лука Лукич",

            // Добавление роли, у которой нет диалогов
            "Вася Пупкин",
        };

        String[] textLines = {
            "Городничий: Я пригласил вас, господа, с тем, чтобы сообщить вам пренеприятное известие: к нам едет ревизор.",
            "Аммос Федорович: Как ревизор?",
            "Артемий Филиппович: Как ревизор?",
            "Городничий: Ревизор из Петербурга, инкогнито. И еще с секретным предписаньем.",
            "Аммос Федорович: Вот те на!",
            "Артемий Филиппович: Вот не было заботы, так подай!",
            "Лука Лукич: Господи боже! еще и с секретным предписаньем!",
        };

        String text = printTextPerRole(roles, textLines);
        System.out.println(text);
    }
}
