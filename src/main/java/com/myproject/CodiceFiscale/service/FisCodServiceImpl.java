package com.myproject.CodiceFiscale.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;

@Service
public class FisCodServiceImpl implements FisCodService {

    char[] consonanti = {'B', 'C', 'D', 'F', 'G', 'H', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'Z'};
    char[] vocali = {'A', 'E', 'I', 'O', 'U'};

    public boolean containsOnlyLetters(String str) {
        return str != null && str.matches("[a-zA-Z]+");
    }

    public static boolean verifyFormatDate(String data) {
        String regex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/((19|20)\\d\\d)$";
        return data.matches(regex);
    }

    @Override
    public String nameElaborator(String string) {
        String name = string.toUpperCase();
        name = name.replaceAll("\\s", "");
        if (!containsOnlyLetters(name)) {
            throw new RuntimeException("Il nome ha simboli al suo interno (utilizza solo lettere).");
        }
        StringBuilder consonantMatching = new StringBuilder();
        StringBuilder vocalsMatching = new StringBuilder();
        for (char c : name.toCharArray()) {
            for (int i = 0; i < consonanti.length; i++) {
                if (c == consonanti[i]) {
                    consonantMatching.append(consonanti[i]);
                }
            }
            for (int i = 0; i < vocali.length; i++) {
                if (c == vocali[i]) {
                    vocalsMatching.append(vocali[i]);
                }
            }

        }

        String result = "";
        if (consonantMatching.length() >= 4) {
            result += String.valueOf(consonantMatching.charAt(0)) + String.valueOf(consonantMatching.charAt(2)) + String.valueOf(consonantMatching.charAt(3));
        } else if (consonantMatching.length() == 3) {
            result += String.valueOf(consonantMatching.charAt(0)) + String.valueOf(consonantMatching.charAt(1)) + String.valueOf(consonantMatching.charAt(2));
        } else if (consonantMatching.length() == 2) {
            result += String.valueOf(consonantMatching.charAt(0)) + String.valueOf(consonantMatching.charAt(1)) + String.valueOf(vocalsMatching.charAt(0));
        } else if (consonantMatching.length() == 1 && vocalsMatching.length() > 1) {
            result += String.valueOf(consonantMatching.charAt(0)) + String.valueOf(vocalsMatching.charAt(0)) + String.valueOf(vocalsMatching.charAt(1));
        } else if (consonantMatching.length() == 1 && vocalsMatching.length() == 1) {
            result += String.valueOf(consonantMatching.charAt(0)) + String.valueOf(vocalsMatching.charAt(0)) + 'X';
        } else if (consonantMatching.length() == 0 && vocalsMatching.length() == 2) {
            result += String.valueOf(vocalsMatching.charAt(0)) + String.valueOf(vocalsMatching.charAt(1)) + 'X';
        }

        return result;
    }

    @Override
    public String surnameElaborator(String string) {

        String surname = string.toUpperCase();
        surname = surname.replaceAll("\\s", "");
        if (!containsOnlyLetters(surname)) {
            throw new RuntimeException("Il cognome ha simboli al suo interno (utilizza solo lettere).");
        }
        StringBuilder consonantMatching = new StringBuilder();
        StringBuilder vocalsMatching = new StringBuilder();
        for (char c : surname.toCharArray()) {
            for (int i = 0; i < consonanti.length; i++) {
                if (c == consonanti[i]) {
                    consonantMatching.append(consonanti[i]);
                }
            }
            for (int i = 0; i < vocali.length; i++) {
                if (c == vocali[i]) {
                    vocalsMatching.append(vocali[i]);
                }
            }

        }

        String result = "";
        if (consonantMatching.length() >= 3) {
            result += String.valueOf(consonantMatching.charAt(0)) + String.valueOf(consonantMatching.charAt(1)) + String.valueOf(consonantMatching.charAt(2));
        } else if (consonantMatching.length() == 2) {
            result += String.valueOf(consonantMatching.charAt(0)) + String.valueOf(consonantMatching.charAt(1)) + String.valueOf(vocalsMatching.charAt(0));
        } else if (consonantMatching.length() == 1 && vocalsMatching.length() > 1) {
            result += String.valueOf(consonantMatching.charAt(0)) + String.valueOf(vocalsMatching.charAt(0)) + String.valueOf(vocalsMatching.charAt(1));
        } else if (consonantMatching.length() == 1 && vocalsMatching.length() == 1) {
            result += String.valueOf(consonantMatching.charAt(0)) + String.valueOf(vocalsMatching.charAt(0)) + 'X';
        } else if (consonantMatching.length() == 0 && vocalsMatching.length() == 2) {
            result += String.valueOf(vocalsMatching.charAt(0)) + String.valueOf(vocalsMatching.charAt(1)) + 'X';
        }

        return result;
    }

    @Override
    public String yearElaborator(String date) {

        if(!verifyFormatDate(date)){
            throw new RuntimeException("Inserisci un formato valido dd/mm/yyyy (eg: 01/01/2000)");
        }

        String[] part = date.split("/");
        String result= part[2];
        result = result.substring(2);
        return result;
    }

    @Override
    public String daysElaborator(String date, String gender) {

        if(!verifyFormatDate(date)){
            throw new RuntimeException("Inserisci un formato valido dd/mm/yyyy (eg: 01/01/2000)");
        }

        String gen=gender.toUpperCase();
        String[] part = date.split("/");
        String result= part[0];

        if(gen.equals("F")){
            int numero = Integer.parseInt(result);
            numero += 40;
            result=""+numero;
        }else if(gen.equals("M")){

        }else{
            throw new RuntimeException("Selezionare M o F nel genere");
        }
        return result;

    }

    @Override
    public String monthElaborator(String date) {

        if(!verifyFormatDate(date)){
            throw new RuntimeException("Inserisci un formato valido dd/mm/yyyy (eg: 01/01/2000)");
        }
        String[] part = date.split("/");
        String result= part[1];
        switch (result){
            case "01":
                result="A";
                break;
            case "02":
                result="B";
                break;
            case "03":
                result="C";
                break;
            case "04":
                result="D";
                break;
            case "05":
                result="E";
                break;
            case "06":
                result="H";
                break;
            case "07":
                result="L";
                break;
            case "08":
                result="M";
                break;
            case "09":
                result="P";
                break;
            case "10":
                result="R";
                break;
            case "11":
                result="S";
                break;
            case "12":
                result="T";
                break;
            default: throw new RuntimeException("mese non valido");


        }
        return result;
    }

    @Override
    public String cityElaborator(String c) {
        String city=c.toUpperCase();
        try {
            // Creare un ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Ottenere l'InputStream del file JSON dal classpath
            InputStream inputStream = getClass().getResourceAsStream("/ComuniITA.json");

            // Leggere il file JSON e convertirlo in un albero JSON
            JsonNode jsonNode = objectMapper.readTree(inputStream);

            // Cerca il comune all'interno del JSON e recupera il codice comune corrispondente
            JsonNode comuniNode = jsonNode.get("Comuni");
            for (JsonNode comuneNode : comuniNode) {
                String comune = comuneNode.get("Comune").asText();
                if (comune.equals(city)) {
                    return comuneNode.get("CodiceComune").asText();
                }
            }
            throw new RuntimeException("Comune non trovato, prova a riscriverlo");
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Comune non trovato, prova a riscriverlo");
    }

    @Override
    public String sixteenLetterElaborator(String string) {
        int num=0;
        for (int i=0; i<string.length();i++){
            if(i%2!=0){
                switch (string.charAt(i)) {
                    case '0': case 'A': num+= 0; break;
                    case '1': case 'B': num+= 1; break;
                    case '2': case 'C': num+= 2; break;
                    case '3': case 'D': num+= 3; break;
                    case '4': case 'E': num+= 4; break;
                    case '5': case 'F': num+= 5; break;
                    case '6': case 'G': num+= 6; break;
                    case '7': case 'H': num+= 7; break;
                    case '8': case 'I': num+= 8; break;
                    case '9': case 'J': num+= 9; break;
                    case 'K': num+= 10; break;
                    case 'L': num+= 11; break;
                    case 'M': num+= 12; break;
                    case 'N': num+= 13; break;
                    case 'O': num+= 14; break;
                    case 'P': num+= 15; break;
                    case 'Q': num+= 16; break;
                    case 'R': num+= 17; break;
                    case 'S': num+= 18; break;
                    case 'T': num+= 19; break;
                    case 'U': num+= 20; break;
                    case 'V': num+= 21; break;
                    case 'W': num+= 22; break;
                    case 'X': num+= 23; break;
                    case 'Y': num+= 24; break;
                    case 'Z': num+= 25; break;
                }
            }else{
                switch (string.charAt(i)) {
                    case '0': case 'A': num+= 1; break;
                    case '1': case 'B': num+= 0; break;
                    case '2': case 'C': num+= 5; break;
                    case '3': case 'D': num+= 7; break;
                    case '4': case 'E': num+= 9; break;
                    case '5': case 'F': num+= 13; break;
                    case '6': case 'G': num+= 15; break;
                    case '7': case 'H': num+= 17; break;
                    case '8': case 'I': num+= 19; break;
                    case '9': case 'J': num+= 21; break;
                    case 'K': num+= 2; break;
                    case 'L': num+= 4; break;
                    case 'M': num+= 18; break;
                    case 'N': num+= 20; break;
                    case 'O': num+= 11; break;
                    case 'P': num+= 3; break;
                    case 'Q': num+= 6; break;
                    case 'R': num+= 8; break;
                    case 'S': num+= 12; break;
                    case 'T': num+= 14; break;
                    case 'U': num+= 16; break;
                    case 'V': num+= 10; break;
                    case 'W': num+= 22; break;
                    case 'X': num+= 25; break;
                    case 'Y': num+= 24; break;
                    case 'Z': num+= 23; break;
                }
            }
        }
        num = num %26;
        switch (num) {
            case 0:
                return "A";
            case 1:
                return "B";
            case 2:
                return "C";
            case 3:
                return "D";
            case 4:
                return "E";
            case 5:
                return "F";
            case 6:
                return "G";
            case 7:
                return "H";
            case 8:
                return "I";
            case 9:
                return "J";
            case 10:
                return "K";
            case 11:
                return "L";
            case 12:
                return "M";
            case 13:
                return "N";
            case 14:
                return "O";
            case 15:
                return "P";
            case 16:
                return "Q";
            case 17:
                return "R";
            case 18:
                return "S";
            case 19:
                return "T";
            case 20:
                return "U";
            case 21:
                return "V";
            case 22:
                return "W";
            case 23:
                return "X";
            case 24:
                return "Y";
            case 25:
                return "Z";
            default:
                throw new RuntimeException("Assicurati di scrivere correttamente il tuo comune");
        }
    }
    public String codFisGenerator (String name, String surname, String date, String gender, String comune ){
        String result = surnameElaborator(surname)
                +nameElaborator(name)
                +yearElaborator(date)
                +monthElaborator(date)
                +daysElaborator(date,gender)
                +cityElaborator(comune);

        result = result+sixteenLetterElaborator(result);

        return result;
    }
}
