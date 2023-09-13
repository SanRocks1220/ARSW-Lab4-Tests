package edu.eci.arsw.blueprints.test.filters.impl;

import static org.junit.Assert.assertArrayEquals;

import java.util.List;

import org.junit.Test;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.services.filters.BlueprintFilter;
import edu.eci.arsw.blueprints.services.filters.impl.RedundancyFilter;

public class RedundancyFilterTest {
    
    @Test
    public void ShouldRemoveTwoPoints() {
        BlueprintFilter filter = new RedundancyFilter();
        Point[] originalPoints=new Point[]{new Point(0, 0), new Point(0, 0), new Point(10, 10), new Point(10, 10), new Point(5, 5)};
        Blueprint bp=new Blueprint("maria", "thepaint",originalPoints);

        Point[] finalPoints=new Point[]{new Point(0, 0), new Point(10, 10), new Point(5, 5)};

        bp = filter.filterBlueprint(bp);

        List<Point> points = bp.getPoints();
        Point[] result = points.toArray(new Point[points.size()]);

        assertArrayEquals(finalPoints, result);
    }

    @Test
    public void ShouldNotRemovePoints() {
        BlueprintFilter filter = new RedundancyFilter();
        Point[] originalPoints=new Point[]{new Point(0, 0), new Point(10, 10), new Point(0, 0), new Point(10, 10), new Point(5, 5)};
        Blueprint bp=new Blueprint("maria", "thepaint",originalPoints);

        Point[] finalPoints=new Point[]{new Point(0, 0), new Point(10, 10), new Point(0, 0), new Point(10, 10), new Point(5, 5)};

        bp = filter.filterBlueprint(bp);

        List<Point> points = bp.getPoints();
        Point[] result = points.toArray(new Point[points.size()]);

        assertArrayEquals(finalPoints, result);
    }
}
