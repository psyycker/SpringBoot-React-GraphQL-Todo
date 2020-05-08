package com.psyycker.graphqltodo;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.psyycker.graphqltodo.todos.GraphQLTodoMutationsDataFetchers;
import com.psyycker.graphqltodo.todos.GraphQLTodoQueriesDataFetchers;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

@Component
public class GraphQLProvider {

    private GraphQL graphQL;

    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }

    @PostConstruct
    public void init() throws IOException {
        URL url = Resources.getResource("schema.graphqls");
        String sdl = Resources.toString(url, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    @Autowired
    GraphQLTodoQueriesDataFetchers graphQLTodoQueriesDataFetchers;

    @Autowired
    GraphQLTodoMutationsDataFetchers graphQLTodoMutationsDataFetchers;

    private GraphQLSchema buildSchema(String sdl) {
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query")
                        .dataFetcher("todoById", graphQLTodoQueriesDataFetchers.getTodoByIdDataFetcher()))
                .type(newTypeWiring("Todo")
                        .dataFetcher("category", graphQLTodoQueriesDataFetchers.getCategoryDataFetcher()))
                .type(newTypeWiring("Query").dataFetcher("todos", graphQLTodoQueriesDataFetchers.getTodosDataFetcher()))
                .type(newTypeWiring("Query").dataFetcher("todosForCategoryId", graphQLTodoQueriesDataFetchers.getTodosWithCategoryForId()))
                .type(newTypeWiring("Query").dataFetcher("todosForCategoryName", graphQLTodoQueriesDataFetchers.getTodosWithCategoryForName()))
                .type(newTypeWiring("Mutation").dataFetcher("addTodo", graphQLTodoMutationsDataFetchers.addTodo()))
                .type(newTypeWiring("Mutation").dataFetcher("setCategoryForTodo", graphQLTodoMutationsDataFetchers.setCategory()))
                .type(newTypeWiring("Mutation").dataFetcher("removeTodo", graphQLTodoMutationsDataFetchers.removeTodo()))
                .type(newTypeWiring("Mutation").dataFetcher("changeCompletionState", graphQLTodoMutationsDataFetchers.changeCompletionState()))
                .build();
    }
}
