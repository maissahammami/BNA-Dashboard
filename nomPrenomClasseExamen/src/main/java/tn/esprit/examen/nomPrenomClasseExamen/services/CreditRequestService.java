package tn.esprit.examen.nomPrenomClasseExamen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.examen.nomPrenomClasseExamen.entities.CreditRequest;
import tn.esprit.examen.nomPrenomClasseExamen.repositories.CreditRequestRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class CreditRequestService {

    @Autowired
    private CreditRequestRepository creditRequestRepository;

    public List<CreditRequest> getAll() {
        return creditRequestRepository.findAll();
    }

    public CreditRequest getById(Long id) {
        return creditRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Demande non trouvée"));
    }

    public CreditRequest create(CreditRequest request) {
        request.setRequestDate(LocalDate.now());
        return creditRequestRepository.save(request);
    }

    public void delete(Long id) {
        creditRequestRepository.deleteById(id);
    }

    public List<CreditRequest> findByRegion(String regionName) {
        return creditRequestRepository.findByRegion_Name(regionName);
    }

    public List<CreditRequest> findByUser(Long userId) {
        return creditRequestRepository.findByRequestedBy_Id(userId);
    }
}

