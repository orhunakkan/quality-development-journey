# Introduction to Arrays
Introduction to Arrays
Let's now talk about our first data structure: Arrays. Suppose I want to store my friends' names in variables so that I can use them later in my program. With the knowledge we have so far, this is how we would do it.

```javascript
const friendOne = "Michael";
const friendTwo = "Steven";
const friendThree = "Peter";
```

However, this approach is not very practical. Imagine wanting to represent 10 friends; you would have to create 10 variables. Instead, wouldn't it be great to bundle all these values together into a bigger container? That's why we have data structures in JavaScript, and Arrays are one such data structure.

An Array is like a big container into which we can put variables and later reference them. This is important because programming often involves data: we get data, store and process it, and then output some data. Data needs to be stored somewhere, and for that, we use data structures like Arrays.

The two most important data structures in JavaScript are Arrays and Objects. Now, let's learn all about Arrays.

## Creating Arrays
Instead of creating multiple variables, let's create a variable called friends and use brackets to create a new Array. Inside the brackets, we can put different values separated by commas.

```javascript
const friends = ["Michael", "Steven", "Peter"];
```

Make sure the commas are outside the strings. Each value should be a complete string, followed by a comma, then the next value, and so on. This is a common error to watch out for.

When we log this Array to the console, it looks just as we wrote it in our code.

```javascript
console.log(friends);
```

This is the absolute basics about Arrays: how to create one. There is another way to create Arrays using the Array constructor function.

```javascript
const years = new Array(1991, 1984, 2008, 2020);
```

This creates an Array of numbers. Arrays can hold as many values as we want, and values of any type, not just strings. The new keyword is necessary when using the Array constructor, but it is more common to use the literal syntax with brackets.

## Accessing Array Elements
To get elements out of an Array, we use square bracket syntax again. For example, to log the first element of the friends Array, which is "Michael":

```javascript
console.log(friends[0]);
```

Arrays are zero-based indexed, meaning the first element is at position zero, the second at position one, and so on. To get "Peter", which is the third element, we use index two:

```javascript
console.log(friends[2]);
```

We can also get the number of elements in the Array using the length property:

```javascript
console.log(friends.length);
```

Note that length is not zero-based. It returns the total number of elements, so for our friends Array, it returns 3.

To get the last element of any Array without counting elements, we can use:

```javascript
console.log(friends[friends.length - 1]);
```

Inside the square brackets, we can put any expression that produces a value, not just a literal number. For example, friends.length - 1 is an expression that evaluates to the last index.

## Modifying Arrays
The square bracket syntax can also be used to change or add elements to the Array. For example, if I am no longer friends with Peter and want to replace him with Jay:

```javascript
friends[2] = "Jay";
console.log(friends);
```

Even though friends was declared with const, we can still mutate the Array by changing its elements. This is because only primitive values are immutable, but Arrays are objects and can be mutated. However, we cannot reassign the entire Array to a new value.

```javascript
// This will cause an error
friends = ["Bob", "Ellis"];
```

## Arrays with Multiple Data Types
An Array can hold values of different types simultaneously. For example, let's create an Array with information about me:

```javascript
const firstName = "Jonas";
const jonas = [firstName, "Schmedtmann", 2037 - 1991, "teacher", friends];
console.log(jonas);
```

Here, the Array jonas contains strings, a number calculated from an expression, and even another Array (friends). This is convenient because it bundles all relevant data into one structure.

## Array Exercise: Calculating Ages
Let's use an existing calcAge function to calculate ages from an Array of birth years.

```javascript
function calcAge(year) {
  return 2037 - year;
}

const years = [1990, 1967, 2002, 2010, 2018];
```

We cannot pass the entire years Array to calcAge because it expects a single value, not an Array. For example, calcAge(years) will not work and will result in NaN (not a number). Instead, we can calculate ages for individual elements:

```javascript
const age1 = calcAge(years[0]);
const age2 = calcAge(years[1]);
const age3 = calcAge(years[years.length - 1]);

console.log(age1, age2, age3);
```

We can also create a new Array of ages by calling calcAge for each desired element:

```javascript
const ages = [
  calcAge(years[0]),
  calcAge(years[1]),
  calcAge(years[years.length - 1])
];

console.log(ages);
```

JavaScript evaluates each function call and places the results into the new Array. This demonstrates how Arrays can be used effectively to store and process collections of data.

## Conclusion
Arrays are fun and useful data structures in JavaScript. In the next lecture, we will explore how to perform operations on Arrays to make them even more powerful.

## Key Takeaways
- Arrays are fundamental data structures in JavaScript used to store multiple values in a single container.
- Arrays are zero-based indexed, meaning the first element is at position zero.
- Arrays can hold values of different types simultaneously, including strings, numbers, and even other arrays.
- Although declared with const, arrays can be mutated by changing their elements, but the entire array cannot be reassigned.
