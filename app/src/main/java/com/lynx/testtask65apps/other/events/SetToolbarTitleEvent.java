package com.lynx.testtask65apps.other.events;

public class SetToolbarTitleEvent {

    private String title;

    public SetToolbarTitleEvent(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
