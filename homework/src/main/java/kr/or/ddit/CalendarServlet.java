package kr.or.ddit;

import java.io.IOException;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/calendarServlet.do")
public class CalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String yearParam = request.getParameter("year");
		String monthParam = request.getParameter("month");
		String localeParam = request.getParameter("locale");
		
		Locale locale = Optional.ofNullable(localeParam)
			.map(lp->Locale.forLanguageTag(lp))
			.orElse(request.getLocale());
		
		int year = Optional.ofNullable(yearParam)
						.filter((yp)->yp.matches("\\d{4}"))
						.map((yp)->Integer.parseInt(yp))
						.orElse(Year.now().getValue());

		
		YearMonth targetMonth = Optional.ofNullable(monthParam)
									.filter((mp)->mp.matches("[1-9]|1[0-2]"))
									.map((mp)->Integer.parseInt(mp))
									.map((m)->YearMonth.of(year, m))
									.orElse(YearMonth.now());
		YearMonth beforeMonth = targetMonth.minusMonths(1);
		YearMonth nextMonth = targetMonth.plusMonths(1);
		
		String.format(locale, "%1$tY, %1$tB", targetMonth);
		
		final String OPTPTRN = "<option value='%s' %s>%s</option>";
		
		Stream.of(Month.values())
		.map((m)->{
			String selected = m.equals(targetMonth.getMonth())?"selected":"";
			String display = m.getDisplayName(TextStyle.FULL, locale);
			return String.format(OPTPTRN, m.getValue(), selected, display);
			})
		.collect(Collectors.joining("\n"));

		
           Stream.of(Locale.getAvailableLocales())
           .filter((l)->!l.getDisplayName(locale).isEmpty())
               .map((l)->{
                  String selected = l.equals(locale) ? "selected" : "";
                  return String.format(OPTPTRN, l.toLanguageTag(), selected, l.getDisplayName(l));
               }).collect(Collectors.joining("\n"));
           
           
           request.setAttribute("beforeMonth", beforeMonth);
           request.setAttribute("nextMonth", nextMonth);
           request.setAttribute("targetMonth", targetMonth);
           request.setAttribute("locale", locale);
           
           
           
	}

}
