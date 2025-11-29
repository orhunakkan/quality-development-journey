# Functions in Java

## Introduction to Functions

Functions (called **methods** in Java) are one of the fundamental building blocks of all Java applications. They allow us to group code into reusable blocks, keeping programs organized, maintainable, and easier to understand.

Just like in JavaScript, functions help avoid repetition and allow us to write logic once and reuse it multiple times. However, Java’s syntax is more structured because Java is a statically typed language.

---

## What Are Functions (Methods)?

A **function/method** in Java is a block of code that performs an action.  
Instead of copying and pasting the same code repeatedly, you define a method once and call it whenever needed.

A variable holds a single value.  
A method can contain **multiple lines of code**, perform computations, and return values.

---

## Declaring a Method

In Java, every method must be declared inside a **class**.

Example: a simple logger method:

```java
public static void logger() {
    System.out.println("My name is Jonas");
}
```

- `public static` — access and context keywords  
- `void` — return type (this method returns nothing)  
- `logger` — method name  
- `()` — parentheses for parameters  
- `{}` — method body  

---

## Calling / Invoking a Method

To execute a method, you call it using its name followed by parentheses:

```java
logger();
```

Each call executes the full method body.  
Calling it three times prints the message three times.

---

## Methods with Parameters and Return Values

Methods can receive **input** using parameters and return **output** using a return statement.

Example: a simple fruit processor:

```java
public static String fruitProcessor(int apples, int oranges) {
    String juice = "Juice with " + apples + " apples and " + oranges + " oranges.";
    return juice;
}
```

### Explanation:
- `String` — return type  
- `apples` and `oranges` — method parameters  
- The method creates a juice description and returns it  

---

## Calling a Method with Arguments

Arguments fill in the parameter values:

```java
String appleJuice = fruitProcessor(5, 0);
System.out.println(appleJuice);
```

Output:
```
Juice with 5 apples and 0 oranges.
```

You can call the method multiple times with different arguments:

```java
String mixedJuice = fruitProcessor(2, 4);
System.out.println(mixedJuice);
```

---

## Methods Without Parameters or Return Values

Some methods simply perform an action and do not need input or return anything:

```java
public static void greet() {
    System.out.println("Hello there!");
}
```

These methods are useful for logging, printing, setup operations, and other reusable actions.

---

## DRY Principle (Don't Repeat Yourself)

Methods help avoid repeating code.  
If you find yourself copying the same lines, that’s a sign you should create a method.

Java encourages clean, modular structure by organizing logic into reusable methods.

---

## Built‑In Java Methods

Java includes many built-in methods that follow the same rules as user-defined ones:

- `System.out.println()`  
- `Integer.parseInt()`  
- `Math.max(…)`, `Math.min(…)`  
- `String.length()`  

All of them receive arguments, perform an action, and return values.

---

## Summary

Methods are essential in Java because:

- They allow you to reuse code  
- They accept input (parameters)  
- They can return output  
- They improve readability and structure  
- They support the DRY principle  

Understanding how to declare, call, and use methods is crucial for writing real Java programs.

---

## Key Takeaways

- Java uses **methods** as reusable blocks of code.
- Methods can accept parameters and return values.
- `void` methods perform actions but do not return values.
- Calling a method executes its code.
- Methods help write clean, efficient, and maintainable code.
- Built‑in Java methods work the same way as user‑defined ones.

