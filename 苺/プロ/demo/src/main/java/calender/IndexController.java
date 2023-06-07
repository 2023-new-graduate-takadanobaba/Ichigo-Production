@Controller
public class CalendarController {
    
    @GetMapping("/")
    public String showCalendar(Model model) {
        // カレンダーのデータを生成するロジックを追加します
        
        List<CalendarDay> calendarDays = generateCalendar();
        
        model.addAttribute("calendarDays", calendarDays);
        
        return "calendar";
    }
    
    private List<CalendarDay> generateCalendar() {
        // カレンダーデータの生成ロジックを実装します
        
        List<CalendarDay> calendarDays = new ArrayList<>();
        
        // 例として、1ヶ月分のデータを生成する処理を追加します
        YearMonth yearMonth = YearMonth.now();
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();
        
        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            boolean hasTransaction = checkTransaction(currentDate); // その日に取引があるかチェックするメソッド
            CalendarDay calendarDay = new CalendarDay(currentDate.getDayOfMonth(), hasTransaction);
            calendarDays.add(calendarDay);
            currentDate = currentDate.plusDays(1);
        }
        
        return calendarDays;
    }
    
    // 取引があるかどうかをチェックするメソッドやその他の必要なメソッドを追加します
}