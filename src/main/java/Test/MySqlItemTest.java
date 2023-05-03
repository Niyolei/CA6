package Test;

import com.dkit.gd2.dominikHampejs.DAO.MySqlItemDAO;
import com.dkit.gd2.dominikHampejs.DTO.Item;
import com.dkit.gd2.dominikHampejs.Exceptions.DAOexception;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.dkit.gd2.dominikHampejs.Core.ServerUtility.getJsonFromList;

public class MySqlItemTest {

    public static MySqlItemDAO itemDAO = new MySqlItemDAO();
    public static int ID = 0;

    @Test
    public void AllItemsJsonTest(){
        try {
            String allItemsJson = itemDAO.getItemsAsJSON();
            ArrayList<Item> items = new ArrayList<>();
            items.add(new Item(1, "Doran's Blade", 450, "Starting Item", "Provides some AD and health for laning phase"));
            items.add(new Item(2, "Health Potion", 50, "Consumable", "Instantly restores 150 health over 15 seconds."));
            items.add(new Item(3, "Berserker's Greaves", 1100, "Boots", "Increases Attack Speed and provides some Movement Speed"));
            items.add(new Item(4, "Infinity Edge", 3400, "Legendary", "Increases critical strike chance and critical strike damage"));
            items.add(new Item(5, "Zhonya's Hourglass", 2900, "Mythic", "Grants AP, Armor, and an active that makes the user invulnerable"));
            items.add(new Item(6, "Sunfire Cape", 2900, "Legendary", "Grants armor, health, and deals damage to nearby enemies"));
            items.add(new Item(7, "Tear of the Goddess", 400, "Starting Item", "Grants mana and increases maximum mana as it is charged"));
            items.add(new Item(8, "Mortal Reminder", 2700, "Legendary", "Grants AD, armor penetration, and reduces healing on enemy targets"));
            items.add(new Item(9, "Guardian Angel", 2800, "Legendary", "Grants AD, Armor, and revives the user upon death"));
            items.add(new Item(10, "Rapid Firecannon", 2600, "Mythic", "Grants critical strike chance, attack speed, and increases range on basic attacks"));
            items.add(new Item(11, "Doran's Ring", 400, "Starting Item", "Provides some AP and mana regen for laning phase"));
            items.add(new Item(12, "Doran's Shield", 450, "Starting Item", "Provides some health and health regen for laning phase"));
            items.add(new Item(13, "Rabadon's Deathcap", 3800, "Legendary", "Grants a large amount of ability power and increases the user's ability power by a percentage."));
            items.add(new Item(14, "Ardent Censer", 2300, "Legendary", "Provides ability power, mana regen, and enhances ally attacks and movement speed"));
            items.add(new Item(15, "Redemption", 2400, "Legendary", "Provides health, health regen, and heals nearby allies"));
            items.add(new Item(16, "Locket of the Iron Solari", 2200, "Legendary", "Provides health, armor, and a shield to nearby allies"));
            items.add(new Item(17, "Spellthief's Edge", 400, "Starting Item", "Provides AP, mana regen, and gold generation on attacking enemy champions"));
            items.add(new Item(18, "Relic Shield", 400, "Starting Item", "Provides health, health regen, and gold generation on killing minions with a charge"));
            items.add(new Item(19, "Steel Shoulderguards", 400, "Starting Item", "Provides AD, health, and gold generation on attacking minions or monsters with a charge"));

            String expected = getJsonFromList(items);

            Assert.assertEquals(expected, allItemsJson);
        }
        catch (DAOexception e) {
            System.out.println("Couldn't get all items as JSON. " + e.getMessage());
            assert false;
        }
    }

    @Test
    public void GetItemByIdAsJsonTest(){
        try {
            String actual = itemDAO.getItemAsJSON(1);
            String expected  = "{\"id\":1,\"name\":\"Doran\\u0027s Blade\",\"price\":450,\"type\":\"Starting Item\",\"description\":\"Provides some AD and health for laning phase\"}";
            Assert.assertEquals(expected, actual);
        }
        catch (DAOexception e) {
            System.out.println("Couldn't get item by id. " + e.getMessage());
            assert false;
        }
    }

    @Test
    public void GetItemByTypeAsJson(){
        try{
            String actual = itemDAO.getItemsByTypeAsJSON("Consumable");
            String expected = "[{\"id\":2,\"name\":\"Health Potion\",\"price\":50,\"type\":\"Consumable\",\"description\":\"Instantly restores 150 health over 15 seconds.\"}]";
            Assert.assertEquals(expected, actual);
        } catch (DAOexception e) {
            System.out.println("Couldn't get item by type. " + e.getMessage());
            assert false;
        }
    }

    @Test
    public void InsertItemTest(){
        try{
            Item item = new Item(25, "Test Item", 1000, "Test Type", "Test Description");
            itemDAO.insertItem(item);

            List<Item> items = itemDAO.findAllItems();
            ID = items.get(items.size() - 1).getId();

            String actual = itemDAO.getItemAsJSON(25);
            String expected = "{\"id\":25,\"name\":\"Test Item\",\"price\":1000,\"type\":\"Test Type\",\"description\":\"Test Description\"}";
            Assert.assertEquals(expected, actual);
        } catch (DAOexception e) {
            System.out.println("Couldn't insert item. " + e.getMessage());
            assert false;
        }
    }

    @Test
    public void DeleteItemTest(){
        try{
            if(!itemDAO.deleteItem(25)){
                System.out.println("Couldn't delete item.");
                assert false;
            }
            assert true;
        } catch (DAOexception e) {
            System.out.println("Couldn't delete item. " + e.getMessage());
            assert false;
        }
    }
}
