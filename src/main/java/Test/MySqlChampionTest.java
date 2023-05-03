package Test;

import com.dkit.gd2.dominikHampejs.DAO.MySqlChampionDAO;
import com.dkit.gd2.dominikHampejs.DTO.Champion;
import com.dkit.gd2.dominikHampejs.Exceptions.DAOexception;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;


public class MySqlChampionTest {
    public static MySqlChampionDAO championDAO = new MySqlChampionDAO();
    public static int ID;


    @Test
    public void AllChampionJsonTest(){
        try {
            String championsActual = championDAO.findAllChampionsAsJSON();
            String championsExpected = "[{\"id\":1,\"name\":\"Ashe\",\"role\":\"Marksman\",\"winRate\":52.37},{\"id\":2,\"name\":\"Garen\",\"role\":\"Tank\",\"winRate\":52.29},{\"id\":3,\"name\":\"Zed\",\"role\":\"Assassin\",\"winRate\":49.95},{\"id\":4,\"name\":\"Jinx\",\"role\":\"Marksman\",\"winRate\":51.75},{\"id\":5,\"name\":\"Evelynn\",\"role\":\"Assassin\",\"winRate\":50.03},{\"id\":6,\"name\":\"Malphite\",\"role\":\"Tank\",\"winRate\":52.54},{\"id\":7,\"name\":\"Lux\",\"role\":\"Mage\",\"winRate\":49.58},{\"id\":8,\"name\":\"Alistar\",\"role\":\"Support\",\"winRate\":52.15},{\"id\":9,\"name\":\"Yasuo\",\"role\":\"Fighter\",\"winRate\":48.82},{\"id\":10,\"name\":\"Soraka\",\"role\":\"Support\",\"winRate\":51.14}]";
            Assert.assertEquals(championsExpected, championsActual);
        } catch (DAOexception e) {
            System.out.println("findAllChampionsJson() " + e.getMessage());
            assert false;
        }
    }

    @Test
    public void ChampionByIdAsJson(){
        try {
            String championActual = championDAO.findChampionByIdAsJSON(1);
            String championExpected = "{\"id\":1,\"name\":\"Ashe\",\"role\":\"Marksman\",\"winRate\":52.37}";
            Assert.assertEquals(championExpected, championActual);
        } catch (DAOexception e) {
            System.out.println("findChampionByIdAsJson() " + e.getMessage());
            assert false;
        }
    }

    @Test
    public void ChampionByRoleAsJson(){
        try {
            String championsActual = championDAO.findChampionsByRoleAsJSON("Marksman");
            String championsExpected = "[{\"id\":1,\"name\":\"Ashe\",\"role\":\"Marksman\",\"winRate\":52.37},{\"id\":4,\"name\":\"Jinx\",\"role\":\"Marksman\",\"winRate\":51.75}]";
            Assert.assertEquals(championsExpected, championsActual);
        } catch (DAOexception e) {
            System.out.println("findChampionsByRoleAsJson() " + e.getMessage());
            assert false;
        }
    }


    @Test
    public void InsertChampionTest(){
try {
            Champion champion = new Champion(11, "Test", "Test", 50.00);
            championDAO.insertChampion(champion);
            List<Champion> all = championDAO.findAllChampions();

            Champion championActual = all.get(all.size() - 1);
            ID = championActual.getId();
            Champion championExpected = new Champion(ID, "Test", "Test", 50.00);
            boolean mismatch = false;

            if (championActual.getId() != championExpected.getId()) {
                System.out.println("Champion ID mismatch.");
                mismatch = true;
            }
            if (!championActual.getName().equals(championExpected.getName())) {
                System.out.println("Champion name mismatch.");
                mismatch = true;
            }
            if (!championActual.getRole().equals(championExpected.getRole())) {
                System.out.println("Champion role mismatch.");
                mismatch = true;
            }
            if (championActual.getWinRate() != championExpected.getWinRate()) {
                System.out.println("Champion win rate mismatch.");
                mismatch = true;
            }
            assertFalse(mismatch);
        }
        catch (DAOexception e) {
            System.out.println("Couldn't insert champion. " + e.getMessage());
            assert false;
        }
    }

    @Test
    public void DeleteChampionTest(){
        try {
            if(!championDAO.deleteChampion(ID)){
                System.out.println("Couldn't delete champion.");
                assert false;
            }
            List<Champion> all = championDAO.findAllChampions();
            boolean found = false;
            for (Champion champion : all) {
                if (champion.getId() == ID) {
                    found = true;
                    break;
                }
            }
            assertFalse(found);
        }
        catch (DAOexception e) {
            System.out.println("Couldn't delete champion. " + e.getMessage());
            assert false;
        }
    }
}
