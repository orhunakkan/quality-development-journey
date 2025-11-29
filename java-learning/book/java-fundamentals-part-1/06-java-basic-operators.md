
# Basic Operators in Java

## Introduction to Basic Operators

In this section, we will explore fundamental operators in Java. Operators allow us to transform values, combine values, compare results, and perform many kinds of operations. Java supports several categories of operators, including mathematical operators, comparison operators, logical operators, assignment operators, and more.

## Mathematical or Arithmetic Operators

Java provides all standard arithmetic operations such as addition, subtraction, multiplication, division, and exponentiation. Let’s calculate ages using subtraction:

```java
int ageJonas = 2037 - 1991;
System.out.println(ageJonas);
```

This calculates Jonas’s age by subtracting his birth year from 2037. Java evaluates the arithmetic before executing the print statement.

```java
int ageSarah = 2037 - 2018;
System.out.println(ageSarah);
```

This computes Sarah’s age similarly. You can print multiple values by calling `System.out.println` multiple times.

## Using Variables to Avoid Repetition

To avoid repeating values such as the year, we use variables:

```java
final int now = 2037;
int ageJonas = now - 1991;
int ageSarah = now - 2018;
System.out.println(ageJonas + " " + ageSarah);
```

Using variables makes the code easier to maintain. The `final` keyword ensures that values which should not change remain constant.

## More Arithmetic Operations

Java also supports multiplication, division, and exponentiation:

```java
System.out.println(ageJonas * 2);
System.out.println(ageJonas / 2);
System.out.println(Math.pow(2, 3));
```

Java does not have a dedicated exponentiation operator like JavaScript’s `**`. Instead, you use `Math.pow`.

## String Concatenation with the Plus Operator

The plus operator `+` can join strings:

```java
String firstName = "Jonas";
String lastName = "Schmedtmann";
System.out.println(firstName + lastName);
```

This concatenates the two strings without a space. To include a space:

```java
System.out.println(firstName + " " + lastName);
```

This prints `"Jonas Schmedtmann"`.

## Assignment Operators

The simplest assignment operator is the equals sign `=`:

```java
int x = 10 + 5;
System.out.println(x);
```

The arithmetic executes first, and then the result is assigned to `x`.

## Compound Assignment Operators

Java supports shorthand assignment operators such as `+=`, which adds a value and reassigns it:

```java
x += 10; // same as x = x + 10
System.out.println(x);
```

Java also includes operators like `*=`, `/=`, and `-=`, similar to JavaScript:

```java
x *= 4; // same as x = x * 4
System.out.println(x);
```

## Increment and Decrement Operators

Java includes increment `++` and decrement `--` operators:

```java
x++;
System.out.println(x);
x--;
x--;
System.out.println(x);
```

These increase or decrease the variable by one.

## Comparison Operators

Comparison operators return boolean values. For example:

```java
System.out.println(ageJonas > ageSarah);
```

This prints `true` because Jonas’s age is greater than Sarah’s. Java supports the same comparison operators: `>`, `<`, `>=`, and `<=`.

## Checking Full Age

To check if Sarah is of full age:

```java
boolean isFullAge = ageSarah >= 18;
System.out.println(isFullAge);
```

This stores the result in a boolean variable, which is useful for later references.

## Combining Calculations and Comparisons

You can combine calculations and comparisons in the same expression:

```java
boolean result = (now - 1991) >= (now - 2018);
System.out.println(result);
```

Java also uses operator precedence rules to determine which operations occur first.

## Key Takeaways

- Java operators allow you to transform and compare values.
- Arithmetic operators include addition, subtraction, multiplication, division, and exponentiation via `Math.pow`.
- Variables help avoid repetition and improve maintainability.
- The plus operator concatenates strings.
- Compound assignment operators like `+=` and `*=` simplify updating variables.
- Comparison operators return boolean values used for decision-making.
- Operator precedence ensures correct evaluation order in expressions.
