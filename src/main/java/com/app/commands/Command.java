package com.app.commands;

public interface Command {
    void execute(String[] arguments);
    String description();
}
