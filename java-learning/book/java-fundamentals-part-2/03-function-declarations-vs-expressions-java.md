# Method Declarations vs. Method Expressions in Java

## Introduction to Method Types in Java
In Java, we write reusable blocks of code called methods. While Java does not support multiple syntactic forms of functions the way JavaScript does, it does allow different ways of organizing and using methods. Each approach works in a slightly different way, but the core idea remains the same. Let’s explore these now.

## Method Declarations
Methods in Java must be placed inside a class. A method declaration includes a return type, a method name, optional parameters, and a method body contained within curly braces `{}`.

Let’s write a method that calculates a person’s age based on their birth year.

This method will be named `calcAge1`. It takes a birth year as an input parameter. A parameter behaves like a local variable and exists only inside the method. To calculate the age, we subtract the provided birth year from a chosen current year, such as 2037, and return the result.

```java
public static int calcAge1(int birthYear) {
    return 2037 - birthYear;
}
```

This method works for any birth year passed to it.

## Calling Method Declarations
To call or invoke this method, we use the method name followed by parentheses that hold the argument value.

The argument is the actual value provided to the method parameter.

For example, calling `calcAge1(1991)` will compute the age for someone born in 1991. The returned value can be stored in a variable.

```java
int age1 = calcAge1(1991);
System.out.println(age1); // Outputs: 46
```

## Method Expressions
Java does not support function expressions in the same way. JavaScript allows assigning anonymous functions to variables, but Java requires that all methods belong to a class and have defined names.

However, Java does have **lambda expressions**, which can represent anonymous behavior. Lambda expressions serve a similar purpose but are only valid in contexts where functional interfaces are expected. This is not the same as JavaScript’s general-purpose function expressions, so it is important to note that Java does not have a direct equivalent.

Since a full method must have a name and cannot exist anonymously inside a variable, Java has only one true form of method definition: the declaration.

## Calling Lambda-like Behavior
Because lambda expressions in Java require a functional interface, they cannot replace normal method declarations in general programming. Therefore, everyday method usage relies entirely on declared methods.

## Methods as Values
In JavaScript, functions are values, but in Java, methods themselves are not values. They cannot be stored directly in variables. However, lambda expressions and method references can act as values, but again, only within the rules of functional interfaces.

Java method declarations themselves cannot be assigned or treated as standalone values.

## Key Difference: Hoisting
Hoisting does not exist for Java methods. Java requires that methods be fully declared before they are used, according to normal compilation rules. You cannot call a method before its class is known by the compiler. JavaScript’s behavior of calling function declarations before definition does not apply in Java.

## Which to Use?
Since Java supports only one true form of method creation, developers do not choose between declarations and expressions. Instead, methods are always defined by declaration inside a class. For situations requiring anonymous behavior, lambda expressions can be used, but these are not substitutes for general-purpose method definitions.

## Key Takeaways
- Java uses method declarations inside classes as its primary way of defining reusable behavior.
- Java does not support function expressions like JavaScript.
- Lambda expressions exist in Java but require functional interfaces and do not replace normal method declarations.
- Java does not have hoisting. Methods must be known to the compiler before use.
