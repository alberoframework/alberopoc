# Albero

Albero aims to make the development and maintenance of applications as simple as possible by using a well defined set of abstractions as the building blocks of an application's model

Getting the abstractions right makes managing the complexity of applications easier, an example of this is Domain Driven Design.

This repository contains a proof of concept of using four simple abstractions to model an application, its only purpose is to explore these concepts further.


## TREE Model

We think that it takes just four abstractions to model an application's state and the interactions with it:

**T**ypes, **R**equests, **E**vents and **E**ntities, we will use the acronym TREE to refer to this model.

### Types

The simplest definition of a type is a value space, that is, a set of possible values that can be assigned to a variable/parameter/property of that type.

This may be represented by a class in object oriented languages for example.

Simple types or also called "Value Types" are types that do not have an identity and can be composed to create more complex types, they are also called "Value Objects" in Domain Driven Design.

### Requests

There are two types of requests: Queries and Commands.

Queries do not have side effects and their aim is to retrieve information by reading the state of the system (entities) and then filter and/or do some calculations before returning the result to the client.

Commands do have side effects, their purpose is to modify the state of the system (entities).

Queries and Commands are called requests because a client ASKS the system to get data or modify data and that request can be DENIED based on the authorization rules and/or the state of the system.

### Events

Events represent changes to the system's state (entities) that other parties may be interested in.

Events are sent to listeners/subscribers and cannot be "Rejected", because they just communicate that a change in the system's state has already happened.

### Entities

Entities represent the state of the system. An individual entity has an identity that allows the system to keep track of the changes that were made to its state.


## Usage

This proof of concept contains the following features:

* Request Handling
* Entities
* REST with HATEOAS
* Content Conversion
* Request Authorization
* BDD Testing
* Useful Utils like Simple Validation and a BaseObject (for equals, hashcode and toString support).
* Integration with Spring (mainly for persistence and spring boot)

You can check out the [issuetracker sample application](https://github.com/alberoframework/alberopoc/tree/master/alberopoc-samples/alberopoc-sample-issuetracker-service) to see a demo of all these features and how to put together a simple application using spring boot.

### Declaring the dependency

You can declare it as a maven dependency in a java project, its enough to reference "alberopoc-spring", we didnt publish this to maven central as it is just a PoC to explore these abstractions, but you can still use it as a maven dependency by adding a repository to your configuration:

Maven:

```xml
<repositories>
    <repository>
        <id>albero-mvn-repo</id>
        <url>https://github.com/alberoframework/alberopoc/raw/master/mvn-repo</url>
    </repository>
</repositories>
<dependency>
    <groupId>com.alberoframework</groupId>
    <artifactId>alberopoc-spring</artifactId>
    <version>1.2</version>
</dependency>
```

Gradle:

```groovy
repositories {
    mavenCentral()
    maven {
        url 'https://github.com/alberoframework/alberopoc/raw/master/mvn-repo'
    }
    mavenLocal()
}

dependencies {
	compile 'com.alberoframework:alberopoc-spring:1.2'
}
```

### Creating an Entity

Creating an entity is very simple, you just have to extend the AbstractEntity class or implement the Entity interface.

Its important that if you implement the Entity Interface you extend BaseObject or implement the equals method using all of the entity's properties (which is what the BaseObject does for you), otherwise testing wont work.

```java
public class MyEntity extends AbstractEntity<String> {
    private String id;    

    @Override
    public String identity() {
        return id;
    }
}
```

### Requests and Envelopes

To implement a request (query or command) you have to create an object that represents the request and a request handler which executes the request.

Requests are passed along inside an envelope that can contain useful context information, in this PoC we only implemented an envelop that contains the current user that sent this request (after being authenticated by the framework).

Handlers can receive the envelope and there are simpler abstract handlers that receive just the plain request when you dont need the metadata contained in the envelope.

### Creating a Command

A Command is just a POJO that implements the Command interface or a class that extends AbstractCommand, and a CommandHandler which is a class that implements only one method for handling the Command.

```java
//The Generic Parameter is the type of the RESPONSE of the command
public class MyCommand extends AbstractCommand<String> {
    private String aString;
}
```

```java
public class MyCommandHandler extends AbstractSimpleAuthenticatedCommandHandler<MyCommand, String> {
    @Override
    protected String doHandle(MyCommand command, ContextualizedQueryGateway queryGateway, 
              ContextualizedCommandGateway commandGateway) {
        //Do something
        return "someString" //the response of the command
    }
}
```

A Command handler also receives a QueryGateway and a CommandGateway that allows it to execute other Commands and Queries.

### Creating a Query

Similarly a Query is just a POJO that implements the Query interface or a class that extends AbstractQuery, and a QueryHandler which is a class that implements only one method for handling the Query.

```java
//The Generic Parameter is the type of the RESPONSE of the query
public class MyQuery extends AbstractQuery<List<String>> {
    private String aString; // use the properties of the query to filter results or as parameters for calculations with the data you are querying
}
```

```java
public class MyQueryHandler extends AbstractSimpleAuthenticatedQueryHandler<MyQuery, String> {
    @Override
    protected List<String> doHandle(MyQuery query, ContextualizedQueryGateway queryGateway) {
        //Do something
        return aList; //the response of the command
    }
}
```

A Query handler also receives a QueryGateway and that allows it to execute other Queries, it doesnt receive a CommandGateway because queries should not alter the state of the system.

### BDD Testing

The framework has a BDD style declarative dsl to test requests, and if you are using spring with spring data, all you need to do is create an abstract support test class that contains the root package so that it can scan all your spring data repositories, if your tests extend that class you dont have to worry about infrastructure stuff and just write the given/when/then tests in a declarative way.

As explained above, the state of the system is represented by entities, so the basic syntax for testing requests is:

```java
  given (entities)
  
  when (request)
  
  then (result)
```

What "result" means depends on whether you are testing a query or a command. 

### Testing a Command

The syntax for testing commands is:

```java
  given (
    [entities],
    [requestStubs]
  )
  when ([command] executed as [user])
  then (
    [responseFromTheCommand],
    [entities],
    [requestsSent]
  )
  OR
  then ( [exceptionType] )
```

That is, given the state of the system as a set of entities and eventually stubs for requests that the commandHandler will make, 
after the execution of the command as a given user (which is anonymous if no user is specified) then we expect a given output/response
from the command, we expect the state of the system to be a new set of entities (the command's side effect) and we expect the command
sent a set of requests OR we expect the execution of the command will throw an exception of a given type.

Of course you dont have to include all of this arguments for each test, in most cases at least a couple of them wont be present, all of them
are optional except the request in the "when", and "then" has to have at least one parameter.

Here is a specific example:

```java
  public class UpdateSomeEntityCommandHandlerTest extends MyAppCommandHandlerTestSupport<UpdateSomeEntityCommandHandler, UpdateSomeEntityCommand, VoidUnit> {
	
      @Test
      public TestSpecification testSuccess() {
        return   given(
                  entities(
                    new SomeEntity("id", "aProperty"),
                    new SomeEntity("id2", "anotherProperty")
                  )
                )
                .when(handle(command(new UpdateProjectCommand("id", "aProperty modified"), "someUserId")))
                .then(
                  entities(
                    new SomeEntity("id", "aProperty modified"),
                    new SomeEntity("id2", "anotherProperty")
                  )
                 );
      }

}
```


### Testing a Query

The syntax for testing a query is similar to that of a command:

```java
  given (
    [entities],
    [queryStubs]
  )
  when ([query] executed as [user])
  then (
    [responseFromTheQuery],
    [otherQueriesSent]
  )
  OR
  then ( [exceptionType] )
```

That is, given the state of the system as a set of entities and eventually stubs for requests that the queryHandler will make, 
after the execution of the query as a given user (which is anonymous if no user is specified) then we expect a given output/response
from the query, we expect the state of the system to be EXACTLY THE SAME (queries cannot have side effiects) and we expect the query
sent a set of other queries (queries cannot send commands) OR we expect the execution of the query will throw an exception of a given type.

Again, you dont have to include all of this arguments for each test all of them are optional except the request in the "when", and "then" 
has to have at least one parameter.

Here is a specific example, notice that once you specified the initial state of the system you can chain when/then test cases,
this helps reduce the boilerplate by grouping query test cases by "initial state of the system", you can also do this with commands but since
they alter the state of the system its recommended to have one test method per command.

```java
  public class SomeQueryHandlerTest extends MyAppQueryHandlerTestSupport<SomeQueryHandler, SomeQuery, String> {
	
      @Test
      public TestSpecification testSuccess() {
        return   given(
                  entities(
                    new SomeEntity("id", "aProperty"),
                    new SomeEntity("id2", "anotherProperty")
                  )
                )
                .when(handle(query(new SomeQuery("someProperty"), "someUserId")))
                .then("someResult")
                .when(handle(query(new SomeQuery("someProperty2"), "someUserId")))
                .then("someResult2")
                .when(handle(query(new SomeQuery("someProperty3"), "someUserId")))
                .then("someResult3");
      }

}
```

### Request Url Mapping

You can map a Request to a url if you want to expose it as an HTTP service, you can do this by registering a request and a url that maps to it to the RequestUrlMapper:

```java
  requestUrlMapper.registerQuery("/api/aContextName/{propertyName}/aQuery", AQuery.class);
  requestUrlMapper.registerCommand("/api/aContextName/{propertyName}/aCommand", ACommand.class);
```
The parts of url in brackets like {propertyName} are mapped to a property name in the request. 
Commands are mapped to POST HTTP requests, Queries are mapped as GET HTTP requests.

### Hypermedia

The framework also implements a very simple way of serializing objects as hypermedia resources, that is resources that have hyperlinks to other resources.

The only format implemented for now is one that is similar to HAL, but the API is independent of the format.

To serialize an object as a hypermedia resource, you only need to wrap it in a HypermediaObjectResource container and then you can add links to requests using that container:

```java
  HypermediaObjectResource<SomeObject> hypermediaObject = 
                  new HypermediaObjectResource<>(new SomeObject("property1 value", "property2 value"))
                           .withLink("aLink", new SomeQuery())
                           .withLink("aLink2", new SomeCommand());
  
```

The object would be serialized as follows:

```json
  {
    "property1": "property1 value",
    "property2": "property2 value",
    "_links": {
      "aLink": {
        "rel": "aLink",
        "url": "/url/of/SomeQuery/instance",
        "template": false
      },
      "aLink2": {
        "rel": "aLink2",
        "url": "/url/of/SomeCommand/instance",
        "template": false
      }
    }
  }
```

You can also serialize a collection of hypermedia objects using the same principle, collections are hypermedia resources themselves and they contain other hypermedia resources:

```java
  //Using Java 8 streams, you can do it in the classic impertive way as well of course
  listOfSomeObject.stream()
                  .map(o -> new HypermediaObjectResource<>(o)
                                 .withLink("aLink", new SomeQuery())
                                 .withLink("aLink2", new SomeCommand())
                  )
                  .collect(HypermediaCollectors.toObjectCollection())
                  .withLink("anotherLink", new SomeOtherQuery()) 
                  /* this last link belongs to the collection, as opposed to the others 
                     that belong to each element of the collection */
```

The collection would be serialized as follows (assuming there are two elements in the list of objects):

```json
  {
    "elements": [
        {
          "property1": "property1 value",
          "property2": "property2 value",
          "_links": {
            "aLink": {
              "rel": "aLink",
              "url": "/url/of/SomeQuery/instance",
              "template": false
            },
            "aLink2": {
              "rel": "aLink2",
              "url": "/url/of/SomeCommand/instance",
              "template": false
            }
          }
        },
        {
          "property1": "another property1 value",
          "property2": "another property2 value",
          "_links": {
            "aLink": {
              "rel": "aLink",
              "url": "/url/of/SomeQuery/instance",
              "template": false
            },
            "aLink2": {
              "rel": "aLink2",
              "url": "/url/of/SomeCommand/instance",
              "template": false
            }
          }
        }
    ],
    "_links": {
      "aLink": {
        "rel": "anotherLink",
        "url": "/url/of/SomeOtherQuery/instance",
        "template": false
      }
    }
  }
```

With this simple tools its possible to build HATEOAS applications and adding things like metadata and constraint serialization (which will be added in the next version) would make possible to create smart dynamic clients that can render a UI based only on the hypermedia responses.


### Request Authorization

You can define the logic of authorization for each request using the authorizationGateway, it maps a request to a function that returns a boolean (predicate) or a predicate query.
A "Predicate Query" is a query whose response type is a boolean and extends the PredicateQuery interface which makes it composable with other Predicate queries.

The function that returns a predicate query receives an envelope, that contains the instance of the request and the user that sent it.

Here is an example of registering an authorization rule using a predicate query:

```java
authorizationGateway.registerAuthorizationSpecification(SomeRequest.class,
        env -> new SomeComplicatedPredicateQuery(env.user(), env.getRequest().getParameter)
);
```

The function that returns a boolean receives the envelope and it receives the queryGateway as well:

```java
authorizationGateway.registerAuthorizationSpecification(SomeRequest.class,
        env, queryGateway -> //some complicated logic
);
```

This authorization predicates will be used to deny a request based on the user that sent it and the state of the system, as expressed in the logic of the predicate functions or predicate queries.
They will also be used when serializing hypermedia responses, to show/hide links to queries/commands based on whether or not the user that got the response has the permission to send those requests.


## Improvements

Getting the abstractions right not only helps to discourage wrong technical choices by design, it also allows for the automation of most of the things that we currently do manually (like ui rendering of components, ui validation, persistence, etc)

The aim of this project is to explore this abstractions to hopefully get to a point in which a developer can create applications very easily using a set of building blocks, in a declarative way, and writing only what is absolutely necessary and cannot be automated 
(essentially the data that represents the state of the system including its constraints/invariants and the domain logic) to have a working application that can be customized by overriding the defaults.

For the next version we are working on things like the structure of entities and the constraints of types, so that for example hypermedia APIs can be created automatically and UI components can render them automatically with sensible overrideable defaults.

We will also be using a more functional approach, trying to clearly separate side effects from the rest of the application, this will simplify things a bit (for example getting rid of repositories) and will allow the framework to optimize the execution of requests (for example paralellizing them when possible, caching the results in a request level cache, etc).

## License

The source code is licensed under the MIT License.

