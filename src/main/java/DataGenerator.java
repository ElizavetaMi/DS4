import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataGenerator {
    public static String generateDate(int daysToAdd) {
        return LocalDate.now().plusDays(daysToAdd).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
