package com.example.Workhour.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.Workhour.domain.WorkHour;
import com.example.Workhour.domain.WorkHourRepository;

@Controller
public class WorkHourController {

	@Autowired
	private WorkHourRepository repository;

	@RequestMapping(value="/login")
    public String login() {	
        return "login";
    }	
	
	//Listaa kaikki työtunnit ja totalhours laskee kaikki työtunnit yhteensä
	
	@RequestMapping(value = "/workhour", method = RequestMethod.GET)
	public String workHourList(Model model) {
	    model.addAttribute("workhours", repository.findAll());
	    model.addAttribute("totalHours", repository.sumTotalHours()); // Lisätään kokonaistuntien määrä
	    return "workhour";
	}

	// NÄYTÄ LOMAKE UUDEN TYÖTUNNIN LISÄÄMISEKSI
	
	@RequestMapping(value = "/addhours")
	public String addWorkHour(Model model) {
		model.addAttribute("workhour", new WorkHour());
		return "addhours";
	}

	// TALLENTAA TYÖTUNNIN
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveWorkHour(@ModelAttribute WorkHour workhour) {
		repository.save(workhour);
		return "redirect:workhour";
	}
	// TYÖTUNNIN POISTAMINEN
	@RequestMapping(value = "/workhour/{id}", method = RequestMethod.GET)
	
    public String workhourWithId(@PathVariable("id") Long id) {
		repository.deleteById(id);   
        // Lopuksi uudelleenohjaa '/workhour'
        return "redirect:/workhour";
    }
	   // NÄYTÄ MUOKKAUSLOMAKE
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	
	public String showEditForm(@PathVariable("id") Long id, Model model) {
	    Optional<WorkHour> workhourOptional = repository.findById(id);
	    if (workhourOptional.isPresent()) {
	        model.addAttribute("workHour", workhourOptional.get());
	        return "edithours";
	    } else {
	        return "redirect:/workhour";
	    }
	}
	
    
    // PÄIVITÄ TYÖTUNTI (POST)
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    
    public String updateWorkHour(@PathVariable("id") Long id, @ModelAttribute("workHour") WorkHour updatedWorkHour) {
        Optional<WorkHour> workhourOptional = repository.findById(id);
        if (workhourOptional.isPresent()) {
            WorkHour existingWorkHour = workhourOptional.get();
            existingWorkHour.setDate(updatedWorkHour.getDate());
            existingWorkHour.setHours(updatedWorkHour.getHours());
            repository.save(existingWorkHour);
        }
        return "redirect:/workhour";
    }
    

}
