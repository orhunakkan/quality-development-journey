# Arrow Functions

## Introduction to Arrow Functions
In the previous video, we learned about function declarations and expressions. However, there is actually a third type of function that was added to JavaScript in ES6, and that is the arrow function. An arrow function is simply a special form of function expression that is shorter and therefore faster to write.

Let's revisit the function expression from before as a reference point for comparison. Now, let's write the arrow function. This is how we write an arrow function: we want the birth year because we want to calculate the age, then we write an arrow, and that's the reason why this function is called an arrow function. Then, we simply write what we want to return. In this case, that's 2037 minus the birth year, and that's it.

```javascript
const calcAge3 = birthYear => 2037 - birthYear;
```

To use this function, we store it in a variable just like we did with the previous function expression. This is a special form of a function expression because it still is a function expression. It is a value that we assign to this variable, just like the function expression above. However, this one is a lot easier and faster to write.

The first reason is that we don't need the curly braces to define a code block. The second is that the return actually happens implicitly. This value will automatically be returned without us having to explicitly write the return keyword. This is excellent for simple one-liner functions and will be extremely helpful in certain situations.

```javascript
const age3 = calcAge3(1991);
console.log(age3); // 46
```

This function works the same way as other functions. We call `calcAge3` just like the other calcAge functions. The returned value is saved to a variable, and when we check it, it should be 46, just like in the previous lecture. Indeed, the value was returned automatically without writing the return keyword. Notice that we also didn't need any parentheses around the single parameter; just this is the function.

## Arrow Functions with Multiple Lines of Code
This is the simplest form of an arrow function, which is when we have exactly one parameter and only one line of code to return. However, it gets more complex when we add more parameters or more code.

Let's write another function to calculate how many years a person has left until retirement. We'll call this function `yearsUntilRetirement`.

```javascript
const yearsUntilRetirement = birthYear => {
  const age = 2037 - birthYear;
  const retirement = 65 - age;
  return retirement;
};
```

In this function, we need more lines of code to calculate the age based on the birth year, then calculate the years until retirement by subtracting the current age from the retirement age, which is 65 years in many countries. Because we have more than one line of code, we need curly braces to define the code block and must explicitly write the return keyword.

```javascript
console.log(yearsUntilRetirement(1991)); // 19
```

When we run this function, it returns 19, which makes sense because 65 minus 46 (the age) is 19. This is the scenario when we have one parameter and more than one line of code. In this case, we need the return statement.

## Arrow Functions with Multiple Parameters
If we have multiple parameters, we need to wrap them in parentheses. For example, if we want to pass both the birth year and the first name to return a sentence, we write:

```javascript
const yearsUntilRetirement = (birthYear, firstName) => {
  const age = 2037 - birthYear;
  const retirement = 65 - age;
  return `${firstName} retires in ${retirement} years.`;
};
```

```javascript
console.log(yearsUntilRetirement(1991, 'Jonas')); // Jonas retires in 19 years.
console.log(yearsUntilRetirement(1980, 'Bob')); // Bob retires in 8 years.
```

This demonstrates how arrow functions work with multiple parameters and multiple lines of code. As you add more code and parameters, the arrow function syntax becomes more complex, and we lose some of the advantages of using arrow functions.

## When to Use Arrow Functions
You might ask: should I use arrow functions for everything since they are easy to write? The answer is no, but it is complicated. There is a fundamental difference between arrow functions and traditional functions (function declarations and expressions): arrow functions do not have their own `this` keyword. This topic will be covered later in the course.

Learning is not linear, and topics build incrementally to avoid overwhelming you. For now, we will mainly use normal functions, except for very simple one-liner functions like the example shown. This does not mean arrow functions are unimportant; they are very useful and widely used. However, you cannot yet understand all the implications of using arrow functions, so we will keep using function expressions mostly for now.

## Key Takeaways
- Arrow functions are a concise form of function expressions introduced in ES6.
- They allow implicit returns for single-line functions without curly braces.
- Multiple parameters require parentheses, and multi-line functions require curly braces and explicit return statements.
- Arrow functions do not have their own `this` keyword, which differentiates them from traditional functions.
