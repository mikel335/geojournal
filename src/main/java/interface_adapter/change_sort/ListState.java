package interface_adapter.change_sort;

import java.util.ArrayList;

/**
 * The state information representing the list view/
 */
public class ListState {
    private ArrayList<String>[] list = new ArrayList[2];
    private int sort = 0;
    private String sortError;

    public ListState(ListState listState) {
        list = listState.list;
        sort = listState.sort;
    }

    public ListState() {

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
}
