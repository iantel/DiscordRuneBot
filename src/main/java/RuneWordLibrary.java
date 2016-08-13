import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.util.*;

/**
 * Created by Ian on 2016-08-10.
 */
public class RuneWordLibrary {

    static Map <String, RuneWeapon> weaponMap = new HashMap<>();

    static void build(String path) {
        try {
            File file = new File(path);
            Document html = Jsoup.parse(file, "UTF-8");
            List<Element> blocks = html.getElementsByTag("center").tagName("TABLE Border=0 Cellpadding=5");

            for (int i = 0; i < 2; i++){
                List<Element> runeBlock = blocks.get(i).getAllElements().select("tr");
                for (int j = 2; j < runeBlock.size(); j++){
                    List<Element> runeDetails = runeBlock.get(j).getElementsByTag("span");
                    runeDetails.get(3).select("br").append("~");

                    String [] availableWeapons = runeDetails.get(1).text().split(" ",2);
                    int sockets = Integer.parseInt(availableWeapons[0]);
                    String weaponType = availableWeapons[1];

                    String name = runeDetails.get(0).text().toUpperCase().replace("*","").trim();
                    String runeCombination = runeDetails.get(2).text();
                    String stats = runeDetails.get(3).text().replaceAll("~","\n");
                    RuneWeapon runeWeapon = new RuneWeapon(sockets, runeCombination, weaponType, stats);
                    weaponMap.put(name, runeWeapon);
                }
            }
            System.out.println("Weapons from " + path + " added to library");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static List<AbstractMap.SimpleEntry<String, RuneWeapon>> findWeapon (String query){
        String [] args = query.split(" ");
        List<AbstractMap.SimpleEntry<String, RuneWeapon>> found = new ArrayList<>();
        for (String key : weaponMap.keySet()){
            int sockets = Integer.parseInt(args[0]);
            if (weaponMap.get(key).sockets == sockets && weaponMap.get(key).weaponType.toUpperCase().contains(args[1].toUpperCase())){
                found.add(new AbstractMap.SimpleEntry<>(key, weaponMap.get(key)));
            }
        }
        return found;
    }
}
