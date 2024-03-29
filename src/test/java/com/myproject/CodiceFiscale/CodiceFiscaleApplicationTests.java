package com.myproject.CodiceFiscale;

import com.myproject.CodiceFiscale.service.FisCodServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CodiceFiscaleApplicationTests {

	@Test
	void contextLoads() {
		FisCodServiceImpl fis = new FisCodServiceImpl();

		String surname = "De Lellis";
		String name = "Pier Vincenzo";
		String date = "26/11/1996";
		String comune = "roma";
		String gender = "M";

		String result = fis.surnameElaborator(surname)
				+ fis.nameElaborator(name)
				+ fis.yearElaborator(date)
				+ fis.monthElaborator(date)
				+ fis.daysElaborator(date,gender)
				+ fis.cityElaborator(comune);

		String lastLetter = fis.sixteenLetterElaborator(result);

		result=result+lastLetter;
		System.out.print(result);

	}

}
