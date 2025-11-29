# Truthy and Falsy Values in Java

## Introduction to Truthy and Falsy Values

In JavaScript, many concepts revolve around automatic type coercion. One of these concepts is “truthy and falsy values,” where non-boolean values may behave like booleans in certain contexts.  
**Java does not have truthy and falsy values.**

Instead, Java requires that all conditions used in `if`, `while`, and other control structures must evaluate **strictly to a boolean type**. Java will not automatically convert numbers, strings, or objects to booleans.

Java avoids this category of implicit behavior entirely.

## No Falsy Values in Java

JavaScript’s falsy values—0, `""`, `undefined`, `null`, and `NaN`—do not exist as falsy in Java.

Java does not allow:

```java
if (0) { }        // invalid in Java
if ("") { }       // invalid in Java
if (null) { }     // invalid in Java
```

All of these produce *compile-time errors* because Java requires a **boolean expression**.

The only false value in Java is:

- `false`

There are no implicit conversions for other types.

## Truthy Values in Java

Java has **no truthy values**.  
Nothing other than `true` can behave as true in a condition.

```java
if (true) { }  // valid
if (false) { } // valid
```

Anything else is invalid unless explicitly converted to a boolean.

## Explicit Boolean Conversion in Java

Java requires explicit boolean expressions. For example, to check if a number is non-zero:

```java
int money = 0;

if (money != 0) {
    System.out.println("Don't spend it all");
} else {
    System.out.println("You should get a job");
}
```

This differs from JavaScript, where `if (money)` would work because of truthy/falsy coercion.

## Checking If a Variable Is Defined

Java variables behave differently from JavaScript:

- Local variables **must** be initialized before use.
- Instance and static variables default to values (0, false, null).

Example:

```java
Integer height = null;

if (height != null) {
    System.out.println("YAY! Height is defined");
} else {
    System.out.println("Height is undefined");
}
```

Here we explicitly compare with `null`.  
Java will never confuse `0` with `null`.

## Zero Is Not Falsy in Java

In JavaScript, `0` is falsy.  
In Java, `0` behaves like any other number and **never** represents “not defined.”

Example:

```java
int height = 0;

if (height != 0) {
    System.out.println("YAY! Height is defined");
} else {
    System.out.println("Height might be zero or not set");
}
```

Since Java does not coerce numbers to booleans, it will not mistake zero for false unless you explicitly compare it.

## No Implicit Boolean Coercion

Java never converts values to booleans automatically.  
You must express the condition directly:

```java
String name = "";

if (!name.isEmpty()) {
    System.out.println("Name exists");
} else {
    System.out.println("Name is empty");
}
```

This is explicit, predictable, and type-safe.

## Conclusion

Java avoids the pitfalls of truthy and falsy behavior. All conditional expressions must evaluate to a boolean, and Java does not implicitly convert types. Explicit checks ensure clarity and prevent bugs common in loosely typed languages.

## Key Takeaways

- Java has **no truthy or falsy** non-boolean values.
- Only `true` and `false` can be used in conditions.
- Java requires explicit boolean expressions—no automatic coercion.
- `null` must be checked explicitly.
- Zero (`0`) is a normal number, not a falsy value.
