package ch.fhnw.webfr.sivakumm.zirper.web;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        model.addAttribute("zirps", zirpRepository.findAll());
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
    public String createZirp(Zirp zirp) {
        zirp.setDate(new Date());
        zirpRepository.save(zirp);
        return "redirect:/zirps";
    }
}
