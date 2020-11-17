package es.unileon.prg1.date;

public class Date {
	private int day;
	private int month;
	private int year;
	
	public Date (int day, int month, int year) throws DateException {
		//this.month = month;
		this.setMonth(month);
		//this.day = day;
		this.setDay(day);
		//this.year = year;
		this.setYear(year);
	}

	public Date(Date date){ //Constructor que copia una fecha ya creada, y por tanto valida
		this.month=date.month;
		this.day=date.day;
		this.year=date.year;
	}
	
	public void setDay(int day) throws DateException {
		if ( day < 1 || day > this.getDaysOfMonth() ) {
			throw new DateException("Date error: Day " + day + " of month " + this.month + " not valid");			
		}
		this.day = day;
	}
	
	public void setMonth (int month) throws DateException {
		if ( month < 1 || month > 12) {
			throw new DateException("Date error: Month " + month + " not valid");
		}
		this.month = month;
	}
	
	public void setYear (int year) {
		this.year = year;
	}

	public int getYear(){
		return this.year;
	}
	
	public int getMonth(){
		return this.month;
	}

	public int getDay(){
		return this.day;
	}
	
	public boolean isSameYear(Date date){
		boolean isSameYear=false;
		if(this.year==date.getYear()){
			isSameYear=true;
		}
		return isSameYear;
	}

	public boolean isSameMonth(Date date){
		boolean isSameMonth=false;
		if(this.month==date.getMonth()){
			isSameMonth=true;
		}
		return isSameMonth;
	}

	public boolean isSameDay(Date date){
		boolean isSameDay=false;
		if(this.day==date.getDay()){
			isSameDay=true;
		}
		return isSameDay;
	}

	public boolean isSame(Date date){
		boolean isSame=false;
		if(isSameYear(date)&&isSameMonth(date)&&isSameDay(date)){
			isSame=true;
		}
		return isSame;
	}

	public boolean isSameYearWithoutIf(Date date){
		return this.year==date.getYear();
	}

	public boolean isSameMonthWithoutIf(Date date){
		return this.month==date.getMonth();
	}

	public boolean isSameDayWithoutIf(Date date){
		return this.day==date.getDay();
	}

	public boolean isSameWithoutIf(Date date){
		return (this.year==date.getYear()&&this.month==date.getMonth()&&this.day==date.getDay());
	}
	
	private int getDaysOfMonth() {
		int numDays;
		
		numDays = 0;
		switch (this.month) {
		case 1: //next
		case 3: //next
		case 5: //next
		case 7: //next
		case 8: //next
		case 10: //next
		case 12:
			numDays = 31;
			break;
		case 4: //next
		case 6: //next
		case 9: //next
		case 11:
			numDays = 30;
			break;
		case 2:
			numDays = 28;
			break;			
		}
		
		return numDays;
	}

	public String getNameOfMonth(){
		String name="";
		switch(this.month){
			case 1: name="Enero";
				break;
			case 2: name="Febrero";
				break;
			case 3: name="Marzo";
				break;
			case 4: name="Abril";
				break;
			case 5: name="Mayo";
				break;
			case 6: name="Junio";
				break;
			case 7: name="Julio";
				break;
			case 8:name="Agosto";
				break;
			case 9: name="Septiembre";
				break;
			case 10: name="Octubre";
				break;
			case 11: name="Noviembre";
				break;
			case 12: name="Diciembre";
				break;
		}
		return name;
	}

	public String seasonOfMonth(){ //Consideramos que si la estacion empieza el 21 se cuenta el mes siguiente
		String season="";
		switch(this.month){
			case 1: //next
			case 2: //next
			case 3: season="Invierno";
				break;
			case 4: //next
			case 5: //next
			case 6: season="Primavera";
				break;
			case 7: 
			case 8: 
			case 9: season="Verano";
				break;
			case 10: //next
			case 11: //next
			case 12: season="Otoño";
				break;
		}
		return season;
	}

	public int monthsUntilEndOfYear(){ //Sin contar el mes actual
		return 12-this.month;
	}

	public String datesUntilEndOfMonth(){
		StringBuffer dates= new StringBuffer();
		for(int i=this.day+1; i<=getDaysOfMonth(); i++){
			dates.append(i+"/"+this.month+"/"+this.year+"\n");
		}
		return dates.toString();
	}

	public String monthsWithSameDays(){
		StringBuffer months= new StringBuffer();
		Date aux= new Date(this);
		for(int i=1; i<=12; i++){
			try{
				aux.setMonth(i);
			}catch (DateException e){}
			if(this.getDaysOfMonth()==aux.getDaysOfMonth()){
				months.append(aux.getNameOfMonth()+"\n");
			}
		}
		return months.toString();
	}

	public int daysSinceStartOfYear(){
		int contador=0;
		Date aux=new Date(this);
		for(int i=1; i<=this.month-1; i++){
			try{
			aux.setMonth(i);
			}catch(DateException e){}
			contador=contador+aux.getDaysOfMonth();
		}
		contador=contador+this.day;
		return contador;
	}

	public int attemptsForRandomDate(){
		Date randomDate= new Date (this);
		int attempts=0;
		try{
			randomDate.setDay(this.day-1);
		}catch (DateException e){}
		do{
			try{
				randomDate=new Date((int)Math.floor(Math.random()*31+1), (int)Math.floor(Math.random()*12), this.year);
				attempts++;
			}catch (DateException e){}
		}while(!(isSame(randomDate)));
		return attempts;
	}

	public String getDayOfTheWeek(int firstDayOfYear){ //Recibe el primer dia de la semana del año
		int dayOfTheWeek=firstDayOfYear;
		String day="";
		for(int i=1;i<=daysSinceStartOfYear(); i++){
			dayOfTheWeek++;
		}
		dayOfTheWeek=dayOfTheWeek%7;
		switch(dayOfTheWeek){
			case 1: day="Lunes";
				break;
			case 2: day="Martes";
				break;
			case 3: day="Miercoles";
				break;
			case 4: day="Jueves";
				break;
			case 5: day="Viernes";
				break;
			case 6: day="Sabado";
				break;
			case 7: day="Domingo";
				break;
		}
		return day;
	}
	
	public String toString() {
		return this.day + "/" + this.month + "/" + this.year;
	}

}
