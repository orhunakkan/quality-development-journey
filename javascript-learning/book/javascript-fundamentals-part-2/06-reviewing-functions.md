# Reviewing Functions

## Reviewing Functions
To conclude our discussion on functions, let's review everything important that we have learned so far. This review ensures that you fully understand functions before we proceed to other topics.

Let's start by bringing back and rewriting the function we wrote earlier, which calculates the years until retirement. This function was introduced in the lecture about arrow functions. We will copy it and paste it here.

The first step is to convert this arrow function to a more traditional function expression. To do this, we remove the arrow and add the `function` keyword at the beginning. Now, this is a regular function expression as we learned before.

Next, we can extract some functionality into another function, such as a `calcAge` function that we have used previously. This allows us to call one function inside another, demonstrating function composition.

Let's write another function expression named `calcAge` that takes the birth year as a parameter and returns the age by subtracting the birth year from 2037. This is a straightforward calculation we have done before.

You might find it confusing that both functions use the parameter name `birthYear`. However, these are two completely separate parameters local to their respective functions. They are independent variables, and even a variable outside the functions could be named `birthYear` without conflict.

Now, let's modify the `yearsUntilRetirement` function to calculate the age by calling the `calcAge` function instead of performing the calculation directly. We delete the previous age calculation and replace it with a call to `calcAge` passing in the birth year.

To illustrate the data flow, we call `yearsUntilRetirement` with the birth year 1991 and the first name "Jonas". Inside this function, `calcAge` is called with 1991 as the argument, which returns the age by computing 2037 minus 1991. This value is then used to calculate years until retirement.

If you find the repeated use of `birthYear` confusing, you can rename the parameter in one of the functions, for example to `year`, without affecting functionality. The parameter names are local and independent.

Let's revert the function to return a number instead of a string, which is a better practice when dealing with numerical inputs and outputs. We will comment out the string return for now and log the result of the function call to the console.

Now, let's call the function again with a different example: Mike, born in 1970. The years until retirement will be negative, indicating that he has already retired.

To handle negative retirement years, we add a conditional statement. If the retirement value is above zero, we return it; otherwise, we return a special number such as -1 to indicate that the person has already retired.

We implement this logic using an if-else statement. If `retirement > 0`, return retirement; else, return -1. This approach is common in programming to indicate special conditions.

Returning numbers instead of strings is preferred when the input is numerical, as it maintains data consistency and allows for further numerical operations.

Testing with a birth year of 1950 now returns -1, indicating retirement. This confirms our conditional logic works as intended.

An important note is that the return statement immediately exits the function. It returns the specified value and no further code in the function is executed after it.

To demonstrate this, we add a `console.log` statement after the return. When running the code, the console.log is ignored because the function has already exited.

To ensure the console.log executes, place it before the return statement. This way, the message is logged before the function exits.

Using editor shortcuts like Option/Alt + Up Arrow can help move lines of code efficiently, such as moving the console.log above the return.

After adjusting the code, running it shows the message before the return value, confirming the behavior of the return statement.

## Summary of Function Types
There are three types of functions:

- **Function Declarations:** Can be used before they are declared in the code.
- **Function Expressions:** Functions stored in variables.
- **Arrow Functions:** Special function expressions ideal for concise one-line functions without explicit return or curly braces.

All three types can receive input, process data, and return output.

## Structure of a Common Function
A typical function includes:

- A function name (e.g., `calcAge`).
- Parameters, which are placeholders for input values and act as local variables.
- A function body where input data is processed.
- A return statement to output a value and immediately terminate the function execution.

## Calling Functions
Functions are called using parentheses after the function name. Without parentheses, the function is treated as a value. With parentheses, the function is executed or invoked. Arguments passed in the parentheses replace the parameters as input data.

When a function completes, the expression calling it is replaced by the returned value, which can be stored in a variable for further use.

## Distinction Between console.log and return
The `console.log` function prints messages to the developer console but does not affect the function's return value. It is a function call inside the function body, used for debugging and output visibility.

This concludes our review of functions. Make sure you understand these concepts well before moving on to the next video, which will present your first coding challenge involving writing your own function.

## Key Takeaways
- Functions can be written as declarations, expressions, or arrow functions, each with unique syntax but similar behavior.
- Function parameters act as local variables, independent across different functions even if named the same.
- The return statement immediately exits a function and outputs a value, preventing any subsequent code in that function from executing.
- Functions can call other functions, enabling modular and reusable code.
