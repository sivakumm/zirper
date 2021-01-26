package ch.fhnw.webfr.sivakumm.zirper.web;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.fhnw.webfr.sivakumm.zirper.domain.Zirp;
import ch.fhnw.webfr.sivakumm.zirper.persistence.ZirpRepository;

@RestController
@RequestMapping("/zirps")
public class ZirpController {
    
    @Autowired
    private ZirpRepository zirpRepository;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Zirp>> findAll() {
        return new ResponseEntity<List<Zirp>>(zirpRepository.findAll(), HttpStatus.OK);
    }
    
    @CrossOrigin
    @GetMapping(value = "/{id}")
    public ResponseEntity<Zirp> findById(@PathVariable String id) {
        Optional<Zirp> zirp = zirpRepository.findById(id);

        if (zirp.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Zirp>(zirp.get(), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Zirp> createZirp(@Valid @RequestBody Zirp zirp, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
        }

        zirp.setDate(new Date());
        Zirp saved = zirpRepository.save(zirp);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @CrossOrigin
    @PutMapping(value = "/{id}")
    public ResponseEntity<Zirp> updateZirp(@PathVariable String id, @Valid @RequestBody Zirp zirp, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
        }

        Optional<Zirp> original = zirpRepository.findById(id);
        if (original.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        zirp.setId(id);
        Zirp updated = zirpRepository.save(zirp);
        return new ResponseEntity<Zirp>(updated, HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Zirp> deleteZirp(@PathVariable String id) {
        if (zirpRepository.findById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        zirpRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @CrossOrigin
    @GetMapping(value = "/users/{username}")
    public ResponseEntity<List<Zirp>> findZirpsOfUser(@PathVariable String username) {
        List<Zirp> zirps = zirpRepository.findByUsername(username);

        if (zirps.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Zirp>>(zirps, HttpStatus.OK);
    }
}
