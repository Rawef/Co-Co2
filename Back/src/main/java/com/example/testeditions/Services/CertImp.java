package com.example.testeditions.Services;

import com.example.testeditions.Entites.Certification;

public interface CertImp {

    public Certification ajouterCerti(Certification certification) ;
    public Certification GetCerti(long id);
    public Certification UpdateCert (long id , Certification certification);

    public Certification getCertificationByUserId(long id);

    public Certification changeEtat(long cert_id);

    public String getetatcertificationbyuserid (long id);
}
