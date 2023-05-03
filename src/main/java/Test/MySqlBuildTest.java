package Test;

import com.dkit.gd2.dominikHampejs.DAO.MySqlBuildDAO;
import com.dkit.gd2.dominikHampejs.DTO.Build;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MySqlBuildTest {

    public static MySqlBuildDAO buildDAO = new MySqlBuildDAO();

    @Test
    public void FindAllBuildsTest(){
        try {
            List<Build> actual = buildDAO.findAllBuilds();
            ArrayList<Build> builds = new ArrayList<>();
            builds.add(new Build(1, 1, "Provides sustain which benefits Ashe"));
            builds.add(new Build(4, 1, "Provides sustain which benefits Jinx"));
            builds.add(new Build(2, 2, "Provides sustain which benefits Garen"));
            builds.add(new Build(6, 2, "Provides sustain and damage output for Malphite"));
            builds.add(new Build(3, 3, "Increases burst damage for Zed"));
            builds.add(new Build(5, 3, "Increases burst damage and mobility for Evelynn"));
            builds.add(new Build(7, 3, "Increases burst damage and utility for Lux"));
            builds.add(new Build(9, 3, "Increases burst damage and mobility for Yasuo"));
            builds.add(new Build(2, 4, "Increases mobility and damage output for Garen"));
            builds.add(new Build(1, 5, "Increases damage and slows enemies"));
            builds.add(new Build(4, 5, "Increases damage output and slows enemies"));
            builds.add(new Build(3, 6, "Provides mobility and sustain for Zed"));
            builds.add(new Build(6, 7, "Increases survivability and crowd control for Malphite"));
            builds.add(new Build(5, 8, "Provides sustain and increased damage output for Evelynn"));
            builds.add(new Build(7, 9, "Provides increased damage output and crowd control for Lux"));
            builds.add(new Build(8, 10, "Provides sustain and crowd control for Alistar"));
            builds.add(new Build(10, 10, "Provides sustain and crowd control for Soraka"));
            builds.add(new Build(8, 11, "Increases survivability and utility for Alistar"));
            builds.add(new Build(9, 12, "Provides increased damage output and survivability for Yasuo"));
            builds.add(new Build(10, 13, "Increases survivability and healing output for Soraka"));
            Assert.assertEquals(builds, actual);
        } catch (Exception e) {
            System.out.println("FindAllBuildsTest() " + e.getMessage());
            assert false;
        }
    }

}
