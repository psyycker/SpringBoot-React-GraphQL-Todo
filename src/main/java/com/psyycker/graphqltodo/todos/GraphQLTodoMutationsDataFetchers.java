package com.psyycker.graphqltodo.todos;

import com.google.common.collect.ImmutableMap;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

import static com.psyycker.graphqltodo.todos.Storage.categories;
import static com.psyycker.graphqltodo.todos.Storage.todos;

@Component
public class GraphQLTodoMutationsDataFetchers {


    public DataFetcher getTodosDataFetcher() {
        return dataFetchingEnvironment -> {
            return todos;
        };
    }


    private String getIdForNewTodo() {
        int higherFound = -1;
        for (Map<String, Object> todo: todos) {
            if (Integer.parseInt((String) todo.get("id")) > higherFound) {
                higherFound = Integer.parseInt((String) todo.get("id"));
            }
        }
        return String.valueOf(higherFound + 1);
    }

    public DataFetcher addTodo() {
        return dataFetchingEnvironment -> {
            Map<String, Object> arguments = dataFetchingEnvironment.getArguments();
            Map<String, Object> newTodo = new HashMap<>();
            for (String key: arguments.keySet()) {
                newTodo.put(key, arguments.get(key));
            }
            newTodo.put("id", getIdForNewTodo());
            todos.add(newTodo);
            return newTodo;
        };
    }

    public DataFetcher setCategory() {
        return dataFetchingEnvironment -> {
            String todoId = dataFetchingEnvironment.getArgument("todoId");
            String categoryId = dataFetchingEnvironment.getArgument("categoryId");
            Map<String, Object> todo = todos.stream().filter(item -> item.get("id").equals(todoId)).findAny().orElse(null);
            if (Objects.isNull(todo)) {
                return null;
            }
            Map<String, String> category = categories.stream().filter(item -> item.get("id").equals(categoryId)).findAny().orElse(null);
            if (Objects.isNull(category)) {
                return null;
            }
            todo.put("categoryId", categoryId);
            return todo;
        };
    }

    public DataFetcher removeTodo() {
        return dataFetchingEnvironment -> {
            String todoId = dataFetchingEnvironment.getArgument("todoId");
            todos = todos.stream().filter(todo -> !todo.get("id").equals(todoId)).collect(Collectors.toList());
            Map<String, Boolean> response = new HashMap<>();
            response.put("success", true);
            return response;
        };
    }

    public DataFetcher changeCompletionState() {
        return dataFetchingEnvironment -> {
            String todoId = dataFetchingEnvironment.getArgument("todoId");
            Boolean completion = dataFetchingEnvironment.getArgument("completion");
            Map<String, Object> todo = todos.stream().filter(item -> item.get("id").equals(todoId)).findFirst().orElse(null);
            if (Objects.isNull(todo)) {
                return null;
            }
            todo.put("done", completion);
            return todo;
        };
        }
}
