package com.example.testeditions.Services;

import com.example.testeditions.Entites.Certification;
import com.example.testeditions.Repositories.CertREpo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CertSER implements CertImp{


    private final CertREpo certREpo;

    @Autowired
    public CertSER(CertREpo certREpo) {
        this.certREpo = certREpo;
    }


    @Override
    public Certification ajouterCerti(Certification certification) {
        return certREpo.save(certification);
    }

    @Override
    public Certification GetCerti(long id) {
        return certREpo.findById(id).orElse(null);
    }

    @Override
    public Certification UpdateCert(long id, Certification certification) {
        Certification existingCert = certREpo.findById(id).orElse(null);
        if (existingCert != null) {
            // Update the existing certification with the new details
            existingCert.setName(certification.getName());
            existingCert.setPrenom(certification.getPrenom());
            existingCert.setArea(certification.getArea());
            existingCert.setVoiture(certification.getVoiture());
            existingCert.setEtat(certification.getEtat());
            return certREpo.save(existingCert);
        }
        return null; // Or throw an exception if needed
    }

    @Override
    public Certification getCertificationByUserId(long id) {
        return certREpo.findByUserId(id);
    }

    @Override
    public Certification changeEtat(long cert_id) {
        Certification certification = certREpo.findById(cert_id).orElse(null);
        if (certification != null) {
            // Change the state of the certification, assuming etat is a state field
            // For example, you can toggle between "active" and "inactive" states
            if ("active".equals(certification.getEtat())) {
                certification.setEtat("inactive");
            } else {
                certification.setEtat("active");
            }
            return certREpo.save(certification);
        }
        return null; // Or throw an exception if needed
    }

    @Override
    public String getetatcertificationbyuserid(long userId) {
        Certification certification = certREpo.findByUserId(userId);
        if (certification != null) {
            return certification.getEtat();
        }
        return null; // Or handle the case where certification is not found
    }
}
