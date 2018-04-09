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

}
