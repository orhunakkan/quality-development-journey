# Reviewing Methods in Java

## Reviewing Methods
To conclude our discussion on methods, let’s review everything important learned so far. This review helps ensure a solid understanding before moving on to new topics.

We will revisit a method that calculates the years until retirement. Instead of rewriting a lambda expression or arrow-style syntax—as Java does not have standalone arrow functions—we will directly write standard methods, which are the primary building blocks in Java.

First, let’s write a method named `calcAge` that takes a birth year and returns the resulting age by subtracting it from the current year (2037 in this example):

```java
public static int calcAge(int birthYear) {
    return 2037 - birthYear;
}
```

It’s possible to use the same parameter name across multiple methods. In Java, parameters are **local to their methods**, so multiple methods can use a parameter named `birthYear` without conflict.

Next, we will write a method named `yearsUntilRetirement` that calls `calcAge` internally. This demonstrates how methods can work together, forming method composition.

```java
public static int yearsUntilRetirement(int birthYear, String name) {
    int age = calcAge(birthYear);
    int retirement = 65 - age;
    return retirement;
}
```

To see the data flow, calling `yearsUntilRetirement(1991, "Jonas")` triggers:
- `calcAge(1991)` → returns 46  
- `65 - 46` → returns 19

If you find repeating parameter names confusing, they can be renamed independently, such as `year` or `bYear`.

Next, let’s update the logic so the method returns a meaningful value even if the person has already retired. We can add a simple conditional:

```java
public static int yearsUntilRetirement(int birthYear, String name) {
    int age = calcAge(birthYear);
    int retirement = 65 - age;

    if (retirement > 0) {
        return retirement;
    } else {
        return -1;
    }
}
```

Testing with `1950` returns `-1`, indicating the person is already retired.

In Java, the `return` statement **immediately ends method execution**. Any code written after `return` will not run. For example:

```java
return retirement;
// System.out.println("This will not run.");
```

To ensure logs appear before returning, simply place them above the return statement.

## Summary of Method Types
Java supports only one true form of method definition:

- **Method Declarations:** Methods defined inside classes using a return type, name, parameters, and a method body.

Java does not support function expressions or arrow functions as standalone constructs, but it does support lambda expressions, which can only be used when working with functional interfaces.

## Structure of a Common Method
A typical Java method contains:

- A method name (`calcAge`)
- Parameters acting as local variables
- A method body containing processing logic
- A return statement that outputs a value and halts method execution

## Calling Methods
Methods are called using their name followed by parentheses. Arguments passed into the parentheses replace the parameters inside the method. After a method finishes, the call expression is replaced by its returned value.

## Distinction Between System.out.println and return
- `System.out.println()` prints output to the console.
- `return` outputs a value from the method and stops execution of the method.

Printing does not affect the returned value, and returning does not print anything. Both serve different purposes.

This concludes our review of methods. Understanding these foundations is essential before moving on to writing your first more complex method.

## Key Takeaways
- Methods in Java can call other methods, enabling modular and reusable code.
- Parameter names are local to each method and do not conflict with other methods.
- A return statement immediately exits a method.
- Java uses method declarations for all reusable behavior, with lambda expressions available only in specific functional interface contexts.
