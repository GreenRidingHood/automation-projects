package helper;

import com.github.javafaker.Faker;
import jdk.jfr.Category;
import models.Bank;

public class TestDataGenerator {
    public static Bank getBank(){
        Faker faker = new Faker();
        String name = faker.name().firstName();
        return new Bank(name,"pending");
    }

}
