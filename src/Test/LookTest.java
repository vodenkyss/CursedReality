package Test;

import World.Item;
import World.Location;
import World.NPC;
import World.Player;
import command.Look;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.List;

class LookTest {

    private Player player;
    private Look look;

    @BeforeEach
    void setUp() throws IOException {
        player = new Player();
        look = new Look(player);
    }

    @Test
    void testExecute_PlayerHasNoLocation() throws IOException {
        player.setCurrentLocation(null);

        String result = look.execute();

        assertEquals("Hráč nemá nastavenou aktuální lokaci.", result);
    }

    @Test
    void testExecute_EmptyLocation() throws IOException {
        Location location = new Location("Prázdná místnost");
        player.setCurrentLocation(location);

        String expected = "Nacházíš se v místnosti: Prázdná místnost\n" +
                "Itemy v místnosti:\nŽádné itemy v této místnosti.\n" +
                "Postavy v místnosti:\nŽádná postava v této místnosti.\n";

        String result = look.execute();

        assertEquals(expected, result);
    }

    @Test
    void testExecute_LocationWithItemsAndNPCs() throws IOException {
        Location location = new Location("prostreno");
        Item item = new Item("houskovyknedlik");
        NPC npc = new NPC("Lucie", "agresivni", 100);

        location.addItem(item);
        location.addNPC(npc);
        player.setCurrentLocation(location);

        String expected = "Nacházíš se v místnosti: prostreno\n" +
                "Itemy v místnosti:\n- houskovyknedlik\n" +
                "Postavy v místnosti:\n- Lucie: agresivniHP: 100\n";

        String result = look.execute();

        assertEquals(expected, result);
    }
}
