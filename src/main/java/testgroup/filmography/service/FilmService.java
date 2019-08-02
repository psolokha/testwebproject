package testgroup.filmography.service;

import java.util.List;
import testgroup.filmography.model.Film;

public interface FilmService {
    
    List<Film> allFilms(int page);
    public int filmsCount();
    void add(Film film);
    void delete (Film film);
    void edit(Film film);
    Film getByID(int id);
    
}
