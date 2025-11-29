# Taking Decisions: if / else Statements in Java

## Taking Decisions: if / else Statements

In this section, we make our Java programs more dynamic by enabling them to make decisions based on conditions. This allows the code to react differently depending on the values it receives.

Suppose we want to write a program that checks whether a person is allowed to start the process of getting a driver’s license. If the person is old enough, the program prints a confirmation message. Otherwise, it prints how many years are left before the person becomes eligible.

Let’s start by defining an age variable and setting it to 19. We already know how to compare values using relational operators such as `>=`.

## Basic if Statement

We create a boolean condition that checks whether the age is at least 18:

```java
int age = 19;
boolean isOldEnough = age >= 18;
```

Now that we have a boolean value, we use it in an `if` statement. An `if` statement contains a condition inside parentheses. If the condition is true, the code block inside the braces executes.

```java
if (isOldEnough) {
    System.out.println("Sarah can start her driving license 🚗");
}
```

If age is 19, the condition is true, so the message prints. If age were 15, the block would be skipped.

Typically, we write the condition directly inside the `if` statement:

```java
if (age >= 18) {
    System.out.println("Sarah can start her driving license 🚗");
}
```

## Adding an else Block

The `else` block runs only when the `if` condition is false. This allows us to provide alternative behavior.

```java
if (age >= 18) {
    System.out.println("Sarah can start her driving license 🚗");
} else {
    int yearsLeft = 18 - age;
    System.out.println("Sarah is too young. Wait another " + yearsLeft + " years.");
}
```

If age is 15, the output becomes:

```
Sarah is too young. Wait another 3 years.
```

The `else` block is optional. Without it, nothing happens if the condition is false.

## Understanding Control Flow

The `if / else` statement is a control structure. It allows the program to follow different paths depending on the condition. This is one of the core concepts in programming: code does not always run top to bottom; it can branch based on logic.

## Another Example: Determining the Century

Let’s determine whether a birth year falls in the 20th or 21st century.

```java
int birthYear = 1998;
int century;

if (birthYear <= 2000) {
    century = 20;
} else {
    century = 21;
}

System.out.println(century);
```

If birthYear is 1998, output is 20.  
If birthYear is 2012, output is 21.

### Important Note About Scope

Variables declared inside a block `{}` cannot be used outside that block. Therefore, we declare `century` before the `if / else` so that it remains accessible afterward.

## Recap

- Use `if` followed by a condition to execute code only when the condition is true.
- The condition must evaluate to a boolean value (`true` or `false`).
- If the condition is false and there is an `else` block, that block executes instead.
- `else` is optional.
- Control structures allow non-linear execution flow.
- Variables declared inside blocks cannot be used outside them; declare them beforehand if needed later.

Mastering `if / else` statements is essential before moving into more complex programming logic.

## Key Takeaways

- The `if` statement executes a block only when its condition is true.
- The `else` statement provides an alternate path when the condition is false.
- Conditions must evaluate to boolean values.
- Block-scoped variables must be declared outside the `if / else` if needed afterward.
