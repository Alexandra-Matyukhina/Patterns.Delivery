package ru.netology.delivery.data;

import com.github.javafaker.Faker;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

import lombok.Data;
import lombok.RequiredArgsConstructor;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateDate(int shift) {
        LocalDate dayPlus = LocalDate.now();
        return dayPlus.plusDays(3 + shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

//    public static String generateCity(String locale) {
//        Faker faker = new Faker(new Locale(locale));
//        return faker.address().city();
//    }
    public static String generateCity() {
        String city;
        String[] cities = {"Майкоп", "Горно-Алтайск", "Уфа", "Улан-Удэ", "Махачкала", "Магас", "Нальчик", "Элиста",
                "Черкесск", "Петрозаводск", "Сыктывкар", "Симферополь", "Йошкар-Ола", "Саранск", "Якутск",
                "Владикавказ", "Казань", "Кызыл", "Ижевск", "Абакан", "Грозный", "Чебоксары", "Барнаул", "Чита",
                "Петропавловск-Камчатский", "Краснодар", "Красноярск", "Пермь", "Владивосток", "Ставрополь", "Хабаровск",
                "Благовещенск", "Архангельск", "Белгород", "Брянск", "Владимир", "Волгоград", "Вологда", "Воронеж", "Иваново",
                "Иркутск", "Калининград", "Калуга", "Кемерово", "Киров", "Кострома", "Курган", "Курск", "Санкт-Петербург",
                "Гатчина", "Липетск", "Магадан", "Москва", "Красногорск", "Мурманск", "Нижний Новгород", "Великий Новгород",
                "Новосибирск", "Омск", "Оренбург", "Орел", "Пенза", "Псков", "Ростов-на-Дону", "Рязань", "Самара", "Саратов",
                "Южно-Сахалинск", "Екатеринбург", "Смоленск", "Томбов", "Тверь", "Томск", "Тула", "Тюмень", "Ульяновск", "Челябинск",
                "Ярославль", "Севастополь", "Биробиджан", "Нарьян-Мар", "Ханты-Мансийск", "Анадырь", "Салехард"};

        Random cityRand = new Random();
        city = cities[cityRand.nextInt(cities.length)];
        return city;
    }

    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().lastName().replace("ё", "е") + " " +
                faker.name().firstName().replace("ё", "е");
    }

    public static String generatePhone(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.numerify("+7##########");
    }

    public static class Registration {
        private Registration() {
        }

        public static User generateDelivery(String locale) {
            return new User(generateCity(), generateName(locale), generatePhone(locale));
        }
    }

    @Data
    @RequiredArgsConstructor
    public static class User {
        private final String city;
        private final String name;
        private final String phone;
    }
}
