import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Media{
    private final String name;
    private final Util.ageRatingsEnum ageRating;
    private final ArrayList<Util.genresEnum> genres;
    private int year, nViews, nUserRatings;
    private float userRating;
    private Logger logger;

    public Media(String name, Util.ageRatingsEnum ageRating, ArrayList<Util.genresEnum> genres){
        this.name = name;
        this.ageRating = ageRating;

        logger = Logger.getLogger(Media.class.getName());

        {
            if(genres == null || genres.size() <= 0){
                logger.log(Level.WARNING, "Não foi passado um gênero!");
                this.genres = null;
            }

            else{
                this.genres = new ArrayList<>();
                if(genres.size() > Util.MAXGENRES)
                    logger.log(Level.WARNING, "Mais de 3 gêneros foram passados!");
                for (int i = 0; (i < Util.MAXGENRES) && (i < genres.size()); ++i)
                    this.genres.add(genres.get(i));
            }
        }
        nUserRatings = 0;
        userRating = 0;
        year = GregorianCalendar.getInstance().get(Calendar.YEAR);
    }



    //professor, eu nao fiz novas instancias desses atributos ja que eles sao final
    Media(Media copy){
      name = copy.name;
      ageRating = copy.ageRating;
      genres = copy.genres;
      year = copy.year;
      nViews = copy.nViews;
      nUserRatings = copy.nUserRatings;
      userRating = copy.userRating;
    }


    //GETTERS
    public int getnUserRatings(){return nUserRatings;}
    public Util.ageRatingsEnum getAgeRating(){return ageRating;}
    public String getAgeRatingDescription(){return ageRating.getRatingDescription();}
    public String getName(){return name;}
    public int getnViews(){return nViews;}
    public int getYear(){return year;}
    public float getUserRating(){return userRating;}

    public ArrayList<Util.genresEnum> getGenres(){
      ArrayList<Util.genresEnum> copy = (ArrayList<Util.genresEnum>) Util.CopyArray(this.genres);
      return copy;
  }



//UTIL
    public void incrementViews(){nViews++;}
    public void setYear(int year){
        if(year < 1878)
            logger.log(Level.WARNING, "Não existiam filmes antes de 1878!");
        else if (year > (GregorianCalendar.getInstance().get(Calendar.YEAR)+1))
            logger.log(Level.WARNING, "O filme não pode ter data de lançamento maior que 1 ano a partir deste!");
        this.year = year;
    }

    public void addUserRating(float userRating){
        this.nUserRatings++;
        this.userRating = this.userRating + ((userRating-this.userRating)/this.nUserRatings);
    }
}
