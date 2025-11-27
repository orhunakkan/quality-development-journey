# Functions

## Introduction to Functions
The fundamental building block of real-world JavaScript applications is functions. They are one of the most essential concepts in the language. Over the next couple of videos, we will study functions in detail.

## What Are Functions?
In the simplest form, a function is a piece of code that we can reuse repeatedly in our code. It is somewhat like a variable, but instead of holding a single value, a function holds one or more complete lines of code.

Remember, a variable holds a value, but a function can hold multiple lines of code.

## Declaring a Function
Let's declare our very first function. We start with the `function` keyword, followed by a function name. For example, let's create a simple logger function that logs something to the console. We name it `logger`.

Next, we add parentheses `()` and then use curly braces `{}` to create the function body, also called the function buddy. All the code inside these curly braces will be executed when we run this function.

## Function Body and Execution
Suppose we need to execute a log many times somewhere in our program. Instead of repeating the same line, we put it inside a function. For example, inside the function body, we write:

```
console.log('My name is Jonas');
```

Now, we can use this function as many times as we want by calling it.

## Calling or Invoking a Function
To use the function, we write the function name followed by parentheses. This process is called calling, running, or invoking the function. These terms are interchangeable and mean executing the function's code.

Each time we call the function, the code inside the function body gets executed.

## Example of Calling a Function Multiple Times
If we call the logger function three times, the message "My name is Jonas" will be logged three times. This demonstrates how functions help reuse code efficiently.

## Functions with Parameters and Return Values
Functions can do more than just reuse code. They can accept input data through parameters and return data back to the caller.

Think of functions as machines: you put something in, the function processes it, and then returns a result.

For example, a fruit processor function takes apples and oranges as input parameters, processes them, and returns a juice string.

## Defining a Function with Parameters
To define such a function, we use the `function` keyword followed by the function name, e.g., `fruitProcessor`. Inside the parentheses, we specify parameters like `apples` and `oranges` separated by commas. These parameters act like variables specific to the function and will be assigned values when the function is called.

## Using Parameters Inside the Function
Inside the function body, we can use the parameters as variables. For example, we can log the values of apples and oranges to the console. We can also create a string that represents the juice produced from the given fruits using template literals.

## Returning a Value from a Function
Using the `return` keyword, we can return a value from the function. In this case, we return the juice string. The returned value can then be used elsewhere in the program.

## Calling the Function with Arguments
When calling the function, we provide specific values called arguments for the parameters. For example, calling `fruitProcessor(5, 0)` assigns 5 to apples and 0 to oranges. These arguments fill the blank spaces defined by the parameters.

## Capturing the Returned Value
The result of calling the function is the returned juice string. To use this value, we store it in a variable, for example:

```
const appleJuice = fruitProcessor(5, 0);
```

We can then log this variable to the console to see the output.

## Calling the Function Multiple Times with Different Arguments
We can call the `fruitProcessor` function multiple times with different arguments to produce different juices. For example, `fruitProcessor(2, 4)` will produce a juice string with two apples and four oranges. This demonstrates the flexibility of functions with parameters.

## Functions Without Parameters or Return Values
Not all functions need to accept parameters or return values. For example, a simple logger function that only logs a message to the console does not require parameters or a return value. Such functions are useful for reusing blocks of code that perform actions without producing a result.

## Summary and Best Practices
Functions allow us to write more maintainable code by creating reusable chunks instead of repeating code. This follows the important programming principle called "Don't Repeat Yourself" (DRY).

Functions are fundamental building blocks of JavaScript programs and are used extensively.

Built-in functions like `console.log` and `Number` work similarly to user-defined functions, accepting arguments and returning values.

## Final Advice
If this is your first encounter with functions, it might feel confusing. Review the concepts thoroughly and understand how parameters, arguments, return values, and function calls work together. This understanding is crucial for writing clean and efficient code.

## Key Takeaways
- Functions are reusable blocks of code that can be invoked multiple times.
- Functions can accept input data through parameters and return output data.
- Using functions promotes maintainable and DRY (Don't Repeat Yourself) code.
- Built-in JavaScript functions like `console.log` and `Number` work similarly to user-defined functions.
