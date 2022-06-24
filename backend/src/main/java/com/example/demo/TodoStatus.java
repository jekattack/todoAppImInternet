package com.example.demo;

public enum TodoStatus {
    OPEN,
    IN_PROGRESS,
    DONE;

    private String status;

    public TodoStatus progressNext(){
        switch(this){
            case OPEN -> {
                return TodoStatus.IN_PROGRESS;
            }
            case IN_PROGRESS -> {
                return TodoStatus.DONE;
            }
            default -> {
                return this;
            }
        }
    }

    public TodoStatus progressPrev(){
        switch(this){
            case DONE -> {
                return TodoStatus.IN_PROGRESS;
            }
            case IN_PROGRESS -> {
                return TodoStatus.OPEN;
            }
            default -> {
                return this;
            }
        }
    }
}
