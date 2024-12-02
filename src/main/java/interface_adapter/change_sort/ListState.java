package interface_adapter.change_sort;

import java.util.ArrayList;

/**
 * The state information representing the list view/
 */
public class ListState {
    private ArrayList<String>[] list = new ArrayList[3];
    private int sort = 0;
    private String sortError;

    public ListState(ListState listState) {
        list = listState.list;
        sort = listState.sort;
    }

    public ListState() {
        list[0] = new ArrayList<>();
        list[1] = new ArrayList<>();
        list[0].add("One");
        list[0].add("Two");
        list[0].add("Three");
        list[1].add("1");
        list[1].add("2");
        list[1].add("3");
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public ArrayList<String>[] getList() {
        return list;
    }

    public void setList(ArrayList<String>[] list) {
        this.list = list;
    }

    public String getSortError() {
        return sortError;
    }

    public void setSortError(String sortError) {
        this.sortError = sortError;
    }

    public String toString() {
        String res1 = "[";
        for (String title : list[0]){
            res1 = res1 + title + ",";
        }
        res1 = res1 + "]";
        String res2 = "[";
        for (String title : list[1]){
            res2 = res2 + title + ",";
        }
        res2 = res2 + "]";
        return res1 + res2;
    }
}
