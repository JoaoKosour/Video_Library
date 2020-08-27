import java.util.ArrayList;

public class Movie extends Video{
    public Movie(String name, Diretor director, Util.ageRatingsEnum ageRating, ArrayList<Util.genresEnum> genres, ArrayList<Actor> actors) {
        super(name, director, ageRating, genres, actors);
    }

    public Movie(Movie copy){
        super(copy);
    }
}
