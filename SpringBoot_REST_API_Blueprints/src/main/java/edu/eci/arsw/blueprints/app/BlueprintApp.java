package edu.eci.arsw.blueprints.app;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;


@Component
public class BlueprintApp {

    static BlueprintApp instance;

    static Point[] pts0=new Point[]{new Point(40, 40)};
    static Point[] pts1=new Point[]{new Point(40, 40),new Point(15, 15), new Point(45, 45),new Point(10, 10), new Point(40, 25),new Point(15, 35)};
    static Blueprint bp0 = new Blueprint("mack", "mypaint",pts0);
    static Blueprint bp1 = new Blueprint("mock", "yourpaint",pts1);

    public static void main(String[] args){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bps = ac.getBean(BlueprintsServices.class);

        try {
            bps.addNewBlueprint(bp0);
            bps.addNewBlueprint(bp1);
            System.out.println(bps.getBlueprint("mack", "mypaint"));
            System.out.println(bps.getBlueprintsByAuthor("mack"));
            System.out.println(bps.getBlueprint("mock", "yourpaint"));
            System.out.println(bps.getBlueprintsByAuthor("mock"));
            Set<Blueprint> allBlueprints = bps.getAllBlueprints();
            for(Blueprint bp : allBlueprints) {
                System.out.println(bp);
            }
        } catch (BlueprintPersistenceException e) {
            e.printStackTrace();
        } catch (BlueprintNotFoundException e){
            e.printStackTrace();
        }
    }
}