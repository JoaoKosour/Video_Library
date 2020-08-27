import java.util.ArrayList;

public class Episode extends Video {
    private final int number, seasonNumber;

    public Episode(String name, Diretor director, Util.ageRatingsEnum ageRating, ArrayList<Util.genresEnum> genres, ArrayList<Actor> actors, int season, int number) {
        super(name, director, ageRating, genres, actors);
        this.number = number;
        this.seasonNumber = season;
    }

    public Episode(Episode copy){
        super(copy);
        number = copy.number;
        seasonNumber = copy.seasonNumber;
    }

    public int getNumber(){ 
        return number; 
    }

    public int getSeasonNumber(){ 
        return seasonNumber; 
    }
}