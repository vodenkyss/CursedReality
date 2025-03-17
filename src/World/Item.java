package World;

public class Item {

    private String name;
    private String description;
    private int dmg;

    private String usableLocation;
    private String useActionResult;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public String getUsableLocation() {
        return usableLocation;
    }

    public void setUsableLocation(String usableInLocation) {
        this.usableLocation = usableInLocation;
    }

    public String getUseActionResult() {
        return useActionResult;
    }

    public void setUseActionResult(String useActionResult) {
        this.useActionResult = useActionResult;
    }


    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", DMG=" + dmg +
                '}';
    }
}
