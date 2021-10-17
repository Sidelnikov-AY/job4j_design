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
        JAXBContext context = JAXBContext.newInstance(Car.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (
                StringWriter writer = new StringWriter()) {
            marshaller.marshal(vaz, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (
                StringReader reader = new StringReader(xml)) {
            Car result = (Car) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }



}
