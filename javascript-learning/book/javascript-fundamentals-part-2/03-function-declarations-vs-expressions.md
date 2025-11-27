# Function Declarations vs. Expressions

## Introduction to Function Types in JavaScript
In JavaScript, there are different ways of writing functions. Each type of function works in a slightly different way, but they are all very similar. Let's explore these types now.

## Function Declarations
The functions shown previously are called function declarations because we use the `function` keyword to declare a function, similar to declaring a variable.

Let's write another function declaration to calculate an age based on a given birth year.

We use the `function` keyword and name this function `calcAge1` because we will have another one later. This function takes a person's birth year as an input parameter.

A parameter is like a local variable only available inside the function. The function body is enclosed in curly braces `{}` where we write the code.

To calculate the age, we take the current year, suppose it is 2037, and subtract the person's birth year. We then return this value using the `return` keyword.

We can simplify this by returning the result of the expression directly without storing it in a variable first.

This function is generic and works for any birth year provided.

```javascript
function calcAge1(birthYear) {
  return 2037 - birthYear;
}
```

## Calling Function Declarations
To call or invoke this function, we use the function name followed by parentheses containing the argument value.

The argument is the actual value passed to the function parameter.

For example, calling `calcAge1(1991)` will calculate the age for the birth year 1991.

The function returns a value, which we can capture by assigning it to a variable.

```javascript
const age1 = calcAge1(1991);
console.log(age1); // Outputs: 46
```

## Function Expressions
Another type of function is the function expression. Instead of naming the function, we write an anonymous function and assign it to a variable.

This variable then holds the function value.

The syntax looks like this:

```javascript
const calcAge2 = function(birthYear) {
  return 2037 - birthYear;
};
```

## Calling Function Expressions
We call function expressions in the same way as function declarations, by using the variable name followed by parentheses with the argument.

For example:

```javascript
const age2 = calcAge2(1991);
console.log(age2); // Outputs: 46
```

## Functions as Values
In JavaScript, functions are values just like numbers, strings, or booleans. They are not a separate type but can be stored in variables.

This means the function expression is an expression that produces a value, which we assign to a variable.

## Key Difference: Hoisting
The main practical difference between function declarations and expressions is hoisting.

Function declarations can be called before they are defined in the code because they are hoisted.

For example, this works:

```javascript
console.log(calcAge1(1991)); // Works

function calcAge1(birthYear) {
  return 2037 - birthYear;
}
```

However, calling a function expression before initialization results in an error:

```javascript
console.log(calcAge2(1991)); // Error: Cannot access 'calcAge2' before initialization

const calcAge2 = function(birthYear) {
  return 2037 - birthYear;
};
```

This behavior is due to hoisting, which will be explained in more detail later.

For now, remember that function declarations can be called before their definition, but function expressions cannot.

## Which to Use?
Choosing between function declarations and expressions often comes down to personal preference.

Some developers prefer function expressions because they enforce defining all functions before calling them, leading to more structured code.

Others prefer function declarations.

Regardless of preference, it is important to understand both types and their differences, as both have their place in JavaScript.

## Key Takeaways
- JavaScript functions can be defined as function declarations or function expressions.
- Function declarations use the `function` keyword with a name and can be called before their definition due to hoisting.
- Function expressions are anonymous functions assigned to variables and cannot be called before initialization.
- Functions in JavaScript are values and can be stored in variables, similar to numbers or strings.
