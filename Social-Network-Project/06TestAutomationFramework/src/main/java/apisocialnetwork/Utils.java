package apisocialnetwork;

import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

import static apisocialnetwork.Constants.*;
import static apisocialnetwork.Constants.RANDOM_EMAIL;

public class Utils {

    public static String generateRandomEmail() {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('a', 'z')
                .filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS)
                .build();

        StringBuilder email = new StringBuilder();
        email.append(generator.generate(10));
        email.append("@example.com");

        return email.toString();
    }

    public static String generateUniqueUsername() {
        return "Username" + RandomStringUtils.randomAlphabetic(8);
    }

    public static String generateUniquePassword() {
        return "Password" + RandomStringUtils.randomAlphabetic(8);
    }

    public static String generateFirstName() {
        return RandomStringUtils.randomAlphabetic(8);
    }

    public static String generateLastName() {
        return RandomStringUtils.randomAlphabetic(8);
    }


    public static boolean isValid(String json) {
        try {
            new JSONObject(json);
        } catch (JSONException e) {
            return false;
        }
        return true;
    }

    public static void fakeValueGenerator(String title1, String title2) {
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        RANDOM_JOB_TITLE_FIRST = fakeValuesService.bothify(title1);
        RANDOM_JOB_TITLE = fakeValuesService.bothify(title2);
    }

    public static void generateRandomConstants(String usernamePattern, String passwordPattern,
                                               String uniqueNamePattern, String skillDescriptionPattern,
                                               String emailPattern) {

        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        USERNAME = fakeValuesService.bothify(usernamePattern);
        PASSWORD = fakeValuesService.bothify(passwordPattern);
        UNIQUE_NAME = fakeValuesService.bothify(uniqueNamePattern);
        SKILL_DESCRIPTION = fakeValuesService.bothify(skillDescriptionPattern);
        SKILL_DESCRIPTION_EDITED = SKILL_DESCRIPTION + UNIQUE_NAME;
        RANDOM_EMAIL = fakeValuesService.bothify(emailPattern);
    }

    public static void deleteUser(String key, String value) {
        String dbUrl = com.telerikacademy.testframework.Utils.getConfigPropertyByKey("weare.db.url");
        String username = com.telerikacademy.testframework.Utils.getConfigPropertyByKey("weare.db.username");
        String password = com.telerikacademy.testframework.Utils.getConfigPropertyByKey("weare.db.password");
        String query = String.format("DELETE FROM users WHERE %s=%s;", key, value);

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (Connection con = DriverManager.getConnection(dbUrl, username, password)) {
            try (Statement stmt = con.createStatement()) {
                stmt.executeUpdate(query);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
