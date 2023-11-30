package edu.cs4730.lazylistsdemo3;

/*
 * from http://www.vogella.de/articles/AndroidListView/article.html
 */

public class InterActive_DataModel {
    private int id;
    private String name;
    private boolean selected;

    public InterActive_DataModel(int id, String name) {
        this.id = id;
        this.name = name;
        selected = false;
    }

    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void FUSelected(boolean selected) {
        this.selected = selected;
    }

}

