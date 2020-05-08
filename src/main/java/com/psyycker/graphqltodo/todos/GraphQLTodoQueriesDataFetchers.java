package com.psyycker.graphqltodo.todos;

import com.google.common.collect.ImmutableMap;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

import static com.psyycker.graphqltodo.todos.Storage.categories;
import static com.psyycker.graphqltodo.todos.Storage.todos;

@Component
public class GraphQLTodoQueriesDataFetchers {




    public DataFetcher getTodosDataFetcher() {
        return dataFetchingEnvironment -> {
            return todos;
        };
    }

    public DataFetcher getTodosWithCategoryForId() {
        return dataFetchingEnvironment -> {
            String categoryId = dataFetchingEnvironment.getArgument("id");
            return todos.stream().filter(todo -> todo.get("categoryId").equals(categoryId)).collect(Collectors.toList());
        };
    }

    public DataFetcher getTodosWithCategoryForName() {
        return dataFetchingEnvironment -> {
            String categoryName = dataFetchingEnvironment.getArgument("name");
            Optional<Map<String, String>> category = categories.stream().filter(_category -> _category.get("name").equals(categoryName)).findFirst();
            if (category.isEmpty()) {
                return null;
            }
            return todos.stream().filter(todo -> todo.get("categoryId").equals(category.get().get("id"))).collect(Collectors.toList());
        };
    }

    public DataFetcher getTodoByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String todoId = dataFetchingEnvironment.getArgument("id");
            return todos
                    .stream()
                    .filter(todo -> todo.get("id").equals(todoId))
                    .findFirst()
                    .orElse(null);
        };
    }

    public DataFetcher getCategoryDataFetcher() {
        return dataFetchingEnvironment -> {
            Map<String,String> todo = dataFetchingEnvironment.getSource();
            String categoryId = todo.get("categoryId");
            return categories
                    .stream()
                    .filter(category -> category.get("id").equals(categoryId))
                    .findFirst()
                    .orElse(null);
        };
    }

}
