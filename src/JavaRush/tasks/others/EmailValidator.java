package JavaRush.tasks.others;

import java.util.regex.*;

public class EmailValidator {
    private Pattern pattern;
    private Matcher matcher;

    // XML тэг в формате <xxx></xxx>
    String xmlRegexp = "^<([a-z]+)([^>]+)*(?:>(.*)<\\/\\1>|\\s+\\/>)$";

    // email адрес в формате xxx@xxx.xxx (регистр букв игнорируется)
    //Pattern.CASE_INSENSITIVE
    String emailRegexp2 ="^([a-z0-9_\\.-]+)@([a-z0-9_\\.-]+)\\.([a-z\\.]{2,6})$";

    // будем искать URL
    String urlRegexp = "^(https?:\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([\\/\\w \\.-]*)*\\/?$";

    // IP адрес
    String ipRegexp = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public EmailValidator() {
        this.pattern = Pattern.compile(EMAIL_PATTERN);
    }

    public boolean validate(final String hex) {
        matcher = pattern.matcher(hex);

        return matcher.matches();
    }

    public static void main(String[] args) {
        String[] emails = {
                "alex@yandex.ru",
                "alex-27@yandex.com",
                "alex.27@yandex.com",
                "alex111@devcolibri.com",
                "alex.100@devcolibri.com.ua",
                "alex@1.com",
                "alex@gmail.com.com",
                "alex+27@gmail.com",
                "alex-27@yandex-test.com",
                "devcolibri",
                "alex@.com.ua",
                "alex123@gmail.a",
                "alex123@.com",
                "alex123@.com.com",
                ".alex@devcolibri.com",
                "alex()*@gmail.com",
                "alex@%*.com",
                "alex..2013@gmail.com",
                "alex.@gmail.com",
                "alex@devcolibri@gmail.com",
                "alex@gmail.com.1ua"
        };

        EmailValidator emailValidator = new EmailValidator();

        for (String email: emails){
            System.out.println(emailValidator.validate(email));
        }
    }
}
