package ch.fhnw.webfr.sivakumm.zirper.persistence;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ch.fhnw.webfr.sivakumm.zirper.domain.Zirp;

public class ZirpRepository {
    
    private final List<Zirp> zirps = new ArrayList<>();
    private static SoftReference<ZirpRepository> instance = null;

    private ZirpRepository() { }

    public static synchronized ZirpRepository getInstance() {
        ZirpRepository repo = instance == null ? null : instance.get();
        if (repo == null) {
            repo = new ZirpRepository();
            instance = new SoftReference<ZirpRepository>(repo);
        }
        return repo;
    }

    public synchronized Zirp save(Zirp zirp) {
        zirp.setId(zirps.size());
        zirps.add(zirp);
        return zirp;
    }

    public List<Zirp> findAll() {
        return zirps;
    }

    public Optional<Zirp> findById(int id) {
        return zirps.stream().filter(zirp -> zirp.getId() == id).findFirst();
    }

    public void clear() {
        zirps.clear();
    }
}
