package com.psyycker.graphqltodo.todos;

import com.google.common.collect.ImmutableMap;

import java.util.*;

public class Storage {

    public static List<Map<String, Object>> todos = new ArrayList<>(Arrays.asList(
            new HashMap<>(ImmutableMap.of("id", "1",
                    "name", "Laundry",
                    "description", "Do the laundry",
                    "done", false,
                    "categoryId", "1")),
            new HashMap<>(ImmutableMap.of("id", "2",
                    "name", "Shop",
                    "description", "Get some potatoes and meat for tonight",
                    "done", false,
                    "categoryId", "2")),
            new HashMap<>(ImmutableMap.of("id", "3",
                    "name", "Clean the fridge",
                    "description", "",
                    "done", false,
                    "categoryId", "1")),
            new HashMap<>(ImmutableMap.of("id", "4",
                    "name", "Clean the floor",
                    "description", "",
                    "done", false,
                    "categoryId", "1")),
            new HashMap<>(ImmutableMap.of("id", "5",
                    "name", "Walk the dog",
                    "description", "",
                    "done", false,
                    "categoryId", "2")),
            new HashMap<>(ImmutableMap.of("id", "6",
                    "name", "Cook",
                    "description", "",
                    "done", false,
                    "categoryId", "1"))
    ));

    public static List<Map<String, String>> categories = new ArrayList<>(Arrays.asList(
            new HashMap<>(ImmutableMap.of("id", "1",
                    "name", "House"
            )),
            new HashMap<>(ImmutableMap.of("id", "2",
                    "name", "Outside"
            ))
    ));
}
