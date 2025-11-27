# Statements and Expressions in JavaScript

## Introduction to Statements and Expressions
Now that we are getting more comfortable with JavaScript, it is time to learn about a more theoretical concept: the difference between statements and expressions. This will be a high-level overview to help future lectures make more sense.

## What is an Expression?
Essentially, an expression is a piece of code that produces a value. For example, `3 + 4` is an expression because it produces a value. It does not have to be the entire line of code; just `3 + 4` itself is an expression because it produces a value.

The same goes for any number. For example, `1991` is also an expression, even though it is just a simple value. It still produces a value in JavaScript.

Boolean values and logical operations are also expressions. For example, `true && false && !false` is an expression because it produces a Boolean value.

## What is a Statement?
On the other hand, a statement is a larger piece of code that is executed and does not produce a value by itself. We can compare this with normal spoken language: a declaration is like a complete sentence, and expressions are like the words that make up the sentences.

We write our whole programs as a sequence of actions, and these actions are statements. For example, the `if...else` statement is a statement.

### Example: If...Else Statement
```javascript
if (23 > 10) {
  const str = '23 is bigger';
}
```

The code itself does not really matter here. The `if...else` statement is a statement. That is why it is called the if...else *statement*. The same is true for the `switch` statement. These statements do not produce a value; they perform actions, such as declaring a variable, but do not themselves produce a value.

## Expressions vs. Statements
The string itself, such as `'23 is bigger'`, is an expression. The whole line of code is a statement because it does not produce a value. Whenever something ends with a semicolon, that is a statement. It is like a complete sentence.

This difference between expressions and statements is important because JavaScript expects statements and expressions in different places.

## Expressions in Template Literals
For example, in a template literal, we can only insert expressions, not statements.

```javascript
const age = 2037 - 1991;
console.log(\`I am \${age} years old.\`);
```

In the placeholder, we need to put an expression—something that produces a value, which can then be put into the string we are building using the template literal. For example, `2037 - 1991` is an expression and will work.

However, we cannot insert an if statement inside a template literal. It would not make any sense.

```javascript
console.log(\`I am \${if (23 > 10) { '23 is bigger'; }} years old.\`);
```

If we try this, we get an error: unexpected token `if`. That is because JavaScript knows this is a statement, and statements do not make sense where JavaScript expects an expression.

If we had a variable, we could use that in the template literal, and it would be an expression. The variable will be replaced with its value, which produces a value and is acceptable.

```javascript
const str = '23 is bigger';
console.log(\`I am \${str} years old.\`);
```

## Conclusion
The main goal is to understand the difference between expressions and statements. Expressions produce values, and statements are like full sentences that translate our actions into code. This distinction is important as we move forward.

## Key Takeaways
- Expressions in JavaScript are pieces of code that produce values.  
- Statements are larger pieces of code that perform actions but do not produce values themselves.  
- JavaScript expects expressions and statements in different contexts, such as only allowing expressions inside template literals.  
- Understanding the distinction between statements and expressions is important for writing correct JavaScript code.
