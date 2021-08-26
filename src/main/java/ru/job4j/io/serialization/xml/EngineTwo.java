package ru.job4j.io.serialization.xml;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringWriter;

@XmlRootElement(name = "engine")
public class EngineTwo {

    @XmlAttribute
    private boolean turbo;
    @XmlAttribute
    private double engineVolume;

    public EngineTwo() {

    }

    public EngineTwo(boolean turbo, double engineVolume) {
        this.turbo = turbo;
        this.engineVolume = engineVolume;
    }

    @Override
    public String toString() {
        return "Engine{"
                +
                "turbo=" + turbo
                +
                ", engineVolume=" + engineVolume
                +
                '}';
    }

    public static void main(String[] args) throws JAXBException {
        EngineTwo engine = new EngineTwo(false, 1.5D);

        JAXBContext context = JAXBContext.newInstance(EngineTwo.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(engine, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {

        }
    }
}
