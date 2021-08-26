package ru.job4j.io.serialization.xml;

import ru.job4j.io.serialization.json.Engine;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class MainCar {
    public static void main(String[] args) throws Exception {
        Car vaz = new Car(true, 1990, "2109", new EngineTwo(false, 1.5D),
                "leather seats", "fog lights");
        // Получаем контекст для доступа к АПИ
        JAXBContext context = JAXBContext.newInstance(Car.class);
        // Создаем сериализатор
        Marshaller marshaller = context.createMarshaller();
        // Указываем, что нам нужно форматирование
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (
                StringWriter writer = new StringWriter()) {
            // Сериализуем
            marshaller.marshal(vaz, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        // Для десериализации нам нужно создать десериализатор
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (
                StringReader reader = new StringReader(xml)) {
            // десериализуем

            Car result = (Car) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }



}
