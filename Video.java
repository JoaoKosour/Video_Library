import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Video extends Media{
    private static int counter = 0;

    private final Diretor director;
    private final ArrayList<Actor> actors;
    private final int id;

    private int length;
    private Logger logger;


    public Video(String name, Diretor director, Util.ageRatingsEnum ageRating, 
                ArrayList<Util.genresEnum> genres, ArrayList<Actor> actors){
        super(name, ageRating, genres);

        logger = Logger.getLogger(Video.class.getName());

        this.director = director;
        {
            if(actors == null || actors.size() <= 0){
                logger.log(Level.WARNING, "Não foi passado um ator!");
                this.actors = null;
            }
            else{
                this.actors = new ArrayList<>();
                if(actors.size() > Util.MAXGENRES)
                    logger.log(Level.WARNING, "Mais de 3 atores foram passados!");

                for (int i = 0; (i < Util.MAXACTORS) && (i < actors.size()); ++i)
                    this.actors.add(actors.get(i));
            }
        }

        this.id = counter++;
        setLength(-1);
    };

    Video(Video copy){
        super(copy);
        id = copy.id;
        actors = copy.actors;
        director = copy.director;
    }

    public ArrayList<Actor> getActors(){
        ArrayList<Actor> copy = (ArrayList<Actor>) Util.CopyArray(this.actors);
        return copy;
    }

    public int getLength(){
        if(length < 0)
            logger.log(Level.WARNING, "Tentando acessar uma duração não definida!");
        return length;
    }

    public int getId() {return id;}
    public void setLength(int length) {this.length = length;}
    public Diretor getDirector(){return director;}
}

