# Equality Operators in Java

## Introduction to Equality Operators

In this section, we explore equality and inequality operators in Java. While JavaScript has both strict (`===`) and loose (`==`) equality, **Java does not have loose equality at all**. Java uses a single equality operator for primitive comparisons, and a completely different mechanism for comparing objects.

Let’s begin by creating a variable named `age` and assigning it the value 18:

```java
int age = 18;
```

Now let’s write a simple condition that prints a message when the age is exactly 18:

```java
if (age == 18)
    System.out.println("You just became an adult.");
```

If `age` is 18, the message prints. If `age` is changed to 19, nothing prints.

## Equality Operator for Primitives

In Java, the equality operator `==` compares **primitive values directly**:

```java
18 == 18  // true
18 == 19  // false
```

Unlike JavaScript:
- Java does **not** perform type coercion.
- Java does **not** have `===`.
- `==` always compares values directly for primitive types.

You cannot confuse assignment (`=`) with equality (`==`), and Java prevents many bugs by rejecting mixed-type comparisons at compile time.

Example of invalid Java:

```java
int a = 18;
if (a == "18") { } // Compile-time error
```

Java will not attempt to convert types implicitly.

## Comparing Strings and Objects

When comparing objects (including strings), the equality operator behaves differently:

- `==` checks **reference equality** (are they the same object in memory?)
- `.equals()` checks **value equality**

Example:

```java
String a = "Hello";
String b = "Hello";
String c = new String("Hello");

System.out.println(a == b);       // true (same interned reference)
System.out.println(a == c);       // false (different objects)
System.out.println(a.equals(c));  // true (same content)
```

This is a key difference from JavaScript, where `'hello' === 'hello'` always compares value.

## Getting User Input

Java does not have `prompt()`. Instead, we use `Scanner`:

```java
import java.util.Scanner;

Scanner scanner = new Scanner(System.in);
System.out.print("What's your favorite number? ");
String favorite = scanner.nextLine();
```

`favorite` is always a string.

To compare it numerically, we must convert it:

```java
int favNum = Integer.parseInt(favorite);

if (favNum == 23)
    System.out.println("Cool. 23 is an amazing number!");
```

Java never compares numbers and strings directly. You must parse values manually.

## else if and Multiple Conditions

Java supports chained conditions just like JavaScript:

```java
if (favNum == 23) {
    System.out.println("Cool. 23 is an amazing number!");
} else if (favNum == 7) {
    System.out.println("7 is also a cool number!");
} else if (favNum == 9) {
    System.out.println("9 is also a cool number!");
} else {
    System.out.println("Number is not 23, 7, or 9.");
}
```

You can add as many `else if` branches as needed.

## Inequality Operator

Java uses `!=` for primitive inequality. It is always strict because Java never coerces types.

```java
if (favNum != 23)
    System.out.println("Why not 23?");
```

For objects (like Strings), you must use:

```java
!favorite.equals("23")
```

Using `!=` with strings only checks references, not values.

## Summary of Equality Rules in Java

### For primitives:
- `==` compares actual values.
- `!=` compares inequality.
- No type coercion exists.

### For objects:
- `==` → reference comparison
- `.equals()` → content/value comparison

### For user input:
- Always convert strings to numbers before numeric comparison.

## Key Takeaways

- Java does **not** have loose equality; there is no `===` or JavaScript-style coercion.
- `==` compares primitive values and object references.
- `.equals()` must be used for comparing string or object content.
- You must manually convert user input to the correct type before comparing.
- Inequality uses `!=` for primitives and `!object.equals()` for objects.
