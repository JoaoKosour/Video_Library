
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

//professor, meu código estava uma bagunça entao tomei um tempo pra reassistir as aulas e ir refazendo ele
//embora esteja bem parecido com o seu agora, creio que assim vai facilitar a correcao e a manutencao do codigo

public class InOut{

public static void readPerson(ArrayList<Person> peopleList, Scanner scnr) throws ParseException{
        Person auxPerson = null;
        int personClass;
        String auxName, auxCountry, auxBirth;
        do{
            personClass = (Integer.parseInt(scnr.nextLine()));
            auxName = scnr.nextLine();
            auxCountry = scnr.nextLine();
            auxBirth = scnr.nextLine();

            if(personClass == 1)
                    auxPerson = new Diretor(auxName, auxCountry, auxBirth);
            else if(personClass == 2)
                    auxPerson = new Actor(auxName, auxCountry, auxBirth);

            peopleList.add(auxPerson);
        } while(scnr.hasNextLine());
    }

private static void readMovie(ArrayList<Media> mediaList, ArrayList<Person> peopleList, Scanner scnr){
        Video auxVideo;
        String auxName;
        Util.ageRatingsEnum auxAgeRating;
        ArrayList<Util.genresEnum> auxGenreList = null;
        ArrayList<Actor> auxActorList = null;
        Diretor auxDiretor;
        int auxNItems;

        auxName = scnr.nextLine();
        auxDiretor = (Diretor) peopleList.get(Integer.parseInt(scnr.nextLine()));
        auxAgeRating = (Util.ageRatingsEnum.valueOf(scnr.nextLine()));
        auxNItems = Integer.parseInt(scnr.nextLine());

        if(auxNItems > 0){
            auxGenreList = new ArrayList<>();
            for(int i = 0; i < auxNItems; ++i)
                auxGenreList.add(Util.genresEnum.valueOf(scnr.nextLine()));
        }
        auxNItems = Integer.parseInt(scnr.nextLine());

        if(auxNItems > 0){
            auxActorList = new ArrayList<>();
            for(int i = 0; i < auxNItems; ++i)
                auxActorList.add((Actor) peopleList.get(Integer.parseInt(scnr.nextLine())));
        }
        auxVideo = new Movie(auxName, auxDiretor, auxAgeRating, auxGenreList, auxActorList);

        mediaList.add(auxVideo);
    }

private static void readSeries(ArrayList<Media> mediaList, ArrayList<Person> peopleList, Scanner scnr){

        Series auxSeries;
        Episode auxEpisode;
        String auxName;
        Util.ageRatingsEnum auxAgeRating;
        ArrayList<Util.genresEnum> auxGenreList = null;
        ArrayList<Actor> auxActorList = null;
        Diretor auxDiretor;
        int auxNItems, nEpisodes, nSeasons;

        auxName = scnr.nextLine();

        auxAgeRating = (Util.ageRatingsEnum.valueOf(scnr.nextLine()));
        auxNItems = Integer.parseInt(scnr.nextLine());
        if(auxNItems > 0){
            auxGenreList = new ArrayList<>();
            for(int i = 0; i < auxNItems; ++i)
                auxGenreList.add(Util.genresEnum.valueOf(scnr.nextLine()));
        }

        auxSeries = new Series(auxName, auxAgeRating, auxGenreList, null);

        nSeasons = Integer.parseInt(scnr.nextLine());
        for(int i = 0; i < nSeasons; ++i){
            nEpisodes = Integer.parseInt(scnr.nextLine());
            for(int j = 0; j < nEpisodes; ++j){
                auxName = scnr.nextLine();
                auxDiretor = (Diretor) peopleList.get(Integer.parseInt(scnr.nextLine()));
                auxNItems = Integer.parseInt(scnr.nextLine());
                
                if(auxNItems > 0){
                    auxActorList = new ArrayList<>();
                    for(int k = 0; k < auxNItems; ++k)
                        auxActorList.add((Actor) peopleList.get(Integer.parseInt(scnr.nextLine())));
                }
                auxEpisode = new Episode(auxName, auxDiretor, auxSeries.getAgeRating(), auxSeries.getGenres(), auxActorList, i+1, j+1);
                auxSeries.addEpisode(auxEpisode, i);
            }
        }
        mediaList.add(auxSeries);
    }

public static void readMedia(ArrayList<Media> mediaList, ArrayList<Person> peopleList, Scanner scnr){

        int mediaClass;
        do{
            mediaClass = Integer.parseInt(scnr.nextLine());
                if(mediaClass == 1)
                    readMovie(mediaList, peopleList, scnr);

                if(mediaClass == 2)
                    readSeries(mediaList, peopleList, scnr);

        }while(scnr.hasNextLine());
    }

public static void printMedia(ArrayList<Media> mediaList){
        ArrayList<Actor> auxActorList;

        for (Media media: mediaList){
            System.out.println("Media name: " + media.getName());
            System.out.println("Media age rating description: " + media.getAgeRatingDescription());
            System.out.println("Media genres: " + media.getGenres());

            if (media instanceof Video){
                Video vid = (Video) media;
                System.out.println("Video Diretor name: " + vid.getDirector().getName());
                auxActorList = vid.getActors();
                if (auxActorList == null)
                    System.out.println("No Actors in video");
                else
                    for (int i = 0; i < auxActorList.size(); ++i)
                        System.out.println("Video Actor " + i + " Name: " + auxActorList.get(i).getName());
            }
            else if (media instanceof Series){
                Series serie = (Series) media;
                int nSeasons = serie.getNSeasons();
                System.out.println("Number of Seasons: " + nSeasons);
                for (int i = 0; i < nSeasons; ++i)
                    System.out.println("Number of Episodes in Season " + (i + 1) + ": " + serie.getNEpisodesInSeason(i));
            }
            else
                System.out.println("Erro");
            System.out.println();
        }
    }







public static void printPerson(ArrayList<Person> peopleList){
        for (Person per: peopleList){
            System.out.println("Person name: "+per.getName());
            System.out.println("Person country: "+per.getCountry());
            System.out.println("Person birthdate: "+per.getBirth());
            System.out.println("Person age: "+per.getAge());
            System.out.println("");
            if (per instanceof Actor){ // uma gamb pra printar tambem, copiei do codigo, nao entendi muito bem instanceof
                System.out.println("Actor name: "+per.getName());
            }
        }
    }

    
 
    private static void printMediaDebbug(ArrayList<Media> mediaList){
        ArrayList<Actor> auxActorList;
        for (Media vid: mediaList){
            System.out.println("Video name: "+vid.getName());
            System.out.println("Video age rating description: "+vid.getAgeRatingDescription());
            System.out.println("Video genres: "+vid.getGenres());

            if(vid instanceof Video){
                System.out.println("Video Diretor name: "+((Video)vid).getDirector().getName());
                auxActorList = ((Video)vid).getActors();
                if(auxActorList == null)
                    System.out.println("No Actors in video");
                else
                    for(int i = 0; i < auxActorList.size(); ++i)
                        System.out.println("Video Actor "+i+ " Name: "+auxActorList.get(i).getName());
            }

            else if(vid instanceof Series){
                int nSeasons = ((Series)vid).getNSeasons();
                System.out.println("Number of Seasons: "+nSeasons);
                for(int i =0; i <nSeasons; ++i)
                    System.out.println("Number of Episodes in Season "+(i+1)+": "+((Series)vid).getNEpisodesInSeason(i));
            }
            else
                System.out.println("Erro");
            System.out.println();
        }
    }





public static void printClone(ArrayList<Media> mediaList){
    ArrayList<Actor> auxActorList;
    Series copiaSeries;
    Movie copiaMovie;
    for (Media vid: mediaList){

        if(vid instanceof Movie){
            copiaMovie = new Movie((Movie)vid);

//AQUI FAZ O PRINT DO ORIGINAL
            System.out.println("Movie name: "+vid.getName());
            System.out.println("Movie  age rating description: "+vid.getAgeRatingDescription());
            System.out.println("Movie  genres: "+vid.getGenres());
            System.out.println("Movie  Director name: "+((Video)vid).getDirector().getName());
            auxActorList = ((Video)vid).getActors();
            if(auxActorList == null)
                System.out.println("No Actors in Movie ");
            else
                for(int i = 0; i < auxActorList.size(); ++i)
                    System.out.println("Movie  Actor "+i+ " Name: "+auxActorList.get(i).getName());



                    System.out.println("\n\n\n");
// AQUI FAZ O PRINT DA COPIA
                    System.out.println("Copy Movie name: "+copiaMovie.getName());
                    System.out.println("Copy Movie  age rating description: "+copiaMovie.getAgeRatingDescription());
                    System.out.println("Copy Movie  genres: "+copiaMovie.getGenres());
                    System.out.println("Copy Movie  Director name: "+((Video)copiaMovie).getDirector().getName());
                    auxActorList = ((Video)copiaMovie).getActors();
                    if(auxActorList == null)
                        System.out.println("Copy No Actors in Movie ");
                    else
                        for(int i = 0; i < auxActorList.size(); ++i)
                            System.out.println("Copy Movie  Actor "+i+ " Name: "+auxActorList.get(i).getName());

            System.out.println("\n\n\n");
            break;
        }
    }

    for (Media vid: mediaList){
        
        if(vid instanceof Series){
            copiaSeries = new Series((Series)vid);
            //ORIGINAL
            System.out.println("Video name: "+vid.getName());
            System.out.println("Video age rating description: "+vid.getAgeRatingDescription());
            System.out.println("Video genres: "+vid.getGenres());
            System.out.println("Movie  Director name: "+((Series)vid).episodes.get(0).get(0).getDirector().getName());
            System.out.println("Season "+((Series)vid).episodes.get(0).get(0).getSeasonNumber());
            System.out.println("Episode "+((Series)vid).episodes.get(0).get(0).getNumber());


            System.out.println("\n\n\n");
            //COPIA
            System.out.println("Copy Video name: "+copiaSeries.getName());
            System.out.println("Copy Video age rating description: "+copiaSeries.getAgeRatingDescription());
            System.out.println("Copy Video genres: "+copiaSeries.getGenres());
            System.out.println("Movie Director name: "+((Series)vid).episodes.get(0).get(0).getDirector().getName());
            System.out.println("Season "+((Series)vid).episodes.get(0).get(0).getSeasonNumber());
            System.out.println("Episode "+((Series)vid).episodes.get(0).get(0).getNumber());
            break;
            }
        }
    }
}
