# Logical Operators in Java

## Introduction to Logical Operators in Java
Logical operators allow us to combine and manipulate boolean values in order to make decisions in our programs. Java uses the same three core logical operators found in many languages: **AND** (`&&`), **OR** (`||`), and **NOT** (`!`). These operators help us build complex logical expressions that control program flow.

In this lesson, we reuse the examples involving driver’s licenses and vision to demonstrate how logical operators work in Java.

## Defining Boolean Variables
We define our boolean variables just as we do in JavaScript, but with Java’s type system:

```java
boolean hasDriversLicense = true;
boolean hasGoodVision = true;
```

These represent two independent true/false conditions.

## Using the AND Operator
The AND operator (`&&`) returns `true` only when **both** operands are true.

```java
System.out.println(hasDriversLicense && hasGoodVision);
```

Since both values are true, the expression prints `true`.

### Changing One Value to False

```java
hasGoodVision = false;
System.out.println(hasDriversLicense && hasGoodVision); // false
```

In Java, as in all Boolean logic, `true && false` results in `false`.

## Using the OR Operator
The OR operator (`||`) returns `true` if **at least one** operand is true:

```java
System.out.println(hasDriversLicense || hasGoodVision);
```

Here, because at least one value is true, the result will be true.

## Using the NOT Operator
The NOT operator (`!`) inverts a boolean value:

```java
System.out.println(!hasDriversLicense);
```

If `hasDriversLicense` is true, this prints false.

## Decision Making with Boolean Variables
Now let’s combine these conditions to decide if Sarah should drive:

```java
boolean shouldDrive = hasDriversLicense && hasGoodVision;

if (shouldDrive) {
    System.out.println("Sarah is able to drive");
} else {
    System.out.println("Someone else should drive");
}
```

This checks that both conditions are true.

## Testing Different Conditions
If both variables are true:

```java
hasGoodVision = true;
shouldDrive = hasDriversLicense && hasGoodVision;
```

The message "Sarah is able to drive" will print.

## Adding a Third Variable: isTired
Let’s add a third condition:

```java
boolean isTired = true;
```

Sarah should drive only if she:
- has a driver’s license,
- has good vision,
- and **is not tired**.

```java
shouldDrive = hasDriversLicense && hasGoodVision && !isTired;
```

```java
if (shouldDrive) {
    System.out.println("Sarah is able to drive");
} else {
    System.out.println("Someone else should drive");
}
```

Because `isTired` is true, `!isTired` becomes false, making the entire expression false. Set `isTired` to false to allow her to drive:

```java
isTired = false;
```

## Summary
Java’s logical operators allow you to combine multiple conditions into meaningful, real‑world decision logic. With practice, these operators will feel simple and intuitive.

## Key Takeaways
- Java logical operators include AND (`&&`), OR (`||`), and NOT (`!`).
- AND returns true only when **all** operands are true.
- OR returns true if **any** operand is true.
- NOT inverts a boolean expression.
- Logical operators can be chained to model complex decision-making (e.g., checking multiple conditions before allowing an action).
