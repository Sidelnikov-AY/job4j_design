package ru.job4j.io.serialization.xml;

import ru.job4j.io.serialization.json.Engine;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;


@XmlRootElement(name = "vaz")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {

    private EngineTwo engine;

    @XmlAttribute
    private boolean isUsed;

    @XmlAttribute
    private int year;

    @XmlAttribute
    private String model;

    @XmlElementWrapper(name = "options")
    @XmlElement(name = "option")
    private String[] options;

    public Car() { }

    public Car(boolean isUsed, int year, String  model, EngineTwo engine, String... options) {
        this.isUsed = isUsed;
        this.year = year;
        this.model = model;
        this.engine = engine;
        this.options = options;
    }


    @Override
    public String toString() {
        return "Car{"
                +
                "isUsed=" + isUsed
                +
                ", year=" + year
                +
                ", model='" + model + '\''
                +
                ", engine=" + engine
                +
                ", options=" + Arrays.toString(options)
                +
                '}';
    }

    public static void main(String[] args) throws JAXBException {
        final Car vaz = new Car(true, 1990, "2109", new EngineTwo(false, 1.5D),
                "leather seats", "fog lights");

        JAXBContext context = JAXBContext.newInstance(Car.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(vaz, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {

        }
    }
}
