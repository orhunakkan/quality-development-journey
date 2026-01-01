# Basic Array Operations (Methods)

## Introduction to Array Methods
Java provides several built-in operations that can be applied to arrays and array-like structures. While arrays themselves have limited built-in functionality, common operations are typically performed using utility classes or by working with collections such as `ArrayList`.

These operations can be thought of as **array operations** that allow us to add, remove, and search for elements.

---

## Adding Elements to the End
In Java, arrays have a fixed size, so elements cannot be added directly once the array is created. To simulate dynamic behavior similar to JavaScript arrays, we commonly use `ArrayList`.

Let us start by bringing back the `friends` collection.

```java
List<String> friends = new ArrayList<>();
friends.add("Michael");
friends.add("Steven");
friends.add("Peter");
```

To add an element to the end, we use the `add` method.

```java
friends.add("Jay");
```

After adding an element, the size of the list increases. The `add` method returns `true` if the operation succeeds.

```java
boolean added = friends.add("Jay");
System.out.println(added);
```

---

## Adding Elements to the Beginning
To add an element at the beginning, we use the overloaded `add` method that accepts an index.

```java
friends.add(0, "John");
```

This shifts all existing elements one position to the right.

---

## Removing Elements
To remove elements, Java provides the `remove` method.

To remove the last element:

```java
String removedLast = friends.remove(friends.size() - 1);
System.out.println(removedLast);
```

To remove the first element:

```java
String removedFirst = friends.remove(0);
System.out.println(removedFirst);
```

Both operations return the removed element.

---

## Finding Elements: indexOf and contains
To find the position of an element, use the `indexOf` method. It returns the index if found, or `-1` if not found.

```java
System.out.println(friends.indexOf("Steven"));
System.out.println(friends.indexOf("Bob"));
```

To check if an element exists in the list, use the `contains` method. It returns `true` or `false`.

```java
System.out.println(friends.contains("Steven"));
System.out.println(friends.contains("Bob"));
```

The `contains` method uses `equals` for comparison, meaning type and value must match.

---

## Using contains in Conditionals
The `contains` method is often used in conditional statements.

```java
if (friends.contains("Peter")) {
    System.out.println("You have a friend called Peter");
}

if (friends.contains("Steven")) {
    System.out.println("You have a friend called Steven");
}
```

---

## Conclusion
Java arrays themselves are limited due to their fixed size, but by using collections such as `ArrayList`, we can perform common operations like adding, removing, and searching for elements efficiently.

---

## Key Takeaways
- Java arrays have a fixed size and do not support dynamic operations directly.
- `ArrayList` is commonly used for dynamic array-like behavior.
- Elements can be added or removed using `add` and `remove` methods.
- `indexOf` helps locate elements, while `contains` checks for their existence.
