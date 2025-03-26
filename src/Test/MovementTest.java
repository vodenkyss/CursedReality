package Test;

import World.LoadMap;
import World.Location;
import World.Player;
import command.Movement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MovementTest {

    private Player player;
    private LoadMap map;
    private Movement movement;
    private Location spawn;
    private Location prostreno;

    @BeforeEach
    void setUp() throws Exception {
        player = new Player();
        map = new LoadMap();
        spawn = new Location("Spawn");
        prostreno = new Location("Prostreno");


        spawn.getConnections().put("prostreno", prostreno);

        player.setCurrentLocation(spawn);
        movement = new Movement(player, map);
    }

    @Test
    void testGetNewLocation_ValidDirection() {
        Location newLocation = movement.getNewLocation("prostreno", spawn);

        assertNotNull(newLocation);
        assertEquals("Prostreno", newLocation.getName());
    }

    @Test
    void testGetNewLocation_InvalidDirection() {
        Location newLocation = movement.getNewLocation("neexistuje", spawn);

        assertNotNull(newLocation);
        assertEquals(spawn, newLocation);
    }
}
