package com.example.teamfind;

public class CategoryList {
    public static Category[] list;
    static{
        list = new Category[38];
        list[0] = new Category("ИИ и ML", R.drawable.red);
        list[1] = new Category("Android-разработка", R.drawable.greenn);
        list[2] = new Category("IOs разработка", R.drawable.redd);
        list[3] = new Category("Веб-разработка", R.drawable.blue);
        list[4] = new Category("Разработка ПО", R.drawable.yellow);
        list[5] = new Category("Unity", R.drawable.gray);
        list[6] = new Category("Unreal Engine", R.drawable.green);
        list[7] = new Category("Математика", R.drawable.gray);
        list[8] = new Category("Базы данных", R.drawable.orange);
        list[9] = new Category("Python", R.drawable.green);
        list[10] = new Category("C", R.drawable.violet);
        list[11] = new Category("C++", R.drawable.violet);
        list[12] = new Category("C#", R.drawable.violet);
        list[13] = new Category("Java", R.drawable.orange);
        list[14] = new Category("JavaScript", R.drawable.orange);
        list[15] = new Category("Haskell", R.drawable.redd);
        list[16] = new Category("Lua", R.drawable.blue);
        list[17] = new Category("Go", R.drawable.waef);
        list[18] = new Category("Оптимизация", R.drawable.gray);
        list[19] = new Category("Kotlin", R.drawable.yellow);
        list[20] = new Category("PHP", R.drawable.gray);
        list[21] = new Category("ООП", R.drawable.red);
        list[22] = new Category("1С", R.drawable.orange);
        list[23] = new Category("Dart", R.drawable.violet);
        list[24] = new Category("Swift", R.drawable.gray);
        list[25] = new Category("Ruby", R.drawable.red);
        list[26] = new Category("Scala", R.drawable.blue);
        list[27] = new Category("Perl", R.drawable.gray);
        list[28] = new Category("Rust", R.drawable.redd);
        list[29] = new Category("Objective-C", R.drawable.gray);
        list[30] = new Category("TypeScript", R.drawable.orange);
        list[31] = new Category("Тестирование", R.drawable.yellow);
        list[32] = new Category("Администрирование", R.drawable.greenn);
        list[33] = new Category("Front-end", R.drawable.waef);
        list[34] = new Category("Back-end", R.drawable.blue);
        list[35] = new Category("Fullstack", R.drawable.redd);
        list[36] = new Category("Дизайн", R.drawable.greenn);
        list[37] = new Category("3D-моделирование", R.drawable.green);

    }
}
