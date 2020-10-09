package me.mrcreepton.minebattle.utils;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class ItemUtils {

    private static List<Material> foodList = new ArrayList<>();

    public static void init()
    {
        foodList.add(Material.getMaterial(322));
        foodList.add(Material.getMaterial(396));
        foodList.add(Material.getMaterial(320));
        foodList.add(Material.getMaterial(350));
        foodList.add(Material.getMaterial(424));
        foodList.add(Material.getMaterial(364));
        foodList.add(Material.getMaterial(393));
        foodList.add(Material.getMaterial(436));
        foodList.add(Material.getMaterial(434));
        foodList.add(Material.getMaterial(297));
        foodList.add(Material.getMaterial(391));
        foodList.add(Material.getMaterial(366));
        foodList.add(Material.getMaterial(412));
        foodList.add(Material.getMaterial(282));
        foodList.add(Material.getMaterial(413));
        foodList.add(Material.getMaterial(260));
        foodList.add(Material.getMaterial(432));
        foodList.add(Material.getMaterial(360));
        foodList.add(Material.getMaterial(394));
        foodList.add(Material.getMaterial(392));
        foodList.add(Material.getMaterial(400));
        foodList.add(Material.getMaterial(363));
        foodList.add(Material.getMaterial(365));
        foodList.add(Material.getMaterial(411));
        foodList.add(Material.getMaterial(319));
        foodList.add(Material.getMaterial(423));
        foodList.add(Material.getMaterial(354));
        foodList.add(Material.getMaterial(357));
        foodList.add(Material.getMaterial(349));
        foodList.add(Material.getMaterial(367));
        foodList.add(Material.getMaterial(375));

    }

    public static boolean isItemFood(Material material)
    {
        return foodList.contains(material);
    }

}
