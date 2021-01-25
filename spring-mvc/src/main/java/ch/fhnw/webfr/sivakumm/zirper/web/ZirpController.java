package ch.fhnw.webfr.sivakumm.zirper.web;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.fhnw.webfr.sivakumm.zirper.domain.Zirp;
import ch.fhnw.webfr.sivakumm.zirper.persistence.ZirpRepository;

@Controller
@RequestMapping("/zirps")
public class ZirpController {
    
    @Autowired
    private ZirpRepository zirpRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String findAll(Model model) {
        Sort sort = Sort.by(Direction.DESC, "date");
        model.addAttribute("zirps", zirpRepository.findAll(sort));
        model.addAttribute("newZirp", new Zirp());
        return "zirp/list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String findById(@PathVariable String id, Model model) {
        Optional<Zirp> zirp = zirpRepository.findById(id);

        if (zirp.isEmpty()) {
            return "404";
        }

        model.addAttribute("zirp", zirp.get());
        return "zirp/detail";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createZirp(Model model, @Valid Zirp zirp, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/zirps";
        }
        zirp.setDate(new Date());
        zirpRepository.save(zirp);
        return "redirect:/zirps";
    }

    @RequestMapping(value = "/{id}", params = "update", method = RequestMethod.GET)
    public String getUpdatePage(@PathVariable String id, Model model) {
        Optional<Zirp> zirp = zirpRepository.findById(id);
        if (zirp.isEmpty()) {
            return "404";
        }

        model.addAttribute("zirp", zirp.get());
        return "zirp/edit";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updateZirp(@PathVariable String id, @Valid Zirp zirp, BindingResult result) {
        if (result.hasErrors()) {
            return "zirp/edit";
        }

        Optional<Zirp> original = zirpRepository.findById(id);
        if (original.isEmpty()) {
            return "404";
        }

        zirp.setId(id);
        zirpRepository.save(zirp);
        return "redirect:/zirps/" + id;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteZirp(@PathVariable String id) {
        if (zirpRepository.findById(id).isEmpty()) {
            return "404";
        }

        zirpRepository.deleteById(id);
        return "redirect:/zirps";
    }
}
