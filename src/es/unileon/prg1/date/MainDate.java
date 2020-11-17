package es.unileon.prg1.date;

public class MainDate {

	public static void main(String[] args) {
		Date today;
		Date tomorrow;
		
		try {
			today = new Date(17,11,2020);
			System.out.println(today);
			System.out.println(today.getDayOfTheWeek(3));
			tomorrow= new Date(11,11,2020);
			System.out.println(tomorrow);
			System.out.println("Dia: "+today.isSameDay(tomorrow)+"Mes: "+today.isSameMonth(tomorrow)+"Año: "+today.isSameYearWithoutIf(tomorrow)+"Todo: "+today.isSameWithoutIf(tomorrow));
		} catch (DateException e) {
			System.out.println(e.getMessage());
		}
		
	}
	

}
