package faker;

import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Log4j2
public class TestFaker {

    @Test
    public void testFake() {
        Faker faker = new Faker();

        String streetName = faker.address().streetName();
        String number = faker.address().buildingNumber();
        String city = faker.address().city();
        String country = faker.address().country();

        Assertions.assertNotNull(number);
        Assertions.assertNotNull(streetName);
        Assertions.assertNotNull(city);
        Assertions.assertNotNull(country);
        log.info(String.format("%s\n%s\n%s\n%s",
                number,
                streetName,
                city,
                country));
    }



}
