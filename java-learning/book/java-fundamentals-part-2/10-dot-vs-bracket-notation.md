# Dot vs. Bracket Notation in Objects

## Introduction to Object Property Access
In this lesson, we learn how to retrieve data from objects and how to change data in objects using different access approaches.

In Java, objects do not support dot and bracket notation in the same way as JavaScript. Instead, Java uses **field access**, **getter/setter methods**, and **collections** to achieve similar behavior. Where JavaScript allows dynamic property names, Java requires explicit structure defined at compile time.

---

## Dot-Style Access (Field Access)
In Java, accessing object data is typically done using **dot notation** with fields or methods.

```java
System.out.println(jonas.lastName);
```

Here, the dot operator accesses the `lastName` field directly from the `jonas` object. This is the most common and straightforward way to retrieve data from an object in Java.

The dot operator accesses a **known property name** that exists in the class definition.

---

## Bracket-Style Behavior Using Maps
Java does **not** support bracket notation for object fields. However, similar dynamic behavior can be achieved using a `Map`, where keys are strings and values are objects.

```java
Map<String, Object> jonas = new HashMap<>();
jonas.put("firstName", "Jonas");
jonas.put("lastName", "Schmedtmann");
jonas.put("age", 2037 - 1991);
jonas.put("job", "teacher");
jonas.put("friends", List.of("Michael", "Peter", "Steven"));
```

Accessing values dynamically:

```java
System.out.println(jonas.get("lastName"));
```

This closely resembles JavaScript’s bracket notation behavior.

---

## Computed Property Names
In JavaScript, bracket notation allows computed property names. Java achieves this using variables as map keys.

```java
String nameKey = "Name";
System.out.println(jonas.get("first" + nameKey));
System.out.println(jonas.get("last" + nameKey));
```

This works because map keys are evaluated at runtime. This kind of dynamic access is **not possible** with regular Java object fields.

---

## When to Use Which Approach
- Use **dot notation (fields or getters)** when the structure of the object is known and fixed.
- Use **Map-based access** when keys need to be computed or determined dynamically at runtime.

Java enforces strict structure, which makes code safer and more predictable, but less flexible than JavaScript in this area.

---

## Dynamic Property Access from User Input
In Java, user input can be used to dynamically retrieve values from a map.

```java
Scanner scanner = new Scanner(System.in);
System.out.println("What do you want to know about Jonas?");
String interestedIn = scanner.nextLine();

System.out.println(jonas.get(interestedIn));
```

Using dot notation here is not possible because Java does not support dynamic field access by name.

---

## Handling Non-Existent Properties
If a key does not exist in the map, `null` is returned.

```java
if (jonas.containsKey(interestedIn)) {
    System.out.println(jonas.get(interestedIn));
} else {
    System.out.println("Wrong request! Choose a valid property.");
}
```

---

## Adding New Properties
New entries can be added to a map at any time.

```java
jonas.put("location", "Portugal");
jonas.put("twitter", "jonasschmedtman");
```

This is similar to adding new properties to objects in JavaScript.

---

## Dynamic Sentence Construction
```java
List<String> friends = (List<String>) jonas.get("friends");
System.out.println(jonas.get("firstName") + " has " + friends.size()
        + " friends, and his best friend is called " + friends.get(0) + ".");
```

---

## Operator Precedence
In Java, method calls and field access using the dot operator have very high precedence and are evaluated from left to right, similar to JavaScript.

---

## Key Takeaways
- Java primarily uses dot notation for accessing object fields and methods.
- Java does not support bracket notation for objects; `Map` is used for dynamic key access.
- Dynamic property access must be implemented explicitly using collections.
- Non-existent keys return `null` and should be handled safely.
- Java favors structure and type safety over dynamic flexibility.
