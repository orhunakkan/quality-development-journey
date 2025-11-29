# Boolean Logic in Java

## Introduction to Boolean Logic
Boolean logic is a fundamental concept used in all programming languages, including Java. It allows us to combine and evaluate conditions using the boolean values **true** and **false**. Just like arithmetic operators work on numbers, logical operators work on boolean expressions.

In this lesson, we examine the three core logical operators in Java: **AND**, **OR**, and **NOT**. These operators behave consistently across languages and follow well‑defined truth tables.

## Boolean Variables Example
Let’s consider two boolean variables, `A` and `B`. These can be either true or false.

For example:
- `A` = Sarah has a driver’s license  
- `B` = Sarah has good vision

Both represent real‑world conditions that can independently be true or false.

## The AND Operator
The **AND** operator in Java is written as `&&`.

`A && B` means “A AND B”, which is true only if **both** values are true.

Truth table for AND:

| A     | B     | A && B |
|-------|-------|--------|
| true  | true  | true   |
| true  | false | false  |
| false | true  | false  |
| false | false | false  |

AND can combine more than two conditions (e.g., `A && B && C`), but the rule stays the same: all must be true for the result to be true.

## The OR Operator
The **OR** operator in Java is written as `||`.

`A || B` means “A OR B”, which is true if **at least one** of the operands is true.

Truth table for OR:

| A     | B     | A || B |
|-------|-------|--------|
| true  | true  | true   |
| true  | false | true   |
| false | true  | true   |
| false | false | false  |

OR is useful when multiple conditions can independently allow an action.

## The NOT Operator
The **NOT** operator in Java is written as `!`.

It applies to a single boolean value and simply flips it:

- `!true` → false  
- `!false` → true  

NOT always executes **before** AND and OR in expressions, due to operator precedence.

## Practical Example
Let’s use an example with an age variable:

```java
int age = 16;

boolean A = age >= 20; // false
boolean B = age < 30;  // true
```

Now evaluate combinations:

- `!A` → true (because A is false)
- `A && B` → false
- `A || B` → true
- `!A && B` → true
- `A || !B` → false

Here, Java first applies NOT before evaluating AND or OR.

## Summary
Logical operators enable us to build complex conditions in a clear and structured way. Understanding AND (`&&`), OR (`||`), and NOT (`!`) is essential for writing effective decision‑making code.

## Key Takeaways
- Boolean logic manipulates true/false values for complex decisions.
- AND (`&&`) is true only if **all** conditions are true.
- OR (`||`) is true if **at least one** condition is true.
- NOT (`!`) inverts a boolean value.
- NOT has higher precedence than AND and OR.

