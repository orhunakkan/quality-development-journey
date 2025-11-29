
# Operator Precedence in Java

## Introduction to Operator Precedence

In this section, we discuss how Java determines the order in which operators are executed within an expression. Operator precedence ensures that Java evaluates expressions in a predictable and consistent way.

## Revisiting Previous Code

To understand precedence, consider the calculation of ages and comparisons similar to earlier examples.

## The Precedence Question

Why are mathematical operations evaluated before comparison in an expression like this?

```java
ageJonas - 1991 > ageSarah - 2018;
```

This works as expected because Java has a well-defined operator precedence system that determines which operators execute first.

## Exploring the Precedence Table

Java has a clear operator hierarchy. Parentheses have the highest precedence, followed by unary operators, then multiplication and division, then addition and subtraction, and so on. Comparison operators appear lower in the precedence list.

Unlike JavaScript, you do not typically reference external documentation like MDN, but Java’s official documentation includes complete precedence tables.

You do not need to memorize the list. What matters is understanding the general order:
- Parentheses first  
- Unary operators  
- Multiplication/division  
- Addition/subtraction  
- Comparison  
- Assignment last  

## Operator Precedence in Practice

Consider the earlier example. The subtraction operations occur before the comparison because arithmetic operators have higher precedence than comparison operators.

### Example

Subtraction has higher precedence, so the expression is processed like:

```java
(ageJonas - 1991) > (ageSarah - 2018)
```

## Operator Associativity: Left-to-Right and Right-to-Left

Operator associativity determines whether operators of the same precedence are evaluated left-to-right or right-to-left.

Most arithmetic operators use left-to-right associativity. Some operators like assignment use right-to-left associativity.

### Example: Left-to-Right Execution

```java
int result = 25 - 10 - 5;
```

This evaluates from left to right:
- 25 - 10 = 15  
- 15 - 5 = 10  

### Example: Right-to-Left Execution (Assignment)

Assignment in Java is evaluated right to left:

```java
int x, y;
x = y = 25 - 10 - 5;
System.out.println(x + " " + y);
```

The subtraction happens first (due to higher precedence), resulting in 10.  
Then assignment proceeds right to left:
- `y = 10`  
- `x = y`  

Both x and y become 10.

## The Importance of Grouping (Parentheses)

Parentheses have the highest precedence and allow you to explicitly control evaluation order.

### Example: Calculating the Average

Without parentheses:

```java
double averageAge = ageJonas + ageSarah / 2;
```

Division happens before addition, so the result may not be what you intended.

Correct version with grouping:

```java
double averageAge = (ageJonas + ageSarah) / 2;
```

This ensures the ages are added first and then divided by two.

## Conclusion

Operator precedence and associativity are essential for understanding how Java processes expressions. Parentheses are the safest way to guarantee that expressions execute in the order you intend.

## Key Takeaways

- Java evaluates operators based on a defined precedence hierarchy.
- Arithmetic operators generally have higher precedence than comparison and assignment operators.
- Associativity determines whether operators of the same level execute left-to-right or right-to-left.
- Parentheses override normal precedence and should be used when clarity or correctness is needed.
