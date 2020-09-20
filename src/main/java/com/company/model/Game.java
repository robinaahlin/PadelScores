package com.company.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<User> users;
    private int scoreTeamOne;
    private int scoreTeamTwo;

    public Game(User player1, User player2, User player3, User player4){
        users = new ArrayList<>();
        users.add(player1);
        users.add(player2);
        users.add(player3);
        users.add(player4);
    }
}
