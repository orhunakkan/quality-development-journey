# Type Conversion and Coercion in Java

## Introduction to Type Conversion

Types are fundamental in programming, and converting between types is something we do frequently. In Java, type conversion exists as well, but unlike JavaScript, Java does **not** perform implicit type coercion in most situations. Java is strongly and statically typed, so conversions must usually be done **explicitly**.

In this lesson, we compare Java’s explicit conversions with JavaScript’s type conversion concepts, keeping the structure and purpose aligned.

## Type Conversion vs. Coercion in Java

JavaScript distinguishes between manual type conversion and automatic coercion.  
Java, however, behaves differently:

- **Type Conversion (Casting)** — Explicit conversion from one type to another.
- **Automatic Promotion (Widening)** — Java will *automatically* promote values to a wider compatible type during arithmetic (e.g., int → long → float → double).
- **No automatic coercion between numbers and strings** — Java will **never** silently convert a number to a string or vice versa.

Thus, Java avoids many of the confusing coercion behaviors seen in JavaScript.

## Manual Type Conversion (Casting)

When converting a value from one numeric type to another, Java requires explicit casting if the conversion may lose information.

Example:

```java
int inputYear = 1991;
System.out.println((double) inputYear);
```

Casting creates a new converted value. The original variable is unchanged, just like in JavaScript.

### Converting Strings to Numbers

Unlike JavaScript’s `Number("1991")`, Java uses the parsing methods from wrapper classes:

```java
String inputYear = "1991";
int year = Integer.parseInt(inputYear);
System.out.println(year + 18);
```

If the string cannot be converted:

```java
int invalid = Integer.parseInt("Jonas"); // Throws NumberFormatException
```

Java does **not** produce something like NaN. Instead, it throws an exception.

### Checking the type of NaN in Java

Java does have `Double.NaN`, but it is **not** produced when parsing fails. It appears only from invalid math operations like:

```java
double result = 0.0 / 0.0; // Produces NaN
```

Its type is still `double`.

## Converting Numbers to Strings

Java provides multiple ways to convert numbers to strings:

```java
System.out.println(String.valueOf(23));
System.out.println(Integer.toString(23));
```

These work similarly to JavaScript’s `String(23)`.

Java can convert:

- numbers → strings  
- strings → numbers  
- primitives → wrappers  

Java **cannot** convert to `null` or similar special types.

## Automatic Type Promotion

Java does perform some automatic conversions during arithmetic operations. This is not full coercion like JavaScript but predictable widening rules.

For example:

```java
int a = 10;
double b = 5.5;
double result = a + b; // int → double automatically
```

Java promotes the `int` to `double` because mixed-type math must use a compatible shared type.

But Java never promotes between numbers and strings:

```java
System.out.println(10 + " years old"); // Works, but via String concatenation
```

This is one of the few places Java behaves like JavaScript:  
When using `+` with a string, Java converts the **entire expression** to a string.

## Java Does Not Coerce Strings to Numbers Automatically

In JavaScript:

```javascript
'23' - '10' - 3 // works
```

In Java, this would cause a compilation error:

```java
int result = "23" - "10"; // Not allowed
```

Java requires explicit conversion:

```java
int result = Integer.parseInt("23") - Integer.parseInt("10") - 3;
```

## Guess the Output (Java Version)

Let’s mimic the JavaScript examples using Java rules.

### Example 1

JavaScript:

```javascript
let n = '1' + 1; // '11'
n = n - 1;       // 10
```

Java equivalent:

```java
String n = "1" + 1; // "11"
int result = Integer.parseInt(n) - 1;
System.out.println(result); // 10
```

### Example 2

JavaScript:

```javascript
console.log(2 + 3 + 4 + '5'); // '95'
```

Java equivalent:

```java
String result = (2 + 3 + 4) + "5";
System.out.println(result); // "95"
```

### Example 3

JavaScript:

```javascript
'10' - '4' - '3' - 2 + '5' // '15'
```

Java equivalent (with parsing):

```java
String result = (Integer.parseInt("10") - Integer.parseInt("4") - Integer.parseInt("3") - 2) + "5";
System.out.println(result); // "15"
```

Java does **not** automatically switch between numbers and strings—everything must be explicit.

## Importance of Understanding Java Type Conversion

Java avoids most of JavaScript’s type coercion pitfalls, but explicit conversion is still vital:

- Prevents unexpected behavior  
- Avoids runtime exceptions  
- Ensures clarity in math and string operations  

Once you understand Java’s predictable conversion rules, type-related bugs become much easier to avoid.

## Key Takeaways

- Java requires **explicit** type conversion (casting) between incompatible primitive types.
- Java uses parsing methods like `Integer.parseInt()` for string → number conversions.
- Java does not use automatic coercion between strings and numbers.
- When mixing numeric types, Java uses **automatic widening** (e.g., int → double).
- Using `+` with a string triggers string concatenation, similar to JavaScript.
- Java throws exceptions instead of producing values like `NaN` for invalid conversions.

