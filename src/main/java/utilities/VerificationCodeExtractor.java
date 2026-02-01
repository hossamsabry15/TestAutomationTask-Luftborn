package utilities;

import java.util.regex.*;

public class VerificationCodeExtractor {

    public static String extractCode(String emailHtml) {
        Pattern pattern = Pattern.compile("Your confirmation code is (\\d{6})");
        Matcher matcher = pattern.matcher(emailHtml);
        if (matcher.find()) {
            return matcher.group(1);
        }
        throw new RuntimeException("Verification code not found in email content.");
    }
}
