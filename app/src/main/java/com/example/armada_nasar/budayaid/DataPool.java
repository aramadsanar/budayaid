package com.example.armada_nasar.budayaid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataPool {
    public ArrayList<Budaya> test = new ArrayList<Budaya>();
    public ArrayList<Budaya> test2 = new ArrayList<Budaya>();
    public Map<String, ArrayList<Budaya>> dict = new HashMap<String, ArrayList<Budaya>>();

    public DataPool() {
        test.add(new Budaya("tesdatapool", "asal1", "tes", 0));
        dict.put("tes", test);

        test2.add(new Budaya("tesdatapool2", "asal2", "tes2", 0));
        dict.put("tes 2", test2);
    }

    public static String[][] data = new String[][]{
            {"Budaya 1", "https://wisatabaru.com/wp-content/uploads/2017/11/pulau-derawan-1.jpg"},
            {"Budaya 2","https://karental.id/wp-content/uploads/2017/05/Jakarta.original.4635.jpg"},
    };

    public static ArrayList<Budaya> getListData() {
        Budaya budaya = null;
        ArrayList<Budaya> list = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            //buat objek president dari class President.class
            budaya = new Budaya();
            budaya.setmAsalKotaBudaya(data[i][0]); //baris i kolom 0 = nama
            budaya.setmImgURLBudaya(data[i][1]); //baris i kolom 1 = jabatan

            list.add(budaya);
        }
        return list;
    }

}
