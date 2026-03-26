
import java.util.Scanner;

public class Date {

	private int month;
	private int day;
	private int year;

	public Date(int month, int day, int year) {
		this.month = month;
		this.day = day;
		this.year = year;
		
		if (!ValidDates()) {
			throw new IllegalArgumentException("Data Inválida");
		}
	}

	public int month() {
		return month;
	}

	public int day() {
		return day;
	}

	public int year() {
		return year;
	}

	public String toString() {
		String s = String.valueOf(month + "/" + day + "/" + year);
		return s;
	}

	private boolean Bissexto(int year) {
		if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))
			return true;
		return false;
	}

	private int DaysOfTheMonth(int month, int year) {
		if (month == 2) {
			return !Bissexto(year) ? 28 : 29;
		} else if ((month < 8 && month % 2 != 0) || (month >= 8 && month % 2 == 0)) {
			return 31;
		} else {
			return 30;
		}
	}

	public boolean before(Date other) {
		if (this.year < other.year) {
			return true;
		} else if ((this.year == other.year) && (this.month < other.month)) {
			return true;
		} else if ((this.year == other.year) && (this.month == other.month) && (this.day < other.day)) {
			return true;
		}
		return false;
	}

	public int daysSinceBeginYear() {
		int count = this.day;
		for (int i = 1; i < this.month; i++) {
			count += DaysOfTheMonth(i, this.year);
		}
		return count;
	}

	public int daysUntilEndYear() {
		int count = DaysOfTheMonth(this.month, this.year) - this.day;
		for (int i = this.month + 1; i <= 12; i++) {
			count += DaysOfTheMonth(i, this.year);
		}
		return count;
	}

	private int DaysInGapYears(Date start, Date end) {
		int IntermediateYearDays = 0;
		for (int i = start.year + 1; i < end.year; i++) {
			IntermediateYearDays += Bissexto(i) ? 366 : 365;
		}
		return start.daysUntilEndYear() + end.daysSinceBeginYear() + IntermediateYearDays;
	}

	public int daysBetween(Date other) {
		if (this.year == other.year) {
			return Math.abs(this.daysSinceBeginYear() - other.daysSinceBeginYear());
		}
		if (this.before(other)) {
			return DaysInGapYears(this, other);
		} else {
			return DaysInGapYears(other, this);
		}
	}

	private boolean ValidDates() {
		if (year < 1) {
			return false;
		}
		if (month < 1 || month > 12) {
			return false;
		}
		if (((month < 8 && month % 2 != 0) || (month >= 8 && month % 2 == 0)) && (day < 1 || day > 31)) {
			return false;
		}
		if (((month < 8 && month % 2 == 0) || (month >= 8 && month % 2 != 0)) && (day < 1 || day > 30)) {
			return false;
		}
		if (month == 2) {
			if (Bissexto(year)) {
				if (day < 1 || day > 29) {
					return false;
				}
			} else {
				if (day < 1 || day > 28) {
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int month1 = s.nextInt();
		int day1 = s.nextInt();
		int year1 = s.nextInt();
		Date d = new Date(month1, day1, year1);
		int month2 = s.nextInt();
		int day2 = s.nextInt();
		int year2 = s.nextInt();
		Date other = new Date(month2, day2, year2);

		System.out.println(d);
		System.out.println(other);
		
		System.out.println("");
		
		System.out.println(d.Bissexto(year1));
		System.out.println(other.Bissexto(year2));
		
		System.out.println("");

		System.out.println(d.DaysOfTheMonth(month1, year1));
		System.out.println(other.DaysOfTheMonth(month2, year2));

		System.out.println("");

		System.out.println(d.before(other));
		
		System.out.println("");


		System.out.println(d.daysSinceBeginYear());
		System.out.println(other.daysSinceBeginYear());

		System.out.println("");

		System.out.println(d.daysUntilEndYear());
		System.out.println(other.daysUntilEndYear());

		System.out.println("");

		System.out.println(d.daysBetween(other)); 

		s.close();
	}
}