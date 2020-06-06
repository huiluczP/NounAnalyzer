package data;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class DataMap {
    private ArrayList<String> templeList = new ArrayList<String>();
    private ConcurrentHashMap<Integer, String> templeMap = new ConcurrentHashMap<Integer, String>();

    public ArrayList<String> getTempleList() {
        return templeList;
    }

    public void setTempleList(ArrayList<String> templeList) {
        this.templeList = templeList;
    }

    public ConcurrentHashMap<Integer, String> getTempleMap() {
        return templeMap;
    }

    public void setTempleMap(ConcurrentHashMap<Integer, String> templeMap) {
        this.templeMap = templeMap;
    }
}
