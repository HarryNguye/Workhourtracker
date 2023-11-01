package com.example.Workhour.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.Workhour.domain.WorkHour;
import com.example.Workhour.domain.WorkHourRepository;
import com.example.Workhour.domain.User;
import com.example.Workhour.domain.UserRepository;



import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/index")
public class WorkHourController {

    @Autowired
    private WorkHourRepository repository;
    

    // NÄYTÄ KAIKKI TYÖTUNNIT
    // Käytetään näyttämään lista kaikista tietokannassa olevista työtunneista.
    @RequestMapping(method = RequestMethod.GET)
    public String listWorkHours(Model model) {
        List<WorkHour> workHours = repository.findAll();
        model.addAttribute("workHours", workHours);
        return "index";
    }

    // NÄYTÄ LOMAKE UUDEN TYÖTUNNIN LISÄÄMISEKSI
    // Käytetään näyttämään lomake, jolla voi lisätä uuden työtunnin.
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showAddForm(Model model) {
        model.addAttribute("workHour", new WorkHour());
        return "add";
    }

    // LISÄÄ UUSI TYÖTUNTI
    // Käytetään lisäämään uusi työtunti tietokantaan.
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addWorkHour(@ModelAttribute WorkHour workHour) {
        repository.save(workHour);
        return "redirect:/index";
    }

    // NÄYTÄ LOMAKE TYÖTUNNIN MUOKKAAMISEKSI
    // Käytetään näyttämään lomake, jolla voi muokata olemassa olevaa työtuntia.
    @RequestMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        Optional<WorkHour> workHourOptional = repository.findById(id);
        if (workHourOptional.isPresent()) {
            model.addAttribute("workHour", workHourOptional.get());
            return "edit";
        } else {
            return "redirect:/index";
        }
    }

    // PÄIVITÄ OLEMASSA OLEVA TYÖTUNTI
    // Käytetään päivittämään olemassa oleva työtunti tietokantaan.
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updateWorkHour(@PathVariable("id") long id, @ModelAttribute WorkHour workHour) {
        if (repository.existsById(id)) {
            workHour.setId(id);
            repository.save(workHour);
        }
        return "redirect:/index";
    }

    // POISTA TYÖTUNTI
    // Käytetään poistamaan työtunti tietokannasta.
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteWorkHour(@PathVariable("id") long id) {
        repository.deleteById(id);
        return "redirect:/index";
    }
}
