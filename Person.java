import java.time.LocalDate;
import java.time.Period; 

public class Person{
    public final String NAME;
    public final String COUNTRY;
    public final LocalDate BIRTHDATE;
    public int AGE;
    
    public Person(String nome, String pais, String nascimento){
        this.NAME = nome;
        this.COUNTRY = pais;
        this.BIRTHDATE = LocalDate.parse(nascimento);
        
        Period diff = Period.between(this.BIRTHDATE, LocalDate.now());
        this.AGE = diff.getYears();
    }


    public String getAge(){
        return (BIRTHDATE.toString());
    }

    public String getName(){
        return NAME;
    }

    public String getCountry(){
        return COUNTRY;
    }

    public int getBirth(){
        return AGE;
    }


}