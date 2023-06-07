package calendar;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CalendarController {

    @GetMapping("/")
    public String showCalendar(Model model) {
        YearMonth yearMonth = YearMonth.now();
        LocalDate today = LocalDate.now();
        List<List<CalendarDay>> calendar = generateCalendar(yearMonth);

        model.addAttribute("month_name", yearMonth.getMonth().toString());
        model.addAttribute("year", yearMonth.getYear());
        model.addAttribute("calendar", calendar);
        model.addAttribute("today", today);

        return "index";
    }

    private List<List<CalendarDay>> generateCalendar(YearMonth yearMonth) {
        List<List<CalendarDay>> calendar = new ArrayList<>();
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();
        LocalDate currentDate = startDate;
        int rowNum = 0;

        while (currentDate.isBefore(endDate.plusDays(1))) {
            if (currentDate.getDayOfWeek().getValue() == 1 || calendar.isEmpty()) {
                calendar.add(new ArrayList<>());
            }

            List<CalendarDay> week = calendar.get(rowNum);
            week.add(new CalendarDay(currentDate.getDayOfMonth(), currentDate.getMonthValue(), currentDate.getYear()));

            currentDate = currentDate.plusDays(1);

            if (currentDate.getDayOfWeek().getValue() == 1) {
                rowNum++;
            }
        }

        return calendar;
    }
}