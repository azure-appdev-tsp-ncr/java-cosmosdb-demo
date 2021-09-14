package com.ghoelzer_rht.cosmosdb.demo;

import java.util.Optional;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.CosmosDBInput;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

/**
 * Azure Functions with HTTP Trigger.
 * 
 * The following example shows a Java function that retrieves a single document. 
 * The function is triggered by an HTTP request that uses route data to specify 
 * the ID to look up. 
 * That ID is used to retrieve a ToDoItem document from the specified database and collection.
 */

public class CityByCountryStateFromQueryString {

    /**
     * This function listens at endpoint "/api/CityByCountryStateFromQueryString". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/CityByCountryStateFromQueryString
     * 2. curl {your host}/api/CityByCountryStateFromQueryString?id=<someid>
     */

    @FunctionName("CityByCountryStateFromQueryString")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", 
              methods = {HttpMethod.GET, HttpMethod.POST}, 
              authLevel = AuthorizationLevel.ANONYMOUS) 
            HttpRequestMessage<Optional<String>> request,  
            CosmosDBInput(name = "database",
                databaseName = "ghasp_java_demo",
                collectionName = "cities_demo",
                SqlQuery = "select * from Items c where c.country_id = {Query.city_name} and {Query.state_code}",
                ConnectionStringSetting = "CosmosDBConnection")
                CityItem[] cities,      
            final ExecutionContext context) {
        
        // Item list
        context.getLogger().info("Parameters are: " + request.getQueryParameters());
        //context.getLogger().info("String from the database is " + item.get());

        // Convert and display
        if (cities == null) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                          .body("No documents found.")
                          .build();
        }
        else {
            return request.createResponseBuilder(HttpStatus.OK)
                          .header("Content-Type", "application/json")
                          .body(cities)
                          .build();
            }
        }
    }
}
