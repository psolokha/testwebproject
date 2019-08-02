package testgroup.filmography.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import testgroup.filmography.model.Film;
import testgroup.filmography.service.FilmService;

@Controller
public class FilmController {
    
    //private FilmService filmService = new FilmServiceImpl();
    private int page;
    private FilmService filmService;
    
    @Autowired
    public void setFilmService(FilmService filmService) {
        this.filmService = filmService;
    }
    
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addFilm(@ModelAttribute("film") Film film) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/?page=" + this.page);
        filmService.add(film);
        return modelAndView;
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteFilm(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/?page=" + this.page);
        Film film = filmService.getByID(id);
        filmService.delete(film);
        return modelAndView;
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        return modelAndView;
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editFilm(@ModelAttribute("film") Film film) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/?page=" + this.page);
        filmService.edit(film);
        return modelAndView;
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") int id) {
        Film film = filmService.getByID(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("film", film);
        return modelAndView;
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView allFilms(@RequestParam(defaultValue = "1") int page) {
        this.page = page;
        List<Film> films = filmService.allFilms(page);
        int filmsCount = filmService.filmsCount();
        int pagesCount = (filmsCount + 9)/10;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("films");
        modelAndView.addObject("page", page);
        modelAndView.addObject("filmsList", films);
        modelAndView.addObject("filmsCount", filmsCount);
        modelAndView.addObject("pagesCount", pagesCount);
        return modelAndView;
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView editPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        return modelAndView;
    }
    
}