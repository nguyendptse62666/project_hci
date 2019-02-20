package day01.nguyendpt.chidstudy;

import android.app.Application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GeneratorDataClass {
    private static final String ANIMAL_CATEGORY  = "ANIMAL";
    private static final String THING_CATEGORY  = "THING";
    private static final String NATURE_CATEGORY  = "NATURE";
    private Map<String, List<ObjectPlay>> dataMap;

    public GeneratorDataClass(){
        initializeData();
    }

    public void initializeData(){
        dataMap = new HashMap<>();
        dataMap.put(ANIMAL_CATEGORY, initializeAnimalList());
        dataMap.put(THING_CATEGORY, initializeThingList());
        dataMap.put(NATURE_CATEGORY, initializeNatureList());
    }

    private ObjectPlay getRandomObjectPlay(List<ObjectPlay> list){
        ObjectPlay objectPlay;
        Random random = new Random();
        objectPlay = list.get(random.nextInt(list.size()));
        return objectPlay;
    }

    public ObjectPlay getObjectPlay(String category, String recentName){
        ObjectPlay objectPlay = null;
        do{
            objectPlay = getRandomObjectPlay(dataMap.get(category.toUpperCase()));
        } while (objectPlay.getEngName() == recentName);


        return objectPlay;
    }

    private List<ObjectPlay> initializeAnimalList(){
        List<ObjectPlay> list = new ArrayList<>();
        String category = "animal";
        String answer[] = {"Cat","Dog","Bear","Fish"};
        list.add(new ObjectPlay(category, "Con chó", "Dog", R.drawable.animal_dog, answer));
        list.add(new ObjectPlay(category, "Con mèo", "Cat", R.drawable.animal_cat, answer));
        list.add(new ObjectPlay(category, "Con cá", "Fish", R.drawable.animal_fish, answer));
        list.add(new ObjectPlay(category, "Con gấu", "Bear", R.drawable.animal_bear, answer));
        return list;
    }

    private List<ObjectPlay> initializeThingList(){
        List<ObjectPlay> list = new ArrayList<>();
        String category = "thing";
        String answer[] = {"Chair","Table","Bed","Door"};
        list.add(new ObjectPlay(category, "Cái ghế", "Chair", R.drawable.thing_chair, answer));
        list.add(new ObjectPlay(category, "Cái bàn", "Table", R.drawable.thing_table, answer));
        list.add(new ObjectPlay(category, "Cái giường", "Bed", R.drawable.thing_bed, answer));
        list.add(new ObjectPlay(category, "Cánh cửa", "Door", R.drawable.thing_door, answer));
        return list;
    }

    private List<ObjectPlay> initializeNatureList(){
        List<ObjectPlay> list = new ArrayList<>();
        String category = "nature";
        String answer[] = {"Sun","Cloud","Moon","Star"};
        list.add(new ObjectPlay(category, "Mặt trời", "Sun", R.drawable.nature_sun, answer));
        list.add(new ObjectPlay(category, "Mặt trăng", "Moon", R.drawable.nature_moon, answer));
        list.add(new ObjectPlay(category, "Đám mây", "Cloud", R.drawable.nature_cloud, answer));
        list.add(new ObjectPlay(category, "Ngôi sao", "Star", R.drawable.nature_star, answer));
        return list;
    }


}
