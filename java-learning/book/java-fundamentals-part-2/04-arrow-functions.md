# Arrow Functions (Java Equivalent)

## Introduction to Arrow Functions
In JavaScript, arrow functions provide a shorter way to write function expressions. In Java, however, there is no direct equivalent to JavaScript’s arrow functions, because Java does not have standalone functions. Instead, Java uses **lambda expressions**, which were introduced in Java 8 and represent a more concise way to express behavior, but only in contexts where functional interfaces are expected.

Let’s explore how Java handles concepts that loosely resemble arrow functions, while noting important differences.

## Lambda Expressions in Java
Lambda expressions in Java are used to implement functional interfaces—interfaces with exactly one abstract method. Unlike JavaScript arrow functions, Java lambdas **cannot exist on their own** and must always be associated with a functional interface.

Let’s create an example similar to the JavaScript version that calculates age from a birth year.

In Java, we first define a functional interface:

```java
@FunctionalInterface
interface AgeCalculator {
    int calculate(int birthYear);
}
```

Now, we can implement it using a lambda expression:

```java
AgeCalculator calcAge = birthYear -> 2037 - birthYear;
```

This lambda expression is shorter than a traditional method and behaves like a small piece of reusable logic.

To use it:

```java
int age = calcAge.calculate(1991);
System.out.println(age); // 46
```

This structure mirrors the idea of assigning an arrow function to a variable in JavaScript, although the Java version must follow the rules of functional interfaces.

## Lambda Expressions with Multiple Lines of Code
Simple Java lambdas can omit curly braces and the return keyword when the expression is a single line. But when the logic expands, curly braces and an explicit return statement are required.

For example, calculating years until retirement:

```java
@FunctionalInterface
interface RetirementCalculator {
    int calculate(int birthYear);
}

RetirementCalculator yearsUntilRetirement = birthYear -> {
    int age = 2037 - birthYear;
    int retirement = 65 - age;
    return retirement;
};
```

To use it:

```java
System.out.println(yearsUntilRetirement.calculate(1991)); // 19
```

Just like JavaScript arrow functions, multi-line lambdas require braces and explicit returns.

## Lambda Expressions with Multiple Parameters
Java lambdas support multiple parameters, but we must still use a functional interface.

```java
@FunctionalInterface
interface RetirementMessage {
    String message(int birthYear, String firstName);
}

RetirementMessage yearsUntilRetirementMessage = (birthYear, firstName) -> {
    int age = 2037 - birthYear;
    int retirement = 65 - age;
    return firstName + " retires in " + retirement + " years.";
};
```

```java
System.out.println(yearsUntilRetirementMessage.message(1991, "Jonas"));
System.out.println(yearsUntilRetirementMessage.message(1980, "Bob"));
```

As the logic grows more complex, the benefits of the shorter lambda syntax become less noticeable.

## When to Use Lambda Expressions
You might wonder whether lambda expressions should replace regular methods. The answer is: **it depends**.

Lambda expressions are excellent for short, simple behaviors, especially when working with APIs that expect functional interfaces, such as the Java Streams API. However:

- Lambdas cannot exist independently; they require a functional interface.
- They do not replace ordinary methods, which are still needed for most class logic.
- They have different behavior around concepts like scoping compared to full methods.

For these reasons, Java developers typically use lambda expressions for concise, simple tasks and traditional methods for broader logic.

## Key Takeaways
- Java does not support standalone arrow functions like JavaScript.
- Java provides lambda expressions, which are concise implementations of functional interfaces.
- Lambdas allow implicit returns for single-line expressions.
- Multi-line lambdas require curly braces and explicit return statements.
- Lambdas must be tied to a functional interface and cannot replace normal methods.
