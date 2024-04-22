package com.example.testeditions.Controllers;

import com.example.testeditions.Entites.Certification;
import com.example.testeditions.Services.CertImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/certifications")
public class CertificationController {

    private final CertImp certService;

    @Autowired
    public CertificationController(CertImp certService) {
        this.certService = certService;
    }

    @PostMapping("/ajoutercerti")
    public Certification addCertification(@RequestBody Certification certification) {
        return certService.ajouterCerti(certification);
    }

    @GetMapping("getcerti/{id}")
    public Certification getCertification(@PathVariable long id) {
        return certService.GetCerti(id);
    }

    @PutMapping("updatecert/{id}")
    public Certification updateCertification(@PathVariable long id, @RequestBody Certification certification) {
        return certService.UpdateCert(id, certification);
    }

    @GetMapping("/getuser/{userId}")
    public Certification getCertificationByUserId(@PathVariable long userId) {
        return certService.getCertificationByUserId(userId);
    }

    @PutMapping("/changeetat/{certId}")
    public Certification toggleCertificationState(@PathVariable long certId) {
        return certService.changeEtat(certId);
    }
}
