package ch.fhnw.webfr.sivakumm.zirper.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.fhnw.webfr.sivakumm.zirper.persistence.ZirpRepository;

@RestController
@RequestMapping("/zirps")
public class ZirpController {
    
    @Autowired
    private ZirpRepository zirpRepository;
}
