
# Variable Declaration in Java

## Introduction to Variable Declaration in Java

In this section, we will examine how variables are declared in Java. Java provides a consistent and strict way of defining variables, and unlike JavaScript, Java does not have multiple keywords such as let or const. Instead, Java uses a single approach: you must always specify the data type of the variable when declaring it.

## Variable Declaration with Data Types

In Java, variables are declared by writing the data type first, followed by the variable name. For example:

```java
int age = 30;
String name = "Jonas";
```

Java is statically typed, which means that the type of a variable cannot change after it is declared. If a variable is declared as an int, it can only ever hold integer values.

### Example: Mutating Variables in Java

Java allows reassigning a new value to a variable, as long as the value matches the original data type:

```java
int age = 30;
age = 31; // allowed
```

This is similar to mutating a variable, but only within the limits of its original type.

### Declaring Empty (Uninitialized) Variables

Java allows declaring a variable without giving it an initial value:

```java
int year;
```

However, unlike JavaScript, Java does not assign undefined. Java requires you to assign a value before you can use the variable. Attempting to use it before assignment results in a compile-time error.

## Constants in Java

Java does not have a keyword like const. Instead, Java uses the final keyword to create variables whose values cannot change after assignment.

```java
final int birthYear = 1991;
```

Attempting to reassign a final variable will cause a compile-time error:

```java
birthYear = 1990; // Error: cannot assign a value to final variable
```

### Initialization Requirement for final

Just like const in JavaScript, Java requires that final variables be initialized immediately:

```java
final int year; // Error if not assigned before use
```

You must assign a value either during declaration or inside a constructor (for class-level variables).

### Best Practices: When to Use final or Regular Variables

In Java, using final is a best practice when you know the value should never change. It improves code clarity and helps prevent unintended modifications.

Examples of good candidates for final:
- birth year  
- configuration values  
- constants used throughout a class  

Regular variables (non-final) should be used only when mutation is required.

## The var Keyword in Java

Java introduced a var keyword in Java 10, but it behaves very differently from JavaScript’s var.  
Java’s var does **not** create dynamically typed variables.

Instead, it allows Java to infer the type at compile time:

```java
var job = "programmer"; // job becomes a String
job = "teacher";        // allowed (same type)
```

Even though var simplifies the syntax, the variable still has a fixed, unchangeable type. You cannot assign a different type later.

```java
job = 10; // Error: incompatible types
```

The var keyword should be used only when the type is obvious to the reader. Otherwise, explicit data types are preferred for clarity.

## Declaring Variables Without Keywords

Java does not allow declaring variables without using a data type or var.  
You must always declare variables explicitly. Any attempt to use an undeclared variable results in a compile-time error.

```java
lastName = "Smith"; // Not allowed in Java
```

There is no mechanism in Java that automatically creates global properties. All variables must be declared before they can be used.

## Conclusion

You are making great progress, even if the code examples look simple. Understanding how Java handles variable declaration and immutability is essential before moving into more advanced topics.

## Key Takeaways

- Java requires specifying a data type when declaring variables.
- Variables declared with a type can be reassigned only with values of the same type.
- The final keyword creates immutable variables similar to constants.
- Java’s var keyword uses type inference but still enforces static typing.
- Variables cannot be created without a declaration; Java requires explicit definitions.
