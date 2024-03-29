package com.myproject.CodiceFiscale.service;


public interface FisCodService {

    String nameElaborator(String string);
    String surnameElaborator(String string);
    String yearElaborator (String date);
    String daysElaborator(String date, String gender);
    String monthElaborator(String date);
    String cityElaborator(String city);
    String sixteenLetterElaborator(String string);
    String codFisGenerator (String name, String surname, String date, String gender, String comune );
}
