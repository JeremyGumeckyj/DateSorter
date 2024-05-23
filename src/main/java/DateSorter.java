import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DateSorter {

    /**
     * The implementation of this method should sort dates.
     * The output should be in the following order:
     * Dates with an 'r' in the month,
     * sorted ascending (first to last),
     * then dates without an 'r' in the month,
     * sorted descending (last to first).
     * For example, October dates would come before May dates,
     * because October has an 'r' in it.
     * thus: (2004-07-01, 2005-01-02, 2007-01-01, 2032-05-03)
     * would sort to
     * (2005-01-02, 2007-01-01, 2032-05-03, 2004-07-01)
     *
     * @param unsortedDates - an unsorted list of dates
     * @return the collection of dates now sorted as per the spec
     */

    public Collection<LocalDate> sortDates(List<LocalDate> unsortedDates) {
        List<LocalDate> datesWithR = unsortedDates.stream()
                .filter(date -> hasRInMonth(date))
                .sorted()
                .collect(Collectors.toList());

        List<LocalDate> datesWithoutR = unsortedDates.stream()
                .filter(date -> !hasRInMonth(date))
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        datesWithR.addAll(datesWithoutR);

        return datesWithR;
    }

    private boolean hasRInMonth(LocalDate date) {
        String monthName = date.getMonth().name().toLowerCase();
        return monthName.contains("r");
    }

    public static void main(String[] args) {
        DateSorter dateSorter = new DateSorter();

        List<LocalDate> unsortedDates = new ArrayList<>();
        unsortedDates.add(LocalDate.of(2004, 7, 1));
        unsortedDates.add(LocalDate.of(2005, 1, 2));
        unsortedDates.add(LocalDate.of(2007, 1, 1));
        unsortedDates.add(LocalDate.of(2032, 5, 3));
        unsortedDates.add(LocalDate.of(2023, 9, 15));
        unsortedDates.add(LocalDate.of(2020, 3, 10));

        Collection<LocalDate> sortedDates = dateSorter.sortDates(unsortedDates);
        sortedDates.forEach(System.out::println);
    }
}
