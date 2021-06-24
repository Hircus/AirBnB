import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class Main {
    public static void main(String[] args) {
        public static void main(String[] args) {
            AirBnB a = new AirBnB();

            LocalDate data1= LocalDate.EPOCH;
            LocalDate data2= LocalDate.now();
            System.out.println((Period.between(data1,data2).getYears()*12+ Period.between(data1,data2).getMonths())*30);
            System.out.println(LocalDate.now().toEpochDay());
            long p2 = ChronoUnit.DAYS.between(data1, data2);
            System.out.println(p2);


        }
    }
}
