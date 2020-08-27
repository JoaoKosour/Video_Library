import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainLib
{
    //Usamos alguns métodos que podem gerar execções. Por isso damos o throw aqui.
    //Vamos ver com calma depois sobre isso.
    public static void main(String[] args) throws ParseException, FileNotFoundException
    {
        ArrayList<Person> peopleList = new ArrayList<>();
        ArrayList<Media> mediaList = new ArrayList<>();

        //Carrega arquivo como input stream
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(Util.PERSONFILE));
        //Redireciona a entrada padrão (não é exatamente necessário, mas pra garantir...)
        System.setIn(in);
        //Atribuindo ao scanner da classe Util a entrada atual
        Scanner scnr = new Scanner(System.in);

        InOut.readPerson(peopleList, scnr);

        //Mesma coisa, mas agora para mídias
        in = new BufferedInputStream(new FileInputStream(Util.MEDIAFILE));
        System.setIn(in);
        scnr = new Scanner(System.in);

        InOut.readMedia(mediaList, peopleList, scnr);
        //InOut.printPerson(peopleList);
        //InOut.printMedia(mediaList);
        InOut.printClone(mediaList);

    }

}
