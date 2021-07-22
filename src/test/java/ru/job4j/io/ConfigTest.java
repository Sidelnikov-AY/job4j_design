package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test
    public void whenPairWithoutComment2() {
        String path = "./data/forConfigTest.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect"));
        assertThat(config.value("hibernate.connection.password"), is("password"));
    }

    @Test (expected = IllegalArgumentException.class)
        public void whenPairWithoutComment3() {
        String path = "./data/forConfigTest2.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"), is(Matchers.nullValue()));
        }
}
