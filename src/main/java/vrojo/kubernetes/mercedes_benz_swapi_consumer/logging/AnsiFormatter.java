package vrojo.kubernetes.mercedes_benz_swapi_consumer.logging;

import lombok.extern.slf4j.Slf4j;
import org.fusesource.jansi.Ansi;

import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.fusesource.jansi.Ansi.ansi;

/**
 * @author valentin.rojo
 */
@Slf4j
public class AnsiFormatter {

    public static String randomlyColorizeString(String uncoloredString){
        final int min = 0;
        final int max = Ansi.Color.values().length - 1;

        final int randomInt = ThreadLocalRandom.current().nextInt(min, max);
        final Ansi.Color randomColor = Ansi.Color.values()[randomInt];

        return ansi()
                .fg(randomColor)
                .a(uncoloredString)
                .reset()
                .toString();
    }

    public static String removeAnsiColoring(final String colorizedString){
        log.info("Colorized String: {}", colorizedString);

        final char ansiColoringStart = (char) 033;
        final String ansiColoringStartString = ansiColoringStart + "\\[.*m";
        final String ansiColoringEndString = ansiColoringStart + "\\[m";

        final Pattern ansiColorCodePattern = Pattern.compile(ansiColoringStartString + "(.*)" + ansiColoringEndString, Pattern.CASE_INSENSITIVE);
        final Matcher ansiColorCodeMatcher = ansiColorCodePattern.matcher(colorizedString);

        if(ansiColorCodeMatcher.find()){
            final String uncolorizedString = ansiColorCodeMatcher.group(1);

            log.info("Uncolored String: {}", uncolorizedString);

            return uncolorizedString;
        }

        log.info("No ANSI color was removed from the String");

        return colorizedString;
    }

    public static String removeAnsiColoringFromRequestId(String colorizedRequestId){
        final String requestId = removeAnsiColoring(colorizedRequestId);

        if(requestId.length() != 32){
            log.warn("The requestId should be 32 characters long, but it is {}!", requestId.length());
        }

        return requestId;
    }
}
