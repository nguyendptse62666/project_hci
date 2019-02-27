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
        list.add(new ObjectPlay(category, "Con cá", "Fish", R.drawable.animal_fish, answer,"underwater"));
        list.add(new ObjectPlay(category, "Con gấu", "Bear", R.drawable.animal_bear, answer));
        answer = new String[]{"Whale","Shark","Mouse","Tiger"};
        list.add(new ObjectPlay(category, "Cá voi","Whale", R.drawable.animal_whale, answer,"underwater"));
        list.add(new ObjectPlay(category, "Cá mập","Shark", R.drawable.animal_shark, answer,"underwater"));
        list.add(new ObjectPlay(category, "Con hổ","Tiger", R.drawable.animal_tiger, answer));
        list.add(new ObjectPlay(category, "Con chuột","Mouse", R.drawable.animal_mouse, answer));
        answer = new String[]{"Octopus","Monkey","Buffalo","Elephant"};
        list.add(new ObjectPlay(category, "Con bạch tuột","Octopus", R.drawable.animal_octopus, answer,"underwater"));
        list.add(new ObjectPlay(category, "Con khỉ","Monkey", R.drawable.animal_monkey, answer));
        list.add(new ObjectPlay(category, "Con trâu","Buffalo", R.drawable.animal_buffalo, answer));
        list.add(new ObjectPlay(category, "Con voi","Elephant", R.drawable.animal_elephant, answer));
        answer = new String[]{"Zebra","Horse","Lion","Bird"};
        list.add(new ObjectPlay(category, "Con ngựa vằn","Zebra", R.drawable.animal_zebra, answer));
        list.add(new ObjectPlay(category, "Con ngựa","Horse", R.drawable.animal_horse, answer));
        list.add(new ObjectPlay(category, "Con sư tử","Lion", R.drawable.animal_lion, answer));
        list.add(new ObjectPlay(category, "Con chim","Bird", R.drawable.animal_bird, answer));
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

        answer = new String[]{"Ruler","Pencil","Book","Eraser"};
        list.add(new ObjectPlay(category, "Cái thước", "Ruler", R.drawable.thing_ruler, answer, "school"));
        list.add(new ObjectPlay(category, "Bút chì", "Pencil", R.drawable.thing_pencil, answer, "school"));
        list.add(new ObjectPlay(category, "Quyển sách", "Book", R.drawable.thing_book, answer,"school"));
        list.add(new ObjectPlay(category, "Cục tẩy", "Eraser", R.drawable.thing_eraser, answer,"school"));

        answer = new String[]{"Bike","Motorbike","Car","Train"};
        list.add(new ObjectPlay(category, "Xe đạp", "Bike", R.drawable.thing_bike, answer, "traffic"));
        list.add(new ObjectPlay(category, "Xe máy", "Motorbike", R.drawable.thing_motorbike, answer,"traffic"));
        list.add(new ObjectPlay(category, "Xe hơi", "Car", R.drawable.thing_car, answer,"traffic"));
        list.add(new ObjectPlay(category, "Tàu hỏa", "Train", R.drawable.thing_train, answer,"traffic"));

        answer = new String[]{"Computer","Candy","Ball","Window"};
        list.add(new ObjectPlay(category, "Máy tính", "Computer", R.drawable.thing_computer, answer));
        list.add(new ObjectPlay(category, "Kẹo", "Candy", R.drawable.thing_candy, answer));
        list.add(new ObjectPlay(category, "Cái bóng", "Ball", R.drawable.thing_ball, answer));
        list.add(new ObjectPlay(category, "Của sổ", "Window", R.drawable.thing_window, answer));

        return list;
    }

    private List<ObjectPlay> initializeNatureList(){
        List<ObjectPlay> list = new ArrayList<>();
        String category = "nature";
        String answer[] = {"Sun","Cloud","Moon","Star"};
        list.add(new ObjectPlay(category, "Mặt trời", "Sun", R.drawable.nature_sun, answer));
        list.add(new ObjectPlay(category, "Mặt trăng", "Moon", R.drawable.nature_moon, answer,"darksky"));
        list.add(new ObjectPlay(category, "Đám mây", "Cloud", R.drawable.nature_cloud, answer));
        list.add(new ObjectPlay(category, "Ngôi sao", "Star", R.drawable.nature_star, answer, "darksky"));

        answer = new String[]{"Ocean","Snow","Rain","Mountain"};
        list.add(new ObjectPlay(category, "Biển", "Ocean", R.drawable.nature_ocean, answer));
        list.add(new ObjectPlay(category, "Tuyết", "Snow", R.drawable.nature_snow, answer));
        list.add(new ObjectPlay(category, "Mưa", "Rain", R.drawable.nature_rain, answer));
        list.add(new ObjectPlay(category, "Ngọn núi", "Mountain", R.drawable.nature_mountain, answer));

        answer = new String[]{"Tree","Sky","Flower","Fruit"};
        list.add(new ObjectPlay(category, "Cái cây", "Tree", R.drawable.nature_tree, answer));
        list.add(new ObjectPlay(category, "Bầu trời", "Sky", R.drawable.nature_sky, answer));
        list.add(new ObjectPlay(category, "Hoa", "Flower", R.drawable.nature_flower, answer));
        list.add(new ObjectPlay(category, "Trái cây", "Fruit", R.drawable.nature_fruit, answer));


        return list;
    }
}
